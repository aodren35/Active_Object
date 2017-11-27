package strategy;

import observer.*;
import strategy.AlgoDiffusion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class DiffusionEpoque implements AlgoDiffusion {

	//Lecteur/R�dacteur avec une copie. Les �critures sont permises dans l�original pendant la lecture de la copie.
    // Epoque : Les afficheurs appelle n'importe quand. Mais il faut qu'ils suivent une suite logique (1 puis 2 puis 3 puis 4 ...)
    //3 – Par époques
    //Chaque valeur possède un tag (par exemple date et heure de la dernière modification) ce qui permet au rédacteur de savoir si la valeur qu’il veut modifier est plus récente que la précédente et donc de modifier en premier la plus ancienne des valeurs.
	
	private Generator genImpl;

    private boolean runnable = true;

    private List<ObservatorGeneratorAsync>copyCanaux;

    private Value valueTest = new Value();

    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
        this.copyCanaux = new ArrayList<>();
    }

    @Override
    public void execute() throws ExecutionException, InterruptedException {
        this.runnable = this.copyIsEmpty();
        if (this.runnable) {
            this.copyCanaux = (ArrayList) new ArrayList<ObservatorGeneratorAsync>(this.genImpl.getObservers());
            this.process();
        }
    }

    @Override
    public boolean getRunnable() {
        return this.runnable;
    }

    @Override
    public void dettach(ObservatorGeneratorAsync obs) {
        this.copyCanaux.remove(obs);
    }

    @Override
    public boolean copyIsEmpty() {
        return this.copyCanaux.isEmpty();
    }

    @Override
    public Value getValue(ObservatorGeneratorAsync obs) {
        this.dettach(obs);
        Value value = this.genImpl.getValue();
        if (this.valueTest.isNull()){
            this.valueTest.set(value);
            return value;
        } else {
            if (value.getTs().after(this.valueTest.getTs())){
                this.valueTest.set(value);

                System.out.println("RETURN VALUE TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
                return value;
            } else {
                System.out.println("RETURN VALUE TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
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
