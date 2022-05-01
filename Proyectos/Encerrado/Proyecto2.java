package Encerrado;

import Encerrado.Estructuras.Lista;

public class Proyecto2 {

    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: Encerrado/Proyecto2");
        System.exit(0);
    }

    public static void main(String[] args) {

        Lista<Jugador> jugadores = new Lista<Jugador>();
        jugadores.add(new Jugador(1));
        jugadores.add(new Jugador(2));
        Partida partida = new Partida(new Tablero(1), jugadores);
        partida.iniciar();
    }
}
