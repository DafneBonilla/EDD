package WizardServidor;

import WizardServidor.Estructuras.Lista;
import java.io.BufferedWriter;
import java.io.IOException;

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
    /* Manera de escribir en el archivo. */
    private BufferedWriter out;

    /**
     * Define el estado inicial de una ronda.
     * @param jugadores la lista de jugadores.
     * @param numRonda el numero de la ronda actual.
     * @param log la cadena del historial del juego.
     * @param mazo la baraja principal.
     */
    public Ronda(Lista<Jugador> jugadores, int numRonda, Baraja mazo, BufferedWriter out) {
        this.jugadores = jugadores;
        this.numRonda = numRonda;
        this.numTrucos = numRonda;
        this.triunfo = new Color(-1);
        this.mazo = mazo;
        this.out = out;
    }

    /**
     * Comienza la ronda.
     */
    public void iniciar() throws IOException {
        enviarMensajeTodos("La ronda " + numRonda + " va a empezar");
        mazo.shuffle();
        repartirCartas();
        defineTriunfo();
        defineApuestas();
        for (int i = 1; i <= numTrucos; i++) {
            Truco actual = new Truco(jugadores, mazo, triunfo, out);
            actual.iniciar();
        }
        verPuntuacion();
        enviarMensajeTodos("Las puntaciones se ven así...");
        for (Jugador jugador : jugadores) {
            enviarMensajeTodos("El jugador " + jugador.getNombre() + " tiene " + jugador.getPuntuacion() + " puntos\n");
        }
    }

    /**
     * Imprime un mensaje a todos los usuarios y guarda 
     * el mensaje en el archivo.
     * @param mensaje el mensaje a imprimir y agregar.
     */
    private void enviarMensajeTodos(String mensaje) throws IOException {
        System.out.println(mensaje+"\n");
        out.write(mensaje);
        out.newLine();
        for (Jugador jugador : jugadores) {
            enviarMensajeJugador(jugador, mensaje);
        }
    }

    private void enviarMensajeJugador(Jugador jugador, String mensaje) throws JugadorInactivo {
        jugador.hablarJugador(mensaje);
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
     * Define la bara de triunfo de la ronda.
     */
    private void defineTriunfo() throws IOException {
        if (!mazo.esVacio()) {
            Carta cartita = mazo.sacaCarta(0);
            Color triunfi = cartita.getColor();
            switch (triunfi.getMerito()) {
                case 5:
                    if (cartita.getValor().getNumero() == 0) {
                        enviarMensajeTodos("El palo de triunfo es " + triunfo);
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
            enviarMensajeTodos("El palo de triunfo es " + triunfo);
        }
    }

    /**
     * Pide al usuario que elija el palo de triunfo.
     */
    private void pedirTriunfo() throws JugadorInactivo {
        Jugador elegir = jugadores.buscarIndice(0);
        enviarMensajeJugador(elegir, "Jugador " + elegir.getNombre() + " elige el palo de triunfo");
        int i = validarTriunfo(elegir);
        triunfo = new Color(i);
    }

    /**
     * Valida que el palo de triunfo sea válido.
     * @return el número del palo de triunfo.
     */
    private int validarTriunfo(Jugador jugador) throws JugadorInactivo {
        enviarMensajeJugador(jugador, "Escribe el número del palo de triunfo \n 1 para \u001B[91mrojo\u001B[0m \n 2 para \u001B[94mazul\u001B[0m \n 3 para \u001B[93mamarillo\u001B[0m \n 4 para \u001B[92mverde\u001B[0m");
        String respuesta = jugador.leerJugador();
        try {
            int i = Integer.parseInt(respuesta);
            if (i < 1 || i > 4) {
                throw new NumberFormatException();
            }
            return i;
        } catch (NumberFormatException e) {
            enviarMensajeJugador(jugador, "No es un número válido");
            return validarTriunfo(jugador);
        }
    }

    /**
     * Define las apuestas de los jugadores.
     */
    private void defineApuestas() throws IOException {
        for (Jugador jugador : jugadores) {
            enviarMensajeJugador(jugador, "Jugador "+ jugador.getNombre() + " es tu turno de ver tus cartas.");
            enviarMensajeJugador(jugador, "Tu mano actual es\n" + jugador.verBarajaOrdenada());
            enviarMensajeTodos("\nEl palo de triunfo es " + triunfo + "\n");
            int ap = pedirApuesta(jugador);
            jugador.setApuesta(ap);
            enviarMensajeTodos("El jugador " + jugador.getNombre() + " ha apostado " + ap);
        }
    }

    /**
     * Pide una apuesta al usuario.
     * @return la apuesta del usuario.
     */
    private int pedirApuesta(Jugador jugador) throws JugadorInactivo {
        enviarMensajeJugador(jugador, "Define tu apuesta (un número entre 0 y " + numRonda + ")");
        String cadenita = jugador.leerJugador();
        try {
            int apuesta = Integer.parseInt(cadenita);
            if (apuesta < 0 || apuesta > numRonda) {
                System.out.println("Apuesta inválida");
                return pedirApuesta(jugador);
            }
            return apuesta;
        } catch (NumberFormatException e) {
            System.out.println("No ingresaste un número");
            return pedirApuesta(jugador);
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