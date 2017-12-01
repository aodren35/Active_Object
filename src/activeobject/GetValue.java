package activeobject;

import utilities.Value;

import java.util.concurrent.Callable;

/**
 *
 * Classe correspondant à une des deux Method Invocation des pattern Active Object mis en place
 * Permet de call une méthode de façon asynchrone qui ira chercher la valeur du generator
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public class GetValue implements Callable<Value> {

	private Generator generator;
	private Canal canal;

	public GetValue(Generator gen, Canal can){
		this.generator = gen;
		this.canal = can;
	}

	@Override
	public Value call() {
		return this.generator.getAlgo().getValue(this.canal);
	}

}
