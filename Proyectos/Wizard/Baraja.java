package Wizard;

import java.lang.Math;
import java.util.Iterator;
import java.util.Random;

import Wizard.Estructuras.Lista;


/**
 * Clase para representar barajas. Seran listas de la clase {@link Carta}.
 */
public class Baraja {
    
    /* Lista de cartas. */
    private Lista<Carta> cartitas;

    /**
     * Constructor único.
     */
    public Baraja() {
        cartitas = new Lista<Carta>();
    }

    /**
     * Agrega una carta a la baraja.
     * @param nueva la carta a agregar.
     */ 
    public void agregaCarta(Carta nueva) {
        cartitas.add(nueva);
    }
    
     /**
     * Saca una carta de la baraja.
     * @param i el indice de la carta.
     */ 
    public Carta sacaCarta(int i) {
        return cartitas.delete2(i);
    }

    /**
     * Regresa una representación en cadena de la baraja.
     * @return una representación en cadena de la baraja.
     */
    @Override public String toString() {
        String datitos = "";
        if (cartitas.isEmpty()) {
            return datitos;
        }
        int contador = 0;
        Iterator<Carta> iterator = cartitas.iterator();
        while (iterator.hasNext()) {
            datitos += "[" + contador + "] " + iterator.next() + "\n";
            contador++;
        }
	    return datitos;
    }

    public int tamanio() {
        return cartitas.size();
    }

    private Carta[] volverArreglo() {
        Carta[] regresar = new Carta[cartitas.size()];
        for (int i=0; i<regresar.length; i++) {
            regresar[i] = sacaCarta(0);
        }
        return regresar;
    }

    private void volverBaraja(Carta[] arreglo) {
        cartitas.empty();
        for (int i=0; i<arreglo.length; i++) {
            agregaCarta(arreglo[i]);
        }
    }

    public void shuffle() {
        Carta[] arreglo = volverArreglo();
        shuffleAux(arreglo);
        volverBaraja(arreglo);
    }

    private void shuffleAux(Carta[] array) {
        int n = array.length;
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            Carta randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }
    }

}
