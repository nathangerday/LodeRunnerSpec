package services;

import java.util.Set;

import data.Entity;

public interface Environment extends /* includes */ Screen{
    /* Observators */

    //\pre 0 <= y
    //\pre y < getHeight()
    //\pre 0 <= x
    //\pre x < getWidth()
    public Set<Entity> getCellContent(int x, int y);

    /* Operators */

    //\pre 0 <= y
    //\pre y < getHeight()
    //\pre 0 <= x
    //\pre x < getWidth()
    //\pre e != null
    //\pre (\Exists Character c \in getCellContent(x, y)) \impl (\not (e \is Character)) //TODO On veut potentiellement supprimer ca
    //\post getCellContent(x, y) == getCellContent(x, y)@pre \Union {e}
    //\post \Forall i in [0, getWidth() - 1]
    //          \Forall j in [0, getHeight() - 1]
    //              i != x || j != y \impl getCellContent(i, j) == getCellContent(i, j)@pre
    //              getCellNature(i, j) == getCellNature(i, j)@pre
    public void addToCellContent(int x, int y, Entity e);


    //\pre 0 <= y
    //\pre y < getHeight()
    //\pre 0 <= x
    //\pre x < getWidth()
    //\pre \Exists e \in getCellContent(x, y)
    //\post getCellContent(x, y) == getCellContent(x, y)@pre \ {e}
    //\post \Forall i in [0, getWidth() - 1]
    //          \Forall j in [0, getHeight() - 1]
    //              i != x || j != y \impl getCellContent(i, j) == getCellContent(i, j)@pre
    //              getCellNature(i, j) == getCellNature(i, j)@pre
    public void removeFromCellContent(int x, int y, Entity e);


    //\pre 0 <= y
    //\pre y < getHeight()
    //\pre 0 <= x
    //\pre x < getWidth()
    //\pre \Exists Character c \in getCellContent(x, y)
    //\post \Exists Character c \in getCellContent(x, y)@pre 
    //          \impl getCellContent(x, y) == getCellContent(x, y)@pre \ {c}
    //\post \Forall i in [0, getWidth() - 1]
    //          \Forall j in [0, getHeight() - 1]
    //              i != x || j != y \impl getCellContent(i, j) == getCellContent(i, j)@pre
    //              getCellNature(i, j) == getCellNature(i, j)@pre
    public Character removeCharacter(int x, int y);
    
    //\pre screen != null
    //\pre screen.isPlayable()
    //\post getHeight() == screen.getHeight()
    //\post getWidth() == screen.getWidht()
    //\post \Forall x in [0, getWidth() - 1]
    //          \Forall y in [0, getHeight() - 1]
    //              getCellNature(x, y) == screen.getCellNature(x, y)
    //              getCellContent(x, y) == {}
    public void init(EditableScreen screen);


    /* Invariants */

    //\inv \Forall x in [0, getWidth() - 1]
    //         \Forall y in [0, getHeight() - 1]
    //              \Forall Character c1, c2 \in getCellContent(x, y)
    //                  c1 == c2

    //\inv \Forall x in [0, getWidth() - 1]
    //          \Forall y in [0, getHeight() - 1]
    //              (getCellNature(x, y) \in {MTL, PLR}) \impl getCellContent(x, y) == {}
    
    //\inv \Forall x in [0, getWidth() - 1]
    //          \Forall y in [0, getHeight() - 1]
    //          (\Exists Treasure t \in getCellContent(x, y)) \impl (getCellNature(x, y) == Cell.EMP \and getCellNature(x, y-1) \in {PLT, MTL})


}