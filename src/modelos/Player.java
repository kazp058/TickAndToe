/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class Player {
    
    int playerTypeAI; // 0 es humano y 1 es computadora, -1 no asignado
    String playerName; //El nombre que haya ingresado el jugador
    char playerSymbol;
    Player oppossing;

    public Player() {
        this(-1, "" ,-1);
    }

    int playerAssignation; //Numero enviado por el sistema

    public Player(int playerTypeAI, String playerName, int playerAssignation) {
        this.playerTypeAI = playerTypeAI;
        this.playerName = playerName;
        this.playerAssignation = playerAssignation;
        
        this.playerSymbol = ' ';
    }

    public Player getOppossing() {
        return oppossing;
    }

    public void setOppossing(Player oppossing) {
        this.oppossing = oppossing;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(char playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public int getPlayerTypeAI() {
        return playerTypeAI;
    }

    public void setPlayerTypeAI(int playerTypeAI) {
        this.playerTypeAI = playerTypeAI;
    }
    
    
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerAssignation() {
        return playerAssignation;
    }

    public void setPlayerAssignation(int playerAssignation) {
        this.playerAssignation = playerAssignation;
    }
}
