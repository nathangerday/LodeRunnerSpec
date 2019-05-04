package impl;

import data.Cell;
import services.Screen;

public class ScreenImpl implements Screen{
    protected int width, height;
    protected Cell[][] natures;

    public void init(int h, int w){
        this.width = w;
        this.height = h;
        this.natures = new Cell[w][h];
        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
                natures[i][j] = Cell.EMP; 
            }
        }
    }

    public Cell getCellNature(int x, int y){
        return this.natures[x][y];
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public void dig(int x, int y) {
        this.natures[x][y] = Cell.HOL;
    }

    @Override
    public void fill(int x, int y) {
        this.natures[x][y] = Cell.PLT;
    }

    @Override
    public void openDoor(int x, int y){
        this.natures[x][y] = Cell.EMP;
    }
    
    @Override
    public void revealTrap(int x, int y){
        this.natures[x][y] = Cell.EMP;
    }

    @Override
    public Screen copy(){
        ScreenImpl copy = new ScreenImpl();
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