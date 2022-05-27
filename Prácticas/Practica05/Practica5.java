/*
Integrantes:
Dafne Bonilla Reyes
José Camilo García Ponce  
*/

public class Practica5 {

    public static void main(String[] args) {

        // Aquí va tu código
        Lista<Integer2> primera = new Lista<>();
        primera.add(new Integer2(59));
        primera.add(new Integer2(12));
        primera.add(new Integer2(1));
        primera.add(new Integer2(3));
        primera.add(new Integer2(42));
        primera.add(new Integer2(7));
        primera.add(new Integer2(9));
        primera.add(new Integer2(11));
        primera.add(new Integer2(13));
        primera.add(new Integer2(101));
        primera.add(new Integer2(15));
        primera.add(new Integer2(17));
        primera.add(new Integer2(19));
        primera.add(new Integer2(21));
        primera.add(new Integer2(23));
        primera.add(new Integer2(25));
        primera.add(new Integer2(27));
        primera.add(new Integer2(29));
        primera.add(new Integer2(31));
        primera.add(new Integer2(33));
        primera.add(new Integer2(0));
        MonticuloMinimo<Integer2> m = new MonticuloMinimo<>();
        MonticuloMinimo<Integer2> m2 = new MonticuloMinimo<>(primera);
        System.out.println("Lista original: " + primera);
        Lista<Integer2> segunda = m.heapSort(primera);
        System.out.println("Lista ordenada: " + segunda);
        System.out.println("Morty: " + m2);
        m2.empty();
        m2.add(new Integer2(59));
        m2.add(new Integer2(12));
        m2.add(new Integer2(1));
        m2.add(new Integer2(3));
        m2.add(new Integer2(42));
        m2.add(new Integer2(7));
        m2.add(new Integer2(9));
        m2.add(new Integer2(11));
        m2.add(new Integer2(13));
        m2.add(new Integer2(101));
        m2.add(new Integer2(15));
        m2.add(new Integer2(17));
        m2.add(new Integer2(19));
        m2.add(new Integer2(21));
        m2.add(new Integer2(23));
        m2.add(new Integer2(25));
        m2.add(new Integer2(27));
        m2.add(new Integer2(29));
        m2.add(new Integer2(31));
        m2.add(new Integer2(33));
        m2.add(new Integer2(0));
        System.out.println("Morty2: " + m2);
        Integer2[] arreglo = new Integer2[primera.size()];
        for (int i = 0; i < primera.size(); i++) {
            arreglo[i] = primera.buscarIndice(i);
        }
        Integer2[] arreglo2 = new Integer2[segunda.size()];
        for (int i = 0; i < segunda.size(); i++) {
            arreglo2[i] = segunda.buscarIndice(i);
        }
        System.out.println(m.esMontMin(arreglo));
        System.out.println(m.esMontMin(arreglo2));
        Integer2[] arreglo3 = new Integer2[6];
        arreglo3[0] = new Integer2(0);
        arreglo3[1] = new Integer2(1);
        arreglo3[2] = new Integer2(7);
        arreglo3[3] = new Integer2(11);
        arreglo3[4] = new Integer2(3);
        arreglo3[5] = new Integer2(12);
        System.out.println(m.esMontMin(arreglo3));
        Integer2[] arreglo4 = new Integer2[9];
        arreglo4[0] = new Integer2(101);
        arreglo4[1] = new Integer2(15);
        arreglo4[2] = new Integer2(13);
        arreglo4[3] = new Integer2(1);
        arreglo4[4] = new Integer2(9);
        arreglo4[5] = new Integer2(7);
        arreglo4[6] = new Integer2(3);
        System.out.println("Arreglo maximo: ");
        for (int i = 0; i < arreglo4.length; i++) {
            System.out.print(arreglo4[i] + " ");
        }
        System.out.println();
        System.out.println(m.esMontMin(arreglo4));
        m.montMaxMontMin(arreglo4);
        System.out.println();
        System.out.println("Arreglo minimo: ");
        for (int i = 0; i < arreglo4.length; i++) {
            System.out.print(arreglo4[i] + " ");
        }
        System.out.println();
        System.out.println(m.esMontMin(arreglo4));

        // Aquí va tu código
        Lista<Integer2> primera2 = new Lista<>();
        primera2.add(new Integer2(59));
        primera2.add(new Integer2(12));
        primera2.add(new Integer2(1));
        primera2.add(new Integer2(3));
        primera2.add(new Integer2(42));
        primera2.add(new Integer2(7));
        primera2.add(new Integer2(9));
        primera2.add(new Integer2(11));
        primera2.add(new Integer2(13));
        primera2.add(new Integer2(101));
        primera2.add(new Integer2(15));
        primera2.add(new Integer2(17));
        primera2.add(new Integer2(19));
        primera2.add(new Integer2(21));
        primera2.add(new Integer2(23));
        primera2.add(new Integer2(25));
        primera2.add(new Integer2(27));
        primera2.add(new Integer2(29));
        primera2.add(new Integer2(31));
        primera2.add(new Integer2(33));
        primera2.add(new Integer2(0));
        MonticuloMaximo<Integer2> m3 = new MonticuloMaximo<>();
        MonticuloMaximo<Integer2> m4 = new MonticuloMaximo<>(primera2);
        System.out.println("Lista original: " + primera2);
        Lista<Integer2> segunda2 = m3.heapSort(primera2);
        System.out.println("Lista ordenada: " + segunda2);
        System.out.println("Morty4: " + m4);
        m4.empty();
        m4.add(new Integer2(59));
        m4.add(new Integer2(12));
        m4.add(new Integer2(1));
        m4.add(new Integer2(3));
        m4.add(new Integer2(42));
        m4.add(new Integer2(7));
        m4.add(new Integer2(9));
        m4.add(new Integer2(11));
        m4.add(new Integer2(13));
        m4.add(new Integer2(101));
        m4.add(new Integer2(15));
        m4.add(new Integer2(17));
        m4.add(new Integer2(19));
        m4.add(new Integer2(21));
        m4.add(new Integer2(23));
        m4.add(new Integer2(25));
        m4.add(new Integer2(27));
        m4.add(new Integer2(29));
        m4.add(new Integer2(31));
        m4.add(new Integer2(33));
        m4.add(new Integer2(0));
        System.out.println("Morty6: " + m4);
        Integer2[] arreglo5 = new Integer2[primera2.size()];
        for (int i = 0; i < primera2.size(); i++) {
            arreglo5[i] = primera2.buscarIndice(i);
        }
        Integer2[] arreglo6 = new Integer2[segunda2.size()];
        for (int i = 0; i < segunda2.size(); i++) {
            arreglo6[i] = segunda2.buscarIndice(i);
        }
        System.out.println(m3.esMontMax(arreglo5));
        System.out.println(m3.esMontMax(arreglo6));
        Integer2[] arreglo7 = new Integer2[6];
        arreglo7[0] = new Integer2(12);
        arreglo7[1] = new Integer2(11);
        arreglo7[2] = new Integer2(7);
        arreglo7[3] = new Integer2(3);
        arreglo7[4] = new Integer2(-15);
        arreglo7[5] = new Integer2(0);
        System.out.println(m3.esMontMax(arreglo7));
        Integer2[] arreglo8 = new Integer2[9];
        arreglo8[0] = new Integer2(0);
        arreglo8[1] = new Integer2(1);
        arreglo8[2] = new Integer2(7);
        arreglo8[3] = new Integer2(11);
        arreglo8[4] = new Integer2(3);
        arreglo8[5] = new Integer2(9);
        arreglo8[6] = new Integer2(12);
        System.out.println("Arreglo minimo: ");
        for (int i = 0; i < arreglo8.length; i++) {
            System.out.print(arreglo8[i] + " ");
        }
        System.out.println();
        System.out.println(m3.esMontMax(arreglo8));
        m3.montMinMontMax(arreglo8);
        System.out.println();
        System.out.println("Arreglo maximo: ");
        for (int i = 0; i < arreglo8.length; i++) {
            System.out.print(arreglo8[i] + " ");
        }
        System.out.println();
        System.out.println(m3.esMontMax(arreglo8));
    }
}