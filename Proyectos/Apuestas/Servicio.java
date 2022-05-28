package Apuestas;

import java.util.Scanner;
import Apuestas.Estructuras.Lista;

/**
 * Clase para representar un menu.
 * Un servicio tiene un cliente, una lista de gallitos, una lista de
 * dinosaurios, si sigue en funcionamiento y un torneo.
 */
public class Servicio {

    /* Cliente del servicio. */
    private Cuenta cliente;
    /* Lista de gallitos del servicio. */
    private Lista<Gallito> gallitos;
    /* Lista de dinosaurios del servicio. */
    private Lista<Dinosaurio> dinosaurios;
    /* Si el servicio esta en funcionamiento. */
    private boolean funcionando;
    /* Torneo del servicio. */
    private Torneo torneo;

    /**
     * Crea un servicio nuevo.
     * 
     * @param cliente     el cliente del servicio.
     * @param dinosaurios la lista de dinosaurios del servicio.
     * @param torneo      el torneo del servicio.
     */
    public Servicio(Cuenta cliente, Lista<Dinosaurio> dinosaurios, Torneo torneo) {
        this.cliente = cliente;
        this.gallitos = torneo.getParticipantes();
        this.dinosaurios = dinosaurios;
        this.funcionando = true;
        this.torneo = torneo;
    }

    /**
     * Inicia el servicio.
     */
    public void iniciar() {
        while (funcionando) {
            System.out.println("Bienvenido al servicio de apuestas " + cliente.getNombre() + ".");
            System.out.println("Cliente: " + cliente.getNombre());
            System.out.println("Saldo: " + cliente.getSaldo());
            System.out.println("1. Ver/Apostar en un torneo.");
            System.out.println("2. Ver/Apostar en una carrera.");
            System.out.println("3. Ver el historial de la cuenta.");
            System.out.println("4. Salir.");
            System.out.print("Seleccione una opcion: ");
            Scanner sc = new Scanner(System.in);
            String opcion = sc.nextLine();
            try {
                int op = Integer.parseInt(opcion);
                switch (op) {
                    case 1:
                        System.out.println("Al torneo de gallitos.");
                        if (torneo.isFinalizado()) {
                            this.torneo = new Torneo(gallitos);
                        }
                        torneo.iniciar(cliente);
                        this.gallitos = torneo.getParticipantes();
                        this.cliente = torneo.getCliente();
                        break;
                    case 2:
                        System.out.println("A la carrera de dinosaurios.");
                        Carrera carrera = new Carrera(dinosaurios, cliente);
                        carrera.iniciar();
                        this.dinosaurios = carrera.getParticipantes();
                        this.cliente = carrera.getCliente();
                        break;
                    case 3:
                        String historial = cliente.getHistorial();
                        System.out.println(historial);
                        System.out.println("Enter para continuar.");
                        sc.nextLine();
                        break;
                    case 4:
                        System.out.println("Hasta la proxima :)");
                        funcionando = false;
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Opcion incorrecta.");
                continue;
            } catch (TorneoPausa tp) {
                continue;
            }
            System.out.println("Guardando datos...");
        }
    }

    /**
     * Devuelve la lista de gallitos del servicio.
     * 
     * @return la lista de gallitos del servicio.
     */
    public Lista<Gallito> getGallitos() {
        return gallitos;
    }

    /**
     * Devuelve la lista de dinosaurios del servicio.
     * 
     * @return la lista de dinosaurios del servicio.
     */
    public Lista<Dinosaurio> getDinosaurios() {
        return dinosaurios;
    }

    /**
     * Devuelve el cliente del servicio.
     * 
     * @return el cliente del servicio.
     */
    public Cuenta getCliente() {
        return cliente;
    }

    /**
     * Devuelve el torneo del servicio.
     * 
     * @return el torneo del servicio.
     */
    public Torneo getTorneo() {
        return torneo;
    }
}