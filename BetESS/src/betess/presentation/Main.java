package betess.presentation;

import betess.business.Facade;

/**
 * Classe Main.
 * É aqui que todo o programa é inicializado.
 * A Main trata de iniciar uma nova instância de Facade e uma 
 * nova instância de LoginFrame, chamando este último.
 * 
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Facade betEss = new Facade().startApp();
        LoginFrame lf = new LoginFrame(betEss);
        
        lf.setLocationRelativeTo(null);
        lf.setVisible(true);
        lf.setFocusableWindowState(true);
    }
}