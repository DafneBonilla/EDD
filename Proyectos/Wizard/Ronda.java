package Wizard;

import Wizard.Estructuras.Lista;
import java.io.BufferedWriter;
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
    /* Mazo principal del juego. */
    private Baraja mazo;
    /* Scanner para comunicacion con el usuario. */
    private Scanner sc;
    /* Manera de escribir en el archivo. */
    private BufferedWriter out;

    /**
     * Define el estado inicial de una ronda.
     * @param jugadores la lista de jugadores.
     * @param numRonda el numero de la ronda actual.
     * @param log la cadena del historial del juego.
     * @param mazo la baraja principal.
     */
    public Ronda(Lista<Jugador> jugadores, int numRonda, Baraja mazo, Scanner sc, BufferedWriter out) {
        this.jugadores = jugadores;
        this.numRonda = numRonda;
        this.numTrucos = numRonda;
        this.triunfo = new Color(-1);
        this.mazo = mazo;
        this.sc = sc;
        this.out = out;
    }

    /**
     * Comienza la ronda.
     */
    public void iniciar() {
        enviarMensaje("La ronda " + numRonda + " va a empezar");
        mazo.shuffle();
        System.out.println("EL TAMAÑO DEL MAZO ES " + mazo.tamanio());
        repartirCartas();
        System.out.println("EL TAMAÑO DEL MAZO LUEGO DE REPARTIR ES " + mazo.tamanio());
        defineTriunfo();
        defineApuestas();
        for (int i = 1; i <= numTrucos; i++) {
            Truco actual = new Truco(jugadores, mazo, triunfo, sc, out);
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
     * guarda en el archivo.
     * @param mensaje el mensaje a imprimir y agregar.
     */
    private void enviarMensaje(String mensaje) {
        System.out.println(mensaje+"\n");
        try {
            out.write(mensaje);
            out.newLine();
        } catch (Exception e) {
            System.out.println("Error al guardar el mensaje, abortando la ejercucion.");
            System.exit(0);
        }
    }

    /**
     * Reparte las cartas a los jugadores.
     */
    private void repartirCartas() {
        for (int i = 0; i < numRonda; i++) {
            for (Jugador jugador : jugadores) {
                if (mazo.esVacio()) {
                    System.out.println("El mazo se vacio");
                    System.exit(0);
                }
                Carta cartita = mazo.sacaCarta(0);
                jugador.recibirCarta(cartita);
            }
        }
    }

    /**
     * Define la bara de triunfo de la ronda.
     */
    private void defineTriunfo() {
        if (!mazo.esVacio()) {
            Carta cartita = mazo.sacaCarta(0);
            Color triunfi = cartita.getColor();
            switch (triunfi.getMerito()) {
                case 5:
                    if (cartita.getValor().getNumero() == 0) {
                        enviarMensaje("El palo de triunfo es " + triunfo);
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
     * Valida que el palo de triunfo sea valido.
     * @return el numero del palo de triunfo.
     */
    private int validarTriunfo() {
        System.out.println("Escribe el numero del palo de triunfo \n 1 para \u001B[91mrojo\u001B[0m \n 2 para \u001B[94mazul\u001B[0m \n 3 para \u001B[93mamarillo\u001B[0m \n 4 para \u001B[92mverde\u001B[0m");
        String respuesta = sc.nextLine();
        try {
            int i = Integer.parseInt(respuesta);
            if (i < 1 || i > 4) {
                throw new NumberFormatException();
            }
            return i;
        } catch (NumberFormatException e) {
            System.out.println("No es un numero valido");
            return validarTriunfo();
        }
    }

    /**
     * Define las apuestas de los jugadores.
     */
    private void defineApuestas() {
        for (Jugador jugador : jugadores) {
            System.out.println("Jugador "+ jugador.getNombre() + " es tu turno de ver tus cartas.");
            System.out.println("Tu mano actual es\n" + jugador.verBarajaOrdenada());
            System.out.println("\nEl palo de triunfo es " + triunfo + "\n");
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
            } else {
                int gan = jugador.getGanados();
                int ap = jugador.getApuesta();
                if (gan > ap) {
                    int diferencia = gan - ap;
                    int punt = jugador.getPuntuacion();
                    punt -= diferencia*10;
                    jugador.setPuntuacion(punt);
                } else {
                    int diferencia = ap - gan;
                    int punt = jugador.getPuntuacion();
                    punt -= diferencia*10;
                    jugador.setPuntuacion(punt);
                }
            }
            jugador.setApuesta(0);
            jugador.setGanados(0);
        }
    }
    
}
