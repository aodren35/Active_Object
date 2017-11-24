package strategy;

import observer.Generator;
import observer.GeneratorAsync;
import observer.GeneratorImpl;
import observer.Observer;
import strategy.AlgoDiffusion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

public class DiffusionAtomique implements AlgoDiffusion {

	//exclusion mutuelle entre les n lecteurs et les n r�dacteurs, une �criture suivie de n lectures

	
	private Generator genImpl;


    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
    }

    @Override
    public void execute() {
        // List<Observer<Generator>> copyList = new ArrayList<>(this.genImpl.getObservers().size());
        // Collections.copy(copyList, this.genImpl.getObservers());
        //for(Observer<Generator> obs:copyList) {
        //}
    }
}
