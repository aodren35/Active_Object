package Display;

import Canal.Canal;
import observer.Generator;
import observer.GeneratorAsync;
import observer.ObservatorGenerator;
import observer.Subject;

import java.util.concurrent.ExecutionException;

public class Display implements ObservatorGenerator {

	private Canal canalGetValue;

	private int value;

    public Display(Canal canalGetValue) {
        this.canalGetValue = canalGetValue;
    }

    //Pour récupérer valeur javaFX algos
    public int getV() {
        return value;
    }

    public void setCanalGetValue(Canal canalGetValue) {
        this.canalGetValue = canalGetValue;
    }

    //Generator = canal => Héritage !!!
    public void update (Generator subject){

        try {
            GeneratorAsync ga = (GeneratorAsync)subject;
            this.value = ga.getValue().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Appel automatiquement toString
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Display{" +
                "value=" + value +
                '}';
    }

}
