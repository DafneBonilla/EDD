package Apuestas;

import java.util.Random;
import Apuestas.Estructuras.Lista;

/**
 * Clase para representar Carreras.
 * Una carrera tiene participantes, ganador y un cliente.
 */
public class Carrera {

    /* Participantes de la carrera. */
    private Lista<Dinosaurio> participantes;
    /* Ganador de la carrera. */
    private Dinosaurio ganador;
    /* Cliente de la carrera. */
    private Cuenta cliente;

    /**
     * Crea una carrera nueva.
     * 
     * @param participantes la lista de los participantes de la carrera.
     */
    public Carrera(Lista<Dinosaurio> participantes, Cuenta cliente) {
        this.participantes = participantes;
        this.ganador = null;
        this.cliente = cliente;
    }

    /**
     * Vuelve a los participantes en un arreglo.
     * El arreglo que regresa puede ser aceptado por el método
     * {@link Carrera#volverLista}.
     * 
     * @return un arreglo con los participantes.
     */
    private Dinosaurio[] volverArreglo() {
        Dinosaurio[] regresar = new Dinosaurio[participantes.size()];
        for (int i = 0; i < regresar.length; i++) {
            regresar[i] = participantes.buscarIndice(i);
        }
        return regresar;
    }

    /**
     * Agrega todas los participantes de un arreglo a la lista de participantes.
     * El arreglo que recibe debe ser del método
     * {@link Carrera#volverArreglo}.
     * 
     * @param arreglo el arreglo con los participantes a agregar.
     */
    private void volverLista(Dinosaurio[] arreglo) {
        participantes.empty();
        for (int i = 0; i < arreglo.length; i++) {
            participantes.add(arreglo[i]);
        }
    }

    /**
     * Revuelve a los participantes de la carrera.
     */
    public void shuffle() {
        Dinosaurio[] arreglo = volverArreglo();
        shuffleAux(arreglo);
        volverLista(arreglo);
    }

    /**
     * Auxiliar para revolver a los participantes de la carrera.
     * Algoritmo: Fisher–Yates shuffle
     * Fuente:
     * https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
     * 
     * @param array el arreglo con las cartas a revolver.
     */
    private void shuffleAux(Dinosaurio[] array) {
        int n = array.length;
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            Dinosaurio randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }
    }

}