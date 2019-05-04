package decorators;

import data.Command;
import data.Coord;
import data.GuardType;
import services.Character;
import services.Engine;
import services.Guard;

public class GuardDecorator extends CharacterDecorator implements Guard {
    public GuardDecorator(Guard delegate) {
        super(delegate);
    }

    protected Guard getDelegate() {
        return (Guard) super.getDelegate();
    }

    @Override
    public int getIdCounter() {
        return getDelegate().getIdCounter();
    }

    @Override
    public int getId() {
        return getDelegate().getId();
    }

    @Override
    public Engine getEngine() {
        return getDelegate().getEngine();
    }

    @Override
    public GuardType getNature() {
        return getDelegate().getNature();
    }

    @Override
    public Coord getInitCoords() {
        return getDelegate().getInitCoords();
    }

    @Override
    public Character getTarget() {
        return getDelegate().getTarget();
    }

    @Override
    public boolean isCarryingTreasure() {
        return getDelegate().isCarryingTreasure();
    }

    @Override
    public int getTimeInHole() {
        return getDelegate().getTimeInHole();
    }

    @Override
    public Command getBehaviour() {
        return getDelegate().getBehaviour();
    }

    @Override
    public void init(Engine e, int x, int y, Character target) {
        getDelegate().init(e, x, y, target);
    }

    @Override
    public void climbLeft() {
        getDelegate().climbLeft();
    }   

    @Override
    public void climbRight() {
        getDelegate().climbRight();
    }

    @Override
    public void moveToInitCoords() {
        getDelegate().moveToInitCoords();
    }

    @Override
    public void step() {
        getDelegate().step();
    }

    @Override
    public void paralyze() {
        getDelegate().paralyze();
    }

    @Override
    public int getTimeLeftParalyzed() {
        return getDelegate().getTimeLeftParalyzed();
    }

}