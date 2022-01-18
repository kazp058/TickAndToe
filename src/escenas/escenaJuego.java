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

    int[] possibleBestMove;

    Tree<Tablero> utilityTree;

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

        HBox toolbox = new HBox();
        HBox currentTurn = new HBox();

        playerSymbol = controlador.getJugador();
        if (playerSymbol == 'o') {
            computerSymbol = 'x';
        } else if (playerSymbol == 'x') {
            computerSymbol = 'o';
        }

        String val = "";

        if (controlador.getInicia() == 0) {
            val = "Tu";
        } else if (controlador.getInicia() == 1) {
            val = "Computadora";
        }

        current = Toolkit.textLabel(val, controlador.getBitsTitle());
        //Text currentLabel = Toolkit.textLabel("Turno: ", controlador.getBits());

        
        pathPlayer = "res/" + playerSymbol + ".png";
        pathComputer = "res/" + computerSymbol + ".png";

        currentVal = controlador.getInicia();

        toolbox.getChildren().addAll(current);
        
        toolbox.setSpacing(10);
        
        //currentTurn.getChildren().add(current);
        //currentTurn.setAlignment(Pos.CENTER);

        root.setTop(toolbox);

        VBox center = new VBox();
        HBox main = new HBox();
        center.getChildren().add(generateTable());
        center.setAlignment(Pos.CENTER);

        main.getChildren().add(center);
        main.setAlignment(Pos.CENTER);

        root.setCenter(main);
        
        if(this.currentVal == 1){
            this.calculateMove();
            this.tablero.pressCell(this.possibleBestMove[0], this.possibleBestMove[1]);
        }
        
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

                Celda celda = this.initCell(i, j, tablero);
                tablero.addCell(celda);
                //boton.setStyle("-fx-background-color: #223312");
                container.add(celda.getButton(), i * 2, j * 2);

            }
        }

        return container;
    }

    public Celda initCell(int i, int j) {
        Celda celda = new Celda(new Image(pathPlayer), new Image(pathComputer), i, j, this);
        celda.setMainAction();
        celda.setTablero(tablero);
        celda.setHolder(-1); //Holder -1 significa ninguno de los 2

        return celda;
    }

    public Celda initCell(int i, int j, Tablero tablero) {
        Celda celda = new Celda(new Image(pathPlayer), new Image(pathComputer), i, j, this);
        celda.setMainAction();
        celda.setTablero(tablero);
        celda.setHolder(-1); //Holder -1 significa ninguno de los 2

        return celda;
    }

    @Override
    public void setParent(ControladorPantallas controlador) {
        this.controlador = controlador;
    }

    private int getOpossing() {
        if (this.currentVal == 1) {
            return 0;
        }
        return 1;
    }

    public void calculateMove() {
        this.utilityTree = new Tree<Tablero>(this.tablero);
        //System.out.println(this.utilityTree);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tablero tablero = this.tablero.copy();
                //System.out.println(tablero);
                if (tablero.setValue(i, j, this.currentVal)) {
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
                    if (newtablero.setValue(i, j, this.getOpossing())) {
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
                if(treeTab.getRoot().getContent().getCurrentUtiliy() > tab.utility(this.currentVal, this.getOpossing())){
                    treeTab.getRoot().getContent().setCurrentUtiliy(tab.utility(this.currentVal, this.getOpossing()));
                }
                //tab.setCurrentUtiliy(currentVal);
                //childVal.add(tab.utility(this.currentVal, this.getOpossing()));
            }
            
            if(this.tablero.getCurrentUtiliy() < treeTab.getRoot().getContent().getCurrentUtiliy()){
                best = treeTab.getRoot().getContent();
                this.tablero.setCurrentUtiliy( treeTab.getRoot().getContent().getCurrentUtiliy());
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
        System.out.println(possibleBestMove[0] + " , " + possibleBestMove[1]);
    }

    public int[] getPossibleBestMove() {
        return possibleBestMove;
    }

    public void setPossibleBestMove(int[] possibleBestMove) {
        this.possibleBestMove = possibleBestMove;
    }
    
    
    public void setWinner(int winner){
        tablero.disableCells();
        
    }
}
