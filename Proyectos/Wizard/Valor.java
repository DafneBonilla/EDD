package Wizard;

/**
 * Clase para representar valores de cartas. Un valor tiene número.
 * Número será un número entre 0 y 14.
 */
public class Valor {
    
    /* Número del valor. */
    private int numero;

    /**
     * Define el estado inicial de un valor.
     * @param numero el mérito del valor.
     */
    public Valor(int numero) {
        this.numero = numero;
    }

    /**
     * Regresa el número del valor.
     * @return el número del valor.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Regresa una representación en cadena del valor.
     * @return una representación en cadena del valor.
     */
    @Override public String toString() {
        switch (numero) {
            case 0:
                return "J";
            case 1:
                return "1";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "7";
            case 8:
                return "8";
            case 9:
                return "9";
            case 10:
                return "10";
            case 11:
                return "11";
            case 12:
                return "12";
            case 13:
                return "13";
            case 14:
                return "W";
            default:
                return "No hay";
        }
    }

    /**
     * Nos dice si el objeto recibido es un valor igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el valor se comparará.
     * @return <code>true</code> si el objeto recibido es un color con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Valor))
            return false;
        Valor valorcito = (Valor)objeto;
        if (!(this.numero == valorcito.numero)){
            return false;
        }
        return true;
    }
}