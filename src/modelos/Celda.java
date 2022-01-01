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
    int i, j; //i columna y j fila
    char value;
    escenaJuego escena;
    int holder; //Jugador 0, y Computador 1

    Tablero tablero;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getHolder() {
        return holder;
    }

    public void setHolder(int holder) {
        this.holder = holder;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

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
                if (value == '-') {
                    button.setGraphic(generateImageView(viewPlayer));
                }
            }
        });
        button.setOnMouseExited((e) -> {
            if (value == '-') {
                button.setGraphic(null);
            }
        });
    }

    public void setMainAction() {
        button.setOnMouseClicked((e) -> {
            if (escena.getCurrentVal() == 0 && value == '-') {
                button.setGraphic(generateImageView(viewPlayer));
                value = escena.getPlayerSymbol();
                escena.setCurrentVal(1);
                escena.getCurrent().setText("Computadora");
                holder = 0;
                System.out.println("tablero: " + tablero + " current: 0");
                tablero.calculateOptions(0);

            } else if (escena.getCurrentVal() == 1 && value == '-') {
                button.setGraphic(generateImageView(viewComputer));
                escena.setCurrentVal(0);
                value = escena.getComputerSymbol();
                escena.getCurrent().setText("Tu");
                holder = 1;
                System.out.println("tablero: " + tablero + " current: 1");

                tablero.calculateOptions(1);
            }
        });
    }

    public ImageView generateImageView(Image image) {
        ImageView view = new ImageView(image);

        view.setFitHeight(80);
        view.setPreserveRatio(true);

        return view;
    }

}
