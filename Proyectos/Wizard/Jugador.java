package Wizard;

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

    /**
     * Define el estado inicial de un jugador.
     * 
     * @param nombre el nombre del jugador.
     */
    public Jugador(String nombre) {
        this.mano = new Baraja();
        this.puntuacion = 0;
        this.apuesta = 0;
        this.ganados = 0;
        this.nombre = nombre;
    }

    /**
     * Regresa el nombre del jugador.
     * 
     * @return el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Regresa la mano del jugador.
     * 
     * @return la mano del jugador.
     */
    public Baraja getBaraja() {
        return mano;
    }

    /**
     * Define la mano del jugador.
     * 
     * @param mano la nueva mano del jugador.
     */
    public void setBaraja(Baraja mano) {
        this.mano = mano;
    }

    /**
     * Regresa la puntuación del jugador.
     * 
     * @return la puntuación del jugador.
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Define la puntuación del jugador.
     * 
     * @param puntuacion la nueva puntuación del jugador.
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Regresa la apuesta actual del jugador.
     * 
     * @return la apuesta actual del jugador.
     */
    public int getApuesta() {
        return apuesta;
    }

    /**
     * Define la apuesta actual del jugador.
     * 
     * @param apuesta la nueva apuesta actual del jugador.
     */
    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    /**
     * Regresa los trucos ganados por por el jugador.
     * 
     * @return los trucos ganados por por el jugador.
     */
    public int getGanados() {
        return ganados;
    }

    /**
     * Define los trucos ganados por por el jugador.
     * 
     * @param ganados los trucos ganados por por el jugador.
     */
    public void setGanados(int ganados) {
        this.ganados = ganados;
    }

    /**
     * Método para agregar una carta a la mano del jugador.
     * 
     * @param nueva la carta a agregar a la mano del jugador.
     */
    public void recibirCarta(Carta nueva) {
        mano.agregaCarta(nueva);
    }

    /**
     * Método para jugar una carta de la mano del jugador.
     * 
     * @param i la posición de la carta de la mano del jugador.
     */
    public Carta sacaCarta(int i) {
        return mano.sacaCarta(i);
    }

    /**
     * Método para ver una representación en cadena de la baraja ordenada.
     * 
     * @return representación en cadena de la baraja ordenada.
     */
    public String verBarajaOrdenada() {
        mano.ordenar();
        return mano.toString();
    }
}