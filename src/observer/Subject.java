package observer;



//Probleme de methode earaser
public interface Subject {

	public void attach (Observer<Subject> obs);
	public void detach (Observer<Subject> obs);
	// public void notifyObservers();
	
}
