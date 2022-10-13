package aeropuerto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio Ruiz
 */
public class Aviones extends Thread {

    private Torres torre;
    private int pista;
    private int avion;

    public Aviones(Torres t, int avion) {
        this.torre = t;
        this.pista = -1;
        this.avion = avion;
    }

    public void run() {
        while (true) {
            this.pista = this.torre.entrarPista();
            
            int random = (int) (Math.random() * 2);
            if (random == 1) {
                this.aterrizar();
            } else {
                this.despegar();
            }
            
            System.out.println("Avión " + avion + " terminó de aterrizar");
            
            this.torre.desocuparPista(pista);
            
            try {
                sleep((long) (Math.random() * 5000)); // Demora entre 0 y 5 segundos
            } catch (InterruptedException ex) {
            }
            
            // Despegar

            this.pista = this.torre.entrarPista();
            
            if (random == 1) {
                this.despegar();
            } else {
                this.aterrizar();
            }
            
            System.out.println("Avión " + avion + " terminó de despegar");
            
            this.torre.desocuparPista(pista);
            
            try {
                sleep((long) (Math.random() * 7000)); // Demora entre 0 y 7 segundos
            } catch (InterruptedException ex) {
            }
        }
    }

    public void aterrizar() {
        System.out.println("Avión " + this.avion + " esta aterrizando en la pista " + this.pista);
        try {
            sleep((long) (Math.random() * 4000)); // Demora entre 0 y 4 segundos
        } catch (InterruptedException ex) {
        }
    }

    public void despegar() {
        System.out.println("Avión " + this.avion + " esta despegando en la pista " + this.pista);
        try {
            sleep((long) (Math.random() * 4000)); // Demora entre 0 y 4 segundos
        } catch (InterruptedException ex) {
        }
    }
}
