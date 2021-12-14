/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import escenas.EscenaControlable;
import escenas.Toolkit;
import escenas.escenaJuego;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class Celda {

    Image viewPlayer;
    Image viewComputer;
    
    ImageView pointer;
    
    Button button;
    int i, j;
    char value;
    escenaJuego escena;

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Celda(Image player, Image computer, int i, int j, escenaJuego parent) {
        this.i = i;
        this.j = j;
        this.viewPlayer = player;
        this.viewComputer = computer;
        value = '-';
        this.escena = parent;
        button = Toolkit.emptyImageButton();

        button.setOnMouseEntered((e) -> {
            if (escena.getCurrentVal() == 0) {
                button.setGraphic(generateImageView(viewPlayer));
            }
        });
        button.setOnMouseExited((e) -> {
            System.out.println("Called");
            if (value == '-') {
                System.out.println("Opps");
                button.setGraphic(null);
            }
        });
    }

    public void setMainAction() {
        button.setOnMouseClicked((e) -> {
            System.out.println("value before:" + value);
            if (escena.getCurrentVal() == 0 && value == '-') {
                button.setGraphic(generateImageView(viewPlayer));
                value = escena.getPlayerSymbol();
                escena.setCurrentVal(1);
                escena.getCurrent().setText("Computadora");
            } else if (escena.getCurrentVal() == 1 && value == '-') {
                button.setGraphic(generateImageView(viewComputer));
                escena.setCurrentVal(0);
                value = escena.getComputerSymbol();
                escena.getCurrent().setText("Tu");
                System.out.println("fff");
            }
            System.out.println("value after:" + value);
        });
    }
    
    public ImageView generateImageView(Image image){
        ImageView view = new ImageView(image);

        view.setFitHeight(80);
        view.setPreserveRatio(true);
        
        return view;
    }

}
