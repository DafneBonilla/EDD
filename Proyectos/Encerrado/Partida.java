package Encerrado;

import Encerrado.Estructuras.Lista;

/**
 * Clase para representar Partidas.
 * Una partida tiene un tablero, jugadores y turno actual.
 */
public class Partida {

    /* Tablero de la partida. */
    private Tablero tablero;
    /* Lista de jugadores. */
    private Lista<Jugador> jugadores;
    /* Turno actual. */
    private int turnoActual;

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
    }

}
