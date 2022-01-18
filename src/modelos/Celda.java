/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import escenas.EscenaControlable;
import escenas.Toolkit;
import escenas.escenaJuego;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class Celda {

    Image viewPlayerA;
    Image viewPlayerB;

    ImageView pointer;

    escenaJuego escena;
    int holder; //Jugador 0, y Computador 1

    Tablero tablero;

    Button button;
    int i, j; //i columna y j fila
    char value;

    public escenaJuego getEscena() {
        return escena;
    }

    public void setEscena(escenaJuego escena) {
        this.escena = escena;
    }

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

    public Celda(Image playerA, Image playerB, int i, int j, escenaJuego parent) {
        this.i = i;
        this.j = j;
        this.viewPlayerA = playerA;
        this.viewPlayerB = playerB;
        value = '-';
        this.escena = parent;
        this.holder = -1;
        button = Toolkit.emptyImageButton();

        button.setOnMouseEntered((e) -> {
            if (escena.getCurrentPlayer().playerTypeAI == 0) {
                if (value == '-') {

                    if (escena.getCurrentPlayer() == escena.getPlayerA()) {
                        button.setGraphic(generateImageView(viewPlayerA));
                    } else if (escena.getCurrentPlayer() == escena.getPlayerB()) {
                        button.setGraphic(generateImageView(viewPlayerB));
                    }
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
            System.out.println("clicked here: " + this);
            this.click();
            //escena.calculateMove();
            //System.out.println(escena.getPossibleBestMove());
        });
    }

    public ImageView generateImageView(Image image) {
        ImageView view = new ImageView(image);

        view.setFitHeight(80);
        view.setPreserveRatio(true);

        return view;
    }

    public Celda copy(Tablero tablero) {
        Celda newCelda = escena.initCell(i, j, tablero);
        newCelda.setHolder(holder);
        return newCelda;
    }

    @Override
    public String toString() {
        return String.valueOf(holder);
    }

    public boolean click() {

        System.out.println("executed here: " + this.i + " , " + this.j);

        int winner = tablero.hasWinner();
        if (winner != -1) {
            escena.setWinner(winner);
        }

        if (value == '-') {
            if (escena.getCurrentPlayer() == escena.getPlayerA()) {
                button.setGraphic(generateImageView(viewPlayerA));
            } else if (escena.getCurrentPlayer() == escena.getPlayerB()) {
                button.setGraphic(generateImageView(viewPlayerB));
            }

            value = escena.getCurrentPlayer().getPlayerSymbol();
            holder = escena.getCurrentPlayer().getPlayerAssignation();
            
            tablero.lastI = this.i;
            tablero.lastJ = this.j;

            escena.setCurrentPlayer(escena.getCurrentPlayer().getOppossing());
            escena.getCurrent().setText(escena.getCurrentPlayer().getOppossing().getPlayerName());

            winner = tablero.hasWinner();
            System.out.println("Winner: " + winner);
            if (winner != -1) {
                escena.setWinner(winner);
            } else {
                System.out.println("current: " + escena.getCurrentPlayer().getPlayerTypeAI());
                if (escena.getCurrentPlayer().getPlayerTypeAI() == 1) {
                    System.out.println("Movimiento calculado");
                    escena.calculateMove();
                    if (escena.getPossibleBestMove() != null) {
                        int[] next = escena.getPossibleBestMove();
                        //tablero.pressCell(next[0], next[1]);
                    }
                }
            }
        }

        return false;
    }

}
