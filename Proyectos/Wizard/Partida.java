package Wizard;

import Wizard.Estructuras.Lista;
import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Clase para representar una partida.
 */
public class Partida {
    
    /* Lista de jugadores. */
    private Lista<Jugador> jugadores;
    /* Numero de rondas. */
    private int numRondas;
    /* Mazo principal del juego. */
    private Baraja mazo;
    /* Historial del juego. */
    private String log;
    /* Archivo del historial. */
    private String historial;
    /* Ver si el juego sigue. */
    private Boolean sigue;
    /* Seed de random */
    private long seed;

    /**
     * Define el estado inicial de una partida.
     * @param numJugadores el numero de jugadores.
     * @param archivo el archivo a escribir.
     */
    public Partida(int numJugadores, String archivo) {
        jugadores = new Lista<>();
        for (int i = 1; i <= numJugadores; i++) {
            jugadores.add(new Jugador(String.valueOf(i)));
        }
        switch (numJugadores) {
            case 3:
                numRondas = 20;
                break;
            case 4:
                numRondas = 15;
                break;
            case 5:
                numRondas = 12;
                break;
            case 6:
                numRondas = 10;
                break;
            default:
                numRondas = 10;
                break;
        }
        seed = System.currentTimeMillis();
        mazo = new Baraja(seed);
        log = "";
        sigue = true;
        historial = archivo;
    }

    /**
     * Comienza la partida.
     */
    public void iniciar() {
        enviarMensaje("La partida va a empezar, todos listos :)");
        enviarMensaje("La seed del juego es "+seed);
        for (int i = 1; i <= numRondas; i++) {
            Ronda actual = new Ronda(jugadores, numRondas, log, mazo);
            actual.iniciar();
            seguir();
            if (!sigue) {
                break;
            }
        }
        resultados();
        guardar(historial);
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
     * Muesta los resultados de la partida.
     */
    private void resultados() {
        String resultados = "Ahora se anunciara el ganador del juego...\n\n";
        resultados += ganador();
        enviarMensaje(resultados);
    }

    /**
     * Calcula quien fue el ganador de la partida.
     * @return una cadena con los datos del ganador.
     */
    private String ganador() {
        Iterator<Jugador> iterator = jugadores.iterator();
        int i = 0;
        int contador = 1;
        int[][] arreglo = new int[2][jugadores.size()];
        while (iterator.hasNext()) {
            arreglo[0][i] = contador;
            arreglo[1][i] = iterator.next().getPuntuacion();
            contador++;
            i++;
        }
        return superior(arreglo);
    }

    /**
     * Ayuda a calcular el ganador.
     * @param arreglo un arreglo con los datos de los jugadores.
     * @return una cadena con el ganador.
     */
    private String superior(int[][] arreglo) {
        String winner = "Hubo un empate entre los Jugadores ";
        int posicion = mayor(arreglo);
        boolean empate = false;
        for (int i = 0; i <= jugadores.size(); i++) {
            if (arreglo[1][i] > arreglo[1][posicion]) {
                posicion = i;
            } else if (arreglo[1][i] == arreglo[1][posicion]) {
                posicion = i;
                empate = true;
                winner += posicion+1 + ", ";
            }
        }
        if (empate) {
            return winner+" todos con"+ arreglo[1][posicion]  +"puntos.\n";
        }
        return "El ganador es el Jugador "+posicion+1+" con "+arreglo[1][posicion]+" puntos.\n";
    }

    /**
     * Buscar la puntacion más alta de los jugadores.
     * @param arreglo un arreglo con los datos de los jugadores.
     * @return la posicion del jugador con mayor puntacion.
     */
    private int mayor(int[][] arreglo) {
        int respuesta = 0;
        for (int i = 0; i < jugadores.size(); i++) {
            if (arreglo[1][i] > arreglo[1][i]) {
                respuesta = i;
            }
        }
        return respuesta;
    }

    /**
     * Saber si el juego va a seguir o se detendra.
     */
    private void seguir() {
        System.out.println("¿Quieres seguir jugando? s/n");
        try (Scanner scanner = new Scanner(System.in)) {
            String algo = scanner.nextLine();
            switch (algo) {
                case "s":
                    break;
                case "n":
                    sigue = false;
                    break;
                default:
                    System.out.println("Respuesta inválida.");
                    seguir();
                    break;
            }
        }
    }

    /**
     * Guarda el log en un archivo.
     * @param historial el archivo donde se guardara.
     */
    private void guardar(String historial) { 
        try {
            BufferedWriter out = 
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(historial)));
            out.write(log);
            out.close();
        } catch (IOException ioe) {
            System.out.println("Error al itentar guardar.");
        }
    }
}
