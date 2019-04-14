package decorators;

import services.Character;
import services.Environment;

public class CharacterDecorator implements Character {
    private final Character delegate;

    public CharacterDecorator(Character delegate){
        this.delegate = delegate;
    }

    protected Character getDelegate(){
        return this.delegate;
    }
    
    @Override
    public Environment getEnvi() {
        return getDelegate().getEnvi();
    }

    @Override
    public int getHgt() {
        return getDelegate().getHgt();
    }

    @Override
    public int getCol() {
        return getDelegate().getCol();
    }

    @Override
    public void init(Environment s, int x, int y) {
        getDelegate().init(s, x, y);
    }

    @Override
    public void goLeft() {
        getDelegate().goLeft();
    }

    @Override
    public void goRight() {
        getDelegate().goRight();
    }

    @Override
    public void goUp() {
        getDelegate().goUp();
    }

    @Override
    public void goDown() {
        getDelegate().goDown();
    }
    
}