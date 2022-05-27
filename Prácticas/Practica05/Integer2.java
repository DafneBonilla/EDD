/**
 * Clase para enteros con indice.
 */
public class Integer2 implements ComparableIndexable<Integer2> {

    /* Entero */
    private Integer numero;
    /* Indice */
    private int indice;

    /**
     * Construye una Integer2.
     *
     * @param num el numero de la Integer2.
     */
    public Integer2(Integer num) {
        this.numero = num;
    }
    
    /** 
     * Regresar el indice de la Integer2.
     *
     * @return el indice de la Integer2.
     */
    @Override
    public int getIndice() {
        return this.indice;
    }

    /**
     * Define el indice de la Integer2.
     *
     * @param indice el nuevo indice de la Integer2.
     */
    @Override
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**
     * Compara dos Integer2.
     *
     * @return el resultado de la comparacion.
     */
    @Override
    public int compareTo(Integer2 o) {
        return this.numero.compareTo(o.numero);
    }

    /**
     * Regresa una representacion en cadena de la Integer2.
     * 
     * @return la representacion en cadena de la Integer2.
     */
    @Override
    public String toString() {
        return this.numero.toString();
    }
}