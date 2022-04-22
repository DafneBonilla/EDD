/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

import java.lang.Math;  

public class Practica3 {
    
    /**
     * Dada una lista de enteros y un entero N, encuentra la pareja de números en la
     * lista tal que la suma de estos, es la más cercana a N.
     * El método es O(nlogn) ya que lo que toma más operaciones es ordenar la lista.
     * @param lista la lista de enteros.
     * @param N el entero que queremos encontrar a partir de la suma.
     */
    public void sumaCercana(Lista<Integer> lista, int N){
        Lista<Integer> ordenada = lista.mergeSort((a,b) -> a-b);
        int[] arreglito = new int[ordenada.size()];
        int cont = 0;
        for (Integer i : ordenada) {
            arreglito[cont] = i;
            cont++;
        }
        int diferencia = 999999999;
        int primero = 0;
        int segundo = ordenada.size()-1;
        int resultadoPrimero = 0;
        int resultadoSegundo = 0;
        while (primero < segundo) {
            int sumita = arreglito[primero]+arreglito[segundo];
            int diferencita = Math.abs(((sumita) - N));
            if (diferencita < diferencia) {
                diferencia = diferencita;
                resultadoPrimero = primero;
                resultadoSegundo = segundo;
            }
            int nuevaSumita = arreglito[primero] + arreglito[segundo];
            if ((nuevaSumita) > N) {
                segundo --;
            } else {
                primero ++;
            }
        }
        System.out.println("Los números más cercanos que suman son " + arreglito[resultadoPrimero] + " y " + arreglito[resultadoSegundo]);
    }
    
    /**
     * Imprime todas las permutaciones de una cadena.
     * @param cadena la cadena a permutar.
     */
    public static void permutacionesCadena(String cadena){
        if (cadena.length() <= 0 || cadena == null)
            return;
        permutacionAux(cadena, 0, cadena.length() - 1);
    }

    /**
     * Método auxiliar para permutacionesCadena.
     * @param cadenita la cadena a permutar.
     * @param inicio el entero que indica el inicio de la cadena.
     * @param ultimo el entero que indica el final de la cadena.
     */
    private static void permutacionAux(String cadenita, int inicio, int ultimo) {
        if (inicio == ultimo)
            System.out.println(cadenita);
        for (int i = inicio; i <= ultimo; i++) {
            cadenita = cambiar(cadenita, inicio, i);
            permutacionAux(cadenita, inicio + 1, ultimo);
            cadenita = cambiar(cadenita, inicio, i);
        }
    }

    /**
     * Método auxiliar para permutacionesCadena.
     * @param cadenita la cadena a permutar.
     * @param primero el entero que indica la posición 1.
     * @param segundo el entero que indica la posición 2.
     * @return la cadena cambiada.
     */
    private static String cambiar(String cadena, int primero, int segundo) {
        char[] palabra = cadena.toCharArray();
        char aux = palabra[primero];
        palabra[primero] = palabra[segundo];
        palabra[segundo] = aux;
        String regresar = String.valueOf(palabra);
        return regresar;
    }

    /**
     * Dados 3 números, la suma S, el primo P, y un entero N, encuentra N primos 
     * mayores que P, tal que su suma sea igual a S.
     * @param S la suma a llegar.
     * @param P el primo inicial.
     * @param N el número de primos a encontrar.
     */
    public void primosQueSuman(int S, int P, int N){
        Lista<Integer> primos = PrimosMayores(P, S);
        Lista<Integer> respuestas = new Lista<Integer>();
        if (sumaRaraSinRepetirAux(primos, S, N, respuestas) == true) {
            respuestas.mergeSort((a, b) -> a - b);
            int cont = 1;
            for (Integer i : respuestas) {
                if (cont <= N) {
                    System.out.print(i + " ");
                    cont++;
                }
            }
        } else {
            System.out.println("No hay solución");
        }
    }

    /**
     * Método auxiliar para primosQueSuman.
     * @param menor el numero menor. 
     * @param mayor el número mayor.
     * @return una lista con los primeros mayores a P. 
     */
    private static Lista<Integer> PrimosMayores(int menor, int mayor) {
        Lista<Integer> primos = new Lista<Integer>();
        for (int i = menor + 1; i <= mayor; i++) {
            if (esPrimo(i))
                primos.add(i);
        }
        return primos;
    }

