package impl;

import java.util.ArrayList;
import java.util.List;

import data.Coord;
import data.LevelSetup;
import services.EditableScreen;
import services.ScreenManager;

public class ScreenManagerImpl implements ScreenManager {

    private List<LevelSetup> levels = new ArrayList<>();

    @Override
    public int getNbScreen() {
        return levels.size();
    }

    @Override
    public EditableScreen getScreen(int i) {
        return levels.get(i).getScreen();
    }

    @Override
    public List<Coord> getGuardsFromScreen(int i) {
        return levels.get(i).getGuards();
    }

    @Override
    public List<Coord> getItemsFromScreen(int i) {
        return levels.get(i).getItems();
    }

    @Override
    public Coord getPlayerFromScreen(int i) {
        return levels.get(i).getPlayer();
    }

    @Override
    public void addScreen(EditableScreen screen, List<Coord> guards, List<Coord> items, Coord player) {
        if(guards == null){
            guards = new ArrayList<>();
        }

        if(items == null){
            items = new ArrayList<>();
        }
        levels.add(new LevelSetup(screen, guards, items, player));
    }

    @Override
    public void removeScreen(int i) {
        levels.remove(i);
    }

}