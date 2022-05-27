/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
 * 
 * Clase para monticulos minimos (Minheaps).
*/
public class MonticuloMinimo<T extends ComparableIndexable<T>> implements Collection<T> {
    
    /* Clase Iterador privada para poder iterar. */
    private class Iterador implements Iterator<T> {
        
        /* Indice para recorrer el arreglo */
        private int indice;

        /* Dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return indice < elementos;
        }

        /* Da el elemento siguiente. */
        @Override public T next() {
            if (hasNext()) {
                return arbol[indice++];
            }
            throw new NoSuchElementException("No hay, no existe");
        }

    }

    /* Clase Adaptador privada para poder hacer arreglos. */
    private static class Adaptador<T extends Comparable<T>> implements ComparableIndexable<Adaptador<T>>{

        /* El elemento. */
        private T elemento;
        /* El índice. */
        private int indice;

        /**
         * Constructor de la clase.
         *
         * @param elemento el elemento.
         */
        public Adaptador(T elemento) {
            this.elemento = elemento;
            this.indice = -1;
        }

        /**
         * Regresa el indice.
         *
         * @return el inidice.
         */
        @Override
        public int getIndice() {
            return this.indice;
        }

        /**
         * Define el inidice.
         *
         * @param indice el nuevo indice.
         */
        @Override
        public void setIndice(int indice) {
            this.indice = indice;
        }

        /**
         * Compara dos adaptadores.
         *
         * @return el resultado de la comparacion.
         */
        @Override
        public int compareTo(Adaptador<T> adaptador) {
            return this.elemento.compareTo(adaptador.elemento);
        }
    }
    
    /* Numero de elementos en el arreglo */
    private int elementos;
    /* Nuestro arbol representado como arreglo */
    private T[] arbol;

    /* Con esto podemos crear arreglos genericos sin que el compilador marque error */
    @SuppressWarnings("unchecked") 
    private T[] nuevoArreglo(int n){
        return (T[])(new ComparableIndexable[n]);
    }

    /**
     * Constructor de la clase.
     */
    public MonticuloMinimo(){
        elementos = 0;
        arbol = nuevoArreglo(100);
    }
    
    /**
     * Constructor de la clase.
     * 
     * @param coleccion coleccion a agregar.
     */
    public MonticuloMinimo(Collection<T> coleccion){
        this(coleccion, coleccion.size());
    }
    
    /**
     * Constructor de la clase.
     * 
     * @param iterable iterable a agregar.
     * @param n numero de elementos a agregar.
     */
    public MonticuloMinimo(Iterable<T> iterable, int n ){
        elementos = n;
        arbol = nuevoArreglo(n);
        int i = 0;
        for (T e : iterable) {
           arbol[i] = e;
           arbol[i].setIndice(i);
           i ++;
        }
        for(int j = (elementos / 2) - 1; j >= 0; j--) {
            monticuloMin(j);
        }
    }

    /**
     * Metodo auxiliar? del constructor de arriba supongo.
     * 
     * @param i un indice.
     */
    private void monticuloMin(int i) {
        int izq = i * 2 + 1;
        int der = i * 2 + 2;
        int minimo = i;
        if (elementos <= i) {
            return;
        }
        if (izq < elementos && arbol[izq].compareTo(arbol[i]) < 0) {
            minimo = izq;
        }
        if (der < elementos && arbol[der].compareTo(arbol[minimo]) < 0) {
            minimo = der;
        }
        if (minimo == i) {
            return;
        } else {
            swap(arbol[minimo], arbol[i]);
            monticuloMin(minimo);
        }
    }

    /**
     * Intercambia un elemento del monticulo.
     *
     * @param i elemento a agregar.
     * @param j indice del elemento.
     */
    private void swap(T i, T j) {
        int aux = j.getIndice();
        arbol[i.getIndice()] = j;
        arbol[j.getIndice()] = i;
        j.setIndice(i.getIndice());
        i.setIndice(aux);
    }

    /**
     * Agrega un elemento al monticulo.
     * 
     * @param elemento elemento a agregar.
     */
    @Override public void add(T elemento){
        if (elementos == arbol.length) {
            duplicaSize();
        }
        elemento.setIndice(elementos);
        arbol[elementos] = elemento;
        elementos++;
        recorreArriba(elementos - 1);
    }

