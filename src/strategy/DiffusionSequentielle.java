package strategy;

import observer.Generator;
import observer.GeneratorImpl;
import observer.ObservatorGeneratorAsync;
import observer.Value;
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
    //exclusion mutuelle entre les n lecteurs et les n r�dacteurs, une �criture suivie de n lectures


    private Generator genImpl;

    private int counter = 0;
    private boolean runnable = true;
    private List<ObservatorGeneratorAsync>copyCanaux;
    private boolean getCopy = false;

    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
        this.copyCanaux = new ArrayList<>();
    }


    @Override
    public void execute() throws ExecutionException, InterruptedException {
        this.runnable = this.copyIsEmpty();
        if (this.runnable) {
            //!! bug make copy parfois fait avant le dernier getvalue
            boolean copied = this.genImpl.makeCopy();
            if (copied) {
                this.copyCanaux = (ArrayList) new ArrayList<ObservatorGeneratorAsync>(this.genImpl.getObservers());
                this.process();
            } else {
                this.execute();
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
        //System.out.println(this.copyCanaux);
    }

    @Override
    public boolean copyIsEmpty() {
        return this.copyCanaux.isEmpty();
    }

    @Override
    public Value getValue(ObservatorGeneratorAsync obs) {
        this.dettach(obs);
        return this.genImpl.getCopyValue();
    }

    private void process(){
        this.genImpl.getObservers().parallelStream().forEach(
                observatorGeneratorAsync -> {
                    Future<Void>updateFuture = observatorGeneratorAsync.update();
                }
        );
    }

}
