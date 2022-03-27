package edd.src.Automata;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Clase que representa una imagen en donde se va a pintar el autómata celular. 
 */
public class Imagen extends JPanel {

    /**
     * Tamanio de la celda para cada célula. 
     */
    public static final int sizeCell = 20;

    /**
     * Número de celdas que contendrá la malla. Este valor se aplica tanto para altura como para anchura.
     * Es decir la malla tendrá numCellsxnumCell número de celdas.
     */
    public static final int numCells = 40;

    /**
     * Tamanio real que debe de tener la malla considerando una línea divisoria entre las celdas, y el taminio de cada una. 
     */
    private static final int tam = numCells * sizeCell + numCells + 1;

    /**
     * Objeto en donde se va a pintar.
     */
    private BufferedImage imagen;

    /**
     * Constructor de la clase.
     */
    public Imagen() {
        setSize(tam, tam);
        imagen = new BufferedImage(tam, tam, BufferedImage.TYPE_INT_RGB);
        createGrid();
    }

    /**
     * Método que dibuja las lineas en la imagen en color gris, para dar la apariencia de que es un entramado.
     */
    private void createGrid() {
        Graphics2D gc = imagen.createGraphics();
        //Rectángulo Blanco POR VALOR DE PENCIL DEFAULT.
        gc.fillRect(0,0,tam,tam);
        gc.setColor(Color.GRAY);
        for (int i = 0; i <= numCells; i++) {
            gc.drawLine((sizeCell * i) + i, 0, (sizeCell * i) + i, tam);
            gc.drawLine(0, (sizeCell * i) + i, tam, (sizeCell * i) + i);
        }        
    }

    /**
     * Método que mapea la matriz del autómata a su representación gráfica. Este método considera
     * el tamanio de cada celda para poderla pintar. Se le pasa como parámetro un arreglo de colores, 
     * para que cada vez que vea un valor en la matriz, éste lo busque en el arreglo de colores y pinte
     * la celda de ese color.
     * Es necesario que la longitud del arreglo sea igual al máximo de los valores que se encuentran en la matriz.
     * @param matriz Representa la malla del autómata con sus posibles estados. 
     * @param colores Se mapean a cada estado de la matriz para pintarlo de su respectivo color.
     */
    public void pinta(int[][] matriz, Color[] colores) {
	    Graphics2D gc = imagen.createGraphics();
        for (int i = 1; i < matriz.length + 1; i++) {
            for (int j = 1; j < matriz.length + 1; j++) {
                int aux1i = (sizeCell * (i - 1) + i);
                int aux1j = (sizeCell * (j - 1) + j);
                switch (matriz [i - 1][j - 1]) {
                    case 0:
                        gc.setColor(new Color(116,4,191)); 
                        break;
                    case 1:
                        gc.setColor(new Color(5,151,242)); 
                        break;
                    case 2:
                        gc.setColor(new Color(73,217,7)); 
                        break;
                    case 3:
                        gc.setColor(new Color(234,242,5)); 
                        break;
                    case 4:
                        gc.setColor(new Color(166,33,3)); 
                        break;
                    case 5: 
                        gc.setColor(new Color(255,255,255)); 
                        break;   
                }
                gc.fillRect(aux1i,aux1j,sizeCell,sizeCell);
            }
        }       
        updateUI();
    }

    @Override
    public void paint(Graphics g) {
        try {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(imagen, null, 0, 0);
        } catch (NullPointerException npe) {}
    }
}