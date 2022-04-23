import java.util.NoSuchElementException;

/**
 * <p>
 * Clase abstracta para colas y pilas genéricas.
 * </p>
 * 
 * @param <T> El tipo de los elementos de la lista.
 */
public abstract class PushPop<T> {

    /* Clase protegida interna para nodos. */
    protected class Nodo {
        /* Elemento del nodo. */
        public T elemento;
        /* Nodo siguente. */
        public Nodo siguiente;

        /* Constructor de nodos para la lista */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /* Primer nodo del PushPop. */
    protected Nodo cabeza;
    /* Último nodo del PushPop. */
    protected Nodo ultimo;
    /* Entero que es la cantidad de elementos en el PushPop. */
    protected int longi;

    /**
     * Agrega un elemento al final o principio del PushPop. Si el PushPop no tiene
     * elementos,
     * el elemento a agregar será el primero y el último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public abstract void push(T elemento);

    /**
     * Elimina el primer elemento del PushPop. Y lo regresa.
     * 
     * @param elemento el elemento a eliminar.
     * @return el elemento eliminado.
     * @throws NoSuchElementException si <code>elemento</code> es
     *                                <code>null</code> o si es vacía.
     */
    public T pop() {
        if (longi == 0 || cabeza == null) {
            throw new NoSuchElementException("");
        }
        if (longi == 1) {
            T valor = cabeza.elemento;
            cabeza = ultimo = null;
            longi = 0;
            return valor;
        }
        T valor = cabeza.elemento;
        cabeza = cabeza.siguiente;
        longi--;
        return valor;
    }

    /**
     * Regresa el elemento de la cabeza.
     * 
     * @return el elemento de la cabeza.
     * @throws NoSuchElementException si es vacía.
     */
    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException("");
        return cabeza.elemento;
    }

    /**
     * Regresa el número de elementos en la estructura.
     * 
     * @return el número de elementos en la estructura.
     */
    public int size() {
        return longi;
    }

    /**
     * Vacía la estructura.
     */
    public void empty() {
        cabeza = ultimo = null;
        longi = 0;
    }

    /**
     * Nos dice si la estructura es vacía.
     * 
     * @return <code>true</code> si la estructura es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty() {
        return longi == 0 || (cabeza == null && ultimo == null);
    }

    /**
     * Regresa un clon de la estructura.
     * 
     * @return un clon de la estructura.
     */
    public abstract PushPop<T> clone();

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            System.out.println("Ejemplares distintos");
            return false;
        }
        @SuppressWarnings("unchecked")
        PushPop<T> pp = (PushPop<T>) o;
        if (this.longi != pp.longi) {
            System.out.println("Los tamaños no son iguales.");
            return false;
        }
        if (this.isEmpty() && pp.isEmpty()) {
            return true;
        }
        Nodo aux1 = this.cabeza;
        Nodo aux2 = pp.cabeza;
        while (aux1 != null && aux2 != null) {
            if (!aux1.elemento.equals(aux2.elemento)) {
                return false;
            }
            aux1 = aux1.siguiente;
            aux2 = aux2.siguiente;
        }
        return true;
    }
}