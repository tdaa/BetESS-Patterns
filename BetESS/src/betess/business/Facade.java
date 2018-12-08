package betess.business;

import betess.persistence.DataBetESS;
import betess.presentation.MenuApostador;
import com.google.gson.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * Classe Facade - Interage com os dados da aplicação.
 * É aqui que são guardadas as estruturas de dados que contêm toda a
 * informação relativa ao sistema.
 *
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Facade implements Subject, Serializable {

    private String user;
    private Map<String, Apostador> apostadores;
    private Map<Integer, Evento> eventos;
    private Map<String, LinkedList<Aposta>> apostas;
    private Map<Integer, Aposta> apostasGerais;
    private Aposta newAposta;
    private ArrayList<Observer> observers;
    private DataBetESS bd;
    private Bookie bookie;

    /**
     * Construtor Vazio - Facade.
     */
    public Facade() {
        this.user = "";
        this.apostadores = new HashMap<>();
        this.eventos = new HashMap<>();
        this.apostas = new HashMap<>();
        this.apostasGerais = new HashMap<>();
        this.newAposta = new Aposta();
        this.observers = new ArrayList<>();
        this.bd = new DataBetESS();
        this.bookie = new Bookie();
    }

    /**
     * Getters e Setters.
     * @return 
     */
    
    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public Map<String, Apostador> getApostadores() {
        Map<String, Apostador> aps = new HashMap<>();
        
        this.apostadores.entrySet().forEach(m -> {
            aps.put(m.getKey(), m.getValue());
        });
        
        return aps;
    }

    public void setApostadores(Map<String, Apostador> aps) {
        aps.entrySet().forEach(m -> {
            this.apostadores.put(m.getKey(), m.getValue());
        });
    }

    public Map<Integer, Evento> getEventos() {
        Map<Integer, Evento> evs = new HashMap<>();
        
        this.eventos.entrySet().forEach(m -> {
            evs.put(m.getKey(), m.getValue());
        });
        
        return evs;
    }

    public void setEventos(Map<Integer, Evento> eventos) {
        eventos.entrySet().forEach(m -> {
            this.eventos.put(m.getKey(), m.getValue());
        });
    }

    public Map<String, LinkedList<Aposta>> getApostas() {
        Map<String, LinkedList<Aposta>> aps = new HashMap<>();
          
        this.apostas.keySet().forEach(id -> {
            LinkedList<Aposta> lista = new LinkedList<>();
            
            this.apostas.get(id).forEach(a -> {
                lista.addFirst(a);
            });
            
            aps.put(id, lista);
        });
        
        return aps;
    }

    public void setApostas(Map<String, LinkedList<Aposta>> apostas) {      
        apostas.keySet().forEach(id -> {
            LinkedList<Aposta> lista = new LinkedList<>();
            
            apostas.get(id).forEach(a -> {
                lista.addFirst(a);
            });
            
            this.apostas.put(id, lista);
        });
    }
    
    public Map<Integer, Aposta> getApostasgerais() {
        Map<Integer, Aposta> aps = new HashMap<>();
        
        this.apostasGerais.entrySet().forEach(m -> {
            aps.put(m.getKey(), m.getValue());
        });
        
        return aps;
    }

    public void setApostasGerais(Map<Integer, Aposta> apostas) {
        apostas.entrySet().forEach(m -> {
            this.apostasGerais.put(m.getKey(), m.getValue());
        });
    }
    
    public Object getObserverMenuApostador(){
        MenuApostador ma = null;
        
        for(Object obj : this.observers){
            if (obj instanceof MenuApostador)
                ma = (MenuApostador) obj;
        }
        return ma;
    }

    public Aposta getNewAposta() {
        return newAposta;
    }

    public void setNewAposta(Aposta newAposta) {
        this.newAposta = newAposta;
    }

    public Bookie getBookie() {
        return bookie;
    }

    public void setBookie(Bookie bookie) {
        this.bookie = bookie;
    }
    
    
    /* Consultar evento por id */
    public Evento getEvento(int id) {
        Evento e = null;
        
        if (this.eventos.containsKey(id))
            e = this.eventos.get(id);
        
        return e;
    }
    
    /* Consultar apostador por email */
    public Apostador getApostador(String email) {
        Apostador a = null;
        
        if (this.apostadores.containsKey(email))
            a = this.apostadores.get(email);
        
        return a;
    }
    
    /**
     * Método registo(...).
     * Efetuar, se possível, o registo de um novo utilizador na aplicação.
     * 
     * @param email
     * @param nome
     * @param pass
     * @param coins
     * @throws RegistoInvalidoException 
     */
    public void registo(String email, String nome, String pass, double coins) 
           throws RegistoInvalidoException
    {   
        if (!this.apostadores.containsKey(email)) {
            Apostador a = new Apostador(email, nome, pass, coins);
            this.apostadores.put(email, a);
        } else 
            throw new RegistoInvalidoException("Utilizador já registado!");
    }
    
    /**
     * Método login(...).
     * Verifica os dados de entrada de um determinado utilizador.
     * 
     * @param email
     * @param pass
     * @return
     * @throws EmailErradoException
     * @throws PassErradaException 
     */
    public int login(String email, String pass) 
           throws EmailErradoException, PassErradaException 
    {
        int estatuto;
        
        if (email.equals("betadmin@betess.pt") && pass.equals("betadmin")) {
            Admin a = new Admin();
            this.setUser(email);
            estatuto = 0;
        } else {
             if (email.equals("bookie@betess.pt") && pass.equals("bookieBet")){
                this.setUser(email);
                estatuto = 2;
            } else {
                 if (this.apostadores.containsKey(email)) {
                    Apostador a = this.apostadores.get(email);
                
                    if (pass.equals(a.getPassword())) {
                        this.setUser(email);
                        estatuto = 1;
                    } else 
                        throw new PassErradaException("Password incorreta!");
                } else 
                     throw new EmailErradoException("Email errado!");
            }
        }
        return estatuto;
    }
    
    /**
     * Método novaAposta(...).
     * Adiciona uma aposta a uma lista de apostas de um determinado apostador.
     * 
     * @param userEmail
     * @param essCoins
     */
    public void novaAposta(String userEmail, double essCoins) {
        // Adiciona valor da aposta.
        this.newAposta.setValor(essCoins);
        
        // Substraí número de coins apostadas ao total do User.
        this.getApostador(userEmail).subtractTotalCoins(essCoins);
        
        this.newAposta.setIdAposta(this.apostasGerais.size() + 1);
        this.apostasGerais.put(newAposta.getIdAposta(), newAposta);
        
        // Adiciona a nova aposta à respetiva estrutura de dados.
        this.apostadores.get(userEmail).addAposta(this.newAposta);
        
        if (this.apostas.containsKey(userEmail)) {
            this.apostas.get(userEmail).add(this.newAposta);
        } else {
            LinkedList<Aposta> lista = new LinkedList<>();
            lista.addFirst(this.newAposta.clone()); // Adiciona aposta à cabeça de lista.
            this.apostas.put(userEmail, lista);
        }
        
        this.newAposta = new Aposta();
    }
    
    /**
     * Método getApostasUser(...).
     * Busca pela coleção de apostas de um determinado apostador.
     * 
     * @param userEmail
     * @return - Coleção de todas as Apostas associadas a um determinado user.
     */
    public Collection<Aposta> getApostasUser(String userEmail) {
        Collection<Aposta> c = null;
        
        if (this.apostadores.containsKey(userEmail))
            c = this.apostadores.get(userEmail).getApostas().values();
        
        return c;
    }
    
    /**
     * Método editaNomeUser(...).
     * Atualiza o nome de um apostador.
     * 
     * @param novoNome
     */
    public void editaNomeUser(String novoNome) {
        this.apostadores.get(user).setNome(novoNome);
        this.apostadores.replace(user, this.apostadores.get(user));
        
        /* Construção do packet a ser enviado ao notifyObserver. */
        Utilizador u = this.getApostador(this.user);
        Sender s = new Sender(u.getNome(), this.getObserverMenuApostador());
        this.notifyObserver(s);
    }
    
    /**
     * Método editaPassUser(...).
     * Atualiza a password de um apostador.
     * 
     * @param novaPass
     */
    public void editaPassUser(String novaPass) {
        this.apostadores.get(user).setPassword(novaPass);
        this.apostadores.replace(user, this.apostadores.get(user));
    }
    
    /**
     * Método editaMailUser(...).
     * Atualiza o email de um apostador.
     * 
     * @param email
     * @return - nova Facade com informação atualizada.
     */
    public Facade editaMailUser(String email) {
        Apostador a = this.apostadores.get(user);
        a.setEmail(email);
        
        this.apostadores.remove(user);
        this.apostadores.put(email, a);
        this.user = email;
        
        return this;
    }
    
    /**
     * Método adicionaCoins(...).
     * Adiciona um número de coins a um determinado Utilizador,
     * notificando o respetivo Observer da alteração efetuada.
     * 
     * @param coins 
     */
    public void adicionaCoins(double coins) {
        this.apostadores.get(this.user).addTotalCoins(coins);
        
        /* Construção do packet a ser enviado ao notifyObserver. */
        Double essCoins = this.apostadores.get(user).getEssCoins();
        Sender s = new Sender(essCoins, this.getObserverMenuApostador());
        this.notifyObserver(s);
    }
    
    /**
     * Método removeEventoFromAposta(...).
     * Remove um determinado evento de uma aposta.
     * 
     * @param e - evento a remover.
     */
    public void removeEventoFromAposta(Evento e) {
        this.newAposta.remEventoFromAposta(e);
    }

    /**
     * Método novoEvento(...).
     * Cria um novo evento, e adiciona-o à respetiva estrutura de dados.
     * 
     * @param equipaUm
     * @param equipaDois
     * @param oddUm
     * @param oddX
     * @param oddDois
     */  
    public void novoEvento(String equipaUm, String equipaDois, 
                           double oddUm, double oddX, double oddDois)
    {
        Evento evento = new Evento(this.eventos.size() + 1, equipaUm, equipaDois,
                                   oddUm, oddDois, oddX, "ABERTO", "-");
        this.eventos.put(evento.getIdEvento(), evento);
    }
    
    /**
     * Método alteraEstadoEvento(...).
     * Altera o estado de um evento de ABERTO para FECHADO.
     * DÚVIDA! Os Eventos não tem endereço partilhado?? Não basta mudar o estado em apenas um, que muda em todos?
     * 
     * @param idEvento
     */
    public void alteraEstadoEvento(int idEvento) {
        Aposta aSender = null;
        
        if (this.eventos.containsKey(idEvento)) {
            this.eventos.get(idEvento).setEstado("FECHADO");
            boolean terminada;
            
            
            for (String apostador: this.apostas.keySet()) {
                LinkedList<Aposta> lista = this.apostas.get(apostador);
                for (Aposta a: lista) {
                    terminada = true;
                    if (!a.getIsTerminada()) {
                        for (Evento e: a.getEventos().values()) {      
                            if (e.getIdEvento() == idEvento) {
                                // Atualiza estado no Map apostador-aposta.
                                e.setEstado("FECHADO");
                            
                                // Atualiza estado nas apostas do apostador.
                                this.apostadores.get(apostador)
                                        .getApostas()
                                        .get(a.getIdAposta())
                                        .getEventos()
                                        .get(idEvento)
                                        .setEstado("FECHADO");
                            }
                        
                            if (!e.getEstado().equals("FECHADO")) {
                                terminada = false;
                            }
                        }
                    
                        // Se a aposta estiver terminada, é calculado o ganho.
                        if (terminada) {
                            a.setTerminada(true);
                            aSender = a;
                            verificaVitoria(a, apostador);              
                        }
                    }
                }
                    
            }
        }
        
        this.eventos.remove(idEvento);
        
        /* Construção do packet a ser enviado ao notifyObserver. */
        Sender s = new Sender(aSender, this.getObserverMenuApostador());
        this.notifyObserver(s);
        
    }
    
    /**
     * Métoto verificaVitoria(...).
     * Verifica se a aposta finalizada de um apostador está toda certa.
     * 
     * @param a
     * @param user 
     */
    public void verificaVitoria(Aposta a, String user) {
        boolean vitoria = true;
        double oddApostada;
        String resultado = "";
        
        for (Evento e: a.getEventos().values()) {       
            // Guarda a odd apostada neste evento.
            oddApostada = a.getOdds().get(e.getIdEvento());
            
            // Procura a equipa na qual apostou baseado na odd.
            if (e.getOddUm() == oddApostada) 
                resultado = e.getEquipaUm();
            if (e.getOddDois() == oddApostada) 
                resultado = e.getEquipaDois();
            if (e.getOddX() == oddApostada) 
                resultado = "EMPATE";
            
            // Verifica se acertou no resultado.
            if (!resultado.equals(e.getResultado()))
                vitoria = false;
        }
        
        if (vitoria){
            this.apostadores.get(user).addTotalCoins(a.getGanhoTotal());
            Collection<Evento> listaEventos = a.getEventos().values();
            this.bookie.verificaEventos(listaEventos, a);
        } else {
            Collection<Evento> listaEventos = a.getEventos().values();
            this.bookie.verificaEventos(listaEventos, a);
        }
            
    }
    
    public double getGanhoEmAposta(int idAposta){
        double ganho = 0;
        
        for(LinkedList<Aposta> lista: this.apostas.values()){
            for(Aposta a: lista){
                if(a.getIsTerminada() && a.getIdAposta() ==  idAposta){
                    ganho = a.getGanhoTotal();
                }
            }
        }
        return ganho;
    }
           
    /**
     * Método novoResultado(...)
     * Atualiza o resultado de um evento.
     * 
     * @param idEvento
     * @param resultado
     */
    public void novoResultado(int idEvento, String resultado) {
        if (this.eventos.containsKey(idEvento))
            this.eventos.get(idEvento).setResultado(resultado);
    }
    
    /**
     * Método removeEvento(...).
     * Remove um determinado evento da lista de eventos.
     * 
     * @param idEvento
     */
    public void removeEvento(int idEvento) {
        this.eventos.remove(idEvento);
    }
    
    /**
     * Método carregaEventos().
     * Importa eventos vindos de um ficheiro JSON.
     */
    public void carregaEventos() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("betess/resources/Eventos.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray evs = rootObject.getAsJsonArray("Eventos");
        
        for (int i = 0; i < evs.size(); i++) {
            JsonObject item = evs.get(i).getAsJsonObject();
            
            int idEvento = item.get("id").getAsInt();
            String equipaUm = item.get("equipaUm").getAsString();
            String equipaDois = item.get("equipaDois").getAsString();
            double oddUm = item.get("oddUm").getAsDouble();
            double oddX = item.get("oddX").getAsDouble();
            double oddDois = item.get("oddDois").getAsDouble();
            
            Evento e = new Evento(idEvento, equipaUm, equipaDois, oddUm, oddDois, oddX, "ABERTO", "-");
            this.eventos.put(idEvento, e);
        }
    }
    
    /**
     * Método startApp().
     * Recupera o estado anterior da aplicação.
     * 
     * @return Objeto 'Facade' - Estado guardado anteriormente.
     */
    public Facade startApp() {
        return this.bd.readData("betdata.obj", this);
    }
    
    /**
     * Método endApp().
     * Guarda o estado de todo o sistema num ficheiro "betdata.obj".
     */
    public void endApp() {
        this.bd.writeData("betdata.obj", this);
    }
    
    
    /** ********************************************* *
     * Conjunto de métodos da interface Subject.      *
     * ********************************************* **/

    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }
    
    public void notifyObserver(Object o) {
        Sender s = (Sender) o;
        
        for (Observer obs: this.observers) {
            obs.update(s.getPacket());
        } 
    }

    public boolean novoInteresseEvento(int idEvento) {
        return this.bookie.novoEvento(idEvento);
    }
    
    public boolean temNotificacoes(){
        return this.bookie.size();
    }
    
    public Map<Integer, Map<Integer, Evento>> getBookieEventos(){
        return this.bookie.getEventos();
    }
    
     /**
     *
     */
    public void limpaNotificados(){
        this.bookie.limpaVistos();
        this.existeEventosEmAposta();
    }
    
    /**
     *
     * @return
     */
    public void existeEventosEmAposta(){
        boolean existe = false;
        
        for(Map.Entry<Integer, Boolean> m: this.bookie.getEventosInteressados().entrySet()){
            
            for(LinkedList<Aposta> lista : this.apostas.values()){
                for(Aposta a: lista){
                    Map<Integer,Evento> eventos = a.getEventos();
                    if(!a.getIsTerminada() && eventos.containsKey(m.getKey())){
                        existe = true;
                    }
                }
                if(!existe){
                    this.bookie.removeEvento(m.getKey());
                }
            }
        }
        
        
    }
}
