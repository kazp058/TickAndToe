/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenas;

import estructuras.Tree;
import estructuras.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
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
import modelos.Player;
import modelos.Tablero;
import javafx.scene.layout.GridPane;


/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class escenaJuego implements EscenaControlable, EscenaJugable{

    ControladorPantallas controlador;
    Text current;
    Player currentPlayer; //0 es player y 1 es computador

    String pathPlayerA;
    String pathPlayerB;

    Player winner;

    Tablero tablero;

    int[] possibleBestMove;

    Tree<Tablero> utilityTree;

    Player playerA;
    Player playerB;

    @Override
    public Scene getScene() {
        BorderPane root = new BorderPane();

        HBox toolbox = new HBox();
        toolbox.setAlignment(Pos.CENTER);
        toolbox.setSpacing(100);

        HBox currentTurn = new HBox();

        currentPlayer = controlador.getInicia();

        playerA = controlador.getPlayerA();
        playerB = controlador.getPlayerB();

        playerA.setPlayerAssignation(
                0);
        playerB.setPlayerAssignation(
                1);

        playerA.setOppossing(playerB);

        playerB.setOppossing(playerA);

        String val = "";

        if (controlador.getInicia()
                == controlador.getPlayerA()) {
            val = playerA.getPlayerName();
        } else if (controlador.getInicia()
                == controlador.getPlayerB()) {
            val = playerB.getPlayerName();
        }

        current = Toolkit.textLabel(currentPlayer.getPlayerName(), controlador.getBits());
        Text currentLabel = Toolkit.textLabel("Turno: ", controlador.getBits());

        pathPlayerA = "res/" + playerA.getPlayerSymbol() + ".png";
        pathPlayerB = "res/" + playerB.getPlayerSymbol() + ".png";

        HBox currentBox = new HBox(); 
        currentBox.setAlignment(Pos.CENTER);

        currentBox.getChildren()
                .addAll(currentLabel, current);

        currentBox.setSpacing(
                10);

        Button next = Toolkit.normalButton("Continuar", 150, controlador.getBits());
        Button menu = Toolkit.normalButton("Menu", 80, controlador.getBits());
        
        next.setOnMouseClicked((e) -> {
            if (currentPlayer.getPlayerTypeAI() == 1) {
                next.setStyle("-fx-background-color: #6600ff");
                System.out.println("calling calculate");
                this.calculateMove();
                System.out.println(possibleBestMove);
                this.tablero.pressCell(this.possibleBestMove[0], this.possibleBestMove[1]);

                if (currentPlayer.getPlayerTypeAI() == 1) {
                    next.setStyle("-fx-background-color: #dcccff");
                } else {
                    next.setStyle("-fx-background-color: transparent");

                }
            }
        });

        next.setOnMouseEntered((e) -> {
            if (currentPlayer.getPlayerTypeAI() == 1) {
                next.setStyle("-fx-background-color: #dcccff");
            }
        });
        next.setOnMouseExited((e) -> {
            if (currentPlayer.getPlayerTypeAI() == 1) {
                next.setStyle("-fx-background-color: transparent");
            }
        });
        
        menu.setOnMouseClicked((e) -> {
            controlador.setScene(controlador.menuNombre);
        });

        menu.setOnMouseEntered((e) -> {
                menu.setStyle("-fx-background-color: #dcccff");
        });
        menu.setOnMouseExited((e) -> {
                menu.setStyle("-fx-background-color: transparent");
        });

        toolbox.getChildren().addAll(currentBox, next, menu);

        //currentTurn.getChildren().add(current);
        //currentTurn.setAlignment(Pos.CENTER);
        root.setTop(toolbox);

        VBox center = new VBox();
        HBox main = new HBox();

        center.getChildren()
                .add(generateTable());
        center.setAlignment(Pos.CENTER);

        main.getChildren()
                .add(center);
        main.setAlignment(Pos.CENTER);

        root.setCenter(main);

        if (this.currentPlayer.getPlayerTypeAI()
                == 1) {
            this.calculateMove();
            this.tablero.pressCell(this.possibleBestMove[0], this.possibleBestMove[1]);
        }

        return new Scene(root, controlador.getDimensiones()[0], controlador.getDimensiones()[1]);
    }

    public Text getCurrent() {
        return current;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    @Override
    public GridPane generateTable() {
        GridPane container = new GridPane();
        container.setStyle("-fx-background-color: #454545");
        container.setHgap(3);
        container.setVgap(3);

        tablero = new Tablero();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                Celda celda = this.initCell(i, j, tablero);
                tablero.addCell(celda);
                //boton.setStyle("-fx-background-color: #223312");
                container.add(celda.getButton(), i * 2, j * 2);

            }
        }

        return container;
    }

    public Celda initCell(int i, int j) {
        Celda celda = new Celda(new Image(pathPlayerA), new Image(pathPlayerB), i, j, this);
        celda.setMainAction();
        celda.setTablero(tablero);
        celda.setHolder(-1); //Holder -1 significa ninguno de los 2

        return celda;
    }

    public Celda initCell(int i, int j, Tablero tablero) {
        Celda celda = new Celda(new Image(pathPlayerA), new Image(pathPlayerB), i, j, this);
        celda.setMainAction();
        celda.setTablero(tablero);
        celda.setHolder(-1); //Holder -1 significa ninguno de los 2

        return celda;
    }

    @Override
    public void setParent(ControladorPantallas controlador) {
        this.controlador = controlador;
    }

    public void calculateMove() {
        this.utilityTree = new Tree<Tablero>(this.tablero);
        //System.out.println(this.utilityTree);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tablero tablero = this.tablero.copy();
                //System.out.println(tablero);
                if (tablero.setValue(i, j, this.currentPlayer.getPlayerAssignation())) {
                    //System.out.println(tablero);
                    this.utilityTree.addChildren(tablero);
                }
                //System.out.println("\n");

            }
        }
        //System.out.println(this.utilityTree);
        for (Tree<Tablero> treeTab : utilityTree.getChildren()) {
            //System.out.println("current: " + treeTab + "\n");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Tablero newtablero = treeTab.getRoot().getContent().copy();
                    if (newtablero.setValue(i, j, this.currentPlayer.getOppossing().getPlayerAssignation())) {
                        //System.out.println(newtablero+"\n");
                        treeTab.addChildren(newtablero);
                    }
                }
            }
        }
        System.out.println(this.utilityTree);
        this.tablero.setCurrentUtiliy(-100000);
        Tablero best = null;
        //System.out.println("currentVal: " + this.currentVal);
        for (Tree<Tablero> treeTab : utilityTree.getChildren()) {
            List<Integer> childVal = new ArrayList<>();
            treeTab.getRoot().getContent().setCurrentUtiliy(1000000000);
            for (Tree<Tablero> child : treeTab.getChildren()) {
                Tablero tab = child.getRoot().getContent();
                if (treeTab.getRoot().getContent().getCurrentUtiliy() > tab.utility(this.currentPlayer.getPlayerAssignation(), this.currentPlayer.getOppossing().getPlayerAssignation())) {
                    treeTab.getRoot().getContent().setCurrentUtiliy(tab.utility(this.currentPlayer.getPlayerAssignation(), this.currentPlayer.getOppossing().getPlayerAssignation()));
                }
                //tab.setCurrentUtiliy(currentVal);
                //childVal.add(tab.utility(this.currentVal, this.getOpossing()));
            }

            if (this.tablero.getCurrentUtiliy() < treeTab.getRoot().getContent().getCurrentUtiliy()) {
                best = treeTab.getRoot().getContent();
                this.tablero.setCurrentUtiliy(treeTab.getRoot().getContent().getCurrentUtiliy());
                //System.out.println("bestloop: " + best);
                //System.out.println("utility: " + treeTab.getRoot().getContent().getCurrentUtiliy());
            }
        }

        System.out.println("best= " + best);
        //System.out.println("utility= " + this.tablero.getCurrentUtiliy());
        //int idxBest = mainVals.get(mainVals.size() - 1);
        //System.out.println("best= " + idxBest);
        //Tree<Tablero> best = utilityTree.getChildren().get(idxBest);

        this.possibleBestMove = best.getLast();
        System.out.println("coords: " + this.possibleBestMove[0] + " , " + this.possibleBestMove[1]);
    }

    public int[] getPossibleBestMove() {
        return possibleBestMove;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setPossibleBestMove(int[] possibleBestMove) {
        this.possibleBestMove = possibleBestMove;
    }

    public void setWinner(int winner) {
        tablero.disableCells();

        Dialog<String> dialog = new Dialog<String>();

        dialog.setTitle("Resultado");
        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
        //Setting the content of the dialog
        if (winner == 0) {
            dialog.setContentText("El ganador es: " + this.playerA.getPlayerName());
        } else if (winner == 1) {
            dialog.setContentText("El ganador es: " + this.playerB.getPlayerName());
        } else {
            dialog.setContentText("Es un empate");
        }
//Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);

        dialog.showAndWait();

        System.out.println("winner: " + winner);

    }
}
