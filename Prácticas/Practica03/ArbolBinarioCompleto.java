package edd.src.Estructuras;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * <p>
 * Clase para árboles binarios completos.
 * </p>
 *
 * <p>
 * Un árbol binario completo agrega y elimina elementos de tal forma que el
 * árbol siempre es lo más cercano posible a estar lleno.
 * </p>
 */
public class ArbolBinarioCompleto<T> extends ArbolBinario<T> {
    /* Clase privada para iteradores de árboles binarios completos. */
    private class Iterador implements Iterator<T>{
        /* Cola para recorrer los vértices en BFS. */
        private Cola<Vertice> cola;

        public Iterador(){
            cola = new Cola<Vertice>();
            if(isEmpty())
                return;
            cola.push(raiz);
        }
        
        /* Nos dice si hay un elemento siguiente. */
        public boolean hasNext(){
            return !cola.isEmpty();
        }

        @Override public T next(){
            if(!hasNext())
                throw new NoSuchElementException();
            Vertice v = cola.pop();
            if(v.izquierdo != null)
                cola.push(v.izquierdo);
            if(v.derecho != null)
                cola.push(v.derecho);
            return v.elemento;
        }

    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioCompleto() {
        super();
    }

    /**
     * Construye un árbol binario completo a partir de una colección. El árbol
     * binario completo tiene los mismos elementos que la colección recibida.
     * 
     * @param coleccion la colección a partir de la cual creamos el árbol
     *                  binario completo.
     */
    public ArbolBinarioCompleto(Collection<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un elemento al árbol binario completo. El nuevo elemento se coloca
     * a la derecha del último nivel, o a la izquierda de un nuevo nivel.
     * 
     * @param elemento el elemento a agregar al árbol.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override public void add(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException();
        }
        Vertice a = nuevoVertice(elemento);
        elementos++;
        if (isEmpty()) {
            raiz = a;
        }
        else{
            Vertice b = BFS();
            if (!b.hayIzquierdo()) {
                b.izquierdo = a;
                a.padre = b;
                return;
            }
            if(!b.hayDerecho()){
                b.derecho = a;
                a.padre = b;
            }
        }
    }

    private Vertice BFS(){
        if(this.isEmpty()){
            return null;
        }
        Cola<Vertice> a = new Cola<Vertice>();
        a.push(raiz);
        while (a.cabeza != null) {
            Vertice b = a.pop();
            if (b.hayIzquierdo()) {
                a.push(b.izquierdo);
            }
            if (b.hayDerecho()) {
                a.push(b.derecho);
            }
            if(!b.hayIzquierdo() || !b.hayDerecho()){
                return b;
            }
        }
        return null;

    }
    
    /**
     * Regresa la altura del árbol. La altura de un árbol binario completo
     * siempre es ⌊log<sub>2</sub><em>n</em>⌋.
     * 
     * @return la altura del árbol.
     */
    @Override
    public int altura() {
        return (int) Math.floor(Math.log(elementos) / Math.log(2));
    }
    
    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden BFS.
     * 
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }



}
