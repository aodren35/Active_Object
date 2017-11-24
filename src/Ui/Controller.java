package Ui;

import Canal.Canal;
import Display.Display;
import IHM.Clock;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import observer.Generator;
import observer.GeneratorImpl;
import observer.ObservatorGenerator;
import strategy.AlgoDiffusion;
import strategy.DiffusionAtomique;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private Generator generator = new GeneratorImpl(-1);

    private AlgoDiffusion algo;

    private Canal canal1 = new Canal();
    private ObservatorGenerator afficheur1 = new Display(canal1);

    private Canal canal2 = new Canal();
    private ObservatorGenerator afficheur2 = new Display(canal2);

    private Canal canal3 = new Canal();
    private ObservatorGenerator afficheur3 = new Display(canal3);

    private Canal canal4 = new Canal();
    private ObservatorGenerator afficheur4 = new Display(canal4);

    private Clock clock;

    @FXML
    TextField afficheur1Value1 = new TextField();

    @FXML
    TextField afficheur1Value2 = new TextField();

    @FXML
    TextField afficheur1Value3 = new TextField();

    @FXML
    TextField afficheur1Value4 = new TextField();


    public void start() {

        clock.activation(this.generator, 1000);

    }

    public void stop() {
        clock.stop();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.clock = new Clock();

        this.algo = new DiffusionAtomique();
        this.algo.configure(this.generator);
        this.generator.setAlgoDiffusion(this.algo);

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

        IntegerProperty fname = new SimpleIntegerProperty();
        fname.bindBidirectional(this.generator.getValueProperty());

        //contact.firstNameProperty().set("new value");
       // fname.set("New First Name");
        this.afficheur1Value3.setText("" + this.generator.getValue());

    }
}
