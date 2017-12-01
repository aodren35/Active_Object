package activeobject;

import utilities.Value;

import java.util.concurrent.*;

public class Canal implements ObservatorGeneratorAsync, GeneratorAsync {

	private ScheduledExecutorService sES;
 	private Generator generator;
 	private ObservatorGenerator display;

	public Canal() {
		this.generator = null;
		this.display = null;
		this.sES = Executors.newScheduledThreadPool(50);
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
	public Future<Value> getValue() {
		GetValue gv = new GetValue(this.generator, this);
		int randomNum = ThreadLocalRandom.current().nextInt(500, 5000);
		return this.sES.schedule(gv, randomNum, TimeUnit.MILLISECONDS);
	}

	@Override
	public Future<Void> update() {
		Update up = new Update(this.display, this);
		int randomNum = ThreadLocalRandom.current().nextInt(500, 5000);
		return this.sES.schedule(up, randomNum, TimeUnit.MILLISECONDS);
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

}
