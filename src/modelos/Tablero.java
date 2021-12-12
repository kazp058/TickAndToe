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
    
    public Tablero(){
        tablero = new ArrayList<Character>();
        
    }
    
    public int getValue(int index){
        return tablero.get(index);
    }
    
    public int setValue(int index, Character value){
        return tablero.set(index, value);
    }
    
}
