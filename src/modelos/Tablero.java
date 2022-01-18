/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import estructuras.Tree;
import java.util.ArrayList;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class Tablero {

    //Jugador es 0 y Computador es 1
    ArrayList<Celda> celdas;
    int lastI;
    int lastJ;
    int currentUtiliy;
    
    public void disableCells(){
        for(int i = 0; i < this.celdas.size(); i++){
            celdas.get(i).button.setDisable(true);
        }
    }

    public int getCurrentUtiliy() {
        return currentUtiliy;
    }

    public void setCurrentUtiliy(int currentUtiliy) {
        this.currentUtiliy = currentUtiliy;
    }

    public Tablero() {
        this(new ArrayList<Celda>());
    }

    public Tablero(ArrayList<Celda> celdas) {
        this.celdas = celdas;
        lastI = -1;
        lastJ = -1;
        this.currentUtiliy = -1;
    }

    public boolean setValue(int i, int j, int holder) {
        Celda celda = celdas.get(this.getIndex(i, j));
        if (celda.holder == -1) {
            celdas.get(this.getIndex(i, j)).setHolder(holder);
            lastI = i;
            lastJ = j;
            return true;
        }
        return false;
    }
    
    public int[] getLast(){
        return new int[]{lastI, lastJ};
    }

    public Celda getCell(int i, int j) {
        return celdas.get(getIndex(i, j));
    }

    private int getIndex(int i, int j) {
        return j * 3 + i;
    }

    public void pressCell(int i, int j) {
        celdas.get(getIndex(i, j)).click();
        lastI = i;
        lastJ = j;
    }

    public void addCell(Celda celda) {
        celdas.add(celda);
    }

    public Tablero copy() {
        ArrayList<Celda> newCeldas = new ArrayList<Celda>();
        for(Celda celda: celdas){
            newCeldas.add(celda.copy(this));
        }
        return new Tablero(newCeldas);
    }

    public int utility(int playerA, int playerB) {
        return calculateOptions(playerA) - calculateOptions(playerB);
    }

    private int horizontalOptions(int player) {
        int options = 0;
        for (int i = 0; i < 3; i++) { //calcular todas las posibilidades horizontales
            int count = 0;
            for (int j = 0; j < 3; j++) {
                if (this.getCell(i, j).holder != -1 && this.getCell(i, j).holder != player) {
                    break;
                }
                count++;
                if (count == 3) {
                    options++;
                }
            }
        }
        return options;
    }

    @Override
    public String toString() {
        return "Tablero{" + "celdas=" + celdas + '}';
    }

    private int verticalOptions(int player) {
        int options = 0;
        for (int j = 0; j < 3; j++) {
            int count = 0;

            for (int i = 0; i < 3; i++) {
                if (this.getCell(i, j).holder != -1 && this.getCell(i, j).holder != player) {
                    break;
                }
                count++;
                if (count == 3) {
                    options++;
                }
            }
        }
        return options;
    }
    
    public int hasWinner(){
        int holder = -1;
        
        if(this.lastI == -1 && this.lastJ == -1) return -1;
        
        for(int i = 0; i < 3; i ++){
            holder = this.checkHorizontal(i);
            if(holder != -1) return holder;
        }
        
        for(int j = 0; j < 3; j++){
            holder = this.checkVertical(j);
            if(holder != -1) return holder;
        }
        
        if(celdas.get(this.getIndex(0, 0)).getHolder() ==  celdas.get(this.getIndex(1, 1)).getHolder() 
                && celdas.get(this.getIndex(1, 1)).getHolder() == celdas.get(this.getIndex(2, 2)).getHolder()) 
            return celdas.get(this.getIndex(1, 1)).getHolder();
        
        holder = 0;
        holder += celdas.get(this.getIndex(0, 2)).getHolder();
        holder += celdas.get(this.getIndex(1, 1)).getHolder();
        holder += celdas.get(this.getIndex(2, 0)).getHolder();
        
        if(celdas.get(this.getIndex(0, 2)).getHolder() == celdas.get(this.getIndex(1, 1)).getHolder() 
                && celdas.get(this.getIndex(1, 1)).getHolder() == celdas.get(this.getIndex(2, 0)).getHolder()) 
            return celdas.get(this.getIndex(1, 1)).getHolder();
        
        for(int i = 0; i < 9; i++){
            if(celdas.get(i).getHolder() == -1) return -1;
        }
        
        return 2; //Retorna empates        
    }
    
    private int checkVertical(int j){
        int holder = celdas.get(this.getIndex(0, j)).getHolder();
        for(int i = 1; i < 3; i ++){
            if(holder != celdas.get(this.getIndex(i, j)).getHolder()){
                return -1;
            }
        }
        return holder;
    }
    
    private int checkHorizontal(int i){
        int holder = celdas.get(this.getIndex(i, 0)).getHolder();
        for(int j = 1; j < 3; j ++){
            if(holder != celdas.get(this.getIndex(i, j)).getHolder()){
                return -1;
            }
        }
        
        return holder;
    }

    private int diagonalOptions(int player) {
        int options = 0;
        int count = 0;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (i == j) {
                    if (this.getCell(i, j).holder != -1 && this.getCell(i, j).holder != player) {
                        break;
                    }
                    count++;
                    if (count == 3) {
                        options++;
                    }
                }
            }
        }
        return options;
    }

    private int inverseDiagonalOptions(int player) {
        int options = 0;
        int count = 0;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                //System.out.println("i: " + i + " j: " + j);
                if (i == flip(j)) {
                    //System.out.println("holder: " + this.getCell(i, j).holder);
                    if (this.getCell(i, j).holder != -1 && this.getCell(i, j).holder != player) {
                        break;
                    }
                    count++;
                    if (count == 3) {
                        options++;
                    }
                }
            }
        }
        return options;
    }

    private int flip(int cord) {
        if (cord == 0) {
            return 2;
        }
        if (cord == 1) {
            return 1;
        }
        if (cord == 2) {
            return 0;
        }

        return -1;
    }

    private int calculateOptions(int player) {
        int options = this.horizontalOptions(player) + this.verticalOptions(player) + this.diagonalOptions(player) + this.inverseDiagonalOptions(player);
        return options;
    }

}
