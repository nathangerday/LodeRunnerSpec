package contracts;

import java.util.Set;

import data.Entity;
import decorators.EnvironmentDecorator;
import services.Character;
import services.EditableScreen;
import services.Environment;

public class EnvironmentContract extends EnvironmentDecorator{

    public EnvironmentContract(Environment delegate){
        super(delegate);
    }

    public void checkInvariant(){
        
    }

    @Override
    public Set<Entity> getCellContent(int x, int y) {
        Set<Entity> res = super.getCellContent(x, y);
        return res;
    }

    @Override
    public void addToCellContent(int x, int y, Entity e) {
        super.addToCellContent(x, y, e);
    }

    @Override
    public void removeFromCellContent(int x, int y, Entity e) {
        super.removeFromCellContent(x, y, e);
    }

    @Override
    public Character removeCharacter(int x, int y) {
        Character res = super.removeCharacter(x, y);
        return res;
    }

    @Override
    public void init(EditableScreen screen) {
        super.init(screen);
    }
}