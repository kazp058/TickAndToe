/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class escenaMenu implements EscenaControlable{
    
    ControladorPantallas controlador;
    Font font;

    public escenaMenu(Font font) {
        this.font = font;
    }

    @Override
    public Scene getScene() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);
        
        Text label = textLabel("wolaa");
        root.getChildren().add(label);
        
        return new Scene(root, controlador.getSize()[0], controlador.getSize()[1]);
    }

    @Override
    public void setParent(ControladorPantallas controlador) {
        this.controlador = controlador;
    }
    
        private Text textLabel(String mensaje) {
        Text texto = new Text(mensaje);
        texto.setFont(font);
        texto.setFill(Color.WHITE);
        texto.setStrokeType(StrokeType.OUTSIDE);
        texto.setStroke(Color.BLACK);
        texto.setStrokeWidth(1);
        texto.setTextAlignment(TextAlignment.CENTER);
        return texto;
    }
    
}
