package observer;

import strategy.AlgoDiffusion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GeneratorImpl implements Generator {

	private int v;

	private Timestamp ts;

	private AlgoDiffusion algo;

	private List<Observer<Generator>> observers;

	public GeneratorImpl(int v) {
		this.v = v;
		this.observers = new ArrayList<Observer<Generator>>();
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setAlgo(AlgoDiffusion algo) {
		this.algo = algo;
		this.algo.configure(this);
	}

	@Override
	public void attach(Observer<Generator> obs) {
		observers.add(obs);
		
	}

	@Override
	public void detach(Observer<Generator> obs) {
		observers.remove(obs);
	}

	@Override
	public int getValue() {
		return v;
	}

	public void change(){
		this.v++;
		this.ts = new Timestamp(System.currentTimeMillis());
		System.out.println(this);
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
}
