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
    private char jugador;
    private short inicia; //Si es 0 inicia el jugador, si es 1 inicia el computador

    public escenaMenu() {
        this.jugador = '-';
        this.inicia = -1;
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

        Text selecPer = textLabel("Selecciona tu personaje");
        Button botonX = imageButton("res/x.png");
        Button botonO = imageButton("res/o.png");

        Text selecStart = textLabel("Selecciona quien inicia:");
        Button botonJugador = imageButton("res/person.png");
        Button botonComputador = imageButton("res/ai.png");

        botonX.setOnMouseClicked((e) -> {
            this.jugador = 'x';
            botonX.setStyle("-fx-background-color: #6600ff");
            botonO.setStyle("-fx-background-color: transparent");
        });
        botonX.setOnMouseEntered((e) -> {
            if (Character.compare(jugador, '-') == 0 || Character.compare(jugador, 'o') == 0) {
                botonX.setStyle("-fx-background-color: #dcccff");
            }
        });
        botonX.setOnMouseExited((e) -> {
            if (Character.compare(jugador, '-') == 0 || Character.compare(jugador, 'o') == 0) {
                botonX.setStyle("-fx-background-color: transparent");
            }
        });

        botonO.setOnMouseClicked((e) -> {
            this.jugador = 'o';
            botonO.setStyle("-fx-background-color: #6600ff");
            botonX.setStyle("-fx-background-color: transparent");
        });
        botonO.setOnMouseEntered((e) -> {
            if (Character.compare(jugador, '-') == 0 || Character.compare(jugador, 'x') == 0) {
                botonO.setStyle("-fx-background-color: #dcccff");
            }
        });
        botonO.setOnMouseExited((e) -> {
            if (Character.compare(jugador, '-') == 0 || Character.compare(jugador, 'x') == 0) {
                botonO.setStyle("-fx-background-color: transparent");
            }
        });

        botonJugador.setOnMouseClicked((e) -> {
            inicia = 0;
            botonJugador.setStyle("-fx-background-color: #6600ff");
            botonComputador.setStyle("-fx-background-color: transparent");
        });
        botonJugador.setOnMouseEntered((e) -> {
            if (inicia == -1 || inicia == 1) {
                botonJugador.setStyle("-fx-background-color: #dcccff");
            }
        });
        botonJugador.setOnMouseExited((e) -> {
            if (inicia == -1 || inicia == 1) {
                botonJugador.setStyle("-fx-background-color: transparent");
            }
        });
        botonComputador.setOnMouseClicked((e) -> {
            inicia = 1;
            botonComputador.setStyle("-fx-background-color: #6600ff");
            botonJugador.setStyle("-fx-background-color: transparent");
        });

        botonComputador.setOnMouseEntered((e) -> {
            if (inicia == -1 || inicia == 0) {
                botonComputador.setStyle("-fx-background-color: #dcccff");
            }
        });
        botonComputador.setOnMouseExited((e) -> {
            if (inicia == -1 || inicia == 0) {
                botonComputador.setStyle("-fx-background-color: transparent");
            }
        });

        HBox iniciarBox = new HBox();

        iniciarBox.setAlignment(Pos.CENTER);
        iniciarBox.setStyle("-fx-background-color: transparent");

        Text iniciar = textLabel("Iniciar");
        iniciarBox.setOnMouseEntered((e) -> {
            if(inicia != -1 && Character.compare(jugador, '-') != 0){
                iniciarBox.setStyle("-fx-background-color: #dcccff");
            }            
        });
        iniciarBox.setOnMouseClicked((e)->{
            if(inicia != -1 && Character.compare(jugador, '-') != 0){
                iniciarBox.setStyle("-fx-background-color: #6600ff");
                controlador.setScene("playground");
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

    private Text textLabel(String mensaje) {
        Text texto = new Text(mensaje);
        texto.setFont(controlador.getBits());
        texto.setFill(Color.WHITE);
        texto.setStrokeType(StrokeType.OUTSIDE);
        texto.setStroke(Color.BLACK);
        texto.setStrokeWidth(1);
        texto.setTextAlignment(TextAlignment.CENTER);
        return texto;
    }

    private Button imageButton(String path) {
        Image img = new Image(path);
        ImageView view = new ImageView(img);

        view.setFitHeight(80);
        view.setPreserveRatio(true);

        Button button = new Button();
        button.setPrefSize(80, 80);
        button.setGraphic(view);
        button.setBackground(new Background(new BackgroundFill(null, null, null)));

        //button.setStyle("-fx-border-color: #6600ff; -fx-border-width: 5px;");
        return button;
    }

    private Button normalButton(String label, double parentWidth) {
        Button button = new Button(label);
        button.setPrefSize(parentWidth, 80);
        button.setBackground(new Background(new BackgroundFill(null, null, null)));
        button.setFont(controlador.getBits());
        //button.setStyle("-fx-border-color: #6600ff; -fx-border-width: 5px;");
        return button;
    }
}
