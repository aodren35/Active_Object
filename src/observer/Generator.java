package observer;

import javafx.beans.property.IntegerProperty;
import strategy.AlgoDiffusion;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface Generator  {


	void attach(ObservatorGeneratorAsync obs);
	void detach(ObservatorGeneratorAsync obs);
	Value getValue();
	AlgoDiffusion getAlgo();
	void setAlgoDiffusion(AlgoDiffusion algo);
	void change() throws ExecutionException, InterruptedException;
	List<ObservatorGeneratorAsync> getObservers();
	boolean isIncrementable();
	void setIncrementable(boolean b);

}
