import java.util.ArrayList;
import java.util.List;

public class GeneratorImpl implements Generator {

	private int v;
	
	GetValue gV;
	
	List<AlgoDiffusion> algo = new ArrayList<AlgoDiffusion>(); 
    
    @Override
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

}
