package betess.business;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe Aposta.
 * É nesta classe que é guardada toda a informação relativa a uma Aposta.
 * 
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Aposta implements Serializable {
    
    private int idAposta;
    private Map<Integer, Evento> eventos;
    private Map<Integer, Double> oddsApostadas;
    private double valor;
    private boolean isTerminada;
    
    /**
     * Construtores da classe Aposta.
     * Construtor Vazio;
     * Construtor pelo ID;
     * Construtor por Objeto;
     * Construtor por parâmetros.
     */
    
    public Aposta() {
        this.eventos = new HashMap<>();
        this.oddsApostadas = new HashMap<>();
        this.valor = 0;
        this.isTerminada = false;
    }

    public Aposta(int id) {
        this.idAposta = id;
        this.eventos = new HashMap<>();
        this.oddsApostadas = new HashMap<>();
        this.valor = 0;
        this.isTerminada = false;
    }
    
    public Aposta(Aposta a) {
        this.idAposta = a.getIdAposta();
        this.valor = a.getValor();
        this.eventos = a.getEventos();
        this.oddsApostadas = a.getOdds();
        this.isTerminada = a.getIsTerminada();
    }
    
    public Aposta(int idAposta, Map<Integer, Evento> eventos, 
                  Map<Integer, Double> odds, double valor, boolean isTerminada) 
    {
        this.idAposta = idAposta;
        this.setEventos(eventos);
        this.setOdds(odds);
        this.valor = valor;
        this.isTerminada = isTerminada;
    }

    /**
     * Getters e Setters
     * @return 
     */
    
    public int getIdAposta() {
        return this.idAposta;
    }

    public void setIdAposta(int idAposta) {
        this.idAposta = idAposta;
    }

    public Map<Integer, Evento> getEventos() {
        Map<Integer, Evento> evs = new HashMap<>();
        
        this.eventos.entrySet().forEach(m -> {
            evs.put(m.getKey(), m.getValue());
        });
        
        return evs;
    }
    
    public void setEventos(Map<Integer, Evento> evs) {
        evs.entrySet().forEach(m -> {
            this.eventos.put(m.getKey(), m.getValue());
        });
    }
    
    public Map<Integer, Double> getOdds() {
        Map<Integer, Double> odds = new HashMap<>();
        
        this.oddsApostadas.entrySet().forEach(m -> {
            odds.put(m.getKey(), m.getValue());
        });
        
        return odds;
    }
    
    public void setOdds(Map<Integer, Double> odds) {
        odds.entrySet().forEach(m -> {
            this.oddsApostadas.put(m.getKey(), m.getValue());
        });
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public double getTotalOdds() {
        return this.oddsApostadas.values()
                .stream()
                .mapToDouble(d -> d)
                .sum();
    }
    
    public boolean getIsTerminada() {
        return this.isTerminada;
    }
    
    public void setTerminada(boolean t) {
        this.isTerminada = t;
    }
    
    /**
     * Método getGanhoTotal().
     * 
     * @return ganho total possível de uma Aposta.
     */
    public double getGanhoTotal() {
        return (this.valor * this.getTotalOdds());
    }
    
    /**
     * Método getResultadoApostador(...).
     *
     * @param e
     * @return resultado apostado num determinado Evento.
     */
    public String getResultadoApostado(Evento e) {
        double odd = this.oddsApostadas.get(e.getIdEvento());
        
        if (e.getOddUm() == odd)
            return e.getEquipaUm();
        
        if (e.getOddDois() == odd)
            return e.getEquipaDois();
        
        else 
            return "EMPATE";
    }

    /**
     * Método toString().
     * 
     * @return representação textual do objeto.
     */
    @Override
    public String toString() {
        return "Aposta{" + "idAposta=" + this.idAposta + 
               ", eventos=" + this.eventos + ", valor=" + this.valor + '}';
    }
    
    /**
     * Método clone().
     * 
     * @return cópia exata do objeto.
     */
    @Override
    public Aposta clone() {
        return new Aposta(this);
    }
    
    /**
     * Método addEventoToAposta(...).
     * Adiciona evento a uma aposta.
     * 
     * @param e - Evento a adicionar à Aposta
     * @param odd - Odd previamente selecionada no Evento.
     */
    public void addEventoToAposta(Evento e, double odd) {
        if (!this.eventos.containsKey(e.getIdEvento())) {
            this.eventos.put(e.getIdEvento(), e);
            this.oddsApostadas.put(e.getIdEvento(), odd);
        }
    }
    
    /**
     * Método remEventoFromAposta(...).
     * Remove evento de uma aposta.
     * 
     * @param e - Evento a remover da respetiva Aposta.
     */
    public void remEventoFromAposta(Evento e) {
        this.eventos.remove(e.getIdEvento(), e);
        this.oddsApostadas.remove(e.getIdEvento());
    }
}
