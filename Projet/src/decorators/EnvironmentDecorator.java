package decorators;

import java.util.Set;

import data.Entity;
import services.Character;
import services.EditableScreen;
import services.Environment;

public class EnvironmentDecorator extends ScreenDecorator implements Environment {

    public EnvironmentDecorator(Environment delegate){
        super(delegate);
    }

    protected Environment getDelegate(){
        return (Environment)super.getDelegate();
    }

    @Override
    public Set<Entity> getCellContent(int x, int y) {
        return getDelegate().getCellContent(x, y);
    }

    @Override
    public void addToCellContent(int x, int y, Entity e) {
        getDelegate().addToCellContent(x, y, e);
    }

    @Override
    public void removeFromCellContent(int x, int y, Entity e) {
        getDelegate().removeFromCellContent(x, y, e);
    }

    @Override
    public void init(EditableScreen screen) {
        getDelegate().init(screen);
    }

    @Override
    public Environment copy(){
        return getDelegate().copy();
    }
    
}