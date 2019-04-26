package services;

public interface Player extends /* includes */ Character{
    /* Observators */
    
    public Engine getEngine();
    

    /* Constructors */
    //\pre e != null
    //\pre x >= 0
    //\pre x < e.getEnvironment().getWidth()
    //\pre y >= 0
    //\pre y < e.getEnvironment().getHeight()
    //\pre \not s.getCellNature(x, y) \in {MTL, PLT}
    //\post getHgt() == y
    //\post getCol() == x
    //\post getEnvi() == e.getEnvironment()
    //\post this \in getEnvi().getCellContent(x, y)
    //\post getEngine() == e
    public void init(int x, int y, Engine e);


    /* Operators */

    //\def falling = \not getEnvi().getCellNature(getCol(), getHgt())@pre \in {LAD, HDR}
    //               \and getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {EMP, HDR, HOL}
    //               \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre

    //\post falling \impl step() == this@pre.goDown()
    //\post (\not falling) \and getNextCommand()@pre == MOVEL \impl step() == this@pre.goLeft()
    //\post (\not falling) \and getNextCommand()@pre == MOVER \impl step() == this@pre.goRight()
    //\post (\not falling) \and getNextCommand()@pre == MOVED \impl step() == this@pre.goDown()
    //\post (\not falling) \and getNextCommand()@pre == MOVEU \impl step() == this@pre.goUp()
    //\post (\not falling) \and getNextCommand()@pre == DIGL \impl step() == this@pre.digLeft()
    //\post (\not falling) \and getNextCommand()@pre == DIGR \impl step() == this@pre.digRight()
    //\post (\not falling) \and getNextCommand()@pre == NONE \impl this == this@pre
    public void step();

    //\def noCellNatureChanged = \Forall i in [0, getEnvi().getWidth() - 1]
    //                              \Forall j in [0, getEnvi().getHeight() - 1]
    //                                  getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre


    //\post getCol() == 0 \impl noCellNatureChanged
    //\post getHgt() == getHgt()@pre
    //\post getCol() == getCol()@pre
    //\post \not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre
    //      \impl noCellNatureChanged
    //\post \not getEnvi().getCellNature(getCol() - 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
    //      \impl noCellNatureChanged
    //\post getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre != PLT
    //      \impl noCellNatureChanged
    //\post (getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD}
    //          \or \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre)
    //      \and getEnvi().getCellNature(getCol() - 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
    //      \and getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre == PLT
    //      \impl getEnvi().getCellNature(getCol() - 1, getHgt() - 1) == HOL
    //            \and \Forall i in [0, getEnvi().getWidth() - 1]
    //                      \Forall j in [0, getEnvi().getHeight() - 1]
    //                          i != getCol() - 1 || j != getHgt() - 1 \impl getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
    public void digLeft();
    
    //\post getCol() == getEnvi().getWidth() - 1 \impl noCellNatureChanged
    //\post getHgt() == getHgt()@pre
    //\post getCol() == getCol()@pre
    //\post \not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD}
    //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre
    //      \impl noCellNatureChanged
    //\post \not getEnvi().getCellNature(getCol() + 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
    //      \impl noCellNatureChanged
    //\post getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre != PLT
    //      \impl noCellNatureChanged
    //\post (getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD}
    //          \or \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre)
    //      \and getEnvi().getCellNature(getCol() + 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
    //      \and getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre == PLT
    //      \impl getEnvi().getCellNature(getCol() + 1, getHgt() - 1) == HOL
    //            \and \Forall i in [0, getEnvi().getWidth() - 1]
    //                      \Forall j in [0, getEnvi().getHeight() - 1]
    //                          i != getCol() + 1 || j != getHgt() - 1 \impl getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
    public void digRight();

}