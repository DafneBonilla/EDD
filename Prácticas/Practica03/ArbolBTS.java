import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

public class ArbolBTS<T extends Comparable<T>> extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios. */
    private class Iterador implements Iterator<T> {
        /* Cola para recorrer los vértices en BFS. */
        private Cola<Vertice> cola;

        /* Constructor de iterador. */
        public Iterador() {
            cola = new Cola<Vertice>();
            if (isEmpty())
                return;
            cola.push(raiz);
        }

        /* Nos dice si hay un elemento siguiente. */
        public boolean hasNext() {
            return !cola.isEmpty();
        }

        /* Dice si hay un elemento siguiente. */
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Vertice v = cola.pop();
            if (v.izquierdo != null)
                cola.push(v.izquierdo);
            if (v.derecho != null)
                cola.push(v.derecho);
            return v.elemento;
        }
    }

    public ArbolBTS() {
        super();
    }

    /*
     * //unsorted
     * public ArbolBTS(Lista<T> lista) {
     * super();
     * lista.mergeSort((a,b) -> a.compareTo(b));
     * }
     * 
     * // sorted
     * public ArbolBTS(Lista<T> lista) {
     * super();
     * for (T i : lista)
     * this.add(i);
     * }
     * }
     * 
     * //convertBTS
     * public ArbolBTS(ArbolBinario<T> arbol) {
     * super();
     * for (T i : arbol) {
     * this.add(i);
     * }
     * this.balance(raiz);
     * }
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

    // ignora repetidos
    @Override
    public void add(T elemento) {
        if (raiz == null) {
            raiz = new Vertice(elemento);
            elementos++;
        }
        insert(raiz, elemento);
    }

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
     * @param elemento el elemento a eliminar.
     * @return
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

    public boolean delete2(Vertice elemento) {
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
     * @param elemento el elemento a eliminar.
     * @return
     */
    public T delete3(T elemento) {
        if (raiz == null) {
            return null;
        }
        Vertice buscado = buscaVertice(raiz, elemento);
        if (buscado == null) {
            return null;
        }
        if (!buscado.hayIzquierdo() && !buscado.hayDerecho()) {
            if (buscado.hayPadre()) {
                Vertice ancestro = buscado.padre;
                if (ancestro.hayIzquierdo() && ancestro.hayDerecho()) {
                    if (ancestro.izquierdo.equals(buscado)) {
                        ancestro.izquierdo = null;
                        elementos--;
                        return buscado.elemento;
                    } else {
                        ancestro.derecho = null;
                        elementos--;
                        return buscado.elemento;
                    }
                } else if (ancestro.hayIzquierdo()) {
                    ancestro.izquierdo = null;
                    elementos--;
                    return buscado.elemento;
                } else {
                    ancestro.derecho = null;
                    elementos--;
                    return buscado.elemento;
                }
            } else {
                raiz = null;
                elementos = 0;
                return buscado.elemento;
            }
        }
        if (buscado.hayIzquierdo() && !buscado.hayDerecho()) {
            Vertice ancestro = buscado.padre;
            ancestro.izquierdo = buscado.izquierdo;
            buscado.izquierdo.padre = ancestro;
            elementos--;
            return buscado.elemento;
        }
        if (!buscado.hayIzquierdo() && buscado.hayDerecho()) {
            Vertice ancestro = buscado.padre;
            ancestro.derecho = buscado.derecho;
            buscado.derecho.padre = ancestro;
            elementos--;
            return buscado.elemento;
        }
        if (buscado.hayIzquierdo() && buscado.hayDerecho()) {
            Vertice hijito = buscaMinimo(buscado.derecho);
            T aux = buscado.elemento;
            buscado.elemento = hijito.elemento;
            hijito.elemento = aux;
            return this.delete4(hijito);
        }
        return null;
    }

    public T delete4(Vertice elemento) {
        if (raiz == null) {
            return null;
        }
        if (elemento == null) {
            return null;
        }
        if (!elemento.hayIzquierdo() && !elemento.hayDerecho()) {
            if (elemento.hayPadre()) {
                Vertice ancestro = elemento.padre;
                if (ancestro.hayIzquierdo() && ancestro.hayDerecho()) {
                    if (ancestro.izquierdo.equals(elemento)) {
                        ancestro.izquierdo = null;
                        elementos--;
                        return elemento.elemento;
                    } else {
                        ancestro.derecho = null;
                        elementos--;
                        return elemento.elemento;
                    }
                } else if (ancestro.hayIzquierdo()) {
                    ancestro.izquierdo = null;
                    elementos--;
                    return elemento.elemento;
                } else {
                    ancestro.derecho = null;
                    elementos--;
                    return elemento.elemento;
                }
            } else {
                raiz = null;
                elementos--;
                return elemento.elemento;
            }
        }
        if (elemento.hayIzquierdo() && !elemento.hayDerecho()) {
            Vertice ancestro = elemento.padre;
            ancestro.izquierdo = elemento.izquierdo;
            elemento.izquierdo.padre = ancestro;
            elementos--;
            return elemento.elemento;
        }
        if (!elemento.hayIzquierdo() && elemento.hayDerecho()) {
            Vertice ancestro = elemento.padre;
            ancestro.derecho = elemento.derecho;
            elemento.derecho.padre = ancestro;
            elementos--;
            return elemento.elemento;
        }
        if (elemento.hayIzquierdo() && elemento.hayDerecho()) {
            Vertice hijito = buscaMinimo(elemento.derecho);
            T aux = elemento.elemento;
            elemento.elemento = hijito.elemento;
            hijito.elemento = aux;
            return this.delete4(hijito);
        }
        return null;
    }

    /**
     * Método auxiliar para delete
     * 
     * @param raiz la raiz desde la cual se busca al mínimo.
     * @return el vértice mínimo.
     */
    private Vertice buscaMinimo(Vertice raiz) {
        if (raiz.hayIzquierdo()) {
            return buscaMinimo(raiz.izquierdo);
        }
        return raiz;
    }

    public void balance(Vertice raiz) {

    }

    @Override
    public String toString() {
        String respuesta = "";
        if (raiz == null) {
            return respuesta;
        }
        respuesta += auxiliarString(raiz.izquierdo) + " " + raiz.elemento.toString() + " "
                + auxiliarString(raiz.derecho);
        return respuesta;
    }

    private String auxiliarString(Vertice raiz) {
        String respuesta = "";
        if (raiz == null) {
            return respuesta;
        }
        respuesta += auxiliarString(raiz.izquierdo) + " " + raiz.elemento.toString() + " "
                + auxiliarString(raiz.derecho);
        return respuesta;
    }

    @Override
    public T pop() {
        Vertice minimo = buscaMinimo(raiz);
        T aux = minimo.elemento;
        delete2(minimo);
        return aux;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }
}