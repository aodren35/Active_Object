
public interface Subject {

	public void attach (Observer<Subject> obs);
	public void detach (Observer<Subject> obs);
	
}
