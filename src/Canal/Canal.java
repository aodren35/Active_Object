package Canal;

import activeobject.GetValue;
import activeobject.Update;
import observer.*;
import Display.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Canal implements ObservatorGeneratorAsync, GeneratorAsync {

	private Display displayGetValue;
	private ScheduledExecutorService sES;
	private List<Observer> observers;
 	private Generator gen;
 	private Display dis;

	public Canal(Generator gen, Display dis) {
		this.observers = new ArrayList<Observer>();
		//encapsulation generator
		this.gen = gen;
		//encapsulation afficheur
		this.dis = dis;
		this.sES = Executors.newScheduledThreadPool(4);
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
	public Future<Integer> getValue(int n) {
		// GetValue
		GetValue gv = new GetValue(this.gen, this);
		return this.sES.schedule(gv, 1000, TimeUnit.MILLISECONDS);
	}

	@Override
	public Future<Integer> update(Generator subject) {
		// Update
		Update up = new Update(this.gen, this);
		return this.sES.schedule(up, 1000, TimeUnit.MILLISECONDS);
	}
}
