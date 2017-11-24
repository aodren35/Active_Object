package observer;


import java.util.concurrent.Future;

public interface GeneratorAsync {


	public void attach (Observer<Generator> obs);
	public void detach (Observer<Generator> obs);
	public Future<Integer> getValue();

}
