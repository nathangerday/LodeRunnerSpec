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
import utils.Util;

public class EngineImpl implements Engine {
    private Environment envi;
    private Player player;
    private List<Item> treasures;
    private Status status;
    private Command nextCommand;

    private Scanner scanner = new Scanner(System.in);

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
        //TODO reste
    }

    public void step() {
        //TODO Gestion des autres step
        //TODO Gerer ailleurs les commandes (ex: CommandManager)
        if(scanner.next().equals("q")){
            this.nextCommand = Command.MOVEL;
        }
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
                    }
                }
            }
            System.out.println();
        }
    }
}