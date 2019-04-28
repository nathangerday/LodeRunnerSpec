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
    private int lifes;
    private int score;
    private int scoreAtLevelStart;
    private int currentLevel;

    private Engine engineInstance; // necessary for contracts
    

    public Environment getEnvironment() {
        return this.envi;
    }

    public ScreenManager getScreenManager(){
        return this.sm;
    }

    public Player getPlayer() {
        return this.player;
    }

    public List<Item> getTreasures() {
        return this.treasures;
    }
    
    public List<Guard> getGuards(){
        return this.guards;
    }

    public Status getStatus() {
        return this.status;
    }

    public Set<Hole> getHoles() {
        return this.holes;
    }
    
    public int getNbLifes(){
        return this.lifes;
    }

    public int getScore(){
        return this.score;
    }
    
    public int getScoreAtStartOfLevel(){
        return this.scoreAtLevelStart;
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
        this.status = Status.Playing;
        this.nextCommand = Command.NONE;
        this.commandManager = cm;
        this.guards = new ArrayList<>();
        this.treasures = new ArrayList<>();
        this.lifes = 3;
        this.score = 0;
        loadLevel(0);
    }

    public void step() {
        if(this.commandManager != null){
            this.nextCommand = this.commandManager.receiveCurrentCommand();
        }else{
            this.nextCommand = Command.NONE;
        }

        if(Util.containsGuard(envi.getCellContent(player.getCol(), player.getHgt()))){
            death();
            return;
        }

        Item t;
        if((t = Util.removeTreasure(envi.getCellContent(player.getCol(), player.getHgt()))) != null){
            this.treasures.remove(t);
            this.nbTreasures--;
            this.score++;
            if(this.nbTreasures == 0 && this.currentLevel < this.sm.getNbScreen() - 1){
                this.currentLevel++;
                loadLevel(currentLevel);
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
                    death();
                }
                if(Util.containsGuard(envi.getCellContent(h.getX(), h.getY()))){
                    Util.getGuard(envi.getCellContent(h.getX(), h.getY())).moveToInitCoords();
                }
                holesIter.remove();
            }
        }
    }

    public void display() {

        if(this.status == Status.Win){
            System.out.println("============================");
            System.out.println("YOU WIN !!!");
            System.out.println("Your score is " + this.score);
            System.out.println("============================");
        }
        else if(this.status == Status.Loss){
            System.out.println("============================");
            System.out.println("YOU LOSE ...");
            System.out.println("Your score is " + this.score);
            System.out.println("============================");
        }else{
            System.out.println("============================");
            System.out.println("Score : " + this.score);
            System.out.println("Lifes : " + this.lifes);
            for(int i=this.envi.getHeight()-1; i >= 0; i--){
                for(int j=0; j < this.envi.getWidth(); j++){
                    Cell c = this.envi.getCellNature(j, i);
                    if(Util.containsPlayer(envi.getCellContent(j, i))){
                        System.out.print("J");
                    }else if(Util.containsGuard(envi.getCellContent(j, i))){
                        Guard g = Util.getGuard(envi.getCellContent(j, i));
                        if(g.isCarryingTreasure()){
                            System.out.print("T");
                        }else{
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


    private void death(){
        this.lifes--;
        if(lifes == 0){
            this.status = Status.Loss;
        }else{
            this.score = this.scoreAtLevelStart;
            loadLevel(this.currentLevel);
        }
    }

    private void loadLevel(int i){
        this.envi = Factory.createEnvironment();
        this.envi.init(sm.getScreen(currentLevel));
        this.player = Factory.createPlayer();
        this.player.init(sm.getPlayerFromScreen(currentLevel).getX(), sm.getPlayerFromScreen(currentLevel).getY(), engineInstance);
        this.nextCommand = Command.NONE;
        this.holes = new HashSet<>();
        this.scoreAtLevelStart = this.score;
        this.guards.clear();
        this.treasures.clear();
        for(Coord c : sm.getGuardsFromScreen(currentLevel)){
            Guard tmp = new GuardImpl();
            tmp.init(this, c.getX(), c.getY(), this.player);
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
    
    

}