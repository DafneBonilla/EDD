package Wizard;

public class Proyecto1 {
    
    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: Proyecto1 <# jugadores>");
        System.exit(0);
    }

    public static void main(String[] args) {
        /*
        if (args.length != 1) {
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
        
        Baraja mano = new Baraja();
        for (int i=0; i<6; i++) {
            mano.agregaCarta(new Carta(String.valueOf(i),"rojo"));
        }
        System.out.println(mano);
        mano.shuffle();
        System.out.println(mano);
    }
}
