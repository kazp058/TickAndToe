/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escenas;

import modelos.Tablero;

/**
 *
 * @author PC
 */
public class HumanPlayer extends PlayerDecorator{

    public HumanPlayer(IPlayer iplayer) {
        super(iplayer);
    }

       @Override
    public void makeMove(Tablero tablero) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
