package Apuestas;

import Apuestas.Estructuras.Lista;

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
        this.historial = "";
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
        return saldo;
    }

    /**
     * Define el saldo de la cuenta.
     * 
     * @param saldo el nuevo saldo de la cuenta.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
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
     * Define el historial de la cuenta.
     * 
     * @param historial el nuevo historial de la cuenta.
     */
    public void setHistorial(String historial) {
        this.historial = historial;
    }

    /**
     * Pide al usuario que de por quien apostara.
     * 
     * @param opciones las opciones disponibles.
     */
    public void pedirApuesta1(Lista<Concursante> opciones) {
    }

    /**
     * Pide al usuario cuanto va a apostar.
     */
    public void pedirApuesta2() {
    }

    /**
     * Pedir al usuario un entero entre unos limites.
     * 
     * @param opciones las opciones disponibles.
     * @param limite1  el limite inferior.
     * @param limite2  el limite superior.
     */
    private int daNumero1(Lista<Concursante> opciones, int limite1, int limite2) {
        return 0;
    }

    /**
     * Pedir al usuario un racional entre 0.1 y su saldo.
     */
    private double daNumero2() {
        return 0.0;
    }

}