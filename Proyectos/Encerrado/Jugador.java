package Encerrado;

import java.util.Scanner;
import Encerrado.Estructuras.Lista;

/**
 * Clase para representar Jugadores.
 * Un jugador tiene nombre, un scanner para leer y un boolean.
 */
public class Jugador {

    /* Nombre del jugador. */
    private int nombre;
    /* Scanner del jugador. */
    private Scanner scanner;
    /* Booleano para saber si el jugador es humano. */
    private boolean humano;

    /**
     * Define el estado inicial de un jugador.
     * 
     * @param nombre el nombre del jugador.
     */
    public Jugador(int nombre) {
        this.nombre = nombre;
        this.scanner = new Scanner(System.in);
        this.humano = true;
    }

    /**
     * Regresa el nombre del jugador.
     * 
     * @return el nombre del jugador.
     */
    public int getNombre() {
        return nombre;
    }

    /**
     * Pide un movimiento al jugador.
     *
     * @param opciones las opciones que puede tomar el jugador.
     * @param tablero  el tablero del juego.
     * @return el movimiento del jugador.
     */
    public Opcion pedirMovimiento(Lista<Opcion> opciones, Tablero tablero) {
        System.out.println("Las opciones son:");
        System.out.println(opciones.verOpcionesBonito());
        System.out.print("Escoge una opción: ");
        String texto = scanner.nextLine();
        try {
            int i = Integer.parseInt(texto);
            if (i < 0 || i > (opciones.size() - 1)) {
                System.out.println("Número inválido");
                return pedirMovimiento(opciones, tablero);
            }
            Opcion respuesta = opciones.buscarIndice(i);
            return respuesta;
        } catch (NumberFormatException nfe) {
            System.out.println("No ingresaste un número");
            return pedirMovimiento(opciones, tablero);
        }
    }

    /**
     * Regresa si el jugador es humano.
     *
     * @return code<true> si el jugador es humano,
     *         code<false> en caso contrario.
     */
    public boolean isHumano() {
        return humano;
    }

    /**
     * Cambia el estado del jugador.
     * 
     * @param humano el nuevo estado del jugador.
     */
    public void setHumano(boolean humano) {
        this.humano = humano;
    }

    /**
     * Modifica la inteligencia del jugador.
     * 
     * @param inteligencia la nueva inteligencia del jugador.
     */
    public void setInteligencia(int inteligencia) {
        return;
    }

    /**
     * Pregunta si se cambiara la inteligencia del otro jugador.
     * 
     * @param jugador2 el otro jugador.
     */
    public void cambioInteligencia(Jugador jugador2) {
        System.out.println("¿Quieres cambiar la inteligencia de la CPU? s/n");
        String texto = scanner.nextLine();
        switch (texto) {
            case "s":
                cambio(jugador2);
                break;
            case "n":
                break;
            default:
                System.out.println("No ingresaste una opción válida");
                cambioInteligencia(jugador2);
                break;
        }
    }

    /**
     * Cambia la inteligencia del otro jugador.
     * 
     * @param jugador2 el otro jugador.
     */
    private void cambio(Jugador jugador2) {
        System.out.println("Opciones:");
        System.out.println("[0] Para random");
        System.out.println("[1] Para minimax");
        String texto = scanner.nextLine();
        try {
            int i = Integer.parseInt(texto);
            if (i < 0 || i > 1) {
                System.out.println("Número inválido");
                cambio(jugador2);
            }
            if (i == 0) {
                jugador2.setInteligencia(0);
            } else {
                jugador2.setInteligencia(1);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("No ingresaste un número");
            cambio(jugador2);
        }
    }
}