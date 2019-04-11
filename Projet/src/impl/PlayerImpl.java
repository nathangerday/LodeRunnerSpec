package impl;

import java.util.HashSet;
import java.util.Set;

import data.Cell;
import data.Command;
import services.Engine;
import services.Environment;
import services.Player;
import utils.Util;

public class PlayerImpl extends CharacterImpl implements Player {

    private Engine engi;

    public Engine getEngine() {
        return this.engi;
    }

    public void step() {
        //TODO Gestion des différents ca
        Set<Cell> libre = new HashSet<>();
        libre.add(Cell.EMP);
        libre.add(Cell.LAD);
        libre.add(Cell.HDR);
        libre.add(Cell.HOL);

        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);
        if(!LAD_HDR.contains(this.envi.getCellNature(this.x, this.y)) && libre.contains(this.envi.getCellNature(this.x, this.y - 1)) &&
        !Util.constainsCharacter(this.envi.getCellContent(this.x, this.y - 1))){
            goDown();
            return;
        }

        Command next = this.engi.getNextCommand();
        this.engi.setNextCommand(Command.NONE);
        switch(next){
            case MOVEL:
                goLeft();
                break;
            case MOVER:
                goRight();
                break;
            case MOVED:
                goDown();
                break;
            case MOVEU:
                goUp();
                break;
            case DIGL:
                //TODO digl
                break;
            case DIGR:
                //TODO digr
                break;
            case NONE:
                break;
        }
    }

    public void init(int x, int y, Engine e) {
        super.init(e.getEnvironment(), x, y);
        this.engi = e;
    }

}