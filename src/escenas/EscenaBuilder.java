/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escenas;

import modelos.Player;
import modelos.Tablero;

/**
 *
 * @author PC
 */
public interface EscenaBuilder {
    public void reset();
    public void setPlayerA(Player player);
    public void setTablero(Tablero tablero);
}
