package Encerrado;

import Encerrado.Estructuras.ArbolDecision;

public class Proyecto2 {

    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: Encerrado/Proyecto2");
        System.exit(0);
    }

    public static void main(String[] args) {

        /**
        Lista<Jugador> jugadores = new Lista<Jugador>();
        jugadores.add(new Jugador(1));
        jugadores.add(new JugadorCPU(2, 0));
        Partida partida = new Partida(new Tablero(1), jugadores);
        partida.iniciar();
        */

        Tablero tablero = new Tablero(1);
        ArbolDecision arbol = new ArbolDecision(tablero, 2);
        System.out.println(arbol);
    }
}
