package Apuestas;

/**
 * Interface para Concursantes.
 */
public interface Concursante {

    /**
     * Regresa al concursante serializado en una linea de texto. La linea de
     * texto que este metodo regresa debe ser aceptada por el constructor del
     * concursante.
     * 
     * @return la serializacion del concursante en una línea de texto.
     */
    public String serializa();

    /**
     * Regresa la probabilidad de ganar del concursante.
     * 
     * @return la probabilidad de ganar del concursante.
     */
    public double getProbabilidad();

    /**
     * Regresa una rempresentacion bonita del concursante.
     * 
     * @return una rempresentacion bonita del concursante.
     */
    public String toStringBonito();

}
