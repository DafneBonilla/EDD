package Encerrado;

import Encerrado.Estructuras.ArbolDecision;

/**
 * Clase para representar Jugadores artificiales.
 * Un jugadorCPU tiene nombre, inteligencia y un árbol de decisión.
 */
public class JugadorCPU extends Jugador {

    /* Inteligencia del jugador. */
    private int inteligencia;
    /* Árbol de decisión del jugador. */
    private ArbolDecision arbol;

    /**
     * Define el estado inicial de un jugadorCPU.
     * 
     * @param nombre       el nombre del jugador.
     * @param inteligencia la inteligencia del jugador.
     */
    public JugadorCPU(int nombre, int inteligencia) {
        super(nombre);
        this.inteligencia = inteligencia;
        this.arbol = new ArbolDecision();
    }

    /**
     * Regresa la inteligencia del jugador.
     * 
     * @return la inteligencia del jugador.
     */
    public int getInteligencia() {
        return inteligencia;
    }

    /**
     * Modifica la inteligencia del jugador.
     * 
     * @param inteligencia la nueva inteligencia del jugador.
     */
    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

}
