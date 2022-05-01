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
        Lista<Posicion> copia = this.tablerito.clone();
        return new Tablero(copia);
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
     * Mueve una ficha basado en una opción.
     * 
     * @param opcion la opcion que se desea mover.
     */
    public void mover(Opcion opcion) {
        mover(opcion.getPosicionInicial(), opcion.getPosicionFinal());
    }

    /**
     * Mueve una ficha de una posicion a otra.
     * 
     * @param origen  la posicion de origen.
     * @param destino la posicion de destino.
     */
    private void mover(int origen, int destino) {
        int aux = tablerito.buscarIndice(destino - 1).getDueño();
        tablerito.buscarIndice(destino - 1).setDueño(tablerito.buscarIndice(origen - 1).getDueño());
        tablerito.buscarIndice(origen - 1).setDueño(aux);
        System.out.println("Se movio la ficha de " + origen + " a " + destino);
    }

    public void moverEspecial(Opcion opcion) {
        moverEspecial(opcion.getPosicionInicial(), opcion.getPosicionFinal());
    }

    private void moverEspecial(int origen, int destino) {
        int aux = tablerito.buscarIndice(destino - 1).getDueño();
        tablerito.buscarIndice(destino - 1).setDueño(tablerito.buscarIndice(origen - 1).getDueño());
        tablerito.buscarIndice(origen - 1).setDueño(aux);
    }

    /**
     * Regresa una lista con los posibles movimientos de un jugador.
     * 
     * @param jugador el jugador.
     */
    public Lista<Opcion> getOpciones(int jugador) {
        Lista<Opcion> movimientos = new Lista<Opcion>();
        for (Posicion pos : tablerito) {
            if (pos.getDueño() == jugador) {
                int lugarcito = pos.getLugar();
                for (Posicion pos2 : tablerito) {
                    if (pos2.getDueño() == 0) {
                        int lugarcito2 = pos2.getLugar();
                        switch (lugarcito) {
                            case 1:
                                if (lugarcito2 == 2 || lugarcito2 == 3 || lugarcito2 == 4) {
                                    movimientos.agregaFinal(new Opcion(lugarcito, lugarcito2));
                                }
                                break;
                            case 2:
                                if (lugarcito2 == 1 || lugarcito2 == 3 || lugarcito2 == 5) {
                                    movimientos.agregaFinal(new Opcion(lugarcito, lugarcito2));
                                }
                                break;
                            case 3:
                                if (lugarcito2 == 1 || lugarcito2 == 2 || lugarcito2 == 4 || lugarcito2 == 5) {
                                    movimientos.agregaFinal(new Opcion(lugarcito, lugarcito2));
                                }
                                break;
                            case 4:
                                if (lugarcito2 == 1 || lugarcito2 == 3) {
                                    movimientos.agregaFinal(new Opcion(lugarcito, lugarcito2));
                                }
                                break;
                            case 5:
                                if (lugarcito2 == 2 || lugarcito2 == 3) {
                                    movimientos.agregaFinal(new Opcion(lugarcito, lugarcito2));
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

        }
        return movimientos;
    }

}
