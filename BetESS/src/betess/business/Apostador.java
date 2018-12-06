package betess.business;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe Apostador - Generalização de Utilizador.
 * É nesta classe que é guardada toda a informação relativa a uma Apostador.
 * 
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Apostador extends Utilizador {
    
    private double essCoins;
    private Map<Integer, Aposta> apostas;
    
    /**
     * Construtores da classe Apostador.
     * Construtor Vazio;
     * Construtor por Objeto;
     * Construtor por parâmetros.
     */
    
    public Apostador() {
        super();
        this.apostas = new HashMap<>();
    }
    
    public Apostador(Apostador a) {
        super(a.getNome(), a.getEmail(), a.getPassword());
        this.essCoins = a.getEssCoins();
        this.apostas = a.getApostas();
    }

    
    public Apostador(String email, String nome, String password, double essCoins) {
        super(nome, email, password);
        this.essCoins = essCoins;
        this.apostas = new HashMap<>();
    }
  
    /**
     * Getters e Setters
     * @return 
     */

    public double getEssCoins() {
        return this.essCoins;
    }

    public void setEssCoins(double essCoins) {
        this.essCoins = essCoins;
    }

    public Map<Integer, Aposta> getApostas() {
        Map<Integer, Aposta> aps = new HashMap<>();
        
        this.apostas.entrySet().forEach(m -> {
            aps.put(m.getKey(), m.getValue());
        });
        
        return aps;
    }

    public void setApostas(Map<Integer, Aposta> aps) {
        aps.entrySet().forEach(m -> {
            this.apostas.put(m.getKey(), m.getValue());
        });
    }
    
    /**
     * Método getIdNovaAposta().
     * 
     * @return novo ID para uma nova Aposta.
     */
    public int getIdNovaAposta() {
        return this.apostas.size() + 1;
    }
    
    /**
     * Método addTotalCoins(...).
     * Adiciona ao total de coins o valor em parâmetro.
     * 
     * @param coins - número de coins a serem adicionadas.
     */
    public void addTotalCoins(double coins) {
        this.essCoins += coins;
    }
    
    /**
     * Método substractTotalCoins(...).
     * Substraí ao total de coins o valor em parâmetro.
     * 
     * @param coins - número de coins a serem subtraídas.
     */
    public void subtractTotalCoins(double coins) {
        this.essCoins -= coins;
    }

    /**
     * Método addAposta(...).
     * Adiciona uma nova aposta ao total de apostas.
     * 
     * @param aposta - nova Aposta a ser adicionada.
     */
    public void addAposta(Aposta aposta) {
        aposta.setIdAposta(this.apostas.size() + 1);
        this.apostas.put(aposta.getIdAposta(), aposta);
    }
    
    /**
     * Método toString().
     * 
     * @return representação textual do objeto.
     */
    @Override
    public String toString() {
        return "Apostador{ " + "nome=" + this.getNome() + ", email= " + this.getEmail() +
               ", essCoins=" + this.essCoins + ", apostas=" + this.apostas + " }";
    }
}
