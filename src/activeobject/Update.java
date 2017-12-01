package activeobject;

import java.util.concurrent.Callable;

public class Update implements Callable<Void> {

	private ObservatorGenerator display;
	private Canal canal;

	public Update(ObservatorGenerator dis, Canal can){
		this.display = dis;
		this.canal = can;
	}

	@Override
	public Void call() {
		this.display.update(this.canal);
		return null;
	}

}
