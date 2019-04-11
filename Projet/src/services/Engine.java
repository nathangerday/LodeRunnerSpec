package services;

import java.util.List;

import data.Command;
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
    public Command getNextCommand();
    public void setNextCommand(Command next);

    /* Constructors */
    public void init(EditableScreen screen, int playerX, int playerY, List<Coord> guards, List<Coord> treasures);
    

    /* Operators */
    public void step();
    public void addHole(int x, int y);    
    public void display();

}