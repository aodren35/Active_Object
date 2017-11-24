package observer;


import javafx.beans.property.SimpleIntegerProperty;

public interface ObservatorGenerator extends Observer<GeneratorAsync> {
    public int getValueAfficheur();
    SimpleIntegerProperty getValueProperty();
}
