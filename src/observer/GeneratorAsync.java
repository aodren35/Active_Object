package observer;


import java.util.concurrent.Future;

public interface GeneratorAsync extends Subject {

	public Future<Integer> getValue(int n);

}
