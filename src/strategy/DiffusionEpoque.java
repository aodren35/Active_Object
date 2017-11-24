package strategy;

import observer.Generator;
import observer.GeneratorImpl;
import strategy.AlgoDiffusion;

public class DiffusionEpoque implements AlgoDiffusion {

	//Lecteur/R�dacteur avec une copie. Les �critures sont permises dans l�original pendant la lecture de la copie.

	
	private GeneratorImpl genImpl;
	
    @Override
    public void configure(Generator generator) {

    }

    @Override
    public void execute() {

    }
}
