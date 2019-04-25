package services;

import data.Command;
import data.Coord;

public interface Guard extends Character{
    public int getId();

    public void init(Environment s, int x, int y, Character target);

    public Coord getInitCoords();

    public Command getBehaviour();

    public Character getTarget();

    public int getTimeInHole();

    public void climbLeft();

    public void climbRight();

    public void moveToInitCoords();

    public void step();

    
}