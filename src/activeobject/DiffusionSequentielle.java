package activeobject;

import utilities.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * Implémentation de la stratégie de cohérence de donnée par époque.
 * Chaque lecteur reçoit la dernière valeur du générateur, temporairement parlant.
 *
 *
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public class DiffusionSequentielle implements AlgoDiffusion {


    private Generator genImpl;
    private boolean runnable = true;
    private List<ObservatorGeneratorAsync>copyCanaux;

    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
        this.copyCanaux = new ArrayList<>();
    }


    @Override
    public void execute()  {
        this.runnable = this.copyIsEmpty();
        if (this.runnable) {
            boolean copied = this.genImpl.makeCopy();
            if (copied) {
                this.copyCanaux = (ArrayList) new ArrayList<ObservatorGeneratorAsync>(this.genImpl.getObservers());
                this.process();
            }
        }
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
        this.remove(obs);
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
