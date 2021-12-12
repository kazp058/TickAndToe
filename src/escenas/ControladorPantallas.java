/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenas;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class ControladorPantallas {

    private HashMap<String, EscenaControlable> pantallas = new HashMap<>();
    private Scene escenaActual;
    private Stage stage;

    private char jugador;
    private short inicia; //Si es 0 inicia el jugador, si es 1 inicia el computador

    public static String juegoNombre = "playground";
    public static String menuNombre = "menu";
    public static String registerName = "register";

    public ControladorPantallas() {

        this.jugador = '-';
        this.inicia = -1;
    }

    public char getJugador() {
        return jugador;
    }

    public void setJugador(char jugador) {
        this.jugador = jugador;
    }

    private Integer[] dimensiones;
    private Font bits;
    private Font bitsTitle;

    public Font getBitsTitle() {
        return bitsTitle;
    }

    public void setBitsTitle(Font bitsTitle) {
        this.bitsTitle = bitsTitle;
    }

    public Integer[] getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(Integer[] dimensiones) {
        this.dimensiones = dimensiones;
    }

    public Font getBits() {
        return bits;
    }

    public short getInicia() {
        return inicia;
    }

    public void setInicia(short inicia) {
        this.inicia = inicia;
    }

    public void setBits(Font bits) {
        this.bits = bits;
    }

    public ControladorPantallas(Stage stage) {
        this.stage = stage;
        this.jugador = '-';
        this.inicia = -1;
    }

    public Stage getStage() {
        return stage;
    }

    public void addScene(String nombre, EscenaControlable escena) {
        escena.setParent(this);
        pantallas.put(nombre, escena);
    }

    public void setScene(String nombre) {
        this.escenaActual = pantallas.get(nombre).getScene();
        stage.setScene(escenaActual);
    }

    public Scene getScene() {
        return this.escenaActual;
    }

    public void setSize(Integer[] dimensiones) {
        this.dimensiones = dimensiones;
    }

    public void removeScene(String name) {
        pantallas.remove(name);
    }

    public Integer[] getSize() {
        return this.dimensiones;
    }

    public void setCurrentScene(Scene escena) {
        this.escenaActual = escena;
    }

    public void getFirstMove() {

    }

    public void getPlayerSign() {

    }
}
