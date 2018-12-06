package betess.business;

/**
 * Classe Sender.
 * Classe auxiliar que tem como intuito "empacotar" a informação que é transmitida
 * a cada um dos Observers.
 * 
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Sender {
    
    /* Informação empacotada a ser enviada. */
    private Object packet;
    /* Observar para qual é necessário enviar a informação. */
    private Object observer;

    /**
     * Construtor por parâmetros.
     * @param packet
     * @param observer 
     */
    public Sender(Object packet, Object observer) {
        this.packet = packet;
        this.observer = observer;
    }

    /**
     * Getters e Setters
     * @return 
     */
    
    public Object getPacket() {
        return this.packet;
    }

    public void setPacket(Object packet) {
        this.packet = packet;
    }

    public Object getObserver() {
        return this.observer;
    }

    public void setObserver(Object observer) {
        this.observer = observer;
    }
}
