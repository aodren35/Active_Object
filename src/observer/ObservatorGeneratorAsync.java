package observer;

import observer.Generator;

import java.util.concurrent.Future;

public interface ObservatorGeneratorAsync {


	Future<Void> update();
	void setGenerator(Generator gen);

	
}
