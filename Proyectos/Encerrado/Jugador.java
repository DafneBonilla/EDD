package Encerrado;

/**
 * Clase para representar JUgadores.
 * Un jugador tiene nombre.
 */
public class Jugador {

    /* Nombre del jugador. */
    private String nombre;

    /**
     * Define el estado inicial de un jugador.
     * 
     * @param nombre el nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el nombre del jugador.
     * 
     * @return el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

}
