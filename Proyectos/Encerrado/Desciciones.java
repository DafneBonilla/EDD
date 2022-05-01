package Encerrado;

/**
 * Clase para representar descisiones.
 * Una descision tiene un tablero y una opcion.
 */
public class Desciciones {

    /* Tablero de la descicion. */
    private Tablero tablero;
    /* Opcion de la descicion. */
    private Opcion opcion;

    /**
     * Define el estado inicial de una descicion.
     * 
     * @param tablero el tablero de la descicion.
     * @param opcion  la opcion de la descicion.
     */
    public Desciciones(Tablero tablero, Opcion opcion) {
        this.tablero = tablero;
        this.opcion = opcion;
    }

}
