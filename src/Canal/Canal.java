package Canal;

import activeobject.GetValue;
import activeobject.Update;
import observer.*;
import Display.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Canal implements ObservatorGeneratorAsync, GeneratorAsync {

	private ScheduledExecutorService sES;



 	private Generator generator;
 	private ObservatorGenerator display;

	public Canal() {
		//encapsulation generator
		this.generator = null;
		//encapsulation afficheur
		this.display = null;
		this.sES = Executors.newScheduledThreadPool(4);
	}

	@Override
	public void attach(Observer<Generator> obs) {
		ObservatorGenerator og = (ObservatorGenerator) obs;
		this.setDisplay(og);

	}

	@Override
	public void detach(Observer<Generator> obs) {
		this.setDisplay(null);
		
	}


	@Override
	public Future<Integer> getValue() {
		// GetValue
		GetValue gv = new GetValue(this.generator, this);
		return this.sES.schedule(gv, 1000, TimeUnit.MILLISECONDS);
	}

	@Override
	public Future<Boolean> update(Generator subject) {
		// Update
		Update up = new Update(this.generator, this);
		return this.sES.schedule(up, 1000, TimeUnit.MILLISECONDS);
	}

	public  ObservatorGenerator getDisplay() {
		return display;
	}

	public void setDisplay( ObservatorGenerator display) {
		this.display = display;
	}

	public Generator getGenerator() {
		return generator;
	}

	public void setGenerator(Generator generator) {
		this.generator = generator;
	}

	@Override
	public void update(Object subject) {

	}
}
