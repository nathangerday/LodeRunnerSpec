package services;

import java.util.List;
import java.util.Set;

import data.Command;
import data.Coord;
import data.Hole;
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
    public Set<Hole> getHoles();
    
    /* Constructors */
    //\pre screen.isPlayable()
    //\pre playerX >= 0
    //\pre playerY >= 0
    //\pre playerX < screen.getWidth()
    //\pre playerY < screen.getHeight()
    //TODO Guards / Treasures
    public void init(EditableScreen screen, int playerX, int playerY, List<Coord> guards, List<Coord> treasures);
    
    
    /* Operators */

    //\pre getStatus() == Status.Playing
    public void step();

    //\pre next != null
    public void setNextCommand(Command next);
    
    //\pre \not \Exists Hole h \in getHoles() \with (h.getX() == x && h.getY() == y)
    //\pre getEnvironment().getCellNature(x, y) == Cell.HOL
    public void addHole(int x, int y);    
    
    
    public void display();

}