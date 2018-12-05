package betess.business;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Apostador extends Utilizador {
    
    private String nome;
    private double essCoins;
    private Map<Integer, Aposta> apostas;

    public Apostador(String email, String nome, String password, double essCoins) {
        super(email, password);
        this.nome = nome;
        this.essCoins = essCoins;
        this.apostas = new HashMap<>();
    }
    
    public Apostador(Apostador a) {
        super(a.getEmail(), a.getPassword());
        this.nome = a.getNome();
        this.essCoins = a.getEssCoins();
        this.apostas = a.getApostas();
    }

    public Apostador() {
        super();
        this.apostas = new HashMap<>();
    }

    /**
     * Getters e Setters
     * @return 
     */
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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
     * Método addTotalCoins(...).
     * Adiciona ao total de coins o valor em parâmetro..
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
     * Método toString().
     * Retorna uma representação textual do objeto.
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Apostador{ " + "nome=" + this.nome + 
               ", essCoins=" + this.essCoins + ", apostas=" + this.apostas + " }";
    }
    
    /**
     * Método getIdNovaAposta().
     * Retorna o novo ID de uma determinada aposta.
     * 
     * @return Identificador para uma nova aposta.
     */
    public int getIdNovaAposta() {
        return this.apostas.size() + 1;
    }
    
    /**
     * Método addAposta().
     * Adiciona uma nova aposta ao total de apostas.
     * 
     * @param aposta - nova Aposta a ser adicionada.
     */
    public void addAposta(Aposta aposta) {
        aposta.setIdAposta(this.apostas.size() + 1);
        this.apostas.put(aposta.getIdAposta(), aposta);
    }
}
