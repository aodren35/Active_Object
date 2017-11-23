package activeobject;

import Canal.Canal;
import observer.Generator;
import observer.GeneratorImpl;

import java.util.concurrent.Callable;

public class Update implements Callable<Integer> {

	private Generator generator;
	private Canal canal;

	public Update(Generator gen, Canal can){
		this.generator = gen;
		this.canal = can;
	}

	@Override
	public Integer call() {
		return this.generator.getValue();
	}

}
