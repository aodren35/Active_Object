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
	private List<ObservatorGeneratorAsync>copyCanaux;

    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
        this.copyCanaux = new ArrayList<>();
    }


    @Override
    public void execute() throws ExecutionException, InterruptedException {
        this.runnable = this.genImpl.isIncrementable();
        System.out.println("Appel execute "+ this.runnable);
        if (this.runnable) {
            this.genImpl.setIncrementable(false);
            this.copyCanaux = (ArrayList) new ArrayList<ObservatorGeneratorAsync>(this.genImpl.getObservers());
            this.process();
        } else {
            if (this.copyCanaux.isEmpty()){
                this.genImpl.setIncrementable(true);
            }
        }
    }

    @Override
    public boolean getRunnable() {
        return this.runnable;
    }

    @Override
    public void dettach(ObservatorGeneratorAsync obs) {
        this.copyCanaux.remove(obs);
    }

    @Override
    public boolean copyIsEmpty() {
        return false;
    }

    @Override
    public Value getValue(ObservatorGeneratorAsync obs) {
        this.dettach(obs);
        return this.genImpl.getValue();
    }

    private void process(){
        this.genImpl.getObservers().parallelStream().forEach(
                observatorGeneratorAsync -> {
                    Future<Void>updateFuture = observatorGeneratorAsync.update();
                }
        );
    }

}
