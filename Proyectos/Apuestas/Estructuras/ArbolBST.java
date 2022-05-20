package Apuestas.Estructuras;

/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArbolBST
 * Implementación de un árbol binario de búsqueda genericos.
 * 
 * @author Dafne Bonilla Reyes, José Camilo García Ponce
 * @version 26-04-2022
 */
public class ArbolBST<T extends Comparable<T>> extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los vértices en in order. */
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

    /**
     * Construye un árbol BTS vacío.
     */
    public ArbolBST() {
        raiz = null;
        elementos = 0;
    }

    /**
     * Construye un árbol BST apartir de otro árbol.
     * 
     * @param arbol el árbol para construirlo.
     */
    public ArbolBST(ArbolBinario<T> arbol) {
        Lista<T> lista = new Lista<T>();
        for (T t : arbol) {
            lista.add(t);
        }
        Lista<T> listaOrdenada = lista.mergeSort((a, b) -> a.compareTo(b));
        Object[] arreglo = new Object[listaOrdenada.size()];
        int contador = 0;
        for (T t : listaOrdenada) {
            arreglo[contador] = t;
            contador++;
        }
        ArbolBST<T> arbolito = buildArreglo(arreglo);
        raiz = arbolito.raiz;
        elementos = arbolito.elementos;
    }

    /**
     * Construye un árbol BST apartir de una lista.
     * 
     * @param lista  la lista para construirlo.
     * @param sorted si la lista esta ordenada o no.
     */
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

    /**
     * Regresa un árbol BST construido apartir de una lista desordenada.
     * La complejidad si es O(nlog(n)), ya que ordenaremos la lista y es lo que toma
     * más tiempo.
     * 
     * @param lista la lista para construirlo.
     * @return el árbol construido.
     */
    private ArbolBST<T> buildUnsorted(Lista<T> lista) {
        if (lista.isEmpty()) {
            return new ArbolBST<T>();
        }
        Lista<T> ordenada = lista.mergeSort((a, b) -> a.compareTo(b));
        return buildSorted(ordenada);
    }

    /**
     * Regresa un árbol BST construido apartir de una lista ordenada.
     * La complejidad si es O(n), ya que sortedMagia solo "recorre" la lista una vez
     * y construye de abaja hacia arriba.
     * 
     * @param lista la lista para construirlo.
     * @return el árbol construido.
     */
    private ArbolBST<T> buildSorted(Lista<T> lista) {
        Iterator<T> iter = lista.iterator();
        return sortedMagia(iter, 0, lista.size() - 1);
    }

    /**
     * Construye un árbol BST apartir de una lista ordenada.
     * Y lo hace en O(n) ya que no accede al i-ésimo elemento de la lista, la
     * construye empezando por las hojas y va "dividiendo" la lista.
     * 
     * @param iter    el iterador para recorrer la lista.
     * @param primero el inicio del tamaño de la lista.
     * @param ultimo  el final del tamaño de la lista.
     * @return el árbol construido.
     */
    private ArbolBST<T> sortedMagia(Iterator<T> iter, int primero, int ultimo) {
        if (primero > ultimo) {
            return new ArbolBST<T>();
        }
        int medio = (primero + ultimo) / 2;
        ArbolBST<T> izq = sortedMagia(iter, primero, medio - 1);
        T elemento = iter.next();
        Vertice v = new Vertice(elemento);
        ArbolBST<T> der = sortedMagia(iter, medio + 1, ultimo);
        ArbolBST<T> arbol = new ArbolBST<T>();
        arbol.raiz = v;
        arbol.elementos = 1;
        if (!izq.isEmpty()) {
            arbol.raiz.izquierdo = izq.raiz;
            izq.raiz.padre = arbol.raiz;
            arbol.elementos += izq.elementos;
        }
        if (!der.isEmpty()) {
            arbol.raiz.derecho = der.raiz;
            der.raiz.padre = arbol.raiz;
            arbol.elementos += der.elementos;
        }
        return arbol;
    }

    /**
     * Regresa un árbol BST construido apartir de un arreglo.
     * 
     * @param arreglo el arreglo para construirlo.
     * @return el árbol construido.
     */
    private ArbolBST<T> buildArreglo(Object[] arreglo) {
        if (arreglo.length == 1) {
            ArbolBST<T> arbolito = new ArbolBST<T>();
            @SuppressWarnings("unchecked")
            T bonito = (T) arreglo[0];
            arbolito.add(bonito);
            return arbolito;
        }
        if (arreglo.length == 0) {
            return null;
        }
        @SuppressWarnings("unchecked")
        T algo2 = (T) arreglo[arreglo.length / 2];
        ArbolBST<T> arbolito = new ArbolBST<T>();
        arbolito.raiz = new Vertice(algo2);
        arbolito.elementos = 1;
        Object[] izq = Arrays.copyOfRange(arreglo, 0, (arreglo.length / 2));
        Object[] der = Arrays.copyOfRange(arreglo, (arreglo.length / 2) + 1, arreglo.length);
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

    /**
     * Ve si el árbol contiene un elemento.
     * 
     * @param elemento el elemento a buscar.
     * @param raiz     la raiz del árbol donde buscaremos.
     * @return <code>true</code> si el árbol contiene el elemento.
     */
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

    /**
     * Ve si el árbol contiene un elemento y regresa el vertice que lo contiene.
     * 
     * @param elemento el elemento a buscar.
     * @param raiz     la raiz del árbol donde buscaremos.
     * @return el vertice que contiene el elemento.s
     */
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
            raiz = new Vertice(elemento);
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
     * Elimina un elemento del árbol.
     * 
     * @param elemento el elemento a eliminar.
     * @return <code>true</code> si el elemento se eliminó.
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

    /**
     * Elimina un vertice del árbol.
     * 
     * @param elemento el vertice a eliminar.
     * @return <code>true</code> si el vertice se eliminó.
     */
    private boolean delete2(Vertice elemento) {
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
     * Regresa el maximo de un árbol.
     * 
     * @param raiz la raiz del árbol.
     * @return el vertice con el maximo del árbol.
     */
    protected Vertice buscaMaximo(Vertice raiz) {
        if (raiz.hayDerecho()) {
            return buscaMinimo(raiz.derecho);
        }
        return raiz;
    }

    /**
     * Regresa el minimo de un árbol.
     * 
     * @param raiz la raiz del árbol.
     * @return el vertice con el minimo del árbol.
     */
    protected Vertice buscaMinimo(Vertice raiz) {
        if (raiz.hayIzquierdo()) {
            return buscaMinimo(raiz.izquierdo);
        }
        return raiz;
    }

    /**
     * Balancea el árbol.
     * La complejidad si es O(n), ya que lo que toma más tiempo es recorrer el árbol
     * para generar la lista y crear el arbol toma O(n)
     * 
     * @param raiz la raiz del árbol.
     */
    public void balance(Vertice raiz) {
        Lista<T> lista = new Lista<T>();
        for (T t : this) {
            lista.add(t);
        }
        ArbolBST<T> arbol = buildSorted(lista);
        this.raiz = arbol.raiz;
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
        return respuesta;
    }

    /**
     * Regresa un elemento de la colección y lo elimina.
     * 
     * @return el elemento a sacar.
     */
    public T pop() {
        Vertice minimo = buscaMinimo(raiz);
        T aux = minimo.elemento;
        delete2(minimo);
        return aux;
    }

    /**
     * Regresa un iterador para recorrer el árbol.
     * 
     * @return un iterador para recorrer el árbol.
     */
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

    /**
     * Regresa la raiz del árbol.
     * 
     * @return la raiz del árbol.
     */
    public Vertice getRaiz() {
        return this.raiz;
    }
}