/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/
import java.util.Iterator;

public class ArbolBTS<T> extends ArbolBinarioCompleto<T> {

    public BuildUnsorted(Lista<T> lista) {
        
    }
    
    public BuildSorted(Lista<T> lista) {

    }

    public convertBTS(ArbolBinario<T> arbol) {

    }

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

    //ignora repetidos
    public void insert(Vertice raiz, T elemento) {
        if (raiz == null) {
            raiz = new Vertice(elemento);
        }
        agregaAux(raiz, elemento);
    }
    
    private void agregaAux(Vertice raiz, T u) {
        if (u.compareTo(raiz.elemento) < 0) {
            if (raiz.izquierdo == null) {
                raiz.izquierdo = new Vertice(u);
            } else {
                agregaAux(raiz.izquierdo, u);
            }
        }
        if (u.compareTo(raiz.elemento) > 0) {
            if (raiz.derecho == null) {
                raiz.derecho = new Vertice(u);
            } else {
                agregaAux(raiz.derecho, u);
            }
        }  
    }

    public void delete(Vertice raiz, T elemento) {

    }

    public void balance(Vertice raiz) {

    }

}
