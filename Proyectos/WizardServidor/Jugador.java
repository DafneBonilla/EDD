package WizardServidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * Clase para representar jugadores.
 */
public class Jugador {
    
    /* Mano del jugador. */
    private Baraja mano;
    /* Puntuacion del jugador. */
    private int puntuacion;
    /* Apuesta actual del jugador. */
    private int apuesta;
    /* Trucos ganados por el jugador. */
    private int ganados;
    /* Trucos ganados por el jugador. */
    private String nombre;
    /* Buffered para escribir al jugador */
    private BufferedWriter out;
    /* Buffered para leer al jugador */
    private BufferedReader in;
    /* Socket del jugador */
    private Socket enchufe;

    /**
     * Define el estado inicial de un jugador.
     */
    public Jugador(String nombre, BufferedWriter out, BufferedReader in, Socket enchufe) {
        this.mano = new Baraja();
        this.puntuacion = 0;
        this.apuesta = 0;
        this.ganados = 0;
        this.nombre = nombre;
        this.out = out;
        this.enchufe = enchufe;
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
     * Regresa la puntuacion del jugador.
     * @return la puntuacion del jugador.
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Define la puntuacion del jugador.
     * @param puntuacion la nueva puntuacion del jugador.
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
     * @param puntuacion la nueva apuesta actual del jugador.
     */
    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    /**
     * Regresa los trucos ganapor por el jugador.
     * @return los trucos ganapor por el jugador.
     */
    public int getGanados() {
        return ganados;
    }

    /**
     * Define los trucos ganapor por el jugador.
     * @param ganados los trucos ganapor por el jugador.
     */
    public void setGanados(int ganados) {
        this.ganados = ganados;
    }

    /**
     * Metodo para agregar una carta a la mano del jugador.
     * @param nueva la carta a agregar a la mano.
     */
    public void recibirCarta(Carta nueva) {
        mano.agregaCarta(nueva);
    }

    /**
     * Metodo para jugar una carta de la mano del jugador.
     * @param i la posicion de la carta.
     */
    public Carta sacaCarta(int i) {
        return mano.sacaCarta(i);
    }

    /**
     * Ver en forma de cadena la baraja ordenada del jugador.
     * @return la baraja ordenada del jugador.
     */
    public String verBarajaOrdenada() {
        mano.ordenar();
        return mano.toString();
    }

    /**
     * Regresa la ultima cadena recibida del jugador.
     * @return la ultima cadena recibida del jugador.
     */
    public String leerJugador() throws JugadorInactivo {
        try {
            String ultimaLinea = null;
            String actualLinea = null;
            while ((actualLinea = in.readLine()) != null) {
                ultimaLinea = actualLinea;
            }
            if (ultimaLinea == null) {
                throw new IOException();
            }
            return ultimaLinea;
        } catch (IOException e) {
            throw new JugadorInactivo();
        }
    }

    /**
     * Envia una cadena al jugador.
     * @param cadena la cadena a enviar.
     */
    public void hablarJugador(String mensaje) throws JugadorInactivo {
        try {
            out.write(mensaje+"\n");
            out.flush();
        } catch (IOException e) {
            throw new JugadorInactivo();
        }
    }
    
}
