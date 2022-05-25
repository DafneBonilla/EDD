package Apuestas;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import Apuestas.Estructuras.Lista;

/**
 * Clase para representar Carreras.
 * Una carrera tiene participantes, ganador y un cliente.
 */
public class Carrera {

    /* Participantes de la carrera. */
    private Lista<Dinosaurio> participantes;
    /* Ganador de la carrera. */
    private Dinosaurio ganador;
    /* Cliente de la carrera. */
    private Cuenta cliente;

    /**
     * Crea una carrera nueva.
     * 
     * @param participantes la lista de los participantes de la carrera.
     */
    public Carrera(Lista<Dinosaurio> participantes, Cuenta cliente) {
        this.participantes = participantes;
        this.ganador = null;
        this.cliente = cliente;
    }

    /**
     * Vuelve a los participantes en un arreglo.
     * El arreglo que regresa puede ser aceptado por el método
     * {@link Carrera#volverLista}.
     * 
     * @return un arreglo con los participantes.
     */
    private Dinosaurio[] volverArreglo() {
        Dinosaurio[] regresar = new Dinosaurio[participantes.size()];
        for (int i = 0; i < regresar.length; i++) {
            regresar[i] = participantes.buscarIndice(i);
        }
        return regresar;
    }

    /**
     * Agrega todas los participantes de un arreglo a la lista de participantes.
     * El arreglo que recibe debe ser del método
     * {@link Carrera#volverArreglo}.
     * 
     * @param arreglo el arreglo con los participantes a agregar.
     */
    private void volverLista(Dinosaurio[] arreglo) {
        participantes.empty();
        for (int i = 0; i < arreglo.length; i++) {
            participantes.add(arreglo[i]);
        }
    }

    /**
     * Revuelve a los participantes de la carrera.
     */
    public void shuffle() {
        Dinosaurio[] arreglo = volverArreglo();
        shuffleAux(arreglo);
        volverLista(arreglo);
    }

    /**
     * Revuelve a un arreglo.
     */
    private void shuffleAux(Integer[] array) {
        int n = array.length;
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            Integer randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }
    }

    /**
     * Auxiliar para revolver a los participantes de la carrera.
     * Algoritmo: Fisher–Yates shuffle
     * Fuente:
     * https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
     * 
     * @param array el arreglo con las cartas a revolver.
     */
    private void shuffleAux(Dinosaurio[] array) {
        int n = array.length;
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            Dinosaurio randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }
    }

    /**
     * Inicia la carrera.
     */
    public void iniciar() {
        shuffle();
        for (Dinosaurio dino : participantes) {
            dino.actualizarProba();
        }
        int random = ThreadLocalRandom.current().nextInt(0, 100);
        double casoBase = 0.0;
        for (Dinosaurio dino : participantes) {
            double proba = dino.getProbabilidad() * 100;
            casoBase += proba;
            if (random <= casoBase) {
                this.ganador = dino;
                break;
            }
        }
        pedirApuesta(cliente);
        System.out.println("El ganador es: " + ganador.toStringBonito());
        int numGanador = participantes.indexOf(ganador);
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
        Dinosaurio ganar = participantes.delete2(numGanador);
        ganar.actualizarHistorial(1);
        Integer[] resto = new Integer[participantes.size()];
        for (int i = 2; i < 17; i++) {
            resto[i - 2] = i;
        }
        shuffleAux(resto);
        int i = 0;
        for (Dinosaurio dino : participantes) {
            dino.actualizarHistorial(resto[i]);
            i++;
        }
        participantes.add(ganar);
    }

    /**
     * Pide las apuestas de los participantes.
     * 
     * @param cliente el cliente que realiza las apuestas.
     */
    private void pedirApuesta(Cuenta cliente) {
        System.out.println("Las opciones son:");
        int i = 0;
        for (Dinosaurio dino : participantes) {
            System.out.println("[" + i + "] " + dino);
            i++;
        }
        System.out.print("\n" + "Ingresa el numero del dinosaurio por el que quieres apostar: ");
        String respuesta = cliente.escuchar();
        if (respuesta == null) {
            System.out.println("No es un numero valido");
            System.out.println("\n" + "Las apuestas se cerraron" + "\n");
            cliente.setNumeroApostado(-99);
            cliente.setApuestaActual(0);
            return;
        }
        try {
            int numero = Integer.parseInt(respuesta);
            if (numero >= 0 && numero < participantes.size()) {
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
                    cliente.actualizarHistorial("Se aposto " + apuesta + " al concursante "
                            + participantes.buscarIndice(numero).getNombre() + ".");
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
                System.out.println("El numero debe estar entre 0 y " + (participantes.size() - 1));
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

    public Lista<Dinosaurio> getPartisipantes() {
        return participantes;
    }

    public Cuenta getCliente() {
        return cliente;
    }
}