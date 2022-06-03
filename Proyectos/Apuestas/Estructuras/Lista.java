package Apuestas.Estructuras;

/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * Clase de listas genéricas doblemente ligadas.
 * </p>
 * 
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Collection<T>, java.io.Serializable {

    /* Clase privada interna para nodos. */
    private class Nodo implements java.io.Serializable {
        /* Elemento del nodo. */
        public T elemento;
        /* Nodo anterior. */
        public Nodo anterior;
        /* Nodo siguente. */
        public Nodo siguiente;

        /* Constructor de nodos para la lista */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /* Clase Iterador privada para poder iterar. */
    private class Iterador implements IteradorLista<T> {
        /* Nodo anterior. */
        public Nodo anterior;
        /* Nodo siguiente. */
        public Nodo siguiente;

        /* Constructor de iterador. */
        public Iterador() {
            siguiente = cabeza;
        }

        /* Dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
            return siguiente != null;
        }

        /* Da el elemento siguiente. */
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T regresar = siguiente.elemento;

            this.anterior = this.siguiente;
            this.siguiente = siguiente.siguiente;
            return regresar;

        }

        /* Nos dice si hay un elemento anterior. */
        @Override
        public boolean hasPrevious() {
            return anterior != null;
        }

        /* Da el elemento anterior. */
        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            T regresar = anterior.elemento;

            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return regresar;

        }

        /* Sitúa el iterador al inicio de la lista. */
        @Override
        public void start() {
            this.anterior = null;
            this.siguiente = cabeza;
        }

        /* Sitúa el iterador al final de la lista. */
        @Override
        public void end() {
            this.anterior = ultimo;
            this.siguiente = null;
        }

    }

    /* Primer nodo de la lista. */
    private Nodo cabeza;
    /* Último nodo de la lista. */
    private Nodo ultimo;
    /* Entero que es la cantidad de elementos en la lista. */
    private int longi;

    /**
     * Agrega un elemento a la lista.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void add(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        agregaFinal(elemento);
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = this.ultimo = nuevo;
        } else {
            this.cabeza.anterior = nuevo;
            nuevo.siguiente = this.cabeza;
            this.cabeza = nuevo;
        }
        longi++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = this.ultimo = nuevo;
        } else {
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
        }
        longi++;
    }

    /**
     * Método auxiliar para buscar el nodo donde está un objeto (elemento).
     * Obtener nodo dando objeto.
     * 
     * @param elemento el elemento del cuál se obtendrá el nodo.
     */
    private Nodo buscaNodo(T elemento) {
        if (elemento == null)
            return null;
        Nodo n = cabeza;
        while (n != null) {
            if (elemento.equals(n.elemento))
                return n;
            n = n.siguiente;
        }
        return null;
    }

    /**
     * Método auxiliar para buscar el nodo donde está un objeto (elemento)
     * Obtener nodo dando int i.
     * 
     * @param i el índice del nodo a buscar.
     */
    private Nodo buscaNodoConI(int i) {
        if (i < 0 || i >= longi)
            return null;
        Nodo explorador = cabeza;
        int contando = 0;
        while (explorador != null) {
            if (contando == i) {
                return explorador;
            }
            explorador = explorador.siguiente;
            contando++;
        }
        return null;
    }

    /**
     * Metodo auxiliar para eliminar un nodo.
     * Eliminar nodo dando el nodo.
     * 
     * @param condenado nodo a eliminar.
     */
    private void desapareceNodo(Nodo condenado) {
        if (condenado == null)
            return;
        if (cabeza == ultimo && cabeza == null)
            return;
        longi--;
        if (cabeza == ultimo) {
            cabeza = ultimo = null;
        } else if (condenado == cabeza) {
            cabeza.siguiente.anterior = null;
            cabeza = cabeza.siguiente;
        } else if (condenado == ultimo) {
            ultimo.anterior.siguiente = null;
            ultimo = ultimo.anterior;
        } else {
            condenado.anterior.siguiente = condenado.siguiente;
            condenado.siguiente.anterior = condenado.anterior;
        }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no esta en la lista,
     * regresa false.
     * 
     * @param elemento el elemento a eliminar.
     * @return <code>true</code> si el elemento se pudo eliminae,
     *         <code>false</code> en otro caso.
     */
    public boolean delete(T elemento) {
        Nodo marcado = buscaNodo(elemento);
        if (elemento == null || marcado == null)
            return false;
        desapareceNodo(marcado);
        return true;
    }

    /**
     * Regresa un elemento de la lista. (Ultimo)
     * y lo elimina.
     * 
     * @return El elemento a sacar.
     */
    public T pop() {
        T valor = ultimo.elemento;
        ultimo = ultimo.anterior;
        ultimo.siguiente = null;
        longi--;
        return valor;
    }

    /**
     * Regresa el número de elementos en la lista.
     * 
     * @return el número de elementos en la lista.
     */
    public int size() {
        return longi;
    }

    /**
     * Nos dice si un elemento está contenido en la lista.
     * 
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la lista.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contains(T elemento) {
        return buscaNodo(elemento) != null;
    }

    /**
     * Vacía la lista.
     */
    public void empty() {
        cabeza = ultimo = null;
        longi = 0;
    }

    /**
     * Nos dice si la lista es vacía.
     * 
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty() {
        return cabeza == null;
    }

    /**
     * Regresa una copia de la lista.
     * 
     * @return una copia de la lista.
     */
    public Lista<T> clone() {
        Lista<T> nueva = new Lista<T>();
        Nodo nodo = cabeza;
        while (nodo != null) {
            nueva.add(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return nueva;
    }

    /**
     * Nos dice si la coleccion es igual a otra coleccion recibida.
     * 
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido
     *         <tt>false</tt> en otro caso.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            // System.out.println("El ejemplar no es una lista");
            return false;
        @SuppressWarnings("unchecked")
        Lista<T> lista = (Lista<T>) o;
        if (lista.longi != this.longi) {
            // System.out.println("Las longitudes no son iguales");
            return false;
        } else if (lista.isEmpty() && this.isEmpty()) {
            return true;
        } else {
            Nodo explorador = cabeza;
            Nodo gemelo = lista.cabeza;
            while (explorador != null) {
                if (explorador.elemento.equals(gemelo.elemento) == false) {
                    return false;
                }
                explorador = explorador.siguiente;
                gemelo = gemelo.siguiente;
            }
            return true;
        }
    }

    /**
     * Método que invierte el orden de la lista.
     * El tiempo es O(n) ya que solo recorremos una vez
     * la lista.
     * El espacio es O(1) ya que no se crean nodos.
     */
    public void reverse() {
        if (longi <= 1)
            return;
        Nodo explorador = ultimo.anterior;
        for (int i = 0; i < longi - 2; i++) {
            ultimo.siguiente = explorador;
            explorador.anterior.siguiente = explorador.siguiente;
            explorador.siguiente.anterior = explorador.anterior;
            Nodo aux = explorador.anterior;
            explorador.anterior = ultimo;
            explorador.siguiente = null;
            ultimo = explorador;
            explorador = aux;
        }
        ultimo.siguiente = explorador;
        cabeza = explorador.siguiente;
        explorador.siguiente.anterior = null;
        explorador.anterior = ultimo;
        explorador.siguiente = null;
        ultimo = explorador;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * 
     * @return una representación en cadena de la lista.
     *         a -> b -> c -> d
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "";
        String cadenitaRegreso = "";
        Nodo explorador = cabeza;
        while (explorador != null) {
            if (explorador.elemento != null) {
                cadenitaRegreso += explorador.elemento.toString();
                cadenitaRegreso += " -> ";
            }
            explorador = explorador.siguiente;
        }
        return cadenitaRegreso.substring(0, cadenitaRegreso.length() - 4);
    }

    /**
     * Junta dos listas siempre y cuando sean del mismo tipo.
     * 
     * @param lista la lista a juntar.
     */
    public void append(Lista<T> lista) {
        Nodo explorador = lista.cabeza;
        while (explorador != null) {
            this.agregaFinal(explorador.elemento);
            explorador = explorador.siguiente;
        }
    }

    /**
     * Regresa un entero con la posicion del elemento.
     * Solo nos importara la primera aparición del elemento
     * Empieza a contar desde 0.
     * 
     * @param elemento elemento del cual queremos conocer la posición.
     * @return entero con la posicion del elemento
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public int indexOf(T elemento) {
        if (elemento == null)
            throw new IllegalArgumentException("El elemento es null");
        int a = 0;
        Nodo b = cabeza;
        while (b != null) {
            if (b.elemento.equals(elemento))
                return a;
            a++;
            b = b.siguiente;
        }
        return -1;
    }

    /**
     * Inserta un elemento en un índice explícito.
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * 
     * @param i        el índice dónde insertar el elemento. Si es menor que 0 el
     *                 elemento se agrega al inicio de la lista, y si es mayor o
     *                 igual
     *                 que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void insert(int i, T elemento) {
        if (elemento == null)
            throw new IllegalArgumentException("El elemento es null");
        if (i <= 0) {
            agregaInicio(elemento);
        } else if (i >= longi) {
            agregaFinal(elemento);
        } else {
            Nodo intercambio = buscaNodoConI(i);
            Nodo nuevo = new Nodo(elemento);
            intercambio.anterior.siguiente = nuevo;
            nuevo.anterior = intercambio.anterior;
            intercambio.anterior = nuevo;
            nuevo.siguiente = intercambio;
            longi++;
        }
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * 
     * @return un iterador para recorrer la lista en una dirección.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * 
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * 
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
        if (longi == 0 || longi == 1)
            return clone();
        Lista<T> l1 = new Lista<T>();
        Lista<T> l2 = new Lista<T>();
        int m = longi / 2;
        int conta = 0;
        for (T e : this) {
            if (conta < m) {
                l1.agregaFinal(e);
            } else {
                l2.agregaFinal(e);
            }
            conta++;
        }
        l1 = l1.mergeSort(comparador);
        l2 = l2.mergeSort(comparador);
        return mezcla(l1, l2, comparador);
    }

    /**
     * Metodo auxiliar para mergeSort.
     * 
     * @param l1         la lista 1 a comparar.
     * @param l2         la lista 2 a comparar.
     * @param comparador el comparador de las listas.
     * @return lista ordenada.
     */
    private Lista<T> mezcla(Lista<T> l1, Lista<T> l2, Comparator<T> comparador) {
        Lista<T> mezclada = new Lista<T>();
        Nodo i = l1.cabeza;
        Nodo j = l2.cabeza;
        while (i != null && j != null) {
            if (comparador.compare(i.elemento, j.elemento) <= 0) {
                mezclada.agregaFinal(i.elemento);
                i = i.siguiente;
            } else {
                mezclada.agregaFinal(j.elemento);
                j = j.siguiente;
            }
        }
        while (i != null) {
            mezclada.agregaFinal(i.elemento);
            i = i.siguiente;
        }
        while (j != null) {
            mezclada.agregaFinal(j.elemento);
            j = j.siguiente;
        }
        return mezclada;
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * 
     * @param <T>   tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>> Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Saca un elemento específico de la lista. Si el elemento no está
     * en la lista, regresa null.
     * 
     * @param i el índice del elemento.
     * @return valor si el elemento se pudo sacar,
     *         <code>null</code> si el nodo con el índice es null.
     */
    public T delete2(int i) {
        Nodo marcado = buscaNodoConI(i);
        if (marcado == null)
            return null;
        T valor = marcado.elemento;
        desapareceNodo(marcado);
        return valor;
    }

    /**
     * Método para buscar el elemento en un índice.
     * Obtener nodo dando int i.
     * 
     * @param i el índice del nodo a buscar.
     */
    public T buscarIndice(int i) {
        return buscaNodoConI(i).elemento;
    }

    /**
     * Regresa una representación especial en cadena de la lista.
     * 
     * @return una representación especial en cadena de la lista.
     */
    public String toStringEspecial() {
        String datos = "";
        for (T e : this) {
            datos += e.toString() + "\t";
        }
        return datos;
    }
}