package activeobject;

import utilities.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * Implémentation de la stratégie de cohérence de donnée atomique. Chaque lecteur lit la valeur du redacteur qui lui ne peut écrire tant que tous les lecteurs n'ont pas fini de lire.
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public class DiffusionAtomique implements AlgoDiffusion {


	private Generator genImpl;
	private boolean runnable = true;
	private List<ObservatorGeneratorAsync>copyCanaux;

    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
        this.copyCanaux = new ArrayList<>();
    }


    @Override
    public void execute() {
        this.runnable = this.genImpl.isIncremental();
        if (this.runnable) {
            this.genImpl.setIncremental(false);
            this.copyCanaux = new ArrayList<>(this.genImpl.getObservers());
            this.process();
        } else {
            if (this.copyCanaux.isEmpty()){
                this.genImpl.setIncremental(true);
            }
        }
    }

    @Override
    public void remove(ObservatorGeneratorAsync obs) {
        this.copyCanaux.remove(obs);
    }

    @Override
    public boolean copyIsEmpty() {
        return false;
    }

    @Override
    public Value getValue(ObservatorGeneratorAsync obs) {
        this.remove(obs);
        return this.genImpl.getValue();
    }

    private void process(){
        this.genImpl.getObservers().parallelStream().forEach(
                observatorGeneratorAsync -> {
                    Future<Void>updateFuture = observatorGeneratorAsync.update();
                }
        );
    }

}
