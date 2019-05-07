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
    public CommandManager getCommandManager();

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
    public int getCurrentLevel();

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

    
    /* Operators */


    //\def death = getNbLifes()@pre == 1 
    //                  \implies getNbLifes() == 0 \and getStatus() = Loss
    //              \and
    //              getNbLifes()@pre > 1
    //                  \implies getNbLifes() = getNbLifes()@pre - 1
    //                  \and getScore() = getScoreAtStartOfLevel()@pre 
    //                  \and loadlevel(getCurrentLevel()@pre)

    //\def loadlevel(no) = getCurrentLevel() = no
    //                  \and getStatus() = Playing
    //                  \and getEnvironment().getHeight() == sm.getScreen(no).getHeight()
    //                  \and getEnvironment().getWidth() == sm.getScreen(no).getWidth()
    //                  \and \Forall i in |0, getEnvironment().getWidth() - 1]
    //                      \Forall j in [0, getEnvironment().getHeight() - 1]
    //                          sm.getScreen(getCurrentLlevel()@pre).getCellNature(i, j) == getEnvironment().getCellNature(i, j))
    //                  \and getPlayer().getCol() == sm.getPlayerFromScreen(no).getX();
    //                  \and getPlayer().getHgt() == sm.getPlayerFromScreen(no).getY();
    //                  \and \Forall CoordItem c \in sm.getItemsFromScreen(no)
    //                            \Exists Item i \in getTreasures() \with (i.getHgt() == c.getY() && i.getCol() == c.getX() && i.getNature() == c.getItemType())
    //                  \and \Forall CoordGuard c \ in sm.getGuardsFromScreen(no)
    //                            \Exists Guard g \in getGuards() \with (g.getHgt() == c.getY() && g.getCol() == g.getX() && g.getNature() == c.getType())
    //                  \and getNextCommand() == NONE
    //                  \and getHoles() == {}
    //                  \and getNbLifes() == 3
    //                  \and getScoreAtStartOfLevel() == getScoreAtStartOfLevel()@pre
    //                  \and getScreenManager() == getScreenManager()@pre
    //                  \and getNbTreasuresLeft() == \Count CoordItem c \in sm.getItemsFromScreen(0) \with (c.getItemType() == Treasure)

    
    //\pre getStatus() == Status.Playing
    //\post getCommandManager()@pre != null \implies getNextCommand() == getCommandManager().peekCurrentCommand()@pre
    //\post getCommandManager()@pre == null \implies getNextCommand() == NONE
    //\post \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //          \and getNbLifes()@pre == 1
    //          \implies getNbLifes() == 0 \and getStatus() = Loss
    //\post \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //          \and getNbLifes()@pre > 1
    //          \implies death
    //\post \not \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //      \and \Exists Treasure t \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //              \implies t \not \in getTreasure() \and getNbTreasuresLeft() = getNbTreasuresLeft()@pre - 1 \and getScore() = getScore()@pre + 1
    //                  \and (getNbTreasuresLeft()@pre == 1 \and getCurrentLevel()@pre < getScreenManager().getNbScreen() - 1
    //                              \implies getCurrentLevel() = getCurrentLevel()@pre + 1 \and loadLevel(getCurrentLevel()@pre + 1))
    //                  \and (getNbTreasuresLeft()@pre == 1 \and getCurrentLevel()@pre = getScreenManager().getNbScreen() - 1
    //                              \implies getStatus() = Win
    //\post \not \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //          \and \Exists Item i \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //              \implies getPlayer().getCurrentlyHeldItem().getNature() = i.getNature()
    //\post \not \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //          \and getEnvironment().getCellNature(getPlayer().getCol()@pre, getPlayer().getHgt()@pre - 1)@pre = TRP
    //                  \implies getEnvironment().getCellNature(getPlayer().getCol()@pre, getPlayer().getHgt()@pre - 1) = EMP
    //\post \not (\Exists Treasure t \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //              \and getNbTreasuresLeft() = 1)
    //      \not \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //      \and \Forall Hole h \in getHoles()@pre
    //              h.getTime() < 14 \impl \Exists Hole o \in getHoles() \with 
    //                  (o.getX() == h.getX() \and o.getY() == h.getY() \and o.getTime() == h.getTime() + 1)
    //              h.getTime() == 14 
    //                  \impl \not \Exists Hole o \in getHoles() \with (o.getX() == h.getX() \and o.getY() == h.getY())
    //              h.getTime() == 14 \and getPlayer().getX() == h.getX() \and getPlayer().getY() == h.getY() 
    //                  \impl death
    //\post \not \Exists Hole h \in getHoles()@pre \with (h.getTime() == 14 \and h.getX() == getPlayer().getX() \and h.getY() == getPlayer().getY())
    //      \and \not \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //      \and \not (\Exists Treasure t \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
    //              \and getNbTreasuresLeft() = 1)
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