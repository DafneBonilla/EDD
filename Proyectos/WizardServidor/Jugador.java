package WizardServidor;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * Clase para representar jugadores.
 */
public class Jugador {
    
    /* Nombre del jugador. */
    private String nombre;
    /* Mano del jugador. */
    private Baraja mano;
    /* Puntuación del jugador. */
    private int puntuacion;
    /* Apuesta actual del jugador. */
    private int apuesta;
    /* Trucos ganados por el jugador. */
    private int ganados;
    /* Buffer de escritura del jugador. */
    private BufferedWriter out;
    /* Socket del jugador. */
    private Socket enchufe;

    /**
     * Define el estado inicial de un jugador.
     * @param nombre el nombre del jugador.
     * @param out el buffer de escritura del jugador.
     * @param enchufe el socket del jugador.
     */
    public Jugador(String nombre, BufferedWriter out, Socket enchufe) {
        this.mano = new Baraja();
        this.puntuacion = 0;
        this.apuesta = 0;
        this.ganados = 0;
        this.nombre = nombre;
        this.enchufe = enchufe;
        this.out = out;
    }

    /**
     * Regresa el nombre del jugador.
     * @return el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Regresa la mano del jugador.
     * @return la mano del jugador.
     */
    public Baraja getBaraja() {
        return mano;
    }

    /**
     * Define la mano del jugador.
     * @param mano la nueva mano del jugador.
     */
    public void setBaraja(Baraja mano) {
        this.mano = mano;
    }

    /**
     * Regresa la puntuación del jugador.
     * @return la puntuación del jugador.
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Define la puntuación del jugador.
     * @param puntuacion la nueva puntuación del jugador.
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Regresa la apuesta actual del jugador.
     * @return la apuesta actual del jugador.
     */
    public int getApuesta() {
        return apuesta;
    }

    /**
     * Define la apuesta actual del jugador.
     * @param apuesta la nueva apuesta actual del jugador.
     */
    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    /**
     * Regresa los trucos ganados por por el jugador.
     * @return los trucos ganados por por el jugador.
     */
    public int getGanados() {
        return ganados;
    }

    /**
     * Define los trucos ganados por por el jugador.
     * @param ganados los trucos ganados por por el jugador.
     */
    public void setGanados(int ganados) {
        this.ganados = ganados;
    }

    /**
     * Método para agregar una carta a la mano del jugador.
     * @param nueva la carta a agregar a la mano del jugador.
     */
    public void recibirCarta(Carta nueva) {
        mano.agregaCarta(nueva);
    }

    /**
     * Método para jugar una carta de la mano del jugador.
     * @param i la posición de la carta de la mano del jugador.
     */
    public Carta sacaCarta(int i) {
        return mano.sacaCarta(i);
    }

    /**
     * Método para ver una representación en cadena de la baraja ordenada.
     * @return representación en cadena de la baraja ordenada.
     */   
    public String verBarajaOrdenada() {
        mano.ordenar();
        return mano.toString();
    }

    /**
     * Regresa la última cadena recibida del jugador.
     * @return la última cadena recibida del jugador.
     * @throws JugadorInactivo si <code>linea</code> es <code>null</code>
     *         o si hay un error de entrada/ salida.
     */
    public String leerJugador() throws JugadorInactivo {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String linea = in.readLine();
            if (linea == null) {
                throw new JugadorInactivo();
            }
            return linea;
        } catch (IOException ioe) {
            throw new JugadorInactivo();
        }
    }

    /**
     * Envia una cadena al jugador.
     * @param mensaje la cadena a enviar.
     * @throws JugadorInactivo si hay un error de entrada/salida.
     */
    public void hablarJugador(String mensaje) throws JugadorInactivo {
        try {
            out.write(mensaje);
            out.newLine();
            out.flush();
        } catch (IOException ioe) {
            throw new JugadorInactivo();
        }
    }
}