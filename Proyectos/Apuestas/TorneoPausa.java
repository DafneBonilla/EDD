package Apuestas;

/**
 * Clase para representar una excepcion del torneo.
 */
public class TorneoPausa extends Exception {

    /**
     * Constructor vacio.
     */
    public TorneoPausa() {
    }

    /**
     * Constructor que recibe un mensaje.
     * 
     * @param mensaje un mensaje.
     */
    public TorneoPausa(String mensaje) {
        super(mensaje);
    }
}