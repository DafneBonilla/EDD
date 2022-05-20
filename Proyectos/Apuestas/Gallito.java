package Apuestas;

/**
 * Clase para representar Gallitos.
 * Un gallito tiene nombre, habilidad y probabilidad de ganar.
 */
public class Gallito {

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
     * Recupera un gallito guardado en una linea de texto genereada por el metodo
     * {@link Gallito#serializa}.
     * 
     * @param linea la línea a deserializar.
     * @throws IllegalArgumentException si la línea recibida es nula, vacía o no
     *                                  es una serialización válida de un gallito.
     */
    public Gallito(String linea) {
        // linea = linea.strip();
        linea = linea.trim();
        if (linea == null || linea.equals("")) {
            throw new IllegalArgumentException("Línea Inválida");
        }
        String[] datitos = linea.split("\t");
        if (datitos.length != 6)
            throw new IllegalArgumentException("Línea Inválida");
        this.nombre = datitos[0];
        this.probabilidad = 0;
        this.piel = datitos[2];
        this.ojos = datitos[3];
        this.pico = datitos[4];
        this.cresta = datitos[5];
        try {
            this.habilidad = Integer.parseInt(datitos[1]);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Línea Inválida");
        }
    }

    /**
     * Regresa el gallito serializado en una linea de texto. La linea de
     * texto que este metodo regresa debe ser aceptada por el constructor.
     * 
     * @return la serializacion del gallito en una línea de texto.
     */
    public String serializa() {
        String linea = String.format("%s\t%d\t%s\t%s\t%s\t%s\n",
                nombre, habilidad, piel, ojos, pico, cresta);
        return linea;
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
     * @param probabilidad la nueva probabilidad de ganar del gallito.
     */
    public void setProbabilidad(double probabilidad) {
        this.probabilidad = probabilidad;
    }

    @Override
    public String toString() {
        return String.format("El gallito %s con habilidad (%d)", nombre, habilidad);
    }

    public String toStringBonito() {
        return dibujarGallito(piel, ojos, pico, cresta);
    }

    public String dibujarGallito(String piel, String ojos, String pico, String cresta) {
        String cuadroNegro = "\u001B[30m■\u001B[0m ";
        String gallito = "";
        gallito = nombre + "\n" +
                cuadroNegro + cuadroNegro + cuadroNegro + cresta + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + cresta + cresta + cresta + cuadroNegro + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + piel + cresta + piel + cuadroNegro + cuadroNegro + "\n" +
                cuadroNegro + piel + piel + cresta + piel + piel + cuadroNegro + "\n" +
                piel + ojos + piel + piel + piel + ojos + piel + "\n" +
                piel + piel + pico + pico + pico + piel + piel + "\n" +
                cuadroNegro + piel + piel + pico + piel + piel + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + piel + cresta + piel + cuadroNegro + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + cresta + cresta + cresta + cuadroNegro + cuadroNegro + "\n" + "\n";
        return gallito;
    }
}