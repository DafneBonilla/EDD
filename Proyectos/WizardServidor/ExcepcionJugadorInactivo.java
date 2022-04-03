package WizardServidor;

import java.io.IOException;

/**
 * Clase para excepciones de jugadores inactivos.
 */
public class ExcepcionJugadorInactivo extends IOException {
    
    /**
     * Constructor vacío.
     */
    public ExcepcionJugadorInactivo() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionJugadorInactivo(String mensaje) {
        super(mensaje);
    }

}
