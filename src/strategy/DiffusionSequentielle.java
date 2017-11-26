package strategy;

import observer.Generator;
import observer.GeneratorImpl;
import observer.ObservatorGeneratorAsync;
import strategy.AlgoDiffusion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DiffusionSequentielle implements AlgoDiffusion {

    // Séquentielle : C'est un lecteur/Rédacteur mais avec une copie du nombre. Les écritures sont permises dans l'originale pdt la lecture de la copie. Du coup les afficheurs peuvent faire 1 puis 3 puis 4 puis 7
    //2 – Séquentielle
    //Chaque lecteur/rédacteur possède une copie de sa valeur. Le rédacteur peut continuer d’avancer et les écritures sont permises dans l’original pendant les lecteurs lisent la valeur copiée.
      private Generator genImpl;

    private boolean runnable = true;


    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
    }

    @Override
    public void execute() throws ExecutionException, InterruptedException {
        this.runnable = false;

        System.out.println("EXECUTE" + this.genImpl.getValue());
        List<ObservatorGeneratorAsync> copyList = new ArrayList<>(this.genImpl.getObservers().size());
        for(int i = 0; i<this.genImpl.getObservers().size(); i++){
            copyList.add(null);
        }
        Collections.copy(copyList, this.genImpl.getObservers());
        Generator genCopy = (GeneratorImpl)((GeneratorImpl)this.genImpl).clone();
        int x = 1;


        copyList.parallelStream().forEach(
                observatorGeneratorAsync -> {
                    this.runnable = true;

                        observatorGeneratorAsync.setGenerator(genCopy);
                        Future<Void>updateFuture = observatorGeneratorAsync.update();
                        while(!updateFuture.isDone()){
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                       //  updateFuture.get();

                }
        );
        this.runnable = true;
    }

    @Override
    public boolean getRunnable() {
        return this.runnable;
    }

}
