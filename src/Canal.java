public class Canal implements ObservatorGenerator, Generator, GeneratorAsync{

	Display displayGetValue;
	SchedulerExecutorService sES;
	
    public void update(Generator subject) {
    	
    }

    public int getValue(Generator subject) {
        return 0;
    }

	@Override
	public void attach(Observer<Subject> obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(Observer<Subject> obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Future getValue(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
