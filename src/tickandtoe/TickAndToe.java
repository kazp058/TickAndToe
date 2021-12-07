/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickandtoe;

import escenas.ControladorPantallas;
import escenas.EscenaControlable;
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
    
    public static String loginName = "login";
    public static String menuNombre = "menu";
    public static String registerName = "register";
    public Stage stage;
    Font bits;
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            this.bits = Font.loadFont(new FileInputStream("src/res/upheavtt.ttf"), 20);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TickAndToe.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.stage = primaryStage;
        
        ControladorPantallas controlador = new ControladorPantallas(this.stage);
        
        controlador.setSize(new Integer[]{1280,720});
        
        EscenaControlable menuPrincipal = new escenaMenu(this.bits);
        
        controlador.addScene(menuNombre, menuPrincipal);
        controlador.setScene(menuNombre);
        
        Scene scene = controlador.getScene();
        
        primaryStage.setTitle("Tick & Toe");
        primaryStage.setScene(scene);
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
