package Wizard;

import Wizard.Estructuras.Lista;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Clase para representar una partida.
 */
public class Partida {
    
    /* Lista de jugadores. */
    private Lista<Jugador> jugadores;
    /* Número de rondas. */
    private int numRondas;
    /* Mazo principal del juego. */
    private Baraja mazo;
    /* Ver si el juego sigue. */
    private Boolean sigue;
    /* Manera de escribir en el archivo. */
    private BufferedWriter out;
    /* Scanner para comunicación con el usuario. */
    private Scanner sc;
    /* Seed de random. */
    private long seed;

    /**
     * Define el estado inicial de una partida.
     * @param numJugadores el número de jugadores.
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
        sigue = true;
        sc = new Scanner(System.in);
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo)));
        } catch (FileNotFoundException fnfe) {
            System.out.println("No se pudo abrir el archivo, abortando la ejecución...");
            System.exit(0);
        }
    }

    /**
     * Comienza la partida.
     */
    public void iniciar() {
        try {
            enviarMensaje("La partida va a empezar, todos listos :)");
            enviarMensaje("La seed del juego es " + seed);
            for (int i = 1; i <= numRondas; i++) {
                Ronda actual = new Ronda(jugadores, i, mazo, sc, out);
                actual.iniciar();  
                if (i != numRondas) {
                    seguir();
                } 
                if (!sigue) {
                    break;
                }
            resultados();
            }
        } catch (IOException ioe) {
            System.out.println("Hubo un problema al escribir en el archivo, el juego se terminará.");
            resultados2();
            System.exit(0);
        }
        try {
            out.close();
        } catch (IOException ioe) {
            System.out.println("No se pudo cerrar el archivo, abortando la ejecución...");
            System.exit(0);
        }
    }

    /**
     * Imprime un mensaje al usuario y guarda el mensaje 
     * en el archivo.
     * @param mensaje el mensaje a imprimir y agregar.
     * @throws IOException si hubo un problema al escribir en el archivo.
     */
    private void enviarMensaje(String mensaje) throws IOException {
        System.out.println(mensaje + "\n");
        out.write(mensaje);
        out.newLine();
    }

    /**
     * Muesta los resultados de la partida.
     * @throws IOException si hubo un problema al escribir en el archivo.
     */
    private void resultados() throws IOException {
        String resultados = "Ahora se anunciará al ganador del juego...\n\n";
        resultados += ganador();
        enviarMensaje(resultados);
    }

    /**
     * Muesta los resultados de la partida si hubo un problema al escribir en el archivo.
     */
    private void resultados2() {
        String resultados = "Ahora se anunciará al ganador del juego...\n\n";
        resultados += ganador();
        System.out.println(resultados);
    }

    /**
     * Calcula quién fue el ganador de la partida.
     * @return una cadena con los datos del ganador.
     */
    private String ganador() {
        return superior(jugadores);
    }

    /**
     * Calcula el jugador ganador.
     * @param lista una lista con los jugadores.
     * @return una cadena con el ganador.
     */
    private String superior(Lista<Jugador> lista) {
        String winner = "Hubo un empate entre los Jugadores ";
        int posicion = mayor(lista);
        Jugador ganadorsito = lista.buscarIndice(posicion);
        int punt = ganadorsito.getPuntuacion();
        Lista<Jugador> resto = lista.clone();
        resto.delete2(posicion);
        boolean empate = false;
        for (Jugador jug : resto) {
            if (jug.getPuntuacion() == punt) {
                empate = true;
                winner += jug.getNombre() + ", ";
            }
        }
        if (empate) {
            winner = winner.substring(0, winner.length() - 2);
            winner += " y " + ganadorsito.getNombre();
            return winner + " todos con " + punt  + " puntos.\n";
        }
        return "El ganador es el Jugador " + ganadorsito.getNombre() + " con " + ganadorsito.getPuntuacion() + " puntos.\n";
    }

    /**
     * Busca la puntación más alta de los jugadores.
     * @param lista una lista con los jugadores.
     * @return la posición del jugador con mayor puntación.
     */
    private int mayor(Lista<Jugador> lista) {
        int respuesta = 0;
        int puntuacion = lista.buscarIndice(0).getPuntuacion();
        for (Jugador jug : lista) {
            if (jug.getPuntuacion() >= puntuacion) {
                respuesta = lista.indexOf(jug);
            }
        }
        return respuesta;
    }

    /**
     * Ayuda a saber si el juego va a seguir o se detendrá.
     */
    private void seguir() {
        System.out.println("¿Quieres seguir jugando? s/n");
        String respuesta = sc.nextLine();
        switch (respuesta) {
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