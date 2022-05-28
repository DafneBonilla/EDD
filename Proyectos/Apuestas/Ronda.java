package Apuestas;

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
    private int numRonda;
    /* Numero de pelea del torneo. */
    private int numBatalla;
    /* Batalla del torneo. */
    private Batalla batalla;
    /* Total de batallas del torneo. */
    private int totalBatallas;
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
    public Ronda(Lista<Gallito> participantes, Lista<Gallito> perdedores, int numRonda, Cuenta cliente) {
        this.participantes = participantes;
        this.perdedores = perdedores;
        this.numRonda = numRonda;
        this.numBatalla = 0;
        this.batalla = null;
        this.cliente = cliente;
        this.totalBatallas = participantes.size() / 2;
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
     * Regresa el cliente.
     * 
     * @return el cliente.
     */
    public Cuenta getCliente() {
        return cliente;
    }

    /**
     * Inicia la ronda.
     * 
     * @throws TorneoPausa cuando se va a pausar el torneo.
     */
    public void iniciar() throws TorneoPausa {
        try {
            System.out.println("Empieza/continua la ronda " + numRonda);
            while (numBatalla != totalBatallas) {
                numBatalla++;
                Lista<Gallito> luchadores = new Lista<>();
                luchadores.add(participantes.delete2(0));
                luchadores.add(participantes.delete2(0));
                this.batalla = new Batalla(participantes, perdedores, numBatalla, cliente, luchadores);
                this.batalla.iniciar();
                this.participantes = batalla.getParticipantes();
                this.perdedores = batalla.getPerdedores();
            }
            System.out.println("Se termino la ronda");
        } catch (TorneoPausa e) {
            this.cliente = batalla.getCliente();
            numBatalla--;
            throw new TorneoPausa();
        }

    }
}