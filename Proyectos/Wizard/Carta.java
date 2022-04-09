package Wizard;

/**
 * Clase para representar cartas. Una carta tiene valor y color.
 */
public class Carta implements Comparable<Carta> {

    /* Valor de la carta. */
    private Valor valor;
    /* Color de la carta. */
    private Color color;

    /**
     * Define el estado inicial de una carta.
     * 
     * @param valor el valor de la carta.
     * @param color el color de la carta.
     */
    public Carta(int valor, int color) {
        this.valor = new Valor(valor);
        this.color = new Color(color);
    }

    /**
     * Regresa el valor de la carta.
     * 
     * @return el valor de la carta.
     */
    public Valor getValor() {
        return valor;
    }

    /**
     * Define el valor de la carta.
     * 
     * @param valor el nuevo valor de la carta.
     */
    public void setValor(Valor valor) {
        this.valor = valor;
    }

    /**
     * Regresa el color de la carta.
     * 
     * @return el color de la carta.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Define el color de la carta.
     * 
     * @param color el nuevo color de la carta.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Regresa una representación en cadena de la carta.
     * 
     * @return una representación en cadena de la carta.
     */
    @Override
    public String toString() {
        String datitos = "";
        switch (color.getMerito()) {
            case 1:
                datitos += String.format("Un \u001B[91m%s\u001B[0m de color \u001B[91m%s\u001B[0m ", valor, color);
                break;
            case 2:
                datitos += String.format("Un \u001B[94m%s\u001B[0m de color \u001B[94m%s\u001B[0m ", valor, color);
                break;
            case 3:
                datitos += String.format("Un \u001B[93m%s\u001B[0m de color \u001B[93m%s\u001B[0m ", valor, color);
                break;
            case 4:
                datitos += String.format("Un \u001B[92m%s\u001B[0m de color \u001B[92m%s\u001B[0m ", valor, color);
                break;
            case 5:
                datitos += String.format("Un \u001B[97m%s\u001B[0m de color \u001B[97m%s\u001B[0m ", valor, color);
                break;
            default:
                datitos += String.format("Un %s de color %s", valor, color);
                break;
        }
        return datitos;
    }

    /**
     * Nos dice si el objeto recibido es una carta igual al que manda llamar
     * el método.
     * 
     * @param objeto el objeto con el que la carta se comparará.
     * @return <code>true</code> si el objeto recibido es una cartas con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object objeto) {
        if (!(objeto instanceof Carta))
            return false;
        Carta cartita = (Carta) objeto;
        if ((this.valor.equals(cartita.valor)) == false) {
            return false;
        } else if ((this.color.equals(cartita.color)) == false) {
            return false;
        }
        return true;
    }

    /**
     * Metodo para poder comparar dos cartas, primero se checan sus
     * colores y luego se checan sus valores.
     * 
     * @param card la carta a comparar.
     * @return dependiendo si la carta que llamo el metodo es:
     *         menor que card, regresa -1
     *         mayor que card, regresa 1
     *         igual que card, regresa 0
     */
    @Override
    public int compareTo(Carta card) {
        if (this.color.getMerito() > card.color.getMerito()) {
            return 1;
        } else if (this.color.getMerito() == card.color.getMerito()) {
            if (this.valor.getNumero() > card.valor.getNumero()) {
                return 1;
            } else if (this.valor.getNumero() == card.valor.getNumero()) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}