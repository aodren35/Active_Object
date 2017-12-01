package activeobject;

import utilities.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * Implémentation de la stratégie de cohérence de donnée séquentielle. Chaque lecteur lit la valeur du redacteur
 * précédemment copié lors de sa première lecture.
 * Le rédacteur est capable d'écrire pendant la lecture, et la copie de la valeur est faites seulement à la fin de la lecture de tous les lecteurs.
 *
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public class DiffusionEpoque implements AlgoDiffusion {

	private Generator genImpl;

    private boolean runnable = true;

    private List<ObservatorGeneratorAsync>copyCanaux;

    private Value valueTest = new Value();

    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
        this.valueTest.set(this.genImpl.getValue());
        this.copyCanaux = new ArrayList<>();
        this.copyCanaux = (ArrayList) new ArrayList<ObservatorGeneratorAsync>(this.genImpl.getObservers());
    }

    @Override
    public void execute() {
        this.process();
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
        Value value = this.genImpl.getValue();
        if (this.valueTest.isNull()){
            this.valueTest.set(value);
            return value;
        } else {
            if (value.getTs().after(this.valueTest.getTs())){
                this.valueTest.set(value);
                return value;
            } else {
                return this.valueTest;
            }
        }
    }


    private void process(){
        this.genImpl.getObservers().parallelStream().forEach(
                observatorGeneratorAsync -> {
                    Future<Void>updateFuture = observatorGeneratorAsync.update();
                }
        );
    }

}
