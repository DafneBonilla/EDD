package Apuestas;

import Apuestas.Estructuras.Lista;

/**
 * Clase para representar un menu.
 * Un servicio tiene un cliente, una lista de gallitos, una lista de dinosaurios
 * y si sigue en funcionamiento.
 */
public class Servicio {

    /* Cliente del servicio. */
    private Cuenta cliente;
    /* Lista de gallitos del servicio. */
    private Lista<Gallito> gallitos;
    /* Lista de dinosaurios del servicio. */
    private Lista<Dinosaurio> dinosaurios;
    /* Si el servicio esta en funcionamiento. */
    private boolean funcionando;

}
