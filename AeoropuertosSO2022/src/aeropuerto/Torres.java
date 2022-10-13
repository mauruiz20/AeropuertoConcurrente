package aeropuerto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio Ruiz
 */
public class Torres {
    
    private boolean[] pistas; // true: pista libre ; false: pista ocupada
        
    public Torres(int numPistas) {
        this.pistas = new boolean[numPistas];
        for (int i = 0; i < pistas.length; i++) {
            pistas[i] = true;
        }
    }
    
    public boolean getPistaEstado(int i) {
        return pistas[i];
    }
    
    public synchronized int entrarPista () {
        int pistaLibre = -1;
        
        for (int i=0; i < pistas.length; i++) {
            if (pistas[i] == true) {
                pistas[i] = false;
                pistaLibre = i;
                break;
            }
        }
        
        if (pistaLibre == -1) {
            try {
                wait();
                pistaLibre = entrarPista();
            } catch (InterruptedException ex) {
                Logger.getLogger(Torres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return pistaLibre;
    }
    
    public synchronized void desocuparPista (int pista) {        
        pistas[pista] = true;
        
        notifyAll(); // Le aviso a todos lo que estaban esperando a que sigan.
    }
}
