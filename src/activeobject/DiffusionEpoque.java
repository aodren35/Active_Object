package activeobject;

import utilities.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DiffusionEpoque implements AlgoDiffusion {

	private Generator genImpl;

    private boolean runnable = true;

    private List<ObservatorGeneratorAsync>copyCanaux;

    private Value valueTest = new Value();

    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
        this.valueTest.set(this.genImpl.getValue());
        this.copyCanaux = new ArrayList<>();
        this.copyCanaux = (ArrayList) new ArrayList<ObservatorGeneratorAsync>(this.genImpl.getObservers());
    }

    @Override
    public void execute() throws ExecutionException, InterruptedException {
        this.process();
    }

    @Override
    public boolean getRunnable() {
        return this.runnable;
    }

    @Override
    public void remove(ObservatorGeneratorAsync obs) {
        this.copyCanaux.remove(obs);
    }

    @Override
    public boolean copyIsEmpty() {
        return this.copyCanaux.isEmpty();
    }

    @Override
    public Value getValue(ObservatorGeneratorAsync obs) {
        Value value = this.genImpl.getValue();
        if (this.valueTest.isNull()){
            this.valueTest.set(value);
            return value;
        } else {
            if (value.getTs().after(this.valueTest.getTs())){
                this.valueTest.set(value);
                return value;
            } else {
                return this.valueTest;
            }
        }
    }


    private void process(){
        this.genImpl.getObservers().parallelStream().forEach(
                observatorGeneratorAsync -> {
                    Future<Void>updateFuture = observatorGeneratorAsync.update();
                }
        );
    }

}
