package betess.business;

import java.io.Serializable;

/**
 *
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Evento implements Serializable {
    
    private int idEvento;
    private String equipaUm;
    private String equipaDois;
    private double oddUm;
    private double oddDois;
    private double oddX;
    private String estado;
    private String resultado;

    
    public Evento(int idEvento, String equipaUm, String equipaDois, double oddUm, 
                  double oddDois, double oddX, String estado, String resultado) 
    {
        this.idEvento = idEvento;
        this.equipaUm = equipaUm;
        this.equipaDois = equipaDois;
        this.oddUm = oddUm;
        this.oddDois = oddDois;
        this.oddX = oddX;
        this.estado = estado;
        this.resultado = resultado;
    }
    
    public Evento(Evento e) {
        this.idEvento = e.getIdEvento();
        this.equipaUm = e.getEquipaUm();
        this.equipaDois = e.getEquipaDois();
        this.oddUm = e.getOddUm();
        this.oddDois = e.getOddDois();
        this.oddX = e.getOddX();
        this.estado = e.getEstado();
        this.resultado = e.getResultado();
    }

    /**
     * Getters e Setters.
     * @return 
     */
    
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getEquipaUm() {
        return equipaUm;
    }

    public void setEquipaUm(String equipaUm) {
        this.equipaUm = equipaUm;
    }

    public String getEquipaDois() {
        return equipaDois;
    }

    public void setEquipaDois(String equipaDois) {
        this.equipaDois = equipaDois;
    }

    public double getOddUm() {
        return oddUm;
    }

    public void setOddUm(double oddUm) {
        this.oddUm = oddUm;
    }

    public double getOddDois() {
        return oddDois;
    }

    public void setOddDois(double oddDois) {
        this.oddDois = oddDois;
    }

    public double getOddX() {
        return oddX;
    }

    public void setOddX(double oddX) {
        this.oddX = oddX;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
