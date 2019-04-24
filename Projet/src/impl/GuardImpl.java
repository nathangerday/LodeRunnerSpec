package impl;

import java.util.HashSet;
import java.util.Set;

import data.Cell;
import data.Command;
import services.Character;
import services.Environment;
import services.Guard;
import utils.Util;

public class GuardImpl extends CharacterImpl implements Guard {

    private static int count = 0;
    private int id;
    private Character target;
    private int timeInHole;

    public void init(Environment s, int x, int y, Character target){
        super.init(s, x, y);
        this.id = count++;
        this.target = target;
        this.timeInHole = 0;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Command getBehaviour() {
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
        
        if(HOL_HDR.contains(getEnvi().getCellNature(x, y)) || PLT_MLT_HDR.contains(getEnvi().getCellNature(x, y - 1)) || Util.constainsCharacter(getEnvi().getCellContent(x, y - 1))){
            if(target.getCol() < getCol()){
                return Command.MOVEL;
            }else if(target.getCol() > getCol()){
                return Command.MOVER;
            }else{
                return Command.NONE;
            }
        }

        if(getEnvi().getCellNature(x, y) == Cell.LAD && (PLT_MLT_HDR.contains(getEnvi().getCellNature(x, y - 1)) || Util.constainsCharacter(getEnvi().getCellContent(x, y - 1)))){
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
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if(getCol() != 0 && !MTL_PLT.contains(envi.getCellNature(x - 1, y + 1)) && !Util.constainsCharacter(envi.getCellContent(x - 1, y + 1))){
            this.envi.removeCharacter(this.x, this.y);
            this.x -= 1;
            this.y += 1;
            this.envi.addToCellContent(this.x, this.y, this);
        }
    }

    @Override
    public void climbRight() {
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if(getCol() != envi.getWidth() - 1 && !MTL_PLT.contains(envi.getCellNature(x + 1, y + 1)) && !Util.constainsCharacter(envi.getCellContent(x + 1, y + 1))){
            this.envi.removeCharacter(this.x, this.y);
            this.x += 1;
            this.y += 1;
            this.envi.addToCellContent(this.x, this.y, this);
        }
    }

    @Override
    public void step() {
        Set<Cell> EMP_HDR_HOL = new HashSet<>();
        EMP_HDR_HOL.add(Cell.EMP);
        EMP_HDR_HOL.add(Cell.HDR);
        EMP_HDR_HOL.add(Cell.HOL);

        Set<Cell> LAD_HDR_HOL = new HashSet<>();
        LAD_HDR_HOL.add(Cell.LAD);
        LAD_HDR_HOL.add(Cell.HDR);
        LAD_HDR_HOL.add(Cell.HOL);
        if(!LAD_HDR_HOL.contains(this.envi.getCellNature(this.x, this.y)) && EMP_HDR_HOL.contains(this.envi.getCellNature(this.x, this.y - 1)) &&
        !Util.constainsCharacter(this.envi.getCellContent(this.x, this.y - 1))){
            goDown();
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
    
    private boolean isFalling(){
        Set<Cell> LAD_HDR_HOL = new HashSet<>();
        LAD_HDR_HOL.add(Cell.LAD);
        LAD_HDR_HOL.add(Cell.HDR);


        Set<Cell> EMP_HDR_HOL = new HashSet<>();
        EMP_HDR_HOL.add(Cell.EMP);
        EMP_HDR_HOL.add(Cell.HDR);
        EMP_HDR_HOL.add(Cell.HOL);

        

        boolean cond1 = !LAD_HDR_HOL.contains(getEnvi().getCellNature(x, y ));
        boolean cond2 = EMP_HDR_HOL.contains(getEnvi().getCellNature(x, y - 1));
        boolean cond3 = !Util.constainsCharacter(getEnvi().getCellContent(x, y - 1));
        return cond1 && cond2 && cond3;

    }    
}