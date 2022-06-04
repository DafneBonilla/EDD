package Apuestas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import Apuestas.Estructuras.Lista;

public class Proyecto3 {

    /**
     * Imprime un mensaje de cómo usar el programa.
     */
    private static void uso() {
        System.out.println("Uso: java Apuestas/Proyecto3 cuentas.txt torneo.txt dino.txt");
        System.exit(0);
    }

    /**
     * Recibe al usuario y ve si creara una cuenta o usara una ya existente.
     * 
     * @param lista lista de cuentas ya existentes.
     * @return la cuenta del usuario.
     */
    public static Cuenta inicio(Lista<Cuenta> lista) {
        return null;
    }

    /**
     * Crea una cuenta nueva, con lo pedido al usuario.
     * 
     * @param lista lista de cuentas ya existentes.
     * @return la cuenta del usuario.
     */
    public static Cuenta crearCuenta(Lista<Cuenta> lista) {
        Scanner sc = new Scanner(System.in);
        return null;
    }

    /**
     * Le pregunta al usuario por su cuenta y se valida si la cuenta es correcta y
     * si es asi, la devuelve.
     * 
     * @param lista lista de cuentas ya existentes.
     * @return la cuenta del usuario.
     */
    public static Cuenta buscarCliente(Lista<Cuenta> lista) {
        Scanner sc = new Scanner(System.in);
        return null;
    }

    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream("cliente.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Cuenta cliente2 = (Cuenta) ois.readObject();
            FileInputStream fis2 = new FileInputStream("torneo.txt");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            Torneo torneo2 = (Torneo) ois2.readObject();
            FileInputStream fis3 = new FileInputStream("dino.txt");
            ObjectInputStream ois3 = new ObjectInputStream(fis3);
            @SuppressWarnings("unchecked")
            Lista<Dinosaurio> lista3 = (Lista<Dinosaurio>) ois3.readObject();
            Servicio ser2 = new Servicio(cliente2, lista3, torneo2);
            ser2.iniciar();
            cliente2 = ser2.getCliente();
            torneo2 = ser2.getTorneo();
            lista3 = ser2.getDinosaurios();
            FileOutputStream fos = new FileOutputStream("cliente.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cliente2);
            oos.flush();
            FileOutputStream fos2 = new FileOutputStream("torneo.txt");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(torneo2);
            oos2.flush();
            FileOutputStream fos3 = new FileOutputStream("dino.txt");
            ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
            oos3.writeObject(lista3);
            oos3.flush();
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo");
        } catch (ClassNotFoundException e) {
            System.out.println("Error");
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
 * 
 * 
 * Lista<Dinosaurio> lista = new Lista<>();
 * lista.add(new Dinosaurio("pedro1", "\u001B[92m■\u001B[0m ",
 * "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 1,
 * 16, 5, 4, 10));
 * lista.add(new Dinosaurio("pedro2", "\u001B[92m■\u001B[0m ",
 * "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 2,
 * 15, 4, 3, 9));
 * lista.add(new Dinosaurio("pedro3", "\u001B[92m■\u001B[0m ",
 * "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 3,
 * 14, 3, 2, 8));
 * lista.add(new Dinosaurio("pedro4", "\u001B[92m■\u001B[0m ",
 * "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 4,
 * 13, 2, 1, 7));
 * lista.add(new Dinosaurio("pedro5", "\u001B[92m■\u001B[0m ",
 * "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 5,
 * 12, 1, 16, 6));
 * lista.add(new Dinosaurio("pedro6", "\u001B[92m■\u001B[0m ",
 * "\u001B[92m■\u001B[0m ", "\u001B[92m■\u001B[0m ", 6,
 * 11, 16, 15, 5));
 * 
 */

/**
 * serializar 101
 * try {
 * FileOutputStream ostream = new FileOutputStream("tree.tmp");
 * ObjectOutputStream p = new ObjectOutputStream(ostream);
 * tree base = new tree(3);
 * 
 * p.writeObject(base); // Write the tree to the stream.
 * p.flush();
 * ostream.close(); // close the file.
 * 
 * FileInputStream istream = new FileInputStream("tree.tmp");
 * ObjectInputStream q = new ObjectInputStream(istream);
 * 
 * tree new_tree = (tree)q.readObject();
 * 
 * new_tree.print(3); // Print out the top 3 levels of the tree
 * } catch (Exception ex) {
 * ex.printStackTrace();
 * }
 */
