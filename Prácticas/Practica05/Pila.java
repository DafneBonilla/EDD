/**
 * <p>
 * Clase de pilas genéricas.
 * </p>
 * 
 * @param <T> El tipo de los elementos de la lista.
 */
public class Pila<T> extends PushPop<T> {

    /**
     * Agrega un elemento arriba de la pila. Si la pila no tiene elementos,
     * el elemento a agregar será el primero y el último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void push(T elemento) {
        if (elemento == null)
            throw new IllegalArgumentException("Elemento null");
        Nodo aux = new Nodo(elemento);
        if (isEmpty()) {
            this.cabeza = ultimo = aux;
            longi++;
            return;
        }
        aux.siguiente = cabeza;
        cabeza = aux;
        longi++;
    }

    /**
     * Regresa una copia de la pila.
     * 
     * @return una copia de la pila.
     */
    public Pila<T> clone() {
        Pila<T> nueva = new Pila<T>();
        if (this.isEmpty())
            return nueva;
        nueva.push(this.cabeza.elemento);
        Nodo n = this.cabeza;
        while (n.siguiente != null) {
            nueva.push(n.siguiente.elemento);
            n = n.siguiente;
        }
        return nueva.cloneAux();
    }

    /**
     * Método auxiliar para clone().
     */
    private Pila<T> cloneAux() {
        Pila<T> nueva = new Pila<T>();
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
     * Regresa una representación en cadena de la pila.
     * 
     * @return una representación en cadena de la pila.
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

    /**
     * Regresa una representación en cadena para Hanoi de la pila.
     * 
     * @return una representación en cadena para Hanoi de la pila.
     */
    public String toStringHanoi() {
        if (this.isEmpty())
            return "";
        String regreso = this.cabeza.elemento.toString();
        Nodo n = this.cabeza;
        while (n.siguiente != null) {
            regreso = n.siguiente.elemento.toString() + ", " + regreso;
            n = n.siguiente;
        }
        return regreso;
    }
}