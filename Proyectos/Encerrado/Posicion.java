package Encerrado;

/**
 * Clase para representar Posiciones en el tablero.
 * Una posicion tiene lugar y dueño.
 */
public class Posicion {

    /* Lugar de la posicion. */
    private int lugar;
    /* Dueño de la posicion. */
    private int dueño;

    /**
     * Define el estado inicial de una posicion.
     * 
     * @param lugar el lugar de la posicion.
     * @param dueño el dueño de la posicion.
     */
    public Posicion(int lugar, int dueño) {
        this.lugar = lugar;
        this.dueño = dueño;
    }

    /**
     * Regresa el lugar de la posicion.
     * 
     * @return el lugar de la posicion.
     */
    public int getLugar() {
        return lugar;
    }

    /**
     * Regresa el dueño de la posicion.
     * 
     * @return el dueño de la posicion.
     */
    public int getDueño() {
        return dueño;
    }

    /**
     * Define el dueño de la posicion.
     * 
     * @param dueño el nuevo dueño de la posicion.
     */
    public void setDueño(int dueño) {
        this.dueño = dueño;
    }

    /**
     * Regresa una representacion en cadena de la posicion.
     * 
     * @return una representacion en cadena de la posicion.
     */
    @Override
    public String toString() {
        String datitos = "";
        switch (dueño) {
            case 0:
                datitos += String.format(" \u001B[92m%d\u001B[0m ", lugar);
                break;
            case 1:
                datitos += String.format(" \u001B[91m%d\u001B[0m ", lugar);
                break;
            case 2:
                datitos += String.format(" \u001B[94m%d\u001B[0m ", lugar);
                break;
            default:
                break;
        }
        return datitos;
    }

    /**
     * Nos dice si el objeto recibido es una posicion igual al que manda llamar
     * el método.
     * 
     * @param objeto el objeto con el que la posicion se comparará.
     * @return <code>true</code> si el objeto recibido es una posicion con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object objeto) {
        if (!(objeto instanceof Posicion))
            return false;
        Posicion posicioncita = (Posicion) objeto;
        if ((this.lugar == posicioncita.lugar) == false) {
            return false;
        } else if ((this.dueño == posicioncita.dueño) == false) {
            return false;
        }
        return true;
    }

}
