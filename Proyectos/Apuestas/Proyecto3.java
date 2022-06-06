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
     * Recibe al usuario y ve si creara una cuenta o usara una ya existente.
     * 
     * @param lista lista de cuentas ya existentes.
     * @return la cuenta del usuario.
     */
    public static Cuenta inicio(Lista<Cuenta> lista) {
        System.out.println("Bienvenido a Apuestas \"Doña Susi\".");
        System.out.println("1. Crear una cuenta.");
        System.out.println("2. Iniciar sesion.");
        System.out.println("3. Salir.");
        System.out.print("Seleccione una opcion: ");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();
        try {
            int respuesta = Integer.parseInt(opcion);
            switch (respuesta) {
                case 1:
                    return crearCuenta(lista);
                case 2:
                    return buscarCliente(lista);
                case 3:
                    System.out.println("Saliendo...");
                    System.exit(0);
                default:
                    System.out.println("Opcion invalida.");
                    return inicio(lista);
            }   
        } catch (NumberFormatException nfe) {
            System.out.println("Opcion invalida.");
            return inicio(lista);
        }
    }

    /**
     * Crea una cuenta nueva, con lo pedido al usuario.
     * 
     * @param lista lista de cuentas ya existentes.
     * @return la cuenta del usuario.
     */
    public static Cuenta crearCuenta(Lista<Cuenta> lista) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario o presione 2 para salir: ");
        String nombreUsuario = sc.nextLine();
        if (nombreUsuario.equals("2")) {
            return inicio(lista);            
        }
        for (Cuenta c : lista) {
            if (c.getNombre().equals(nombreUsuario)) {
                System.out.println("La cuenta ya existe.");
                return crearCuenta(lista);
            }
        }
        System.out.print("Ingrese su contraseña o presione 2 para salir: ");
        String contrasena = sc.nextLine();
        if (contrasena.equals("2")) {
            return inicio(lista);            
        }
        Cuenta nueva = new Cuenta(nombreUsuario, contrasena);
        return nueva;
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
        System.out.print("Ingrese su nombre de usuario o presione 2 para salir: ");
        String nombreUsuario = sc.nextLine();
        if (nombreUsuario.equals("2")) {
            return inicio(lista);            
        }
        Boolean existe = false;
        Cuenta buscada = null;
        for (Cuenta c : lista) {
            if (c.getNombre().equals(nombreUsuario)) {
                existe = true;
                buscada = c;
            }
        }
        if (!existe) {
            System.out.println("La cuenta no esta registrada");
            return buscarCliente(lista);
        }
        lista.delete(buscada);
        Boolean correcto = false;
        while (!correcto) {
            System.out.println("Ingrese su contraseña o presione 2 para salir: ");
            String contrasena = sc.nextLine();
            if (contrasena.equals("2")) {
                lista.add(buscada);
                return inicio(lista);            
            }
            if (buscada.getContrasena().equals(contrasena)) {
                break;
            }
            System.out.println("Contraseña incorrecta.");
        }
        return buscada;
    }

    public static void main(String[] args) {

        Lista<Cuenta> listaClientes = new Lista<>();
        listaClientes.add(new Cuenta("Pedro", "1234"));
        listaClientes.add(new Cuenta("Juan", "5678"));
        listaClientes.add(new Cuenta("Maria", "9012"));
        listaClientes.add(new Cuenta("Susana", "3456"));
        listaClientes.add(new Cuenta("Camilo", "7890"));
        listaClientes.add(new Cuenta("Jose", "4567"));
        listaClientes.add(new Cuenta("Dafne", "7890"));
        listaClientes.add(new Cuenta("Laura", "3871"));
        try {
            FileOutputStream fos = new FileOutputStream("clientes.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaClientes);
            oos.flush();
        } catch (IOException ioe) {
            System.out.println("No se pudo leer el archivo");
        }
        System.exit(0);

        try {
            FileInputStream fis = new FileInputStream("clientes.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            Lista<Cuenta> cuentas = (Lista<Cuenta>) ois.readObject();
            FileInputStream fis2 = new FileInputStream("torneo.txt");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            Torneo torneo2 = (Torneo) ois2.readObject();
            FileInputStream fis3 = new FileInputStream("dino.txt");
            ObjectInputStream ois3 = new ObjectInputStream(fis3);
            @SuppressWarnings("unchecked")
            Lista<Dinosaurio> lista3 = (Lista<Dinosaurio>) ois3.readObject();
            Cuenta cliente = inicio(cuentas);
            Servicio ser2 = new Servicio(cliente, lista3, torneo2);
            ser2.iniciar();
            cliente = ser2.getCliente();
            cuentas.add(cliente);
            torneo2 = ser2.getTorneo();
            lista3 = ser2.getDinosaurios();
            FileOutputStream fos = new FileOutputStream("clientes.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cuentas);
            oos.flush();
            FileOutputStream fos2 = new FileOutputStream("torneo.txt");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(torneo2);
            oos2.flush();
            FileOutputStream fos3 = new FileOutputStream("dino.txt");
            ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
            oos3.writeObject(lista3);
            oos3.flush();
        } catch (IOException ioe) {
            System.out.println("No se pudo leer/guardar el archivo");
        } catch (ClassNotFoundException cnfe) {
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
