package Apuestas;

public class TorneoPausa extends Exception {

    /**
     * Constructor vacío.
     */
    public TorneoPausa() {
    }

    /**
     * Constructor que recibe un mensaje para el usuario.
     * 
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public TorneoPausa(String mensaje) {
        super(mensaje);
    }
}