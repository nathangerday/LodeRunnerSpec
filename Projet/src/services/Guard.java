package services;

import data.Command;

public interface Guard extends Character{
    public int getId();

    public void init(Environment s, int x, int y, Character target);

    public Command getBehaviour();

    public Character getTarget();

    public int getTimeInHole();

    public void climbLeft();

    public void climbRight();

    public void step();

    
}