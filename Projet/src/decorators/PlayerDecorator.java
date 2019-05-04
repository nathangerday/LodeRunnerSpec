package decorators;

import data.Item;
import data.ItemType;
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

    @Override
    public boolean isFacingRight() {
        return getDelegate().isFacingRight();
    }

    @Override
    public Item getCurrentlyHeldItem() {
        return getDelegate().getCurrentlyHeldItem();
    }

    @Override
    public int getNumberOfUsagesLeftForCurrentItem() {
        return getDelegate().getNumberOfUsagesLeftForCurrentItem();
    }

    @Override
    public void useItem() {
        getDelegate().useItem();
    }

    @Override
    public void pickupItem(ItemType type) {
        getDelegate().pickupItem(type);
    }
}