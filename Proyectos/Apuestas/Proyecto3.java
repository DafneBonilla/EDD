package Apuestas;

public class Proyecto3 {

    public static void main(String[] args) {
        Gallito pedro = new Gallito("pedro", 100, "\u001B[93m■\u001B[0m ", "\u001B[30m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m ");
        System.out.println(pedro.toStringBonito());

        Dinosaurio rogelio = new Dinosaurio("rogelio", "\u001B[94m■\u001B[0m ", "\u001B[30m■\u001B[0m ", "\u001B[91m■\u001B[0m ");
        System.out.println(rogelio.toStringBonito());
    }
}