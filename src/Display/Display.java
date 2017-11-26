package Display;

import Canal.Canal;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import observer.Generator;
import observer.GeneratorAsync;
import observer.ObservatorGenerator;
import observer.Subject;

import java.util.concurrent.ExecutionException;

public class Display implements ObservatorGenerator {

	private Canal canalGetValue;

	private int value = 0;

	private SimpleIntegerProperty valueProperty = new SimpleIntegerProperty();

	private boolean available = true;

    public Display(Canal canalGetValue) {

        this.canalGetValue = canalGetValue;
    }

    //Pour récupérer valeur javaFX algos
    public int getValueAfficheur() {
        return value;
    }

    public SimpleIntegerProperty getValueProperty() {
        return this.valueProperty;
    }

    public void setCanalGetValue(Canal canalGetValue) {
        this.canalGetValue = canalGetValue;
    }

    //Generator = canal => Héritage !!!
    public void update (GeneratorAsync generatorAsync){
        try {
            int v = generatorAsync.getValue().get();
            this.value = v;
            System.out.println("Display " + this.value);
            while (!available) {
            }

            Task<Integer> task = new Task<Integer>() {
                @Override protected Integer call() throws Exception {
                    valueProperty.set(v);
                    available = true;
                    return v;
                }
            };
            if(this.available){
                this.available = false;
                Platform.runLater(task);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Appel automatiquement toString
        // System.out.println(this);
    }

    @Override
    public String toString() {
        return "Display{" +
                "value=" + value +
                '}';
    }

}
