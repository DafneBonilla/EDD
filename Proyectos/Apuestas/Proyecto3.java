package Apuestas;

import Apuestas.Estructuras.Lista;

public class Proyecto3 {

    public static void main(String[] args) {
        Cuenta cliente = new Cuenta("patito", "patito");
        cliente.aumentarSaldo(30);
        Lista<Dinosaurio> lista = new Lista<>();
        lista.add(new Dinosaurio("pedro1", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 1, 16, 5, 4, 10));
        lista.add(new Dinosaurio("pedro2", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 2, 15, 4, 3, 9));
        lista.add(new Dinosaurio("pedro3", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 3, 14, 3, 2, 8));
        lista.add(new Dinosaurio("pedro4", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 4, 13, 2, 1, 7));
        lista.add(new Dinosaurio("pedro5", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 5, 12, 1, 16, 6));
        lista.add(new Dinosaurio("pedro6", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 6, 11, 16, 15, 5));
        lista.add(new Dinosaurio("pedro7", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 7, 10, 15, 14, 4));
        lista.add(new Dinosaurio("pedro8", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 8, 9, 14, 13, 3));
        lista.add(new Dinosaurio("pedro9", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 9, 8, 13, 12, 2));
        lista.add(new Dinosaurio("pedro10", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 10, 7, 12, 11, 1));
        lista.add(new Dinosaurio("pedro11", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 11, 6, 11, 10, 16));
        lista.add(new Dinosaurio("pedro12", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 12, 5, 10, 9, 15));
        lista.add(new Dinosaurio("pedro13", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 13, 4, 9, 8, 14));
        lista.add(new Dinosaurio("pedro14", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 14, 3, 8, 7, 13));
        lista.add(new Dinosaurio("pedro15", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 15, 2, 7, 6, 12));
        lista.add(new Dinosaurio("pedro16", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 16, 1, 6, 5, 11));
        try {
            Carrera carrera = new Carrera(lista, cliente);
            carrera.iniciar();
            cliente = carrera.getCliente();
            lista = carrera.getPartisipantes();
            System.out.println(cliente.getHistorial());
            Carrera carrera2 = new Carrera(lista, cliente);
            carrera2.iniciar();
            cliente = carrera2.getCliente();
            lista = carrera2.getPartisipantes();
            System.out.println(cliente.getHistorial());
        } catch (Exception e) {
            System.out.println("Error al escuchar");
            System.exit(0);
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
 */