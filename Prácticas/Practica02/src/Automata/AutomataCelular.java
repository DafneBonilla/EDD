package edd.src.Automata;
import java.awt.Color;

/**
 * Interfaz que describe las características más importantes que deben de considerarse en un autómata.
 */
public interface AutomataCelular {

    /**
     * Obtiene el estado en el que se encuentra el autómata. Este método tiene que ir ligado al método evoluciona()
     * ya que cada vez que esto sucude el autómata se debe de encontrar en el siguiente estado que es el número de
     * evolución.
     * @return número de veces que se ha llamado al método evoluciona.
     */
    public int getEvolucion();

    /*
     * Método que reinicia el número de evolución.
     */
    public void reiniciaEvolucion();

    /**
     * Obtiene una representación matricial de los valores que contiene este autómata en un cierto estado de tiempo.
     * @return
     */
    public int[][] getAutomata();

    /**
     * Obtiene los colores que representa los esatados del autómata.
     * @return
     */
    public Color[] getColores();

    /**
     * Método que se invoca para que el autómata evolucione. Esto nos da a entender de que si el autómata se encontraba 
     * en un estado t, ahora tendrá que estar en el estado t + 1.
     */
    public void evoluciona();

    public int[][] getAutomata2();
}