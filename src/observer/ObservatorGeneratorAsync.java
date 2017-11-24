package observer;

import observer.Generator;

import java.util.concurrent.Future;

public interface ObservatorGeneratorAsync extends Observer {


	public Future update(Generator subject);

	
}
