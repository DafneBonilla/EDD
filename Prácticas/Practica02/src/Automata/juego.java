package edd.src.Automata;
import java.util.Random;
import edd.src.Estructuras.*;

public class juego extends AC{

		int[][] Maux2=new int[Imagen.numCells][Imagen.numCells];
		int[][] MauxCopia=new int [Imagen.numCells][Imagen.numCells];
		int[][] CopiaM =new int [Imagen.numCells][Imagen.numCells];


		Pila<Integer> pila1 = new Pila<Integer>();
	 
   	/*
   	*Metodo que pinta una matriz de Blanco y le da valores aleatorios a las casillas.
   	*
   	*/
	 @Override
    public int[][] getAutomata() {
    	int aux1;
    	for (int i=0;i<Maux2.length ;i++ ) {
    		for (int j=0;j<Maux2.length ;j++ ) {
    		Maux2[i][j]=2;
    		MauxCopia[i][j]=2;
    		}
    	}
 		   	//Maux2[2][1]=1;  Elementos que use como prueba del automata
 		   	//Maux2[2][2]=1;   Trazando una linea vertical en la matriz
 		   	//Maux2[2][3]=1;     para crear un Parpadeador o ´blinker´.
		

		//Modifico cada valor de la matriz Maux de forma aleatoria.
			for (int i=0;i<Maux2.length;i++) {
				for (int j=0;j<Maux2.length;j++) {

					aux1 = (int) ( Math.random() * 14 ); //Random del 0 al 12
					
					if (aux1<1) {
						Maux2[i][j] = 2; 
					}else if (aux1>3 && aux1<=5) {
						Maux2[i][j] = 1; // Azul
					}else if (aux1>5 && aux1<=7) {
						Maux2[i][j] = 2; 
					}else if (aux1>6 && aux1<=8) {
						Maux2[i][j] = 2; 
					}else {

						Maux2[i][j] = 2;
					}
				}
			}
    	return Maux2;
    }
    
	/*
    *Metodo para evolucionar el automata.
	*
	*/
	 @Override
	public void evoluciona(){
		
		// Se crea una matriz copia para reemplazar los Valores.
		int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];
		// System.out.println("entre"); SOP que ayuda a verificar cuando se efectuaba un
		// evoluciona.

		// super.estado++; // Operacion que aumentaba el contador en una unidad.
		int vivos; // Contador de casillas vecindad vivas.
		int muertos; // Contador de casillas vecindad muertas.

		// For que escanea toda la matriz.
		for (int i = 0; i < Maux2.length; i++) {
			for (int j = 0; j < Maux2.length; j++) {
				vivos = 0; // Reiniciar contador de vivos.
				muertos = 0; // Reiniciar contador de muertos.

				// System.out.println("Revisando " + i + "," + j ); SOP que ayuda a checar que
				// se realize correctamente el for.
				for (int k = i - 1; k <= i + 1; k++) {
					for (int l = j - 1; l <= j + 1; l++) {
						// Analisis de casillas vecindad.
						if (k >= 0 && l >= 0 && k < Maux2.length && l < Maux2.length && (k != i || l != j)) {
							// System.out.println(" Analizando " + k + "," + l + " --> " + Maux2[k][l] );
							// SOP que ayuda a checar los for.
							if (Maux2[k][l] == 1) {
								vivos++;
							} else {
								muertos++;
							}
						}
					}
				}
				if (Maux2[i][j] == 1) { // Si la casilla esta viva,
					if (vivos == 2 || vivos == 3) { // Y tiene dos o tres vecinos vivos
						CopiaM[i][j] = 1; // entonces la casilla vivira.
					} else {
						CopiaM[i][j] = 2; // De otra forma muere.
					}
				} else { // Si la casilla esta muerta,
					if (vivos == 3) { // Y tiene 3 vecinos vivos
						CopiaM[i][j] = 1; // La casilla muerta vivira.
					} else {
						CopiaM[i][j] = 2; // De otro modo seguira muerta.
					}
				}
				// SOP que cuenta las casillas vecinas muertas y vivas y dice como cambiara el
				// estado de la casilla.
				// System.out.println(" Muertos " + muertos + " Vivos-> " + vivos + " -----> " +
				// CopiaM[i][j] );
			}
		}
		for (int i = 0; i < Maux2.length; i++) { // Fors que arreglan la matriz a regresar en la copia.
			for (int j = 0; j < Maux2.length; j++) {
				Maux2[i][j] = CopiaM[i][j];
			}
		}
		//System.out.println("Termine");//SOP que ayuda a saber cuando acaba una evolucion.
	}
	
	public int[][] getAutomata2() {
		return Maux2;
	}
}