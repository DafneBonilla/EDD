package edd.src.Estructuras;

public class Pila<T> extends PushPop<T>{
    
    
    // Agregar al inicio.
    public void push(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("");
        }
        Nodo aux = new Nodo(elemento);
        if(isEmpty()){
            this.cabeza=ultimo=aux;
            longi++;
            return ;
        }
        aux.siguiente = cabeza;
        cabeza = aux;
        longi ++;

    }

    /**
     * Regresa un clon de la estructura.
     * 
     * @return un clon de la estructura.
     */
    public Pila<T> clone(){
        Pila<T> nueva = new Pila<T>();
        Pila<T> aux = new Pila<T>();
        if (this.isEmpty()) {
            return nueva;
        }
        Nodo aux2 = this.cabeza;
        while(aux2 != null){
            aux.push(aux2.elemento);
            aux2 = aux2.siguiente;
        }
        while (!aux.isEmpty()) {
            nueva.push(aux.pop());
        }
        return nueva;  

    }

    public String toString(){
        if (this.isEmpty()) {
            return "";
        }
        String regreso = this.cabeza.elemento.toString();
        Nodo n = this.cabeza;
        while (n.siguiente != null) {
            regreso += ", " + n.siguiente.elemento.toString();
            n = n.siguiente;
        }
        return regreso;
    }


}
