package Encerrado;

import java.util.Scanner;
import Encerrado.Estructuras.Lista;

/**
 * Clase para representar JUgadores.
 * Un jugador tiene nombre y un scanner para leer.
 */
public class Jugador {

    /* Nombre del jugador. */
    private int nombre;
    /* Scanner del jugador. */
    private Scanner scanner;

    /**
     * Define el estado inicial de un jugador.
     * 
     * @param nombre el nombre del jugador.
     */
    public Jugador(int nombre) {
        this.nombre = nombre;
        this.scanner = new Scanner(System.in);
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
     */
    public Opcion pedirMovimiento(Lista<Opcion> opciones, Tablero tablero) {
        System.out.println("El tablero es:");
        System.out.println(tablero);
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

}
