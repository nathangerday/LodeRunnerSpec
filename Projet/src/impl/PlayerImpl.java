package impl;

import data.Command;
import services.Engine;
import services.Environment;
import services.Player;

public class PlayerImpl extends CharacterImpl implements Player {

    private Engine engi;

    public Engine getEngine() {
        return this.engi;
    }

    public void step() {
        //TODO Gestion des différents ca
        if(this.engi.getNextCommand() == Command.MOVEL){
            goLeft();;
        }
    }

    public void init(int x, int y, Engine e) {
        super.init(e.getEnvironment(), x, y);
        this.engi = e;
    }

}