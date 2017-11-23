package observer;

import observer.Generator;

import java.util.concurrent.Future;

public interface ObservatorGeneratorAsync {

	public Future update(Generator subject);

	
}
