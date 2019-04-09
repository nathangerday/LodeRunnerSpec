package services;

import java.util.List;

import data.Coord;
import data.Item;
import data.Status;

public interface Engine{
    /* Observators */

    public Environment getEnvironment();
    public Player getPlayer();
    // public List<Guard> getGuards();
    public List<Item> getTreasures();
    public Status getStatus();
    // public Command getNextCommand(); //TODO Commands

    /* Constructors */
    public void init(EditableScreen screen, List<Coord> guards, List<Coord> treasures);
    

    /* Operators */
    public void step();

}