package strategy;

import observer.*;
import strategy.AlgoDiffusion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
        List<ObservatorGeneratorAsync> copyList = new ArrayList<>(this.genImpl.getObservers().size());
        copyList.addAll(this.genImpl.getObservers());
        this.runnable = false;
        int x = 1;
        for(ObservatorGeneratorAsync obs:copyList) {
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
