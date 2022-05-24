package Apuestas;

import java.util.concurrent.ThreadLocalRandom;
import Apuestas.Estructuras.Lista;

/**
 * Clase para representar Rondas del torneo.
 * Un torneo tiene participantes, perdedores, número de ronda, un cliente y un
 * numero de pelea.
 */
public class Ronda implements java.io.Serializable {

    /* Participantes del torneo. */
    private Lista<Gallito> participantes;
    /* Perdedores del torneo. */
    private Lista<Gallito> perdedores;
    /* Numero de ronda del torneo. */
    private int ronda;
    /* Numero de pelea */
    private int pelea;
    /* Cliente del torneo. */
    private Cuenta cliente;

    /**
     * Crea una ronda nueva.
     * 
     * @param participantes la lista de los participantes del torneo.
     * @param perdedores    la lista de los perdedores del torneo.
     * @param ronda         el numero de ronda del torneo.
     * @param cliente       el cliente del torneo.
     */
    public Ronda(Lista<Gallito> participantes, Lista<Gallito> perdedores, int ronda, Cuenta cliente) {
        this.participantes = participantes;
        this.perdedores = perdedores;
        this.ronda = ronda;
        this.cliente = cliente;
        this.pelea = 0;
    }

    /**
     * Regresa la lista de participantes.
     * 
     * @return la lista de participantes.
     */
    public Lista<Gallito> getParticipantes() {
        return participantes;
    }

    /**
     * Regresa la lista de perdedores.
     * 
     * @return la lista de perdedores.
     */
    public Lista<Gallito> getPerdedores() {
        return perdedores;
    }

    /**
     * Retorna el indice de una batalla.
     * 
     * @param g1 el indice del primer gallo.
     * @param g2 el indice del segundo gallo.
     * @return el indice del gallo que gana la batalla.
     */
    public int ganador(int g1, int g2) {
        int random = ThreadLocalRandom.current().nextInt(0, 100);
        return random;
    }

    /**
     * Inicia la ronda.
     */
    public void iniciar() {

    }
}