    /**
     * Duplica el tamaño del arreglo del monticulo.
     */
    private void duplicaSize(){
        T[] arr = nuevoArreglo(arbol.length * 2);
        elementos = 0;
        for(T e: arbol){
            arr[elementos++] = e;
        }
        this.arbol = arr;
    }

    /**
     * Recorre el arreglo hacia arriba y lo pone en su lugar.
     *
     * @param i indice del elemento.
     */
    private void recorreArriba(int i) {
        int padre = (i - 1) / 2;
        int m = i;
        if(padre >= 0 && arbol[padre].compareTo(arbol[i]) > 0){
            m = padre;
        }
        if (m!= i) {
            T a = arbol[i];
            arbol[i] = arbol[padre];
            arbol[i].setIndice(i);
            arbol[padre] = a;
            arbol[padre].setIndice(padre);
            recorreArriba(m);
        }
    }
    
    /**
     * Elimina el elemento minimo del monticulo.
     */
    public T delete() {
        if(elementos == 0){
            throw new IllegalStateException("Monticulo vacio");
        }
        T e = arbol[0];
        boolean bool = delete(e);
        if (bool) {
            return e;
        }
        else {
            return null;
        }
    }

    /**
     * Elimina un elmento del monticulo.
     *
     * @param elemento el elemento a eliminar.
     */
    @Override public boolean delete(T elemento) {
        if (elemento == null || isEmpty() ) {
            return false;
        }
        if (!contains(elemento)) {
            return false;
        }
        int i = elemento.getIndice();
        if (i < 0 || elementos <= i )
            return false;
        swap(arbol[i], arbol[elementos - 1]);
        arbol[elementos - 1] = null;
        elementos--;
        recorreAbajo(i);
        return true;
    }

    /**
     * Recorre el arreglo hacia abajo y lo pone en su lugar.
     *
     * @param i indice del elemento.
     */
    private void recorreAbajo(int i){
        if(i < 0) {
            return;
        }
        int izq = 2 * i + 1;
        int der = 2 * i + 2;
        if (izq >= elementos && der >= elementos){
            return;
        }
        int minimo = i;
        if (izq < elementos && arbol[izq].compareTo(arbol[i]) < 0) {
            minimo = izq;
        }
        if (der < elementos && arbol[der].compareTo(arbol[minimo]) < 0) {
            minimo = der;
        }
        if (minimo == i) {
            return;
        } else {
            swap(arbol[minimo], arbol[i]);
            recorreAbajo(minimo);
        }
    }

    /**
     * Nos dice si un elemento esta contenido en el monticulo.
     * 
     * @param elemento el elemento que queremos verificar si esta contenido en
     *                 el monticulo.
     * @return <code>true</code> si el elemento esta contenido en el monticulo,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contains(T elemento){
        for(T e: arbol){
            if(elemento.equals(e))
                return true;
        }
        return false;
    }

    /** 
     * Nos dice si el monticulo esta vacio.
     * 
     * @return <code>true</code> si el monticulo esta vacio,
     *          <code>false</code> en otro caso.
     */
    @Override public boolean isEmpty(){
        return elementos == 0;
    }
    
    /**
     * Vacia al monticulo
     */
    @Override
    public void empty() {
        for (int i = 0; i < elementos; i++) {
            arbol[i] = null;
        }
        elementos = 0;
    }

    /**
     * Nos dice el tamaño del monticulo.
     * 
     * @return el tamaño del monticulo.
     */
    @Override
    public int size(){
        return elementos;
    }

    /**
     * Nos dice devuelve un elemento del monticulo.
     * 
     * @return el elemento  del monticulo.
     */
    public T get(int i){
        if (i< 0 || i>= elementos) {
            throw new NoSuchElementException("Indice no valido");
        }
        return arbol[i];
    }

