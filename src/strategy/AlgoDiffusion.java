package strategy;

import observer.Generator;
import observer.ObservatorGeneratorAsync;
import observer.Value;

import java.util.concurrent.ExecutionException;

public interface AlgoDiffusion {

    public void execute() throws ExecutionException, InterruptedException;

    public void configure(Generator generator);

    boolean getRunnable();

    void dettach(ObservatorGeneratorAsync obs);
}
