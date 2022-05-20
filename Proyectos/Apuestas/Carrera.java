package Apuestas;

import Apuestas.Estructuras.Lista;

/**
 * Clase para representar Carreras.
 * Una carrera tiene participantes y ganador.
 */
public class Carrera {

    /* Participantes de la carrera. */
    private Lista<Dinosaurio> participantes;
    /* Ganador de la carrera. */
    private Dinosaurio ganador;

    /**
     * Crea una carrera nueva.
     * 
     * @param participantes la lista de los participantes de la carrera.
     */
    public Carrera(Lista<Dinosaurio> participantes) {
        this.participantes = participantes;
        this.ganador = null;
    }

}