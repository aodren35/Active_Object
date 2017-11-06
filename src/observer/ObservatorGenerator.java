package observer;

import observer.Generator;
import observer.Observer;

public interface ObservatorGenerator extends Observer<Generator> {
    
	public void update(Generator subject);

}
