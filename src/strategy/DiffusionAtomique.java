package strategy;

import observer.*;
import strategy.AlgoDiffusion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DiffusionAtomique implements AlgoDiffusion {

	//exclusion mutuelle entre les n lecteurs et les n r�dacteurs, une �criture suivie de n lectures

	
	private Generator genImpl;

	private int counter = 0;
	private boolean runnable = true;

    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
    }


    @Override
    public void execute() {
        // List<Observer<Generator>> copyList = new ArrayList<>(this.genImpl.getObservers().size());
        // Collections.copy(copyList, this.genImpl.getObservers());
        this.runnable = false;
        int x = 1;
        for(ObservatorGeneratorAsync obs:this.genImpl.getObservers()) {
            System.out.println("compteur : "+ x);
            x ++ ;
            boolean finished = false;
            Future<Boolean> future = obs.update(this.genImpl);
            try {
                 finished = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            while (!finished) {
                System.out.println("NOT FINISHED");
            }
        }
        this.runnable = true;
    }

    @Override
    public boolean getRunnable() {
        return this.runnable;
    }

}
