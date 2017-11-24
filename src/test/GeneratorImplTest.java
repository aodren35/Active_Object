package test;

import IHM.Clock;
import observer.GeneratorImpl;
import strategy.AlgoDiffusion;
import strategy.DiffusionAtomique;

public class GeneratorImplTest {

    public static void main(String[] args) {
        AlgoDiffusion ad = new DiffusionAtomique();

        // GeneratorImpl gen = new GeneratorImpl(50, ad);

        Clock clock = new Clock();

        //clock.activation(gen, 1000);


    }

}
