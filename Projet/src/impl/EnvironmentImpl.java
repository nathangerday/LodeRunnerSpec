package impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Entity;
import data.Item;
import services.Character;
import services.EditableScreen;
import services.Environment;
import services.Guard;

public class EnvironmentImpl extends ScreenImpl implements Environment {

    private EditableScreen editScreen;
    private List<List<Set<Entity>>> content;

    public Set<Entity> getCellContent(int x, int y) {
        return content.get(x).get(y);
    }

    public void init(EditableScreen es) {
        super.init(es.getHeight(), es.getWidth());
        this.editScreen = es;
        this.content = new ArrayList<>();
        for (int x = 0; x < getWidth(); x++) {
            this.content.add(new ArrayList<>());
            for (int y = 0; y < getHeight(); y++) {
                this.content.get(x).add(new HashSet<>());
            }
        }
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                this.natures[x][y] = editScreen.getCellNature(x, y);
            }
        }
    }

    public void addToCellContent(int x, int y, Entity e) {
        this.content.get(x).get(y).add(e);
    }

    public void removeFromCellContent(int x, int y, Entity e) {
        this.content.get(x).get(y).remove(e);
    }

    @Override
    public Environment copy(){
        EnvironmentImpl copy = new EnvironmentImpl();
        copy.height = this.height;
        copy.width = this.width;
        copy.editScreen = this.editScreen.copy();
        copy.natures = this.natures.clone();
        copy.content = new ArrayList<>();
        for (int x = 0; x < getWidth(); x++) {
            copy.content.add(new ArrayList<>());
            for (int y = 0; y < getHeight(); y++) {
                copy.content.get(x).add(new HashSet<>());
                for(Entity e : this.content.get(x).get(y)){
                    if(e instanceof Item){
                        copy.addToCellContent(x, y, e);
                    }
                }
            }
        }
        return copy;
    }
    
}