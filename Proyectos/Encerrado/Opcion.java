package Encerrado;

/**
 * Clase para representar Opciones.
 * Una opcion tiene posicion inicial y posicion final.
 */
public class Opcion {

    /* Posicion inicial de la opcion. */
    private int posicionInicial;
    /* Posicion final de la opcion. */
    private int posicionFinal;

    /**
     * Define el estado inicial de una opcion.
     * 
     * @param posicionInicial la posicion inicial de la opcion.
     * @param posicionFinal   la posicion final de la opcion.
     */
    public Opcion(int posicionInicial, int posicionFinal) {
        this.posicionInicial = posicionInicial;
        this.posicionFinal = posicionFinal;
    }

    /**
     * Regresa la posicion inicial de la opcion.
     * 
     * @return la posicion inicial de la opcion.
     */
    public int getPosicionInicial() {
        return posicionInicial;
    }

    /**
     * Regresa la posicion final de la opcion.
     * 
     * @return la posicion final de la opcion.
     */
    public int getPosicionFinal() {
        return posicionFinal;
    }

    /**
     * Regresa una representacion en cadena de la posicion.
     * 
     * @return una representacion en cadena de la posicion.
     */
    @Override
    public String toString() {
        return "Mover la ficha de la posicion " + posicionInicial + " a la posición " + posicionFinal;
    }

    /**
     * Regresa la opcion inversa de la opcion.
     * 
     * @return la opcion inversa de la opcion.
     */
    public Opcion inversa() {
        return new Opcion(posicionFinal, posicionInicial);
    }

}
