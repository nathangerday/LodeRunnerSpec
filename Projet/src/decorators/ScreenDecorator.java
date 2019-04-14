package decorators;

import data.Cell;
import services.Screen;

public class ScreenDecorator implements Screen {

    private final Screen delegate;

    protected ScreenDecorator(Screen delegate){
        this.delegate = delegate;
    }

    protected Screen getDelegate(){
        return this.delegate;
    }

    @Override
    public int getHeight() {
        return getDelegate().getHeight();
    }

    @Override
    public int getWidth() {
        return getDelegate().getWidth();
    }

    @Override
    public Cell getCellNature(int x, int y) {
        return getDelegate().getCellNature(x, y);
    }

    @Override
    public void init(int h, int w) {
        getDelegate().init(h, w);
    }

    @Override
    public void dig(int x, int y) {
        getDelegate().dig(x, y);
    }

    @Override
    public void fill(int x, int y) {
        getDelegate().fill(x, y);
    }
    
}