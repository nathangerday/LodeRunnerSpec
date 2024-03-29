package services;

import data.Entity;

public interface Character extends Entity{
    /* Observators */
    public Environment getEnvi();
    public int getHgt();
    public int getCol();


    /* Constructors */

    //\pre s != null
    //\pre x >= 0
    //\pre x < s.getWidth()
    //\pre y >= 0
    //\pre y < s.getHeight()
    //\pre \not s.getCellNature(x, y) \in {MTL, PLT, DOR}
    //\post getHgt() == y
    //\post getCol() == x
    //\post getEnvi() == s
    //\post this \in getEnvi().getCellContent(x, y)
    public void init(Environment s, int x, int y);

    /* Invariants */

    //\inv getEnvi().getCellNature(getCol(), getHgt()) \in {EMP, HOL, LAD, HDR, NPL, NGU}


    /* Operators */
    
    //\post getHgt() == getHgt()@pre
    //\post getCol()@pre == 0 \impl getCol() == getCol()@pre
    //\post (getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR}) \impl getCol() == getCol()@pre
    //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
    //      \impl getCol() == getCol()@pre()
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \impl getCol() == getCol()@pre
    //\post getCol()@pre != 0
    //      \and \not getEnvi().getCellNature()(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR}
    //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
    //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR}
    //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre)
    //      \impl getCol() == getCol()@pre - 1
    public void goLeft();

    //\post getHgt() == getHgt()@pre
    //\post getCol()@pre == getEnvi().getWidth() - 1 \impl getCol() == getCol()@pre
    //\post (getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR}) \impl getCol() == getCol()@pre
    //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
    //      \impl getCol() == getCol()@pre()
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \impl getCol() == getCol()@pre
    //\post getCol()@pre != getEnvi().getWidth() - 1
    //      \and \not getEnvi().getCellNature()(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR}
    //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
    //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR}
    //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre)
    //      \impl getCol() == getCol()@pre + 1
    public void goRight();

    //\post getCol() == getCol()@pre
    //\post getHgt()@pre == getEnvi().getHeight() - 1 \impl getHgt() == getHgt()@pre
    //\post (getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \in {MTL, PLT, DOR} \impl getHgt() == getHgt()@pre
    //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \impl getHgt() == getHgt()@pre
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \impl getHgt() == getHgt()@pre
    //\post getHgt()@pre != getEnvi().getHeight() - 1 
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \in {MTL, PLT, DOR}
    //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1)
    //      \impl getHgt() == getHgt()@pre + 1
    public void goUp();

    //\post getCol() == getCol()@pre
    //\post getHgt()@pre == 0 \impl getHgt() == getHgt()@pre
    //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR} \impl getHgt() == getHgt()@pre
    //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \impl getHgt() == getHgt()@pre
    //\post getHgt()@pre != 0
    //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) 
    //      \impl getHgt() == getHgt()@pre - 1
    public void goDown();

    public Character copy();
    
}