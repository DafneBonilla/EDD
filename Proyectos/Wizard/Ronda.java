package Wizard;

import Wizard.Estructuras.Lista;
import java.util.Iterator;
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
        enviarMensaje("La ronda " + numRonda + " va a empezar");
        mazo.shuffle();
        repartirCartas();
        defineTriunfo();
        defineApuestas();
        for (int i = 1; i <= numTrucos; i++) {
            Truco actual = new Truco(jugadores, log, mazo, triunfo);
            actual.iniciar();
        }
        verPuntuacion();
        enviarMensaje("Las puntaciones se ven asi...");
        int contador = 1;
        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            enviarMensaje("El jugador " + contador + " tiene " + iterator.next().getPuntuacion() + " puntos\n");
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
        }
    }

    /**
     * Define las apuestas de los jugadores.
     */
    private void defineApuestas() {
        int contador = 1;
        try (Scanner scanner = new Scanner(System.in)) {
            for (Jugador jugador : jugadores) {
                System.out.println("Jugador "+ contador + " es tu turno de ver tus cartas.\n(Presiona cualquier tecla para continuar)");
                String basura = scanner.nextLine();
                int ap = pedirApuesta(scanner);
                jugador.setApuesta(ap);
                contador++;
            }
        }
    }

    private int pedirApuesta(Scanner sc) {
        System.out.println("Define tu apuesta (un número entre 0 y " + numRonda + ")");
        int apuesta = sc.nextInt();
        if (apuesta < 0 || apuesta > numRonda) {
            System.out.println("Apuesta inválida");
            return pedirApuesta(sc);
        }
        return apuesta;
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
