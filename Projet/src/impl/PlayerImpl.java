package impl;

import java.util.HashSet;
import java.util.Set;

import data.Cell;
import data.Command;
import data.Item;
import data.ItemType;
import services.Engine;
import services.Guard;
import services.Player;
import utils.Util;

public class PlayerImpl extends CharacterImpl implements Player {

    private Engine engi;
    private Item currentlyHeldItem;
    private int numberOfUsagesLeftForCurrentItem;
    private boolean facingRight;

    public Player copy(Engine e){
        PlayerImpl copy = new PlayerImpl();
        copy.x = this.x;
        copy.y = this.y;
        copy.engi = e;
        copy.envi = e.getEnvironment();
        copy.currentlyHeldItem = this.currentlyHeldItem;
        copy.numberOfUsagesLeftForCurrentItem = this.numberOfUsagesLeftForCurrentItem;
        copy.facingRight = this.facingRight;
        return copy;
    }

    public Engine getEngine() {
        return this.engi;
    }

    public void step() {
        Set<Cell> EMP_HDR_HOL_NGU = new HashSet<>();
        EMP_HDR_HOL_NGU.add(Cell.EMP);
        // EMP_HDR_HOL_NGU.add(Cell.LAD);
        EMP_HDR_HOL_NGU.add(Cell.HDR);
        EMP_HDR_HOL_NGU.add(Cell.HOL);
        EMP_HDR_HOL_NGU.add(Cell.NGU);

        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);
        if (!LAD_HDR.contains(this.envi.getCellNature(this.x, this.y))
                && EMP_HDR_HOL_NGU.contains(this.envi.getCellNature(this.x, this.y - 1))
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
        this.currentlyHeldItem = new Item(ItemType.Sword, 0, 0);
        this.numberOfUsagesLeftForCurrentItem = 3;
        this.facingRight = true;
    }

    public void digLeft() {
        Set<Cell> MTL_PLT_LAD_DOR_NPL = new HashSet<>();
        MTL_PLT_LAD_DOR_NPL.add(Cell.MTL);
        MTL_PLT_LAD_DOR_NPL.add(Cell.PLT);
        MTL_PLT_LAD_DOR_NPL.add(Cell.LAD);
        MTL_PLT_LAD_DOR_NPL.add(Cell.DOR);
        MTL_PLT_LAD_DOR_NPL.add(Cell.NPL);

        Set<Cell> libre = new HashSet<>();
        libre.add(Cell.EMP);
        libre.add(Cell.LAD);
        libre.add(Cell.HDR);
        libre.add(Cell.HOL);

        if (this.getCol() != 0
                && (MTL_PLT_LAD_DOR_NPL.contains(this.envi.getCellNature(this.x, this.y - 1))
                        || Util.containsGuard(this.envi.getCellContent(this.x, this.y - 1)))
                && libre.contains(this.envi.getCellNature(this.x - 1, this.y))
                && this.envi.getCellNature(this.x - 1, this.y - 1) == Cell.PLT) {
            this.envi.dig(this.x - 1, this.y - 1);
            this.engi.addHole(this.x - 1, this.y - 1);
        }
        this.facingRight = false;

    }

