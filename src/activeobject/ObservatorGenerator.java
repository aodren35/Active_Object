package activeobject;


import utilities.Value;

import java.sql.Timestamp;

/**
 *
 * Interface jouant différents rôles sur les pattern mis en place.
 * Correspond notament à l'observer dans le pattern Observer,
 * au Client puis au Component dans le pattern Proxy et au Client et au Servant pour le pattern Active Object
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public interface ObservatorGenerator extends Observer<GeneratorAsync>{
    Value getValue();
    Timestamp getTs();
}
