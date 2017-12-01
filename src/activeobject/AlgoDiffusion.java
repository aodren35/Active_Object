package activeobject;

import utilities.Value;

import java.util.concurrent.ExecutionException;

public interface AlgoDiffusion {

    void execute() throws ExecutionException, InterruptedException;

    void configure(Generator generator);

    boolean getRunnable();

    void remove(ObservatorGeneratorAsync obs);

    boolean copyIsEmpty();

    Value getValue(ObservatorGeneratorAsync obs);

}
