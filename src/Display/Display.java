package Display;

import Canal.Canal;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import observer.*;

import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Display implements ObservatorGenerator {

	private Canal canalGetValue;

	private Value value;


	private boolean available = true;

    public Display(Canal canalGetValue) throws ExecutionException, InterruptedException {
        this.canalGetValue = canalGetValue;
        this.value = new Value(-1);
    }

    public IntegerProperty getValueProperty() {
        return this.value.valuePropertyProperty();
    }

    public Value getValue(){
        return this.value;
    }

    public void update (GeneratorAsync generatorAsync){
        try {
            Future<Value> newValue =  generatorAsync.getValue();
            while  (!newValue.isDone()){
                Thread.sleep(300);
            }
            //this.value.set(newValue.get());
            //System.out.println("Changement dans display " +newValue.get().getValueProperty());
            // this.valueProperty.set(this.value.getV());

            //this.valueProperty.set(1000);
            while (!available) {
            }

            Value valueFinale = newValue.get();
            Task<Integer> task = new Task<Integer>() {
                @Override protected Integer call() throws Exception {
                    value.set(valueFinale);
                    System.out.println("Value changé dans afficheur "+ value.getValueProperty());
                    available = true;
                    return 1;
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
    }

    @Override
    public String toString() {
        return "Display{" +
                "value=" + value +
                '}';
    }

    public Timestamp getTs(){
        return this.value.getTs();
    }

}
