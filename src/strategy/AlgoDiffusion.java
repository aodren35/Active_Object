package strategy;

import observer.Generator;

import java.util.concurrent.ExecutionException;

public interface AlgoDiffusion {

    public void execute() throws ExecutionException, InterruptedException;

    public void configure(Generator generator);

    boolean getRunnable();
}
