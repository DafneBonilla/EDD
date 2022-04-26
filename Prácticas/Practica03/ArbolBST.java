import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

public class ArbolBST<T extends Comparable<T>> extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios. */
    private class Iterador implements Iterator<T> {
        /* Cola para recorrer los vértices en in order. */
        private Cola<Vertice> cola;

        /* Constructor de iterador. */
        public Iterador() {
            cola = new Cola<Vertice>();
            if (isEmpty())
                return;
            cola.push(buscaMinimo(raiz));
        }

        /* Nos dice si hay un elemento siguiente. */
        public boolean hasNext() {
            return !cola.isEmpty();
        }

        /* Dice si hay un elemento siguiente. */
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Vertice v = cola.pop();
            if (v.padre != null)
                cola.push(v.padre);
            if (v.derecho != null)
                cola.push(v.derecho);
            return v.elemento;
        }
    }

    public ArbolBST() {
        raiz = null;
    }

    public ArbolBST(Lista<T> lista, boolean sorted) {
        if (lista.isEmpty()) {
            raiz = null;
            return;
        }
        if (!sorted) {
            raiz = buildUnsorted(lista).raiz;
        }
        raiz = buildSorted(lista).raiz;
    }

    public ArbolBST(ArbolBinario<T> arbol) {
        Lista<T> lista = new Lista<T>();
        for (T i : arbol) {
            lista.add(i);
        }
        raiz = buildUnsorted(lista).raiz;
    }

    private ArbolBST<T> buildUnsorted(Lista<T> lista) {
        if (lista.isEmpty()) {
            return new ArbolBST<T>();
        }
        Lista<T> ordenada = lista.mergeSort((a, b) -> a.compareTo(b));
        return buildSorted(ordenada);
    }

    private ArbolBST<T> buildSorted(Lista<T> lista) {
        if (lista.isEmpty()) {
            return new ArbolBST<T>();
        }
        int mitad = lista.size() / 2;
        raiz = new Vertice(lista.buscarIndice(mitad));
        Lista<T> izq = new Lista<T>();
        Lista<T> der = new Lista<T>();
        int conta = 0;
        for (T e : lista) {
            if (conta < mitad) {
                izq.agregaFinal(e);
            } else {
                der.agregaFinal(e);
            }
            conta++;
        }
        ArbolBST<T> izquierdo = buildSorted(izq);
        ArbolBST<T> derecho = buildSorted(der);
        if (izquierdo.raiz != null) {
            raiz.izquierdo = izquierdo.raiz;
        }
        if (derecho.raiz != null) {
            raiz.derecho = derecho.raiz;
        }
        ArbolBST<T> arbolito = new ArbolBST<T>();
        arbolito.raiz = raiz;
        return arbolito;
    }

    public boolean search(Vertice raiz, T elemento) {
        if (raiz == null) {
            return false;
        }
        if (raiz.elemento.equals(elemento)) {
            return true;
        }
        if (elemento.compareTo(raiz.elemento) < 0) {
            return search(raiz.izquierdo, elemento);
        }
        if (elemento.compareTo(raiz.elemento) > 0) {
            return search(raiz.derecho, elemento);
        }
        return false;
    }

    public Vertice buscaVertice(Vertice raiz, T elemento) {
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

    // ignora repetidos
    @Override
    public void add(T elemento) {
        if (raiz == null) {
            raiz = new Vertice(elemento);
            elementos++;
        }
        insert(raiz, elemento);
    }

    private void insert(Vertice raiz, T u) {
        if (u.compareTo(raiz.elemento) < 0) {
            if (raiz.izquierdo == null) {
                Vertice nuevo = new Vertice(u);
                raiz.izquierdo = nuevo;
                nuevo.padre = raiz;
                elementos++;
            } else {
                insert(raiz.izquierdo, u);
            }
        }
        if (u.compareTo(raiz.elemento) > 0) {
            if (raiz.derecho == null) {
                Vertice nuevo = new Vertice(u);
                raiz.derecho = nuevo;
                nuevo.padre = raiz;
                elementos++;
            } else {
                insert(raiz.derecho, u);
            }
        }
    }

    /**
     * @param elemento el elemento a eliminar.
     * @return
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
                        return true;
                    } else {
                        ancestro.derecho = null;
                        elementos--;
                        return true;
                    }
                } else if (ancestro.hayIzquierdo()) {
                    ancestro.izquierdo = null;
                    elementos--;
                    return true;
                } else {
                    ancestro.derecho = null;
                    elementos--;
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
            return true;
        }
        if (!buscado.hayIzquierdo() && buscado.hayDerecho()) {
            Vertice ancestro = buscado.padre;
            ancestro.derecho = buscado.derecho;
            buscado.derecho.padre = ancestro;
            elementos--;
            return true;
        }
        if (buscado.hayIzquierdo() && buscado.hayDerecho()) {
            Vertice hijito = buscaMinimo(buscado.derecho);
            T aux = buscado.elemento;
            buscado.elemento = hijito.elemento;
            hijito.elemento = aux;
            return this.delete2(hijito);
        }
        return false;
    }

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
                        return true;
                    } else {
                        ancestro.derecho = null;
                        elementos--;
                        return true;
                    }
                } else if (ancestro.hayIzquierdo()) {
                    ancestro.izquierdo = null;
                    elementos--;
                    return true;
                } else {
                    ancestro.derecho = null;
                    elementos--;
                    return true;
                }
            } else {
                raiz = null;
                elementos--;
                return true;
            }
        }
        if (elemento.hayIzquierdo() && !elemento.hayDerecho()) {
            Vertice ancestro = elemento.padre;
            ancestro.izquierdo = elemento.izquierdo;
            elemento.izquierdo.padre = ancestro;
            elementos--;
            return true;
        }
        if (!elemento.hayIzquierdo() && elemento.hayDerecho()) {
            Vertice ancestro = elemento.padre;
            ancestro.derecho = elemento.derecho;
            elemento.derecho.padre = ancestro;
            elementos--;
            return true;
        }
        if (elemento.hayIzquierdo() && elemento.hayDerecho()) {
            Vertice hijito = buscaMinimo(elemento.derecho);
            T aux = elemento.elemento;
            elemento.elemento = hijito.elemento;
            hijito.elemento = aux;
            return this.delete2(hijito);
        }
        return false;
    }

    /**
     * @param elemento el elemento a eliminar.
     * @return
     */
    public T delete3(T elemento) {
        if (raiz == null) {
            return null;
        }
        Vertice buscado = buscaVertice(raiz, elemento);
        if (buscado == null) {
            return null;
        }
        if (!buscado.hayIzquierdo() && !buscado.hayDerecho()) {
            if (buscado.hayPadre()) {
                Vertice ancestro = buscado.padre;
                if (ancestro.hayIzquierdo() && ancestro.hayDerecho()) {
                    if (ancestro.izquierdo.equals(buscado)) {
                        ancestro.izquierdo = null;
                        elementos--;
                        return buscado.elemento;
                    } else {
                        ancestro.derecho = null;
                        elementos--;
                        return buscado.elemento;
                    }
                } else if (ancestro.hayIzquierdo()) {
                    ancestro.izquierdo = null;
                    elementos--;
                    return buscado.elemento;
                } else {
                    ancestro.derecho = null;
                    elementos--;
                    return buscado.elemento;
                }
            } else {
                raiz = null;
                elementos = 0;
                return buscado.elemento;
            }
        }
        if (buscado.hayIzquierdo() && !buscado.hayDerecho()) {
            Vertice ancestro = buscado.padre;
            ancestro.izquierdo = buscado.izquierdo;
            buscado.izquierdo.padre = ancestro;
            elementos--;
            return buscado.elemento;
        }
        if (!buscado.hayIzquierdo() && buscado.hayDerecho()) {
            Vertice ancestro = buscado.padre;
            ancestro.derecho = buscado.derecho;
            buscado.derecho.padre = ancestro;
            elementos--;
            return buscado.elemento;
        }
        if (buscado.hayIzquierdo() && buscado.hayDerecho()) {
            Vertice hijito = buscaMinimo(buscado.derecho);
            T aux = buscado.elemento;
            buscado.elemento = hijito.elemento;
            hijito.elemento = aux;
            return this.delete4(hijito);
        }
        return null;
    }

    public T delete4(Vertice elemento) {
        if (raiz == null) {
            return null;
        }
        if (elemento == null) {
            return null;
        }
        if (!elemento.hayIzquierdo() && !elemento.hayDerecho()) {
            if (elemento.hayPadre()) {
                Vertice ancestro = elemento.padre;
                if (ancestro.hayIzquierdo() && ancestro.hayDerecho()) {
                    if (ancestro.izquierdo.equals(elemento)) {
                        ancestro.izquierdo = null;
                        elementos--;
                        return elemento.elemento;
                    } else {
                        ancestro.derecho = null;
                        elementos--;
                        return elemento.elemento;
                    }
                } else if (ancestro.hayIzquierdo()) {
                    ancestro.izquierdo = null;
                    elementos--;
                    return elemento.elemento;
                } else {
                    ancestro.derecho = null;
                    elementos--;
                    return elemento.elemento;
                }
            } else {
                raiz = null;
                elementos--;
                return elemento.elemento;
            }
        }
        if (elemento.hayIzquierdo() && !elemento.hayDerecho()) {
            Vertice ancestro = elemento.padre;
            ancestro.izquierdo = elemento.izquierdo;
            elemento.izquierdo.padre = ancestro;
            elementos--;
            return elemento.elemento;
        }
        if (!elemento.hayIzquierdo() && elemento.hayDerecho()) {
            Vertice ancestro = elemento.padre;
            ancestro.derecho = elemento.derecho;
            elemento.derecho.padre = ancestro;
            elementos--;
            return elemento.elemento;
        }
        if (elemento.hayIzquierdo() && elemento.hayDerecho()) {
            Vertice hijito = buscaMinimo(elemento.derecho);
            T aux = elemento.elemento;
            elemento.elemento = hijito.elemento;
            hijito.elemento = aux;
            return this.delete4(hijito);
        }
        return null;
    }

    /**
     * Método auxiliar para delete
     * 
     * @param raiz la raiz desde la cual se busca al mínimo.
     * @return el vértice mínimo.
     */
    private Vertice buscaMinimo(Vertice raiz) {
        if (raiz.hayIzquierdo()) {
            return buscaMinimo(raiz.izquierdo);
        }
        return raiz;
    }

    public void balance(Vertice raiz) {
        Object[] arreglo = new Object[elementos];
        int cont = 0;
        for (T t : this) {
            arreglo[cont] = t;
            cont++;
        }
        raiz = buildSorted(arreglo).raiz;
    }

    private ArbolBST<T> buildSorted(Object[] arreglo) {
        if (arreglo.length == 0) {
            return new ArbolBST<T>();
        }
        int mitad = arreglo.length / 2;
        raiz = new Vertice((T) arreglo[mitad]);
        Object[] izq = Arrays.copyOfRange(arreglo, 0, mitad);
        Object[] der = Arrays.copyOfRange(arreglo, mitad + 1, arreglo.length);
        ArbolBST<T> izquierdo = buildSorted(izq);
        ArbolBST<T> derecho = buildSorted(der);
        if (izquierdo.raiz != null) {
            raiz.izquierdo = izquierdo.raiz;
        }
        if (derecho.raiz != null) {
            raiz.derecho = derecho.raiz;
        }
        ArbolBST<T> arbolito = new ArbolBST<T>();
        arbolito.raiz = raiz;
        return arbolito;
    }

    @Override
    public String toString() {
        String respuesta = "";
        if (raiz == null) {
            return respuesta;
        }
        respuesta += auxiliarString(raiz.izquierdo) + " " + raiz.elemento.toString() + " "
                + auxiliarString(raiz.derecho);
        return respuesta;
    }

    private String auxiliarString(Vertice raiz) {
        String respuesta = "";
        if (raiz == null) {
            return respuesta;
        }
        respuesta += auxiliarString(raiz.izquierdo) + " " + raiz.elemento.toString() + " "
                + auxiliarString(raiz.derecho);
        return respuesta;
    }

    @Override
    public T pop() {
        Vertice minimo = buscaMinimo(raiz);
        T aux = minimo.elemento;
        delete2(minimo);
        return aux;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Método auxiliar de toString
     * 
     * @return espacios
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
     * Metodo auxiliar de toString
     *
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
}