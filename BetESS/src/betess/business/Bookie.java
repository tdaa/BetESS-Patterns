/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.business;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tiagoalves
 */
public class Bookie extends Utilizador implements Serializable{
    
    private Map<Integer, Map<Integer, Evento>> eventos;
    private Map<Integer, Boolean> eventosInteressados;

    public Bookie() {
        this.eventosInteressados = new HashMap<>();
        this.eventos = new HashMap<>();
    }

    public Bookie(String nome, String email, String password) {
        super(nome, email, password);
        this.eventosInteressados = new HashMap<>();   
        this.eventos = new HashMap<>();
    }

    public Map<Integer, Map<Integer, Evento>> getEventos() {
        /*Map<Integer, Evento> mapEventos = new HashMap<>();
        Map<Integer, Map<Integer,Evento>> evs = new HashMap<>();
        
        this.eventos.entrySet().forEach(m -> {
            m.getValue().entrySet().forEach(m2 -> {
                mapEventos.put(m2.getKey(), m2.getValue());
            });
            evs.put(m.getKey(), mapEventos);
        });
        */
        return this.eventos;
    }

    public void setEventos(Map<Integer, Map<Integer, Evento>> eventos) {
        Map<Integer, Evento> mapEventos = new HashMap<>();
        eventos.entrySet().forEach(m -> {
            m.getValue().entrySet().forEach(m2 -> {
                mapEventos.put(m2.getKey(), m2.getValue());
            });
            this.eventos.put(m.getKey(), mapEventos);
        });
    }
    
     public Map<Integer, Boolean> getEventosInteressados() {
        Map<Integer, Boolean> evs = new HashMap<>();
        
        this.eventosInteressados.entrySet().forEach(m -> {
            evs.put(m.getKey(), m.getValue());
        });
        
        return evs;
    }

    public void setEventosInteressados(Map<Integer, Boolean> eventosInteressados) {
        eventosInteressados.entrySet().forEach(m -> {
            this.eventosInteressados.put(m.getKey(), m.getValue());
        });
    }
    
    /**
     *
     * @param idEvento
     * @return
     */
    public boolean novoEvento(int idEvento){
        this.eventosInteressados.put(idEvento, false);
        return this.eventosInteressados.containsKey(idEvento);
    }
    
    /**
     *
     * @param evs
     * @param a
     */
    public void verificaEventos(Collection<Evento> evs, Aposta a){
        int idEvento, idAposta;
        Map<Integer, Evento> map;
        for(Evento e: evs){
            idEvento = e.getIdEvento();
            if(this.eventosInteressados.containsKey(idEvento)){
                this.eventosInteressados.put(idEvento, true);
                idAposta = a.getIdAposta();
                if(this.eventos.containsKey(a.getIdAposta())){
                    this.eventos.get(idAposta).put(idEvento, e);
                }
                else{
                    map = new HashMap<>();
                    map.put(idEvento, e);
                    this.eventos.put(idAposta, map);
                }
            }
        }
    }
    
    /**
     *
     * @return
     */
    public boolean size(){
        if (this.eventos.size() > 0) return true;
        return false;
    }
    
    /**
     *
     */
    public void limpaVistos(){
        this.eventos.clear();
        this.eventos = new HashMap<>();
    }
    
    /**
     *
     */
    public void limpaInteressados(){
        this.eventosInteressados.clear();
        this.eventosInteressados = new HashMap<>();
    }
    
    public void removeEvento(int id){
        this.eventosInteressados.remove(id);
    }
    
    
}
