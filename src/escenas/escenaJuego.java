/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import modelos.Tablero;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class escenaJuego implements EscenaControlable {

    ControladorPantallas controlador;
    String current;

    String path;

    Tablero tablero;
    ImageView view;

    public escenaJuego() {
        current = "";
    }

    @Override
    public Scene getScene() {
        BorderPane root = new BorderPane();

        HBox currentTurn = new HBox();

        path = "res/" + controlador.getJugador() + ".png";

        Image img = new Image(path);
        view = new ImageView(img);

        view.setFitHeight(80);
        view.setPreserveRatio(true);

        if (controlador.getInicia() == 0) {
            current = "Tu";
        } else if (controlador.getInicia() == 1) {
            current = "Computadora";
        }
        System.out.println("current:" + current);
        Text currentLabel = Toolkit.textLabel(current, controlador.getBitsTitle());

        currentTurn.getChildren().add(currentLabel);
        currentTurn.setAlignment(Pos.CENTER);

        root.setTop(currentTurn);

        VBox center = new VBox();
        HBox main = new HBox();
        center.getChildren().add(generateTable());
        center.setAlignment(Pos.CENTER);

        main.getChildren().add(center);
        main.setAlignment(Pos.CENTER);

        root.setCenter(main);

        return new Scene(root, controlador.getDimensiones()[0], controlador.getDimensiones()[1]);
    }

    private GridPane generateTable() {
        GridPane container = new GridPane();
        container.setStyle("-fx-background-color: #454545");
        container.setHgap(3);
        container.setVgap(3);

        int l = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button boton = Toolkit.emptyImageButton();

                boton.setOnMouseEntered((e) -> {
                    boton.setGraphic(view);
                });
                boton.setOnMouseExited((e) -> {
                    boton.setGraphic(null);
                });
                //boton.setStyle("-fx-background-color: #223312");
                container.add(boton, i * 2, j * 2);
                System.out.println("i:" + i + " j:" + j);
            }
        }

        return container;
    }

    @Override
    public void setParent(ControladorPantallas controlador) {
        this.controlador = controlador;
    }

}
