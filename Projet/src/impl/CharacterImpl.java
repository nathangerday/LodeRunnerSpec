package impl;

import java.util.HashSet;
import java.util.Set;

import data.Cell;
import services.Character;
import services.Environment;
import services.Screen;
import utils.Util;

public class CharacterImpl implements Character{
    private int x,y;
    private Environment envi;

    //TODO Veut-on directement un environment ici ? Dans le sujet on donne un Screen, sauf qu'on veut que getEnvi renvoi un environment. Il faut peut etre créer ici l'environement a partir du screen...
    public void init(Environment s, int x, int y){
        this.x = x;
        this.y = y;
        this.envi = s;
        this.envi.addToCellContent(x, y, this);
    }

    public Environment getEnvi() {
        return this.envi;
    }

    @Override
    public int getHgt() {
        return this.y;
    }

    @Override
    public int getWdt() {
        return this.x;
    }

    @Override
    public void goLeft() {
        if(this.x == 0){
            return;
        }
        Cell currentCaseNature = this.envi.getCellNature(this.x, this.y);
        Cell leftCaseNature = this.envi.getCellNature(this.x - 1, this.y);
        Cell belowCaseNature = this.envi.getCellNature(this.x, this.y - 1);
        
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if(MTL_PLT.contains(leftCaseNature)){
            return;
        }

        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);

        //TODO Les 2 premiers if sont potentiellement inutiles pusiqu'ils se repetent dans le 3eme
        if(!LAD_HDR.contains(currentCaseNature) && !MTL_PLT.contains(belowCaseNature) && !Util.constainsCharacter(this.envi.getCellContent(this.x, this.y - 1))){
            return;
        }

        if(Util.constainsCharacter(this.envi.getCellContent(this.x - 1, this.y))){
            return;
        }


        Set<Cell> MTL_PLT_LAD = new HashSet<>();
        MTL_PLT_LAD.add(Cell.MTL);
        MTL_PLT_LAD.add(Cell.PLT);
        MTL_PLT_LAD.add(Cell.LAD);
        if(this.x != 0 && !MTL_PLT.contains(leftCaseNature) && 
            (LAD_HDR.contains(currentCaseNature) || MTL_PLT_LAD.contains(belowCaseNature) || Util.constainsCharacter(this.envi.getCellContent(this.x, this.y - 1)))
           && !Util.constainsCharacter(this.envi.getCellContent(this.x - 1, this.y))){
                this.envi.removeCharacter(x, y);
                this.x = this.x - 1; 
                this.envi.addToCellContent(x, y, this);
                
           }

    }

    @Override
    public void goRight() {

    }

    @Override
    public void goUp() {

    }

    @Override
    public void goDown() {

    }

    
}