package Apuestas;

import Apuestas.Estructuras.Lista;

/**
 * Clase para representar Dinoasurios.
 * Un dinosaurio tiene nombre, historial y probabilidad de ganar.
 */
public class Dinosaurio implements java.io.Serializable {

    /* El nombre del dinosaurio. */
    private String nombre;
    /* El historial del dinosaurio. */
    private Lista<Integer> historial;
    /* La probabilidad de ganar del dinosaurio. */
    private double probabilidad;
    /* El color de piel del dinosaurio. */
    private String piel;
    /* El color de ojos del dinosaurio. */
    private String ojos;
    /* El color de manchitas del dinosaurio. */
    private String manchitas;

    /**
     * Crea un dinosaurio nuevo.
     * 
     * @param nombre    el nombre del dinosaurio.
     * @param piel      el color de piel del dinosaurio.
     * @param ojos      el color de ojos del dinosaurio.
     * @param manchitas el color de manchitas del dinosaurio.
     */
    public Dinosaurio(String nombre, String piel, String ojos, String manchitas, int carrera1, int carrera2,
            int carrera3, int carrera4, int carrera5) {
        this.nombre = nombre;
        this.historial = new Lista<Integer>();
        this.historial.add(carrera1);
        this.historial.add(carrera2);
        this.historial.add(carrera3);
        this.historial.add(carrera4);
        this.historial.add(carrera5);
        this.probabilidad = 0;
        this.piel = piel;
        this.ojos = ojos;
        this.manchitas = manchitas;
    }

    /**
     * Regresa el nombre del dinosaurio.
     * 
     * @return el nombre del dinosaurio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Regresa la habilidad del dinosaurio.
     * 
     * @return el nombre del dinosaurio.
     */
    public Lista<Integer> getHistorial() {
        return historial;
    }

    public void actualizarHistorial(int posicion) {
        historial.delete2(0);
        historial.add(posicion);
    }

    /**
     * Regresa la probabilidad de ganar del dinosaurio.
     * 
     * @return la probabilidad de ganar del dinosaurio.
     */
    public double getProbabilidad() {
        return probabilidad;
    }

    /**
     * Actualizar la probabilidad de ganar del dinosaurio.
     * p_C= [s * (n + 1) - Sumatoria(desde i = 1 hasta s) h_i] / s * [n * (n + 1)/2]
     */
    public void actualizarProba() {
        int posiciones = 0;
        for (Integer i : historial) {
            posiciones += i;
        }
        double parte1 = 5 * (6 + 1) - posiciones;
        double parte3 = 6 * (6 + 1);
        double parte4 = parte3 / 2;
        double parte5 = 5 * parte4;
        double total = parte1 / parte5;
        this.probabilidad = total;
    }

    /**
     * Regresa una representación en cadena del dinosaurio.
     * 
     * @return una representación en cadena del dinosaurio.
     */
    @Override
    public String toString() {
        String algo = "";
        for (Integer i : historial) {
            algo += i + " ";
        }
        algo = algo.trim();
        return String.format("Un dinosaurio %s con historial (%s)", nombre, algo);
    }

    /**
     * Regresa una representacion bonita del dinosaurio.
     * 
     * @return una representacion bonita del dinosaurio.
     */
    public String toStringBonito() {
        return dibujarDinosaurio(piel, ojos, manchitas);
    }

    /**
     * Dibuja un dinosaurio.
     * 
     * @param piel    el color de piel del dinosaurio.
     * @param ojos    el color de ojos del dinosaurio.
     * @param manchas el color de las manchitas del dinosaurio.
     * @return el dibujo del dinosaurio.
     */
    public String dibujarDinosaurio(String piel, String ojos, String manchitas) {
        String cuadroNegro = "\u001B[30m■\u001B[0m ";
        String dino = "";
        dino = nombre + "\n" + "\n" +
                cuadroNegro + piel + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro
                + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                piel + piel + ojos + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro
                + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                piel + piel + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro
                + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro
                + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + piel + manchitas + piel + cuadroNegro
                + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + piel + piel + piel + piel + piel + piel + piel + cuadroNegro + cuadroNegro
                + cuadroNegro + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + piel + piel + piel + manchitas + piel + manchitas + piel + piel + piel
                + cuadroNegro + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + piel + piel + piel + piel + piel + piel + piel + piel + piel + cuadroNegro
                + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + piel + piel
                + cuadroNegro + piel + piel + cuadroNegro + "\n" +
                cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + piel + piel
                + cuadroNegro + cuadroNegro + piel + piel + "\n" +
                cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + piel + piel
                + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" + "\n";
        return dino;
    }
}