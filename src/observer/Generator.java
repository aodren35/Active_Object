package observer;

import javafx.beans.property.IntegerProperty;
import strategy.AlgoDiffusion;

import java.util.List;

public interface Generator  {


	void attach(Observer<Generator> obs);
	void detach(Observer<Generator> obs);
	int getValue();
	void setValue(int v);
	AlgoDiffusion getAlgo();
	void setAlgoDiffusion(AlgoDiffusion algo);
	void change();
	IntegerProperty getValueProperty();
	List<ObservatorGeneratorAsync> getObservers();

}
