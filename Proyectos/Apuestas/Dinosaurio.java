package Apuestas;

import Apuestas.Estructuras.Lista;

/**
 * Clase para representar Dinoasurios.
 * Un dinosaurio tiene nombre, historial y probabilidad de ganar.
 */
public class Dinosaurio {

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
     * @param nombre el nombre del dinosaurio.
     * @param piel el color de piel del dinosaurio.
     * @param ojos el color de ojos del dinosaurio.
     * @param manchi el color de manchitas del dinosaurio.
     */
    public Dinosaurio(String nombre, String piel, String ojos, String manchitas) {
        this.nombre = nombre;
        this.historial = new Lista<Integer>();
        this.historial.add(0);
        this.historial.add(0);
        this.historial.add(0);
        this.historial.add(0);
        this.historial.add(0);
        this.probabilidad = 0;
        this.piel = piel;
        this.ojos = ojos;
        this.manchitas = manchitas;
    }

    /**
     * Recupera un dinosaurio guardado en una linea de texto genereada por el metodo {@link Dinosaurio#serializa}.
     * 
     * @param linea la línea a deserializar.
     * @throws IllegalArgumentException si la línea recibida es nula, vacía o no
     *         es una serialización válida de un dinosaurio.
     */
    public Dinosaurio(String linea) {
        linea = linea.strip();
        if (linea == null || linea.equals("")) {
            throw new IllegalArgumentException("Línea Inválida");
        }
        String[] datitos = linea.split("\t");
        if (datitos.length != 9)
          throw new IllegalArgumentException("Línea Inválida");
        this.nombre = datitos[0];
        this.probabilidad = 0;
        this.piel = datitos[6];
        this.ojos = datitos[7];
        this.manchitas = datitos[8];
        try {  
            Lista<Integer> historial = new Lista<Integer>();
            historial.add(Integer.parseInt(datitos[1]));
            historial.add(Integer.parseInt(datitos[2]));
            historial.add(Integer.parseInt(datitos[3]));
            historial.add(Integer.parseInt(datitos[4]));
            historial.add(Integer.parseInt(datitos[5]));
        } catch (NumberFormatException nfe) {  
            throw new IllegalArgumentException("Línea Inválida");
        }
    }

    /**
     * Regresa al dinosaurio serializado en una linea de texto. La linea de
     * texto que este metodo regresa debe ser aceptada por el constructor.
     * @return la serializacion del dinosaurio en una línea de texto.
     */
    public String serializa() {
        String linea = String.format("%s\t%s\t%s\t%s\t%s\n",
                                     nombre, historial.toStringEspecial(), piel, ojos, manchitas);
        return linea;
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

    /**
     * Regresa la probabilidad de ganar del dinosaurio.
     * 
     * @return la probabilidad de ganar del dinosaurio.
     */
    public double getProbabilidad() {
        return probabilidad;
    }
    
     /**
     * Define la probabilidad de ganar del dinosaurio.
     * 
     * @param probabilidad la nueva probabilidad de ganar del dinosaurio.
     */
    public void setProbabilidad(double probabilidad){
        this.probabilidad = probabilidad;
    }

    @Override
    public String toString() {
        return String.format("Un dinosaurio %s con historial (%s)", nombre, historial.toStringEspecial());
    }

    public String toStringBonito() {
        return dibujarDinosaurio(piel, ojos, manchitas);
    }

    public String dibujarDinosaurio(String piel, String ojos, String manchitas) {
        String cuadroNegro = "\u001B[30m■\u001B[0m ";
        String dino = "";
        dino = nombre + "\n" +
                  cuadroNegro + piel + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                  piel + piel + ojos + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                  piel + piel + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                  cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                  cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + piel + manchitas + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                  cuadroNegro + cuadroNegro + piel + piel + piel + piel + piel + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" +
                  cuadroNegro + cuadroNegro + piel + piel + piel + manchitas + piel + manchitas + piel + piel + piel + cuadroNegro + cuadroNegro + "\n" +
                  cuadroNegro + cuadroNegro + piel + piel + piel + piel + piel + piel + piel + piel + piel + cuadroNegro + cuadroNegro + "\n" +
                  cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + piel + piel + cuadroNegro + "\n" +
                  cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + cuadroNegro + piel + piel + "\n" +
                  cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + piel + piel + cuadroNegro + cuadroNegro + cuadroNegro + cuadroNegro + "\n" + "\n";
        return dino;
    }
}