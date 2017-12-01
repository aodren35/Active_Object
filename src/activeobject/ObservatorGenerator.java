package activeobject;


import utilities.Value;

import java.sql.Timestamp;

public interface ObservatorGenerator extends Observer<GeneratorAsync>{
    Value getValue();
    Timestamp getTs();
}
