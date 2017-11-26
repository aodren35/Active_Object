package strategy;

import observer.Generator;
import observer.GeneratorImpl;
import observer.ObservatorGeneratorAsync;
import strategy.AlgoDiffusion;

import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class DiffusionEpoque implements AlgoDiffusion {

	//Lecteur/R�dacteur avec une copie. Les �critures sont permises dans l�original pendant la lecture de la copie.
    // Epoque : Les afficheurs appelle n'importe quand. Mais il faut qu'ils suivent une suite logique (1 puis 2 puis 3 puis 4 ...)

	
	private Generator genImpl;

    private boolean runnable = true;

    private Timestamp ts = new Timestamp(System.currentTimeMillis());


    @Override
    public void configure(Generator generator) {
        this.genImpl = generator;
        this.ts = new Timestamp(System.currentTimeMillis());
        this.ts = this.genImpl.getTs();
    }

    @Override
    public void execute() {
        this.runnable = false;
        Timestamp tsTest = this.genImpl.getTs();
        if (this.ts == null){
            this.ts = tsTest;
        }
        if (tsTest.after(this.ts) || tsTest.equals(this.ts)) {
            this.ts = tsTest;
            int x = 1;
            for (ObservatorGeneratorAsync obs : this.genImpl.getObservers()) {
                System.out.println("compteur : " + x);
                x++;
                this.runnable = true;
                boolean finished = false;
                Future<Boolean> future = obs.update(this.genImpl);
                try {
                    boolean test = true;
                    finished = future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                while (!finished) {
                    System.out.println("NOT FINISHED");
                }
            }
        }
        this.runnable = true;
    }

    @Override
    public boolean getRunnable() {
        return this.runnable;
    }

}
