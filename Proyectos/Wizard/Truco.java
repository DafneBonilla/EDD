package Wizard;

import Wizard.Estructuras.Lista;
import java.util.Scanner;

/**
 * Clase para representar una truco.
 */
public class Truco {

    /* Lista de jugadores. */
    private Lista<Jugador> jugadores;
    /* Historial del juego. */
    private String log;
    /* Palo de triunfo. */
    private Color triunfo;
    /* Palo líder. */
    private Color lider;
    /* Mazo principal del juego. */
    private Baraja mazo;
    /* Lista de cartas. */
    private Carta[] jugadas;
    
    public Truco(Lista<Jugador> jugadores, String log, Baraja mazo, Color triunfo) {
        this.jugadores = jugadores;
        this.log = log;
        this.triunfo = triunfo;
        this.lider = new Color(-1);
        this.mazo = mazo;
        this.jugadas = new Carta[jugadores.size()];
    }

    /**
     * Comienza el truco.
     */
    public void iniciar() {
        enviarMensaje("El truco va a empezar");
        int cont = 1;
        try (Scanner scanner = new Scanner(System.in)) {
            for (Jugador jugador : jugadores) {
                System.out.println("Jugador "+ jugador.getNombre() + " es tu turno de jugar una carta.");
                System.out.println("Tu mano actual es\n" + jugador.getBaraja());
                int indice = validarCarta(scanner, jugador);
                Carta cartita = recibeCarta(jugador, indice);
                defineLider(cartita);
                enviarMensaje("El jugador " + jugador.getNombre() + " jugó la carta " + cartita);
                jugadas[cont - 1] = cartita;
                cont++;
            }
            int ganador = cartaGanadora();
            Jugador jug = jugadores.buscarIndice(ganador);
            int ganados = jug.getGanados();
            jug.setGanados(ganados);
            enviarMensaje("El jugador " + ganador + " ganó el truco");
            for (Carta carta : jugadas) {
                mazo.agregaCarta(carta);
            }
            actualizarLista(ganador);
        }
    }
    
    /**
     * Imprime un mensaje al usuario, además el mensaje lo
     * agrega a log.
     * @param mensaje el mensaje a imprimir y agregar.
     */
    private void enviarMensaje(String mensaje) {
        System.out.println(mensaje);
        log += mensaje;
    }

    private void defineLider(Carta carta) {
        if (lider.getMerito() == -1) {
            if (carta.getColor().getMerito() == 0) {
                return;
            }
            lider = carta.getColor();
        }
    }

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
        int i = Integer.parseInt(cadenita);
        if (i < 0 || i > (jugador.getBaraja().tamanio()-1)) {
            System.out.println("Número inválido");
            return validarCarta(sc, jugador);
        }
        Carta cartita = jugador.getBaraja().checaCarta(i);
        if (cartaLegal(cartita, jugador.getBaraja().copia(), i)) {
            return i;
        } else {
            System.out.println("Carta inválida");
            return validarCarta(sc, jugador);
        }
    }

    private boolean cartaLegal(Carta carta, Baraja mano, int i) {
        boolean colorLider = false;
        mano.sacaCarta(i);
        if (mano.esVacio()) {
            return true;
        }
        if (carta.getColor() == lider) {
            colorLider = true;
        }
        if (carta.getValor().getNumero() == 0 || carta.getValor().getNumero() == 14) {
            return true;
        }
        for (Carta cartita : mano.getLista()) {
            if (cartita.getColor() == lider) {
                if (!colorLider) {
                    return false;
                }
            }
        }
        return true;
    }

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

    private int jugoMago() {
        for (int i = 0; i < jugadas.length; i++) {
            if (jugadas[i].getValor().getNumero() == 14) {
                return i;
            }
        }
        return -1;
    }

    private int altaTriunfo() {
        int comparar = 0;
        int indice = -1;
        for (int i = 0; i < jugadas.length; i++) {
            if (jugadas[i].getColor().getMerito() == triunfo.getMerito()) {
                if (jugadas[i].getValor().getNumero() > comparar) {
                    comparar = jugadas[i].getValor().getNumero();
                    indice = i;
                }
            }
        }
        return indice;
    }

    private int altaLider() {
        int comparar = 0;
        int indice = -1;
        for (int i = 0; i < jugadas.length; i++) {
            if (jugadas[i].getColor().getMerito() == lider.getMerito()) {
                if (jugadas[i].getValor().getNumero() > comparar) {
                    comparar = jugadas[i].getValor().getNumero();
                    indice = i;
                }
            }
        }
        return indice;
    }

    private int bufon() {
        for (int i = 0; i < jugadas.length; i++) {
            if (jugadas[i].getValor().getNumero() == 0) {
                return i;
            }
        }
        return -1;
    }

    private void actualizarLista(int i) {
        Jugador ganador = jugadores.delete2(i);
        jugadores.agregaInicio(ganador);
    }
}