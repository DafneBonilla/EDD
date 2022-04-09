package WizardServidor;

import java.io.IOException;

/**
 * Clase para excepciones de jugadores inactivos.
 */
public class JugadorInactivo extends IOException {

    /**
     * Constructor vacío.
     */
    public JugadorInactivo() {
    }

    /**
     * Constructor que recibe un mensaje para el usuario.
     * 
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public JugadorInactivo(String mensaje) {
        super(mensaje);
    }

}
