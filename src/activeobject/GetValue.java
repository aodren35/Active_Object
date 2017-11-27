package activeobject;

import observer.Generator;
import observer.GeneratorImpl;
import Canal.Canal;
import observer.Value;

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
		System.out.println("Get Value call");

		return this.generator.getAlgo().getValue(this.canal);
	}

}
