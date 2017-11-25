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
	private CompletionService<Integer>completionGetValue;
	private CompletionService<Boolean>completionUpdate;

 	private Generator generator;
 	private ObservatorGenerator display;

	public Canal() {
		//encapsulation generator
		this.generator = null;
		//encapsulation afficheur
		this.display = null;
		this.sES = Executors.newScheduledThreadPool(10);
		this.completionGetValue = new ExecutorCompletionService<Integer>(this.sES);
		this.completionUpdate = new ExecutorCompletionService<Boolean>(this.sES);
	}

	@Override
	public void attach(ObservatorGenerator obs) {
		this.setDisplay(obs);

	}

	@Override
	public void detach(ObservatorGenerator obs) {
		this.setDisplay(null);
		
	}


	@Override
	public Future<Integer> getValue() {
		// GetValue
		GetValue gv = new GetValue(this.generator, this);
		// this.completionGetValue.submit(gv);
		return this.sES.schedule(gv, 1000, TimeUnit.MILLISECONDS);
	}

	@Override
	public Future<Boolean> update(Generator subject) {
		// Update
		Update up = new Update(this.generator, this);
		//this.completionUpdate.submit(up);
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
		System.out.println("TEST");
	}
}