    /**
     * Verifica que un número sea primo.
     * @param numero el número a verificar.
     * @return <tt>true</tt> si el número es primo,
     *         <tt>false</tt> en otro caso.
    */
    private static boolean esPrimo(int numero) {
        if (numero < 1)
            return false;
        if (numero == 1)
            return true;
        for (int i = 2; i < numero; i++)
            if (numero % i == 0)
                return false;
        return true;
    }

    /**
     * Suma a los primos sin repetir. 
     * @param primos la lista de primos a sumar.
     * @param s la suma a encontrar.
     * @param n el primero inicial.
     * @param respuestas la lista con las respuestas.
     * @return <tt>true</tt> si encontró una solución.
     *         <tt>false</tt> en otro caso.
     */
    private static boolean sumaRaraSinRepetirAux(Lista<Integer> primos, int s, int n, Lista<Integer> respuestas) {
        if (n <= respuestas.size()) {
            if (checar(respuestas, s)) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < primos.size(); i++) {
            respuestas.add(primos.buscarIndice(i));
            primos.delete2(i);
            if (sumaRaraSinRepetirAux(primos, s, n, respuestas)) {
                return true;
            }
            primos.add(respuestas.buscarIndice(respuestas.size() - 1));
            respuestas.delete2(respuestas.size() - 1);
        }
        return false;
    }

    /**
     * Método auxiliar para primosQueSuman.
     * @param respuestas la lista con las respuestas.
     * @param s la suma a encontrar.
     * @return <tt>true</tt> si encontró una solución.
     *         <tt>false</tt> en otro caso.
     */
    private static boolean checar(Lista<Integer> respuestas, int s) {
        int total = 0;
        for (Integer i : respuestas) {
            total += i;
        }
        if (total == s) {
            return true;
        }
        return false;
    }

    /**
     * Acomoda N reinas en un tablero de N x N, de tal forma que ninguna se ataque mutuamente
     * @param N el tamaño del tablero y reinas.
     */
    public static void N_Reinas(int N) {
        if (N <= 0)
            return;
        int[][] tablero = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tablero[i][j] = 0;
            }
        }
        if (N == 2 || N == 3 || NReinasAux(tablero, 0, N) == false) {
            System.out.println("No hay solución :(");
        } else {
            imprimir(tablero, N);
        }
    }

    /**
     * Revisa si hay una solucion para una columna del tablero
     * @param tablero el tablero.
     * @param columna la columna a resolver.
     * @param n la cantidad de reinas.
     * @return <tt>true</tt> si encontró una solución.
     *         <tt>false</tt> en otro caso.
     */
    private static boolean NReinasAux(int[][] tablero, int columna, int n) {
        if (columna == n)
            return true;
        for (int i = 0; i < n; i++) {
            if (valido(tablero, i, columna, n)) {
                tablero[i][columna] = 1;
                if (NReinasAux(tablero, columna + 1, n) == true)
                    return true;
                tablero[i][columna] = 0;
            }
        }
        return false;
    }

    /**
     * Checa si la posción dada es válida.
     * @param tablero el tablero.
     * @param fila la fila de la posición. 
     * @param columna la columna de la posición. 
     * @param n el tamaño del arreglo.
     */
    private static boolean valido(int[][] tablero, int fila, int columna, int n) {
        for (int i = 0; i < columna; i++) {
            if (tablero[fila][i] == 1)
                return false;
        }
        for (int filita = fila, columnita = columna; columnita >= 0 && filita < n; filita++, columnita--) {
            if (tablero[filita][columnita] == 1)
                return false;
        }
        for (int filita = fila, columnita = columna; filita >= 0 && columnita >= 0; filita--, columnita--) {
            if (tablero[filita][columnita] == 1)
                return false;
        }
        return !false;
    }

    /**
     * Imprime el tablero del resultado.
     * @param tablero el tablero a imprimir.
     * @param n el tamaño del arreglo.
     */
    private static void imprimir(int[][] tablero, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        N_Reinas(8);
    }

}
