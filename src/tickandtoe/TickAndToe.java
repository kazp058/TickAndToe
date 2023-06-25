/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickandtoe;

import escenas.ControladorPantallas;
import escenas.EscenaControlable;
import escenas.escenaJuego;
import escenas.escenaMenu;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class TickAndToe extends Application {
   
    public Stage stage;
    Font bits;
    Font bitsTitle;
    @Override
    public void start(Stage primaryStage) {
        
        this.bits = Toolkit.makeFont("src/res/upheavtt.ttf", 20);
        this.bitsTitle =  Toolkit.makeFont("src/res/upheavtt.ttf", 40);
        this.stage = primaryStage;
        
        primaryStage.setTitle("Tick & Toe");
        primaryStage.setScene(Toolkit.makeScene(this.bits, this.bitsTitle, this.stage));
        primaryStage.show();
        primaryStage.setOnCloseRequest((e) -> {
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
