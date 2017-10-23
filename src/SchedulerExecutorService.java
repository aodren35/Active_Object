import java.util.concurrent.TimeUnit;

public interface SchedulerExecutorService {

	public void scheduler(Callable c, int delai, TimeUnit unit);
	
	
	
}
