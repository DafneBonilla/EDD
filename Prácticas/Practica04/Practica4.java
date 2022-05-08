/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

public class Practica4 {

    public static void main(String[] args) {

        // Aquí va tu código
        ArbolAVL<Integer> arbol = new ArbolAVL<Integer>();
        arbol.add(10);
        arbol.add(20);
        arbol.add(30);
        arbol.add(5);
        System.out.println();
        System.out.println(arbol.toStringBonito());
        System.out.println(arbol);
        System.out.println();
        arbol.add(7);
        // arbol.add(15);
        // arbol.add(40);
        System.out.println(arbol.toStringBonito());
        System.out.println(arbol);
    }
}