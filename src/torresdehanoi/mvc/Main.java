package torresdehanoi.mvc;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Carlos Contreras
 */
public class Main {

    public static void main(String[] args) {
        añadirTema();
        TorresDeHanoiVista vista = new TorresDeHanoiVista();
        TorresDeHanoiModelo modelo = new TorresDeHanoiModelo();
        TorresDeHanoiControlador controlador = new TorresDeHanoiControlador(vista, modelo);

        vista.setControlador(controlador);
        vista.lanzarVista();
    }

    public static void añadirTema() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
