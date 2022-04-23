package Encerrado;

import Encerrado.Estructuras.Lista;

/**
 * Clase para representar tableros. Serán listas de la clase {@link Posicion}.
 */
public class Tablero {

    /* Lista de posiciones */
    private Lista<Posicion> tablerito;

    /**
     * Define el estado inical de un tablero.
     */
    public Tablero() {
        tablerito = new Lista<Posicion>();
    }

    /**
     * Define el estado inical de un tablero, usando
     * una lista de posiciones. Usado para generar una copia
     * de un tablero.
     * 
     * @param tablerito la lista de posiciones.
     */
    public Tablero(Lista<Posicion> tablerito) {
        this.tablerito = tablerito;
    }

    /**
     * Define el estado inical de un tablero, usando
     * un entero para saber el estado inicial del tablero.
     * Si n = 1 entonces la ficha de la esquina superior izquierda
     * sera de color rojo, si n = 2 entonces la ficha de la esquina superior
     * izquierda sera de color azul.
     * 
     * @param n la version de posicion iniciales.
     */
    public Tablero(int n) {
        tablerito = new Lista<Posicion>();
        if (n == 1) {
            tablerito.agregaFinal(new Posicion(1, 1));
            tablerito.agregaFinal(new Posicion(2, 2));
            tablerito.agregaFinal(new Posicion(3, 0));
            tablerito.agregaFinal(new Posicion(4, 2));
            tablerito.agregaFinal(new Posicion(5, 1));
        } else if (n == 2) {
            tablerito.agregaFinal(new Posicion(1, 2));
            tablerito.agregaFinal(new Posicion(2, 1));
            tablerito.agregaFinal(new Posicion(3, 0));
            tablerito.agregaFinal(new Posicion(4, 1));
            tablerito.agregaFinal(new Posicion(5, 2));
        }
    }

    /**
     * Regresa una copia de la lista de posiciones.
     * 
     * @return una copia de la lista de posiciones.
     */
    public Tablero copia() {
        return new Tablero(tablerito.clone());
    }

    /**
     * Regresa la lista de posiciones.
     * 
     * @return la lista de posiciones.
     */
    public Lista<Posicion> getTablerito() {
        return tablerito;
    }

    /**
     * Regresa una representacion en cadena de la posicion.
     * 
     * @return una representacion en cadena de la posicion.
     */
    @Override
    public String toString() {
        String datitos = "";
        datitos += String.format(
                "%s-----------------%s\n - --             -- - \n -    --       --    - \n -       -- --       - \n -        %s        - \n -       -- --       - \n -    --       --    - \n - --             -- - \n%s                 %s\n",
                tablerito.buscarIndice(0).toString(), tablerito.buscarIndice(1).toString(),
                tablerito.buscarIndice(2).toString(), tablerito.buscarIndice(3).toString(),
                tablerito.buscarIndice(4).toString());
        return "\n" + datitos + "\n";
    }

    /**
     * Mueve una ficha de una posicion a otra.
     * 
     * @param origen  la posicion de origen.
     * @param destino la posicion de destino.
     */
    public void mover(int origen, int destino) {
        int aux = tablerito.buscarIndice(destino - 1).getDueño();
        tablerito.buscarIndice(destino - 1).setDueño(tablerito.buscarIndice(origen - 1).getDueño());
        tablerito.buscarIndice(origen - 1).setDueño(aux);
    }

}
