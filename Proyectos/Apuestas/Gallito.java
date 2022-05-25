package Apuestas;

/**
 * Clase para representar Gallitos.
 * Un gallito tiene nombre, habilidad y probabilidad de ganar.
 */
public class Gallito implements java.io.Serializable {

    /* El nombre del gallito. */
    private String nombre;
    /* La habilidad del gallito. */
    private int habilidad;
    /* La probabilidad de ganar del gallito. */
    private double probabilidad;
    /* El color de piel del gallito. */
    private String piel;
    /* El color de ojos del gallito. */
    private String ojos;
    /* El color de pico del gallito. */
    private String pico;
    /* El color de cresta del gallito. */
    private String cresta;

    /**
     * Crea un gallito nuevo.
     * 
     * @param nombre    el nombre del gallito.
     * @param habilidad la habilidad del gallito.
     * @param piel      el color de piel del gallito.
     * @param ojos      el color de ojos del gallito.
     * @param pico      el color de pico del gallito.
     * @param cresta    el color de cresta del gallito.
     */
    public Gallito(String nombre, int habilidad, String piel, String ojos, String pico, String cresta) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.probabilidad = 0;
        this.piel = piel;
        this.ojos = ojos;
        this.pico = pico;
        this.cresta = cresta;
    }

    /**
     * Regresa el nombre del gallito.
     * 
     * @return el nombre del gallito.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Regresa la habilidad del gallito.
     * 
     * @return el nombre del gallito.
     */
    public int getHabilidad() {
        return habilidad;
    }

    /**
     * Regresa la probabilidad de ganar del gallito.
     * 
     * @return la probabilidad de ganar del gallito.
     */
    public double getProbabilidad() {
        return probabilidad;
    }

    /**
     * Define la probabilidad de ganar del gallito.
     * 
     * @param rival el contrincante del gallito.
     */
    public void setProbabilidad(Gallito rival) {
        double aux = this.habilidad + rival.habilidad;
        double numero = this.habilidad / aux;
        this.probabilidad = numero;
    }

    /**
     * Regresa una representacion en cadena del galllito.
     * 
     * @return una representacion en cadena del gallito.
     */
    @Override
    public String toString() {
        return String.format("El gallito %s con habilidad (%d)", nombre, habilidad);
    }

    /**
     * Regresa una rempresentacion bonita del gallito.
     * 
     * @return una rempresentacion bonita del gallito.
     */
    public String toStringBonito() {
        return dibujarGallito(piel, ojos, pico, cresta);
    }

    /**
     * Dibuja un gallito.
     * 
     * @param piel   el color de piel del gallito.
     * @param ojos   el color de ojos del gallito.
     * @param pico   el color de pico del gallito.
     * @param cresta el color de cresta del gallito.
     * @return el dibujo del gallito.
     */
    public String dibujarGallito(String piel, String ojos, String pico, String cresta) {
        String cuadroNegro = "\u001B[30m■\u001B[0m ";
        String gallito = "";
        gallito = nombre + "\n" + "\n" +
                cuadroNegro + cuadroNegro + cresta + cresta + cresta + cuadroNegro + cuadroNegro + cuadroNegro
                + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + cresta + cresta + cresta + cuadroNegro + cuadroNegro + cuadroNegro
                + cuadroNegro + "\n" +
                cuadroNegro + piel + piel + piel + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                piel + piel + piel + piel + piel + piel + piel + cuadroNegro + cuadroNegro + "\n" +
                piel + ojos + piel + piel + ojos + piel + piel + cuadroNegro + cuadroNegro + "\n" +
                piel + ojos + piel + piel + ojos + piel + piel + cuadroNegro + piel + "\n" +
                piel + pico + pico + piel + piel + piel + piel + piel + piel + "\n" +
                piel + piel + cresta + piel + piel + piel + piel + piel + piel + "\n" +
                piel + piel + cresta + piel + piel + piel + piel + piel + piel + "\n" +
                cuadroNegro + piel + piel + piel + piel + piel + piel + piel + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + pico + cuadroNegro + cuadroNegro + pico + cuadroNegro + cuadroNegro
                + cuadroNegro + "\n" + "\n";
        return gallito;
    }
}