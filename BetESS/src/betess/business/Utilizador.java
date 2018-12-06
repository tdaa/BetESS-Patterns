package betess.business;

import java.io.Serializable;

/**
 * Classe Utilizador.
 * É nesta classe que é guardada toda a informação relativa a um Utilizador geral.
 * 
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Utilizador implements Serializable {
    
    private String nome;
    private String email;
    private String password;
    
    /**
     * Construtores da classe Utilizador.
     * Construtor Vazio;
     * Construtor por parâmetros.
     */
    
    public Utilizador() { }

    public Utilizador(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    /**
     * Getters e Setters.
     * @return 
     */
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
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
