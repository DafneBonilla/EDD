package Apuestas;

import Apuestas.Estructuras.Lista;

/**
 * Clase para representar Torneos.
 * Un torneo tiene participantes, perdedores, ganador y número de ronda.
 */
public class Torneo {

    /* Participantes del torneo. */
    private Lista<Gallito> participantes;
    /* Perdedores del torneo. */
    private Lista<Gallito> perdedores;
    /* Ganador del torneo. */
    private Gallito ganador;
    /* Numero de ronda del torneo. */
    private int ronda;

    /**
     * Crea un torneo nueva.
     * 
     * @param participantes la lista de los participantes del torneo.
     */
    public Torneo(Lista<Gallito> participantes) {
        this.participantes = participantes;
        this.perdedores = new Lista<Gallito>();
        this.ganador = null;
        this.ronda = 0;
    }

}