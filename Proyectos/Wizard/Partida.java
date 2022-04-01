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
    /* Numero de rondas. */
    private int numRondas;
    /* Mazo principal del juego. */
    private Baraja mazo;
    /* Ver si el juego sigue. */
    private Boolean sigue;
    /* Manera de escribir en el archivo. */
    private BufferedWriter out;
    /* Scanner para comunicacion con el usuario. */
    private Scanner sc;
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
        sigue = true;
        sc = new Scanner(System.in);
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo)));
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir el archivo, abortando la ejecución.");
            System.exit(0);
        }
    }

    /**
     * Comienza la partida.
     */
    public void iniciar() {
        enviarMensaje("La partida va a empezar, todos listos :)");
        enviarMensaje("La seed del juego es "+seed);
        for (int i = 1; i <= numRondas; i++) {
            Ronda actual = new Ronda(jugadores, i, mazo, sc, out);
            actual.iniciar();   
            seguir();
            if (!sigue) {
                break;
            }
        }
        resultados();
        try {
            out.close();
        } catch (IOException e) {
            System.out.println("No se pudo cerrar el archivo, abortando la ejecución.");
            System.exit(0);
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
            System.out.println("Error al guardar el mensaje, abortando la ejercución.");
            System.exit(0);
        }
    }

    /**
     * Muesta los resultados de la partida.
     */
    private void resultados() {
        String resultados = "Ahora se anunciará al ganador del juego...\n\n";
        resultados += ganador();
        enviarMensaje(resultados);
    }

    /**
     * Calcula quien fue el ganador de la partida.
     * @return una cadena con los datos del ganador.
     */
    private String ganador() {
        return superior(jugadores);
    }

    /**
     * Ayuda a calcular el ganador.
     * @param arreglo un arreglo con los datos de los jugadores.
     * @return una cadena con el ganador.
     */
    private String superior(Lista<Jugador> lista) {
        String winner = "Hubo un empate entre los Jugadores ";
        int posicion = mayor(lista);
        int punt = jugadores.buscarIndice(posicion).getPuntuacion();
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
            winner += " y " + lista.buscarIndice(posicion).getNombre();
            return winner + " todos con " + jugadores.buscarIndice(posicion).getPuntuacion()  + " puntos.\n";
        }
        Jugador ganadorsito = jugadores.buscarIndice(posicion);
        return "El ganador es el Jugador " + ganadorsito.getNombre() + " con " + ganadorsito.getPuntuacion() + " puntos.\n";
    }

    /**
     * Buscar la puntacion más alta de los jugadores.
     * @param arreglo un arreglo con los datos de los jugadores.
     * @return la posicion del jugador con mayor puntacion.
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
     * Saber si el juego va a seguir o se detendrá.
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