    /**
     * Regresa una representacion en cadena del monticulo.
     * 
     * @return una representacion en cadena del monticulo.
     *         a -> b -> c -> d
     */
    @Override public String toString(){
        String resultado ="";
        for (int i = 0; i <elementos; i++) {
            resultado += arbol[i].toString() + ",";
        }
        if (resultado.length() > 0) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }
        return resultado;
    }
    
    /**
     * Nos dice si el monticulo es igual a otro.
     * 
     * @return <code>true</code> si el monticulo es igual a otro,
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj==null || getClass() != obj.getClass()){
            return false;
        }
        @SuppressWarnings("unchecked") MonticuloMinimo<T> monticulo = (MonticuloMinimo<T>)obj;
        if (elementos != monticulo.elementos) {
            return false;
        }
        for (int i = 0; i < elementos; i++) {
            if(!arbol[i].equals(monticulo.arbol[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * Regresa un iterador para iterar el montículo mínimo. El montículo se
     * itera en orden BFS.
     * 
     * @return un iterador para iterar el montículo mínimo.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Ordena la colección usando HeapSort.
     *
     * @param <T> tipo del que puede ser el arreglo.
     * @param coleccion la colección a ordenar.
     * @return una lista ordenada con los elementos de la colección.
     */
    public <T extends Comparable<T>> Lista<T> heapSort(Collection<T> coleccion) {
        Lista<Adaptador<T>> lAdaptador = new Lista<Adaptador<T>>();
        for (T elem : coleccion) {
            lAdaptador.add(new Adaptador<T>(elem));
        }
        MonticuloMinimo<Adaptador<T>> aux = new MonticuloMinimo<>(lAdaptador);
        Lista<T> respuestas = new Lista<T>();
        while (!aux.isEmpty()) {
            Adaptador<T> adap = aux.delete();
            respuestas.add(adap.elemento);
        }
        return respuestas;
    }

    /**
     * Revisa si un arreglo es un montículo mínimo.
     * 
     * @return <code>true</code> si el arreglo es un montículo mínimo,
     *         <code>false</code> en otro caso.
     */
    public boolean esMontMin(T[] arreglo) {
        for (int i = 0; i <= (arreglo.length - 2) / 2; i++) {
            if ((2 * i + 1) < arreglo.length) {
                if (arreglo[i].compareTo(arreglo[2 * i + 1]) > 0) {
                    return false;
                }
            }
            if ((2 * i + 2) < arreglo.length) {
                if (arreglo[i].compareTo(arreglo[2 * i + 1]) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Convierte un arreglo que representa un montículo máximo 
     * a uno mínimo en O(n)
     * 
     * @param arreglo el arreglo que representa un montículo máximo.
     */
    public void montMaxMontMin(T[] arreglo) {
        int n = arreglo.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            if (arreglo[i] != null) {
                recorreAbajo(arreglo, i);
            }
        }
    }

    /**
     * Recorre el arreglo hacia abajo y lo pone en su lugar.
     *
     * @param i indice del elemento.
     */
    private void recorreAbajo(T[] arreglo, int i) {
        if(i < 0) {
            return;
        }
        int izq = 2 * i + 1;
        int der = 2 * i + 2;
        if (izq >= arreglo.length && der >= arreglo.length) {
            return;
        }
        int minimo = i;
        if (izq < arreglo.length) {
            if (arreglo[izq] != null) {
                if (arreglo[izq].compareTo(arreglo[i]) < 0) {
                    minimo = izq;
                }
            }
        }
        if (der < arreglo.length) {
            if (arreglo[der] != null) {
                if (arreglo[der].compareTo(arreglo[minimo]) < 0) {
                    minimo = der;
                }
            }
        }
        if (minimo == i) {
            return;
        } else {
            swap(arreglo, arreglo[minimo], arreglo[i]);
            recorreAbajo(arreglo, minimo);
        }
    }

    /**
     * Intercambia un elemento del arreglo.
     *
     * @param T el arreglo.
     * @param i indice del elemento.
     * @param j indice del elemento.
     */
    private void swap(T[] arreglo, T i, T j) {
        int aux = j.getIndice();
        arreglo[i.getIndice()] = j;
        arreglo[j.getIndice()] = i;
        j.setIndice(i.getIndice());
        i.setIndice(aux);
    }
}