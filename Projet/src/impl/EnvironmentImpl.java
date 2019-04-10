package impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Entity;
import services.EditableScreen;
import services.Environment;

public class EnvironmentImpl extends ScreenImpl implements Environment {

    private EditableScreen editScreen;
    private List<List<Set<Entity>>> content;

    public Set<Entity> getCellContent(int x, int y) {
        return null;
    }

    public void init(int h, int w, EditableScreen es){
        super.init(h, w);
        this.editScreen = es;
        this.content = new ArrayList<>();
        for(int x=0; x<getWidth(); x++){
            this.content.set(x, new ArrayList<>());
            for(int y=0; y<getHeight(); y++){
                this.content.get(x).set(y, new HashSet<>());
            }
        }
    }

}