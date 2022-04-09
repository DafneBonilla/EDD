package Wizard;

import Wizard.Estructuras.Lista;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase para representar una ronda.
 */
public class Ronda {

    /* Lista de jugadores. */
    private Lista<Jugador> jugadores;
    /* Número de ronda acutal. */
    private int numRonda;
    /* Número de trucos. */
    private int numTrucos;
    /* Palo de triunfo. */
    private Color triunfo;
    /* Mazo principal del juego. */
    private Baraja mazo;
    /* Scanner para comunicación con el usuario. */
    private Scanner sc;
    /* Manera de escribir en el archivo. */
    private BufferedWriter out;
    /* El historial de la partida. */
    private String log;

    /**
     * Define el estado inicial de una ronda.
     * 
     * @param jugadores la lista de jugadores.
     * @param numRonda  el número de la ronda actual.
     * @param mazo      la baraja principal.
     * @param sc        el scanner para comunicación con el usuario.
     * @param out       la manera de escribir en el archivo.
     */
    public Ronda(Lista<Jugador> jugadores, int numRonda, Baraja mazo, Scanner sc, BufferedWriter out, String log) {
        this.jugadores = jugadores;
        this.numRonda = numRonda;
        this.numTrucos = numRonda;
        this.triunfo = new Color(-1);
        this.mazo = mazo;
        this.sc = sc;
        this.out = out;
        this.log = log;
    }

    /**
     * Comienza la ronda.
     * 
     * @throws IOException si hay un error de entrada/salida.
     */
    public void iniciar() throws IOException {
        enviarMensaje("La ronda " + numRonda + " va a empezar");
        mazo.shuffle();
        repartirCartas();
        defineTriunfo();
        defineApuestas();
        for (int i = 1; i <= numTrucos; i++) {
            Truco actual = new Truco(jugadores, mazo, triunfo, sc, out, log);
            actual.iniciar();
            log = actual.getLog();
        }
        verPuntuacion();
        enviarMensaje("Las puntaciones se ven así...");
        for (Jugador jugador : jugadores) {
            enviarMensaje("El jugador " + jugador.getNombre() + " tiene " + jugador.getPuntuacion() + " puntos\n");
        }
    }

    /**
     * Imprime un mensaje al usuario y guarda el mensaje
     * en el archivo.
     * 
     * @param mensaje el mensaje a imprimir y agregar.
     */
    private void enviarMensaje(String mensaje) throws IOException {
        System.out.println(mensaje + "\n");
        out.write(mensaje);
        out.newLine();
    }

    /**
     * Reparte las cartas a los jugadores.
     */
    private void repartirCartas() {
        for (int i = 0; i < numRonda; i++) {
            for (Jugador jugador : jugadores) {
                Carta cartita = mazo.sacaCarta(0);
                jugador.recibirCarta(cartita);
            }
        }
    }

    /**
     * Define la carta de triunfo de la ronda.
     * 
     * @throws IOException si hubo un error de entrada/salida.
     */
    private void defineTriunfo() throws IOException {
        if (!mazo.esVacio()) {
            Carta cartita = mazo.sacaCarta(0);
            Color triunfi = cartita.getColor();
            switch (triunfi.getMerito()) {
                case 5:
                    if (cartita.getValor().getNumero() == 0) {
                        enviarMensaje("El palo de triunfo es " + triunfo);
                        mazo.agregaCarta(cartita);
                        return;
                    }
                    pedirTriunfo();
                    break;
                default:
                    triunfo = triunfi;
                    break;
            }
            mazo.agregaCarta(cartita);
            enviarMensaje("El palo de triunfo es " + triunfo);
        }
    }

    /**
     * Pide al usuario que elija el palo de triunfo.
     */
    private void pedirTriunfo() {
        Jugador elegir = jugadores.buscarIndice(0);
        System.out.println("Jugador " + elegir.getNombre() + " elige el palo de triunfo");
        int i = validarTriunfo();
        triunfo = new Color(i);
    }

    /**
     * Valida que el palo de triunfo sea válido.
     * 
     * @return el número del palo de triunfo.
     */
    private int validarTriunfo() {
        System.out.println(
                "Escribe el número del palo de triunfo \n 1 para \u001B[91mrojo\u001B[0m \n 2 para \u001B[94mazul\u001B[0m \n 3 para \u001B[93mamarillo\u001B[0m \n 4 para \u001B[92mverde\u001B[0m (presiona \"h\" para ver todo el historial del juego)");
        String respuesta = sc.nextLine();
        try {
            int i = Integer.parseInt(respuesta);
            if (i < 1 || i > 4) {
                throw new NumberFormatException();
            }
            return i;
        } catch (NumberFormatException nfe) {
            if (respuesta.equals("h")) {
                System.out.println("Historial:");
                System.out.println(log);
                return validarTriunfo();
            }
            System.out.println("No es un número válido");
            return validarTriunfo();
        }
    }

    /**
     * Define las apuestas de los jugadores.
     * 
     * @throws IOException si hubo un error de entrada/salida.
     */
    private void defineApuestas() throws IOException {
        for (Jugador jugador : jugadores) {
            System.out.println("Jugador " + jugador.getNombre() + " es tu turno de ver tus cartas.");
            System.out.println("Tu mano actual es\n" + jugador.verBarajaOrdenada());
            System.out.println("\nEl palo de triunfo es " + triunfo + "\n");
            int ap = pedirApuesta(sc, jugador);
            jugador.setApuesta(ap);
            enviarMensaje("El jugador " + jugador.getNombre() + " ha apostado " + ap);
        }
    }

    /**
     * Pide una apuesta al usuario.
     * 
     * @param sc el scanner para pedir datos.
     * @param jugador el jugador al que se le pide la apuesta.
     * @return la apuesta del usuario.
     */
    private int pedirApuesta(Scanner sc, Jugador jugador) {
        System.out.println("Define tu apuesta (un número entre 0 y " + numRonda + ") (presiona \"h\" para ver todo el historial del juego)");
        String cadenita = sc.nextLine();
        try {
            int apuesta = Integer.parseInt(cadenita);
            if (apuesta < 0 || apuesta > numRonda) {
                System.out.println("Apuesta inválida");
                return pedirApuesta(sc, jugador);
            }
            return apuesta;
        } catch (NumberFormatException nfe) {
            if (cadenita.equals("h")) {
                System.out.println("Historial:");
                System.out.println(log);
                System.out.println("Jugador " + jugador.getNombre() + " es tu turno de ver tus cartas");
                System.out.println("Tu mano actual es\n" + jugador.verBarajaOrdenada());
                System.out.println("\nEl palo de triunfo es " + triunfo + "\n");
                return pedirApuesta(sc, jugador);
            }
            System.out.println("No ingresaste un número");
            return pedirApuesta(sc, jugador);
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
                punt += 20 + 10 * jugador.getGanados();
                jugador.setPuntuacion(punt);
            } else {
                int gan = jugador.getGanados();
                int ap = jugador.getApuesta();
                if (gan > ap) {
                    int diferencia = gan - ap;
                    int punt = jugador.getPuntuacion();
                    punt -= diferencia * 10;
                    jugador.setPuntuacion(punt);
                } else {
                    int diferencia = ap - gan;
                    int punt = jugador.getPuntuacion();
                    punt -= diferencia * 10;
                    jugador.setPuntuacion(punt);
                }
            }
            jugador.setApuesta(0);
            jugador.setGanados(0);
        }
    }

    /**
     * Regresa el historial de la partida.
     * 
     * @return el historial de la partida.
     */
    public String getLog() {
        return log;
    }
}