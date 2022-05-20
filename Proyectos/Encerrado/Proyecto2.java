package Encerrado;

import Encerrado.Estructuras.Lista;
import java.util.Scanner;

public class Proyecto2 {

    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: Encerrado/Proyecto2 #version");
        System.exit(0);
    }

    /* Pide la configuración personalizada del tablero */
    private static Lista<Lista<Integer>> pedirPersonalizado() {
        Lista<Lista<Integer>> config = new Lista<Lista<Integer>>();
        Lista<Integer> rojos = new Lista<Integer>();
        Lista<Integer> azules = new Lista<Integer>();
        Lista<Integer> opciones = new Lista<>();
        for (int i = 1; i < 6; i++) {
            opciones.agregaFinal(i);
        }
        boolean valido = false;
        String pregunta = "Da la primera posición de la ficha roja";
        int posicion = -1;
        while (!valido) {
            posicion = pedirDato(pregunta, 1, 5);
            valido = validar(posicion, opciones);
        }
        rojos.add(posicion);
        opciones.delete(posicion);
        valido = false;
        pregunta = "Da la segunda posición de la ficha roja";
        while (!valido) {
            posicion = pedirDato(pregunta, 1, 5);
            valido = validar(posicion, opciones);
        }
        rojos.add(posicion);
        opciones.delete(posicion);
        valido = false;
        pregunta = "Da la primera posición de la ficha azul";
        posicion = -1;
        while (!valido) {
            posicion = pedirDato(pregunta, 1, 5);
            valido = validar(posicion, opciones);
        }
        azules.add(posicion);
        opciones.delete(posicion);
        valido = false;
        pregunta = "Da la segunda posición de la ficha azul";
        posicion = -1;
        while (!valido) {
            posicion = pedirDato(pregunta, 1, 5);
            valido = validar(posicion, opciones);
        }
        azules.add(posicion);
        opciones.delete(posicion);
        config.agregaFinal(rojos);
        config.agregaFinal(azules);
        return config;
    }

    /* Valida si un número es valido */
    private static boolean validar(int posicion, Lista<Integer> opciones) {
        if (opciones.contains(posicion)) {
            return true;
        } else {
            System.out.println("La posición no es válida");
            return false;
        }
    }

    /* Pide un dato al usuario */
    private static int pedirDato(String pregunta, int rango1, int rango2) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(pregunta);
        String datos = scanner.nextLine();
        try {
            int respuesta = Integer.parseInt(datos);
            if (respuesta < rango1 || respuesta > rango2) {
                System.out.println("Número inválido");
                return pedirDato(pregunta, rango1, rango2);
            }
            return respuesta;
        } catch (NumberFormatException nfe) {
            System.out.println("Respuesta inválida");
            return pedirDato(pregunta, rango1, rango2);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            uso();
        }
        int version = -1;
        try {
            version = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            uso();
        }
        System.out.println("Configuración del juego: ");
        String pregunta = "¿Quién jugará primero?\n[1] Jugador humano\n[2] Jugador CPU";
        int primero = pedirDato(pregunta, 1, 2);
        pregunta = "¿Cuál será la configuración?\n[1] Ficha roja en la esquina superior izquierda\n[2] Ficha azul en la esquina superior izquierda\n[3] Personalizado";
        int configuracion = pedirDato(pregunta, 1, 3);
        if (configuracion != 1 && configuracion != 2) {
            Lista<Lista<Integer>> config = pedirPersonalizado();
            pregunta = "¿Cuál será la inteligencia de la CPU?\n[0] Random\n[1] Minimax";
            int inteligencia = pedirDato(pregunta, 0, 1);
            pregunta = "¿Que color tendra el Jugador humano?\n[1] Rojo\n[2] Azul";
            int color1 = pedirDato(pregunta, 1, 2);
            int color2 = color1 == 1 ? 2 : 1;
            Lista<Jugador> jugadores = new Lista<Jugador>();
            if (primero == 1) {
                jugadores.add(new Jugador(color1));
                jugadores.add(new JugadorCPU(color2, inteligencia));
            } else {
                jugadores.add(new JugadorCPU(color2, inteligencia));
                jugadores.add(new Jugador(color1));
            }
            Partida partida = new Partida(new Tablero(configuracion, version, config), jugadores);
            partida.iniciar();
        } else {
            pregunta = "¿Cuál será la inteligencia de la CPU?\n[0] Random\n[1] Minimax";
            int inteligencia = pedirDato(pregunta, 0, 1);
            pregunta = "¿Que color tendra el Jugador humano?\n[1] Rojo\n[2] Azul";
            int color1 = pedirDato(pregunta, 1, 2);
            int color2 = color1 == 1 ? 2 : 1;
            Lista<Jugador> jugadores = new Lista<Jugador>();
            if (primero == 1) {
                jugadores.add(new Jugador(color1));
                jugadores.add(new JugadorCPU(color2, inteligencia));
            } else {
                jugadores.add(new JugadorCPU(color2, inteligencia));
                jugadores.add(new Jugador(color1));
            }
            Partida partida = new Partida(new Tablero(configuracion, version, null), jugadores);
            partida.iniciar();
        }
    }
}

// notas
// primero:
// 1: jugador humano, 2: jugador CPU
// configuraciones:
// 1: rojo esquiza sup izq, 2: azul esquiza sup izq, 3: personalizado
// inteligencias:
// 0: random, 1: minimax
// colores: (color1 es el del jugador humano)
// 1: es el rojo, 2: es el azul
// versiones:
// 1: con circulitos, 2: sin circulitos