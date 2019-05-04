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
    public int getTimeLeftParalyzed();

    //TODO in inv
    public Command getBehaviour();

    /* Invariants */

    // \inv (\Exists Guard g \in getEnvi().getCellContent(getCol(), getHgt()))
    //          \impl g == this


    
    /* Constructors */


    //\pre e != null
    //\pre x >= 0
    //\pre x < e.getEnvironment().getWidth()
    //\pre y >= 0
    //\pre y < e.getEnvironment().getHeight()
    //\pre \not e.getEnvironment().getCellNature(x, y) \in {MTL, PLT, DOR, NGU}
    //\post getId() == getIdCounter() - 1
    //\post getEngine() == e
    //\post getNature() == NORMAL
    //\post getInitCoords().getX() == x
    //\post getInitCoords().getY() == y
    //\post getTarget() == target
    //\post !isCarryingTreasure()
    //\post getTimeInHole() == 0
    //\post getTimeLeftParalyzed() == 0
    public void init(Engine e, int x, int y, Character target);
    

    //\pre getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.HOL
    //\post getCol()@pre == 0 \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1)@pre \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post getCol()@pre != 0 && 
    //      \not getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} &&
    //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1)@pre &&
    //      \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)@pre
    //      \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre + 1
    //\post getCol()@pre != 0 && 
    //      \not getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} &&
    //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1)@pre &&
    //      \not \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)@pre
    //      \impl getCol() == getCol()@pre - 1 && getHgt() == getHgt()@pre + 1
    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == getTimeInHole()@pre
    //\post getIdCounter() == getIdCounter()@pre
    //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
    public void climbLeft();

    
    //\pre getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.HOL
    //\post getCol()@pre == getEnvi().getWidth() - 1 \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1)@pre \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
    //\post getCol()@pre != getEnvi().getWidth() - 1 && 
    //      \not getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} &&
    //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1)@pre &&
    //      \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)@pre
    //      \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre + 1
    //\post getCol()@pre != getEnvi().getWidth() - 1 && 
    //      \not getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} &&
    //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1)@pre &&
    //      \not \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)@pre
    //      \impl getCol() == getCol()@pre + 1 && getHgt() == getHgt()@pre + 1
    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == getTimeInHole()@pre
    //\post getIdCounter() == getIdCounter()@pre
    //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
    public void climbRight();

    //\post \Exists Guard g \in getEnvi().getCellContent(getInitCoords().getX(), getInitCoords().getY())@pre
    //      \impl g.getCol() == g.getInitCoords.getX() \and g.getHgt() == g.getInitCoords().getY()
    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == 0
    //\post getCol() == getInitCoords().getX()
    //\post getHgt() == getInitCoords().getY()
    //\post getIdCounter() == getIdCounter()@pre
    //\post getTimeLeftParalyzed() == 0
    public void moveToInitCoords();

    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == 0
    //\post getCol() == getCol()@pre
    //\post getHgt() == getHgt()@pre
    //\post getIdCounter() == getIdCounter()@pre
    //\post getTimeLeftParalyzed() == 10
    public void paralyze();

    
    //\def isFalling == \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR, HOL} \and
    //                  getEnvi().getCellContent(getCol()@pre, getHgt()@pre) \in {EMP, HDR, HOL, NPL} \and
    //                  \not \Exists Guard g \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)

    //\post getTimeLeftParalyzed()@pre == 0 \impl getTimeLeftParalyzed() == 0
    //\post getTimeLeftParalyzed()@pre > 0 \and isFalling \impl getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
    //\post getTimeLeftParalyzed()@pre > 0 \and \not isFalling \impl getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre - 1
    //\post \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \and \not isCarryingTreasure()@pre 
    //      \impl isCarryingTreasure() \and \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
    //\post \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \and isCarryingTreasure()@pre \and \not isFalling 
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
    //\post getTimeLeftParalyzed()@pre == 0 \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == HOL \and getTimeInHole()@pre < 5 \impl getTimeInHole() == getTimeInHole()@pre + 1
    //\post getTimeLeftParalyzed()@pre == 0 \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == HOL 
    //      \and getTimeInHole()@pre >= 5 
    //      \and getBehaviour()@pre == MOVEL 
    //      \impl getTimeInHole() == 0 \and climbLeft()
    //\post getTimeLeftParalyzed()@pre == 0 \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == HOL 
    //      \and getTimeInHole()@pre >= 5 
    //      \and getBehaviour()@pre == MOVER
    //      \impl getTimeInHole() == 0 \and climbRight()
    //\post isFalling \impl goDown()
    //\post getTimeLeftParalyzed()@pre == 0 \and \not isFalling \and getEnvi().getCellnature()@pre != HOL \and getBehaviour()@pre == MOVEL \impl goLeft()
    //\post getTimeLeftParalyzed()@pre == 0 \and \not isFalling \and getEnvi().getCellnature()@pre != HOL \and getBehaviour()@pre == MOVER \impl goRight()
    //\post getTimeLeftParalyzed()@pre == 0 \and \not isFalling \and getEnvi().getCellnature()@pre != HOL \and getBehaviour()@pre == MOVEU \impl goUp()
    //\post getTimeLeftParalyzed()@pre == 0 \and \not isFalling \and getEnvi().getCellnature()@pre != HOL \and getBehaviour()@pre == MOVED \impl goDown()
    //\post getIdCounter() == getIdCounter()@pre
    public void step();
    
    //\post getHgt() == getHgt()@pre
    //\post getCol()@pre == 0 \impl getCol() == getCol()@pre
    //\post (getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR, NGU}) \impl getCol() == getCol()@pre
    //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NGU}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
    //      \impl getCol() == getCol()@pre()
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \impl getCol() == getCol()@pre
    //\post getCol()@pre != 0
    //      \and \not getEnvi().getCellNature()(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR, NGU}
    //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
    //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NGU}
    //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre)
    //      \impl getCol() == getCol()@pre - 1
    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == getTimeInHole()@pre
    //\post getIdCounter() == getIdCounter()@pre
    //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
    public void goLeft();

    //\post getHgt() == getHgt()@pre
    //\post getCol()@pre == getEnvi().getWidth() - 1 \impl getCol() == getCol()@pre
    //\post (getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR, NGU}) \impl getCol() == getCol()@pre
    //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NGU}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
    //      \impl getCol() == getCol()@pre()
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \impl getCol() == getCol()@pre
    //\post getCol()@pre != getEnvi().getWidth() - 1
    //      \and \not getEnvi().getCellNature()(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR, NGU}
    //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
    //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NGU}
    //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre)
    //      \impl getCol() == getCol()@pre + 1
    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == getTimeInHole()@pre
    //\post getIdCounter() == getIdCounter()@pre
    //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
    public void goRight();

    //\post getCol() == getCol()@pre
    //\post getHgt()@pre == getEnvi().getHeight() - 1 \impl getHgt() == getHgt()@pre
    //\post (getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \in {MTL, PLT, DOR, NGU} \impl getHgt() == getHgt()@pre
    //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \impl getHgt() == getHgt()@pre
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \impl getHgt() == getHgt()@pre
    //\post getHgt()@pre != getEnvi().getHeight() - 1 
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \in {MTL, PLT, DOR, NGU}
    //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1)
    //      \impl getHgt() == getHgt()@pre + 1
    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == getTimeInHole()@pre
    //\post getIdCounter() == getIdCounter()@pre
    //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
    public void goUp();

    //\post getCol() == getCol()@pre
    //\post getHgt()@pre == 0 \impl getHgt() == getHgt()@pre
    //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR, NGU} \impl getHgt() == getHgt()@pre
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \impl getHgt() == getHgt()@pre
    //\post getHgt()@pre != 0
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR, NGU}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) 
    //      \impl getHgt() == getHgt()@pre - 1
    //\post isCarryingTreasure() == isCarryingTreasure()@pre
    //\post getTimeInHole() == getTimeInHole()@pre
    //\post getIdCounter() == getIdCounter()@pre
    //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
    public void goDown();

    public Guard copy(Engine e);

}