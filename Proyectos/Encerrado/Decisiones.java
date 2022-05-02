package Encerrado;

/**
 * Clase para representar decisiones.
 * Una descisión tiene un tablero, una opción, un jugador y una puntuacion.
 */
public class Decisiones {

    /* Tablero de la decisión. */
    private Tablero tablero;
    /* Opción de la decisión. */
    private Opcion opcion;
    /* Jugador que realiza la decisión. */
    private int jugador;
    /* Puntuación de la decisión. */
    private int puntuacion;

    /**
     * Define el estado inicial de una descicion.
     * 
     * @param tablero el tablero de la descicion.
     * @param opcion  la opcion de la descicion.
     */
    public Decisiones(Tablero tablero, Opcion opcion, int jugador, int puntuacion) {
        this.tablero = tablero;
        this.opcion = opcion;
        this.jugador = jugador;
        this.puntuacion = puntuacion;
    }

    /**
     * Devuelve el tablero de la decisión.
     * 
     * @return el tablero de la decisión.
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Devuelve la opcion de la decisión.
     * 
     * @return la opcion de la decisión.
     */
    public Opcion getOpcion() {
        return opcion;
    }

    /**
     * Devuelve el jugador que realiza la decisión.
     * 
     * @return el jugador que realiza la decisión.
     */
    public int getJugador() {
        return jugador;
    }

    /**
     * Devuelve la puntuación de la decisión.
     * 
     * @return la puntuación de la decisión.
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Cambia la puntuación de la decisión.
     * 
     * @param puntuacion la nueva puntuación de la decisión.
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Regresa una representacion en cadena de la decisión.
     * 
     * @return una representacion en cadena de la decisión.
     */
    @Override
    public String toString() {
        return "Decisiones [" + opcion.getPosicionInicial() + " -> " + opcion.getPosicionFinal() + ", " + jugador + "]";
    }
}