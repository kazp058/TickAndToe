/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class escenaMenu implements EscenaControlable {

    ControladorPantallas controlador;

    public escenaMenu() {

    }

    @Override
    public Scene getScene() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);

        GridPane menu = new GridPane();
        menu.setHgap(5);
        menu.setVgap(10);
        menu.setAlignment(Pos.CENTER);

        Text selecPer = Toolkit.textLabel("Elije tu icono:", controlador.getBits());
        Button botonX = Toolkit.imageButton("res/x.png");
        Button botonO = Toolkit.imageButton("res/o.png");

        Text selecStart = Toolkit.textLabel("Elije quien inicia:", controlador.getBits());
        Button botonJugador = Toolkit.imageButton("res/person.png");
        Button botonComputador = Toolkit.imageButton("res/ai.png");

        botonX.setOnMouseClicked((e) -> {
            controlador.setJugador('x');
            botonX.setStyle("-fx-background-color: #6600ff");
            botonO.setStyle("-fx-background-color: transparent");
        });
        botonX.setOnMouseEntered((e) -> {
            if (Character.compare(controlador.getJugador(), '-') == 0 || Character.compare(controlador.getJugador(), 'o') == 0) {
                botonX.setStyle("-fx-background-color: #dcccff");
            }
        });
        botonX.setOnMouseExited((e) -> {
            if (Character.compare(controlador.getJugador(), '-') == 0 || Character.compare(controlador.getJugador(), 'o') == 0) {
                botonX.setStyle("-fx-background-color: transparent");
            }
        });

        botonO.setOnMouseClicked((e) -> {
            controlador.setJugador('o');
            botonO.setStyle("-fx-background-color: #6600ff");
            botonX.setStyle("-fx-background-color: transparent");
        });
        botonO.setOnMouseEntered((e) -> {
            if (Character.compare(controlador.getJugador(), '-') == 0 || Character.compare(controlador.getJugador(), 'x') == 0) {
                botonO.setStyle("-fx-background-color: #dcccff");
            }
        });
        botonO.setOnMouseExited((e) -> {
            if (Character.compare(controlador.getJugador(), '-') == 0 || Character.compare(controlador.getJugador(), 'x') == 0) {
                botonO.setStyle("-fx-background-color: transparent");
            }
        });

        botonJugador.setOnMouseClicked((e) -> {
            controlador.setInicia((short)0);
            botonJugador.setStyle("-fx-background-color: #6600ff");
            botonComputador.setStyle("-fx-background-color: transparent");
        });
        botonJugador.setOnMouseEntered((e) -> {
            if (controlador.getInicia() == -1 || controlador.getInicia() == 1) {
                botonJugador.setStyle("-fx-background-color: #dcccff");
            }
        });
        botonJugador.setOnMouseExited((e) -> {
            if (controlador.getInicia() == -1 || controlador.getInicia() == 1) {
                botonJugador.setStyle("-fx-background-color: transparent");
            }
        });
        botonComputador.setOnMouseClicked((e) -> {
            controlador.setInicia((short)1);
            botonComputador.setStyle("-fx-background-color: #6600ff");
            botonJugador.setStyle("-fx-background-color: transparent");
        });

        botonComputador.setOnMouseEntered((e) -> {
            if (controlador.getInicia() == -1 || controlador.getInicia() == 0) {
                botonComputador.setStyle("-fx-background-color: #dcccff");
            }
        });
        botonComputador.setOnMouseExited((e) -> {
            if (controlador.getInicia() == -1 || controlador.getInicia() == 0) {
                botonComputador.setStyle("-fx-background-color: transparent");
            }
        });

        HBox iniciarBox = new HBox();

        iniciarBox.setAlignment(Pos.CENTER);
        iniciarBox.setStyle("-fx-background-color: transparent");

        Text iniciar = Toolkit.textLabel("Iniciar Juego", controlador.getBits());
        iniciarBox.setOnMouseEntered((e) -> {
            if(controlador.getInicia() != -1 && Character.compare(controlador.getJugador(), '-') != 0){
                iniciarBox.setStyle("-fx-background-color: #dcccff");
            }            
        });
        iniciarBox.setOnMouseClicked((e)->{
            if(controlador.getInicia() != -1 && Character.compare(controlador.getJugador(), '-') != 0){
                iniciarBox.setStyle("-fx-background-color: #6600ff");
                controlador.setScene(controlador.juegoNombre);
            }   
        });
        iniciarBox.setOnMouseExited((e) -> {
            iniciarBox.setStyle("-fx-background-color: transparent");

        });
        iniciarBox.getChildren().add(iniciar);

        menu.add(selecPer, 0, 0, 2, 2);
        menu.add(botonX, 0, 2, 1, 1);
        menu.add(botonO, 2, 2, 1, 1);
        menu.add(selecStart, 0, 3, 2, 2);
        menu.add(botonJugador, 0, 5, 1, 1);
        menu.add(botonComputador, 2, 5, 1, 1);
        menu.add(iniciarBox, 0, 8, 4, 4);

        root.getChildren().add(menu);
        return new Scene(root, controlador.getSize()[0], controlador.getSize()[1]);
    }

    @Override
    public void setParent(ControladorPantallas controlador) {
        this.controlador = controlador;
    }

    private DropShadow getEffect() {
        DropShadow effect = new DropShadow();
        effect.setBlurType(BlurType.GAUSSIAN);
        effect.setColor(Color.rgb(0, 0, 0, .5));
        effect.setSpread(0);
        effect.setOffsetX(0);
        effect.setOffsetY(7);

        return effect;
    }
}
