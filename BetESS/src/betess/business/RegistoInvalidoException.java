package betess.business;

/**
 *
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class RegistoInvalidoException extends Exception {
    
    public RegistoInvalidoException() {
        super();
    }
    
    public RegistoInvalidoException(String m) {
        super(m);
    }
}