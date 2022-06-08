package Apuestas;

import java.util.Random;
import Apuestas.Estructuras.Lista;

/**
 * Clase para representar Torneos.
 * Un torneo tiene participantes, perdedores, ganador, número de ronda, si ya
 * termino y un
 * cliente.
 */
public class Torneo implements java.io.Serializable {

    /* Participantes del torneo. */
    private Lista<Gallito> participantes;
    /* Perdedores del torneo. */
    private Lista<Gallito> perdedores;
    /* Ganador del torneo. */
    private Gallito ganador;
    /* Ronda del torneo. */
    private Ronda ronda;
    /* Numero de ronda del torneo. */
    private int numRonda;
    /* Cliente del torneo. */
    private Cuenta cliente;
    /* Si el torneo esta finalizado. */
    private boolean finalizado;

    /**
     * Crea un torneo nueva.
     * 
     * @param participantes la lista de los participantes del torneo.
     * @param cliente       el cliente del torneo.
     */
    public Torneo(Lista<Gallito> participantes) {
        this.participantes = participantes;
        this.perdedores = new Lista<Gallito>();
        this.ganador = null;
        this.numRonda = 0;
        this.ronda = null;
        this.cliente = null;
        this.finalizado = false;
        shuffle();
    }

    /**
     * Vuelve a los participantes en un arreglo.
     * El arreglo que regresa puede ser aceptado por el método
     * {@link Torneo#volverLista}.
     * 
     * @return un arreglo con los participantes.
     */
    private Gallito[] volverArreglo() {
        Gallito[] regresar = new Gallito[participantes.size()];
        for (int i = 0; i < regresar.length; i++) {
            regresar[i] = participantes.buscarIndice(i);
        }
        return regresar;
    }

    /**
     * Agrega todas los participantes de un arreglo a la lista de participantes.
     * El arreglo que recibe debe ser del método
     * {@link Torneo#volverArreglo}.
     * 
     * @param arreglo el arreglo con los participantes a agregar.
     */
    private void volverLista(Gallito[] arreglo) {
        participantes.empty();
        for (int i = 0; i < arreglo.length; i++) {
            participantes.add(arreglo[i]);
        }
    }

    /**
     * Revuelve a los participantes del torneo.
     */
    public void shuffle() {
        Gallito[] arreglo = volverArreglo();
        shuffleAux(arreglo);
        volverLista(arreglo);
    }

    /**
     * Auxiliar para revolver a los participantes del torneo.
     * Algoritmo: Fisher–Yates shuffle
     * Fuente:
     * https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
     * 
     * @param array el arreglo con las cartas a revolver.
     */
    private void shuffleAux(Gallito[] array) {
        int n = array.length;
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            Gallito randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }
    }

    /**
     * Inicia el torneo.
     * 
     * @throws TorneoPausa cuando se va a pausar el torneo.
     */
    public void iniciar(Cuenta cliente) throws TorneoPausa {
        this.cliente = cliente;
        try {
            System.out.println("El torneo va a empezar/continuar...");
            while (numRonda != 4) {
                this.numRonda++;
                if (ronda == null) {
                    this.ronda = new Ronda(participantes, perdedores, numRonda, cliente);
                }
                this.ronda.iniciar(cliente);
                this.participantes = ronda.getParticipantes();
                this.perdedores = ronda.getPerdedores();
                this.cliente = ronda.getCliente();
                this.ronda = null;
            }
            this.ganador = participantes.buscarIndice(0);
            System.out.println("El ganador del torneo es...");
            System.out.println(ganador.toStringBonito());
            this.finalizado = true;
            for (Gallito gallito : perdedores) {
                participantes.add(gallito);
            }
            perdedores.empty();
        } catch (TorneoPausa tp) {
            this.participantes = ronda.getParticipantes();
            this.perdedores = ronda.getPerdedores();
            this.cliente = ronda.getCliente();
            numRonda--;
            throw new TorneoPausa();
        }
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
     * Regresa el cliente.
     * 
     * @return el cliente.
     */
    public Cuenta getCliente() {
        return cliente;
    }

    /**
     * Dice si ya termino el torneo.
     * 
     * @return si ya termino el torneo.
     */
    public boolean isFinalizado() {
        return finalizado;
    }
}