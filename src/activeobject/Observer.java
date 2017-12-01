package activeobject;


/**
 *
 * Interface jouant le rôle de l'observer dans le design patter Observer mis en place
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public interface Observer<T> {


    void update(T subject);
}