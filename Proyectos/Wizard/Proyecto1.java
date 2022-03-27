package Wizard;

public class Proyecto1 {
    
    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: java Wizard/Proyecto1 #jugadores archivo");
        System.exit(0);
    }

    public static void main(String[] args) {
        /*
        if (args.length != 2) {
            uso();
        }

        int numJugadores = 0;
        try {
            numJugadores = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            uso();
        }
        if (numJugadores < 3 || numJugadores > 6) {
            uso();
        }
        */

        Baraja mano = new Baraja(System.currentTimeMillis());
        System.out.println("baraja original");
        System.out.println(mano);
        mano.shuffle();
        System.out.println("baraja revuelta");
        System.out.println(mano);
        System.out.println("baraja ordenada");
        System.out.println(mano.toStringOrden());
        
    }
}
