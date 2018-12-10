package betess.persistence;

import betess.business.Facade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe DataBetESS.
 * Nesta classe são invocados os métodos necessários para guardar
 * o estado da aplicação.
 * 
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class DataBetESS implements Serializable {

    /**
     * Construtor Vazio.
     */
    public DataBetESS() { } 
    
    /**
     * Método writeData(...).
     * Aqui é feita a escrita do estado atual do sistema para um 
     * determinado ficheiro.
     *
     * @param fileName
     * @param system
     */
    public void writeData(String fileName, Facade system) {
        system.clearObservers();
        
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(system);
            oos.flush();
            oos.close();
            
            System.out.println("Guardado com sucesso!");
	} catch (IOException e) {
            System.out.println(e.getMessage());
	}
    }
    
    /**
     * Método readData(...).
     * Aqui é lido o estado anteriormente guardado no ficheiro em parâmetro.
     * 
     * @param fileName
     * @param system
     * @return - estado que foi lido do ficheiro.
     */
    public Facade readData(String fileName, Facade system) {
        system.clearObservers();
        Facade betEss = system;
        
        try {
            File f = new File(fileName);
            
            if (f.exists()) {
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                
                betEss = (Facade) ois.readObject();
                ois.close();
                
                System.out.println("Estado importado com sucesso!");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        return betEss;
    }   
}
