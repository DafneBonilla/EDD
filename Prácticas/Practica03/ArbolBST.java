/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbolBST<T extends Comparable<T>> extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios. */
    private class Iterador implements Iterator<T> {
        /* PIla para recorrer los vértices en in order. */
        private Pila<Vertice> pila;

        /* Constructor de iterador. */
        public Iterador() {
            pila = new Pila<Vertice>();
            if (isEmpty())
                return;
            Vertice p = raiz;
            while (p != null) {
                pila.push(p);
                p = p.izquierdo;
            }
        }

        /* Nos dice si hay un elemento siguiente. */
        public boolean hasNext() {
            return !pila.isEmpty();
        }

        /* Dice cual es el elemento siguiente. */
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Vertice v = pila.pop();
            if (v.derecho != null) {
                Vertice u = v.derecho;
                while (u != null) {
                    pila.push(u);
                    u = u.izquierdo;
                }
            }
            return v.elemento;
        }
    }

    public ArbolBST() {
        raiz = null;
        elementos = 0;
    }

    public ArbolBST(Lista<T> lista, boolean sorted) {
        if (lista.isEmpty()) {
            raiz = null;
            return;
        }
        if (!sorted) {
            ArbolBST<T> arbolito = buildUnsorted(lista);
            raiz = arbolito.raiz;
            elementos = arbolito.elementos;
            return;
        }
        ArbolBST<T> arbolito = buildSorted(lista);
        raiz = arbolito.raiz;
        elementos = arbolito.elementos;
    }

    public ArbolBST(ArbolBinario<T> arbol) {
        Object[] arreglo = new Object[arbol.size()];
        int cont = 0;
        for (T t : arbol) {
            arreglo[cont] = t;
            cont++;
        }
        ArbolBST<T> arbolito = buildArreglo(arreglo);
        raiz = arbolito.raiz;
        elementos = arbolito.elementos;
    }

    private ArbolBST<T> buildUnsorted(Lista<T> lista) {
        if (lista.isEmpty()) {
            return new ArbolBST<T>();
        }
        Lista<T> ordenada = lista.mergeSort((a, b) -> a.compareTo(b));
        return buildSorted(ordenada);
    }

    private ArbolBST<T> buildSorted(Lista<T> lista) {
        Object[] arreglo = new Object[lista.size()];
        int cont = 0;
        for (T t : lista) {
            arreglo[cont] = t;
            cont++;
        }
        return buildArreglo(arreglo);
    }

    private ArbolBST<T> buildArreglo(Object[] arreglo) {
        if (arreglo.length == 1) {
            ArbolBST<T> arbolito = new ArbolBST<T>();
            arbolito.add((T)arreglo[0]);
            return arbolito;
        }
        if (arreglo.length == 0) {
            return null;
        }
        ArbolBST<T> arbolito = new ArbolBST<T>();
        T algo2 = (T) arreglo[arreglo.length / 2];
        arbolito.raiz = new Vertice(algo2);
        arbolito.elementos = 1;
        Object[] izq = Arrays.copyOfRange(arreglo, 0, (arreglo.length/2));
        Object[] der = Arrays.copyOfRange(arreglo, (arreglo.length / 2)+1, arreglo.length);
        ArbolBST<T> izqu = buildArreglo(izq);
        ArbolBST<T> dere = buildArreglo(der);
        if (izqu != null) {
            arbolito.raiz.izquierdo = (ArbolBinario<T>.Vertice) izqu.raiz();
            izqu.raiz.padre = (ArbolBinario<T>.Vertice) arbolito.raiz();
            arbolito.elementos += izqu.elementos;
        }
        if (dere != null) {
            arbolito.raiz.derecho = (ArbolBinario<T>.Vertice) dere.raiz();
            dere.raiz.padre = (ArbolBinario<T>.Vertice) arbolito.raiz();
            arbolito.elementos += dere.elementos;
        }
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
        ArbolBST<T> arbol = buildArreglo(arreglo);
        this.raiz = arbol.raiz;
    }

    @Override
    public String toString() {
        String respuesta = "";
        for (T i : this) {
            respuesta += i + " ";
        }
        return respuesta+"\n";
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

    public Vertice getRaiz() {
        return this.raiz;
    }
}