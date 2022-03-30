package Wizard;

import java.io.IOException;

public class Proyecto1 {
    
    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: java Wizard/Proyecto1 #jugadores archivo");
        System.exit(0);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            uso();
        }
        int numJugadores = 0;
        try {
            numJugadores = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            uso();
        }
        if (numJugadores < 3 || numJugadores > 6) {
            uso();
        }
        Partida partida = new Partida(numJugadores, args[1]);
        try {
            partida.iniciar();
        } catch (IOException ioe) {
            System.out.println("Escribiste algo mal. Adios");
        }
    }
}
