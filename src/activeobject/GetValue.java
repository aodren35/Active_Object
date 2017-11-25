package activeobject;

import observer.Generator;
import observer.GeneratorImpl;
import Canal.Canal;

import java.util.concurrent.Callable;

public class GetValue implements Callable<Integer> {

	private Generator generator;
	private Canal canal;

	public GetValue(Generator gen, Canal can){
		this.generator = gen;
		this.canal = can;
	}

	@Override
	public Integer call() {
		// System.out.println("Get Value call");

		return this.generator.getValue();
	}

}
