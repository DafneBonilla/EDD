package Wizard;

import Wizard.Estructuras.Lista;
import java.util.Iterator;

/**
 * Clase para representar una ronda.
 */
public class Ronda {

    /* Lista de jugadores. */
    private Lista<Jugador> jugadores;
    /* Numero de ronda acutal. */
    private int numRonda;
    /* Numero de trucos. */
    private int numTrucos;
    /* Palo de triundo. */
    private Color triunfo;
    /* Historial del juego. */
    private String log;
    /* Mazo principal del juego. */
    private Baraja mazo;

    /**
     * Define el estado inicial de una ronda.
     * @param jugadores la lista de jugadores.
     * @param numRonda el numero de la ronda actual.
     * @param log la cadena del historial del juego.
     * @param mazo la baraja principal.
     */
    public Ronda(Lista<Jugador> jugadores, int numRonda, String log, Baraja mazo) {
        this.jugadores = jugadores;
        this.numRonda = numRonda;
        this.numTrucos = numRonda;
        this.triunfo = new Color(-1);
        this.log = log;
        this.mazo = mazo;
    }

    /**
     * Comienza la ronda.
     */
    public void iniciar() {
        enviarMensaje("La ronda "+numRonda+" va a empezar");
        repartirCartas();
        defineTriunfo();
        defineApuestas();
        for (int i = 1; i < numTrucos; i++) {
            Truco actual = new Truco();
            actual.iniciar();
        }
        verPuntuacion();
        enviarMensaje("Las puntaciones se ven asi...");
        int contador = 1;
        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            enviarMensaje("El jugador " + contador + " tiene "+ iterator.next().getPuntuacion() + " puntos\n");
            contador++;
        }
    }

    /**
     * Imprime un mensaje al usuario, ademes el mensaje lo
     * agrega a log.
     * @param mensaje el mensaje a imprimir y agregar.
     */
    private void enviarMensaje(String mensaje) {
        System.out.println(mensaje);
        log += mensaje;
    }

    /**
     * Reparte las cartas a los jugadores.
     */
    private void repartirCartas() {
        
    }

    /**
     * Define la bara de triunfo de la ronda.
     */
    private void defineTriunfo() {

    }

    /**
     * Define las apuestas de los jugadores.
     */
    private void defineApuestas() {

    }

    /**
     * Define la puntaucion de los jugadores
     * basado en sus apuestas.
     */
    private void verPuntuacion() {

    }
    
}
