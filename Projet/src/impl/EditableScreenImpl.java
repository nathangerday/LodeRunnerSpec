package impl;

import data.Cell;
import services.EditableScreen;

public class EditableScreenImpl extends ScreenImpl implements EditableScreen {

    public boolean isPlayable() {
        for(int x=0; x<getWidth(); x++){
            for(int y=0; y<getHeight(); y++){
                if(getCellNature(x, y) == Cell.HOL){
                    return false;
                }
                if(y == 0 && getCellNature(x, y) != Cell.MTL){
                    return false;
                }
            }
        }
        return true;
    }

    public void setNature(int x, int y, Cell type) {
        this.natures[x][y] = type;
    }

    @Override
    public EditableScreen copy(){
        EditableScreenImpl copy = new EditableScreenImpl();
        copy.height = this.height;
        copy.width = this.width;
        copy.natures = new Cell[getWidth()][getHeight()];
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                copy.natures[x][y] = this.natures[x][y];
            }
        }
        return copy;
    }
}