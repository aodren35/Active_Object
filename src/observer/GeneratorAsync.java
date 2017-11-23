package observer;


import java.util.concurrent.Future;

public interface GeneratorAsync {

	public Future<Integer> getValue(int n);

}
