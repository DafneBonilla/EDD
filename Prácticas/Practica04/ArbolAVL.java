/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

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
        /** El padre del vértice. */
        public VerticeAVL padre;
        /** El izquierdo del vértice. */
        public VerticeAVL izquierdo;
        /** El derecho del vértice. */
        public VerticeAVL derecho;

        /**
         * Constructor único que recibe un elemento.
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
        public int getAltura() {
            return altura;
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

         @Override public boolean equals(Object o){
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

        private boolean equalsAuxDer(VerticeAVL a,VerticeAVL b){
            if(a.hayDerecho() && b.hayDerecho() && b.elemento.equals(a.elemento) && a.altura == b.altura){
                return a.derecho.equals(b.derecho);
            }
            if(!a.hayDerecho() && !b.hayDerecho() && b.elemento.equals(a.elemento) && a.altura == b.altura){
                return true;
            }
            if(a.hayDerecho() && !b.hayDerecho()){
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
            VerticeAVL hijoIzq = this.padre.izquierdo;
            if (hijoIzq.equals(this)) {
                return true;
            }
            return false;
        }
    }

    /** La raíz del árbol. */
    protected VerticeAVL raiz;

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
     * Regresa una representación en cadena del árbol.
     * 
     * @return una representación en cadena del árbol.
     */
    @Override
    public String toString() {
        String respuesta = "";
        for (T i : this) {
            respuesta += i + " ";
        }
        return respuesta + "\n";
    }

    /**
     * Método auxiliar de toString para dibujar espacios.
     * 
     * @return una represetación en cadena de espacios.
     */
    private String dibujaEspacios(int l, int[] a, int n) {
        String s = "";
        for (int i = 0; i < l; i++) {
            if (a[i] == 1) {
                s = s + "│  ";
            } else {
                s = s + "   ";
            }
        }
        return s;
    }

    /**
     * Metodo auxiliar de toString.
     *
     * @return una representación en cadena del árbol.
     */
    private String toStringBonito(Vertice v, int l, int[] m) {
        String s = v.toString() + "\n";
        m[l] = 1;

        if (v.izquierdo != null && v.derecho != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "├─›";
            s = s + toStringBonito(v.izquierdo, l + 1, m);
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─»";
            m[l] = 0;
            s = s + toStringBonito(v.derecho, l + 1, m);
        } else if (v.izquierdo != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─›";
            m[l] = 0;
            s = s + toStringBonito(v.izquierdo, l + 1, m);
        } else if (v.derecho != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─»";
            m[l] = 0;
            s = s + toStringBonito(v.derecho, l + 1, m);
        }
        return s;
    }

    /**
     * Regresa una representación en cadena del árbol.
     * 
     * @return una representación en cadena del árbol.
     */
    public String toStringBonito() {
        if (this.raiz == null) {
            return "";
        }
        int[] a = new int[this.altura() + 1];
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;
        }
        return toStringBonito(this.raiz, 0, a);
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
     * @return el número de la altura del árbol.
     */
    public int getAltura() {
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
            raiz = new VerticeAVL(elemento);
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
        if (Math.abs(alturaIzq -  alturaDer) == 2) {
            rebalancear(nuevo);
            return;
        }
        nuevo.altura = 1 + Math.max(alturaIzq, alturaDer);
        if (nuevo.hayPadre()) {
            actualizarAlturas(nuevo.padre);            
        }
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
        if (hd.altura == hi.altura + 2) {
            int k = hi.altura;
            VerticeAVL wi = hd.izquierdo;
            VerticeAVL wd = hd.derecho;
            if (wd.altura == k + 1) {
                rotarIzq(v);
                actualizarAlturas(hd);
                return;
            } else if (wd.altura == k) {
                rotarDer(hd);
                rotarIzq(v);
                actualizarAlturas(wi);
                return;
            }
        } else if (hi.altura == hd.altura + 2) {
            int k = hd.altura;
            VerticeAVL wi = hi.izquierdo;
            VerticeAVL wd = hi.derecho;
            if (wi.altura == k + 1) {
                rotarDer(v);
                actualizarAlturas(hi);
                return;
            } else if (wi.altura == k) {
                rotarIzq(hi);
                rotarDer(v);
                actualizarAlturas(wd);
                return;
            }
        }
        
    }

    private void rotarIzq(VerticeAVL v) {
        VerticeAVL hijoDer = v.derecho;
        VerticeAVL padreZ = v.padre;
        VerticeAVL nietoIzq = hijoDer.izquierdo;
        if (raiz == v) {
            v.padre = hijoDer;
            hijoDer.izquierdo = v;
            hijoDer.padre = null;
            v.derecho = nietoIzq;
            nietoIzq.padre = v;
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
        nietoIzq.padre = v;
    }

    private void rotarDer(VerticeAVL v) {
        VerticeAVL hijoIzq = v.izquierdo;
        VerticeAVL padreZ = v.padre;
        VerticeAVL nietoDer = hijoIzq.derecho;
        if (raiz == v) {
            v.padre = hijoIzq;
            hijoIzq.derecho = v;
            hijoIzq.padre = null;
            v.izquierdo = nietoDer;
            nietoDer.padre = v;
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
        nietoDer.padre = v;
    }
}