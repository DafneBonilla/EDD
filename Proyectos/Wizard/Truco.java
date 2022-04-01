package Wizard;

import Wizard.Estructuras.Lista;
import java.io.BufferedWriter;
import java.util.Scanner;

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
    /* Scanner para comunicacion con el usuario. */
    private Scanner sc;
    /* Manera de escribir en el archivo. */
    private BufferedWriter out;
    
    /**
     * Define el estado inicial de una ronda.
     * @param jugadores la lista de jugadores.
     * @param log la cadena del historial del juego.
     * @param mazo la baraja principal.
     * @param sc el scanner para comunicacion con el usuario.
     */
    public Truco(Lista<Jugador> jugadores, Baraja mazo, Color triunfo, Scanner sc, BufferedWriter out) {
        this.jugadores = jugadores;
        this.triunfo = triunfo;
        this.lider = new Color(-1);
        this.mazo = mazo;
        this.jugadas = new Lista<>();
        this.sc = sc;
        this.out = out;
    }

    /**
     * Comienza el truco.
     */
    public void iniciar() {
        enviarMensaje("El truco va a empezar");
        for (Jugador jugador : jugadores) {
            System.out.println("Jugador "+ jugador.getNombre() + " es tu turno de jugar una carta.");
            System.out.println("El palo lider es "+lider);
            System.out.println("El palo de triunfo es "+triunfo);
            System.out.println("Tu mano actual es\n" + jugador.verBarajaOrdenada());
            int indice = validarCarta(sc, jugador);
            Carta cartita = recibeCarta(jugador, indice);
            enviarMensaje("El jugador " + jugador.getNombre() + " jugó la carta " + cartita);
            defineLider(cartita);
            jugadas.agregaFinal(cartita);
        }
        int ganador = cartaGanadora();
        Jugador jug = jugadores.buscarIndice(ganador);
        int ganados = jug.getGanados();
        jug.setGanados(ganados + 1);
        enviarMensaje("El jugador " + jug.getNombre() + " ganó el truco");
        for (Carta carta : jugadas) {
            mazo.agregaCarta(carta);
        }
        actualizarLista(ganador);
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
     * Define el color lider.
     * @param carta la carta con el color lider.
     */
    private void defineLider(Carta carta) {
        if (lider.getMerito() == -1) {
            if (carta.getColor().getMerito() == 0) {
                return;
            }
            lider = carta.getColor();
            enviarMensaje("El palo lider es " + lider);
        }
    }

    /**
     * Saca una carta de la mano del jugador.
     * @return la carta sacada.
     */
    private Carta recibeCarta(Jugador jugador, int i) {
        return jugador.sacaCarta(i);
    }
    
    /**
     * Revisa si el índice de la carta es válido.
     * @param sc el mensaje a imprimir y agregar.
     * @param jugador el mensaje a imprimir y agregar.
     */
    private int validarCarta(Scanner sc, Jugador jugador) {
        System.out.println("Ingresa el número (entre 0 y " + (jugador.getBaraja().tamanio()-1) +") de la carta a jugar.");
        String cadenita = sc.nextLine();
        try {
            int i = Integer.parseInt(cadenita);
            if (i < 0 || i > (jugador.getBaraja().tamanio()-1)) {
                System.out.println("Número inválido");
                return validarCarta(sc, jugador);
            }
            Carta cartita = jugador.getBaraja().checaCarta(i);
            if (cartaLegal(cartita, jugador.getBaraja().copia(), i)) {
                return i;
            } else {
                System.out.println("Carta inválida, debes jugar otra carta");
                return validarCarta(sc, jugador);
            }
        } catch (NumberFormatException e) {
            System.out.println("No ingresaste un número");
            return validarCarta(sc, jugador);
        }
    }

    /**
     * Revisa si la carta es legal para jugarla.
     * @param carta la carta a revisar.
     * @param baraja la baraja del jugador.
     * @param i el índice de la carta.
     * @return <code>true</code> si la carta es legal, <code>false</code> en caso contrario.
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
     * @return el índice del primer jugador que jugo un mago.
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
     * @return el índice del primer jugador que jugo una carta con el plao de triunfo.
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
     * Revisa si un jugador jugó una carta con el palo lider.
     * @return el índice del primer jugador que jugo una carta con el palo lider.
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
     * @return el índice del primer jugador que jugo un bufón.
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
     * @param i el índice del ganador del truco.
     */
    private void actualizarLista(int i) {
        for (int j = 0; j < i; j++) {
            Jugador ajustando = jugadores.delete2(0);
            jugadores.agregaFinal(ajustando);
        }
    }
}