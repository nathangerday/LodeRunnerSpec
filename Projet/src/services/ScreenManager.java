package services;

import java.util.List;

import data.Coord;
import data.CoordGuard;
import data.CoordItem;
import data.LevelSetup;

public interface ScreenManager{

    /* Observators */

    public int getNbScreen();

    //\pre i < getNbScreen()
    //\pre i >= 0
    public LevelSetup getLevelSetup(int i);

    //\pre i < getNbScreen()
    //\pre i >= 0
    public EditableScreen getScreen(int i);

    //\pre i < getNbScreen()
    //\pre i >= 0
    public List<CoordGuard> getGuardsFromScreen(int i);

    //\pre i < getNbScreen()
    //\pre i >= 0
    public List<CoordItem> getItemsFromScreen(int i);

    //\pre i < getNbScreen()
    //\pre i >= 0
    public Coord getPlayerFromScreen(int i);

    /* Constructor */
    //\post getNbScreen() == 0
    public void init();

    /* Invariants */
    //\inv getScreen(i) == getLevelSetup(i).getScreen()
    //\inv getGuardsFromScreen(i) == getLevelSetup(i).getGuards()
    //\inv getItemsFromScreen(i) == getLevelSetup(i).getItems()
    //\inv getPlayerFromScreen(i) == getLevelSetup(i).getPlayer()

    /* Operators */

    //\pre screen != null
    //\pre player != null
    //\post getNbScreen() == getNbScreen()@pre + 1
    //\post getScreen(getNbScreen()@pre) == screen
    //\post getPlayerFromScreen(getNbScreen()@pre) == player
    //\post guards == null \impl getGuardsFromScreen(getNbScreen()@pre).size == 0
    //\post guards != null \impl getGuardsFromScreen(getNbScreen()@pre) == guards
    //\post items == null \impl getItemsFromScreen(getNbScreen()@pre).size == 0
    //\post items != null \impl getItemsFromScreen(getNbScreen()@pre) == items
    //\post \Forall i in [0, getNbScreen()@pre - 1]
    //          getLevelSetup(i) == getLevelSetup(i)@pre
    public void addScreen(EditableScreen screen, List<CoordGuard> guards, List<CoordItem> items, Coord player);

    //\pre i < getNbScreen()
    //\pre i >= 0
    //\post getNbScreen() == getNbScreen()@pre - 1
    //\post \Forall v \in [0, getNbScreen() - 1]
    //          v < i \impl getLevelSetup(v) == getLevelSetup(v)@pre
    //          v >= i \impl getLevelSetup(v) == getLevelSetup(v + 1)@pre
    public void removeScreen(int i);

    
    public ScreenManager copy();
}