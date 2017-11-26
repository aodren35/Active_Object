package strategy;

import javafx.concurrent.Task;
import observer.*;
import strategy.AlgoDiffusion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

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
    public void execute() throws ExecutionException, InterruptedException {
        // List<Observer<Generator>> copyList = new ArrayList<>(this.genImpl.getObservers().size());
        // Collections.copy(copyList, this.genImpl.getObservers());
        this.runnable = false;
        int x = 1;
        this.genImpl.getObservers().parallelStream().forEach(
                observatorGeneratorAsync -> {

                        Future<Void>updateFuture = observatorGeneratorAsync.update();
                        while(!updateFuture.isDone()){
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                }
        );
        this.runnable = true;
    }

    @Override
    public boolean getRunnable() {
        return this.runnable;
    }

    private void process(){

    }

}
