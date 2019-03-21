package torresdehanoi.mvc;

/**
 *
 * @author Carlos Contreras
 */
public class Main {

    public static void main(String[] args) {
        TorresDeHanoiVista vista = new TorresDeHanoiVista();
        TorresDeHanoiModelo modelo = new TorresDeHanoiModelo();
        TorresDeHanoiControlador controlador = new TorresDeHanoiControlador(vista, modelo);
        
        vista.setControlador(controlador);
        vista.lanzarVista();
//        controlador.iniciarJuego(3);
    }
    
}
