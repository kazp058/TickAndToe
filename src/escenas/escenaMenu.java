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
import modelos.Player;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class escenaMenu implements EscenaControlable {

    ControladorPantallas controlador;

    Player playerA;
    Player playerB;

    public escenaMenu() {

    }

    @Override
    public Scene getScene() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);

        playerA = new Player();
        playerB = new Player();

        //GridPane menu = new GridPane();
        //menu.setHgap(5);
        //menu.setVgap(10);
        //menu.setAlignment(Pos.CENTER);
        Text selecPer = Toolkit.textLabel("Elije el icono del Jugador A:", controlador.getBits());
        Button botonX = Toolkit.imageButton("res/x.png");
        Button botonO = Toolkit.imageButton("res/o.png");

        Text selecStart = Toolkit.textLabel("Elije quien inicia:", controlador.getBits());


        Button startJugadorA = Toolkit.normalButton("Jugador A", 150, controlador.getBits());
        Button startJugadorB = Toolkit.normalButton("Jugador B", 150, controlador.getBits());

        Button botonAJugador = Toolkit.imageButton("res/person.png");
        Button botonAComputador = Toolkit.imageButton("res/ai.png");

        Button botonBJugador = Toolkit.imageButton("res/person.png");
        Button botonBComputador = Toolkit.imageButton("res/ai.png");

        Text jugadorA = Toolkit.textLabel("Jugador A", controlador.getBits());
        Text jugadorB = Toolkit.textLabel("Jugador B", controlador.getBits());

        startJugadorA.setOnMouseClicked((e) -> {
            controlador.setInicia(playerA);
            startJugadorA.setStyle("-fx-background-color: #6600ff");
            startJugadorB.setStyle("-fx-background-color: transparent");
        });

        startJugadorA.setOnMouseEntered((e) -> {
            if (controlador.getInicia() == null || controlador.getInicia() == playerB) {
                startJugadorA.setStyle("-fx-background-color: #dcccff");
            }
        });
        startJugadorA.setOnMouseExited((e) -> {
            if (controlador.getInicia() == null || controlador.getInicia() == playerB) {
                startJugadorA.setStyle("-fx-background-color: transparent");
            }
        });
        
        startJugadorB.setOnMouseClicked((e) -> {
            controlador.setInicia(playerB);
            startJugadorB.setStyle("-fx-background-color: #6600ff");
            startJugadorA.setStyle("-fx-background-color: transparent");
        });
        
        startJugadorB.setOnMouseEntered((e) -> {
            if (controlador.getInicia() == null || controlador.getInicia() == playerA) {
                startJugadorB.setStyle("-fx-background-color: #dcccff");
            }
        });
        startJugadorB.setOnMouseExited((e) -> {
            if (controlador.getInicia() == null || controlador.getInicia() == playerA) {
                startJugadorB.setStyle("-fx-background-color: transparent");
            }
        });
        
        //Icono
        botonX.setOnMouseClicked((e) -> {
            playerA.setPlayerSymbol('x');
            playerB.setPlayerSymbol('o');
            botonX.setStyle("-fx-background-color: #6600ff");
            botonO.setStyle("-fx-background-color: transparent");
        });

        botonX.setOnMouseEntered((e) -> {
            if (Character.compare(playerA.getPlayerSymbol(), ' ') == 0 || Character.compare(playerA.getPlayerSymbol(), 'o') == 0 ) {
                botonX.setStyle("-fx-background-color: #dcccff");
            }
        });
        botonX.setOnMouseExited((e) -> {
            if (Character.compare(playerA.getPlayerSymbol(), ' ') == 0 || Character.compare(playerA.getPlayerSymbol(), 'o') == 0 ) {
                botonX.setStyle("-fx-background-color: transparent");
            }
        });
        
        botonO.setOnMouseClicked((e) -> {
            playerA.setPlayerSymbol('o');
            playerB.setPlayerSymbol('x');
            botonO.setStyle("-fx-background-color: #6600ff");
            botonX.setStyle("-fx-background-color: transparent");
        });
        
        botonO.setOnMouseEntered((e) -> {
            if (Character.compare(playerA.getPlayerSymbol(), ' ') == 0 || Character.compare(playerA.getPlayerSymbol(), 'x') == 0 ) {
                botonO.setStyle("-fx-background-color: #dcccff");
            }
        });
        botonO.setOnMouseExited((e) -> {
            if (Character.compare(playerA.getPlayerSymbol(), ' ') == 0 || Character.compare(playerA.getPlayerSymbol(), 'x') == 0 ) {
                botonO.setStyle("-fx-background-color: transparent");
            }
        });

        //PLAYER A
        this.setButtons(botonAJugador, botonAComputador, playerA);
        this.setButtons(botonBJugador, botonBComputador, playerB);

        HBox iniciarBox = new HBox();

        iniciarBox.setAlignment(Pos.CENTER);
        iniciarBox.setStyle("-fx-background-color: transparent");

        Button iniciar = Toolkit.normalButton("Iniciar", 130, controlador.getBits());
        iniciar.setOnMouseEntered((e) -> {
            if (controlador.getInicia() != null && playerA.getPlayerTypeAI() != -1 && playerB.getPlayerTypeAI() != -1 && Character.compare(playerA.getPlayerSymbol(), ' ') != 0) {
                iniciar.setStyle("-fx-background-color: #dcccff");
            }
        });
        iniciar.setOnMouseClicked((e) -> {
            if (controlador.getInicia() != null && playerA.getPlayerTypeAI() != -1 && playerB.getPlayerTypeAI() != -1  && Character.compare(playerA.getPlayerSymbol(), ' ') != 0) {
                iniciar.setStyle("-fx-background-color: #6600ff");
                
                playerA.setPlayerName("Jugador A");
                playerB.setPlayerName("Jugador B");
                
                controlador.setPlayerA(playerA);
                controlador.setPlayerB(playerB);
                controlador.setScene(controlador.juegoNombre);
            }
        });
        iniciar.setOnMouseExited((e) -> {
            iniciar.setStyle("-fx-background-color: transparent");

        });
        iniciarBox.getChildren().add(iniciar);

        HBox characters = new HBox();
        
        characters.setAlignment(Pos.CENTER);
        characters.setSpacing(80);
        
        VBox playerABox = new VBox();
        playerABox.setSpacing(10);
        
        VBox playerBBox = new VBox();
        playerBBox.setSpacing(10);
        
        playerABox.setAlignment(Pos.CENTER);
        playerBBox.setAlignment(Pos.CENTER);

        HBox buttonsA = new HBox();
        buttonsA.setSpacing(30);
        HBox buttonsB = new HBox();
        buttonsB.setSpacing(30);

        buttonsA.getChildren().addAll(botonAJugador, botonAComputador);
        buttonsB.getChildren().addAll(botonBJugador, botonBComputador);

        playerABox.getChildren().addAll(jugadorA, buttonsA);
        playerBBox.getChildren().addAll(jugadorB, buttonsB);

        characters.getChildren().add(playerABox);
        characters.getChildren().add(playerBBox);

        VBox start = new VBox();
        start.setAlignment(Pos.CENTER);
        start.setSpacing(10);
        
        HBox startButtons = new HBox();
        startButtons.setAlignment(Pos.CENTER);
        startButtons.setSpacing(80);

        startButtons.getChildren().addAll(startJugadorA, startJugadorB);

        start.getChildren().addAll(selecStart, startButtons);
        
        VBox symbols = new VBox();
        symbols.setSpacing(10);
        symbols.setAlignment(Pos.CENTER);
        
        HBox symbolsButtons = new HBox();
        symbolsButtons.setAlignment(Pos.CENTER);
        symbolsButtons.setSpacing(80);
        
        symbolsButtons.getChildren().addAll(botonX, botonO);
        symbols.getChildren().addAll(selecPer, symbolsButtons);
        

        //menu.add(jugadorA, 0, 0, 2, 2);
        //menu.add(jugadorB, 2, 0, 2, 2);
        //menu.add(botonJugador, 2, 2, 1, 1);
        //menu.add(botonComputador, 2, 2, 1, 1);
        //menu.add(selecPer, 0, 0, 2, 2);
        //menu.add(botonX, 0, 2, 1, 1);
        //menu.add(botonO, 2, 2, 1, 1);
        //menu.add(selecStart, 0, 3, 2, 2);
        //menu.add(botonJugador, 0, 5, 1, 1);
        //menu.add(botonComputador, 2, 5, 1, 1);
        //menu.add(iniciarBox, 0, 8, 4, 4);
        root.getChildren().addAll(characters, start,symbols,iniciarBox);
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

    private void setButtons(Button jugador, Button computador, Player player) {
        jugador.setOnMouseClicked((e) -> {
            player.setPlayerTypeAI(0);
            jugador.setStyle("-fx-background-color: #6600ff");
            computador.setStyle("-fx-background-color: transparent");
        });
        jugador.setOnMouseEntered((e) -> {
            if (player.getPlayerTypeAI() == -1 || player.getPlayerTypeAI() == 1) {
                jugador.setStyle("-fx-background-color: #dcccff");
            }
        });
        jugador.setOnMouseExited((e) -> {
            if (player.getPlayerTypeAI() == -1 || player.getPlayerTypeAI() == 1) {
                jugador.setStyle("-fx-background-color: transparent");
            }
        });
        computador.setOnMouseClicked((e) -> {
            player.setPlayerTypeAI(1);
            computador.setStyle("-fx-background-color: #6600ff");
            jugador.setStyle("-fx-background-color: transparent");
        });

        computador.setOnMouseEntered((e) -> {
            if (player.getPlayerTypeAI() == -1 || player.getPlayerTypeAI() == 0) {
                computador.setStyle("-fx-background-color: #dcccff");
            }
        });
        computador.setOnMouseExited((e) -> {
            if (player.getPlayerTypeAI() == -1 || player.getPlayerTypeAI() == 0) {
                computador.setStyle("-fx-background-color: transparent");
            }
        });
    }
}
