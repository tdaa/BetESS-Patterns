package betess.business;

/**
 * Interface Observer.
 * Esta interface representa um Observer no padrão de Software usado,
 * a qual contém o seu método principal (update) cuja função é aplicar um determinada
 * alteração no programa, depois de recebida a notificação.
 * 
 *
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public interface Observer {
    
    void update(Object o);  
}
