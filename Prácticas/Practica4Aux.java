import java.util.ArrayList;
import java.util.List;

public class Practica4Aux {

    // Problema: Permutaciones
    public static void permutuaciones(String cadenita) {
        if (cadenita.length() <= 0 || cadenita == null)
            return;
        permutacionAux(cadenita, 0, cadenita.length() - 1);
    }

    public static void permutacionAux(String cadenita, int inicio, int ultimo) {
        if (inicio == ultimo)
            System.out.println(cadenita);
        for (int i = inicio; i <= ultimo; i++) {
            cadenita = cambiar(cadenita, inicio, i);
            permutacionAux(cadenita, inicio + 1, ultimo);
            cadenita = cambiar(cadenita, inicio, i);
        }
    }

    public static String cambiar(String cadena, int primero, int segundo) {
        char[] palabra = cadena.toCharArray();
        char aux = palabra[primero];
        palabra[primero] = palabra[segundo];
        palabra[segundo] = aux;
        String regresar = String.valueOf(palabra);
        return regresar;
    }

    // Problema: N-Reinas
    public static void NReinas(int n) {
        if (n <= 0)
            return;
        int[][] tablero = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tablero[i][j] = 0;
            }
        }
        if (n == 2 || n == 3 || NReinasAux(tablero, 0, n) == false) {
            System.out.println("No hay solución :(");
        } else {
            imprimir(tablero, n);
        }
    }

    public static boolean NReinasAux(int[][] tablero, int columna, int n) {
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

    public static boolean valido(int[][] tablero, int fila, int columna, int n) {
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

    public static void imprimir(int[][] tablero, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Problema: Primos
    public static List<Integer> PrimosMayores(int menor, int mayor) {
        List<Integer> primos = new ArrayList<Integer>();
        for (int i = menor + 1; i <= mayor; i++) {
            if (esPrimo(i))
                primos.add(i);
        }
        return primos;
    }

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

    private static void sumaRara(int n, int p, int s) {
        List<Integer> primos = PrimosMayores(p, s);
        List<Integer> respuestas = new ArrayList<Integer>();
        if (sumaRaraAux(primos, s, n, respuestas) == true) {
            respuestas.sort((a, b) -> a - b);
            int cont = 1;
            for (Integer i : respuestas) {
                if (cont <= n) {
                    System.out.print(i + " ");
                    cont++;
                }
            }
        } else {
            System.out.println("No hay solucion");
        }
    }

    private static boolean sumaRaraAux(List<Integer> primos, int s, int n, List<Integer> respuestas) {
        if (n <= respuestas.size()) {
            if (checar(respuestas, s)) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < primos.size(); i++) {
            respuestas.add(primos.get(i));
            if (sumaRaraAux(primos, s, n, respuestas)) {
                return true;
            }
            respuestas.remove(respuestas.size() - 1);
        }
        return false;
    }

    private static boolean checar(List<Integer> respuestas, int s) {
        int total = 0;
        for (Integer i : respuestas) {
            total += i;
        }
        if (total == s) {
            return true;
        }
        return false;
    }

    private static void sumaRaraSinRepetir(int n, int p, int s) {
        List<Integer> primos = PrimosMayores(p, s);
        List<Integer> respuestas = new ArrayList<Integer>();
        if (sumaRaraSinRepetirAux(primos, s, n, respuestas) == true) {
            respuestas.sort((a, b) -> a - b);
            int cont = 1;
            for (Integer i : respuestas) {
                if (cont <= n) {
                    System.out.print(i + " ");
                    cont++;
                }
            }
        } else {
            System.out.println("No hay solución");
        }
    }

    private static boolean sumaRaraSinRepetirAux(List<Integer> primos, int s, int n, List<Integer> respuestas) {
        if (n <= respuestas.size()) {
            if (checar(respuestas, s)) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < primos.size(); i++) {
            respuestas.add(primos.get(i));
            primos.remove(i);
            if (sumaRaraSinRepetirAux(primos, s, n, respuestas)) {
                return true;
            }
            primos.add(respuestas.get(respuestas.size() - 1));
            respuestas.remove(respuestas.size() - 1);
        }
        return false;
    }

    // Problema: Búsqueda raíz

    public static float BusquedaSqrt(int n) {
        double respuesta = BusquedaSqrtAux(0, n, n, 0.0);
        /*
         * double increment = 0.1;
         * for (int i = 0; i < precision; i++) {
         * while (ans * ans <= number) {
         * ans += increment;
         * }
         * 
         * // loop terminates when ans * ans > number
         * ans = ans - increment;
         * increment = increment / 10;
         * }
         * return (float) ans;
         */
        return (float) respuesta;
    }

    private static double BusquedaSqrtAux(int menor, int mayor, int n, double respuesta) {
        if (menor <= mayor) {
            int mitad = (menor + mayor) / 2;
            if (mitad * mitad == n) {
                return mitad;
            }
            if (mitad * mitad < n) {
                return BusquedaSqrtAux((mitad + 1), mayor, n, mitad);
            } else {
                return BusquedaSqrtAux(menor, (mitad - 1), n, respuesta);
            }
        }
        return respuesta;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            System.out.println("la raiz de " + i + " es " + BusquedaSqrt(i));
        }
    }
}