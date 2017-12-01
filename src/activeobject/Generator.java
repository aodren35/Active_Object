package activeobject;

import utilities.Value;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface Generator  {


	void attach(ObservatorGeneratorAsync obs);
	void detach(ObservatorGeneratorAsync obs);
	Value getValue();
	Value getCopyValue();
	boolean makeCopy();
	AlgoDiffusion getAlgo();
	void setAlgoDiffusion(AlgoDiffusion algo);
	void change() throws ExecutionException, InterruptedException;
	List<ObservatorGeneratorAsync> getObservers();
	boolean isIncremental();
	void setIncremental(boolean b);
	boolean isCopyable();
	void setCopyable(boolean copyable);

}
