package WizardServidor;

import WizardServidor.Estructuras.Lista;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Clase para representar una truco.
 */
public class Truco {

    /* Lista de jugadores. */
    private Lista<Jugador> jugadores;
    /* Palo de triunfo. */
    private Color triunfo;
    /* Palo líder. */
    private Color lider;
    /* Mazo principal del juego. */
    private Baraja mazo;
    /* Lista de cartas jugadas. */
    private Lista<Carta> jugadas;
    /* Manera de escribir en el archivo. */
    private BufferedWriter out;
    /* El historial de la partida. */
    private String log;

    /**
     * Define el estado inicial de una ronda.
     * 
     * @param jugadores la lista de jugadores.
     * @param log       la cadena del historial del juego.
     * @param mazo      la baraja principal.
     */
    public Truco(Lista<Jugador> jugadores, Baraja mazo, Color triunfo, BufferedWriter out, String log) {
        this.jugadores = jugadores;
        this.triunfo = triunfo;
        this.lider = new Color(-1);
        this.mazo = mazo;
        this.jugadas = new Lista<>();
        this.out = out;
        this.log = log;
    }

    /**
     * Comienza el truco.
     * 
     * @throws IOException si hubo un error de entrada/salida.
     */
    public void iniciar() throws IOException {
        enviarMensajeTodos("El truco va a empezar");
        for (Jugador jugador : jugadores) {
            enviarMensajeJugador(jugador, "Jugador " + jugador.getNombre() + " es tu turno de jugar una carta");
            enviarMensajeJugador(jugador, "El palo líder es " + lider);
            enviarMensajeJugador(jugador, "El palo de triunfo es " + triunfo);
            enviarMensajeJugador(jugador, "Tu mano actual es\n" + jugador.verBarajaOrdenada());
            int indice = validarCarta(jugador);
            Carta cartita = recibeCarta(jugador, indice);
            enviarMensajeTodos("El jugador " + jugador.getNombre() + " jugó la carta " + cartita);
            defineLider(cartita);
            jugadas.agregaFinal(cartita);
        }
        int ganador = cartaGanadora();
        Jugador jug = jugadores.buscarIndice(ganador);
        int ganados = jug.getGanados();
        jug.setGanados(ganados + 1);
        enviarMensajeTodos("El jugador " + jug.getNombre() + " gana el truco");
        for (Carta carta : jugadas) {
            mazo.agregaCarta(carta);
        }
        actualizarLista(ganador);
    }

    /**
     * Imprime un mensaje a un usuario.
     * 
     * @param jugador el jugador al que se le imprimirá el mensaje.
     * @param mensaje el mensaje a imprimir.
     * @throws JugadorInactivo si no se pudo imprimir el mensaje.
     */
    private void enviarMensajeJugador(Jugador jugador, String mensaje) throws JugadorInactivo {
        jugador.hablarJugador(mensaje);
    }

