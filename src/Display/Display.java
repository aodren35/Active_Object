package Display;

import Canal.Canal;
import observer.Generator;
import observer.ObservatorGenerator;

public class Display implements ObservatorGenerator {

	private Canal canalGetValue;

	private int value;

    public Display(Canal canalGetValue) {
        this.canalGetValue = canalGetValue;
    }

    //Pour récupérer valeur javaFX aldos
    public int getV() {
        return value;
    }

    public void setCanalGetValue(Canal canalGetValue) {
        this.canalGetValue = canalGetValue;
    }

    //Generator = canal => Héritage !!!
    public void update (Generator subject){
        this.value = subject.getValue();
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
