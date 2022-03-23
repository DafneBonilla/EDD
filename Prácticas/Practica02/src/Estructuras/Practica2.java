/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/
package edd.src.Estructuras;

public class Practica2 {
    
    /**
     * Metodo que "resuelve" las torres de Hanoi, sin usar
     * recursion, porque no tengo amor propio CorgiDerp
     * @param cantidadDiscos el numero de discos a mover.
     * @param origen el primer "poste" (donde estan los discos al inicio).
     * @param auxiliar el segundo "poste" (el auxiliar).
     * @param destino el tercer "poste" (donde estaran los discos al final).
     */
    public static void torresHanoi(int cantidadDiscos, Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){
        // No olvides imprimir cada paso de la solución. 
        // Aquí va su código.
    }

    /**
     * Metodo que imprime los numeros en binario desde 1 hasta
     * el numero dado.
     * @param N hasta que numero.
     */
    public static void binarioColas(int N){
        if (N < 1) {
            return;
        }
        Cola<String> numeritos = new Cola<>();
        numeritos.push("1");
        for (int i = 0; i < N; i++) {
            String actual = numeritos.pop();
            System.out.println(actual);
            numeritos.push(actual+"0");
            numeritos.push(actual+"1");
        }
        // Aquí va su código.
    }

    public static void main(String[] args) {
        // Escribe aqui tu codigo para probar los metodos anteriores. 
        // No olvides comentar tu codigo y escribir tu nombre en el readme.

        // "Prueba" de Cola         Funciona
        /*
        Cola<Integer> numeritos = new Cola<>();
        for (int i = 0; i < 5; i++) {
            numeritos.push(i);
        } 
        for (int i = 0; i < 5; i++) {
            System.out.println(numeritos.pop());
        }
        Cola<Integer> num = numeritos.clone();
        System.out.println(num.size());
        */

        // "Prueba" de Binario      Funciona
        /*
        binarioColas(20);
        */
    }

}
