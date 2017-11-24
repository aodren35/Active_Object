package observer;

import javafx.beans.property.IntegerProperty;
import strategy.AlgoDiffusion;

import java.util.List;

public interface Generator  {


	public void attach (Observer<Generator> obs);
	public void detach (Observer<Generator> obs);
	public int getValue();
	public AlgoDiffusion getAlgo();
	public void setAlgoDiffusion(AlgoDiffusion algo);
	public void change();
	public IntegerProperty getValueProperty();
	public List<Observer<Generator>> getObservers();

}
