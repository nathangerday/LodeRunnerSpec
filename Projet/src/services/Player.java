package services;

import data.Item;
import data.ItemType;

public interface Player extends /* includes */ Character{
    /* Observators */
    
    public Engine getEngine();
    public boolean isFacingRight();
    public Item getCurrentlyHeldItem();
    public int getNumberOfUsagesLeftForCurrentItem();

    /* Constructors */
    //\pre e != null
    //\pre x >= 0
    //\pre x < e.getEnvironment().getWidth()
    //\pre y >= 0
    //\pre y < e.getEnvironment().getHeight()
    //\pre \not s.getCellNature(x, y) \in {MTL, PLT, DOR, NPL}
    //\post getHgt() == y
    //\post getCol() == x
    //\post getEnvi() == e.getEnvironment()
    //\post this \in getEnvi().getCellContent(x, y)
    //\post getEngine() == e
    //\post isFacingRight()
    //\post getCurrentlyHeldItem().getNature() == Sword
    //\post getNumberOfUsagesLeftForCurrentItem() == 3

    public void init(int x, int y, Engine e);


    /* Operators */

    //\def falling = \not getEnvi().getCellNature(getCol(), getHgt())@pre \in {LAD, HDR}
    //               \and getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {EMP, HDR, HOL, NGU}
    //               \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre

    //\post falling \impl step() == this@pre.goDown()
    //\post (\not falling) \and getNextCommand()@pre == MOVEL \impl step() == this@pre.goLeft()
    //\post (\not falling) \and getNextCommand()@pre == MOVER \impl step() == this@pre.goRight()
    //\post (\not falling) \and getNextCommand()@pre == MOVED \impl step() == this@pre.goDown()
    //\post (\not falling) \and getNextCommand()@pre == MOVEU \impl step() == this@pre.goUp()
    //\post (\not falling) \and getNextCommand()@pre == DIGL \impl step() == this@pre.digLeft()
    //\post (\not falling) \and getNextCommand()@pre == DIGR \impl step() == this@pre.digRight()
    //\post (\not falling) \and getNextCommand()@pre == USEITEM \impl step() == this@pre.useItem()
    //\post (\not falling) \and getNextCommand()@pre == NONE \impl this == this@pre
    public void step();

    //\post isFacingRight() == isFacingRight()@pre
    //\post getCol() == getCol()@pre
    //\post getHgt() == getHgt()@pre
    //\post getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type
    //      \and type == Key
    //      \impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 1 \and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
    //\post \not (getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type)
    //      \and type == Key
    //      \impl getNumberOfUsagesLeftForCurrentItem() == 1 \and getCurrentlyHeldItem() == type
    //\post getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type
    //      \and type == Gun
    //      \impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 1 \and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
    //\post \not (getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type)
    //      \and type == Gun
    //      \impl getNumberOfUsagesLeftForCurrentItem() == 1 \and getCurrentlyHeldItem() == type
    //\post getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type
    //      \and type == Sword
    //      \impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 3 \and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
    //\post \not (getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type)
    //      \and type == Sword
    //      \impl getNumberOfUsagesLeftForCurrentItem() == 3 \and getCurrentlyHeldItem() == type
    //\post getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type
    //      \and type == Flash
    //      \impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 1 \and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
    //\post \not (getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type)
    //      \and type == Flash
    //      \impl getNumberOfUsagesLeftForCurrentItem() == 1 \and getCurrentlyHeldItem() == type
    public void pickupItem(ItemType type);


    //\def canUseItem = (getCurrentlyHeldItem()@pre != null \and getNumberOfUsagesLeftForCurrentItem()@pre >= 1) 

    //\post getCurrentlyHeldItem()@pre != null \and getNumberOfUsagesLeftForCurrentItem()@pre == 1 
    //      \impl getCurrentlyHeldItem() == null \and getNumberOfUsagesLeftForCurrentItem() == 0
    //\post getCurrentlyHeldItem()@pre != null \and getNumberOfUsagesLeftForCurrentItem()@pre > 1 
    //      \impl getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre \and getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre - 1
    //\post canUseItem \and getCurrentlyHeldItem()@pre == Key \and isFacingRight()@pre 
    //      \and getCol()@pre < getEnvi().getWidth() - 1 \and getEnvi().getCellNature(getCol() + 1, getHgt())@pre == DOR
    //      \impl getEnvi().getCellNature(getCol() + 1, getHgt()) == EMP
    //\post canUseItem \and getCurrentlyHeldItem()@pre == Key \and \not isFacingRight()@pre 
    //      \and getCol()@pre > 0 \and getEnvi().getCellNature(getCol() - 1, getHgt())@pre == DOR
    //      \impl getEnvi().getCellNature(getCol() - 1, getHgt()) == EMP
    //\post \Forall Guard g \in getEngine().getGuards()
    //          canUseItem \and getCurrentlyHeldItem()@pre == Flash \impl \impl g.getTimeLeftParalyzed() == 10
    //\post canUseItem \and getCurrentlyHeldItem()@pre == Sword \and getCol()@pre - 2 >= 0 \and \Exists Guard g \in getEnvi().getCellContent(getCol()@pre - 2, getHgt())@pre \impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()
    //\post canUseItem \and getCurrentlyHeldItem()@pre == Sword \and getCol()@pre - 1 >= 0 \and \Exists Guard g \in getEnvi().getCellContent(getCol()@pre - 1, getHgt())@pre \impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()
    //\post canUseItem \and getCurrentlyHeldItem()@pre == Sword \and getCol()@pre + 2 < getEnvi().getWidth() \and \Exists Guard g \in getEnvi().getCellContent(getCol()@pre + 2, getHgt())@pre \impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()
    //\post canUseItem \and getCurrentlyHeldItem()@pre == Sword \and getCol()@pre + 1 < getEnvi().getWidth() \and \Exists Guard g \in getEnvi().getCellContent(getCol()@pre + 1, getHgt())@pre \impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()
    //\post getCol() == getCol()@pre
    //\post getHgt() == getHgt()@pre

