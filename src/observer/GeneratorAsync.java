package observer;


import java.util.concurrent.Future;

public interface GeneratorAsync {


	 void attach (ObservatorGenerator obs);
	 void detach (ObservatorGenerator obs);
	 Future<Value> getValue();

}
