package edd.src.Estructuras;



public interface ComparableIndexable<T> extends Comparable<T> {
    /** 
     * Regresar el indice del objeto
     */
    public int getIndice();

    /**
     * Actualiza el indice del objeto
    */
    public void setIndice(int indice);

    

}
