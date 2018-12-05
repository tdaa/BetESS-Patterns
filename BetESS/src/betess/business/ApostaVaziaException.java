package betess.business;

/**
 *
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class ApostaVaziaException extends Exception {
    
    public ApostaVaziaException() {
        super();
    }
    
    public ApostaVaziaException(String m) {
        super(m);
    }
}