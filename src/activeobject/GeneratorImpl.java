package activeobject;

import utilities.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * Classe concrète du generator
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public class GeneratorImpl implements Generator{

	private Value value;

	private Value valueCopy;

	private AlgoDiffusion algo;

	private List<ObservatorGeneratorAsync> observers;

	private boolean incremental;

	private boolean copyable;


	public GeneratorImpl(int v) {
		this.value = new Value(v);
		this.valueCopy = new Value();
		this.observers = new ArrayList<>();
		this.incremental = true;
		this.copyable = true;
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
	public Value getValue() {
		return this.value;
	}

	@Override
	public Value getCopyValue() {
		return this.valueCopy;
	}

	@Override
	public boolean makeCopy() {
		if (this.copyable){
			this.valueCopy.set(this.value);
			return true;
		} else {
			return false;
		}
	}

	public void setValue(int v){
		return;
	}

	public void change(){
		if (this.incremental) {
			this.value.incrementV();
		}
		this.algo.execute();
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

	public boolean isIncremental() {
		return incremental;
	}

	public void setIncremental(boolean b) {
		this.incremental = b;
	}

	public boolean isCopyable() {
		return copyable;
	}

	public void setCopyable(boolean copyable) {
		this.copyable = copyable;
	}
}
