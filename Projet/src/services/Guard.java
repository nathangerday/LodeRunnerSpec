package services;

import data.Command;
import data.Coord;
import data.GuardType;

public interface Guard extends Character{
    public int getId();
    public Engine getEngine();
    public GuardType getNature();
    
    public boolean isCarryingTreasure();

    public void init(Engine e, int x, int y, Character target);

    public Coord getInitCoords();

    //TODO pre / post
    public Command getBehaviour();

    public Character getTarget();

    public int getTimeInHole();

    public void climbLeft();

    public void climbRight();

    public void moveToInitCoords();

    public void step();

    
}