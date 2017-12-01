package activeobject;


import utilities.Value;

import java.util.concurrent.Future;

/**
 *
 * Interface correspondant au rôle de service dans le pattern Active Object mis en place pour le Getvalue
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public interface GeneratorAsync {


	 void attach (ObservatorGenerator obs);
	 void detach (ObservatorGenerator obs);
	 Future<Value> getValue();

}
