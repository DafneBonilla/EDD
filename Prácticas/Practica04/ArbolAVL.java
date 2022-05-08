/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

/**
 * ArbolAVL
 * Implementación de un árbol binario AVL.
 * 
 * @author Dafne Bonilla Reyes, José Camilo García Ponce
 * @version 06-05-2022
 */
public class ArbolAVL<T extends Comparable<T>> extends ArbolBST<T> {

    /**
     * Clase interna protegida para vértices AVL.
     */
    protected class VerticeAVL extends Vertice {

        /** La altura del vertice. */
        private int altura;

        /**
         * Constructor único que recibe un elemento.
         * 
         * @param elemento el elemento del vértice.
         */
        public VerticeAVL(T elemento) {
            super(elemento);
            this.altura = 0;
        }

        /***
         * Regresa la altura del vértice.
         * 
         * @return el número de la altura.
         */
        @Override
        public int altura() {
            return altura;
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * 
         * @param o el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeRojinegro}, su elemento es igual al elemento de
         *         éste vértice, los descendientes de ambos son recursivamente
         *         iguales, y los colores son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked")
            VerticeAVL vertice = (VerticeAVL) o;
            return altura == vertice.altura && super.equals(o);
        }

        /**
         * Regresa una representación en cadena del vértice.
         * 
         * @return una representación en cadena del vértice.
         */
        @Override
        public String toString() {
            return elemento.toString() + " ," + altura;
        }
    }

    /**
     * Regresa un vértice AVL a partir de un vértice de árbol binario.
     * 
     * @param vertice el vertice del árbol binario
     * @return el vertice AVL.
     */
    protected VerticeAVL convertirVerticeAVL(VerticeArbolBinario<T> vertice) {
        return (VerticeAVL) vertice;
    }

    /**
     * Construye un árbol AVL vacío.
     */
    public ArbolAVL() {
        super();
    }

    /**
     * Construye un árbol AVL a partir de un arból. El árbol
     * AVL tiene los mismos elementos que la colección recibida.
     * 
     * @param coleccion la colección a partir de la cual creamos el árbol
     *                  AVL.
     */
    public ArbolAVL(ArbolBinario<T> coleccion) {
        super();
        for (T elem : coleccion) {
            this.add(elem);
        }
    }

    /**
     * Regresa la altura del árbol.
     * 
     * @return el número de la altura del árbol.
     */
    public int getAltura() {
        if (raiz == null) {
            return -1;
        }
        VerticeAVL raizAVL = convertirVerticeAVL(raiz);
        return raizAVL.altura;
    }

    /**
     * Agrega un elemento al árbol.
     * 
     * @param elemento el elemento a agregar.
     */
    @Override
    public void add(T elemento) {
        if (elemento == null) {
            return;
        }
        if (raiz == null) {
            this.raiz = new VerticeAVL(elemento);
            elementos++;
            return;
        }
        insert(raiz, elemento);
    }

    /**
     * Inserta un elemento en el árbol.
     * 
     * @param raiz     la raiz del árbol donde insertaremos.
     * @param elemento el elemento a insertar.
     */
    private void insert(Vertice raiz, T u) {
        if (u.compareTo(raiz.elemento) < 0) {
            if (raiz.izquierdo == null) {
                VerticeAVL nuevo = new VerticeAVL(u);
                raiz.izquierdo = nuevo;
                nuevo.padre = raiz;
                elementos++;
                actualizarAlturas(nuevo);
            } else {
                insert(raiz.izquierdo, u);
            }
        }
        if (u.compareTo(raiz.elemento) > 0) {
            if (raiz.derecho == null) {
                VerticeAVL nuevo = new VerticeAVL(u);
                raiz.derecho = nuevo;
                nuevo.padre = raiz;
                elementos++;
                actualizarAlturas(nuevo);
            } else {
                insert(raiz.derecho, u);
            }
        }
    }

    /**
     * Elimina un elemento del árbol.
     * 
     * @param elemento el elemento a eliminar.
     * @return <code>true</code> si el elemento se eliminó
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean delete(T elemento) {
        if (raiz == null) {
            return false;
        }
        Vertice buscado = buscaVertice(raiz, elemento);
        if (buscado == null) {
            return false;
        }
        if (!buscado.hayIzquierdo() && !buscado.hayDerecho()) {
            if (buscado.hayPadre()) {
                Vertice ancestro = buscado.padre;
                if (ancestro.hayIzquierdo() && ancestro.hayDerecho()) {
                    if (ancestro.izquierdo.equals(buscado)) {
                        ancestro.izquierdo = null;
                        elementos--;
                        rebalancear(ancestro);
                        return true;
                    } else {
                        ancestro.derecho = null;
                        elementos--;
                        rebalancear(ancestro);
                        return true;
                    }
                } else if (ancestro.hayIzquierdo()) {
                    ancestro.izquierdo = null;
                    elementos--;
                    rebalancear(ancestro);
                    return true;
                } else {
                    ancestro.derecho = null;
                    elementos--;
                    rebalancear(ancestro);
                    return true;
                }
            } else {
                raiz = null;
                elementos = 0;
                return true;
            }
        }
        if (buscado.hayIzquierdo() && !buscado.hayDerecho()) {
            Vertice ancestro = buscado.padre;
            ancestro.izquierdo = buscado.izquierdo;
            buscado.izquierdo.padre = ancestro;
            elementos--;
            rebalancear(ancestro);
            return true;
        }
        if (!buscado.hayIzquierdo() && buscado.hayDerecho()) {
            Vertice ancestro = buscado.padre;
            ancestro.derecho = buscado.derecho;
            buscado.derecho.padre = ancestro;
            elementos--;
            rebalancear(ancestro);
            return true;
        }
        if (buscado.hayIzquierdo() && buscado.hayDerecho()) {
            Vertice hijito = buscaMaximo(buscado.izquierdo);
            T aux = buscado.elemento;
            buscado.elemento = hijito.elemento;
            hijito.elemento = aux;
            return this.delete2(hijito);
        }
        return false;
    }

    /**
     * Elimina un vertice del árbol.
     * 
     * @param elemento el vertice a eliminar.
     * @return <code>true</code> si el vertice se eliminó
     *         <code>false</code> en otro caso.
     */
    public boolean delete2(Vertice elemento) {
        if (raiz == null) {
            return false;
        }
        if (elemento == null) {
            return false;
        }
        if (!elemento.hayIzquierdo() && !elemento.hayDerecho()) {
            if (elemento.hayPadre()) {
                Vertice ancestro = elemento.padre;
                if (ancestro.hayIzquierdo() && ancestro.hayDerecho()) {
                    if (ancestro.izquierdo.equals(elemento)) {
                        ancestro.izquierdo = null;
                        elementos--;
                        rebalancear(ancestro);
                        return true;
                    } else {
                        ancestro.derecho = null;
                        elementos--;
                        rebalancear(ancestro);
                        return true;
                    }
                } else if (ancestro.hayIzquierdo()) {
                    ancestro.izquierdo = null;
                    elementos--;
                    rebalancear(ancestro);
                    return true;
                } else {
                    ancestro.derecho = null;
                    elementos--;
                    rebalancear(ancestro);
                    return true;
                }
            } else {
                raiz = null;
                elementos = 0;
                return true;
            }
        }
        if (elemento.hayIzquierdo() && !elemento.hayDerecho()) {
            Vertice ancestro = elemento.padre;
            ancestro.izquierdo = elemento.izquierdo;
            elemento.izquierdo.padre = ancestro;
            elementos--;
            rebalancear(ancestro);
            return true;
        }
        if (!elemento.hayIzquierdo() && elemento.hayDerecho()) {
            Vertice ancestro = elemento.padre;
            ancestro.derecho = elemento.derecho;
            elemento.derecho.padre = ancestro;
            elementos--;
            rebalancear(ancestro);
            return true;
        }
        if (elemento.hayIzquierdo() && elemento.hayDerecho()) {
            Vertice hijito = buscaMaximo(elemento.izquierdo);
            T aux = elemento.elemento;
            elemento.elemento = hijito.elemento;
            hijito.elemento = aux;
            return this.delete2(hijito);
        }
        return false;
    }

    /**
     * Actualiza la altura de los vertices.
     * 
     * @param nuevo el vértice AVL que se va a actualizar.
     */
    private void actualizarAlturas(Vertice v) {
        int alturaIzq = -1;
        int alturaDer = -1;
        if (v.hayIzquierdo()) {
            alturaIzq = convertirVerticeAVL(v.izquierdo).altura;
        }
        if (v.hayDerecho()) {
            alturaDer = convertirVerticeAVL(v.derecho).altura;
        }
        if (Math.abs(alturaIzq - alturaDer) == 2) {
            rebalancear(v);
            return;
        }
        int nuevaAlt = 1 + Math.max(alturaIzq, alturaDer);
        convertirVerticeAVL(v).altura = nuevaAlt;
        if (!v.hayPadre()) {
            return;
        }
        actualizarAlturas(v.padre);
    }

    /**
     * Rebalancea un árbol AVL.
     * 
     * @param ancestro el vertice a rebalancear.
     */
    private void rebalancear(Vertice ancestro) {
        if (ancestro == null) {
            return;
        }
        Vertice hi = ancestro.izquierdo;
        Vertice hd = ancestro.derecho;
        int hiAltura = -1;
        int hdAltura = -1;
        if (hi != null) {
            hiAltura = convertirVerticeAVL(hi).altura;
        }
        if (hd != null) {
            hdAltura = convertirVerticeAVL(hd).altura;
        }
        if (hdAltura == hiAltura + 2) {
            int k = hiAltura;
            Vertice wi = hd.izquierdo;
            Vertice wd = hd.derecho;
            int wdAltura = -1;
            if (wd != null) {
                wdAltura = convertirVerticeAVL(wd).altura;
            }
            if (wdAltura == k + 1) {
                rotarIzq(ancestro);
                // actualizarAlturas(hd);
                actualizarAlturas(ancestro);
                return;
            } else if (wdAltura == k) {
                rotarDer(hd);
                rotarIzq(ancestro);
                actualizarAlturas(wi);
                return;
            }
        } else if (hiAltura == hdAltura + 2) {
            int k = hdAltura;
            Vertice wi = hi.izquierdo;
            Vertice wd = hi.derecho;
            int wiAltura = -1;
            if (wi != null) {
                wiAltura = convertirVerticeAVL(wi).altura;
            }
            if (wiAltura == k + 1) {
                rotarDer(ancestro);
                // actualizarAlturas(hi);
                actualizarAlturas(ancestro);
                return;
            } else if (wiAltura == k) {
                //rotarIzq(hi);
                rotarDer(ancestro);
                // actualizarAlturas(wd);
                return;
            }
        }
    }

    private void rotarIzq(Vertice v) {
        if (!v.hayDerecho()) {
            return;
        }
        Vertice hijoDer = v.derecho;
        Vertice padreZ = v.padre;
        Vertice nietoIzq = hijoDer.izquierdo;
        v.padre = hijoDer;
        hijoDer.izquierdo = v;
        hijoDer.padre = padreZ;
        if (v.esHijoIzq()) {
            if (padreZ != null) {
                padreZ.izquierdo = hijoDer;
            }
        } else if (v.esHijoDer()) {
            if (padreZ != null) {
                padreZ.derecho = hijoDer;
            }
        }
        v.derecho = nietoIzq;
        if (nietoIzq != null) {
            nietoIzq.padre = v;
        }
        if (padreZ == null) {
            raiz = hijoDer;
        }
    }

    private void rotarDer(Vertice v) {
        if (!v.hayIzquierdo()) {
            return;
        }
        Vertice hijoIzq = v.izquierdo;
        Vertice padreZ = v.padre;
        Vertice nietoDer = hijoIzq.derecho;
        v.padre = hijoIzq; 
        hijoIzq.derecho = v;
        hijoIzq.padre = padreZ;
        if (v.esHijoIzq()) {
            if (padreZ != null) {
                padreZ.izquierdo = hijoIzq;
            }
        } else if (v.esHijoDer()) {
            if (padreZ != null) {
                padreZ.derecho = hijoIzq;
            }
        }
        v.izquierdo = nietoDer;
        if (nietoDer != null) {
            nietoDer.padre = v;
        }
        if (padreZ == null) {
            raiz = hijoIzq;
        }
    }
}