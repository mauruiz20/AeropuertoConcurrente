package aeropuerto;

/**
 *
 * @author Mauricio Ruiz
 * @author Benjamin Cascales
 * @author Fernando Tuquina
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Torres t = new Torres(2);
        
        for (int i = 1; i <= 30; i++) {
            Aviones av = new Aviones(t, i);
            
            av.start();
        }
    }
    
    
}
