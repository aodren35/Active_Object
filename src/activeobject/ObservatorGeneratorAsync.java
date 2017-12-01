package activeobject;

import java.util.concurrent.Future;

/**
 *
 * Interface correspondant au rôle de service dans le design pattern Active Object mis en place pour l'Update
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
public interface ObservatorGeneratorAsync {


	Future<Void> update();
	void setGenerator(Generator gen);

	
}
