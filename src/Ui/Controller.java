package Ui;

import Canal.Canal;
import Display.Display;
import IHM.Clock;
import javafx.fxml.Initializable;
import observer.Generator;
import observer.GeneratorImpl;
import observer.ObservatorGenerator;
import strategy.DiffusionAtomique;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private Generator generator = new GeneratorImpl(1);

    private Canal canal1 = new Canal();
    private ObservatorGenerator afficheur1 = new Display(canal1);

    private Canal canal2 = new Canal();
    private ObservatorGenerator afficheur2 = new Display(canal2);

    private Canal canal3 = new Canal();
    private ObservatorGenerator afficheur3 = new Display(canal3);

    private Canal canal4 = new Canal();
    private ObservatorGenerator afficheur4 = new Display(canal4);

    private Clock clock;



    public void start() {

        clock.activation(this.generator, 1000);

    }

    public void stop() {
        clock.stop();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.clock = new Clock();


        this.generator.setAlgoDiffusion(new DiffusionAtomique());

        this.canal1.setGenerator(this.generator);
        this.canal2.setGenerator(this.generator);
        this.canal3.setGenerator(this.generator);
        this.canal4.setGenerator(this.generator);

        this.canal1.attach(this.afficheur1);
        this.canal2.attach(this.afficheur2);
        this.canal3.attach(this.afficheur3);
        this.canal4.attach(this.afficheur4);

        this.generator.attach(this.canal1);
        this.generator.attach(this.canal2);
        this.generator.attach(this.canal3);
        this.generator.attach(this.canal4);
        System.out.println("LOL DE LOL");
    }
}
