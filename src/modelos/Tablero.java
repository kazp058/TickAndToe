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
    
    ArrayList<Character> tablero;
    ArrayList<Celda> celdas;
    
    
    public Tablero(){
        tablero = new ArrayList<Character>();
        
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
    
}
