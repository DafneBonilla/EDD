package Encerrado.Estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;
import Encerrado.Decisiones;
import Encerrado.Opcion;
import Encerrado.Tablero;

/**
 * Clase para representar Arboles de Decisiones.
 * Un árbol de decisión tiene un nodo raíz y un número de elementos.
 */
public class ArbolDecision extends ArbolBinario<Decisiones> {

    /* Clase privada para iteradores de árboles binarios completos. */
    private class Iterador implements Iterator<Decisiones> {
        /* Cola para recorrer los vértices en BFS. */
        private Cola<Vertice> cola;

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

        @Override
        public Decisiones next() {
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

    private int dueño;

    /**
     * Constructor de la clase ArbolDecision.
     * 
     * @param tablero el tablero del arbol.
     */
    public ArbolDecision(Tablero tablero, int jugador, int dueño) {
        this.dueño = dueño;
        this.raiz = new Vertice(new Decisiones(tablero, new Opcion(-1, -1), jugador, -2));
        construir(raiz, 3, jugador, tablero);
    }

    private void construir(Vertice raiz, int i, int jugador, Tablero tablero) {
        if (i == 0) {
            return;
        }
        Lista<Opcion> opciones = tablero.getOpciones(jugador);
        int opci = opciones.size();
        for (int j = 0; j < opci; j++) {
            Tablero tablerito = tablero.copia();
            Opcion opcioncita = opciones.buscarIndice(j);
            tablerito.moverEspecial(opcioncita);
            Vertice hijo = new Vertice(new Decisiones(tablerito, opcioncita, jugador, dueño));
            int nuevo = jugador;
            nuevo = actualizarTurno(nuevo);
            construir(hijo, i - 1, nuevo, tablerito);
            if (!raiz.hayIzquierdo()) {
                raiz.izquierdo = hijo;
                hijo.padre = raiz;
            } else {
                raiz.derecho = hijo;
                hijo.padre = raiz;
            }
        }
    }

    public int actualizarTurno(int jugador) {
        jugador++;
        if (jugador == 3) {
            jugador = 1;
        }
        return jugador;
    }

    @Override
    public void add(Decisiones elemento) {
    }

    @Override
    public boolean delete(Decisiones elemento) {
        return false;
    }

    @Override
    public Decisiones pop() {
        return null;
    }

    @Override
    public Iterator<Decisiones> iterator() {
        return new Iterador();
    }

    private String dibujaEspacios(int l, int[] a, int n) {
        String s = "";
        for (int i = 0; i < l; i++) {
            if (a[i] == 1) {
                s = s + "│  ";
            } else {
                s = s + "   ";
            }
        }
        return s;
    }

    private String toString(Vertice v, int l, int[] m) {
        String s = v.toString() + "\n";
        m[l] = 1;

        if (v.izquierdo != null && v.derecho != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "├─›";
            s = s + toString(v.izquierdo, l + 1, m);
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─»";
            m[l] = 0;
            s = s + toString(v.derecho, l + 1, m);
        } else if (v.izquierdo != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─›";
            m[l] = 0;
            s = s + toString(v.izquierdo, l + 1, m);
        } else if (v.derecho != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─»";
            m[l] = 0;
            s = s + toString(v.derecho, l + 1, m);
        }
        return s;
    }

    @Override
    public String toString() {
        if (this.raiz == null) {
            return "";
        }
        int[] a = new int[this.altura() + 1];
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;
        }
        return toString(this.raiz, 0, a);
    }
}
