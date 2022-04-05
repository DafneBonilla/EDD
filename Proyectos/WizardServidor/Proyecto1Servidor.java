package WizardServidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import WizardServidor.Estructuras.Lista;

public class Proyecto1Servidor {
    
    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: java Wizard/Proyecto1Servidor #jugadores archivo.txt puerto");
        System.exit(0);
    }

    private static void enviarMensaje(String mensaje, BufferedWriter out) {
        System.out.println(mensaje);
        try {
            out.write(mensaje);
            out.newLine();
        } catch (Exception e) {
            System.out.println("Error al guardar el mensaje, abortando la ejercución.");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            uso();
        }
        int numJugadores = 0;
        int puerto = 1234;
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
            BufferedWriter archivo = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
            ServerSocket serverSocket = new ServerSocket(puerto);
            enviarMensaje("El servidor esta andando", archivo);
            Lista<Jugador> jugadores = new Lista<Jugador>();
            enviarMensaje("Esperando jugadores...", archivo);
            while (jugadores.size() != numJugadores) {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                out.write("Ingresa tu nombre: ");
                out.flush();
                String nombre = in.readLine();
                Jugador jugador = new Jugador(nombre, out, socket);
                jugadores.agregaFinal(jugador);
                out.write("Bienvenido " + nombre + "!\n");
                out.write("La partida comenzara en unos momentos...\n");
                enviarMensaje("El jugador "+nombre+" ingreso.", archivo);
                out.flush();
            }
            Partida partida = new Partida(numJugadores, args[1], jugadores, archivo);
            enviarMensaje("Jugadores conectados. Iniciando partida...", archivo);
            partida.iniciar();
            for (Jugador jugador : jugadores) {
                jugador.hablarJugador("Gracias por jugar :D");
            }
            serverSocket.close();
            archivo.close();
        } catch (IOException ioe) {
            System.out.println("Error con el servidor/archivo. Abortando la ejecucion.");
            System.exit(0);
        }       
    }
}
