package Apuestas;

import java.util.concurrent.ThreadLocalRandom;
import Apuestas.Estructuras.Lista;

/**
 * Clase para representar Batallas.
 * Una batalla tiene participantes, perdedores, número de batalla, cliente y luchadores.
 */
public class Batalla implements java.io.Serializable {

    /* Participantes de la batalla. */
    private Lista<Gallito> participantes;
    /* Participantes de la batalla. */
    private Lista<Gallito> perdedores;
    /* Numero de batalla de la batalla. */
    private int numBatalla;
    /* Cliente de la batalla. */
    private Cuenta cliente;
    /* Luchadores de la batalla. */
    private Lista<Gallito> luchadores;

    private Gallito ganador;

    /**
     * Crea una batalla nueva.
     * @param participantes la lista de los participantes de la batalla.
     * @param perdedores   la lista de los perdedores de la batalla.
     * @param numBatalla  el numero de batalla de la batalla.
     * @param cliente    el cliente de la batalla.
     * @param luchadores la lista de los luchadores de la batalla.
     */
    public Batalla(Lista<Gallito> participantes, Lista<Gallito> perdedores, int numBatalla, Cuenta cliente, Lista<Gallito> luchadores) {
        this.participantes = participantes;
        this.perdedores = perdedores;
        this.numBatalla = numBatalla;
        this.cliente = cliente;
        this.luchadores = luchadores;
        this.ganador = null;
    }

    /**
     * Regresa la lista de participantes.
     * 
     * @return la lista de participantes.
     */
    public Lista<Gallito> getParticipantes() {
        return participantes;
    }

    /**
     * Regresa la lista de perdedores.
     * 
     * @return la lista de perdedores.
     */
    public Lista<Gallito> getPerdedores() {
        return perdedores;
    }

    /**
     * Regresa el cliente.
     * 
     * @return el cliente.
     */
    public Cuenta getCliente() {
        return cliente;
    }

    public void iniciar() throws TorneoPausa {
        Lista<Gallito> clone = luchadores.clone();
        System.out.println("Va iniciar la batalla " + numBatalla);
        pedirApuesta(cliente);
        Gallito gallo1 = luchadores.delete2(0);
        Gallito gallo2 = luchadores.delete2(0);
        gallo1.setProbabilidad(gallo2);
        gallo2.setProbabilidad(gallo1);
        int random = ThreadLocalRandom.current().nextInt(0, 100);
        if (random <= gallo1.getProbabilidad() * 100) {
            this.ganador = gallo1;
            participantes.add(gallo1);
            perdedores.add(gallo2);
        } else {
            this.ganador = gallo2;
            participantes.add(gallo2);
            perdedores.add(gallo1);
        }
        System.out.println("El ganador de la batalla es: " + ganador);
        int numGanador = clone.indexOf(ganador);
        if (numGanador == cliente.getNumeroApostado()) {
            System.out.println("Tu apuesta fue existosa");
            double apostado = cliente.getApuestaActual();
            double cuota = 1 / ganador.getProbabilidad();
            double ganancia = apostado * cuota;
            cliente.aumentarSaldo(ganancia);
            cliente.setApuestaActual(0);
            cliente.setNumeroApostado(-99);
            cliente.actualizarHistorial("Ganaste " + ganancia + " y tu nuevo saldo es " + cliente.getSaldoBonito() + ".");
        } else {
            if (cliente.getNumeroApostado() > -1) {
                System.out.println("Tu apuesta fue fallida");
                cliente.setApuestaActual(0);
                cliente.setNumeroApostado(-99);
            } else {
                cliente.setApuestaActual(0);
                cliente.setNumeroApostado(-99);
            }
        }

    }

    /**
     * Pide las apuestas de los luchadores.
     * 
     * @param cliente el cliente que realiza las apuestas.
     * @throws TorneoPausa
     */
    private void pedirApuesta(Cuenta cliente) throws TorneoPausa {
        System.out.println("Las opciones son:");
        int i = 0;
        for (Gallito gallito : luchadores) {
            System.out.println("[" + i + "] " + gallito);
            i++;
        }
        System.out.print("\n" + "Ingresa el numero del gallito por el que quieres apostar o escribe \"salir\" para regresar al menu:");
        String respuesta = cliente.escuchar();
        if (respuesta == null) {
            System.out.println("No es un numero valido");
            System.out.println("\n" + "Las apuestas se cerraron" + "\n");
            cliente.setNumeroApostado(-99);
            cliente.setApuestaActual(0);
            return;
        }
        if (respuesta.equals("salir")) {
            cliente.setNumeroApostado(-99);
            cliente.setApuestaActual(0);
            throw new TorneoPausa();
        }
        try {
            int numero = Integer.parseInt(respuesta);
            if (numero >= 0 && numero < luchadores.size()) {
                System.out.print("Ingresa la cantidad de dinero que quieres apostar (entre 0.1 y tu saldo actual "
                        + cliente.getSaldoBonito() + "): ");
                String respuesta2 = cliente.escuchar();
                if (respuesta2 == null) {
                    System.out.println("No es un numero valido");
                    System.out.println("\n" + "Las apuestas se cerraron" + "\n");
                    cliente.setNumeroApostado(-99);
                    cliente.setApuestaActual(0);
                    return;
                }
                double apuesta = Double.parseDouble(respuesta2);
                if (apuesta >= 0.1 && apuesta <= cliente.getSaldo()) {
                    cliente.setApuestaActual(apuesta);
                    cliente.setNumeroApostado(numero);
                    cliente.aumentarSaldo(-apuesta);
                    String nuevo = "$" + String.format("%.2f", apuesta);
                    cliente.actualizarHistorial("Se aposto " + nuevo + " al concursante "
                            + luchadores.buscarIndice(numero).getNombre() + ", ");
                    System.out.println("\n" + "Las apuestas se cerraron" + "\n");
                    return;
                } else {
                    System.out.println("La cantidad de dinero que ingresaste no es valida");
                    System.out.println("\n" + "Las apuestas se cerraron" + "\n");
                    cliente.setNumeroApostado(-99);
                    cliente.setApuestaActual(0);
                    return;
                }
            } else {
                System.out.println("El numero debe estar entre 0 y " + (luchadores.size() - 1));
                System.out.println("\n" + "Las apuestas se cerraron" + "\n");
                cliente.setNumeroApostado(-99);
                cliente.setApuestaActual(0);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("No es un numero valido");
            System.out.println("\n" + "Las apuestas se cerraron" + "\n");
            cliente.setNumeroApostado(-99);
            cliente.setApuestaActual(0);
            return;
        }
    }
}
