package betess.business;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe Bookie.
 * É nesta classe que é guardada toda a informação relativa a um Bookie.
 * 
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Bookie extends Utilizador implements Serializable {
    
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
    public boolean novoEvento(int idEvento) {
        this.eventosInteressados.put(idEvento, false);

        return this.eventosInteressados.containsKey(idEvento);
    }
    
    /**
     *
     * @param evs
     * @param a
     */
    public void verificaEventos(Collection<Evento> evs, Aposta a) {
        int idEvento, idAposta;
        Map<Integer, Evento> map;

        for (Evento e: evs) {
            idEvento = e.getIdEvento();
            
            if (this.eventosInteressados.containsKey(idEvento)) {
                this.eventosInteressados.put(idEvento, true);
                idAposta = a.getIdAposta();
                
		if (this.eventos.containsKey(a.getIdAposta())) {
                    this.eventos.get(idAposta).put(idEvento, e);
                } else {
                    map = new HashMap<>();
                    map.put(idEvento, e);
                    this.eventos.put(idAposta, map);
                }
            }
        }
    }
    
    /**
     * Método existsNotifications().
     * Verifica se existem notificações pendentes.
     * 
     * @return - valor booleano que indica a veracidade do método.
     */
    public boolean existsNotifications() {
        return (this.eventos.size() > 0);
    }
    
    /**
     *
     */
    public void limpaVistos() {
        this.eventos.clear();
        this.eventos = new HashMap<>();
    }
    
    /**
     *
     */
    public void limpaInteressados() {
        this.eventosInteressados.clear();
        this.eventosInteressados = new HashMap<>();
    }
    
    public void removeEvento(int id) {
        this.eventosInteressados.remove(id);
    } 
}
