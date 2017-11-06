package IHM;

import observer.GeneratorImpl;

import java.util.Timer;
import java.util.TimerTask;

//Parrallelisme
public class Clock {

    private Timer timer = new Timer();

    public void activation(GeneratorImpl gi, double period){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                gi.change();
            }
        };
        timer.schedule(task, 0,(long) period);
    }


}
