package activeobject;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public interface SchedulerExecutorService {

	public void scheduler(Callable<Integer> c, int delai, TimeUnit unit);
	
	
	
}
