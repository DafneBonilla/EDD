package edd.src.Estructuras;

import java.util.NoSuchElementException;

public abstract class PushPop<T> {
    // Clase Nodo
    protected class Nodo {
        public T elemento;
        public Nodo siguiente;

        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    protected Nodo cabeza;
    protected Nodo ultimo;
    protected int longi;

    public abstract void push(T elemento);

    // Eliminar al inicio. 
    public T pop(){
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
        longi --;
        return valor;
    }


    public T peek(){
        if (isEmpty()) {
            throw new NoSuchElementException("");
        }
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
     * 
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
    public abstract PushPop<T> clone() ;
    
    public abstract String toString() ;

    @Override public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            System.out.println("Ejemplares distintos");
            return false;
        }
        @SuppressWarnings("unchecked") PushPop<T> pp = (PushPop<T>)o;
        if (this.longi != pp.longi) {
            System.out.println("Los tamaños no son iguales.");
            return false;
        }
        if (this.isEmpty() && pp.isEmpty()) {
            return true;
        }
        Nodo aux1 = this.cabeza;
        Nodo aux2 = pp.cabeza;
        while (aux1!=null && aux2 != null) {
            if (!aux1.elemento.equals(aux2.elemento)) {
                return false;
            } 
            aux1 = aux1.siguiente;
            aux2 = aux2.siguiente;
        }  
        return true;
    }



}
