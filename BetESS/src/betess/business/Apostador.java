package betess.business;

import java.util.ArrayList;
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
    private ArrayList<Integer> notifications;
    
    /**
     * Construtores da classe Apostador.
     * Construtor Vazio;
     * Construtor por Objeto;
     * Construtor por parâmetros.
     */
    
    public Apostador() {
        super();
        this.apostas = new HashMap<>();
        this.notifications = new ArrayList<>();
    }
    
    public Apostador(Apostador a) {
        super(a.getNome(), a.getEmail(), a.getPassword());
        this.essCoins = a.getEssCoins();
        this.apostas = a.getApostas();
        this.notifications = a.getNotifications();
    }

    
    public Apostador(String email, String nome, String password, double essCoins) {
        super(nome, email, password);
        this.essCoins = essCoins;
        this.apostas = new HashMap<>();
        this.notifications = new ArrayList<>();
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
    
    public ArrayList<Integer> getNotifications() {
        ArrayList<Integer> noti = new ArrayList<>();
        
        this.notifications.forEach(id -> {
            noti.add(id);
        });
        
        return noti;
    }
    
    public void setNotifications(ArrayList<Integer> noti) {
        noti.forEach(id -> {
            this.notifications.add(id);
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
        this.apostas.put(aposta.getIdAposta(), aposta);
    }
    
    /**
     * Método addNotificationApostador(...).
     * Adiciona uma notificação ao respetivo ArrayList.
     * 
     * @param idAposta 
     */
    public void addNotificationApostador(Integer idAposta) {
        this.notifications.add(idAposta);
    }
    
    /**
     * Método existsNotifications().
     * Verifica se existem notificações no ArrayList.
     * 
     * @return - valor booleano que indica a veracidade do método.
     */
    public boolean existsNotifications() {
        return (this.notifications.size() > 0);
    }
    
    /**
     * Método limpaVistos().
     * Limpa todas as notificações já vistas do respetivo ArrayList.
     */
    public void limpaVistos() {
        this.notifications.clear();
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
