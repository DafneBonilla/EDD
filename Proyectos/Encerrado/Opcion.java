package Encerrado;

/**
 * Clase para representar Opciones.
 * Una opción tiene posición inicial y posición final.
 */
public class Opcion {

    /* Posición inicial de la opción. */
    private int posicionInicial;
    /* Posición final de la opción. */
    private int posicionFinal;

    /**
     * Define el estado inicial de una opción.
     * 
     * @param posicionInicial la posición inicial de la opción.
     * @param posicionFinal   la posición final de la opción.
     */
    public Opcion(int posicionInicial, int posicionFinal) {
        this.posicionInicial = posicionInicial;
        this.posicionFinal = posicionFinal;
    }

    /**
     * Regresa la posición inicial de la opción.
     * 
     * @return la posición inicial de la opción.
     */
    public int getPosicionInicial() {
        return posicionInicial;
    }

    /**
     * Regresa la posición final de la opción.
     * 
     * @return la posición final de la opción.
     */
    public int getPosicionFinal() {
        return posicionFinal;
    }

    /**
     * Regresa una representación en cadena de la posición.
     * 
     * @return una representación en cadena de la posición.
     */
    @Override
    public String toString() {
        return "Mover la ficha de la posicion " + posicionInicial + " a la posición " + posicionFinal;
    }

    /**
     * Regresa la opción inversa de la opción.
     * 
     * @return la opción inversa de la opción.
     */
    public Opcion inversa() {
        return new Opcion(posicionFinal, posicionInicial);
    }
}