package edd.src.Automata;

import java.util.Random;
import edd.src.Estructuras.*;

public class Mondrian extends AC {

    int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
    int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
    int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];

    /*
     * Método que pinta una matriz inicial de banco y le da valores aleatorios a las casillas.
     */
    @Override
    public int[][] getAutomata() {
        int aux1; 
        // Inicialización de dos matrices en blanco.
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = 2;
                MauxCopia[i][j] = 2;
            }
        }
        /* Modificación de cada valor de la matriz Maux de forma aleatoria. Para empezar con un 
           estado random de colores. */
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {
                aux1 = (int) (Math.random() * 14); // Random del 0 al 12.
                if (aux1 <= 1) {
                    Maux2[i][j] = 3; // Negro
                } else if (aux1 > 3 && aux1 <= 5) {
                    Maux2[i][j] = 1; // Azul
                } else if (aux1 > 5 && aux1 <= 7) {
                    Maux2[i][j] = 0; // Amarillo
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
     * Método para evolucionar el autómata.
     */
    @Override
    public void evoluciona() {
        // Se crea una matriz copia para reemplazar los valores.
        int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];
        // Aquí empieza una evolución.
        // Creamos una pila y una cola, para que te diviertas joven artista. 
        Pila<Integer> pila = new Pila<Integer>();
        Cola<Integer> cola = new Cola<Integer>();
        // La matriz Maux2 contiene el estado actual de la matriz.
        // La matriz CopiaM es una matriz copia de Maux2 donde debes poner los nuevos valores.
        for (int i = 0; i < Maux2.length; i++) { 
			for (int j = 0; j < Maux2.length; j++) {
				pila.empty(); // Vaciar la pila.
                cola.empty(); // Vaciar la cola.
				/* System.out.println("Revisando " + i  + ","  + j  ); SOP que ayuda a checar 
                   que se realize correctamente el for. */
				for (int k = i - 1; k <= i + 1; k++) {
					for (int l = j - 1; l <= j + 1; l++) {
						// Análisis de casillas vecindad.
						if (k >= 0 && l >= 0 && k < Maux2.length && l < Maux2.length && (k != i || l != j)) {
							/* System.out.println("    Analizando " + k  + ","  + l  + "  --> " + Maux2[k][l]     ); 
                               SOP que ayuda a checar los for. */
                            switch (Maux2[k][l]) {
                                case 0:
                                    pila.push(0); // Agregando a la pila el color de su vecino.
                                    cola.push(0); // Agregando a la cola el color de su vecino.
                                    break;
                                case 1:
                                    pila.push(1); // Agregando a la pila el color de su vecino.
                                    cola.push(1); // Agregando a la cola el color de su vecino.
                                    break;
                                case 2:
                                    pila.push(2); // Agregando a la pila el color de su vecino.
                                    cola.push(2); // Agregando a la cola el color de su vecino.
                                    break;
                                case 3:
                                    pila.push(3); // Agregando a la pila el color de su vecino.
                                    cola.push(3); // Agregando a la cola el color de su vecino.
                                    break;
                                case 4:
                                    pila.push(4); // Agregando a la pila el color de su vecino.
                                    cola.push(4); // Agregando a la cola el color de su vecino.
                                    break;
                                case 5:
                                    pila.push(5); // Agregando a la pila el color de su vecino.
                                    cola.push(5); // Agregando a la cola el color de su vecino.
                                    break;
                                default:    break;
                            }
						}
					}
				}
                if (Maux2[i][j] % 2 == 0) { // Checamos si el color actual es par.
                    switch (cola.peek() % 2) { // Checamos si su primer vecino es par o impar.
                        case 0:
                            CopiaM[i][j] = (Maux2[i][j] + 5) % 6; // Si es par hacemos esto.
                            break;
                        case 1:
                            CopiaM[i][j] = (Maux2[i][j] + 4) % 6; // Si es impar hacemos esto.
                            break;
                        default: // No se debería llegar a este caso.
                            break;
                    }
                } else if (pila.peek() > 3) { // Checamos si su último vecino es mayor estricto que 3.
                    CopiaM[i][j] = (cola.pop() + 5)%6; // Si pasa esto sacamos al primer vecino y hacemos esto.
                } else {
                    CopiaM[i][j] = (pila.pop() + 4)%6; // Si pasa esto sacamos al último vecino y hacemos esto.
                }  
            }  // Usamos la vecindad de Maux2[i][j]
			   // SOP que cuenta las casillas vecinas muertas y vivas y dice como cambiara el estado de la casilla.
			   // System.out.println("      Muertos " + muertos + "  Vivos-> " + vivos   + " -----> " + CopiaM[i][j]   );
		}
		for (int i = 0; i < Maux2.length; i++) {      // Iteraciones que arreglan la matriz a regresar en la copia.
			for (int j = 0; j < Maux2.length; j++) {
				Maux2[i][j] = CopiaM[i][j];
			}
		}
        for (int i = 0; i < Maux2.length; i++) {      // Iteraicones que arreglan la matriz a regresar en la copia.
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = CopiaM[i][j];
            }
        }
        // System.out.println("Termine"); SOP que ayuda a saber cuando acaba una evolución.
    }

    public int[][] getAutomata2() {
        return Maux2;
    }
}