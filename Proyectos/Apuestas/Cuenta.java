package Apuestas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase para representar Cuentas.
 * Una cuenta tiene nombre, contrasena, saldo, apuesta actual, numero apostado,
 * ganadas consecutivas y historial de acciones.
 */
public class Cuenta {

    /* Nombre de la cuenta. */
    private String nombre;
    /* Contrasena de la cuenta. */
    private String contrasena;
    /* Saldo de la cuenta. */
    private double saldo;
    /* Apuesta de la cuenta. */
    private double apuestaActual;
    /* Numero apostado de la cuenta. */
    private int numeroApostado;
    /* Apuestas ganadas consecutivamente de la cuenta. */
    private int ganadasConsecutivas;
    /* Historial de acciones de la cuenta. */
    private String historial;

    /**
     * Crea una cuenta nueva.
     * 
     * @param nombre     el nombre de la cuenta.
     * @param contrasena la constrasena de la cuenta.
     */
    public Cuenta(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.saldo = 0.0;
        this.apuestaActual = 0.0;
        this.numeroApostado = -99;
        this.ganadasConsecutivas = 0;
        this.historial = "La cuenta se creo.";
    }

    /**
     * Recupera una cuenta guardada en una linea de texto genereada por el metodo
     * {@link Cuenta#serializa}.
     * 
     * @param linea la linea a deserializar.
     * @throws IllegalArgumentException si la linea recibida es nula, vacia o no
     *                                  es una serializacion valida de una cuenta.
     */
    public Cuenta(String linea) {
        // linea = linea.strip();
        linea = linea.trim();
        if (linea == null || linea.equals("")) {
            throw new IllegalArgumentException("Línea Inválida");
        }
        String[] datitos = linea.split("\t");
        if (datitos.length != 7)
            throw new IllegalArgumentException("Línea Inválida");
        this.nombre = datitos[0];
        this.contrasena = datitos[1];
        this.historial = datitos[6];
        try {
            this.saldo = Double.parseDouble(datitos[2]);
            this.apuestaActual = Double.parseDouble(datitos[3]);
            this.numeroApostado = Integer.parseInt(datitos[4]);
            this.ganadasConsecutivas = Integer.parseInt(datitos[5]);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Línea Inválida");
        }
    }

    /**
     * Regresa la cuenta serializada en una linea de texto. La linea de
     * texto que este metodo regresa debe ser aceptada por el constructor.
     * 
     * @return la serializacion de la cuenta en una linea de texto.
     */
    public String serializa() {
        String linea = String.format("%s\t%s\t%.2f\t%.2f\t%d\t%d\t%s\n",
                nombre, contrasena, saldo, apuestaActual, numeroApostado, ganadasConsecutivas, historial);
        return linea;
    }

    /**
     * Regresa el nombre de la cuenta.
     * 
     * @return el nombre de la cuenta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre de la cuenta.
     * 
     * @param nombre el nuevo nombre de la cuenta.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa la contrasena de la cuenta.
     * 
     * @return la contrasena de la cuenta.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Define la contrasena de la cuenta.
     * 
     * @param contraseña la nueva contrasena de la cuenta.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Regresa el saldo de la cuenta.
     * 
     * @return el saldo de la cuenta.
     */
    public double getSaldo() {
        String respuesta = String.format("%.2f", saldo);
        return Double.parseDouble(respuesta);
    }
    
    /**
     * Método que incrementa el saldo de una cuenta. 
     * 
     * @param cantidad la cantidad a sumar al saldo.
     */
    public void aumentarSaldo(double cantidad) {
        double actual = saldo;
        actual += cantidad;
        this.saldo = actual;
        actualizarHistorial("Saldo aumentado en " + cantidad + ", tu saldo es " + saldo + ".");
    }

    /**
     * Regresa la apuesta actual de la cuenta.
     * 
     * @return la apuesta actual de la cuenta.
     */
    public double getApuestaActual() {
        return apuestaActual;
    }

    /**
     * Define la apuesta actual de la cuenta.
     * 
     * @param apuestaActual la nueva apuesta actual de la cuenta.
     */
    public void setApuestaActual(double apuestaActual) {
        this.apuestaActual = apuestaActual;
    }

    /**
     * Regresa el nmero apostado de la cuenta.
     * 
     * @return el número apostado de la cuenta.
     */
    public int getNumeroApostado() {
        return numeroApostado;
    }

    /**
     * Define el numero apostado de la cuenta.
     * 
     * @param numeroApostado el nuevo numero apostado de la cuenta.
     */
    public void setNumeroApostado(int numeroApostado) {
        this.numeroApostado = numeroApostado;
    }

    /**
     * Regresa el numero de veces que la cuenta ha ganado consecutivamente.
     * 
     * @return el numero de veces que la cuenta ha ganado consecutivamente.
     */
    public int getGanadasConsecutivas() {
        return ganadasConsecutivas;
    }

    /**
     * Define el numero de veces que la cuenta ha ganado consecutivamente.
     * 
     * @param ganadasConsecutivas el nuevo numero de veces que la cuenta ha ganado
     *                            consecutivamente.
     */
    public void setGanadasConsecutivas(int ganadasConsecutivas) {
        this.ganadasConsecutivas = ganadasConsecutivas;
    }

    /**
     * Regresa el historial de la cuenta.
     * 
     * @return el historial de la cuenta.
     */
    public String getHistorial() {
        return historial;
    }

    /**
     * Actualiza el historial de la cuenta.
     * 
     * @param cadena lo nuevo del historial.
     */
    public void actualizarHistorial(String cadena) {
        String actual = historial;
        actual += " " + cadena;
        this.historial = actual;
    }

    /**
     * Regresa una cadena de lo que escribió el cliente.
     * 
     * @return una cadena de lo que escribió el cliente.
     * @throws IOException si hay un error de entrada/salida.
     * @throws InterruptedException si el hilo es interrumpido.
     */
    public String escuchar() throws InterruptedException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean entrada = false;
        //System.out.print("Time left to bet:  ");
        for (int i = 10; i > 0 && !entrada; i--) {
            //System.out.print(i + "...");
            Thread.sleep(1000);
            entrada = reader.ready();
        }
        if (!entrada) {
            return null;
        }
        return reader.readLine();
    }
}