package impl;

import java.util.List;
import java.util.Scanner;

import data.Cell;
import data.Command;
import data.Coord;
import data.Item;
import data.Status;
import services.EditableScreen;
import services.Engine;
import services.Environment;
import services.Player;
import utils.CommandManager;
import utils.Util;

public class EngineImpl implements Engine {
    private Environment envi;
    private Player player;
    private List<Item> treasures;
    private Status status;
    private Command nextCommand;


    public Environment getEnvironment() {
        return this.envi;
    }

    public Player getPlayer() {
        return this.player;
    }

    public List<Item> getTreasures() {
        return this.treasures;
    }

    public Status getStatus() {
        return this.status;
    }

    public Command getNextCommand() {
        return this.nextCommand;
    }
    

    public void init(EditableScreen screen, int playerX, int playerY, List<Coord> guards, List<Coord> treasures) {
        this.envi = new EnvironmentImpl();
        this.envi.init(screen);
        this.player = new PlayerImpl();
        this.player.init(5, 2, this);
        this.status = Status.Playing;
        //TODO reste (treasures and guards)
    }

    public void step() {
        //TODO Gestion des autres step
        //TODO 1 => Si joueur sur un trésor, trésor disparait
        //TODO 2 => Si plus de trésors, jeu gagné
        //TODO 3 => Gestion de Holes
        //TODO 4 => Si joueur dans la meme case qu'un garde
        this.nextCommand = CommandManager.getNextUserCommandInput();
        this.player.step();
    }

    public void display() {
        //TODO Separer afficahge dans une autre classe
        for(int i=this.envi.getHeight()-1; i >= 0; i--){
            for(int j=0; j < this.envi.getWidth(); j++){
                Cell c = this.envi.getCellNature(j, i);
                if(Util.constainsCharacter(envi.getCellContent(j, i))){
                    System.out.print("J");
                }else{
                    switch(c){
                        case PLT:
                            System.out.print("X");
                            break;
                        case EMP:
                            System.out.print(".");
                            break;
                        case MTL:
                            System.out.print("W");
                            break;
                        case LAD:
                            System.out.print("H");
                            break;
                        case HDR:
                            System.out.print("_");
                            break;
                        case HOL:
                            System.out.print("U");
                            break;
                            
                    }
                }
            }
            System.out.println();
        }
    }
}