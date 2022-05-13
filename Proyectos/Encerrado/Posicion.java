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
                datitos += String.format(" \u001B[92m%s\u001B[0m ", volverCirculo(lugar));
                break;
            case 1:
                datitos += String.format(" \u001B[91m%s\u001B[0m ", volverCirculo(lugar));
                break;
            case 2:
                datitos += String.format(" \u001B[94m%s\u001B[0m ", volverCirculo(lugar));
                break;
            default:
                break;
        }
        return datitos;
    }

    private String volverCirculo(int lugar) {
        switch (lugar) {
            case 1:
                return "\u2460";
            case 2:
                return "\u2461";
            case 3:
                return "\u2462";
            case 4:
                return "\u2463";
            case 5:
                return "\u2464";
            default:
                return "";
        }
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
