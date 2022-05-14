package Encerrado;

/**
 * Clase para representar Posiciones en el tablero.
 * Una posición tiene lugar y dueño.
 */
public class Posicion {

    /* Lugar de la posición. */
    private int lugar;
    /* Dueño de la posición. */
    private int dueño;
    /* Manera de mostrar. */
    private int version;

    /**
     * Define el estado inicial de una posición.
     * 
     * @param lugar   el lugar de la posición.
     * @param dueño   el dueño de la posición.
     * @param version la manera de mostrar.
     */
    public Posicion(int lugar, int dueño, int version) {
        this.lugar = lugar;
        this.dueño = dueño;
        this.version = version;
    }

    /**
     * Regresa el lugar de la posición.
     * 
     * @return el lugar de la posición.
     */
    public int getLugar() {
        return lugar;
    }

    /**
     * Regresa el dueño de la posición.
     * 
     * @return el dueño de la posición.
     */
    public int getDueño() {
        return dueño;
    }

    /**
     * Define el dueño de la posición.
     * 
     * @param dueño el nuevo dueño de la posición.
     */
    public void setDueño(int dueño) {
        this.dueño = dueño;
    }

    /**
     * Regresa una representacion en cadena de la posición.
     * 
     * @return una representacion en cadena de la posición.
     */
    @Override
    public String toString() {
        if (version == 2) {
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

    /**
     * Vuelve la posición a un círculo.
     * 
     * @param lugar la posición a vuelve a un círculo.
     */
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
     * Nos dice si el objeto recibido es una posición igual al que manda llamar
     * el método.
     * 
     * @param objeto el objeto con el que la posición se comparará.
     * @return <code>true</code> si el objeto recibido es una posición con las
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