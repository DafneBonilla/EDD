package Encerrado;

import Encerrado.Estructuras.Lista;

public class Proyecto2 {

    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: Encerrado/Proyecto2");
        System.exit(0);
    }

    private static void inicializar(int primero, int configuracion, int inteligencia, int color1, int color2,
            Lista<Lista<Integer>> config) {
        // preguntar y actualizar
    }

    public static void main(String[] args) {

        int primero = -2;
        int configuracion = -2;
        int inteligencia = -2;
        int color1 = -2;
        int color2 = -2;
        Lista<Integer> rojos = new Lista<Integer>();
        Lista<Integer> azules = new Lista<Integer>();
        Lista<Lista<Integer>> config = new Lista<Lista<Integer>>();
        config.add(rojos);
        config.add(azules);
        inicializar(primero, configuracion, inteligencia, color1, color2, config);
        Lista<Jugador> jugadores = new Lista<Jugador>();
        if (primero == 1) {
            jugadores.add(new Jugador(color1));
            jugadores.add(new JugadorCPU(color2, inteligencia));
        } else {
            jugadores.add(new JugadorCPU(color2, inteligencia));
            jugadores.add(new Jugador(color1));
        }
        if (configuracion != 3) {
            Partida partida = new Partida(new Tablero(configuracion), jugadores);
            partida.iniciar();
        } else {
            Partida partida = new Partida(new Tablero(config), jugadores);
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