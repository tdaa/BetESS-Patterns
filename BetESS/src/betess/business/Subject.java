package betess.business;

/**
 * Interface Subject.
 * Esta interface representa um Observable no padrão de Software usado,
 * e tem o intuito de definir os métodos que iram ser previamente implementados.
 * 
 *
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public interface Subject {
    
    void registerObserver(Observer o);
    
    void removeObserver(Observer o);
    
    void notifyObserver(Object o);
}
