package activeobject;

import utilities.Value;

import java.util.concurrent.Callable;

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
