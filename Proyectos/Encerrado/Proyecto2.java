package Encerrado;

public class Proyecto2 {

    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: Encerrado/Proyecto2");
        System.exit(0);
    }

    public static void main(String[] args) {

        Tablero prueba = new Tablero(1);
        System.out.println(prueba);
        prueba.mover(1, 3);
        System.out.println(prueba);
        prueba.mover(2, 1);
        System.out.println(prueba);

    }

}
