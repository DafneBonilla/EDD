package Apuestas.Estructuras;

/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

/**
 * <p>
 * Clase de colas genéricas.
 * </p>
 * 
 * @param <T> El tipo de los elementos de la lista.
 */
public class Cola<T> extends PushPop<T> {

    /**
     * Agrega un elemento final de la cola. Si la cola no tiene elementos,
     * el elemento a agregar será el primero y el último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void push(T elemento) {
        if (elemento == null)
            throw new IllegalArgumentException("Elemento null");
        Nodo nuevo = new Nodo(elemento);
        if (isEmpty()) {
            this.cabeza = ultimo = nuevo;
            longi++;
            return;
        }
        ultimo.siguiente = nuevo;
        ultimo = nuevo;
        longi++;
    }

    /**
     * Regresa una copia de la pila.
     * 
     * @return una copia de la pila.
     */
    public Cola<T> clone() {
        Cola<T> nueva = new Cola<T>();
        if (this.isEmpty())
            return nueva;
        nueva.push(this.cabeza.elemento);
        Nodo n = this.cabeza;
        while (n.siguiente != null) {
            nueva.push(n.siguiente.elemento);
            n = n.siguiente;
        }
        return nueva;
    }

    /**
     * Regresa una representación en cadena de la cola.
     * 
     * @return una representación en cadena de la cola.
     */
    public String toString() {
        if (this.isEmpty())
            return "";
        String regreso = this.cabeza.elemento.toString();
        Nodo n = this.cabeza;
        while (n.siguiente != null) {
            regreso += ", " + n.siguiente.elemento.toString();
            n = n.siguiente;
        }
        return regreso;
    }
}