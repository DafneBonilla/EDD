package Wizard;

/**
 * Clase para representar colores de cartas. Un color tiene mérito.
 * Mérito será un número entre 1 y 5.
 */
public class Color {
    
    /* Mérito del color. */
    private int merito;

    /**
     * Define el estado inicial de un color.
     * @param merito el mérito del color.
     */
    public Color(int merito) {
        this.merito = merito;
    }

    /**
     * Regresa el mérito del color.
     * @return el mérito del color.
     */
    public int getMerito() {
        return merito;
    }

    /**
     * Regresa una representación en cadena del color.
     * @return una representación en cadena del color.
     */
    @Override public String toString() {
        switch (merito) {
            case 1:
                return "\u001B[91mrojo\u001B[0m ";
            case 2:
                return "\u001B[94mazul\u001B[0m ";
            case 3:
                return "\u001B[93mamarillo\u001B[0m ";
            case 4:
                return "\u001B[92mverde\u001B[0m ";
            case 5:
                return "\u001B[97mblanco\u001B[0m ";
            default:
                return "no hay";
        }
    }

    /**
     * Nos dice si el objeto recibido es un color igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el color se comparará.
     * @return <code>true</code> si el objeto recibido es un color con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Color))
            return false;
        Color colorcito = (Color)objeto;
        if (!(this.merito == colorcito.merito)){
            return false;
        }
        return true;
    }
}