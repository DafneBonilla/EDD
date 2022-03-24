package Proyectos.Wizard;

/**
 * Clase para representar cartas. Una carta tiene nombre, color y valor.
 */
public class Carta {
    
    /* Nombre de la carta. */
    private String nombre;
    /* Color de la carta. */
    private String color;
    /* Valor de la carta.*/
    private int valor;

    /**
     * Define el estado inicial de una carta.
     * @param nombre el nombre de la carta.
     * @param color el color de la carta.
     * @param valor el valor de la carta.
     */
    public Carta(String nombre, String color, int valor) {
        this.nombre = nombre;
        this.color = color;
        this.valor = valor;
    }

    /**
     * Regresa el nombre de la carta.
     * @return el nombre de la carta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre de la carta.
     * @param nombre el nuevo nombre el nombre de la carta.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * Regresa el valor de la carta.
     * @return el valor de la carta.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Define el valor de la carta.
     * @param valor el nuevo valor el nombre de la carta.
     */
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    /**
     * Regresa una representación en cadena de la carta.
     * @return una representación en cadena de la carta.
     */
    @Override public String toString() {
        String datitos = String.format("Nombre: %s Color: %s Valor: %d", nombre, color, valor);
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
        if ((this.nombre.equals(cartita.nombre)) == false){
            return false;
        } else if ((this.color.equals(cartita.color)) == false) {
            return false;
        } else if ((this.valor == cartita.valor) == false){
            return false;
        }
        return true;
    }

}
