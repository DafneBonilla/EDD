package Apuestas;

import Apuestas.Estructuras.Lista;

public class Proyecto3 {

    public static void main(String[] args) {
        Cuenta cliente = new Cuenta("patito", "patito");
        cliente.aumentarSaldo(30);
        Lista<Gallito> lista = new Lista<>();
        lista.add(new Gallito("pedro1", 50, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro2", 400, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro3", 90, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro4", 356, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro5", 99, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro6", 240, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro7", 345, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro8", 51, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro9", 98, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro10", 320, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro11", 65, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro12", 399, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro13", 250, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro14", 132, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro15", 34, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        lista.add(new Gallito("pedro16", 29, "\u001B[93m■\u001B[0m ", "\u001B[90m■\u001B[0m ", "\u001B[91m■\u001B[0m ", "\u001B[91m■\u001B[0m "));
        try {
            Torneo carrera = new Torneo(lista, cliente);
            carrera.iniciar();
            cliente = carrera.getCliente();
            lista = carrera.getPartisipantes();
            System.out.println(cliente.getHistorial());
        } catch (TorneoPausa e) {
            System.out.println("Se pauso el torneo");
        }
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


Lista<Dinosaurio> lista = new Lista<>();
        lista.add(new Dinosaurio("pedro1", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 1,
                16, 5, 4, 10));
        lista.add(new Dinosaurio("pedro2", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 2,
                15, 4, 3, 9));
        lista.add(new Dinosaurio("pedro3", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 3,
                14, 3, 2, 8));
        lista.add(new Dinosaurio("pedro4", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 4,
                13, 2, 1, 7));
        lista.add(new Dinosaurio("pedro5", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 5,
                12, 1, 16, 6));
        lista.add(new Dinosaurio("pedro6", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 6,
                11, 16, 15, 5));
 */