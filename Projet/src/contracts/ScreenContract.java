package contracts;

import data.Cell;
import decorators.ScreenDecorator;
import services.Screen;

public class ScreenContract extends ScreenDecorator {
    
    public ScreenContract(Screen delegate){
        super(delegate);
    }

    public void checkInvariant(){

    }

    @Override
    public int getHeight() {
        int res = super.getHeight();
        return res;
    }

    @Override
    public int getWidth() {
        int res = super.getWidth();
        return res;
    }

    @Override
    public Cell getCellNature(int x, int y) {
        Cell res = super.getCellNature(x, y);
        return res;
    }

    @Override
    public void init(int h, int w) {
        super.init(h, w);
    }

    @Override
    public void dig(int x, int y) {
        super.dig(x, y);
    }

    @Override
    public void fill(int x, int y) {
        super.fill(x, y);
    }
    
}