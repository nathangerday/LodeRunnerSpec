package services;

import data.Command;
import data.Coord;
import data.GuardType;

public interface Guard extends Character{
    /* Observators */
    public int getId();
    public Engine getEngine();
    public GuardType getNature();
    public Coord getInitCoords();
    public Character getTarget();
    
    public int getIdCounter();
    public boolean isCarryingTreasure();
    public int getTimeInHole();

    //TODO in inv
    public Command getBehaviour();

    
    /* Constructors */
    //\pre e != null
    //\pre x >= 0
    //\pre x < e.getEnvironment().getWidth()
    //\pre y >= 0
    //\pre y < e.getEnvironment().getHeight()
    //\pre \not e.getEnvironment().getCellNature(x, y) \in {MTL, PLT}
    //\post getId() == getIdCounter() - 1
    //\post getEngine() == e
    //\post getNature() == NORMAL
    //\post getInitCoords().getX() == x
    //\post getInitCoords().getY() == y
    //\post getTarget() == target
    //\post !isCarryingTreasure()
    //\post getTimeInHole() == 0
    public void init(Engine e, int x, int y, Character target);
    

    //\pre getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.HOL
    //\post getCol()@pre == 0 \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1) \in {MTL, PLT} \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1) \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post getCol()@pre != 0 && 
    //      \not getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1) \in {MTL, PLT} &&
    //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1) &&
    //      \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)
    //      \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre + 1
    //\post getCol()@pre != 0 && 
    //      \not getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1) \in {MTL, PLT} &&
    //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1) &&
    //      \not \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)
    //      \impl getCol() == getCol()@pre - 1 && getHgt() == getHgt()@pre + 1
    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == getTimeInHole()@pre
    //\post getIdCounter() == getIdCounter()@pre
    public void climbLeft();

    
    //\pre getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.HOL
    //\post getCol()@pre == getEnvi().getWidth() - 1 \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1) \in {MTL, PLT} \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1) \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post getCol()@pre != getEnvi().getWidth() - 1 && 
    //      \not getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1) \in {MTL, PLT} &&
    //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1) &&
    //      \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)
    //      \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre + 1
    //\post getCol()@pre != getEnvi().getWidth() - 1 && 
    //      \not getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1) \in {MTL, PLT} &&
    //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1) &&
    //      \not \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)
    //      \impl getCol() == getCol()@pre + 1 && getHgt() == getHgt()@pre + 1
    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == getTimeInHole()@pre
    //\post getIdCounter() == getIdCounter()@pre
    public void climbRight();

    //\pre getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.PLT
    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == 0
    //\post getCol() == getInitCoords().getX()
    //\post getHgt() == getInitCoords().getY()
    //\post getIdCounter() == getIdCounter()@pre
    public void moveToInitCoords();

    public void step();

}