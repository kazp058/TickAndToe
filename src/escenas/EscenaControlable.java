/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenas;

import javafx.scene.Scene;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public interface EscenaControlable {
    
    public abstract Scene getScene();
    public abstract void setParent(ControladorPantallas controlador);
    
}
