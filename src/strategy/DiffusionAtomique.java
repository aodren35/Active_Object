package strategy;

import observer.Generator;
import observer.GeneratorImpl;
import strategy.AlgoDiffusion;

public class DiffusionAtomique implements AlgoDiffusion {

	//exclusion mutuelle entre les n lecteurs et les n r�dacteurs, une �criture suivie de n lectures

	
	private GeneratorImpl genImpl;
	
    @Override
    public void configure(Generator generator) {

    }

    @Override
    public void execute() {

    }
}
