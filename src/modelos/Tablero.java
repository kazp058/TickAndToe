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
public class Tablero{
    
    //Jugador es 0 y Computador es 1
    
    ArrayList<Character> tablero;
    ArrayList<Celda> celdas;
    
    
    public Tablero(){
        tablero = new ArrayList<Character>();
        celdas = new ArrayList<Celda>();
        
    }
    
    public Character getValue(int i, int j){
        return tablero.get(getIndex(i,j));
    }
    
    public void setValue(int i, int j, Character value){
        tablero.set(getIndex(i,j), value);
    }
    
    public Celda getCell(int i, int j){        
        return celdas.get(getIndex(i,j));
    }
    
    private int getIndex(int i, int j){
        return j * 3 + i;
    }
    
    public void pressCell(int i, int j){
        celdas.get(getIndex(i,j)).getButton().arm();
    }
    
    public void addCell(Celda celda){
        celdas.add(celda);
    }
    
    public int utility(){
        return calculateOptions(0) - calculateOptions(1); 
    }
    
    public int calculateOptions(int player){
        for(int i = 0; i < 3; i++){ //calcular todas las posibilidades horizontales
            int pos = i * 3;
            System.out.println("tablero: " + celdas.size());
            for( int j = 0; j < 3; j++){
                System.out.println("i: " + i + " j: " + j + " holder " + this.getCell(i, j).holder);
                if(this.getCell(i, j).holder != player){
                    //break;
                }
            }
            
        }
        return 0;
    }
    
    
}
