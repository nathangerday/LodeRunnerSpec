package decorators;

import services.Engine;
import services.Player;

public class PlayerDecorator extends CharacterDecorator implements Player {

    public PlayerDecorator(Player delegate) {
        super(delegate);
    }

    protected Player getDelegate() {
        return (Player) super.getDelegate();
    }

    @Override
    public Engine getEngine() {
        return getDelegate().getEngine();
    }

    @Override
    public void init(int x, int y, Engine e) {
        getDelegate().init(x, y, e);
    }

    @Override
    public void step() {
        getDelegate().step();
    }

    @Override
    public void digLeft() {
        getDelegate().digLeft();
    }

    @Override
    public void digRight() {
        getDelegate().digRight();
    }
}