    /**
     * Imprime un mensaje a todos los usuarios y guarda
     * el mensaje en el archivo.
     * 
     * @param mensaje el mensaje a imprimir y agregar.
     * @throws IOException si no se pudo imprimir o escribir en el archivo.
     */
    private void enviarMensajeTodos(String mensaje) throws IOException {
        System.out.println(mensaje + "\n");
        log += mensaje + "\n";
        out.write(mensaje);
        out.newLine();
        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            Jugador jug = iterator.next();
            enviarMensajeJugador(jug, mensaje);
        }
    }

    /**
     * Define el color líder.
     * 
     * @param carta la carta con el color líder.
     * @throws IOException si hubo un error de entrada/salida.
     */
    private void defineLider(Carta carta) throws IOException {
        if (lider.getMerito() == -1) {
            if (carta.getColor().getMerito() == 5) {
                return;
            }
            lider = carta.getColor();
            enviarMensajeTodos("El palo líder es " + lider);
        }
    }

    /**
     * Saca una carta de la mano del jugador.
     * 
     * @param jugador el jugador que saca la carta.
     * @param indice  el índice de la carta.
     * @return la carta sacada.
     */
    private Carta recibeCarta(Jugador jugador, int i) {
        return jugador.sacaCarta(i);
    }

    /**
     * Revisa si el índice de la carta es válido.
     * 
     * @param jugador el mensaje a imprimir y agregar.
     * @return el índice de la carta.
     * @throws JugadorInactivo si el jugador se desconectó.
     */
    private int validarCarta(Jugador jugador) throws JugadorInactivo {
        enviarMensajeJugador(jugador,
                "Ingresa el número (entre 0 y " + (jugador.getBaraja().tamanio() - 1) + ") de la carta a jugar (presiona \"h\" para ver todo el historial del juego)");
        String cadenita = jugador.leerJugador();
        try {
            int i = Integer.parseInt(cadenita);
            if (i < 0 || i > (jugador.getBaraja().tamanio() - 1)) {
                enviarMensajeJugador(jugador, "Número inválido");
                return validarCarta(jugador);
            }
            Carta cartita = jugador.getBaraja().checaCarta(i);
            if (cartaLegal(cartita, jugador.getBaraja().copia(), i)) {
                return i;
            } else {
                enviarMensajeJugador(jugador, "Carta inválida, debes jugar otra carta");
                return validarCarta(jugador);
            }
        } catch (NumberFormatException nfe) {
            if (cadenita.equals("h")){
                enviarMensajeJugador(jugador, "Historial:");
                enviarMensajeJugador(jugador, log);
                enviarMensajeJugador(jugador, "Jugador " + jugador.getNombre() + " es tu turno de jugar una carta");
                enviarMensajeJugador(jugador, "El palo líder es " + lider);
                enviarMensajeJugador(jugador, "El palo de triunfo es " + triunfo);
                enviarMensajeJugador(jugador, "Tu mano actual es\n" + jugador.verBarajaOrdenada());
                return validarCarta(jugador);  
            }
            enviarMensajeJugador(jugador, "No ingresaste un número");
            return validarCarta(jugador);
        }
    }

    /**
     * Revisa si la carta es legal para jugarla.
     * 
     * @param carta  la carta a revisar.
     * @param baraja la baraja del jugador.
     * @param i      el índice de la carta.
     * @return <code>true</code> si la carta es legal, <code>false</code> en caso
     *         contrario.
     */
    private boolean cartaLegal(Carta carta, Baraja mano, int i) {
        mano.sacaCarta(i);
        if (mano.esVacio()) {
            return true;
        }
        if (carta.getColor().equals(lider)) {
            return true;
        }
        if (carta.getValor().getNumero() == 0 || carta.getValor().getNumero() == 14) {
            return true;
        }
        if (lider.getMerito() == -1) {
            return true;
        }
        for (Carta cartita : mano.getLista()) {
            if (cartita.getColor().equals(lider)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Revisa cual es la carta ganadora del truco.
     * 
     * @return el índice del jugador ganador.
     */
    private int cartaGanadora() {
        int mago = jugoMago();
        if (mago != -1) {
            return mago;
        }
        int triunfo = altaTriunfo();
        if (triunfo != -1) {
            return triunfo;
        }
        int lider = altaLider();
        if (lider != -1) {
            return lider;
        }
        int bufon = bufon();
        if (bufon != -1) {
            return bufon;
        }
        return -1;
    }

    /**
     * Revisa si un jugador jugó un mago.
     * 
     * @return el índice del primer jugador que jugó un mago.
     */
    private int jugoMago() {
        for (Carta cartita : jugadas) {
            if (cartita.getValor().getNumero() == 14) {
                return jugadas.indexOf(cartita);
            }
        }
        return -1;
    }

    /**
     * Revisa si un jugador jugó una carta con el palo de triunfo.
     * 
     * @return el índice del primer jugador que jugó una carta con el palo de
     *         triunfo.
     */
    private int altaTriunfo() {
        int comparar = 0;
        int indice = -1;
        for (Carta cartita : jugadas) {
            if (cartita.getColor().equals(triunfo)) {
                if (cartita.getValor().getNumero() > comparar) {
                    comparar = cartita.getValor().getNumero();
                    indice = jugadas.indexOf(cartita);
                }
            }
        }
        return indice;
    }

    /**
     * Revisa si un jugador jugó una carta con el palo líder.
     * 
     * @return el índice del primer jugador que jugó una carta con el palo líder.
     */
    private int altaLider() {
        int comparar = 0;
        int indice = -1;
        for (Carta cartita : jugadas) {
            if (cartita.getColor().equals(lider)) {
                if (cartita.getValor().getNumero() > comparar) {
                    comparar = cartita.getValor().getNumero();
                    indice = jugadas.indexOf(cartita);
                }
            }
        }
        return indice;
    }

    /**
     * Revisa si un jugador jugó un bufón.
     * 
     * @return el índice del primer jugador que jugó un bufón.
     */
    private int bufon() {
        for (Carta cartita : jugadas) {
            if (cartita.getValor().getNumero() == 0) {
                return jugadas.indexOf(cartita);
            }
        }
        return -1;
    }

    /**
     * Actualiza el orden de jugadores.
     * 
     * @param i el índice del ganador del truco.
     */
    private void actualizarLista(int i) {
        for (int j = 0; j < i; j++) {
            Jugador ajustando = jugadores.delete2(0);
            jugadores.agregaFinal(ajustando);
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