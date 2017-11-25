package IHM;

import observer.Generator;
import observer.GeneratorImpl;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Parrallelisme
public class Clock {

    private Timer timer ;

    public void activation(Generator gi, int period){
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (gi.getAlgo().getRunnable()){
                    new Thread(() -> {
                        gi.change();
                    }).start();
                } else {
                    return;
                }
            }
        };
        this.timer = new Timer(period, taskPerformer);
        this.timer.setRepeats(true);
        this.timer.start();
    }

    public void stop(){
        this.timer.stop();
    }


}
