package services;

import java.util.List;
import java.util.Set;

import data.Command;
import data.Coord;
import data.Hole;
import data.Item;
import data.Status;
import utils.CommandManager;

public interface Engine{
    /* Observators */

    public Environment getEnvironment();
    public Player getPlayer();
    public List<Guard> getGuards();
    public List<Item> getTreasures();
    public Status getStatus();
    public Command getNextCommand();
    public Set<Hole> getHoles();
    public int getNbLifes();
    public int getScore();
    public int getScoreAtStartOfLevel();
    public ScreenManager getScreenManager();
    public int getNbTreasuresLeft();

    /* Constructors */
    //\pre sm.getNbScreen() >= 1
    //\post getEnvironment().getHeight() == sm.getScreen(0).getHeight()
    //\post getEnvironment().getWidth() == sm.getScreen(0).getWidth()
    //\post \Forall i in |0, getEnvironment().getWidth() - 1]
    //          \Forall j in [0, getEnvironment().getHeight() - 1]
    //              sm.getScreen(0).getCellNature(i, j) == getEnvironment().getCellNature(i, j))
    //\post getPlayer().getCol() == sm.getPlayerFromScreen(0).getX();
    //\post getPlayer().getHgt() == sm.getPlayerFromScreen(0).getY();
    //\post getPlayer().getEngine() == engineInstance
    //\post \Forall CoordItem c \in sm.getItemsFromScreen(0)
    //          \Exists Item i \in getTreasures() \with (i.getHgt() == c.getY() && i.getCol() == c.getX() && i.getNature() == c.getItemType())
    //\post \Forall CoordGuard c \ in sm.getGuardsFromScreen(0)
    //          \Exists Guard g \in getGuards() \with (g.getHgt() == c.getY() && g.getCol() == g.getX() && g.getNature() == c.getType())
    //\post getStatus() == Playing
    //\post getNextCommand() == NONE
    //\post getHoles() == {}
    //\post getNbLifes() == 3
    //\post getScore() == 0
    //\post getScoreAtStartOfLevel() == 0
    //\post getScreenManager() == sm
    //\post getNbTreasuresLeft() == \Count CoordItem c \in sm.getItemsFromScreen(0) \with (c.getItemType() == Treasure)
    public void init(ScreenManager sm, CommandManager cm, Engine engineInstance);
    
    /* Invariants */
    //TODO Synchronisation entre l'environment et personnage
    
    
    
    /* Operators */

    //TODO pre / post
    //\pre getStatus() == Status.Playing
    //\post getPlayer() == getPlayer()@pre.step()
    //\post \Forall Hole h \in getHoles()@pre
    //          h.getTime() < 14 \impl \Exists Hole o \in getHoles() \with 
    //              (o.getX() == h.getX() \and o.getY() == h.getY() \and o.getTime() == h.getTime() + 1)
    //          h.getTime() == 14 
    //              \impl \not \Exists Hole o \in getHoles() \with (o.getX() == h.getX() \and o.getY() == h.getY())
    //          h.getTime() == 14 \and getPlayer().getX() == h.getX() \and getPlayer().getY() == h.getY() 
    //              \impl getStatus() == Loss
    //\post \not \Exists Hole h \in getHoles()@pre \with (h.getTime() == 14 \and h.getX() == getPlayer().getX() \and h.getY() == getPlayer().getY())
    //      \impl getStatus() == Playing
    public void step();

    //\pre \not \Exists Hole h \in getHoles() \with (h.getX() == x && h.getY() == y)
    //\pre getEnvironment().getCellNature(x, y) == HOL
    //\post getHoles() == getHoles()@pre \Union {h} \with (h.getX() == x \and h.getY() == y)
    //\post getEnvironment() == getEnvironment()@pre
    //\post getPlayer() == getPlayer()@pre
    //\post getGuards() == getGuards()@pre
    //\post getTreasures() == getTreasures()@pre
    //\post getStatus() == getStatus()@pre
    //\post getNextCommand() == getNextCommand()@pre
    //\post getNbLifes() == getNbLifes()@pre
    //\post getScore() == getScore()@pre
    //\post getScoreAtStartOfLevel() == getScoreAtStartOfLevel()@pre
    //\post getScreenManager() == getScreenManager()@pre
    //\post getNbTreasuresLeft() == getNbTreasuresLeft()@pre
    public void addHole(int x, int y);    
    
    //\post getEnvironment() == getEnvironment()@pre
    //\post getPlayer() == getPlayer()@pre
    //\post getGuards() == getGuards()@pre
    //\post getTreasures() == getTreasures()@pre
    //\post getStatus() == getStatus()@pre
    //\post getNextCommand() == getNextCommand()@pre
    //\post getHoles() == getHoles()@pre
    //\post getNbLifes() == getNbLifes()@pre
    //\post getScore() == getScore()@pre
    //\post getScoreAtStartOfLevel() == getScoreAtStartOfLevel()@pre
    //\post getScreenManager() == getScreenManager()@pre
    //\post getNbTreasuresLeft() == getNbTreasuresLeft()@pre
    public void display();
    
    public Engine copy();
}