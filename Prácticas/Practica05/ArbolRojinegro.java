package edd.src.Estructuras;

/**
 * Clase para árboles rojinegros. Un árbol rojinegro cumple las siguientes
 * propiedades:
 *
 * <ol>
 * <li>Todos los vértices son NEGROS o ROJOS.</li>
 * <li>La raíz es NEGRA.</li>
 * <li>Todas las hojas (<tt>null</tt>) son NEGRAS (al igual que la raíz).</li>
 * <li>Un vértice ROJO siempre tiene dos hijos NEGROS.</li>
 * <li>Todo camino de un vértice a alguna de sus hojas descendientes tiene el
 * mismo número de vértices NEGROS.</li>
 * </ol>
 *
 * Los árboles rojinegros se autobalancean.
 */
public class ArbolRojinegro<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {
    /**
     * Clase interna protegida para vértices de árboles rojinegros. La única
     * diferencia con los vértices de árbol binario, es que tienen un campo para
     * el color del vértice.
    */
    protected class VerticeRojinegro extends Vertice {
        public Color color;
    
        public VerticeRojinegro(T elemento){
            super(elemento);
            color = Color.NINGUNO;
        }
        
        /**
         * Regresa una representación en cadena del vértice rojinegro.
         * 
         * @return una representación en cadena del vértice rojinegro.
         */
        public String toString() {
            if (this == null)
                return "";
            if (this.color == Color.ROJO)
                return "R{" + this.elemento.toString() + "}";
            return "N{" + this.elemento.toString() + "}";
        }
        
        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * 
         * @param o el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeRojinegro}, su elemento es igual al elemento de
         *         éste vértice, los descendientes de ambos son recursivamente
         *         iguales, y los colores son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked")
            VerticeRojinegro vertice = (VerticeRojinegro) o;
            return color == vertice.color && super.equals(o);
        }

    }

    public ArbolRojinegro() {
        super();
    }

    /**
     * Construye un árbol rojinegro a partir de una colección. El árbol
     * rojinegro tiene los mismos elementos que la colección recibida.
     * 
     * @param coleccion la colección a partir de la cual creamos el árbol
     *                  rojinegro.
     */
    public ArbolRojinegro(Collection<T> coleccion) {
        super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link
     * VerticeRojinegro}.
     * 
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice rojinegro con el elemento recibido dentro del mismo.
     */
    @Override
    protected Vertice nuevoVertice(T elemento) {
        return new VerticeRojinegro(elemento);
    }

    protected VerticeRojinegro convertirRojiNegro(VerticeArbolBinario<T> vertice) {
        return (VerticeRojinegro) vertice;
    }

    /**
     * Regresa el color del vértice rojinegro.
     * 
     * @param vertice el vértice del que queremos el color.
     * @return el color del vértice rojinegro.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *                            VerticeRojinegro}.
     */
    public Color getColor(VerticeArbolBinario<T> vertice) {
        VerticeRojinegro aux = (VerticeRojinegro) vertice;
        return aux.color;
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol recoloreando
     * vértices y girando el árbol como sea necesario.
     * 
     * @param elemento el elemento a agregar.
     */
    @Override
    public void add(T elemento) {
        if (elemento != null) {
            super.add(elemento);

            // ToDo ... Conseguir el ultimoAgregado
            VerticeRojinegro v = convertirRojiNegro(ultimoAgregado);
            v.color = Color.ROJO;
            rebalancea(v);
        }

    }

    private void rebalancea(VerticeRojinegro v){
        // Caso 1
        // Caso 2
        // Caso 3
        // Caso 4
        // Caso 5
    }

    //Saber si un vertice tiene 2 hijos
    private boolean tiene2hijos(VerticeRojinegro v){

    
    }


    private boolean hasAbuelo(VerticeRojinegro v){

    }

    private VerticeRojinegro getAbuelo(VerticeRojinegro v) {

    }

    private VerticeRojinegro getPadre(VerticeRojinegro v) {
        
    }

    private boolean hasTio(VerticeRojinegro v) {
        return ;
    }

    private VerticeRojinegro getTio(VerticeRojinegro v) {
        return;
    }

    private boolean isLeft(VerticeRojinegro v) {
        return;
    }

    private boolean isDerecho(VerticeRojinegro v) {
        return;
    }   

    

    private boolean hasHermano(VerticeRojinegro v) {
        return;
    }

    // Si es derecho -> devuelve el izq
    // Si es Izq -> devuelve el der
    private VerticeRojinegro getHermano(VerticeRojinegro v) {
        return;
    }

    //v.padre es izq y v es derecho.
    private boolean cruzadosIzq(VerticeRojinegro v){
        return;
    }

    // v.padre es der y v es izq.
    private boolean cruzadosDer(VerticeRojinegro v) {
        return;
    }

    private boolean isRojo(){
        return;
    }

    private boolean isNegro(){
        return;
    }



    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y recolorea y gira el árbol como sea necesario para
     * rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public boolean delete(T elemento) {
        // Caso 1
        // Caso 2
        // Caso 3
        return false;
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la izquierda por los usuarios de la
     * clase, porque se desbalancean.
     * 
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    //@Override
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                "pueden girar a la izquierda " +
                "por el usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la derecha por los usuarios de la
     * clase, porque se desbalancean.
     * 
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    //@Override
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                "pueden girar a la derecha " +
                "por el usuario.");
    }






    
}
