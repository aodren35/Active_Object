package strategy;

import observer.Generator;

public interface AlgoDiffusion {

    public void execute();

    public void configure(Generator generator);

    boolean getRunnable();
}
