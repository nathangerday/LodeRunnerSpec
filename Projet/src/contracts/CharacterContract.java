package contracts;

import decorators.CharacterDecorator;
import services.Character;
import services.Environment;

public class CharacterContract extends CharacterDecorator {
    
    public CharacterContract(Character delegate){
        super(delegate);
    }

    public void checkInvariant(){
        
    }
    

    @Override
    public Environment getEnvi() {
        Environment res = super.getEnvi();
        return res;
    }

    @Override
    public int getHgt() {
        int res = super.getHgt();
        return res;
    }

    @Override
    public int getCol() {
        int res = super.getCol();
        return res;
    }

    @Override
    public void init(Environment s, int x, int y) {
        super.init(s, x, y);
    }

    @Override
    public void goLeft() {
        super.goLeft();
    }

    @Override
    public void goRight() {
        super.goRight();
    }

    @Override
    public void goUp() {
        super.goUp();
    }

    @Override
    public void goDown() {
        super.goDown();
    }
}