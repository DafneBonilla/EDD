public class pruebamonti {

    public static void main(String[] args) {
        Pokemon poke1 = new Pokemon("Bulbasaur", "Planta", 1, 110);
        Pokemon poke2 = new Pokemon("Charmander", "Fuego", 2, 100);
        Pokemon poke3 = new Pokemon("Pikachu", "Electrico", 3, 100);
        Pokemon poke4 = new Pokemon("Squirtle", "Agua", 4, 100);
        Pokemon poke5 = new Pokemon("Pidgey", "Volador", 5, 100);

        MonticuloMinimo<Pokemon> monticulo = new MonticuloMinimo<Pokemon>();
        // Agregamos elementos
        monticulo.add(poke3);
        monticulo.add(poke2);
        monticulo.add(poke1);
        monticulo.add(poke4);
        monticulo.add(poke5);

        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        // Eliminamos el elemento con el mínimo valor
        monticulo.delete();
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        // Eliminamos el elemento con el mínimo valor
        monticulo.delete();
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        System.out.println(monticulo.contains(poke5));

        monticulo.empty();
        System.out.println("########");

        System.out.println(monticulo);
        System.out.println(monticulo.size());

    }

}
