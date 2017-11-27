package observer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Timestamp;

public class Value {
    private Timestamp ts;
    private IntegerProperty valueProperty ;


    public Value(){
        this.valueProperty = null;
        this.ts = new Timestamp(0);
    }

    public Value(int v){
        this.valueProperty = new SimpleIntegerProperty(v);
        this.ts = new Timestamp(System.currentTimeMillis());
    }

    public Value(Value v){
        this.valueProperty = new SimpleIntegerProperty(v.getValueProperty());
        this.ts = new Timestamp(v.getTs().getTime());
    }

    public int getValueProperty() {
        return valueProperty.get();
    }

    public IntegerProperty valuePropertyProperty() {
        return valueProperty;
    }

    public Timestamp getTs(){
        return this.ts;
    }

    public void incrementV(){
        this.valueProperty.set(this.valueProperty.get()+1);
        this.ts = new Timestamp(System.currentTimeMillis());
    }
    public void setTs(){
        this.ts = new Timestamp(System.currentTimeMillis());
    }
    public void set(Value valuecopy) {
        this.ts = valuecopy.getTs();
        if (this.valueProperty == null){
            this.valueProperty = new SimpleIntegerProperty(valuecopy.getValueProperty());
        } else {
            this.valueProperty.set(valuecopy.getValueProperty());
        }
    }

    public boolean isNull(){
        return this.valueProperty == null;
    }
}
