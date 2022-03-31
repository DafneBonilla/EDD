package Wizard;

import Wizard.Estructuras.Lista;
import java.util.Scanner;

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
    /* Scanner para comunicacion con el usuario. */
    private Scanner sc;

    /**
     * Define el estado inicial de una ronda.
     * @param jugadores la lista de jugadores.
     * @param numRonda el numero de la ronda actual.
     * @param log la cadena del historial del juego.
     * @param mazo la baraja principal.
     */
    public Ronda(Lista<Jugador> jugadores, int numRonda, String log, Baraja mazo, Scanner sc) {
        this.jugadores = jugadores;
        this.numRonda = numRonda;
        this.numTrucos = numRonda;
        this.triunfo = new Color(-1);
        this.log = log;
        this.mazo = mazo;
        this.sc = sc;
    }

    /**
     * Comienza la ronda.
     */
    public void iniciar() {
        enviarMensaje("La ronda " + numRonda + " va a empezar");
        mazo.shuffle();
        repartirCartas();
        defineTriunfo();
        defineApuestas();
        for (int i = 1; i <= numTrucos; i++) {
            Truco actual = new Truco(jugadores, log, mazo, triunfo, sc);
            actual.iniciar();
        }
        verPuntuacion();
        enviarMensaje("Las puntaciones se ven asi...");
        for (Jugador jugador : jugadores) {
            enviarMensaje("El jugador " + jugador.getNombre() + " tiene " + jugador.getPuntuacion() + " puntos\n");
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
        for (int i = 0; i < numRonda; i++) {
            for (Jugador jugador : jugadores) {
                jugador.recibirCarta(mazo.sacaCarta(0));
            }
        }
    }

    /**
     * Define la bara de triunfo de la ronda.
     */
    private void defineTriunfo() {
        if (!mazo.esVacio()) {
            triunfo = mazo.sacaCarta(0).getColor();
            enviarMensaje("El palo de triunfo es " + triunfo);
        }

    }

    /**
     * Define las apuestas de los jugadores.
     */
    private void defineApuestas() {
        for (Jugador jugador : jugadores) {
            System.out.println("Jugador "+ jugador.getNombre() + " es tu turno de ver tus cartas.");
            System.out.println("Tu mano actual es\n" + jugador.getBaraja().toStringOrden());
            int ap = pedirApuesta(sc);
            jugador.setApuesta(ap);
            enviarMensaje("El jugador " + jugador.getNombre() + " ha apostado " + ap);
        }
    }

    /**
     * Pide una apuesta al usuario.
     * @param sc el scanner para pedir datos.
     * @return la apuesta del usuario.
     */
    private int pedirApuesta(Scanner sc) {
        System.out.println("Define tu apuesta (un número entre 0 y " + numRonda + ")");
        String cadenita = sc.nextLine();
        try {
            int apuesta = Integer.parseInt(cadenita);
            if (apuesta < 0 || apuesta > numRonda) {
                System.out.println("Apuesta inválida");
                return pedirApuesta(sc);
            }
            return apuesta;
        } catch (NumberFormatException e) {
            System.out.println("No ingresaste un número.");
            return pedirApuesta(sc);
        }
    }

    /**
     * Define la puntuación de los jugadores
     * basado en sus apuestas.
     */
    private void verPuntuacion() {
        for (Jugador jugador : jugadores) {
            if (jugador.getApuesta() == jugador.getGanados()) {
                int punt = jugador.getPuntuacion();
                punt += 20 + 10*jugador.getGanados();
                jugador.setPuntuacion(punt);
            }
            jugador.setApuesta(0);
            jugador.setGanados(0);
        }
    }
    
}
