package Apuestas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Clase para representar Cuentas.
 * Una cuenta tiene nombre, contrasena, saldo, apuesta actual, numero apostado y
 * historial de acciones.
 */
public class Cuenta implements java.io.Serializable {

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
        this.historial = "La cuenta se creo.\n";
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
     * Regresa una representacion en cadena del saldo de la cuenta.
     * 
     * @return una representacion en cadena del saldo de la cuenta.
     */
    public String getSaldoBonito() {
        return "$" + String.format("%.2f", saldo);
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
        String nuevo = "$" + String.format("%.2f", cantidad);
        actualizarHistorial("Saldo aumentado en " + nuevo + ", tu saldo es " + getSaldoBonito() + ".");
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
        actual += cadena + "\n";
        this.historial = actual;
    }

    /**
     * Regresa una cadena de lo que escribió el cliente.
     * 
     * @return una cadena de lo que escribió el cliente.
     */
    public String escuchar() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean entrada = false;
        try {
            // System.out.print("Time left to bet: ");
            for (int i = 10; i > 0 && !entrada; i--) {
                // System.out.print(i + "...");
                Thread.sleep(1000);
                entrada = reader.ready();
            }
            if (!entrada) {
                return null;
            }
            return reader.readLine();
        } catch (Exception e) {
            return null;
        }
    }
}