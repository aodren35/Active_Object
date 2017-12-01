package activeobject;

import utilities.Value;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * Interface jouant différents rôles sur les pattern mis en place.
 * Correspond notament au context pour le pattern strategy, au Subject dans le pattern Observer,
 * au Client puis au Component dans le pattern Proxy et au Client et au Servant pour le pattern Active Object
 *
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public interface Generator  {


	/**
	 * Attache un canal au generator
	 * @param obs le canal à attacher
	 */
	void attach(ObservatorGeneratorAsync obs);

	/**
	 * Détache un canal du generator
	 * @param obs le canal à détacher
	 */
	void detach(ObservatorGeneratorAsync obs);

	/**
	 * Retourne la valeur contenue dans le generator sous forme de {@link Value}
	 * @return la valeur concrète
	 */
	Value getValue();

	/**
	 * Retourne la valeur copiée précédement sous forme de {@link Value}
	 * @return la valeur copiée
	 */
	Value getCopyValue();

	/**
	 * Permet de faire une copie de la valeur du generator si possible
	 * @return retourne si oui ou non la copie a été faites
	 */
	boolean makeCopy();

	/**
	 *
	 * @return Retourne l'algorithme de cohérence des données actuel
	 */
	AlgoDiffusion getAlgo();

	/**
	 * Permet de définir l'algorithme de cohérence des données.
	 * @param algo l'algorithme souhaité
	 */
	void setAlgoDiffusion(AlgoDiffusion algo);

	/**
	 * Permet d'incrémenter la value si possible. Puis d'executer l'algorithme.
	 */
	void change();

	/**
	 * Permet d'obtenir la liste des canaux attachés au generator
	 * @return la liste des canaux
	 */
	List<ObservatorGeneratorAsync> getObservers();

	/**
	 * Permet de savoir si le generator est actuellement incrémentable ou non.
	 * @return le generator peut-il écrire
	 */
	boolean isIncremental();

	/**
	 * Permet de dire au generator si il peut écrire ou non.
	 * @param b valeur booléenne
	 */
	void setIncremental(boolean b);
}
