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
import modelos.Player;

/**
 *
 * @author Kevin Zhang <kzhang@espol.edu.ec>
 */
public class ControladorPantallas {

    private HashMap<String, EscenaControlable> pantallas = new HashMap<>();
    private Scene escenaActual;
    private Stage stage;

    private Player playerA;
    private Player playerB; //Si es 0 inicia el jugador, si es 1 inicia el computador

    private Player inicia;

    public static String juegoNombre = "playground";
    public static String menuNombre = "menu";
    public static String registerName = "register";

    public ControladorPantallas() {

        this.playerA = new Player();
        this.playerB = new Player();
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
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

    public Player getInicia() {
        return inicia;
    }

    public void setInicia(Player inicia) {
        this.inicia = inicia;
    }

    public void setBits(Font bits) {
        this.bits = bits;
    }

    public ControladorPantallas(Stage stage) {
        this.stage = stage;

        this.playerA = new Player();
        this.playerB = new Player();

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
