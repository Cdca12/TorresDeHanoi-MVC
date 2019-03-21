package torresdehanoi.mvc;

import java.util.Vector;

/**
 *
 * @author Carlos Contreras
 */
public class TorresDeHanoiModelo {

    private Vector<Movimiento> movimientos;
    
    public int index = 0;

    public TorresDeHanoiModelo() {
        movimientos = new Vector();
    }
    
    public Vector<Movimiento> obtenerMovimientos() {
        return movimientos;
    }
    
    public void iniciarSimulacion(int numeroDiscos, int torreInicial, int torreCentral, int torreFinal) {
        this.movimientos.removeAllElements();
        hanoi(numeroDiscos, torreInicial, torreCentral, torreFinal);
    }
    
    public void hanoi(int numeroDiscos, int torreInicial, int torreCentral, int torreFinal) {
        if (numeroDiscos == 1) {
            movimientos.add(new Movimiento(1, torreInicial, torreFinal));
            return;
        }
        hanoi(numeroDiscos - 1, torreInicial, torreFinal, torreCentral);
        movimientos.add(new Movimiento(numeroDiscos, torreInicial, torreFinal));
        hanoi(numeroDiscos - 1, torreCentral, torreInicial, torreFinal);
    }

    public static class Movimiento {
        private int disco, torreInicial, torreFinal;
    
        public Movimiento(int disco, int torreInicial, int torreFinal) {
            this.disco = disco;
            this.torreInicial = torreInicial;
            this.torreFinal = torreFinal;
        }

        public int getDisco() {
            return disco;
        }

        public int getTorreInicial() {
            return torreInicial;
        }

        public int getTorreFinal() {
            return torreFinal;
        }
        
        
        
        
    }

}
