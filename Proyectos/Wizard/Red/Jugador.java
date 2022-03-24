package Proyectos.Wizard.Red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Clase para jugadores.
 */
public class Jugador {
    
    /* Contador de números seriales. */
    private static int contadorSerial;

    /* La entrada de la conexión. */
    private BufferedReader in;
    /* La salida de la conexión. */
    private BufferedWriter out;
    /* El enchufe. */
    private Socket enchufe;
    /* Si la conexión está activa. */
    private boolean activa;
    /* El número serial único de la conexión. */
    private int serial;

    /**
     * Define el estado inicial de un nuevo jugador.
     * @param enchufe el enchufe de la conexión ya establecida.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public Jugador(Socket enchufe) throws IOException {
        in = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(enchufe.getOutputStream()));
            this.enchufe = enchufe;
            this.activa = true;
            this.serial = ++contadorSerial;
    }

}
