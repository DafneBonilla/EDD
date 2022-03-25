package edd.src.Automata;

import java.util.Random;
import edd.src.Estructuras.*;

public class Mondrian extends AC {

    int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
    int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
    int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];

    

    /*
     * Metodo que pinta una matriz inicial de Blanco y le da valores aleatorios a las
     * casillas.
     *
     */
    @Override
    public int[][] getAutomata() {
        int aux1; 
        //Inicializo dos matrices en blanco
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = 2;
                MauxCopia[i][j] = 2;
            }
        }
        // Modifico cada valor de la matriz Maux de forma aleatoria. Para empezar con un estado random de colores
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {

                aux1 = (int) (Math.random() * 14); // Random del 0 al 12

                if (aux1 <= 1) {
                    Maux2[i][j] = 3; // Color negro
                } else if (aux1 > 3 && aux1 <= 5) {
                    Maux2[i][j] = 1; // Azul
                } else if (aux1 > 5 && aux1 <= 7) {
                    Maux2[i][j] = 0; // amarillo
                } else if (aux1 > 6 && aux1 <= 8) {
                    Maux2[i][j] = 4; // Rojo
                } else {

                    Maux2[i][j] = 2; // Blanco
                }
            }
        }
        return Maux2;
    }

    /*
     * Metodo para evolucionar el automata.
     *
     */
    @Override
    public void evoluciona() {

        // Se crea una matriz copia para reemplazar los Valores.
        int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];
        
        // Aqui empieza una evolucion.

        //Creamos una pila y una cola, para que te diviertas joven Artista. 
        Pila<Integer> pila = new Pila<Integer>();
        Cola<Integer> cola = new Cola<Integer>();
        
        //La matriz Maux2 Contiene el estado actual de la matriz.
        //La matriz CopiaM es una matriz copia de Maux2 donde debes poner los nuevos valores
        
        for (int i=0;i<Maux2.length;i++) { 
			for (int j=0;j<Maux2.length;j++) {
				pila.empty(); //Vaciar la pila.
                cola.empty(); //Vaciar la cola.

				//System.out.println("Revisando " + i  + ","  + j  ); SOP que ayuda a checar que se realize correctamente el for.
				for (int k=i-1;k<=i+1;k++) {
					for (int l=j-1;l<=j+1;l++) {
						//Analisis de casillas vecindad.
						if (k>=0&&l>=0&&k<Maux2.length&&l<Maux2.length&&(k!=i|| l!=j)) {
							//System.out.println("    Analizando " + k  + ","  + l  + "  --> " + Maux2[k][l]     ); SOP que ayuda a checar los for.
                            switch (Maux2[k][l]) {
                                case 0:
                                    pila.push(0);
                                    cola.push(0);
                                    break;
                                case 1:
                                    pila.push(1);
                                    cola.push(1);
                                    break;
                                case 2:
                                    pila.push(2);
                                    cola.push(2);
                                    break;
                                case 3:
                                    pila.push(3);
                                    cola.push(3);
                                    break;
                                case 4:
                                    pila.push(4);
                                    cola.push(4);
                                    break;
                                case 5:
                                    pila.push(5);
                                    cola.push(5);
                                    break;
                                default:    break;
                            }
						}
					}
				}
                if (Maux2[i][j]%2 == 0) {
                    switch (cola.peek()%2) {
                        case 0:
                            CopiaM[i][j] = (Maux2[i][j]+5)%6;
                            break;
                        case 1:
                            CopiaM[i][j] = (Maux2[i][j]+4)%6;
                            break;
                        default:
                            break;
                    }
                } else if (pila.peek()>2) {
                    CopiaM[i][j] = cola.pop();
                } else {
                    CopiaM[i][j] = pila.pop();
                }
                }
				// SOP que cuenta las casillas vecinas muertas y vivas y dice como cambiara el estado de la casilla.
				// System.out.println("      Muertos " + muertos + "  Vivos-> " + vivos   + " -----> " + CopiaM[i][j]   );
			}
		for (int i=0;i<Maux2.length;i++) { 			//Fors que arreglan la matriz a regresar en la copia.
			for (int j=0;j<Maux2.length;j++) {
				Maux2[i][j]=CopiaM[i][j];
			}
		}
        // Aqui va tu codigo  

        


        for (int i = 0; i < Maux2.length; i++) { // Fors que arreglan la matriz a regresar en la copia.
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = CopiaM[i][j];
            }
        }
        // System.out.println("Termine");//SOP que ayuda a saber cuando acaba una
        // evolucion.
    }

    public int[][] getAutomata2() {
        return Maux2;
    }
}