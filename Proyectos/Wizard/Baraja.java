package Wizard;

import Wizard.Estructuras.Lista;
import java.util.Iterator;
import java.util.Random;

/**
 * Clase para representar barajas. Seran listas de la clase {@link Carta}.
 */
public class Baraja {
    
    /* Lista de cartas. */
    private Lista<Carta> cartitas;
    /* Seed de random */
    private long seed;

    /**
     * Define el estado inical de una baraja.
     */
    public Baraja(){
        cartitas = new Lista<Carta>();
        seed = 0;
    }

    /**
     * Define el estado inical de una baraja, usando
     * una lista de carta. Usado para generar una copia
     * de una baraja.
     * @param cartitas la lista de cartas
     */
    public Baraja(Lista<Carta> cartitas) {
        this.cartitas = cartitas;
        seed = 0;
    }

    /**
     * Define el estado inicial de una baraja,
     * usando una seed.
     * @param seed la semilla de la baraja.
     */
    public Baraja(long seed) {
        cartitas = new Lista<Carta>();
        for (int i = 1; i <= 13; i++) {
            cartitas.add(new Carta(i, 1));
        }
        for (int i = 1; i <= 13; i++) {
            cartitas.add(new Carta(i, 2));
        }
        for (int i = 1; i <= 13; i++) {
            cartitas.add(new Carta(i, 3));
        }
        for (int i = 1; i <= 13; i++) {
            cartitas.add(new Carta(i, 4));
        }
        for (int i = 1; i <= 4; i++) {
            cartitas.add(new Carta(14, 5));
        }
        for (int i = 1; i <= 4; i++) {
            cartitas.add(new Carta(0, 5));
        }
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
     * @param i el índice de la carta.
     */ 
    public Carta sacaCarta(int i) {
        return cartitas.delete2(i);
    }

    /**
     * Checa una carta de la baraja.
     * @param i el índice de la carta.
     */ 
    public Carta checaCarta(int i) {
        return cartitas.buscarIndice(i);
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
            datitos += " [" + contador + "] " + "\t" + iterator.next() + "\n";
            contador++;
        }
	    return datitos.substring(0, datitos.length() - 2);
    }

    /**
     * Regresa una representación en cadena de la baraja ordenada.
     * @return una representación en cadena de la baraja ordenada.
     */
    public String toStringOrden() {
        String datitos = "";
        if (cartitas.isEmpty()) {
            return datitos;
        }
        Lista<Carta> orden = cartitas.mergeSort(
            (card1, card2) -> card1.compareTo(card2)
        );
        int contador = 0;
        Iterator<Carta> iterator = orden.iterator();
        while (iterator.hasNext()) {
            datitos += " [" + contador + "] " + "\t" + iterator.next() + "\n";
            contador++;
        }
	    return datitos.substring(0, datitos.length() - 2);
    }

    /**
     * Regresa una representación en cadena de la baraja ordenada.
     * @return una representación en cadena de la baraja ordenada.
     */
    public void ordenar() {
        Lista<Carta> orden = cartitas.mergeSort(
            (card1, card2) -> card1.compareTo(card2)
        );
        this.cartitas = orden;
    }

    /**
     * Regresa el tamaño de la baraja.
     * @return el tamaño de la bajara.
     */
    public int tamanio() {
        return cartitas.size();
    }

    /**
     * Vuelve la baraja en un arreglo.
     * El arreglo que regresa puede ser aceptado por el método
     * {@link Baraja#volverBaraja}.
     * @return un arreglo con las cartas de la baraja.
     */
    private Carta[] volverArreglo() {
        Carta[] regresar = new Carta[cartitas.size()];
        for (int i=0; i<regresar.length; i++) {
            regresar[i] = sacaCarta(0);
        }
        return regresar;
    }

    /**
     * Agrega todas las cartas de un arreglo a la baraja.
     * El arreglo que recibe debe ser del método
     * {@link Baraja#volverArreglo}.
     */
    private void volverBaraja(Carta[] arreglo) {
        cartitas.empty();
        for (int i=0; i<arreglo.length; i++) {
            agregaCarta(arreglo[i]);
        }
    }

    /**
     * Revuelve las cartas de la baraja.
     */
    public void shuffle() {
        Carta[] arreglo = volverArreglo();
        shuffleAux(arreglo);
        volverBaraja(arreglo);
    }

    /**
     * Auxiliar para devolver las cartas.
     * Algoritmo: Fisher–Yates shuffle
     */
    private void shuffleAux(Carta[] array) {
        int n = array.length;
        Random random = new Random(seed);
        for (int i = 0; i < array.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            Carta randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }
    }

    /**
     * Nos dice si la baraja está vacía.
     * @return <code>true</code> si la baraja es vacia, <code>false</code> en caso contrario.
     */
    public boolean esVacio() {
        return cartitas.isEmpty();
    }

    /**
     * Regresa una copia de la baraja.
     * @return una copia de la baraja.
     */
    public Baraja copia() {
        return new Baraja(cartitas.clone());
    }

    /**
     * Regresa la lista de cartas.
     * @return la lista de cartas.
     */
    public Lista<Carta> getLista() {
        return cartitas;
    }
}
