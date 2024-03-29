package impl;

import java.util.HashSet;
import java.util.Set;

import data.Cell;
import services.Character;
import services.Environment;
import utils.Util;

public class CharacterImplBug implements Character{
    protected int x,y;
    protected Environment envi;

    public void init(Environment s, int x, int y){
        this.x = x;
        this.y = y;
        this.envi = s;
        this.envi.addToCellContent(x, y, this);
    }

    @Override
    public Character copy(){
        CharacterImplBug copy = new CharacterImplBug();
        copy.x = x;
        copy.y = y;
        copy.envi = envi.copy();
        return copy;
    }

    public Environment getEnvi() {
        return this.envi;
    }

    @Override
    public int getHgt() {
        return this.y;
    }

    @Override
    public int getCol() {
        return this.x;
    }

    @Override
    public void goLeft() {
        Set<Cell> MTL_PLT_DOR = new HashSet<>();
        MTL_PLT_DOR.add(Cell.MTL);
        MTL_PLT_DOR.add(Cell.PLT);
        MTL_PLT_DOR.add(Cell.DOR);
        
        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);

        Set<Cell> MTL_PLT_LAD_DOR = new HashSet<>();
        MTL_PLT_LAD_DOR.add(Cell.MTL);
        MTL_PLT_LAD_DOR.add(Cell.PLT);
        MTL_PLT_LAD_DOR.add(Cell.LAD);
        MTL_PLT_LAD_DOR.add(Cell.DOR);
        if(this.x != 0 && !MTL_PLT_DOR.contains(this.envi.getCellNature(this.x - 1, this.y)) && 
            (LAD_HDR.contains(this.envi.getCellNature(this.x, this.y)) || MTL_PLT_LAD_DOR.contains(this.envi.getCellNature(this.x, this.y - 1)) || Util.containsGuard(this.envi.getCellContent(this.x, this.y - 1)))
           && !Util.containsGuard(this.envi.getCellContent(this.x - 1, this.y))){
                this.envi.removeFromCellContent(this.x, this.y, this);
                this.x = this.x - 2; //BUG On se deplace de 2 cases au lieu d'une 
                this.envi.addToCellContent(this.x, this.y, this);
                
        }

    }

    @Override
    public void goRight() {
        Set<Cell> MTL_PLT_DOR = new HashSet<>();
        MTL_PLT_DOR.add(Cell.MTL);
        MTL_PLT_DOR.add(Cell.PLT);
        MTL_PLT_DOR.add(Cell.DOR);
        
        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);

        Set<Cell> MTL_PLT_LAD_DOR = new HashSet<>();
        MTL_PLT_LAD_DOR.add(Cell.MTL);
        MTL_PLT_LAD_DOR.add(Cell.PLT);
        MTL_PLT_LAD_DOR.add(Cell.LAD);
        MTL_PLT_LAD_DOR.add(Cell.DOR);
        if(this.x != this.envi.getWidth() - 1 && !MTL_PLT_DOR.contains(this.envi.getCellNature(this.x + 1, this.y)) && 
            (LAD_HDR.contains(this.envi.getCellNature(this.x, this.y)) || MTL_PLT_LAD_DOR.contains(this.envi.getCellNature(this.x, this.y - 1)) || Util.containsGuard(this.envi.getCellContent(this.x, this.y - 1)))
           && !Util.containsGuard(this.envi.getCellContent(this.x + 1, this.y))){
                this.envi.removeFromCellContent(this.x, this.y, this);
                this.x = this.x + 2; //BUG On se deplace de 2 cases au lieu d'une 
                this.envi.addToCellContent(this.x, this.y, this);
                
        }
    }
    
    @Override
    public void goDown() {
        Set<Cell> libre = new HashSet<>();
        libre.add(Cell.EMP);
        libre.add(Cell.LAD);
        libre.add(Cell.HDR);
        libre.add(Cell.HOL);

        if(this.y != 0 && libre.contains(this.envi.getCellNature(this.x, this.y - 1)) && !Util.containsGuard(this.envi.getCellContent(this.x, this.y - 1))){
            this.envi.removeFromCellContent(this.x, this.y, this);
            this.y = this.y - 2; //BUG On se deplace de 2 cases au lieu d'une 
            this.envi.addToCellContent(this.x, this.y, this);
        }
    }

    @Override
    public void goUp() {
        Set<Cell> libre = new HashSet<>();
        libre.add(Cell.EMP);
        libre.add(Cell.LAD);
        libre.add(Cell.HDR);
        libre.add(Cell.HOL);

        if(this.y != this.envi.getHeight() - 1 && this.envi.getCellNature(this.x, this.y) == Cell.LAD && libre.contains(this.envi.getCellNature(this.x, this.y + 1))
            && !Util.containsGuard(this.envi.getCellContent(this.x, this.y + 1)) ){
            this.envi.removeFromCellContent(this.x, this.y, this);
            // this.y = this.y + 1; //BUG On se ne déplace pas
            this.envi.addToCellContent(this.x, this.y, this);
        }
    }


    
}