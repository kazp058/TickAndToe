/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenas;

import java.util.ArrayList;
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
import modelos.Celda;
import modelos.Tablero;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class escenaJuego implements EscenaControlable {

    ControladorPantallas controlador;
    Text current;
    int currentVal; //0 es player y 1 es computador

    String pathPlayer;
    String pathComputer;
    
    char playerSymbol;
    char computerSymbol;

    Tablero tablero;
    

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(char playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public char getComputerSymbol() {
        return computerSymbol;
    }

    public void setComputerSymbol(char computerSymbol) {
        this.computerSymbol = computerSymbol;
    }

    @Override
    public Scene getScene() {
        BorderPane root = new BorderPane();

        HBox currentTurn = new HBox();

        playerSymbol = controlador.getJugador();
        if(playerSymbol == 'o') computerSymbol = 'x';
        else if(playerSymbol == 'x') computerSymbol = 'o';
        
        String val = "";
        
        if (controlador.getInicia() == 0) {
            val = "Tu";
        } else if (controlador.getInicia() == 1) {
            val = "Computadora";
        }
        
        current = Toolkit.textLabel(val, controlador.getBitsTitle());
        
        pathPlayer = "res/" + playerSymbol + ".png";
        pathComputer = "res/" + computerSymbol + ".png";        
        
        currentVal = controlador.getInicia();
        
        currentTurn.getChildren().add(current);
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

    public Text getCurrent() {
        return current;
    }

    public int getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(int currentVal) {
        this.currentVal = currentVal;
    }

    private GridPane generateTable() {
        GridPane container = new GridPane();
        container.setStyle("-fx-background-color: #454545");
        container.setHgap(3);
        container.setVgap(3);

        tablero = new Tablero();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                
                Celda celda = new Celda(new Image(pathPlayer),new Image(pathComputer) , i, j, this);
                celda.setMainAction();
                //boton.setStyle("-fx-background-color: #223312");
                container.add(celda.getButton(), i * 2, j * 2);
                
            }
        }

        return container;
    }

    @Override
    public void setParent(ControladorPantallas controlador) {
        this.controlador = controlador;
    }

}
