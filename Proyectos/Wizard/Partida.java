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
     * Constructor único.
     */
    public Partida(int numJugadores, String archivo) {
        jugadores = new Lista<>();
        for (int i = 1; i <= numJugadores; i++) {
            jugadores.add(new Jugador());
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
     * Inicia la partida.
     */
    public void iniciar() throws IOException {
        System.out.println("La partida va a empezar, todos listos :)");
        System.out.println("La seed del juego es "+seed);
        for (int i = 1; i <= numRondas; i++) {
            Ronda actual = new Ronda();
            //actual.iniciar(mazo, log, i);
            seguir();
            if (!sigue) {
                break;
            }
        }
        resultados();
        BufferedWriter out = 
            new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(historial))); 
        guardar(out);
    }

    private void resultados() {
        String resultados = "";
        System.out.println(resultados);
        int contador = 1;
        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            resultados += "El jugador " + contador + " tiene "+ iterator.next().getPuntuacion() + " puntos\n";
            contador++;
        }
        resultados += "Ahora se anunciara el ganador del juego...\n\n";
        resultados += ganador();
        log += resultados;
        System.out.print(resultados);
    }

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
                winner += posicion + ", ";
            }
        }
        if (empate) {
            return winner+" todos con"+ arreglo[1][posicion]  +"puntos.\n";
        }
        return "El ganador es el Jugador "+posicion+" con "+arreglo[1][posicion]+" puntos.\n";
    }

    private int mayor(int[][] arreglo) {
        int respuesta = 0;
        for (int i = 0; i < jugadores.size(); i++) {
            if (arreglo[1][i] > arreglo[1][i]) {
                respuesta = i;
            }
        }
        return respuesta;
    }

    public void seguir() {
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

    public void guardar(BufferedWriter out) {
        try {
            out.write(log);
        } catch (IOException ioe) {
            System.out.println("Error al itentar guardar.");
        }
    }
}
