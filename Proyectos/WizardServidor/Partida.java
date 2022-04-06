package WizardServidor;

import WizardServidor.Estructuras.Lista;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Clase para representar una partida.
 */
public class Partida {
    
    /* Lista de jugadores. */
    private Lista<Jugador> jugadores;
    /* Número de rondas. */
    private int numRondas;
    /* Mazo principal del juego. */
    private Baraja mazo;
    /* Ver si el juego sigue. */
    private Boolean sigue;
    /* Manera de escribir en el archivo. */
    private BufferedWriter out;
    /* Seed de random. */
    private long seed;

    /**
     * Define el estado inicial de una partida.
     * @param numJugadores el número de jugadores.
     * @param archivo el archivo a escribir.
     */
    public Partida(int numJugadores, String archivo, Lista<Jugador> jugadores, BufferedWriter out) {
        this.jugadores = jugadores;
        switch (numJugadores) {
            case 3:
                numRondas = 20;
                break;
            case 4:
                numRondas = 15;
                break;
            case 5:
                numRondas = 12;
                break;
            case 6:
                numRondas = 10;
                break;
            default:
                numRondas = 10;
                break;
        }
        seed = System.currentTimeMillis();
        mazo = new Baraja(seed);
        sigue = true;
        this.out = out;
    }

    /**
     * Comienza la partida.
     */
    public void iniciar() {
        try {
            enviarMensajeTodos("La partida va a empezar, todos listos :)");
            enviarMensajeTodos("La seed del juego es "+seed);
            for (int i = 1; i <= numRondas; i++) {
                Ronda actual = new Ronda(jugadores, i, mazo, out);
                actual.iniciar();  
                if (i != numRondas) {
                    seguir();
                } 
                if (!sigue) {
                    break;
                }
            }
        } catch (JugadorInactivo ji) {
            try {
                resultadosDesconecta();
            } catch (IOException ioe) {
                resultadosErrorEscribir();
            }
        } catch (IOException ioe) {
            System.out.println("Error al guardar en el archivo, terminando el juego...");
            resultadosErrorEscribir();
        }
        try {
            finalizar();
        } catch (IOException ioe) {
            System.out.println("No se pudo cerrar el archivo, abortando la ejecución...");
            System.exit(0);
        }
    }
    
    /**
     * Muestra los resultados de la partida y termina.
     * @throws IOException si no se pudo cerrar correctamente.
     */
    private void finalizar() throws IOException {
        resultados();
        out.close();
    }

