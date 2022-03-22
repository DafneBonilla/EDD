package edd.src.Automata;



/*
*@Author Macias Gomez Jorge
*
*
*/
public class VentanaPrincipal extends javax.swing.JFrame {

    private AutomataCelular automata;
    private boolean stop;
    static private VentanaPrincipal vp;
    static private Thread t;

    /**
     * Clase para mostar una ventana grafica con una malla para simular una automata celular.
     * @author Manuel
     */
    public VentanaPrincipal(AutomataCelular automata) {
        this.automata = automata;
        this.stop = false;
        initComponents();
        labelEvolucion.setText("Evolucion: " + automata.getEvolucion());
        imagen.pinta(automata.getAutomata(), automata.getColores());
        setVisible(true);
    }

    
    /**
     * Metodo main para ejecutar la aplicacion. 
     * @param args
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /**
         * CAMBIA POR EL AUTOMATA A PROBAR. En La linea de abajo
         * Examples.
         * AutomataCelular a = new JuegoVida();
         * AutomataCelular a = new Mondrian();
         * AutomataCelular a = new juego();
         */
       //AutomataCelular a = new JuegoVida();
       AutomataCelular a = new Mondrian();
       //AutomataCelular a=new juego();
       vp = new VentanaPrincipal(a);
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {

        imagen = new Imagen();
        botonIniciar = new javax.swing.JButton();
        botonDetener = new javax.swing.JButton();
        labelEvolucion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Automata Celular by Jorge Macias");
        setResizable(false);

        javax.swing.GroupLayout imagenLayout = new javax.swing.GroupLayout(imagen);
        imagen.setLayout(imagenLayout);
        imagenLayout.setHorizontalGroup(
            imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        imagenLayout.setVerticalGroup(
            imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        botonIniciar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonIniciar.setText("Iniciar");
        botonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIniciarActionPerformed(evt);
            }
        });

        botonDetener.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        botonDetener.setText("Reiniciar");
        botonDetener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDetenerActionPerformed(evt);
            }
        });

        labelEvolucion.setFont(new java.awt.Font("Tahoma", 1, 14));
        labelEvolucion.setText("Evolucion: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelEvolucion)
                        .addGap(252, 252, 252)
                        .addComponent(botonIniciar)
                        .addGap(57, 57, 57)
                        .addComponent(botonDetener)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEvolucion)
                    .addComponent(botonIniciar)
                    .addComponent(botonDetener))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }

    private void botonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIniciarActionPerformed
        botonIniciar.setEnabled(true);
        botonDetener.setEnabled(true);
        stop = false;	
        t = new Thread(new Evolucionador());
        t.start();
    }

    private void botonDetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDetenerActionPerformed
        automata.reiniciaEvolucion();
        stop = true;
        t.stop();
        botonDetener.setEnabled(false);
        botonIniciar.setEnabled(true);
        imagen.pinta(automata.getAutomata(), automata.getColores());
        botonDetener.setEnabled(true);
        //stop = false;	
        //t = new Thread(new Evolucionador());
        //t.start();

        //AutomataCelular a = new JuegoVida();
        //vp = new VentanaPrincipal(a);
    }

    private class Evolucionador implements Runnable {

        @Override
        public void run() {
            while (!stop) {
                automata.evoluciona();
                labelEvolucion.setText("Evolucionando: "+automata.getEvolucion());
                imagen.pinta(automata.getAutomata2(),automata.getColores());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {}
            }
        }
    }
    
    private javax.swing.JButton botonDetener;
    private javax.swing.JButton botonIniciar;
    private Imagen imagen;
    private javax.swing.JLabel labelEvolucion;
}
