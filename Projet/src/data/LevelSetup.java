package data;

import java.util.ArrayList;
import java.util.List;

import impl.GuardImpl;
import services.EditableScreen;
import services.Environment;
import services.Guard;
import services.Player;

public class LevelSetup{
    private EditableScreen screen;
    private List<Coord> guards;
    private List<Coord> items;
    private Coord player;

    public LevelSetup(EditableScreen screen, List<Coord> guards, List<Coord> items, Coord player){
        this.screen = screen;
        this.guards = guards;
        this.items = items;
        this.player = player;
    }

    public EditableScreen getScreen(){
        return this.screen;
    }

    public List<Coord> getGuards(){
        return this.guards;
    }
    public List<Coord> getItems(){
        return this.items;
    }

    public Coord getPlayer(){
        return this.player;
    }

}