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
    //\post getStatus == Playing
    //\post getNextCommand() == NONE
    //\post getHoles == {}
    public void init(EditableScreen screen, int playerX, int playerY, List<Coord> guards, List<Coord> treasures);
    
    
    /* Operators */

    //\pre getStatus() == Status.Playing
    //\post getPlayer() == getPlayer()@pre.step()
    //\post \Forall Hole h \in getHoles()@pre
    //          h.getTime() < 14 \impl \Exists Hole o \in getHoles() \with 
    //              (o.getX() == h.getX() \and o.getY() == h.getY() \and o.getTime() == h.getTime() + 1)
    //          h.getTime() == 14 
    //              \impl \not \Exists Hole o \in getHoles() \with (o.getX() == h.getX() \and o.getY() == h.getY())
    //          h.getTime() == 14 \and getPlayer().getX() == h.getX() \and getPlayer().getY() == h.getY() 
    //              \impl getStatus() == Loss
    public void step();

    //\pre \not \Exists Hole h \in getHoles() \with (h.getX() == x && h.getY() == y)
    //\pre getEnvironment().getCellNature(x, y) == HOL
    //\post getHoles() == getHoles()@pre \Union {h} \with (h.getX() == x \and h.getY() == y)
    public void addHole(int x, int y);    
    
    //\post getTreasures() == getTreasures()@pre
    //\post getStatus() == getStatus()@pre
    //\post getNextCommand() == getNextCommand()@pre
    //\post getHoles() == getHoles()@pre
    public void display();

}