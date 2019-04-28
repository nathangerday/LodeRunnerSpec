package impl;

import java.util.ArrayList;
import java.util.List;

import data.Coord;
import data.CoordGuard;
import data.CoordItem;
import data.LevelSetup;
import services.EditableScreen;
import services.ScreenManager;

public class ScreenManagerImpl implements ScreenManager {

    private List<LevelSetup> levels;

    @Override
    public void init(){
        this.levels = new ArrayList<>();
    }
    

    @Override
    public int getNbScreen() {
        return levels.size();
    }

    @Override
    public LevelSetup getLevelSetup(int i){
        return levels.get(i);
    }

    @Override
    public EditableScreen getScreen(int i) {
        return levels.get(i).getScreen();
    }

    @Override
    public List<CoordGuard> getGuardsFromScreen(int i) {
        return levels.get(i).getGuards();
    }

    @Override
    public List<CoordItem> getItemsFromScreen(int i) {
        return levels.get(i).getItems();
    }

    @Override
    public Coord getPlayerFromScreen(int i) {
        return levels.get(i).getPlayer();
    }

    @Override
    public void addScreen(EditableScreen screen, List<CoordGuard> guards, List<CoordItem> items, Coord player) {
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