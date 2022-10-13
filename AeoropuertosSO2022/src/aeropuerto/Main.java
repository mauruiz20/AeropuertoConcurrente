package aeropuerto;

/**
 *
 * @author Mauricio Ruiz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Torres t = new Torres(2);
        for (int i = 1; i <= 5; i++) {
            Aviones av = new Aviones(t, i);
            
            av.start();
        }
    }
    
    
}
