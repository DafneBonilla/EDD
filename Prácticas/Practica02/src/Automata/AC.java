package edd.src.Automata;

import java.awt.Color;
public abstract class AC implements AutomataCelular{
    int estado; //Contador de evoluciones.
    Color[] colores; //Arreglo de colores.
    int [][] Maux;  //Matriz auxiliar.

     /*
     *Metodo que regresa el numero de evolucion.
     */
     @Override
    public int getEvolucion() {
        return estado++;
    }

     /*
     *Metodo que reinicia el numero de evolucion.
     */
    public void reiniciaEvolucion() {
        estado = 0;
    }

    /*
    *Metodo que inicia el automata sin evoluciones.
    */
    @Override
    public int[][] getAutomata() {
        Maux = new int[Imagen.numCells][Imagen.numCells];
        for (int i=0;i<Maux.length;i++) {
            for (int j =0;i<Maux.length;i++) {
                Maux[i][j]=3;
            }
        }
        return Maux;
    }

    /*
    *Metodo que regresa el arreglo de colores.
    */
    @Override
    public Color[] getColores() {
        colores = new Color[]{Color.ORANGE,Color.BLUE,Color.BLACK,Color.WHITE,Color.RED,Color.GREEN};
        return colores;
    }
    /*
    *Metodo absracto para evolucionar.
    */
    @Override
    abstract public void evoluciona();

    /*
    *Metodo que regresa la matriz en las evoluciones.
    */
    @Override
    abstract public int[][] getAutomata2();
}