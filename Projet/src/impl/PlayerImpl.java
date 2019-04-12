package impl;

import java.util.HashSet;
import java.util.Set;

import data.Cell;
import data.Command;
import services.Engine;
import services.Player;
import utils.Util;

public class PlayerImpl extends CharacterImpl implements Player {

    private Engine engi;

    public Engine getEngine() {
        return this.engi;
    }

    public void step() {
        //TODO Gestion des différents ca
        Set<Cell> EMP_HDR_HOL = new HashSet<>();
        EMP_HDR_HOL.add(Cell.EMP);
        // EMP_HDR_HOL.add(Cell.LAD);
        EMP_HDR_HOL.add(Cell.HDR);
        EMP_HDR_HOL.add(Cell.HOL);

        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);
        if(!LAD_HDR.contains(this.envi.getCellNature(this.x, this.y)) && EMP_HDR_HOL.contains(this.envi.getCellNature(this.x, this.y - 1)) &&
        !Util.constainsCharacter(this.envi.getCellContent(this.x, this.y - 1))){
            goDown();
            this.engi.setNextCommand(Command.NONE);
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
                digLeft();
                break;
            case DIGR:
                digRight();
                break;
            case NONE:
                break;
        }
    }

    public void init(int x, int y, Engine e) {
        super.init(e.getEnvironment(), x, y);
        this.engi = e;
    }

    public void digLeft(){
        Set<Cell> MTL_PLT_LAD = new HashSet<>();
        MTL_PLT_LAD.add(Cell.MTL);
        MTL_PLT_LAD.add(Cell.PLT);
        MTL_PLT_LAD.add(Cell.LAD);

        Set<Cell> libre = new HashSet<>();
        libre.add(Cell.EMP);
        libre.add(Cell.LAD);
        libre.add(Cell.HDR);
        libre.add(Cell.HOL);

        if((MTL_PLT_LAD.contains(this.envi.getCellNature(this.x, this.y - 1)) || Util.constainsCharacter(this.envi.getCellContent(this.x, this.y - 1))) 
        && this.envi.getCellNature(this.x - 1, this.y - 1) == Cell.PLT){
            this.envi.dig(this.x - 1, this.y - 1);
            this.engi.addHole(this.x - 1, this.y - 1);
        }   

    }

    public void digRight(){
        Set<Cell> MTL_PLT_LAD = new HashSet<>();
        MTL_PLT_LAD.add(Cell.MTL);
        MTL_PLT_LAD.add(Cell.PLT);
        MTL_PLT_LAD.add(Cell.LAD);

        Set<Cell> libre = new HashSet<>();
        libre.add(Cell.EMP);
        libre.add(Cell.LAD);
        libre.add(Cell.HDR);
        libre.add(Cell.HOL);

        if((MTL_PLT_LAD.contains(this.envi.getCellNature(this.x, this.y - 1)) || Util.constainsCharacter(this.envi.getCellContent(this.x, this.y - 1))) 
        && this.envi.getCellNature(this.x + 1, this.y - 1) == Cell.PLT){
            this.envi.dig(this.x + 1, this.y - 1);
            this.engi.addHole(this.x + 1, this.y - 1);
        }   

    
    }


}