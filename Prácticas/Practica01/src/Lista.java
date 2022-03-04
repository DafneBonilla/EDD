package Clases;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * <p>Clase de listas genéricas doblemente ligadas.</p>
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Collection<T> {

    /* Clase privada interna para nodos. */
    private class Nodo {
        /* Elemento del nodo. */
        public T elemento;
        /* Nodo anterior. */
        public Nodo anterior;
        /* Nodo siguente. */
        public Nodo siguiente;

        /* Constructor de nodos para la lista */
        public Nodo(T elemento){
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
        @Override public boolean hasNext() {
            return siguiente != null;
        }

        /* Da el elemento siguiente. */
        @Override public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            T regresar = siguiente.elemento;
            
            this.anterior = this.siguiente ;
            this.siguiente=siguiente.siguiente;
            return regresar;

        }
        
        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            return anterior != null;
        }
        
        /* Da el elemento anterior. */
        @Override public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            T regresar = anterior.elemento;

            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return regresar;

        }
        
        /* Sitúa el iterador al inicio de la lista. */
        @Override public void start() {
            this.anterior = null;
            this.siguiente = cabeza;
        }
        
        /* Sitúa el iterador al final de la lista. */
        @Override public void end() {
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
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override public void add(T elemento) {
        if (elemento == null)
            throw new IllegalArgumentException("El elemento es null");
        agregaFinal(elemento);
    }
    
    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     * <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) 
            throw new IllegalArgumentException("El elemento es null");
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
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     * <code>null</code>.
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
     */
    private Nodo buscaNodo(T elemento) {
        if (elemento == null)
            return null;
        Nodo n = cabeza;
        while(n !=null) {
            if (elemento.equals(n.elemento))
                return n;
            n=n.siguiente;
        }
        return null;
    }

    /**
     * Método auxiliar para buscar el nodo donde está un objeto (elemento)
     * Obtener nodo dando int i.
     */
    private Nodo buscaNodoConI(int i) {
        if (i < 0 || i >= longitud) {
            return null;
        }
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
     * @param condenado nodo a eliminar.
    */
    private void desapareceNodo(Nodo condenado) {
        if (condenado == null) {
            return;
        }
        if (cabeza == rabo && cabeza == null) {
            return;
        }
        longitud--;
        if (cabeza == rabo) {
            cabeza = rabo = null;
        } else if (condenado == cabeza){
            cabeza.siguiente.anterior = null;
            cabeza = cabeza.siguiente;
        } else if (condenado == rabo) {
            rabo.anterior.siguiente = null;
            rabo = rabo.anterior;
        } else {
            condenado.anterior.siguiente = condenado.siguiente;
            condenado.siguiente.anterior = condenado.anterior;
        }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no esta en la lista,
     * regresa false.
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
     * Regresa el último elemento de la lista y lo elimina.
     * @return el elemento a eliminar.
     */
    public T pop() {
        T valor = ultimo.elemento;
        ultimo = ultimo.anterior;
        ultimo.siguiente = null;
        longi --;
        return valor;
    }

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos en ella.
     */
    public int size() {
        return longi;
    }

    /**
     * Nos dice si un elemento está contenido en la lista.
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
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty(){
        return cabeza == null;
    }

    /**
     * Regresa una copia de la lista.
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
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido
     *         <tt>false</tt> en otro caso.
    */
    public boolean equals(Collection<T> coleccion) {
        // lo vemos en clase
        if (coleccion instanceof Lista) {
            return true;
        }
        return false;
        // Aquí va su código.
    }
    
    /**
     * Método que invierte el orden de la lista.
     */
    public void reverse() {
        Nodo explorador = ultimo;
        for (int i = 0; i < longi; i++) {
            agregaFinal(explorador.elemento);
            Nodo aux = explorador.anterior;
            boolean basura = delete(explorador.elemento);
            explorador = aux;
        }
        return;
        // Aquí va su código.
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     * a -> b -> c -> d
     */
    public String toString(){
        if (isEmpty()) {
            return "";
        }
        String cadenitaRegreso = "";
        Nodo explorador = cabeza;
        while (explorador != null) {
            if (explorador.elemento != null) {
                cadenitaRegreso += explorador.elemento;
            }
            if (explorador.siguiente != null) {
                cadenitaRegreso += " -> ";
            }
            explorador = explorador.siguiente;
        }
        return cadenitaRegreso;
        // Aquí va su código.
    }

    /**
     * Junta dos listas "siempre y cuando sean del mismo tipo".
     * @param lista la lista a juntar.
     */
    public void append(Lista<T> lista) {
        Nodo explorador = lista.cabeza;
        while (explorador != null) {
            this.agregaFinal(explorador.elemento);
            explorador = explorador.siguiente;
        }
        // Aquí va su código.
    }

    /**
     * Regresa un entero con la posicion del elemento.
     * Solo nos importara la primera aparición del elemento
     * Empieza a contar desde 0.
     * @param elemento elemento del cual queremos conocer la posición.
     * @return entero con la posicion del elemento
     * @throws IllegalArgumentException si <code>elemento</code> es
     * <code>null</code>.
     */
    public int indexOf(T elemento) {
        if (elemento == null)
            throw new IllegalArgumentException("El elemento es null");
        int a = 0;
        Nodo b = cabeza;
        while (b != null) {
            if (b.elemento.equals(elemento))
                return a;
            a ++;
            b = b.siguiente;
        }
        return -1;
        // Aquí va su código.
    }
    
    /**
     * Inserta un elemento en un índice explícito.
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void insert(int i, T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        if (i <= 0) {
            agregaInicio(elemento);
        } else if (i >= longitud) {
            agregaFinal(elemento);
        } else {
            Nodo intercambio = buscaNodoConI(i);
            Nodo nuevo = new Nodo(elemento);
            intercambio.anterior.siguiente = nuevo;
            nuevo.anterior = intercambio.anterior;
            intercambio.anterior = nuevo;
            nuevo.siguiente = intercambio;
            longitud++;
        }
        // Aquí va su código.
    }

    /**
     * Une dos ejemplares de listas de manera alternada.
     * @param lista la lista a mezclar.
     */
    public void mezclaAlternada(Lista<T> lista) {
        Nodo explorador = lista.cabeza;
        int indice = 1;
        while (explodor != null) {
            this.insert(indice, explorador.elemento);
            explorador = explorador.siguiente;
        }
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
