package impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import contracts.EnvironmentContract;
import contracts.PlayerContract;
import data.Cell;
import data.Command;
import data.Coord;
import data.Hole;
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
    private Set<Hole> holes;
    private CommandManager commandManager;

    public EngineImpl(Player player, Environment environment){
        this.player = player;
        this.envi = environment;
    }

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

    public Set<Hole> getHoles() {
        return this.holes;
    }

    public synchronized Command getNextCommand() {
        return this.nextCommand;
    }

    public void addHole(int x, int y){
        this.holes.add(new Hole(x, y));
    }

    public void init(EditableScreen screen, int playerX, int playerY, List<Coord> guards, List<Coord> treasures, CommandManager cm, Engine engineInstance) {
        // this.envi = new EnvironmentContract(new EnvironmentImpl());
        this.envi.init(screen);
        // this.player = new PlayerImpl();
        // this.player = new PlayerContract(new PlayerImpl());
        this.player.init(playerX, playerY, engineInstance);
        this.status = Status.Playing;
        this.nextCommand = Command.NONE;
        this.holes = new HashSet<>();
        this.commandManager = cm;
        // TODO reste (treasures and guards)
    }

    public void step() {
        // TODO Gestion des autres step
        // TODO 1 => Si joueur sur un trésor, trésor disparait
        // TODO 2 => Si plus de trésors, jeu gagné
        // TODO 3 => Si joueur dans la meme case qu'un garde
        if(this.commandManager != null){
            this.nextCommand = this.commandManager.receiveCurrentCommand();
        }else{
            this.nextCommand = Command.NONE;
        }
        this.player.step();
        Iterator<Hole> holesIter = this.holes.iterator();
        while(holesIter.hasNext()){
            Hole h = holesIter.next();
            h.incTime();
            if(h.getTime() == 15){
                this.envi.fill(h.getX(), h.getY());
                if(this.player.getHgt() == h.getY() && this.player.getCol() == h.getX()){
                    this.status = Status.Loss;
                }
                // TODO Gerer le fait de reset les gardes
                holesIter.remove();
            }
        }
    }

    public void display() {
        //TODO Separer affichage dans une autre classe

        if(this.status == Status.Win){
            System.out.println("============================");
            System.out.println("YOU WIN !!!");
            System.out.println("============================");
        }
        else if(this.status == Status.Loss){
            System.out.println("============================");
            System.out.println("YOU LOSE ...");
            System.out.println("============================");
        }else{
            System.out.println("============================");
            for(int i=this.envi.getHeight()-1; i >= 0; i--){
                for(int j=0; j < this.envi.getWidth(); j++){
                    Cell c = this.envi.getCellNature(j, i);
                    if(Util.constainsCharacter(envi.getCellContent(j, i))){
                        System.out.print("J");
                    }else{
                        switch(c){
                            case PLT:
                                System.out.print("≡");
                                break;
                            case EMP:
                                System.out.print(" ");
                                break;
                            case MTL:
                                System.out.print("▓");
                                break;
                            case LAD:
                                System.out.print("H");
                                break;
                            case HDR:
                                System.out.print("─");
                                break;
                            case HOL:
                                System.out.print("U");
                                break;
                                
                        }
                    }
                }
                System.out.println();
            }
            System.out.println("============================");
        }
    }

}