package aeropuerto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio Ruiz
 * @author Benjamin Cascales
 * @author Fernando Tuquina
 */
public class Torres {
    
    private boolean[] pistas; // true: pista libre - false: pista ocupada
        
    public Torres(int numPistas) {
        this.pistas = new boolean[numPistas];
        for (int i = 0; i < pistas.length; i++) {
            pistas[i] = true; // Inicialmente todas las pistas están libres
        }
    }
    
    public synchronized int elegirPista () {
        
        int pistaLibre = -1; // bandera para saber si hay una pista libre (-1: no hay pistas libres)
        
        // Recorro todas las pistas para ver si hay una libre
        for (int i=0; i < pistas.length; i++) {
            if (pistas[i]) { // Si hay una pista libre:
                pistas[i] = false; // La ocupo
                pistaLibre = i;
                break;
            }
        }
        
        if (pistaLibre == -1) { // Si no hay pistas libres entonces espero hasta que se libere una
            try {
                
                wait();
                pistaLibre = elegirPista(); // Llamada recursiva para comprobar de vuelta cual pista se liberó
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Torres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return pistaLibre; // Devuelvo la pista que se ocupó
    }
    
    public synchronized void liberarPista (int pista) { 
        
        pistas[pista] = true;
        
        notify(); // Avisa al primero de la cola wait a que despierte.
    }
}
