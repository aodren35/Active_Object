package observer;

import activeobject.Future;
import observer.Generator;

public interface ObservatorGeneratorAsync {

	public Future update(Generator subject);

	
}
