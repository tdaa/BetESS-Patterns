/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.business;

/**
 *
 * @author tiagoalves
 */
public class Sender {
    
    private Object packet;
    private Object observer;

    public Sender(Object packet, Object observer) {
        this.packet = packet;
        this.observer = observer;
    }

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
