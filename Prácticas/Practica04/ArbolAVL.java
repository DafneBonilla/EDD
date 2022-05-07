/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

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

        private void setAltura(int altura) {
            this.altura = altura;
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>. Las clases que extiendan {@link Vertice} deben
         * sobrecargar el método {@link Vertice#equals}.
         * 
         * @param o el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link Vertice}, su elemento es igual al elemento de éste
         *         vértice, y los descendientes de ambos son recursivamente
         *         iguales; <code>false</code> en otro caso.
         */

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked")
            VerticeAVL vertice = (VerticeAVL) o;
            if (!this.elemento.equals(vertice.elemento)) {
                return false;
            }
            if (this.altura != vertice.altura) {
                return false;
            }
            return equalsAuxDer(this, vertice) && equalsAuxIzq(this, vertice);
        }

        private boolean equalsAuxDer(VerticeAVL a, VerticeAVL b) {
            if (a.hayDerecho() && b.hayDerecho() && b.elemento.equals(a.elemento) && a.altura == b.altura) {
                return a.derecho.equals(b.derecho);
            }
            if (!a.hayDerecho() && !b.hayDerecho() && b.elemento.equals(a.elemento) && a.altura == b.altura) {
                return true;
            }
            if (a.hayDerecho() && !b.hayDerecho()) {
                return false;
            }
            if (!a.hayDerecho() && b.hayDerecho()) {
                return false;
            }
            return false;
        }

        private boolean equalsAuxIzq(VerticeAVL a, VerticeAVL b) {
            if (a.hayIzquierdo() && b.hayIzquierdo() && b.elemento.equals(a.elemento) && a.altura == b.altura) {
                return a.izquierdo.equals(b.izquierdo);
            }
            if (!a.hayIzquierdo() && !b.hayIzquierdo() && b.elemento.equals(a.elemento) && a.altura == b.altura) {
                return true;
            }
            if (a.hayIzquierdo() && !b.hayIzquierdo()) {
                return false;
            }
            if (!a.hayIzquierdo() && b.hayIzquierdo()) {
                return false;
            }
            return false;
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

        public boolean esHijoIzq() {
            if (!hayPadre()) {
                return false;
            }
            Vertice hijoIzq = this.padre.izquierdo;
            if (hijoIzq.equals(this)) {
                return true;
            }
            return false;
        }
    }

    // revisar todo abajo unu

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
     * Regresa un vértice AVL a partir de un vértice de árbol binario.
     * 
     * @param vertice el vertice del árbol binario
     * @return el vertice AVL.
     */
    protected VerticeAVL convertirVerticeAVL(VerticeArbolBinario<T> vertice) {
        return (VerticeAVL) vertice;
    }

    /**
     * Compara el árbol con un objeto.
     * 
     * @param o el objeto con el que queremos comparar el árbol.
     * @return <code>true</code> si el objeto recibido es un árbol binario y los
     *         árboles son iguales; <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        ArbolBinario<T> arbol = (ArbolBinario<T>) o;
        if (isEmpty()) {
            return arbol.isEmpty();
        }
        return this.raiz.equals(arbol.raiz);
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
        return raiz.altura;
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
    private void insert(VerticeAVL raiz, T u) {
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
        VerticeAVL buscado = buscaVertice(raiz, elemento);
        if (buscado == null) {
            return false;
        }
        if (!buscado.hayIzquierdo() && !buscado.hayDerecho()) {
            if (buscado.hayPadre()) {
                VerticeAVL ancestro = buscado.padre;
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
            VerticeAVL ancestro = buscado.padre;
            ancestro.izquierdo = buscado.izquierdo;
            buscado.izquierdo.padre = ancestro;
            elementos--;
            rebalancear(ancestro);
            return true;
        }
        if (!buscado.hayIzquierdo() && buscado.hayDerecho()) {
            VerticeAVL ancestro = buscado.padre;
            ancestro.derecho = buscado.derecho;
            buscado.derecho.padre = ancestro;
            elementos--;
            rebalancear(ancestro);
            return true;
        }
        if (buscado.hayIzquierdo() && buscado.hayDerecho()) {
            VerticeAVL hijito = buscaMinimo(buscado.derecho);
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
    public boolean delete2(VerticeAVL elemento) {
        if (raiz == null) {
            return false;
        }
        if (elemento == null) {
            return false;
        }
        if (!elemento.hayIzquierdo() && !elemento.hayDerecho()) {
            if (elemento.hayPadre()) {
                VerticeAVL ancestro = elemento.padre;
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
            VerticeAVL ancestro = elemento.padre;
            ancestro.izquierdo = elemento.izquierdo;
            elemento.izquierdo.padre = ancestro;
            elementos--;
            rebalancear(ancestro);
            return true;
        }
        if (!elemento.hayIzquierdo() && elemento.hayDerecho()) {
            VerticeAVL ancestro = elemento.padre;
            ancestro.derecho = elemento.derecho;
            elemento.derecho.padre = ancestro;
            elementos--;
            rebalancear(ancestro);
            return true;
        }
        if (elemento.hayIzquierdo() && elemento.hayDerecho()) {
            VerticeAVL hijito = buscaMinimo(elemento.derecho);
            T aux = elemento.elemento;
            elemento.elemento = hijito.elemento;
            hijito.elemento = aux;
            return this.delete2(hijito);
        }
        return false;
    }

    /**
     * Ve si el árbol contiene un elemento y regresa el vértice que lo contiene.
     * 
     * @param elemento el elemento a buscar.
     * @param raiz     la raíz del árbol donde buscaremos.
     * @return el vértice que contiene el elemento.
     */
    public VerticeAVL buscaVertice(VerticeAVL raiz, T elemento) {
        if (raiz == null) {
            return null;
        }
        if (raiz.elemento.equals(elemento)) {
            return raiz;
        }
        if (elemento.compareTo(raiz.elemento) < 0) {
            return buscaVertice(raiz.izquierdo, elemento);
        }
        if (elemento.compareTo(raiz.elemento) > 0) {
            return buscaVertice(raiz.derecho, elemento);
        }
        return null;
    }

    /**
     * Regresa el mínimo de un árbol.
     * 
     * @param raiz la raiz del árbol.
     * @return el vertice con el minimo del árbol.
     */
    private VerticeAVL buscaMinimo(VerticeAVL raiz) {
        if (raiz.hayIzquierdo()) {
            return buscaMinimo(raiz.izquierdo);
        }
        return raiz;
    }

    /**
     * Actualiza la altura de los vertices.
     * 
     * @param nuevo el vértice AVL que se va a actualizar.
     */
    private void actualizarAlturas(VerticeAVL nuevo) {
        int alturaIzq = -1;
        int alturaDer = -1;
        if (nuevo.hayIzquierdo()) {
            alturaIzq = nuevo.izquierdo.altura;
        }
        if (nuevo.hayDerecho()) {
            alturaDer = nuevo.derecho.altura;
        }
        if (Math.abs(alturaIzq - alturaDer) == 2) {
            rebalancear(nuevo);
            return;
        }
        int nuevaAlt = 1 + Math.max(alturaIzq, alturaDer);
        nuevo.setAltura(nuevaAlt);
        if (!nuevo.hayPadre()) {
            return;
        }
        actualizarAlturas(nuevo.padre);
    }

    /**
     * Rebalancea un árbol AVL.
     * 
     * @param v el vertice a rebalancear.
     */
    private void rebalancear(VerticeAVL v) {
        if (v == null) {
            return;
        }
        VerticeAVL hi = v.izquierdo;
        VerticeAVL hd = v.derecho;
        int hiAltura = -1;
        int hdAltura = -1;
        if (hi != null) {
            hiAltura = hi.altura;
        }
        if (hd != null) {
            hdAltura = hd.altura;
        }
        if (hdAltura == hiAltura + 2) {
            int k = hiAltura;
            VerticeAVL wi = hd.izquierdo;
            VerticeAVL wd = hd.derecho;
            int wdAltura = -1;
            if (wd != null) {
                wdAltura = wd.altura;
            }
            if (wdAltura == k + 1) {
                rotarIzq(v);
                // actualizarAlturas(hd);
                actualizarAlturas(v);
                return;
            } else if (wdAltura == k) {
                rotarDer(hd);
                rotarIzq(v);
                actualizarAlturas(wi);
                return;
            }
        } else if (hiAltura == hdAltura + 2) {
            int k = hdAltura;
            VerticeAVL wi = hi.izquierdo;
            VerticeAVL wd = hi.derecho;
            int wiAltura = -1;
            if (wi != null) {
                wiAltura = wi.altura;
            }
            if (wiAltura == k + 1) {
                rotarDer(v);
                // actualizarAlturas(hi);
                actualizarAlturas(v);
                return;
            } else if (wiAltura == k) {
                rotarIzq(hi);
                rotarDer(v);
                actualizarAlturas(wd);
                return;
            }
        }

    }

    private void rotarIzq(VerticeAVL v) {
        if (!v.hayDerecho()) {
            return;
        }
        VerticeAVL hijoDer = v.derecho;
        VerticeAVL padreZ = v.padre;
        VerticeAVL nietoIzq = hijoDer.izquierdo;
        if (raiz.equals(v)) {
            v.padre = hijoDer;
            hijoDer.izquierdo = v;
            hijoDer.padre = null;
            v.derecho = nietoIzq;
            if (nietoIzq != null) {
                nietoIzq.padre = v;
            }
            raiz = hijoDer;
            return;
        }
        v.padre = hijoDer;
        hijoDer.izquierdo = v;
        hijoDer.padre = padreZ;
        if (v.esHijoIzq()) {
            padreZ.izquierdo = hijoDer;
        } else {
            padreZ.derecho = hijoDer;
        }
        v.derecho = nietoIzq;
        if (nietoIzq != null) {
            nietoIzq.padre = v;
        }
    }

    private void rotarDer(VerticeAVL v) {
        if (!v.hayIzquierdo()) {
            return;
        }
        VerticeAVL hijoIzq = v.izquierdo;
        VerticeAVL padreZ = v.padre;
        VerticeAVL nietoDer = hijoIzq.derecho;
        if (raiz.equals(v)) {
            v.padre = hijoIzq;
            hijoIzq.derecho = v;
            hijoIzq.padre = null;
            v.izquierdo = nietoDer;
            if (nietoDer != null) {
                nietoDer.padre = v;
            }
            raiz = hijoIzq;
            return;
        }
        v.padre = hijoIzq;
        hijoIzq.derecho = v;
        hijoIzq.padre = padreZ;
        if (v.esHijoIzq()) {
            padreZ.izquierdo = hijoIzq;
        } else {
            padreZ.derecho = hijoIzq;
        }
        v.izquierdo = nietoDer;
        if (nietoDer != null) {
            nietoDer.padre = v;
        }
    }

}