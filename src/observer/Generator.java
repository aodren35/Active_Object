package observer;

import strategy.AlgoDiffusion;

public interface Generator  {


	public void attach (Observer<Generator> obs);
	public void detach (Observer<Generator> obs);
	public int getValue();

	public AlgoDiffusion getAlgo();

	public void setAlgoDiffusion(AlgoDiffusion algo);

	public void change();
   
}
