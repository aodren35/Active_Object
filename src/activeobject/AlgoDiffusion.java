package activeobject;

import utilities.Value;

import java.util.concurrent.ExecutionException;

/**
 *
 * Interface permettant de configurer la stratégie de cohérence des données via la pattern Strategy et de l'executer.
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public interface AlgoDiffusion {

    /**
     * Fonction permettant d'executer la stratégie
     *
     */
    void execute();


    /**
     * Permet de definir la stratégie à adopter lors l'algorithme de cohérence de donnée.
     * @param generator Fait office de context pour le pattern Stratégie. Contient la valeur, sous forme de {@link utilities.Value},
     */
    void configure(Generator generator);

    /**
     * Permet d'enlever une référence d'un des canaux contenus dans le context({@link GeneratorImpl})
     * @param obs Le canal correspondant à la référence à enlever de la liste
     */
    void remove(ObservatorGeneratorAsync obs);

    /**
     * Méthode pour savoir si la liste des références aux canaux du context est vide ou non.
     * @return Valeur booleénne correspondant au ramplissage de la liste des références aux canaux
     */
    boolean copyIsEmpty();

    /**
     * Méthode permettant d'établir une sorte de proxy entre l'algorithme et le générateur
     * @param obs Correspond au canal, attaché au générateur, et qui a appellé, via la création d'un objet {@link GetValue}, cette méthode pour récuperer la valeur voulue.
     * @return La valeur à afficher en fonction de l'algorithme choisi
     */
    Value getValue(ObservatorGeneratorAsync obs);

}
