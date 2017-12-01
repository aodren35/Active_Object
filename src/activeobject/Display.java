package activeobject;

import utilities.Value;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.concurrent.Task;

import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * Classe d'implémentation de l'Obseravateur du générateur. Afficheur comprenant la valeur affiché dans l'interface graphique utilisateur.
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public class Display implements ObservatorGenerator {

	private Canal canal;
	private Value value;


	private boolean available = true;

    public Display(Canal canalGetValue) {
        this.canal = canalGetValue;
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
            while (!available) {
            }

            Value valueFinale = newValue.get();
            Task<Integer> task = new Task<Integer>() {
                @Override protected Integer call() throws Exception {
                    value.set(valueFinale);
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
