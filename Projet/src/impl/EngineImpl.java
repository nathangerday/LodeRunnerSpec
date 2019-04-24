package impl;

import java.util.ArrayList;
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
import data.ItemType;
import data.Status;
import services.EditableScreen;
import services.Engine;
import services.Environment;
import services.Guard;
import services.Player;
import services.ScreenManager;
import utils.CommandManager;
import utils.Factory;
import utils.Util;

public class EngineImpl implements Engine {
    private ScreenManager sm;
    private Environment envi;
    private Player player;
    private List<Item> treasures;
    private List<Guard> guards;
    private Status status;
    private Command nextCommand;
    private Set<Hole> holes;
    private CommandManager commandManager;
    private int nbTreasures;
    private int currentLevel;

    private Engine engineInstance; // necessary for contracts
    

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

    public void init(ScreenManager sm, CommandManager cm, Engine engineInstance) {
        this.currentLevel = 0;
        this.sm = sm;
        this.engineInstance = engineInstance;
        this.envi = Factory.createEnvironment();
        this.envi.init(sm.getScreen(currentLevel));
        this.player = Factory.createPlayer();
        this.player.init(sm.getPlayerFromScreen(currentLevel).getX(), sm.getPlayerFromScreen(currentLevel).getY(), engineInstance);
        this.status = Status.Playing;
        this.nextCommand = Command.NONE;
        this.holes = new HashSet<>();
        this.commandManager = cm;
        this.guards = new ArrayList<>();
        this.treasures = new ArrayList<>();
        for(Coord c : sm.getGuardsFromScreen(currentLevel)){
            Guard tmp = new GuardImpl();
            tmp.init(this.envi, c.getX(), c.getY(), this.player);
            this.guards.add(tmp);
        }
        this.nbTreasures = 0;
        for(Coord c : sm.getItemsFromScreen(currentLevel)){
            Item tmp = new Item(ItemType.Treasure, c.getX(), c.getY());
            this.treasures.add(tmp);
            this.envi.addToCellContent(c.getX(), c.getY(), tmp);
            this.nbTreasures++;
        }
    }

    public void step() {
        // TODO Gestion des autres step
        // TODO 3 => Si joueur dans la meme case qu'un garde
        if(this.commandManager != null){
            this.nextCommand = this.commandManager.receiveCurrentCommand();
        }else{
            this.nextCommand = Command.NONE;
        }

        if(Util.removeTreasure(envi.getCellContent(player.getCol(), player.getHgt()))){
            this.nbTreasures--;
            if(this.nbTreasures == 0 && this.currentLevel < this.sm.getNbScreen() - 1){
                this.currentLevel++;
                this.envi = Factory.createEnvironment();
                this.envi.init(sm.getScreen(currentLevel));
                this.player = Factory.createPlayer();
                this.player.init(sm.getPlayerFromScreen(currentLevel).getX(), sm.getPlayerFromScreen(currentLevel).getY(), engineInstance);
                this.nextCommand = Command.NONE;
                this.holes = new HashSet<>();
                this.guards.clear();
                this.treasures.clear();
                for(Coord c : sm.getGuardsFromScreen(currentLevel)){
                    Guard tmp = new GuardImpl();
                    tmp.init(this.envi, c.getX(), c.getY(), this.player);
                    this.guards.add(tmp);
                }
                this.nbTreasures = 0;
                for(Coord c : sm.getItemsFromScreen(currentLevel)){
                    Item tmp = new Item(ItemType.Treasure, c.getX(), c.getY());
                    this.treasures.add(tmp);
                    this.envi.addToCellContent(c.getX(), c.getY(), tmp);
                    this.nbTreasures++;
                }
                return;
            }else if(this.nbTreasures == 0){
                this.status = Status.Win;
                return;
            }
        }

        this.player.step();
        for(Guard g : this.guards){
            g.step();
        }
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
                        if(Util.getCharacter(envi.getCellContent(j, i)) instanceof Player){
                            System.out.print("J");
                        }else if(Util.getCharacter(envi.getCellContent(j, i)) instanceof Guard){
                            System.out.print("G");
                        }
                    }else if(Util.containsTreasure(envi.getCellContent(j, i))){
                        System.out.print("Ø");
                        
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