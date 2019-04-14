package contracts;

import decorators.PlayerDecorator;
import services.Engine;
import services.Player;

public class PlayerContract extends PlayerDecorator{

    public PlayerContract(Player delegate){
        super(delegate);
    }

    public void checkInvariant(){
        
    }

    @Override
    public Engine getEngine() {
        Engine res = super.getEngine();
        return res;
    }

    @Override
    public void init(int x, int y, Engine e) {
        super.init(x, y, e);
    }

    @Override
    public void step() {
        super.step();
    }

    @Override
    public void digLeft() {
        super.digLeft();
    }

    @Override
    public void digRight() {
        super.digRight();
    }
    
}