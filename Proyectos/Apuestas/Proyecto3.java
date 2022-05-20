package Apuestas;

public class Proyecto3 {

    public static void main(String[] args) {
        Gallito pedro = new Gallito("pedro", 100, "\u001B[33m■\u001B[0m ", "\u001B[30m■\u001B[0m ",
                "\u001B[97m■\u001B[0m ", "\u001B[91m■\u001B[0m ");
        System.out.println(pedro.toStringBonito());

        Dinosaurio rogelio = new Dinosaurio("rogelio", "\u001B[95m■\u001B[0m ", "\u001B[30m■\u001B[0m ",
                "\u001B[91m■\u001B[0m ");
        System.out.println(rogelio.toStringBonito());
    }
}

/*
 * Colores:
 * "\u001B[90m■\u001B[0m "
 * 
 * Negro = 90
 * Rojo = 91
 * Verde = 92
 * Amarillo = 93
 * Azul = 94
 * Morado = 95
 * Cyan = 96
 * Blanco = 97
 * 
 * https://gist.github.com/RabaDabaDoba/145049536f815903c79944599c6f952a
 */