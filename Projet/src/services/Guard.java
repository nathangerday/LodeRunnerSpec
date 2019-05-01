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

    
    //\def isFalling == \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR, HOL} \and
    //                  getEnvi().getCellContent(getCol()@pre, getHgt()@pre) \in {EMP, HDR, HOL} \and
    //                  \not \Exists Guard g \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)

    //\post \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \and \not isCarryingTreasure()@pre 
    //      \impl isCarryingTreasure() \and \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
    //\post \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \and isCarryingTreasure()@pre 
    //      \impl isCarryingTreasure() \and \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
    //\post \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \and \not isCarryingTreasure()@pre
    //      \impl \not isCarryingTreasure() \and \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
    //\post \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre
    //      \and isCarryingTreasure()@pre
    //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1)@pre == HOL
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre)@pre \in {LAD, HDR, HOL}
    //      \and \not \Exists Guard g \ in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)@pre
    //      \impl \not isCarryingTreasure() \and  \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
    //\post \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre
    //      \and isCarryingTreasure()@pre
    //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1)@pre != HOL
    //          \or \getEnvi().getCellNature(getCol()@pre, getHgt()@pre)@pre \in {LAD, HDR, HOL}
    //          \or \Exists Guard g \ in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)@pre
    //      \impl isCarryingTreasure() \and \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
    //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \and getTimeInHole()@pre < 5 \impl getTimeInHole() == getTimeInHole()@pre + 1
    //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre) 
    //      \and getTimeInHole()@pre >= 5 
    //      \and getBehaviour()@pre == MOVEL 
    //      \impl getTimeInHole() == 0 \and climbLeft()
    //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre) 
    //      \and getTimeInHole()@pre >= 5 
    //      \and getBehaviour()@pre == MOVER
    //      \impl getTimeInHole() == 0 \and climbRight()
    //\post isFalling \impl goDown()
    //\post \not isFalling \and getEnvi().getCellnature() != HOL \and getBehaviour() == MOVEL \impl goLeft()
    //\post \not isFalling \and getEnvi().getCellnature() != HOL \and getBehaviour() == MOVER \impl goRight()
    //\post \not isFalling \and getEnvi().getCellnature() != HOL \and getBehaviour() == MOVEU \impl goUp()
    //\post \not isFalling \and getEnvi().getCellnature() != HOL \and getBehaviour() == MOVED \impl goDown()
    //\post getIdCounter() == getIdCounter()@pre
    public void step();

}