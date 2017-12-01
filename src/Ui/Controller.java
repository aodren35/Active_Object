package Ui;

import activeobject.Canal;
import activeobject.Display;
import utilities.Clock;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import activeobject.Generator;
import activeobject.GeneratorImpl;
import activeobject.ObservatorGenerator;
import activeobject.AlgoDiffusion;
import activeobject.DiffusionAtomique;
import activeobject.DiffusionEpoque;
import activeobject.DiffusionSequentielle;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

/**
 *
 * Class Controler pour l'interface graphique en javaFx.
 * Contient tous les objets de classe misent en place durant ce projet.
 *
 * @version 1.0
 * @author Barbé Cammille et Letellier Aodren
 */
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
    private BooleanProperty started = new SimpleBooleanProperty();

    @FXML
    Text afficheur1Value1 = new Text();
    @FXML
    Text afficheurTs1 = new Text();

    @FXML
    Text afficheur1Value2 = new Text();
    @FXML
    Text afficheurTs2 = new Text();

    @FXML
    Text afficheur1Value3 = new Text();
    @FXML
    Text afficheurTs3 = new Text();

    @FXML
    Text afficheur1Value4 = new Text();
    @FXML
    Text afficheurTs4 = new Text();

    @FXML
    RadioButton diffusionAtomique = new RadioButton();

    @FXML
    RadioButton sequentiel = new RadioButton();

    @FXML
    RadioButton epoque = new RadioButton();

    @FXML
    Button start = new Button();

    @FXML
    Button stop = new Button();

    public Controller() throws ExecutionException, InterruptedException {
    }


    public void start() {
        this.started.set(true);
        this.generator.setAlgoDiffusion(this.algo);
        clock.activation(this.generator, 1000);

    }

    public void stop() {
        this.started.set(false);
        clock.stop();
    }

    @FXML
    public void switchAlgo(ActionEvent event) {
        if (this.started.getValue()){
            this.stop();
        }
        if(event.getSource().equals(this.diffusionAtomique)) {
            this.algo = new DiffusionAtomique();
            this.algo.configure(this.generator);
            this.generator.setIncremental(true);
            this.sequentiel.selectedProperty().set(false);
            this.epoque.selectedProperty().set(false);
        } else if (event.getSource().equals(this.sequentiel)){
            this.algo = new DiffusionSequentielle();
            this.algo.configure(this.generator);
            this.generator.setIncremental(true);
            this.diffusionAtomique.selectedProperty().set(false);
            this.epoque.selectedProperty().set(false);
        } else {
            this.algo = new DiffusionEpoque();
            this.algo.configure(this.generator);
            this.generator.setIncremental(true);
            this.diffusionAtomique.selectedProperty().set(false);
            this.sequentiel.selectedProperty().set(false);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.clock = new Clock();

        this.algo = new DiffusionAtomique();
        this.algo.configure(this.generator);


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

        this.afficheur1Value1.textProperty().setValue(this.afficheur1.getValue().getValueProperty() + "");
        this.afficheur1Value2.textProperty().setValue(this.afficheur2.getValue().getValueProperty() + "");
        this.afficheur1Value3.textProperty().setValue(this.afficheur3.getValue().getValueProperty() + "");
        this.afficheur1Value4.textProperty().setValue(this.afficheur4.getValue().getValueProperty() + "");

        this.afficheur1.getValue().valuePropertyProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                afficheurTs1.textProperty().setValue(afficheur1.getTs().toString());

                afficheur1Value1.textProperty().setValue("" + newValue);
            }
        });

        this.afficheur2.getValue().valuePropertyProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                afficheurTs2.textProperty().setValue(afficheur2.getTs().toString());

                afficheur1Value2.textProperty().setValue("" + newValue);
            }
        });

        this.afficheur3.getValue().valuePropertyProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                afficheurTs3.textProperty().setValue(afficheur3.getTs().toString());


                afficheur1Value3.textProperty().setValue("" + newValue);
            }
        });

        this.afficheur4.getValue().valuePropertyProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                afficheurTs4.textProperty().setValue(afficheur4.getTs().toString());

                afficheur1Value4.textProperty().setValue("" + newValue);
            }
        });

        this.start.disableProperty().bindBidirectional(this.started);
        this.stop.disableProperty().bind(this.start.disabledProperty().not());

    }
}
