package Proyectos.Wizard;

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

    /**
     * Constructor único.
     */
    public Jugador() {
        this.mano = new Baraja();
        this.puntuacion = 0;
        this.apuesta = 0;
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
     * Metodo para agregar una carta a la mano jugador.
     * @param nueva la carta a agregar a la mano.
     */
    public void recibirCarta(Carta nueva) {
        mano.agregaCarta(nueva);
    }

}
