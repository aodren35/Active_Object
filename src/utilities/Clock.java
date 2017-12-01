package utilities;

import activeobject.Generator;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class Clock {

    private Timer timer;

    public void activation(Generator gi, int period) {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                new Thread(() -> {
                    try {
                        gi.change();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }).start();

            }
        };
        this.timer = new Timer(period, taskPerformer);
        this.timer.setRepeats(true);
        this.timer.setInitialDelay(0);
        this.timer.start();
    }

    public void stop() {
        this.timer.stop();
    }


}
