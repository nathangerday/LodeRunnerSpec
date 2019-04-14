package decorators;

import data.Cell;
import services.EditableScreen;

public class EditableScreenDecorator extends ScreenDecorator implements EditableScreen {

    public EditableScreenDecorator(EditableScreen delegate){
        super(delegate);
    }

    protected EditableScreen getDelegate(){
        return (EditableScreen) super.getDelegate();
    }
    

    @Override
    public boolean isPlayable() {
        return getDelegate().isPlayable();
    }

    @Override
    public void setNature(int x, int y, Cell type) {
        getDelegate().setNature(x, y, type);
    }
    
}