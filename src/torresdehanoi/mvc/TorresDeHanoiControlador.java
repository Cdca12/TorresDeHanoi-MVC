package torresdehanoi.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import torresdehanoi.mvc.TorresDeHanoiVista.Disco;

/**
 *
 * @author Carlos Contreras
 */
public class TorresDeHanoiControlador implements ActionListener {

    private TorresDeHanoiVista vista;
    private TorresDeHanoiModelo modelo;

    public TorresDeHanoiControlador(TorresDeHanoiVista vista, TorresDeHanoiModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void iniciarJuego(int numeroDiscos) {
        vista.limpiarDiscos();
        vista.inicializarDiscos(numeroDiscos);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == vista.getBtnAñadir()) {
            int numeroDiscos = (int) vista.getCmbNumeroDiscos().getSelectedItem();
            vista.setDiscos(new Disco[numeroDiscos]);
            iniciarJuego(numeroDiscos);
            return;
        }
        if (evt.getSource() == vista.getBtnIniciar()) {
            System.out.println("Botón Iniciar");
            return;
        }
        System.out.println("Otros casos");
    }

}
