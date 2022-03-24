package Proyectos.Wizard;

/**
 * Clase para representar cartas. Una carta tiene valor y color.
 */
public class Carta {
    
    /* Valor de la carta. */
    private String valor;
    /* Color de la carta. */
    private String color;

    /**
     * Define el estado inicial de una carta.
     * @param valor el valor de la carta.
     * @param color el color de la carta.
     */
    public Carta(String valor, String color) {
        this.valor = valor;
        this.color = color;
    }

    /**
     * Regresa el valor de la carta.
     * @return el valor de la carta.
     */
    public String getValor() {
        return valor;
    }

    /**
     * Define el valor de la carta.
     * @param valor el nuevo valor de la carta.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Regresa el color de la carta.
     * @return el color de la carta.
     */
    public String getColor() {
        return color;
    }

    /**
     * Define el color de la carta.
     * @param color el nuevo color el nombre de la carta.
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    /**
     * Regresa una representación en cadena de la carta.
     * @return una representación en cadena de la carta.
     */
    @Override public String toString() {
        String datitos = String.format("Un %s de color %s", valor, color);
	    return datitos;
    }

    /**
     * Nos dice si el objeto recibido es una carta igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que la carta se comparará.
     * @return <code>true</code> si el objeto recibido es una cartas con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Carta))
            return false;
        Carta cartita = (Carta)objeto;
        if ((this.valor.equals(cartita.valor)) == false){
            return false;
        } else if ((this.color.equals(cartita.color)) == false) {
            return false;
        }
        return true;
    }

    /*
    Metodos auxiliares para caza
    */
    private boolean cazaValor(Object buscar){
        if (!(buscar instanceof String)) {
            return false;
        }
        if ((String)buscar == "") {
            return false;
        }
        if (valor.contains((String)buscar)) {
            return true;
        }
        return false;
    }
    private boolean cazaColor(Object buscar){
        if (!(buscar instanceof String)) {
            return false;
        }
        if ((String)buscar == "") {
            return false;
        }
        if (color.contains((String)buscar)) {
            return true;
        }
        return false;
    }

    /**
     * Nos dice si la carta caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param buscar el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoCarta#VALOR} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del valor de la carta.</li>
     *           <li><code>campo</code> es {@link CampoCarta#COLOR} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del color de la carta.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    public boolean caza(CampoCarta campo, Object buscar) {
        if (campo == null) {
            throw new IllegalArgumentException("el campo es null");
        }
        switch (campo) {
            case VALOR:
                return cazaValor(buscar);
            case COLOR:
                return cazaColor(buscar);
            default:
                throw new IllegalArgumentException("el campo no es instancia de CampoCarta");
        }
    }
}
