package services;

import java.util.List;

import data.Coord;

public interface ScreenManager {

    /* Observators */

    public int getNbScreen();

    public EditableScreen getScreen(int i);
    public List<Coord> getGuardsFromScreen(int i);
    public List<Coord> getItemsFromScreen(int i);
    public Coord getPlayerFromScreen(int i);

    /* Operators */
    public void addScreen(EditableScreen screen, List<Coord> guards, List<Coord> items, Coord player);
    public void removeScreen(int i);

    

}