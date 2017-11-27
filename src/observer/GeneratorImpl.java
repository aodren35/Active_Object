package observer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import strategy.AlgoDiffusion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GeneratorImpl implements Generator{

	private Value value;

	private Value valueCopy;

	private AlgoDiffusion algo;

	private List<ObservatorGeneratorAsync> observers;


	public GeneratorImpl(int v) {
		this.value = new Value(v);
		this.observers = new ArrayList<>();
	}

	public void setAlgo(AlgoDiffusion algo) {
		this.algo = algo;
		this.algo.configure(this);
	}

	@Override
	public void attach(ObservatorGeneratorAsync obs) {
		observers.add(obs);
		
	}

	@Override
	public void detach(ObservatorGeneratorAsync obs) {
		observers.remove(obs);
	}

	@Override
	public Value getValue(ObservatorGeneratorAsync obs) {
		String algoClasse = this.algo.getClass().getCanonicalName();
		if (algoClasse.equals("strategy.DiffusionAtomique")){
			this.algo.dettach(obs);
			return this.value;
		} else if (algoClasse.equals("strategy.DiffusionSequentielle")) {
			return this.valueCopy;
		} else if (algoClasse.equals("strategy.DiffusionEpoque")) {
			return this.value;
		} else {
			return null;
		}
	}


	public void setValue(int v){
		return;
	}

	public void change() throws ExecutionException, InterruptedException {
		String algoClasse = this.algo.getClass().getCanonicalName();
		if (algoClasse.equals("strategy.DiffusionAtomique")){
			this.value.incrementV();
			System.out.println(this.value.getValueProperty());
		} else if (algoClasse.equals("strategy.DiffusionSequentielle")) {

		} else if (algoClasse.equals("strategy.DiffusionEpoque")) {

		} else {
		}

	}

	@Override
	public String toString() {
		return "GeneratorImpl{" +
				"v=" + value.getValueProperty() +
				", ts=" + value.getTs() +
				'}';
	}

	public AlgoDiffusion getAlgo() {
		return algo;
	}

	public void setAlgoDiffusion(AlgoDiffusion algo) {
		this.algo = algo;
	}


	public List<ObservatorGeneratorAsync> getObservers() {
		return observers;
	}



}
