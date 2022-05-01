package Encerrado;

import Encerrado.Estructuras.Lista;

/**
 * Clase para representar Partidas.
 * Una partida tiene un tablero, jugadores, turno actual y si hay un ganador.
 */
public class Partida {

    /* Tablero de la partida. */
    private Tablero tablero;
    /* Lista de jugadores. */
    private Lista<Jugador> jugadores;
    /* Turno actual. */
    private int turnoActual;
    /* Si hay un ganador. */
    private boolean ganador;

    /**
     * Define el estado inicial de una partida.
     * 
     * @param tablero     el tablero de la partida.
     * @param jugadores   la lista de jugadores de la partida.
     * @param turnoActual el turno actual de la partida.
     */
    public Partida(Tablero tablero, Lista<Jugador> jugadores) {
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.turnoActual = 0;
        this.ganador = false;
    }

    public void iniciar() {
        System.out.println("El juego comienza!");
        while (ganador == false) {
            Jugador jugador = jugadores.buscarIndice(turnoActual);
            Lista<Opcion> opciones = tablero.getOpciones(jugador.getNombre());
            if (opciones.isEmpty()) {
                ganador = true;
                break;
            }
            System.out.println("Turno del jugador " + jugador.getNombre());
            Opcion opci = jugador.pedirMovimiento(opciones, tablero);
            tablero.mover(opci);
            System.out.println("El tablero es:");
            System.out.println(tablero);
            actualizarTurno();
        }
        actualizarTurno();
        System.out.println("El ganador es el Jugador " + jugadores.buscarIndice(turnoActual).getNombre());
        System.out.println("Gracias por jugar!");
    }

    public void actualizarTurno() {
        turnoActual++;
        if (turnoActual == jugadores.size()) {
            turnoActual = 0;
        }
    }

}