package utilities;

import activeobject.Generator;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

/**
 * Classe utilitaire permettant de lancer toutes les X secondes une tâche pour activer le rédacteur.
 *
 * @author Barbé Cammille et Letellier Aodren
 * @version 1.0
 */
public class Clock {

    private Timer timer;

    public void activation(Generator gi, int period) {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                new Thread(() -> {

                    gi.change();


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
