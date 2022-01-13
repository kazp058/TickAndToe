/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class Tablero {

    //Jugador es 0 y Computador es 1
    ArrayList<Character> tablero;
    ArrayList<Celda> celdas;

    public Tablero() {
        tablero = new ArrayList<Character>();
        celdas = new ArrayList<Celda>();

    }

    public Character getValue(int i, int j) {
        return tablero.get(getIndex(i, j));
    }

    public void setValue(int i, int j, Character value) {
        tablero.set(getIndex(i, j), value);
    }

    public Celda getCell(int i, int j) {
        return celdas.get(getIndex(i, j));
    }

    private int getIndex(int i, int j) {
        return j * 3 + i;
    }

    public void pressCell(int i, int j) {
        celdas.get(getIndex(i, j)).getButton().arm();
    }

    public void addCell(Celda celda) {
        celdas.add(celda);
    }

    public int utility() {
        return calculateOptions(0) - calculateOptions(1);
    }

    private int horizontalOptions(int player) {
        int options = 0;
        for (int i = 0; i < 3; i++) { //calcular todas las posibilidades horizontales
            int pos = i * 3;
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

    private int diagonalOptions(int player) {
        int options = 0;
        int count = 0;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                System.out.println("i: " + i + " j: " + j);
                if (i == j) {
                    System.out.println("holder: " + this.getCell(i, j).holder);
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
    
    private int inverseDiagonalOptions(int player){
        int options = 0;
        int count = 0;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                System.out.println("i: " + i + " j: " + j);
                if (i == flip(j)) {
                    System.out.println("holder: " + this.getCell(i, j).holder);
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
    
    private int flip(int cord){
        if(cord == 0) return 2;
        if(cord == 1) return 1;
        if(cord == 2) return 0;
        
        return -1;
    }

    private int calculateOptions(int player) {
        int options = this.horizontalOptions(player) + this.verticalOptions(player) + this.diagonalOptions(player) + this.inverseDiagonalOptions(player);
        return options;
    }

}
