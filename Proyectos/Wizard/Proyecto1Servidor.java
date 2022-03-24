package Proyectos.Wizard;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Proyectos.Wizard.Red.ServidorCartas;

/**
 * ServidorProyecto1.
 */
public class Proyecto1Servidor {
    
    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: Proyecto1Servidor <puerto> <# jugadores>");
        System.exit(0);
    }

    public static void main(String[] args) {
        
        if (args.length != 2) {
            uso();
        }

        int puerto = Integer.parseInt(args[0]);
        try {
            puerto = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            uso();
        }

        try {
            ServidorCartas servidor;
            servidor = new ServidorCartas(puerto);
            //servidor.sirve();
        } catch (IOException ioe) {
            System.out.println("Error al crear el servidor.");
        }

        //codigo bonito y funcional

    }
}
