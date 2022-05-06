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
        this.raiz = new Vertice(new Decisiones(tablero, new Opcion(-1, -1), jugador, 0));
        construir(raiz, 10, jugador);
        evaluar();
    }

    private void construir(Vertice raiz, int i, int jugador) {
        if (i == 0) {
            Tablero tablero = raiz.elemento.getTablero();
            Lista<Opcion> opciones = tablero.getOpciones(jugador);
            if (opciones.size() == 0) {
                if (jugador == dueño) {
                    raiz.elemento.setPuntuacion(-1);
                } else {
                    raiz.elemento.setPuntuacion(1);
                }
            }
            return;
        }
        int posicion = 0;
        Tablero tablero = raiz.elemento.getTablero();
        Lista<Opcion> opciones = tablero.getOpciones(jugador);
        if (opciones.size() == 0) {
            if (jugador == dueño) {
                raiz.elemento.setPuntuacion(-1);
            } else {
                raiz.elemento.setPuntuacion(1);
            }
        }
        for (Opcion opci : opciones) {
            Opcion inversa = opci.inversa();
            tablero.moverEspecial(opci);
            Decisiones dec = new Decisiones(tablero.copia(), opci, jugador, 0);
            Vertice hijo = new Vertice(dec);
            int nuevo = actualizarTurno(jugador);
            construir(hijo, i - 1, nuevo);
            if (posicion == 0) {
                raiz.izquierdo = hijo;
                hijo.padre = raiz;
                posicion++;
            } else {
                raiz.derecho = hijo;
                hijo.padre = raiz;
            }
            tablero.moverEspecial(inversa);
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

    public void evaluar() {
        evaluar(this.raiz);
    }

    // hacer esto xd (sale mal creo, lo de max y min)
    private void evaluar(Vertice raiz) {
        if (raiz.izquierdo == null && raiz.derecho == null) {
            return;
        }
        if (raiz.izquierdo != null && raiz.derecho != null) {
            evaluar(raiz.izquierdo);
            evaluar(raiz.derecho);
            Decisiones dec = raiz.elemento;
            if (dec.getJugador() == dueño) {
                int max = Math.max(raiz.izquierdo.elemento.getPuntuacion(), raiz.derecho.elemento.getPuntuacion());
                dec.setPuntuacion(max);
            } else {
                int min = Math.min(raiz.izquierdo.elemento.getPuntuacion(), raiz.derecho.elemento.getPuntuacion());
                dec.setPuntuacion(min);
            }
        }
        if (raiz.izquierdo != null) {
            evaluar(raiz.izquierdo);
            Decisiones dec = raiz.elemento;
            dec.setPuntuacion(raiz.izquierdo.elemento.getPuntuacion());
        }
        if (raiz.derecho != null) {
            evaluar(raiz.derecho);
            Decisiones dec = raiz.elemento;
            dec.setPuntuacion(raiz.derecho.elemento.getPuntuacion());
        }
    }

    // trabajar en esto
    public int mejorMovimiento() {
        if (!raiz.hayDerecho()) {
            return 0;
        }
        int izqi = raiz.izquierdo.elemento.getPuntuacion();
        int deri = raiz.derecho.elemento.getPuntuacion();
        if (izqi >= deri) {
            return 0;
        } else {
            return 1;
        }
    }
}
