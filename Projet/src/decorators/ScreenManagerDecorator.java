package decorators;

import java.util.List;

import data.Coord;
import data.CoordGuard;
import data.CoordItem;
import data.LevelSetup;
import services.EditableScreen;
import services.ScreenManager;

public class ScreenManagerDecorator implements ScreenManager {
    private final ScreenManager delegate;

    public ScreenManagerDecorator(ScreenManager delegate) {
        this.delegate = delegate;
    }

    protected ScreenManager getDelegate() {
        return this.delegate;
    }

    @Override
    public int getNbScreen() {
        return getDelegate().getNbScreen();
    }

    @Override
    public LevelSetup getLevelSetup(int i) {
        return getDelegate().getLevelSetup(i);
    }

    @Override
    public EditableScreen getScreen(int i) {
        return getDelegate().getScreen(i);
    }

    @Override
    public List<CoordGuard> getGuardsFromScreen(int i) {
        return getDelegate().getGuardsFromScreen(i);
    }

    @Override
    public List<CoordItem> getItemsFromScreen(int i) {
        return getDelegate().getItemsFromScreen(i);
    }

    @Override
    public Coord getPlayerFromScreen(int i) {
        return getDelegate().getPlayerFromScreen(i);
    }

    @Override
    public void init(){ 
        getDelegate().init();
    }

    @Override
    public void addScreen(EditableScreen screen, List<CoordGuard> guards, List<CoordItem> items, Coord player) {
        getDelegate().addScreen(screen, guards, items, player);
    }

    @Override
    public void removeScreen(int i) {
        getDelegate().removeScreen(i);
    }

    @Override
    public ScreenManager copy() {
        return getDelegate().copy();
    }


}