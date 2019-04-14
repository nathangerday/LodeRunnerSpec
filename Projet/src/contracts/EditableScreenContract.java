package contracts;

import data.Cell;
import decorators.EditableScreenDecorator;
import services.EditableScreen;

public class EditableScreenContract extends EditableScreenDecorator{

    public EditableScreenContract(EditableScreen delegate){
        super(delegate);
    }

    public void checkInvariant(){

    }

    @Override
    public boolean isPlayable() {
        boolean res = super.isPlayable();
        return res;
    }   

    @Override
    public void setNature(int x, int y, Cell type) {
        super.setNature(x, y, type);
    }
}