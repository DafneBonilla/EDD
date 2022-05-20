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

        /* Dice cual es el elemento siguiente. */
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

    private int dueno;

    /**
     * Constructor de la clase ArbolDecision.
     * 
     * @param tablero el tablero del árbol.
     * @param jugador el jugador del árbol.
     * @param dueno   el dueño del árbol.
     */
    public ArbolDecision(Tablero tablero, int jugador, int dueno) {
        this.dueno = dueno;
        this.raiz = new Vertice(new Decisiones(tablero, new Opcion(-1, -1), jugador, 0));
        construir(raiz, 10, jugador);
        evaluar();
    }

    /**
     * Construye un árbol de decisiones a partir de un tablero y sus opciones.
     * 
     * @param v       el vertice raiz del árbol.
     * @param i       la profundidad.
     * @param jugador el turno del jugador.
     */
    private void construir(Vertice raiz, int i, int jugador) {
        if (i == 0) {
            Tablero tablero = raiz.elemento.getTablero();
            Lista<Opcion> opciones = tablero.getOpciones(jugador);
            if (opciones.size() == 0) {
                if (jugador == dueno) {
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
            if (jugador == dueno) {
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

    /**
     * Actualiza el turno del jugador.
     * 
     * @param jugador el turno actual.
     * @return el nuevo turno.
     */
    public int actualizarTurno(int jugador) {
        jugador++;
        if (jugador == 3) {
            jugador = 1;
        }
        return jugador;
    }

    /* no hace nada xd */
    @Override
    public void add(Decisiones elemento) {
    }

    /* no hace nada xd */
    @Override
    public boolean delete(Decisiones elemento) {
        return false;
    }

    /* no hace nada xd */
    @Override
    public Decisiones pop() {
        return null;
    }

    /**
     * Regresa un iterador para recorrer el árbol.
     * 
     * @return un iterador para recorrer el árbol.
     */
    @Override
    public Iterator<Decisiones> iterator() {
        return new Iterador();
    }

    /**
     * Evualua el arbol de decisiones.
     */
    public void evaluar() {
        evaluar(this.raiz);
    }

    /**
     * Evalua el arbol de decisiones.
     * 
     * @param raiz el vertice raiz del arbol.
     */
    private void evaluar(Vertice raiz) {
        if (raiz.izquierdo == null && raiz.derecho == null) {
            return;
        }
        if (raiz.izquierdo != null && raiz.derecho != null) {
            evaluar(raiz.izquierdo);
            evaluar(raiz.derecho);
            Decisiones dec = raiz.elemento;
            if (dec.getJugador() == dueno) {
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

    /**
     * Método para obtener el valor máximo de dos enteros.
     * 
     * @param a el primer entero.
     * @param b el segundo entero.
     * @return el valor máximo de los dos enteros.
     */
    private int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Método para obtener el máximo entre dos enteros.
     * 
     * @param a el primer entero.
     * @param b el segundo entero.
     * @return el valor máximo de los enteros.
     */
    private int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Devuelve el mejor movimiento del árbol.
     * 
     * @return el mejor movimiento del árbol.
     */
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
