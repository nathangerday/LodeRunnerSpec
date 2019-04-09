package services;

import data.*;

public interface Screen {
    /* Observators */


    public int getHeight();
    public int getWidth();

    //\pre 0 <= y 
    //\pre y < getHeight()
    //\pre 0 <= x
    //\pre x < getWidth()
    public Cell getCellNature(int x, int y);


    
    /* Constructors */    
    

    //\pre 0 < h
    //\pre 0 < w
    //\post getHeight() == h
    //\post getWidth() == w
    //\post \Forall x in [0, getWidth() - 1]
    //          \Forall y in [0, getHeight() - 1]
    //              getCellNature(x, y) == Cell.EMP
    public void init(int h, int w);

    
    /* Operators */
    

    //\pre getCellNature(x,y) == Cell.PLT
    //\post getCellNature(x, y) == Cell.HOL
    //\post \Forall u in [0, getWidht() - 1]
    //          \Forall v in [0, getHeight() - 1]
    //              u != x || v != y \impl getCellNature(u,v) == getCellNature(u, v)@pre
    public void dig(int x, int y);

    //\pre getCellNature(x,y) == Cell.HOL
    //\post getCellNature(x, y) == Cell.PLT
    //\post \Forall u in [0, getWidht() - 1]
    //          \Forall v in [0, getHeight() - 1]
    //              u != x || v != y \impl getCellNature(u,v) == getCellNature(u, v)@pre
    public void fill(int x, int y);



    
    
}