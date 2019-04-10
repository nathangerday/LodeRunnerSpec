package impl;

import java.util.List;

import data.Command;
import data.Coord;
import data.Item;
import data.Status;
import services.EditableScreen;
import services.Engine;
import services.Environment;
import services.Player;

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
    

    public void init(EditableScreen screen, List<Coord> guards, List<Coord> treasures) {

    }

    public void step() {
        //TODO
        this.player.step();
    }
}