    public void digRight() {
        Set<Cell> MTL_PLT_LAD_DOR_NPL = new HashSet<>();
        MTL_PLT_LAD_DOR_NPL.add(Cell.MTL);
        MTL_PLT_LAD_DOR_NPL.add(Cell.PLT);
        MTL_PLT_LAD_DOR_NPL.add(Cell.LAD);
        MTL_PLT_LAD_DOR_NPL.add(Cell.DOR);
        MTL_PLT_LAD_DOR_NPL.add(Cell.NPL);

        Set<Cell> libre = new HashSet<>();
        libre.add(Cell.EMP);
        libre.add(Cell.LAD);
        libre.add(Cell.HDR);
        libre.add(Cell.HOL);

        if (this.getCol() != this.envi.getWidth() - 1
                && (MTL_PLT_LAD_DOR_NPL.contains(this.envi.getCellNature(this.x, this.y - 1))
                        || Util.containsGuard(this.envi.getCellContent(this.x, this.y - 1)))
                && libre.contains(this.envi.getCellNature(this.x + 1, this.y))
                && this.envi.getCellNature(this.x + 1, this.y - 1) == Cell.PLT) {
            this.envi.dig(this.x + 1, this.y - 1);
            this.engi.addHole(this.x + 1, this.y - 1);
        }
        this.facingRight = true;
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
    public void pickupItem(ItemType type){
        int keyUsages = 1;
        int gunUsages = 1;
        int flashUsages = 1;
        int swordUsages = 3;
        
        switch(type){
            case Key:
                if(this.currentlyHeldItem != null && this.currentlyHeldItem.getNature() == ItemType.Key){
                    this.numberOfUsagesLeftForCurrentItem += keyUsages;
                }else{
                    this.currentlyHeldItem = new Item(type, 0, 0);
                    this.numberOfUsagesLeftForCurrentItem = keyUsages;
                }
                break;
            case Gun:
                if(this.currentlyHeldItem != null && this.currentlyHeldItem.getNature() == ItemType.Gun){
                    this.numberOfUsagesLeftForCurrentItem += gunUsages;
                }else{
                    this.currentlyHeldItem = new Item(type, 0, 0);
                    this.numberOfUsagesLeftForCurrentItem = gunUsages;
                }
                break;
            case Sword:
                if(this.currentlyHeldItem != null && this.currentlyHeldItem.getNature() == ItemType.Sword){
                    this.numberOfUsagesLeftForCurrentItem += swordUsages;
                }else{
                    this.currentlyHeldItem = new Item(type, 0, 0);
                    this.numberOfUsagesLeftForCurrentItem = swordUsages;
                }
                break;
            case Flash:
                if(this.currentlyHeldItem != null && this.currentlyHeldItem.getNature() == ItemType.Flash){
                    this.numberOfUsagesLeftForCurrentItem += flashUsages;
                }else{
                    this.currentlyHeldItem = new Item(type, 0, 0);
                    this.numberOfUsagesLeftForCurrentItem = flashUsages;
                }
                break;
        }
    }

    @Override
    public void useItem() {
        if(getCurrentlyHeldItem() == null || getNumberOfUsagesLeftForCurrentItem() == 0){
            return;
        }

        Set<Cell> MTL_PLT_DOR_NPL = new HashSet<>();
        MTL_PLT_DOR_NPL.add(Cell.MTL);
        MTL_PLT_DOR_NPL.add(Cell.PLT);
        MTL_PLT_DOR_NPL.add(Cell.DOR);
        MTL_PLT_DOR_NPL.add(Cell.NPL);

        switch(getCurrentlyHeldItem().getNature()){
            case Gun:
                if(isFacingRight()){
                    for(int i = this.x + 1; i<getEnvi().getWidth(); i++){
                        if(Util.containsGuard(getEnvi().getCellContent(i, y))){
                            Util.getGuard(getEnvi().getCellContent(i, y)).moveToInitCoords();
                        }

                        if(MTL_PLT_DOR_NPL.contains(getEnvi().getCellNature(i, y))){
                            break;
                        }
                    }
                }else{
                    for(int i = this.x - 1; i>=0; i--){
                        if(Util.containsGuard(getEnvi().getCellContent(i, y))){
                            Util.getGuard(getEnvi().getCellContent(i, y)).moveToInitCoords();
                        }

                        if(MTL_PLT_DOR_NPL.contains(getEnvi().getCellNature(i, y))){
                            break;
                        }
                    }
                }
                break;
            case Sword:
                if(this.x - 2 >= 0){
                    if(Util.containsGuard(getEnvi().getCellContent(this.x - 2, this.y))){
                        Util.getGuard(getEnvi().getCellContent(this.x - 2, this.y)).moveToInitCoords();
                    }
                }
                if(this.x - 1 >= 0){
                    if(Util.containsGuard(getEnvi().getCellContent(this.x - 1, this.y))){
                        Util.getGuard(getEnvi().getCellContent(this.x - 1, this.y)).moveToInitCoords();
                    }
                }
                if(this.x + 2 < getEnvi().getWidth()){
                    if(Util.containsGuard(getEnvi().getCellContent(this.x + 2, this.y))){
                        Util.getGuard(getEnvi().getCellContent(this.x + 2, this.y)).moveToInitCoords();
                    }
                }
                if(this.x + 1 < getEnvi().getWidth()){
                    if(Util.containsGuard(getEnvi().getCellContent(this.x + 1, this.y))){
                        Util.getGuard(getEnvi().getCellContent(this.x + 1, this.y)).moveToInitCoords();
                    }
                }
                break;
            case Flash:
                for(Guard g : getEngine().getGuards()){
                    g.paralyze();
                }
                break;
            case Key:
                if(isFacingRight() && this.x < getEnvi().getWidth() - 1){
                    if(getEnvi().getCellNature(this.x + 1, this.y) == Cell.DOR){
                        getEnvi().openDoor(this.x + 1, this.y);
                    }
                }else if(!isFacingRight() && this.x > 0){
                    if(getEnvi().getCellNature(this.x - 1, this.y) == Cell.DOR){
                        getEnvi().openDoor(this.x - 1, this.y);
                    }
                }
                break;
        }

         this.numberOfUsagesLeftForCurrentItem--;
         if(this.numberOfUsagesLeftForCurrentItem == 0){
             this.currentlyHeldItem = null;
         }
    }

    @Override
    public void goLeft() {
        this.facingRight = false;
        if(this.x != 0 && this.envi.getCellNature(this.x - 1, this.y) != Cell.NPL){ 
            super.goLeft();
        }
    }

    @Override
    public void goRight() {
        this.facingRight = true;
        if(this.x != this.envi.getWidth() - 1 && this.envi.getCellNature(this.x + 1, this.y) != Cell.NPL){ 
            super.goRight();
        }
    }
    
    @Override
    public void goDown() {
        if(this.y != 0 && this.envi.getCellNature(this.x, this.y - 1) != Cell.NPL){ 
            super.goDown();
        }
    }

    @Override
    public void goUp() {
        if(this.x != this.envi.getHeight() - 1 && this.envi.getCellNature(this.x, this.y + 1) != Cell.NPL){ 
            super.goUp();
        }
    }
}