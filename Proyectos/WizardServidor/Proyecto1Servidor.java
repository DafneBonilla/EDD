package WizardServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import WizardServidor.Estructuras.Lista;

public class Proyecto1Servidor {
    
    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: java Wizard/Proyecto1Servidor #jugadores archivo.txt puerto");
        System.exit(0);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            uso();
        }
        int numJugadores = 0;
        int puerto = 0;
        try {
            numJugadores = Integer.parseInt(args[0]);
            puerto = Integer.parseInt(args[2]);
        } catch (NumberFormatException nfe) {
            uso();
        }
        if (numJugadores < 3 || numJugadores > 6) {
            uso();
        }

        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            Lista<Jugador> jugadores = new Lista<Jugador>();
            int cont = 0;
            while (jugadores.size() != numJugadores) {
                cont++;
                Socket socket = serverSocket.accept();
                Jugador jugador = new Jugador(String.valueOf(cont), socket);
                jugadores.agregaFinal(jugador);
            }
            Partida partida = new Partida(numJugadores, args[1], jugadores);
            System.out.println("la partida se creo exitosamente");
            //partida.iniciar();
            //System.out.println("Gracias por jugar :D"); //imprimir a cada jugador
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error con el servidor. Abortando la ejecucion.");
            System.exit(0);
        }       
    }
}