    //TODO GUN
    //\post isFacingRight() == isFacingRight()@pre
    public void useItem();

    //\def noCellNatureChanged = \Forall i in [0, getEnvi().getWidth() - 1]
    //                              \Forall j in [0, getEnvi().getHeight() - 1]
    //                                  getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre


    //\post getCol() == 0 \impl noCellNatureChanged
    //\post getHgt() == getHgt()@pre
    //\post getCol() == getCol()@pre
    //\post \not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD, DOR, NPL}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre
    //      \impl noCellNatureChanged
    //\post \not getEnvi().getCellNature(getCol() - 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
    //      \impl noCellNatureChanged
    //\post getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre != PLT
    //      \impl noCellNatureChanged
    //\post (getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD, DOR, NPL}
    //          \or \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre)
    //      \and getEnvi().getCellNature(getCol() - 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
    //      \and getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre == PLT
    //      \impl getEnvi().getCellNature(getCol() - 1, getHgt() - 1) == HOL
    //            \and \Forall i in [0, getEnvi().getWidth() - 1]
    //                      \Forall j in [0, getEnvi().getHeight() - 1]
    //                          i != getCol() - 1 || j != getHgt() - 1 \impl getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
    //\post \not isFacingRight()
    //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
    //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
    public void digLeft();
    
    //\post getCol() == getEnvi().getWidth() - 1 \impl noCellNatureChanged
    //\post getHgt() == getHgt()@pre
    //\post getCol() == getCol()@pre
    //\post \not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD, DOR, NPL}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre
    //      \impl noCellNatureChanged
    //\post \not getEnvi().getCellNature(getCol() + 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
    //      \impl noCellNatureChanged
    //\post getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre != PLT
    //      \impl noCellNatureChanged
    //\post (getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD, DOR, NPL}
    //          \or \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre)
    //      \and getEnvi().getCellNature(getCol() + 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
    //      \and getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre == PLT
    //      \impl getEnvi().getCellNature(getCol() + 1, getHgt() - 1) == HOL
    //            \and \Forall i in [0, getEnvi().getWidth() - 1]
    //                      \Forall j in [0, getEnvi().getHeight() - 1]
    //                          i != getCol() + 1 || j != getHgt() - 1 \impl getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
    //\post isFacingRight()
    //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
    //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
    public void digRight();

    //\post getHgt() == getHgt()@pre
    //\post getCol()@pre == 0 \impl getCol() == getCol()@pre
    //\post (getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR, NPL}) \impl getCol() == getCol()@pre
    //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NPL}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
    //      \impl getCol() == getCol()@pre()
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \impl getCol() == getCol()@pre
    //\post getCol()@pre != 0
    //      \and \not getEnvi().getCellNature()(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR, NPL}
    //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
    //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NPL}
    //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre)
    //      \impl getCol() == getCol()@pre - 1
    //\post \not isFacingRight()
    //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
    //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
    public void goLeft();

    //\post getHgt() == getHgt()@pre
    //\post getCol()@pre == getEnvi().getWidth() - 1 \impl getCol() == getCol()@pre
    //\post (getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR, NPL}) \impl getCol() == getCol()@pre
    //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NPL}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
    //      \impl getCol() == getCol()@pre()
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \impl getCol() == getCol()@pre
    //\post getCol()@pre != getEnvi().getWidth() - 1
    //      \and \not getEnvi().getCellNature()(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR, NPL}
    //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
    //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NPL}
    //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre)
    //      \impl getCol() == getCol()@pre + 1
    //\post isFacingRight()
    //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
    //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
    public void goRight();

    //\post getCol() == getCol()@pre
    //\post getHgt()@pre == getEnvi().getHeight() - 1 \impl getHgt() == getHgt()@pre
    //\post (getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \in {MTL, PLT, DOR, NPL} \impl getHgt() == getHgt()@pre
    //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \impl getHgt() == getHgt()@pre
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \impl getHgt() == getHgt()@pre
    //\post getHgt()@pre != getEnvi().getHeight() - 1 
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \in {MTL, PLT, DOR, NPL}
    //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1)
    //      \impl getHgt() == getHgt()@pre + 1
    //\post isFacingRight() == isFacingRight()@pre
    //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
    //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
    public void goUp();

    //\post getCol() == getCol()@pre
    //\post getHgt()@pre == 0 \impl getHgt() == getHgt()@pre
    //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR, NPL} \impl getHgt() == getHgt()@pre
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \impl getHgt() == getHgt()@pre
    //\post getHgt()@pre != 0
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR, NPL}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) 
    //      \impl getHgt() == getHgt()@pre - 1
    //\post isFacingRight() == isFacingRight()@pre
    //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
    //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
    public void goDown();


    public Player copy(Engine e);
}