package Canal;

import activeobject.Future;
import activeobject.SchedulerExecutorService;
import observer.*;
import Display.*;

import java.util.ArrayList;
import java.util.List;

public class Canal implements ObservatorGenerator, Generator, GeneratorAsync {

	private Display displayGetValue;
	private SchedulerExecutorService sES;
	private List<Observer> observers;
 	private Generator gen;
 	private Display dis;

	public Canal(Generator gen, Display dis) {
		this.observers = new ArrayList<Observer>();
		//encapsulation generator
		this.gen = gen;
		//encapsulation afficheur
		this.dis = dis;
	}

	public void update(Generator subject) {
    	this.dis.update(this);
    }

    public int getValue(Generator subject) {
    	return 0;
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
		//manière synchrone
		return this.gen.getValue();
	}

	@Override
	public Future getValue(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