    /**
     * Imprime un mensaje a todos los usuarios y guarda 
     * el mensaje en el archivo.
     * @param mensaje el mensaje a imprimir y agregar.
     */
    private void enviarMensajeTodos(String mensaje) throws IOException {
        System.out.println(mensaje + "\n");
        out.write(mensaje);
        out.newLine();
        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            Jugador jug = iterator.next();
            enviarMensajeJugador(jug, mensaje);
        }
    }

    private void enviarMensajeJugador(Jugador jugador, String mensaje) throws JugadorInactivo {
        jugador.hablarJugador(mensaje);
    }

    /**
     * Muesta los resultados de la partida.
     * @throws IOException si no ...
     */
    private void resultados() throws IOException {
        String resultados = "Ahora se anunciará al ganador del juego...\n\n";
        resultados += ganador();
        enviarMensajeTodos(resultados);
    }

    /**
     * Cuando un jugador se deconecta.
     * @throws IOException si no se pudo haer.
     */
    private void resultadosDesconecta() throws IOException {
        String resultados = "Un jugador se pudo haber desconectado, terminando el juego...\n";
        resultados += "Ahora se anunciará al ganador del juego...\n\n";
        resultados += ganador();
        enviarMensajeTodosDesconecta(resultados);
        System.exit(0);
    }

    /**
     * Cuando un jugador se deconecta, avisamos a todos los resultados.
     * el mensaje en el archivo.
     * @param mensaje el mensaje a imprimir y agregar.
     */
    private void enviarMensajeTodosDesconecta(String mensaje) throws IOException {
        System.out.println(mensaje + "\n");
        out.write(mensaje);
        out.newLine();
        for (Jugador jugador : jugadores) {
            try {
                enviarMensajeJugador(jugador, mensaje);
            } catch (JugadorInactivo e) {
                continue;
            }
        }
    }
    
    /**
     * Hubo en error al escribir.
     */
    private void resultadosErrorEscribir() {
        String resultados = "Un jugador se pudo haber desconectado, terminando el juego...\n";
        resultados += "Ahora se anunciará al ganador del juego...\n\n";
        resultados += ganador();
        enviarMensajeTodosErrorEscribir(resultados);
        System.exit(0);
    }
    
    private void enviarMensajeTodosErrorEscribir(String mensaje) {
        System.out.println(mensaje + "\n");
        for (Jugador jugador : jugadores) {
            try {
                enviarMensajeJugador(jugador, mensaje);
            } catch (JugadorInactivo e) {
                continue;
            }
        }
    }

    /**
     * Calcula quien fue el ganador de la partida.
     * @return una cadena con los datos del ganador.
     */
    private String ganador() {
        return superior(jugadores);
    }

    /**
     * Ayuda a calcular el ganador.
     * @param arreglo un arreglo con los datos de los jugadores.
     * @return una cadena con el ganador.
     */
    private String superior(Lista<Jugador> lista) {
        String winner = "Hubo un empate entre los Jugadores ";
        int posicion = mayor(lista);
        Jugador ganadorsito = lista.buscarIndice(posicion);
        int punt = ganadorsito.getPuntuacion();
        Lista<Jugador> resto = lista.clone();
        resto.delete2(posicion);
        boolean empate = false;
        for (Jugador jug : resto) {
            if (jug.getPuntuacion() == punt) {
                empate = true;
                winner += jug.getNombre() + ", ";
            }
        }
        if (empate) {
            winner = winner.substring(0, winner.length() - 2);
            winner += " y " + ganadorsito.getNombre();
            return winner + " todos con " + punt  + " puntos.\n";
        }
        return "El ganador es el Jugador " + ganadorsito.getNombre() + " con " + ganadorsito.getPuntuacion() + " puntos.\n";
    }

    /**
     * Buscar la puntacion más alta de los jugadores.
     * @param arreglo un arreglo con los datos de los jugadores.
     * @return la posicion del jugador con mayor puntacion.
     */
    private int mayor(Lista<Jugador> lista) {
        int respuesta = 0;
        int puntuacion = lista.buscarIndice(0).getPuntuacion();
        for (Jugador jug : lista) {
            if (jug.getPuntuacion() >= puntuacion) {
                respuesta = lista.indexOf(jug);
            }
        }
        return respuesta;
    }

    /**
     * Saber si el juego va a seguir o se detendrá.
     */
    private void seguir() throws JugadorInactivo {
        Lista<String> si = new Lista<>();
        Lista<String> no = new Lista<>();
        for (Jugador jugador : jugadores) {
            pedirRespuesta(jugador, si, no);
        }
        if (si.size() < no.size()) {
            sigue = false;
            return;
        } else if (si.size() == no.size()) {
            for (Jugador jugador : jugadores) {
                enviarMensajeJugador(jugador, "Hubo un empate, se realizará una nueva votación");
            }
            seguir();
            return;
        }
    }

    private void pedirRespuesta(Jugador jugador, Lista<String> si, Lista<String> no) throws JugadorInactivo {
        enviarMensajeJugador(jugador, "¿Quieres seguir jugando? s/n");
        String respuesta = jugador.leerJugador();
        switch (respuesta) {
        case "s":
            si.add("si");
            break;
        case "n":
            no.add("no");
            break;
        default:
            enviarMensajeJugador(jugador, "Respuesta inválida.");
            pedirRespuesta(jugador, si, no);
            break;
        }
    }
}