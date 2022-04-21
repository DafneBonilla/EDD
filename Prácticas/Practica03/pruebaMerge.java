package edd.src.Estructuras;

import java.util.Comparator;

public class pruebaMerge {
    

    public static void main(String[] args) {
        Lista<Integer> lista1 = new Lista<Integer>();
        Lista<Integer> lista2 = new Lista<Integer>();
        lista1.add(3);
        lista1.add(2);
        lista1.add(1);
        lista1.add(6);
        lista1.add(7);

        lista2.add(4);
        lista2.add(5);
        lista2.add(8);
        System.out.println(lista1.mergeSort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }));

        Lista<Pokemon> lista3 = new Lista<Pokemon>();
        lista3.add(new Pokemon("Bulbasaur", "Planta", 1, 110));
        lista3.add(new Pokemon("Charmander", "Fuego", 2, 100));
        lista3.add(new Pokemon("Pikachu", "Electrico", 3, 100));
        String tipo = "Electrico";
        System.out.println(tipo.compareTo("Electrico"));
        System.out.println(lista3.mergeSort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                if (o1.tipo.compareTo("Electrico") == 0 ) {
                    System.out.println("entro");
                   return -1;
                }else if (o2.tipo.compareTo("Electrico") == 0) {
                    System.out.println("entro");
                    return 1;
                } else if (o1.nivel < o2.nivel) {
                    return -1;
                }
                return 1;
            }
        }));
        
    }

}
