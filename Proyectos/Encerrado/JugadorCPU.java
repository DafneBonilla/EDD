package Encerrado;

import java.util.concurrent.ThreadLocalRandom;
import Encerrado.Estructuras.ArbolDecision;
import Encerrado.Estructuras.Lista;

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
        this.arbol = null;
        setHumano(false);
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
    @Override
    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    /**
     * Pide un movimiento al jugador.
     *
     * @param opciones las opciones que puede tomar el jugador.
     * @param tablero  el tablero del juego.
     * @return el movimiento del jugador.
     */
    @Override
    public Opcion pedirMovimiento(Lista<Opcion> opciones, Tablero tablero) {
        if (inteligencia == 0) {
            int random = ThreadLocalRandom.current().nextInt(0, opciones.size());
            return opciones.buscarIndice(random);
        } else {
            this.arbol = new ArbolDecision(tablero, this.getNombre(), this.getNombre());
            return opciones.buscarIndice(0);
        }
    }
}