package Proyectos.Wizard.Red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase para servidores de cartitas.
 */
public class ServidorCartas {

    /* El servidor de enchufes. */
    private ServerSocket servidor;
    /* El puerto. */
    private int puerto;
    /* Lista con las jugadores. */
    //private Lista<Jugador> jugadores;
    /* Bandera de continuación. */
    private Boolean continuaEjecucion;

    /**
     * Crea un nuevo servidor .
     * @param puerto el puerto dónde escuchar por conexiones.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public ServidorCartas(int puerto) throws IOException {
        this.servidor = new ServerSocket(puerto);
        this.puerto = puerto;
        //this.jugadores = new Lista<Jugador>();
        //carga();
    }
    
}
