package torresdehanoi.mvc;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import torresdehanoi.mvc.TorresDeHanoiModelo.Movimiento;

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
    private Torre[] torres;
    private Timer timer;
    private boolean estaBajando;

    public TorresDeHanoiVista() {
        super("Torres de Hanói");
        hazInterfaz();
        estaBajando = false;
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

    public Disco[] getDiscos() {
        return discos;
    }

    public Timer getTimer() {
        return timer;
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

        torres = new Torre[3];

        // Torre 1
        torres[0] = new Torre();
        torres[0].setBounds(0, -20, 220, 300);
        add(torres[0]);

        // Torre 2
        torres[1] = new Torre();
        torres[1].setBounds(220, -20, 220, 300);
        add(torres[1]);

        // Torre 3
        torres[2] = new Torre();
        torres[2].setBounds(440, -20, 220, 300);
        add(torres[2]);

        // Label Número de Discos
        lbCmbNumeroDiscos = new JLabel("Discos:");
        lbCmbNumeroDiscos.setBounds(710, 60, 150, 20);
        add(lbCmbNumeroDiscos);

        // Botón para añadir discos
        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(710, 140, 90, 30);
        add(btnAñadir);

        // Botón para Iniciar
        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(710, 180, 90, 30);
        add(btnIniciar);

        // Combo con Número de Discos
        cmbNumeroDiscos = new JComboBox();
        for (int i = 3; i <= 8; cmbNumeroDiscos.addItem(i), i++);
        cmbNumeroDiscos.setBounds(710, 80, 90, 30);
        cmbNumeroDiscos.setBackground(Color.WHITE);
        add(cmbNumeroDiscos);

    }

    public void inicializarDiscos(int numeroDiscos) {
        Disco disco = new Disco();
        Disco discoAnterior = disco;
        disco.setBounds(25, 230, 175, 15);
        discos[0] = disco; // Primer disco
        torres[0].discosActuales = numeroDiscos;
        for (int i = numeroDiscos - 1; i >= 0; i--) {
            disco = new Disco();
            disco.setBounds(discoAnterior.getX() + 10, discoAnterior.getY() - (discoAnterior.getHeight()), discoAnterior.getWidth() - 20, discoAnterior.getHeight());
            discos[i] = disco;
            discoAnterior = disco;
        }
        for (int i = 0; i < discos.length; glassPanelDiscos.add(discos[i]), i++);
        update(getGraphics());
    }

    public void iniciarTimer() {
        timer = new Timer(20, controlador);
        timer.start();
    }
    
    public void pausarTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void alerta() {
        JOptionPane.showMessageDialog(this, "Favor de insertar discos primero");
    }

    public void terminarJuego() {
        JOptionPane.showMessageDialog(this, "¡Juego Completado!");
        timer.stop();
    }

    public void lanzarVista() {
        setVisible(true);
        btnAñadir.requestFocus();
    }

    public void limpiarDiscos() {
        glassPanelDiscos.removeAll();
        for (int i = 0; i < torres.length; torres[i].discosActuales = 0, i++);
        pausarTimer();
    }

    public boolean subirDisco(Movimiento movimiento) {
        Disco discoAMover = discos[movimiento.getDisco() - 1];
        if (discoAMover.getY() == 10 || estaBajando) {
            return false;
        }
        discoAMover.setBounds(discoAMover.getX(), discoAMover.getY() - 5, discoAMover.getWidth(), discoAMover.getHeight());
        return true;
    }

    public boolean bajarDisco(Movimiento movimiento) {
        Disco discoAMover = discos[movimiento.getDisco() - 1];
        int altura = 215 - discoAMover.getHeight() * (torres[movimiento.getTorreFinal() - 1].discosActuales);
        if (discoAMover.getY() >= altura) {
            estaBajando = false;
            torres[movimiento.getTorreInicial() - 1].discosActuales--;
            torres[movimiento.getTorreFinal() - 1].discosActuales++;
            return false;
        }
        estaBajando = true;
        discoAMover.setBounds(discoAMover.getX(), discoAMover.getY() + 5, discoAMover.getWidth(), discoAMover.getHeight());
        return true;

    }

    public boolean moverDisco(Movimiento movimiento) {
        Disco discoAMover = discos[movimiento.getDisco() - 1];
        int espacio = (10 * (discos.length - movimiento.getDisco())) + 30;
        // Ha llegado al destino
        if (movimiento.getTorreFinal() == 1 && discoAMover.getX() == torres[0].getX() + espacio
                || movimiento.getTorreFinal() == 2 && discoAMover.getX() == torres[1].getX() + espacio
                || movimiento.getTorreFinal() == 3 && discoAMover.getX() == torres[2].getX() + espacio) { // Ha llegado al destino de Torre 1
            return false;
        }
        // Mover
        if (movimiento.getTorreInicial() < movimiento.getTorreFinal()) { // Se tiene que mover de izquierda a derecha
            discoAMover.setBounds(discoAMover.getX() + 5, discoAMover.getY(), discoAMover.getWidth(), discoAMover.getHeight());
            return true;
        }
        // Se tiene que mover de derecha a izquierda
        discoAMover.setBounds(discoAMover.getX() - 5, discoAMover.getY(), discoAMover.getWidth(), discoAMover.getHeight());
        return true;
    }

    private static class Torre extends JPanel {

        public int discosActuales;

        public Torre() {
            setLayout(null);
            this.discosActuales = 0;
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
