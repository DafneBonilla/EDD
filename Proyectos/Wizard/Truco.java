package Wizard;

import Wizard.Estructuras.Lista;

/**
 * Clase para representar una truco.
 */
public class Truco {

    /* Lista de jugadores. */
    private Lista<Jugador> jugadores;
    /* Historial del juego. */
    private String log;

    //palo lider

    /**
     * Comienza el truco.
     */
    public void iniciar() {

    }
    
    /**
     * Imprime un mensaje al usuario, ademes el mensaje lo
     * agrega a log.
     * @param mensaje el mensaje a imprimir y agregar.
     */
    private void enviarMensaje(String mensaje) {
        System.out.println(mensaje);
        log += mensaje;
    }
    
}
