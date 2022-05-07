/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

public class Practica4 {

    public static void main(String[] args) {

        // Aqui va tu codigo
        ArbolAVL<Integer> arbol = new ArbolAVL<Integer>();
        arbol.add(10);
        arbol.add(5);
        arbol.add(1);
        System.out.println(arbol.search(arbol.getRaiz(), 5));
        arbol.delete(5);
    }

}
