package services;

import java.util.Set;

import data.Entity;

public interface Environment extends /* includes */ Screen{
    /* Observators */

    //\pre 0 <= y
    //\pre y < getHeight()
    //\pre 0 <= x
    //\pre x <= getWidht()
    public Set<Entity> getCellContent(int x, int y);

    public void addToCellContent(int x, int y, Entity e);

    public void removeFromCellContent(int x, int y, Entity e);

    public Character removeCharacter(int x, int y);
    
    //\pre screen != null
    //\pre screen.isPlayable()
    //\post \Forall x in [0, getWidht() - 1]
    //          \Forall y in [0, getHeight() - 1]
    //              getCellNature(x, y) == screen.getCellNature(x, y)
    public void init(EditableScreen screen);


    /* Invariants */

    //\inv  \Forall x in [0, getWidht() - 1]
    //          \Forall y in [0, getHeight() - 1]
    //              \Forall Character c1, c2 \in getCellContent(x, y)
    //                  c1 == c2

    //\inv \Forall x in [0, getWidht() - 1]
    //          \Forall y in [0, getHeight() - 1]
    //              (getCellNature(x, y) \in {MTL, PLR}) \impl getCellContent(x, y) == {}
    
    //\inv \Forall x in [0, getWidht() - 1]
    //          \Forall y in [0, getHeight() - 1]
    //          (\Exists Treasure t \in getCellContent(x, y)) \impl (getCellNature(x, y) == Cell.EMP \and getCellNature(x, y-1) \in {PLT, MTL})


}