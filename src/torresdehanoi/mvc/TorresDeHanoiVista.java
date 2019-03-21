package torresdehanoi.mvc;

import com.sun.glass.ui.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Carlos Contreras
 */
public class TorresDeHanoiVista extends JFrame {

    private TorresDeHanoiControlador controlador;

    private JButton btnAñadir, btnIniciar;
    private JComboBox cmbNumeroDiscos;
    private JLabel lbCmbNumeroDiscos;
    private JPanel glassPanelDiscos;
    private Disco[] discos;
    private Timer timer;

    private final int LIMITE_Y = 19;
    int decrementoY = 0;

    public TorresDeHanoiVista() {
        super("Torres de Hanói");
        hazInterfaz();
    }

    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public JComboBox getCmbNumeroDiscos() {
        return cmbNumeroDiscos;
    }

    public void setDiscos(Disco[] discos) {
        this.discos = discos;
    }
    
    
    
    

    public void setControlador(TorresDeHanoiControlador controlador) {
        this.controlador = controlador;
        btnAñadir.addActionListener(controlador);
        btnIniciar.addActionListener(controlador);
    }

    private void hazInterfaz() {
        setSize(850, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);

        // GlassPane donde van los discos
        glassPanelDiscos = (JPanel) getGlassPane();
        glassPanelDiscos.setLayout(null);
        glassPanelDiscos.setVisible(true);

        // Torre 1
        Torre torre1 = new Torre();
        torre1.setBounds(0, -20, 220, 300);
        add(torre1);

        // Torre 2
        Torre torre2 = new Torre();
        torre2.setBounds(220, -20, 220, 300);
        add(torre2);

        // Torre 3
        Torre torre3 = new Torre();
        torre3.setBounds(440, -20, 220, 300);
        add(torre3);

        // TEST: Inicializar Discos
        discos = new Disco[3];
        inicializarDiscos(3);

        // Label Número de Discos
        lbCmbNumeroDiscos = new JLabel("Discos:");
        lbCmbNumeroDiscos.setBounds(710, 60, 150, 20);
        add(lbCmbNumeroDiscos);

        // Botón para añadir discos
        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(710, 140, 90, 30);
         // No llega
//        btnAñadir.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                
//            }
//        });
        add(btnAñadir);
//        btnAñadir.addActionListener();

        // Botón para Iniciar
        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(710, 180, 90, 30);
        btnIniciar.addActionListener(controlador);

        add(btnIniciar);

        // Combo con Número de Discos
        cmbNumeroDiscos = new JComboBox();
        for (int i = 3; i <= 8; cmbNumeroDiscos.addItem(i), i++);
        cmbNumeroDiscos.setBounds(710, 80, 90, 30);
//        cmbNumeroDiscos.setBackground(Color.WHITE);
        add(cmbNumeroDiscos);

    }

    public void inicializarDiscos(int numeroDiscos) {
        Disco disco = new Disco();
        Disco discoAnterior = disco;
        disco.setBounds(25, 215, 170, 15);
        discos[0] = disco; // Primer disco
        for (int i = 1; i < numeroDiscos; i++) {
            disco = new Disco();
            disco.setBounds(discoAnterior.getX() + 10, discoAnterior.getY() - (discoAnterior.getHeight()), discoAnterior.getWidth() - 20, discoAnterior.getHeight());
            // glassPanelDiscos.add(disco);
            discos[i] = disco;
            discoAnterior = disco;
        }

        for (int i = discos.length - 1; i >= 0; i--) {
            glassPanelDiscos.add(discos[i]);
        }
        update(getGraphics());

    }

    public void lanzarVista() {
        setVisible(true);
        btnAñadir.requestFocus();
    }

    public void limpiarDiscos() {
        glassPanelDiscos.removeAll();
    }

    public void sacarDisco() {
        while (decrementoY != 40) {
            discos[2].setBounds(discos[2].getX(), discos[2].getY() - decrementoY, discos[2].getWidth(), discos[2].getHeight());
            decrementoY += 5;
        }
    }

//    public void inicializarDiscos(int numeroDiscos) {
//        Disco disco = new Disco();
//        Disco discoAux;
//        disco.setBounds(20, 10, 45, 20);
//        add(disco);
////        torre1.add(discoInicial);
//        for (int i = 0; i <= numeroDiscos; i++) {
//            discoAux = new Disco();
////            discoAux.setBounds(disco.getX() - , disco.getY(), disco.getWidth(), disco.getHeight());
//            add(disco);
//        }
////        torre1.updateUI();
//    }
//    private void pintarGrafico() {
//        super.paint(g);
//        int desplazamientoX = 0;
//        // 3 torres
//        for (int i = 0; i < 3; i++) {
//            g.setColor(new Color(118, 60, 40));
//            g.fillRect(10 + desplazamientoX, 250, 200, 15);
//
//            g.setColor(Color.BLACK);
//            g.drawRect(10 + desplazamientoX, 250, 200, 15);
//
//            // Palo
//            g.setColor(new Color(166, 94, 46));
//            g.fillRect(110 + desplazamientoX, 50, 5, 200);
//
//            desplazamientoX += 220;
//        }
//        // Se pintan los discos TODO:
//
//        repaint();
//    }
    private static class Torre extends JPanel {

        public Torre() {
            setLayout(null);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(new Color(118, 60, 40));
            g.fillRect(10, 250, 200, 15);

            g.setColor(Color.BLACK);
            g.drawRect(10, 250, 200, 15);

            // Palo
            g.setColor(new Color(166, 94, 46));
//            g.fillRect(110, 50, 5, 200);
            g.fillRect(110, 60, 5, 190);
        }

    }

    public static class Disco extends JPanel {

        public Disco() {
            Random random = new Random();
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            setBackground(new Color(r, g, b));
            setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        }
    }

}
