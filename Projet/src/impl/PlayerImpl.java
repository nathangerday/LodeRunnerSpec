package impl;

import java.util.HashSet;
import java.util.Set;

import data.Cell;
import data.Command;
import data.Item;
import services.Engine;
import services.Player;
import utils.Util;

public class PlayerImpl extends CharacterImpl implements Player {

    private Engine engi;
    private Item currentlyHeldItem;
    private int numberOfUsagesLeftForCurrentItem;
    private boolean facingRight;

    

    public Engine getEngine() {
        return this.engi;
    }

    public void step() {
        Set<Cell> EMP_HDR_HOL = new HashSet<>();
        EMP_HDR_HOL.add(Cell.EMP);
        // EMP_HDR_HOL.add(Cell.LAD);
        EMP_HDR_HOL.add(Cell.HDR);
        EMP_HDR_HOL.add(Cell.HOL);

        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);
        if (!LAD_HDR.contains(this.envi.getCellNature(this.x, this.y))
                && EMP_HDR_HOL.contains(this.envi.getCellNature(this.x, this.y - 1))
                && !Util.containsGuard(this.envi.getCellContent(this.x, this.y - 1))) {
            goDown();
            return;
        }

        Command next = this.engi.getNextCommand();
        switch (next) {
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
        case USEITEM:
            useItem();
            break;
        case NONE:
            break;
        }
    }

    public void init(int x, int y, Engine e) {
        super.init(e.getEnvironment(), x, y);
        this.engi = e;
        this.currentlyHeldItem = null;
        this.numberOfUsagesLeftForCurrentItem = 0;
        this.facingRight = true;
    }

    public void digLeft() {
        Set<Cell> MTL_PLT_LAD = new HashSet<>();
        MTL_PLT_LAD.add(Cell.MTL);
        MTL_PLT_LAD.add(Cell.PLT);
        MTL_PLT_LAD.add(Cell.LAD);

        Set<Cell> libre = new HashSet<>();
        libre.add(Cell.EMP);
        libre.add(Cell.LAD);
        libre.add(Cell.HDR);
        libre.add(Cell.HOL);

        if (this.getCol() != 0
                && (MTL_PLT_LAD.contains(this.envi.getCellNature(this.x, this.y - 1))
                        || Util.containsGuard(this.envi.getCellContent(this.x, this.y - 1)))
                && this.envi.getCellNature(this.x - 1, this.y - 1) == Cell.PLT) {
            this.envi.dig(this.x - 1, this.y - 1);
            this.engi.addHole(this.x - 1, this.y - 1);
        }

    }

    public void digRight() {
        Set<Cell> MTL_PLT_LAD = new HashSet<>();
        MTL_PLT_LAD.add(Cell.MTL);
        MTL_PLT_LAD.add(Cell.PLT);
        MTL_PLT_LAD.add(Cell.LAD);

        Set<Cell> libre = new HashSet<>();
        libre.add(Cell.EMP);
        libre.add(Cell.LAD);
        libre.add(Cell.HDR);
        libre.add(Cell.HOL);

        if (this.getCol() != this.envi.getWidth() - 1
                && (MTL_PLT_LAD.contains(this.envi.getCellNature(this.x, this.y - 1))
                        || Util.containsGuard(this.envi.getCellContent(this.x, this.y - 1)))
                && this.envi.getCellNature(this.x + 1, this.y - 1) == Cell.PLT) {
            this.envi.dig(this.x + 1, this.y - 1);
            this.engi.addHole(this.x + 1, this.y - 1);
        }

    }

    @Override
    public void goRight(){
        super.goRight();
        this.facingRight = true;
    }

    @Override
    public void goLeft(){
        super.goLeft();
        this.facingRight = false;
    }

    @Override
    public boolean isFacingRight() {
        return this.facingRight;
    }

    @Override
    public Item getCurrentlyHeldItem() {
        return this.currentlyHeldItem;
    }

    @Override
    public int getNumberOfUsagesLeftForCurrentItem() {
        return this.numberOfUsagesLeftForCurrentItem;
    }

    @Override
    public void useItem() {
        System.out.println("HEY I'M USING AN ITEM");
    }
}