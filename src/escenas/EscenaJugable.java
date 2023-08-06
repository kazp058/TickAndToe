/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package escenas;

import modelos.Celda;
import modelos.Player;
import modelos.Tablero;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author PC
 */
public interface EscenaJugable {
    public GridPane generateTable();
    public Celda initCell(int i, int j);
    public Celda initCell(int i, int j, Tablero tablero);
    public void calculateMove();
    public void setWinner(int winner);
    public Player getCurrentPlayer();
    public Player getPlayerA();
    public Player getPlayerB();
    public int[] getPossibleBestMove();
    public Text getCurrent();
    public void setCurrentPlayer(Player currentPlayer);
}
