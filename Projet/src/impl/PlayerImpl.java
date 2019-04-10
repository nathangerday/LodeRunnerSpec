package impl;

import services.Engine;
import services.Environment;
import services.Player;

public class PlayerImpl extends CharacterImpl implements Player {

    private Engine engi;

    public Engine getEngine() {
        return this.engi;
    }

    public void step() {
        //TODO
    }

    public void init(Environment s, int x, int y, Engine e) {
        super.init(s, x, y);
        this.engi = e;
    }

}