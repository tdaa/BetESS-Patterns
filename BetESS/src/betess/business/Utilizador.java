package betess.business;

import java.io.Serializable;

/**
 *
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Utilizador implements Serializable {
    
    private String email;
    private String password;
    
    public Utilizador() { }

    public Utilizador(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Getters e Setters.
     * @return 
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
