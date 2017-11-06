package observer;

import observer.Generator;
import observer.Observer;
import observer.Subject;
import strategy.AlgoDiffusion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GeneratorImpl implements Generator, Subject {

	private int v;

	private Timestamp ts;

	private AlgoDiffusion algo;

	private List<Observer> observers;

	public GeneratorImpl(int v, AlgoDiffusion algo) {
		this.v = v;
		this.algo = algo;
		this.observers = new ArrayList<Observer>();
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setAlgo(AlgoDiffusion algo) {
		this.algo = algo;
		this.algo.configure(this);
	}

	@Override
	public void attach(Observer<Subject> obs) {
		// TODO Auto-generated method stub
		observers.add(obs);
		
	}

	@Override
	public void detach(Observer<Subject> obs) {
		// TODO Auto-generated method stub
		observers.remove(obs);
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
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
}
