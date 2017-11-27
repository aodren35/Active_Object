package observer;


import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Time;
import java.sql.Timestamp;

public interface ObservatorGenerator extends Observer<GeneratorAsync> {
    Value getValue();
    Timestamp getTs();
}
