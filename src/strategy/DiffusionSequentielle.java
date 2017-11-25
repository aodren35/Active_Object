package strategy;

import observer.Generator;
import observer.GeneratorImpl;
import strategy.AlgoDiffusion;

public class DiffusionSequentielle implements AlgoDiffusion {

    // Séquentielle : C'est un lecteur/Rédacteur mais avec une copie du nombre. Les écritures sont permises dans l'originale pdt la lecture de la copie. Du coup les afficheurs peuvent faire 1 puis 3 puis 4 puis 7


    private Generator genImpl;

    private boolean runnable = true;


    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean getRunnable() {
        return this.runnable;
    }

}
