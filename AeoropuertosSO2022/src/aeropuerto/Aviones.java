package aeropuerto;

/**
 *
 * @author Mauricio Ruiz
 * @author Benjamin Cascales
 * @author Fernando Tuquina
 */
public class Aviones extends Thread {

    private Torres torre;
    private int pista;
    private int avion;
    private int estado;
    private String color;
    public static String[] colores = {"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", 
                                        "\u001B[37m", "\u001B[93m","\u001B[0m"};

    public Aviones(Torres t, int avion) {
        this.torre = t;
        this.pista = -1; // No tiene una pista inicial
        this.avion = avion;
        this.color = colores[avion % 9];
        this.estado = (int) (Math.random() * 2); // estado = 0: quiere aterrizar - estado = 1: quiere despegar
    }

    public void run() {
        
        while (true) {
            
            this.pista = this.torre.elegirPista(); // El avión quiere elegir una pista
            
            if (this.estado == 0) {
                this.aterrizar();
                this.estado = 1;
            } else {      
                this.despegar();
                this.estado = 0;
            }          
            
            this.torre.liberarPista(pista); // Se libera la pista que se ocupó
            
            try {
                sleep((long) (Math.random() * 5000)); // Demora entre 0 y 5 segundos
            } catch (InterruptedException ex) {
            }
        }
    }

    public void aterrizar() {
        
        System.out.println(this.color + "Avión " + this.avion + " está aterrizando en la pista " + (this.pista + 1));
        
        try {
            sleep((long) (Math.random() * 4000)); // Demora entre 0 y 4 segundos
        } catch (InterruptedException ex) {
        }
        
        System.out.println(this.color + "Avión " + avion + " terminó de aterrizar, se liberó la pista " + (this.pista + 1));
        
    }

    public void despegar() {
        
        System.out.println(this.color + "Avión " + this.avion + " está despegando en la pista " + (this.pista + 1));
        
        try {
            sleep((long) (Math.random() * 4000)); // Demora entre 0 y 4 segundos
        } catch (InterruptedException ex) {
        }
        
        System.out.println(this.color + "Avión " + avion + " terminó de despegar, se liberó la pista " + (this.pista + 1));
        
    }
}
