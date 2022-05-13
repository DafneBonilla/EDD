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
            int nuevo = actualizarTurno(jugador);
            Decisiones dec = new Decisiones(tablero.copia(), opci, nuevo, 0);
            Vertice hijo = new Vertice(dec);
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
                int max = max(raiz.izquierdo.elemento.getPuntuacion(), raiz.derecho.elemento.getPuntuacion());
                dec.setPuntuacion(max);
            } else {
                int min = min(raiz.izquierdo.elemento.getPuntuacion(), raiz.derecho.elemento.getPuntuacion());
                dec.setPuntuacion(min);
            }
        } else if (raiz.izquierdo != null) {
            evaluar(raiz.izquierdo);
            Decisiones dec = raiz.elemento;
            dec.setPuntuacion(raiz.izquierdo.elemento.getPuntuacion());
        } else if (raiz.derecho != null) {
            evaluar(raiz.derecho);
            Decisiones dec = raiz.elemento;
            dec.setPuntuacion(raiz.derecho.elemento.getPuntuacion());
        }
    }

    private int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    private int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    // trabajar en esto
    public int mejorMovimiento() {
        if (!raiz.hayDerecho()) {
            return 0;
        }
        int izqi = raiz.izquierdo.elemento.getPuntuacion();
        int deri = raiz.derecho.elemento.getPuntuacion();
        if (izqi > deri) {
            return 0;
        } else if (izqi == deri) {
            return 0;
        } else if (izqi < deri) {
            return 1;
        }
        return -1;
    }
}
