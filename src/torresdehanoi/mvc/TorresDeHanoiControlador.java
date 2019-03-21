package torresdehanoi.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import torresdehanoi.mvc.TorresDeHanoiModelo.Movimiento;
import torresdehanoi.mvc.TorresDeHanoiVista.Disco;

/**
 *
 * @author Carlos Contreras
 */
public class TorresDeHanoiControlador implements ActionListener {

    private TorresDeHanoiVista vista;
    private TorresDeHanoiModelo modelo;
    private boolean juegoCompletado;

    public TorresDeHanoiControlador(TorresDeHanoiVista vista, TorresDeHanoiModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    private void reiniciarJuego(int numeroDiscos) {
        vista.limpiarDiscos();
        vista.inicializarDiscos(numeroDiscos);
    }

    private void iniciarJuego() {
        vista.iniciarTimer();
        Disco[] discos = vista.getDiscos();
        juegoCompletado = false;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        int numeroDiscos = (int) vista.getCmbNumeroDiscos().getSelectedItem();
        if (evt.getSource() == vista.getBtnAñadir()) {
            vista.setDiscos(new Disco[numeroDiscos]);
            reiniciarJuego(numeroDiscos);
            return;
        }
        if (evt.getSource() == vista.getBtnIniciar()) {
            if (vista.getDiscos() == null) {
                vista.alerta();
                return;
            }
            modelo.index = 0;
            modelo.iniciarSimulacion(numeroDiscos, 1, 2, 3);
            iniciarJuego();
            return;
        }
        if (evt.getSource() == vista.getTimer()) {
            if (juegoCompletado) {
                vista.terminarJuego();
                return;
            }
            Movimiento movimiento = modelo.obtenerMovimientos().get(modelo.index);
//            System.out.println("El Disco " + movimiento.getDisco() + " se mueve de la Torre " + movimiento.getTorreInicial() + " a la Torre " + movimiento.getTorreFinal());
            

            // Subir
            if(vista.subirDisco(movimiento)) {
                return;
            }
            if (vista.moverDisco(movimiento)) {
                return;
            }
            if (vista.bajarDisco(movimiento)) {
                return;
            }
            // Bajar
//            vista.bajarDisco(numeroDisco);
            
            if((modelo.index+1) >= modelo.obtenerMovimientos().size()) {
                juegoCompletado = true;
                return;
            }
            modelo.index++;
            return;
        }
    }

}
