package Clases;

public class Practica1 {

    /**
     * Agrega un elemento de manera ordenada a un ejemplar ordenado de la clase Lista.
     * @param lista la lista ordenada.
     * @param nuevo el elemento a agregar.
     * @return lista ordenada con el elemento agregado.
     */
    public static Lista<Integer> AgregaOrdenado(Lista<Integer> lista, int nuevo) {
        /*Nodo explorador = lista.cabeza;
        int contador = 0;
        while (explorador != null) { 
            if (explorador.elemento > nuevo)
                lista.insert(contador, nuevo);
            explorador = explorador.siguiente;
            contador++;
        }
        */
        return lista;
        // Aquí va su código.
    }

    /**
     * Dadas dos listas obtener la union de las dos sin repetidos y no importa orden.
     * @param lista1 la primera lista y la que sera mdificada.
     * @param lista2 la segunda lista.
     */
    public static void Union(Lista<Integer> lista1,Lista<Integer> lista2) {
        Lista<Integer> copia = lista1.clone();
    }

    /**
     * Dadas dos listas obtener la interseccion de las dos sin repetidos y no importa orden.
     * @param lista la primera lista y la que sera mdificada.
     * @param lista2 la segunda lista.
     */
    public static void Interseccion(Lista<Integer> lista,Lista<Integer> lista2) {
        Lista<Integer> copia = lista.clone();
    }


    public static void main(String[] args) {
        Lista<Integer> primera = new Lista<Integer>();
        Lista<Integer> segunda = new Lista<Integer>();
        Lista<Integer> tercera = new Lista<Integer>();
        
        /* Tests toString. */
        for (int i = 0; i <= 5; i++) {
            primera.add(i);
        }
        String test = "1 -> 2 -> 3 -> 4 -> 5";
        if (!primera.toString().equals(test)) {
            System.out.println("1 El toString no funciona!");
        }
        primera = new Lista<Integer>();
        if (!primera.toString().equals("")) {
            System.out.println("2 El toString no funciona!");
        }
         
        /* Tests Reverse. */
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.agregaInicio(i);
        }
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El reverse no funciona!");    
        }
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("2 El reverse no funciona!");
        }

        /* Tests Append. */
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            segunda.add(i);
        }
        primera.append(primera.clone());        
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El Append no funciona!");
        }

        /* Tests IndexOf. */
        if (primera.indexOf(0) != 0) {
            System.out.println("1 El IndexOf no funciona!");
        }
        if (primera.indexOf(1) != 1) {
            System.out.println("2 El IndexOf no funciona!");
        }
        if (primera.indexOf(10) != 10) {
            System.out.println("3 El IndexOf no funciona!");
        }

        /* Tests Insert. */
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i); 
        }
        for (int i = 0; i <= 4; i++) {
            segunda.add(i);
        }
        segunda.add(6);
        for (int i = 5; i <= 10; i++) {
            segunda.add(i);
        }
        primera.insert(5, 6);
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El insert no funciona!");
        }

        /* Tests Mezcla Alternada */
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                primera.add(i);
            }   
        }
        primera.add(11);
        for (int i = 0; i <= 10; i++) {
            if (i % 2 != 0) {
                segunda.add(i);
            }
        }
        for (int i = 0; i <= 11; i++) {
                tercera.add(i);
        }
        primera.mezclaAlternada(segunda);
        if (!primera.toString().equals(tercera.toString())) {
            System.out.println("1 la mezclaAlternada no funciona!");
        }

        /* Tests Agrega Ordenado. */
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
        }
        for (int i = 0; i <= 9; i++) {
            segunda.add(i);
        }
        segunda.add(9);
        segunda.add(10);
        tercera = AgregaOrdenado(primera,9);
        if (!tercera.toString().equals(segunda.toString())) {
            System.out.println("1 el agregaOrdenado no funciona!");
        }
        
        /* Tests Union. */
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Union(primera, segunda);
        if (!(primera.contains(1) && primera.contains(2) && primera.contains(3) && primera.size() == 3)) {
            System.out.println("1 La union no funciona!");
        }
        
        /* Tests interseccion. */
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Interseccion(primera, segunda);
        if (!(primera.contains(2) && primera.size() == 1)) {
            System.out.println("1 La intersección no funciona!");
        }
    }   
}
