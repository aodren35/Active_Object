package strategy;

import observer.*;
import strategy.AlgoDiffusion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DiffusionAtomique implements AlgoDiffusion {

	//exclusion mutuelle entre les n lecteurs et les n r�dacteurs, une �criture suivie de n lectures

	
	private Generator genImpl;

	private int counter = 0;

    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
    }

    @Override
    public void execute() {
        // List<Observer<Generator>> copyList = new ArrayList<>(this.genImpl.getObservers().size());
        // Collections.copy(copyList, this.genImpl.getObservers());
        for(ObservatorGeneratorAsync obs:this.genImpl.getObservers()) {
            Future<Boolean> future = obs.update(this.genImpl);
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            counter ++;
        }
    }
}
