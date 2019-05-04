package impl;

import java.util.HashSet;
import java.util.Set;

import data.Cell;
import data.Command;
import data.Coord;
import data.GuardType;
import data.Item;
import data.ItemType;
import services.Character;
import services.Engine;
import services.Environment;
import services.Guard;
import utils.Util;

public class GuardImpl extends CharacterImpl implements Guard {

    private static int count = 0;
    private int id;
    private Character target;
    private int timeInHole;
    private Coord initCoords;
    private boolean carryingTreasure;
    private Engine engi;
    private int timeLeftParalyzed;

    public Guard copy(Engine e){
        GuardImpl copy = new GuardImpl();
        copy.x = this.x;
        copy.y = this.y;
        copy.engi = e;
        copy.envi = e.getEnvironment();
        copy.target = e.getPlayer();
        copy.timeInHole = this.timeInHole;
        copy.id = this.id;
        copy.initCoords = this.initCoords;
        copy.carryingTreasure = this.carryingTreasure;
        copy.timeLeftParalyzed = this.timeLeftParalyzed;
        return copy;
    }
    

    public void init(Engine e, int x, int y, Character target){
        super.init(e.getEnvironment(), x, y);
        this.engi = e;
        this.id = count++;
        this.target = target;
        this.timeInHole = 0;
        this.initCoords = new Coord(x, y);
        this.carryingTreasure = false;
        this.timeLeftParalyzed = 0;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getIdCounter(){
        return count;
    }

    @Override
    public Coord getInitCoords(){
        return this.initCoords;
    }

    @Override
    public boolean isCarryingTreasure(){
        return this.carryingTreasure;
    }

    @Override
    public Engine getEngine(){
        return this.engi;
    }

    @Override
    public GuardType getNature(){
        return GuardType.NORMAL;
    }

    @Override
    public int getTimeLeftParalyzed() {
        return this.timeLeftParalyzed;
    }

    @Override
    public Command getBehaviour() {
        //TODO Improve behaviour
        if(getEnvi().getCellNature(x, y) == Cell.LAD && 
            getTarget().getHgt() > getHgt()){
                return Command.MOVEU;
        }
        Set<Cell> HOL_HDR = new HashSet<>();
        HOL_HDR.add(Cell.HOL);
        HOL_HDR.add(Cell.HDR);

        Set<Cell> PLT_MLT_HDR = new HashSet<>();
        PLT_MLT_HDR.add(Cell.PLT);
        PLT_MLT_HDR.add(Cell.PLT);
        PLT_MLT_HDR.add(Cell.LAD);

        if(getEnvi().getCellNature(x, y) == Cell.HOL && this.target.getCol() == this.getCol() && this.target.getHgt() == this.getHgt() + 1){
            return Command.MOVER;
        }

        
        if(HOL_HDR.contains(getEnvi().getCellNature(x, y)) || PLT_MLT_HDR.contains(getEnvi().getCellNature(x, y - 1)) || Util.containsGuard(getEnvi().getCellContent(x, y - 1))){
            if(target.getCol() < getCol()){
                return Command.MOVEL;
            }else if(target.getCol() > getCol()){
                return Command.MOVER;
            }else{
                return Command.NONE;
            }
        }

        if(getEnvi().getCellNature(x, y) == Cell.LAD && (PLT_MLT_HDR.contains(getEnvi().getCellNature(x, y - 1)) || Util.containsGuard(getEnvi().getCellContent(x, y - 1)))){
            int distanceh = getCol() - target.getCol();
            int distancev = getHgt() - target.getHgt();

            if(Math.abs(distancev) <= Math.abs(distanceh)){
                if(distancev < 0){
                    return Command.MOVEU;
                }
                //TODO D'apres la spec, on ne peut pas aller en bas puisque c'est pas une case libre
                // else{
                //     return Command.MOVED;
                // }
                return Command.NONE;
            }else{
                if(distanceh < 0){
                    return Command.MOVER;
                }else{
                    return Command.MOVEL;
                }
            }
        }

        return Command.NONE;
    }

    @Override
    public Character getTarget() {
        return this.target;
    }

    @Override
    public int getTimeInHole() {
        return this.timeInHole;
    }

    @Override
    public void climbLeft() {
        Set<Cell> MTL_PLT_DOR_NGU = new HashSet<>();
        MTL_PLT_DOR_NGU.add(Cell.MTL);
        MTL_PLT_DOR_NGU.add(Cell.PLT);
        MTL_PLT_DOR_NGU.add(Cell.DOR);
        MTL_PLT_DOR_NGU.add(Cell.NGU);
        if(getCol() != 0 && !MTL_PLT_DOR_NGU.contains(envi.getCellNature(x - 1, y + 1)) && !Util.containsGuard(envi.getCellContent(x - 1, y + 1))){
            this.envi.removeFromCellContent(this.x, this.y, this);
            if(!Util.containsPlayer(envi.getCellContent(x, y + 1))){ //if player above, stops on the player
                this.x -= 1;
            }
            this.y += 1;
            this.envi.addToCellContent(this.x, this.y, this);
        }
    }

    @Override
    public void climbRight() {
        Set<Cell> MTL_PLT_DOR_NGU = new HashSet<>();
        MTL_PLT_DOR_NGU.add(Cell.MTL);
        MTL_PLT_DOR_NGU.add(Cell.PLT);
        MTL_PLT_DOR_NGU.add(Cell.DOR);
        MTL_PLT_DOR_NGU.add(Cell.NGU);
        if(getCol() != envi.getWidth() - 1 && !MTL_PLT_DOR_NGU.contains(envi.getCellNature(x + 1, y + 1)) && !Util.containsGuard(envi.getCellContent(x + 1, y + 1))){
            this.envi.removeFromCellContent(this.x, this.y, this);
            if(!Util.containsPlayer(envi.getCellContent(x, y + 1))){
                this.x += 1;
            }
            this.y += 1;
            this.envi.addToCellContent(this.x, this.y, this);
        }
    }

    @Override
    public void moveToInitCoords(){
        if(Util.containsGuard(this.envi.getCellContent(this.initCoords.getX(), this.initCoords.getY()))){
            Util.getGuard(this.envi.getCellContent(this.initCoords.getX(), this.initCoords.getY())).moveToInitCoords();
        }
        this.envi.removeFromCellContent(this.x, this.y, this);
        this.x = this.initCoords.getX();
        this.y = this.initCoords.getY();
        this.envi.addToCellContent(this.x, this.y, this);
        this.timeInHole = 0;
        this.timeLeftParalyzed = 0;
    }

    @Override
    public void paralyze(){
        this.timeLeftParalyzed = 10;
    }

    @Override
    public void step() {
        if(Util.containsTreasure(this.envi.getCellContent(x, y)) && !this.carryingTreasure){
            Item t = Util.removeTreasure(this.envi.getCellContent(x, y));
            this.engi.getTreasures().remove(t);
            this.carryingTreasure = true;
        }

        Set<Cell> EMP_HDR_HOL = new HashSet<>();
        EMP_HDR_HOL.add(Cell.EMP);
        EMP_HDR_HOL.add(Cell.HDR);
        EMP_HDR_HOL.add(Cell.HOL);

        Set<Cell> LAD_HDR_HOL = new HashSet<>();
        LAD_HDR_HOL.add(Cell.LAD);
        LAD_HDR_HOL.add(Cell.HDR);
        LAD_HDR_HOL.add(Cell.HOL);
        if(!LAD_HDR_HOL.contains(this.envi.getCellNature(this.x, this.y)) && EMP_HDR_HOL.contains(this.envi.getCellNature(this.x, this.y - 1)) &&
        !Util.containsGuard(this.envi.getCellContent(this.x, this.y - 1))){
            if(this.carryingTreasure && this.envi.getCellNature(this.x, this.y - 1) == Cell.HOL && !Util.containsTreasure(this.envi.getCellContent(this.x, this.y))){
                this.carryingTreasure = false;
                Item t = new Item(ItemType.Treasure, this.x, this.y);
                this.envi.addToCellContent(this.x, this.y, t);
                this.engi.getTreasures().add(t);
            }
            goDown();
            return;
        }

        if(this.timeLeftParalyzed > 0){
            this.timeLeftParalyzed--;
            return;
        }


        if(getEnvi().getCellNature(x, y) == Cell.HOL){
            if(this.timeInHole < 5){
                this.timeInHole++;
            }else{
                if(getBehaviour() == Command.MOVEL){
                    climbLeft();
                }else if(getBehaviour() == Command.MOVER){
                    climbRight();
                }
                this.timeInHole = 0;
            }
            return;
        }

        if(getBehaviour() == Command.MOVEL){
            goLeft();
        }else if(getBehaviour() == Command.MOVER){
            goRight();
        }else if(getBehaviour() == Command.MOVEU){
            goUp();
        }else if(getBehaviour() == Command.MOVED){
            goDown();
        }



    }

    @Override
    public void goLeft() {
        if(this.x != 0 && this.envi.getCellNature(this.x - 1, this.y) != Cell.NGU){ 
            super.goLeft();
        }
    }

    @Override
    public void goRight() {
        if(this.x != this.envi.getWidth() - 1 && this.envi.getCellNature(this.x + 1, this.y) != Cell.NGU){ 
            super.goRight();
        }
    }
    
    @Override
    public void goDown() {
        if(this.y != 0 && this.envi.getCellNature(this.x, this.y - 1) != Cell.NGU){ 
            super.goDown();
        }
    }

    @Override
    public void goUp() {
        if(this.x != this.envi.getHeight() - 1 && this.envi.getCellNature(this.x, this.y + 1) != Cell.NGU){ 
            super.goUp();
        }
    }
}