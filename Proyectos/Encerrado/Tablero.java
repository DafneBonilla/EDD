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
     * un entero para saber el estado inicial del tablero.
     * Si n = 1 entonces la ficha de la esquina superior izquierda
     * será de color rojo, si n = 2 entonces la ficha de la esquina superior
     * izquierda será de color azul y si n = 3 es personalizado el tablero.
     * 
     * @param n       la versión de posición iniciales.
     * @param version si tendrá circulitos o no.
     * @param config  la lista personalizada.
     */
    public Tablero(int n, int version, Lista<Lista<Integer>> config) {
        tablerito = new Lista<Posicion>();
        if (n == 1) {
            tablerito.agregaFinal(new Posicion(1, 1, version));
            tablerito.agregaFinal(new Posicion(2, 2, version));
            tablerito.agregaFinal(new Posicion(3, 0, version));
            tablerito.agregaFinal(new Posicion(4, 2, version));
            tablerito.agregaFinal(new Posicion(5, 1, version));
        } else if (n == 2) {
            tablerito.agregaFinal(new Posicion(1, 2, version));
            tablerito.agregaFinal(new Posicion(2, 1, version));
            tablerito.agregaFinal(new Posicion(3, 0, version));
            tablerito.agregaFinal(new Posicion(4, 1, version));
            tablerito.agregaFinal(new Posicion(5, 2, version));
        } else if (n == 3) {
            Lista<Integer> rojos = config.buscarIndice(0);
            Lista<Integer> azules = config.buscarIndice(1);
            for (int i = 1; i < 6; i++) {
                int dueno = 0;
                if (rojos.contains(i)) {
                    dueno = 1;
                } else if (azules.contains(i)) {
                    dueno = 2;
                }
                Posicion p = new Posicion(i, dueno, version);
                tablerito.agregaFinal(p);
            }
        }
    }

    /**
     * Regresa una copia de la lista de posiciones.
     * 
     * @return una copia de la lista de posiciones.
     */
    public Tablero copia() {
        Lista<Posicion> copia = this.tablerito.clone();
        Tablero nuevo = new Tablero();
        nuevo.tablerito = copia;
        return nuevo;
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
     * Regresa una representación en cadena de la posición.
     * 
     * @return una representación en cadena de la posición.
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
     * @param opcion la opción que se desea mover.
     */
    public void mover(Opcion opcion) {
        mover(opcion.getPosicionInicial(), opcion.getPosicionFinal());
    }

    /**
     * Mueve una ficha de una posición a otra.
     * 
     * @param origen  la posición de origen.
     * @param destino la posición de destino.
     */
    private void mover(int origen, int destino) {
        int aux = tablerito.buscarIndice(destino - 1).getDueno();
        tablerito.buscarIndice(destino - 1).setDueno(tablerito.buscarIndice(origen - 1).getDueno());
        tablerito.buscarIndice(origen - 1).setDueno(aux);
        System.out.println("Se movió la ficha de " + origen + " a " + destino);
    }

    /**
     * Mueve una ficha basado en una opción, pero sin decirlo.
     * 
     * @param opcion la opción que se desea mover.
     */
    public void moverEspecial(Opcion opcion) {
        moverEspecial(opcion.getPosicionInicial(), opcion.getPosicionFinal());
    }

    /**
     * Mueve una ficha de una posición a otra, pero sin decirlo.
     * 
     * @param origen  la posición de origen.
     * @param destino la posición de destino.
     */
    private void moverEspecial(int origen, int destino) {
        int aux = tablerito.buscarIndice(destino - 1).getDueno();
        tablerito.buscarIndice(destino - 1).setDueno(tablerito.buscarIndice(origen - 1).getDueno());
        tablerito.buscarIndice(origen - 1).setDueno(aux);
    }

    /**
     * Regresa una lista con los posibles movimientos de un jugador.
     * 
     * @param jugador el jugador.
     * @return una lista con los posibles movimientos de un jugador.
     */
    public Lista<Opcion> getOpciones(int jugador) {
        Lista<Opcion> movimientos = new Lista<Opcion>();
        for (Posicion pos : tablerito) {
            if (pos.getDueno() == jugador) {
                int lugarcito = pos.getLugar();
                for (Posicion pos2 : tablerito) {
                    if (pos2.getDueno() == 0) {
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