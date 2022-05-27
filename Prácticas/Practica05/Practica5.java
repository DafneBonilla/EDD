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
        Integer2[] arreglo4 = new Integer2[6];
        arreglo4[0] = new Integer2(101);
        arreglo4[1] = new Integer2(15);
        arreglo4[2] = new Integer2(13);
        arreglo4[3] = new Integer2(11);
        arreglo4[4] = new Integer2(9);
        arreglo4[5] = new Integer2(7);
        System.out.println("Arreglo maximo: ");
        for (int i = 0; i < arreglo4.length; i++) {
            System.out.print(arreglo4[i] + " ");
        }
        m.montMaxMontMin(arreglo3);
        System.out.println();
        System.out.println("Arreglo minimo: ");
        for (int i = 0; i < arreglo4.length; i++) {
            System.out.print(arreglo4[i] + " ");
        }
    }
}