package activeobject;

import java.util.concurrent.Callable;

/**
 *
 *
 * Classe correspondant à une des deux Method Invocation des pattern Active Object mis en place
 * Permet de call une méthode de façon asynchrone qui dira aux lecteurs que la valeur a été mise à jour.
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public class Update implements Callable<Void> {

	private ObservatorGenerator display;
	private Canal canal;

	public Update(ObservatorGenerator dis, Canal can){
		this.display = dis;
		this.canal = can;
	}

	@Override
	public Void call() {
		this.display.update(this.canal);
		return null;
	}

}
