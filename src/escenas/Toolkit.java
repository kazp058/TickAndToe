/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import tickandtoe.TickAndToe;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class Toolkit {

    public static Text textLabel(String mensaje, Font font) {
        Text texto = new Text(mensaje);
        texto.setFont(font);
        texto.setFill(Color.WHITE);
        texto.setStrokeType(StrokeType.OUTSIDE);
        texto.setStroke(Color.BLACK);
        texto.setStrokeWidth(1);
        texto.setTextAlignment(TextAlignment.CENTER);
        return texto;
    }

    public static Button imageButton(String path) {
        Image img = new Image(path);
        ImageView view = new ImageView(img);

        view.setFitHeight(80);
        view.setPreserveRatio(true);

        Button button = new Button();
        button.setPrefSize(80, 80);
        button.setGraphic(view);
        button.setBackground(new Background(new BackgroundFill(null, null, null)));

        //button.setStyle("-fx-border-color: #6600ff; -fx-border-width: 5px;");
        return button;
    }

    public static Button emptyImageButton() {
        Button button = new Button();
        button.setPrefSize(100, 100);
        //button.setBackground(new Background(new BackgroundFill(null, null, null)));

        button.setStyle("-fx-border-color: #e3e3e3;");
        return button;
    }

    public static Button normalButton(String label, double parentWidth, Font font) {
        Button button = new Button(label);
        button.setPrefSize(parentWidth, 80);
        button.setBackground(new Background(new BackgroundFill(null, null, null)));
        button.setFont(font);
        //button.setStyle("-fx-border-color: #6600ff; -fx-border-width: 5px;");
        return button;
    }
    
    public static Font makeFont(String url1, int size){
        Font font;
        try {
            font = Font.loadFont(new FileInputStream(url1), size);

            return font;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TickAndToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Scene makeScene(Font bits, Font bitsTitle, Stage stage){
            
        ControladorPantallas controlador = new ControladorPantallas(stage);
        controlador.setBits(bits);
        controlador.setBitsTitle(bitsTitle);
        controlador.setSize(new Integer[]{1280,720});
                
        EscenaControlable menuPrincipal = new escenaMenu();
        EscenaControlable juego = new escenaJuego();
        
        controlador.addScene(controlador.menuNombre, menuPrincipal);
        controlador.addScene(controlador.juegoNombre, juego);
        controlador.setScene(controlador.menuNombre);
        
        Scene scene = controlador.getScene();
        return scene;
    }
}
