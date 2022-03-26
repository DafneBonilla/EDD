/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/
package edd.src.Estructuras;

public class Practica2 {
    
    /**
     * Metodo auxiliar para mover "discos" entre "postes"
     * @param primero el primer "poste".
     * @param segundo el segundo "poste".
     */
    private static void moverDiscos(Pila<Integer> Primero, Pila<Integer> Segundo, Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino,
                                    String nombre1, String nombre2) {
        if (Primero.isEmpty()) {
            int wowi = Segundo.pop();
            Primero.push(wowi);
            System.out.println("~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~");
            System.out.println("Moviendo el disco "+wowi+" del poste "+nombre2+" al poste "+nombre1);
            System.out.println("Origen\t\t"+origen.toStringHanoi());
            System.out.println("Auxiliar\t"+auxiliar.toStringHanoi());
            System.out.println("Destino\t\t"+destino.toStringHanoi());
        } else if (Segundo.isEmpty()) {
            int wowi = Primero.pop();
            Segundo.push(wowi);
            System.out.println("~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~");
            System.out.println("Moviendo el disco "+wowi+" del poste "+nombre1+" al poste "+nombre2);
            System.out.println("Origen\t\t"+origen.toStringHanoi());
            System.out.println("Auxiliar\t"+auxiliar.toStringHanoi());
            System.out.println("Destino\t\t"+destino.toStringHanoi());
        } else if (Primero.peek() > Segundo.peek()) {
            int wowi = Segundo.pop();
            Primero.push(wowi);
            System.out.println("~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~");
            System.out.println("Moviendo el disco "+wowi+" del poste "+nombre2+" al poste "+nombre1);
            System.out.println("Origen\t\t"+origen.toStringHanoi());
            System.out.println("Auxiliar\t"+auxiliar.toString());
            System.out.println("Destino\t\t"+destino.toStringHanoi());
        } else {
            int wowi = Primero.pop();
            Segundo.push(wowi);
            System.out.println("~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~▫~~~");
            System.out.println("Moviendo el disco "+wowi+" del poste "+nombre1+" al poste "+nombre2);
            System.out.println("Origen\t\t"+origen.toStringHanoi());
            System.out.println("Auxiliar\t"+auxiliar.toStringHanoi());
            System.out.println("Destino\t\t"+destino.toStringHanoi());
        }
    }

    /**
     * Metodo que "resuelve" las torres de Hanoi, sin usar
     * recursion, porque no tengo amor propio CorgiDerp
     * Metodo sacado con mucho esfuerzo y mucha ayuda de tutoriales de YouTube
     * @param cantidadDiscos el numero de discos a mover.
     * @param origen el primer "poste" (donde estan los discos al inicio).
     * @param auxiliar el segundo "poste" (el auxiliar).
     * @param destino el tercer "poste" (donde estaran los discos al final).
     */
    public static void torresHanoi(int cantidadDiscos, Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){
        int pasitos = (int)(Math.pow(2, cantidadDiscos))-1;
        for (int i = 0; i < cantidadDiscos; i++) {
            origen.push(cantidadDiscos-i);
        }
        System.out.println("Origen\t\t"+origen.toStringHanoi());
        System.out.println("Auxiliar\t"+auxiliar.toStringHanoi());
        System.out.println("Destino\t\t"+destino.toStringHanoi());
        if (cantidadDiscos%2 == 1) {
            for (int i = 1; i <= pasitos; i++) {
                switch (i%3) {
                    case 1:
                        moverDiscos(origen, destino, origen, auxiliar, destino, "Origen", "Destino");
                        break;
                    case 2:
                        moverDiscos(origen, auxiliar, origen, auxiliar, destino, "Origen", "Auxiliar");
                        break;
                        case 0:
                        moverDiscos(destino, auxiliar, origen, auxiliar, destino, "Destino", "Auxiliar");
                        break;
                    default:
                        break;
                }
            }
        } else {
            for (int i = 1; i <= pasitos; i++) {
                switch (i%3) {
                    case 1:
                        moverDiscos(origen, auxiliar, origen, auxiliar, destino, "Origen", "Auxiliar");
                        break;
                    case 2:
                        moverDiscos(origen, destino, origen, auxiliar, destino, "Origen", "Destino");
                        break;
                        case 0:
                        moverDiscos(auxiliar, destino, origen, auxiliar, destino, "Auxiliar", "Destino");
                        break;
                    default:
                        break;
                }
            }
        }
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

        // "Prueba" de Cola             Funciona
        /*
        Cola<Integer> numeritos = new Cola<>();
        for (int i = 0; i < 5; i++) {
            numeritos.push(i);
        } 
        Cola<Integer> num1 = numeritos.clone();
        for (int i = 0; i < 5; i++) {
            System.out.println(numeritos.pop());
        }
        Cola<Integer> num2 = numeritos.clone();
        for (int i = 0; i < 5; i++) {
            System.out.println(num1.pop());
        }
        System.out.println(num1.size());
        */

        // "Prueba" de Pila (clone())   NO Funciona
        
        Pila<Integer> numeritos = new Pila<>();
        for (int i = 0; i < 5; i++) {
            numeritos.push(i);
        } 
        Pila<Integer> num1 = numeritos.clone();
        for (int i = 0; i < 5; i++) {
            System.out.println(numeritos.pop());
        }
        Pila<Integer> num2 = numeritos.clone();
        for (int i = 0; i < 5; i++) {
            System.out.println(num1.pop());
        }
        System.out.println(num1.size());
        

        // "Prueba" de Hanoi            Funciona
        /*
        Pila<Integer> origen = new Pila<>();
        Pila<Integer> auxiliar = new Pila<>();
        Pila<Integer> destino = new Pila<>();
        torresHanoi(5, origen, auxiliar, destino);
        */

        // "Prueba" de Binario          Funciona
        /*
        binarioColas(15);
        */

    }

}
