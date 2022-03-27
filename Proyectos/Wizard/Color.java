package Wizard;

/**
 * Clase para representar colores de cartas. Un color tiene merito.
 * Merito sera un numero entre 1 y 5.
 */
public class Color {
    
    /* Merito del color. */
    private int merito;

    /**
     * Define el estado inicial de un color.
     * @param merito el merito del color.
     */
    public Color(int merito) {
        this.merito = merito;
    }

    /**
     * Regresa el merito del color.
     * @return el merito del color.
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
                return "rojo";
            case 2:
                return "azul";
            case 3:
                return "amarillo";
            case 4:
                return "verde";
            case 5:
                return "blanco";
            default:
                return "no existe";
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
