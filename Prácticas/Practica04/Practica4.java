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
        arbol.add(7);
        arbol.add(15);
        arbol.add(40);
        arbol.delete(7);
        arbol.delete(30);
        arbol.delete(40);
        System.out.println(arbol.toStringBonito());
        System.out.println(arbol);
        System.out.println();
        ArbolAVL<Integer> arbol2 = new ArbolAVL<Integer>();
        arbol2.add(37);
        arbol2.add(8);
        arbol2.add(23);
        arbol2.add(27);
        arbol2.add(24);
        arbol2.add(58);
        arbol2.add(26);
        arbol2.add(46);
        arbol2.add(30);
        arbol2.add(49);
        arbol2.add(60);
        arbol2.add(25);
        arbol2.add(22);
        arbol2.add(34);
        arbol2.delete(24);
        arbol2.add(50);
        arbol2.add(47);
        arbol2.delete(49);
        arbol2.add(59);
        arbol2.add(31);
        arbol2.add(52);
        arbol2.delete(34);
        arbol2.delete(26);
        arbol2.delete(46);
        arbol2.delete(59);
        arbol2.delete(23);
        arbol2.add(28);
        System.out.println(arbol2.toStringBonito());
        System.out.println(arbol2);
        System.out.println(arbol2.size());
        System.out.println();

        ArbolAVL<String> arbol3 = new ArbolAVL<String>();
        arbol3.add("pato");
        arbol3.add("perro");
        arbol3.add("gato");
        arbol3.add("caballo");
        arbol3.add("cocodrilo");
        arbol3.delete("caballo");
        arbol3.add("pingüino");
        arbol3.add("camello");
        arbol3.add("oveja");
        arbol3.add("vaca");
        arbol3.add("gallina");
        arbol3.add("cerdo");
        arbol3.add("cabra");
        arbol3.add("camaleón");
        arbol3.add("jirafa");
        arbol3.add("elefante");
        arbol3.add("hipopótamo");
        arbol3.add("mono");
        arbol3.delete("pato");
        arbol3.delete("gato");
        arbol3.delete("camello");
        arbol3.delete("oveja");
        arbol3.add("aguila");
        arbol3.add("araña");
        arbol3.add("ballena");
        arbol3.add("anguila");
        arbol3.add("alce");
        arbol3.add("abeja");
        arbol3.delete("vaca");
        System.out.println(arbol3.toStringBonito());
        System.out.println(arbol3);
        System.out.println(arbol3.size());
        System.out.println();
    }
}