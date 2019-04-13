package services;

import data.*;

public interface EditableScreen extends /* includes */ Screen {
    /* Observators */
    
    public boolean isPlayable();

    /* Operators */

    //\pre 0 <= y
    //\pre y < getHeight()
    //\pre 0 <= x
    //\pre x < getWidth()
    //\post getCellNature(x, y) == type
    //\post \Forall u in [0, getWidth() - 1]
    //          \Forall v in [0, getHeight() - 1]
    //              u != x || v != y \impl getCellNature(u,v) == getCellNature(u, v)@pre 
    public void setNature(int x, int y, Cell type);

    /* Invariants */
    
    //\inv isPlayable() ==  \Forall x in [0, getWidth() - 1]
    //                          \Forall y in [0, getHeigth() - 1]
    //                              getCellNature(x, y) != Cell.HOL
    //                      \and 
    //                      \Forall x in [0, getWidth()]
    //                          getCellNature(x, 0) == Cell.MTL
}