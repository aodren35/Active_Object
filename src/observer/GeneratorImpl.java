package observer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import strategy.AlgoDiffusion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GeneratorImpl implements Generator, Cloneable{

	private int v;

	private Timestamp ts;

	private AlgoDiffusion algo;

	private List<ObservatorGeneratorAsync> observers;


	public GeneratorImpl(int v) {
		this.v = v;
		this.observers = new ArrayList<>();
	}

	public Timestamp getTs() {
		return ts;
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
	public int getValue() {
		return v;
	}

	@Override
	public void setValue(int v){
		this.v = v;
	}

	public void change() throws ExecutionException, InterruptedException {
			this.v++;
			this.ts = new Timestamp(System.currentTimeMillis());
			System.out.println(this);
			this.algo.execute();
	}

	@Override
	public String toString() {
		return "GeneratorImpl{" +
				"v=" + v +
				", ts=" + ts +
				'}';
	}

	public AlgoDiffusion getAlgo() {
		return algo;
	}

	public void setAlgoDiffusion(AlgoDiffusion algo) {
		this.algo = algo;
	}

	public IntegerProperty getValueProperty() {
		IntegerProperty result = new SimpleIntegerProperty();
		result.setValue(this.v);
		return result;
	}

	public List<ObservatorGeneratorAsync> getObservers() {
		return observers;
	}

	public Object clone() {
		Object o = null;
		try {
			// On récupère l'instance à renvoyer par l'appel de la
			// méthode super.clone()
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous implémentons
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return o;
	}

}
