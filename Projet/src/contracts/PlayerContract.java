package contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Cell;
import data.Command;
import data.Coord;
import data.Entity;
import data.Item;
import data.ItemType;
import decorators.PlayerDecorator;
import impl.EditableScreenImpl;
import impl.EngineImpl;
import impl.EnvironmentImpl;
import impl.PlayerImpl;
import services.EditableScreen;
import services.Engine;
import services.Environment;
import services.Guard;
import services.Player;
import services.ScreenManager;
import utils.Factory;
import utils.Util;

public class PlayerContract extends PlayerDecorator{

    public PlayerContract(Player delegate){
        super(delegate);
    }

    public void checkInvariant(){
        
    }

    @Override
    public void init(int x, int y, Engine e) {
        //\pre e != null
        if(!(e != null)){
            Contractor.defaultContractor().preconditionError("PlayerContract", "init", "e != null");
        }

        //\pre x >= 0
        if(!(x >= 0)){
            Contractor.defaultContractor().preconditionError("PlayerContract", "init", "x >= 0");
        }

        //\pre x < e.getEnvironment().getWidth()
        if(!(x < e.getEnvironment().getWidth())){
            Contractor.defaultContractor().preconditionError("PlayerContract", "init", "x < e.getEnvironment().getWidth()");
        }

        //\pre y >= 0
        if(!(y >= 0)){
            Contractor.defaultContractor().preconditionError("PlayerContract", "init", "y >= 0");
        }

        //\pre y < e.getEnvironment().getHeight()
        if(!(y < e.getEnvironment().getHeight())){
            Contractor.defaultContractor().preconditionError("PlayerContract", "init", "y < e.getEnvironment().getHeight()");
        }

        //\pre \not s.getCellNature(x, y) \in {MTL, PLT, DOR, NPL}
        Set<Cell> MTL_PLT_DOR_NPL = new HashSet<>();
        MTL_PLT_DOR_NPL.add(Cell.MTL);
        MTL_PLT_DOR_NPL.add(Cell.PLT);
        MTL_PLT_DOR_NPL.add(Cell.DOR);
        MTL_PLT_DOR_NPL.add(Cell.NPL);
        if(!(!MTL_PLT_DOR_NPL.contains(e.getEnvironment().getCellNature(x, y)))){
            Contractor.defaultContractor().preconditionError("PlayerContract", "init", "\\not s.getCellNature(x, y) \\in {MTL, PLT, DOR, NPL}");
        }
        super.init(x, y, e);

        //inv post
        checkInvariant();

        //\post getHgt() == y
        if(!(getHgt() == y)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "init", "getHgt() == y");
        }

        //\post getCol() == x
        if(!(getCol() == x)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "init", "getCol() == x");
        }

        //\post getEnvi() == e.getEnvironment()
        if(!(getEnvi() == e.getEnvironment())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "init", "getEnvi() == e.getEnvironment()");
        }

        //\post this \in getEnvi().getCellContent(x, y)
        if(!(getEnvi().getCellContent(x, y).contains(getDelegate()))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "init", "this \\in getEnvi().getCellContent(x, y)");
        }

        //\post getEngine() == e
        if(!(getEngine() == e)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "init", "getEngine() == e");
        }

        //\post getCurrentlyHeldItem().getNature() == Sword
        if(!(getCurrentlyHeldItem().getNature() == ItemType.Sword)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "init", "getCurrentlyHeldItem().getNature() == Sword");
        }

        //\post getNumberOfUsagesLeftForCurrentItem() == 3
        if(!(getNumberOfUsagesLeftForCurrentItem() == 3)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "init", "getNumberOfUsagesLeftForCurrentItem() == 3");
        }

        //\post isFacingRight()
        if(!(isFacingRight())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "init", "isFacingRight()");
        }

    }

    @Override
    public void step() {
        //captures
        Cell[][] getCellNature_atPre = new Cell[getEnvi().getWidth()][getEnvi().getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvi().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvi().getHeight(); v++){
                getCellNature_atPre[u][v] = getEnvi().getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvi().getCellContent(u, v)));
            }
        }
        int getHgt_atPre = getHgt();
        int getCol_atPre = getCol();
        Environment getEnvi_atPre = getEnvi();
        PlayerContract this_atPre = new PlayerContract(duplicatePlayer(getDelegate()));


        //inv pre
        checkInvariant();

        super.step();

        //inv post
        checkInvariant();

        // //\post falling \impl step() == this@pre.goDown()
        if(isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre)){
            PlayerContract clone = new PlayerContract(duplicatePlayer(this_atPre.getDelegate()));
            clone.goDown();
            if(!(Checker.implication(isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre), this.isEqual(clone)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "step", "falling \\impl step() == this@pre.goDown()");
            }
        }

        if(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVEL){
            //\post (\not falling) \and getNextCommand()@pre == MOVEL \impl step() == this@pre.goLeft()
            PlayerContract clone = new PlayerContract(duplicatePlayer(this_atPre.getDelegate()));
            clone.goLeft();
            if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVEL, this.isEqual(clone)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == MOVEL \\impl step() == this@pre.goLeft()");
            }
        }


        //\post (\not falling) \and getNextCommand()@pre == MOVER \impl step() == this@pre.goRight()
        if(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVER){
            PlayerContract clone = new PlayerContract(duplicatePlayer(this_atPre.getDelegate()));
            clone.goRight();
            if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVER, this.isEqual(clone)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == MOVER \\impl step() == this@pre.goRight()");
            }
        }

        // //\post (\not falling) \and getNextCommand()@pre == MOVED \impl step() == this@pre.goDown()
        if(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVED){
            PlayerContract clone = new PlayerContract(duplicatePlayer(this_atPre.getDelegate()));
            clone.goDown();
            if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVED, this.isEqual(clone)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == MOVED \\impl step() == this@pre.goDown()");
            }
        }

        // //\post (\not falling) \and getNextCommand()@pre == MOVEU \impl step() == this@pre.goUp()
        if(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVEU) {
            PlayerContract clone = new PlayerContract(duplicatePlayer(this_atPre.getDelegate()));
            clone.goUp();
            if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVEU, this.isEqual(clone)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == MOVEU \\impl step() == this@pre.goUp()");
            }
        }

        //\post (\not falling) \and getNextCommand()@pre == DIGL \impl step() == this@pre.digLeft()
        if(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.DIGL){
            PlayerContract clone = new PlayerContract(duplicatePlayer(this_atPre.getDelegate()));
            clone.digLeft();
            if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.DIGL, this.isEqual(clone)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == DIGL \\impl step() == this@pre.digLeft()");
            }
        }
        
        //\post (\not falling) \and getNextCommand()@pre == DIGR \impl step() == this@pre.digRight()
        if(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.DIGR){
            PlayerContract clone = new PlayerContract(duplicatePlayer(this_atPre.getDelegate()));
            clone.digRight();
            if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.DIGR, this.isEqual(clone)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == DIGR \\impl step() == this@pre.digRight()");
            }
        } 

        //\post (\not falling) \and getNextCommand()@pre == USEITEM \impl step() == this@pre.useItem()
        if(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.USEITEM){
            PlayerContract clone = new PlayerContract(duplicatePlayer(this_atPre.getDelegate()));
            clone.useItem();
            if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.USEITEM, this.isEqual(clone)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == USEITEM \\impl step() == this@pre.useItem()");
            }
        }

        //\post (\not falling) \and getNextCommand()@pre == NONE \impl this == this@pre
        if(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.NONE){
            PlayerContract clone = new PlayerContract(duplicatePlayer(this_atPre.getDelegate()));
            if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.NONE, this.isEqual(clone)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == NONE \\impl this == this@pre");
            }
        }

        //\post const getEnvi()
        if(!(getEnvi().equals(getEnvi_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "step", "const getEnvi()");
        }
    }

    @Override
    public void pickupItem(ItemType type) {
        //captures
        int getHgt_atPre = getHgt();
        int getCol_atPre = getCol();
        Environment getEnvi_atPre = getEnvi();
        Item getCurrentlyHeldItem_atPre = getCurrentlyHeldItem();
        int getNumberOfUsagesLeftForCurrentItem_atPre = getNumberOfUsagesLeftForCurrentItem();
        boolean isFacingRight_atPre = isFacingRight();
        
        //inv pre
        checkInvariant();
        
        super.pickupItem(type);

        //inv post
        checkInvariant();

        //\post getCol() == getCol()@pre
        if(!(getCol() == getCol_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "getCol() == getCol()@pre");
        }

        //\post getHgt() == getHgt()@pre
        if(!(getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "getHgt() == getHgt()@pre");
        }

        //\post isFacingRight() == isFacingRight()@pre
        if(!(isFacingRight() == isFacingRight_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "isFacingRight() == isFacingRight()@pre");
        }

        //\post getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type
        //      \and type == Key
        //      \impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 1 \and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
        if(!(Checker.implication(getCurrentlyHeldItem_atPre != null && getCurrentlyHeldItem_atPre.getNature() == type && type == ItemType.Key,
                                 getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre + 1 && getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "//\\post getCurrentlyHeldItem()@pre != null \\and getCurrentlyHeldItem().getNature()@pre == type \\and type == Key \\impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 1 \\and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre");
        }

        //\post \not (getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type)
        //      \and type == Key
        //      \impl getNumberOfUsagesLeftForCurrentItem() == 1 \and getCurrentlyHeldItem() == type
        if(!(Checker.implication(!(getCurrentlyHeldItem_atPre != null && getCurrentlyHeldItem_atPre.getNature() == type) && type == ItemType.Key,
                                 getNumberOfUsagesLeftForCurrentItem() == 1 && getCurrentlyHeldItem().getNature() == type))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "\\not (getCurrentlyHeldItem()@pre != null \\and getCurrentlyHeldItem().getNature()@pre == type) \\and type == Key \\impl getNumberOfUsagesLeftForCurrentItem() == 1 \\and getCurrentlyHeldItem() == type");
        }

        //\post getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type
        //      \and type == Gun
        //      \impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 1 \and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
        if(!(Checker.implication(getCurrentlyHeldItem_atPre != null && getCurrentlyHeldItem_atPre.getNature() == type && type == ItemType.Gun,
                                 getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre + 1 && getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "//\\post getCurrentlyHeldItem()@pre != null \\and getCurrentlyHeldItem().getNature()@pre == type \\and type == Gun \\impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 1 \\and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre");
        }

        //\post \not (getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type)
        //      \and type == Gun
        //      \impl getNumberOfUsagesLeftForCurrentItem() == 1 \and getCurrentlyHeldItem() == type
        if(!(Checker.implication(!(getCurrentlyHeldItem_atPre != null && getCurrentlyHeldItem_atPre.getNature() == type) && type == ItemType.Gun,
                                 getNumberOfUsagesLeftForCurrentItem() == 1 && getCurrentlyHeldItem().getNature() == type))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "\\not (getCurrentlyHeldItem()@pre != null \\and getCurrentlyHeldItem().getNature()@pre == type) \\and type == Gun \\impl getNumberOfUsagesLeftForCurrentItem() == 1 \\and getCurrentlyHeldItem() == type");
        }

        //\post getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type
        //      \and type == Flash
        //      \impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 1 \and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
        if(!(Checker.implication(getCurrentlyHeldItem_atPre != null && getCurrentlyHeldItem_atPre.getNature() == type && type == ItemType.Flash,
                                 getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre + 1 && getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "//\\post getCurrentlyHeldItem()@pre != null \\and getCurrentlyHeldItem().getNature()@pre == type \\and type == Flash \\impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 1 \\and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre");
        }

        //\post \not (getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type)
        //      \and type == Flash
        //      \impl getNumberOfUsagesLeftForCurrentItem() == 1 \and getCurrentlyHeldItem() == type
        if(!(Checker.implication(!(getCurrentlyHeldItem_atPre != null && getCurrentlyHeldItem_atPre.getNature() == type) && type == ItemType.Flash,
                                 getNumberOfUsagesLeftForCurrentItem() == 1 && getCurrentlyHeldItem().getNature() == type))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "\\not (getCurrentlyHeldItem()@pre != null \\and getCurrentlyHeldItem().getNature()@pre == type) \\and type == Flash \\impl getNumberOfUsagesLeftForCurrentItem() == 1 \\and getCurrentlyHeldItem() == type");
        }

        //\post getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type
        //      \and type == Sword
        //      \impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 3 \and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
        if(!(Checker.implication(getCurrentlyHeldItem_atPre != null && getCurrentlyHeldItem_atPre.getNature() == type && type == ItemType.Sword,
                                 getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre + 3 && getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "//\\post getCurrentlyHeldItem()@pre != null \\and getCurrentlyHeldItem().getNature()@pre == type \\and type == Sword \\impl getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre + 3 \\and getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre");
        }

        //\post \not (getCurrentlyHeldItem()@pre != null \and getCurrentlyHeldItem().getNature()@pre == type)
        //      \and type == Sword
        //      \impl getNumberOfUsagesLeftForCurrentItem() == 3 \and getCurrentlyHeldItem() == type
        if(!(Checker.implication(!(getCurrentlyHeldItem_atPre != null && getCurrentlyHeldItem_atPre.getNature() == type) && type == ItemType.Sword,
                                 getNumberOfUsagesLeftForCurrentItem() == 3 && getCurrentlyHeldItem().getNature() == type))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "pickupItem", "\\not (getCurrentlyHeldItem()@pre != null \\and getCurrentlyHeldItem().getNature()@pre == type) \\and type == Sword \\impl getNumberOfUsagesLeftForCurrentItem() == 3 \\and getCurrentlyHeldItem() == type");
        }

    }


    @Override
    public void useItem(){
        //captures
        Cell[][] getCellNature_atPre = new Cell[getEnvi().getWidth()][getEnvi().getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvi().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvi().getHeight(); v++){
                getCellNature_atPre[u][v] = getEnvi().getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvi().getCellContent(u, v)));
            }
        }
        int getHgt_atPre = getHgt();
        int getCol_atPre = getCol();
        Environment getEnvi_atPre = getEnvi();
        Item getCurrentlyHeldItem_atPre = getCurrentlyHeldItem();
        int getNumberOfUsagesLeftForCurrentItem_atPre = getNumberOfUsagesLeftForCurrentItem();
        boolean isFacingRight_atPre = isFacingRight();

        //inv pre 
        checkInvariant();

        super.useItem();

        //inv post
        checkInvariant();

        //\post getCurrentlyHeldItem()@pre != null \and getNumberOfUsagesLeftForCurrentItem()@pre == 1 
        //      \impl getCurrentlyHeldItem() == null \and getNumberOfUsagesLeftForCurrentItem() == 0
        if(!(Checker.implication(getCurrentlyHeldItem_atPre != null && getNumberOfUsagesLeftForCurrentItem_atPre == 1, getCurrentlyHeldItem() == null && getNumberOfUsagesLeftForCurrentItem() == 0))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "getCurrentlyHeldItem()@pre != null \\and getNumberOfUsagesLeftForCurrentItem()@pre == 1 \\impl getCurrentlyHeldItem() == null \\and getNumberOfUsagesLeftForCurrentItem() == 0");
        }
        
        //\post getCurrentlyHeldItem()@pre != null \and getNumberOfUsagesLeftForCurrentItem()@pre > 1 
        //      \impl getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre \and getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre - 1
        if(!(Checker.implication(getCurrentlyHeldItem_atPre != null && getNumberOfUsagesLeftForCurrentItem_atPre > 1, getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre && getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre - 1))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "getCurrentlyHeldItem()@pre != null \\and getNumberOfUsagesLeftForCurrentItem()@pre > 1 \\impl getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre \\and getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre - 1");
        }

        boolean canUseItem = (getCurrentlyHeldItem_atPre != null && getNumberOfUsagesLeftForCurrentItem_atPre >= 1); 

        //\post canUseItem \and getCurrentlyHeldItem()@pre == Key \and isFacingRight()@pre 
        //      \and getCol()@pre < getEnvi().getWidth() - 1 \and getEnvi().getCellNature(getCol() + 1, getHgt())@pre == DOR
        //      \impl getEnvi().getCellNature(getCol() + 1, getHgt()) == EMP
        if(getCol_atPre < getEnvi().getWidth() - 1){
            if(!(Checker.implication(canUseItem && getCurrentlyHeldItem_atPre.getNature() == ItemType.Key && isFacingRight_atPre && getCol_atPre < getEnvi().getWidth() - 1 && getCellNature_atPre[getCol_atPre + 1][getHgt_atPre] == Cell.DOR, getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre) == Cell.EMP))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "canUseItem \\and getCurrentlyHeldItem()@pre == Key \\and isFacingRight()@pre  \\and getCol()@pre < getEnvi().getWidth() - 1 \\and getEnvi().getCellNature(getCol() + 1, getHgt())@pre == DOR \\impl getEnvi().getCellNature(getCol() + 1, getHgt()) == EMP");
            }
        }

        //\post canUseItem \and getCurrentlyHeldItem()@pre == Key \and \not isFacingRight()@pre 
        //      \and getCol()@pre > 0 \and getEnvi().getCellNature(getCol() - 1, getHgt())@pre == DOR
        //      \impl getEnvi().getCellNature(getCol() - 1, getHgt()) == EMP
        if(getCol_atPre > 0){
            if(!(Checker.implication(canUseItem && getCurrentlyHeldItem_atPre.getNature() == ItemType.Key && isFacingRight_atPre && getCol_atPre > 0 && getCellNature_atPre[getCol_atPre - 1][getHgt_atPre] == Cell.DOR, getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre) == Cell.EMP))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "canUseItem \\and getCurrentlyHeldItem()@pre == Key \\and \\not isFacingRight()@pre  \\and getCol()@pre > 0 \\and getEnvi().getCellNature(getCol() - 1, getHgt())@pre == DOR \\impl getEnvi().getCellNature(getCol() - 1, getHgt()) == EMP");
            }
        }
        
        //\post \Forall Guard g \in getEngine().getGuards()
        //          canUseItem \and getCurrentlyHeldItem()@pre == Flash \impl g.getTimeLeftParalyzed() == 10
        for(Guard g : getEngine().getGuards()){
            if(!(Checker.implication(canUseItem && getCurrentlyHeldItem_atPre.getNature() == ItemType.Flash, g.getTimeLeftParalyzed() == 10))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "\\Forall Guard g \\in getEngine().getGuards() canUseItem \\and getCurrentlyHeldItem()@pre == Flash \\impl g.getTimeLeftParalyzed() == 10");
            }
        }


        //\post canUseItem \and getCurrentlyHeldItem()@pre == Sword \and getCol()@pre - 2 >= 0 \and \Exists Guard g \in getEnvi().getCellContent(getCol()@pre - 2, getHgt())@pre \impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()
        Guard g;
        if(getCol_atPre - 2 >= 0){
            g = Util.getGuard(getCellContent_atPre.get(getCol_atPre - 2).get(getHgt_atPre));
            if(g != null && !(Checker.implication(canUseItem && getCurrentlyHeldItem_atPre.getNature() == ItemType.Sword && getCol_atPre - 2 >= 0 && g != null, g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "canUseItem \\and getCurrentlyHeldItem()@pre == Sword \\and getCol()@pre - 2 >= 0 \\and \\Exists Guard g \\in getEnvi().getCellContent(getCol()@pre - 2, getHgt())@pre \\impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()");
            }
        }

        //\post canUseItem \and getCurrentlyHeldItem()@pre == Sword \and getCol()@pre - 1 >= 0 \and \Exists Guard g \in getEnvi().getCellContent(getCol()@pre - 1, getHgt())@pre \impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()
        if(getCol_atPre - 1 >= 0){        
            g = Util.getGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre));
            if(g != null && !(Checker.implication(canUseItem && getCurrentlyHeldItem_atPre.getNature() == ItemType.Sword && getCol_atPre - 1 >= 0 && g != null, g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "canUseItem \\and getCurrentlyHeldItem()@pre == Sword \\and getCol()@pre - 1 >= 0 \\and \\Exists Guard g \\in getEnvi().getCellContent(getCol()@pre - 1, getHgt())@pre \\impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()");
            }
        }
        //\post canUseItem \and getCurrentlyHeldItem()@pre == Sword \and getCol()@pre + 2 < getEnvi().getWidth() \and \Exists Guard g \in getEnvi().getCellContent(getCol()@pre + 2, getHgt())@pre \impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()
        if(getCol_atPre + 2 < getEnvi().getWidth()){        
            g = Util.getGuard(getCellContent_atPre.get(getCol_atPre + 2).get(getHgt_atPre));
            if(g != null && !(Checker.implication(canUseItem && getCurrentlyHeldItem_atPre.getNature() == ItemType.Sword && getCol_atPre + 2 < getEnvi().getWidth() && g != null, g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "canUseItem \\and getCurrentlyHeldItem()@pre == Sword \\and getCol()@pre + 2 < getEnvi().getWidth() \\and \\Exists Guard g \\in getEnvi().getCellContent(getCol()@pre + 2, getHgt())@pre \\impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()");
            }
        }
        
        //\post canUseItem \and getCurrentlyHeldItem()@pre == Sword \and getCol()@pre + 1 < getEnvi().getWidth() \and \Exists Guard g \in getEnvi().getCellContent(getCol()@pre + 1, getHgt())@pre \impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()
        if(getCol_atPre + 1 < getEnvi().getWidth()){
            g = Util.getGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre));
            if(g != null && !(Checker.implication(canUseItem && getCurrentlyHeldItem_atPre.getNature() == ItemType.Sword && getCol_atPre + 1 < getEnvi().getWidth() && g != null, g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "canUseItem \\and getCurrentlyHeldItem()@pre == Sword \\and getCol()@pre + 1 < getEnvi().getWidth() \\and \\Exists Guard g \\in getEnvi().getCellContent(getCol()@pre + 1, getHgt())@pre \\impl g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()");
            }
        }

        //\post \Forall i \in [0, getEnvi().getWidth()-1]
        //      canUseItem \and getCurrentlyHeldItem()@pre == Gun \and isFacingRight()@pre \and i > getCol()@pre
        //          \and \not ExistsObstacleBetween(getCol()@pre, i, getHgt()@pre)
        //          \and \Exists Guard g \in getEnvi().getCellContent(i, getHgt()@pre)@pre
        //          \impl \Exists g \in getEnvi().getCellContent(g.getInitCoords().getX(), g.getInitCoords().getY())
        //      canUseItem \and getCurrentlyHeldItem()@pre == Gun \and \not isFacingRight()@pre \and i < getCol()@pre
        //          \and \not ExistsObstacleBetween(i, getCol()@pre, getHgt()@pre)
        //          \and \Exists Guard g \in getEnvi().getCellContent(i, getHgt()@pre)@pre
        //          \impl \Exists g \in getEnvi().getCellContent(g.getInitCoords().getX(), g.getInitCoords().getY())
        for(int i=0;i<getEnvi().getWidth(); i++){
            Guard guard = Util.getGuard(getCellContent_atPre.get(i).get(getHgt_atPre));
            if(canUseItem && getCurrentlyHeldItem_atPre.getNature() == ItemType.Gun && isFacingRight_atPre && i > getCol_atPre &&
                !existsObstacleBetween(getCol_atPre, i, getHgt_atPre, getCellNature_atPre) &&  guard != null){
                    System.out.println("SHOOT RIGHT");
                    if(!(guard.getCol() == guard.getInitCoords().getX() && guard.getHgt() == guard.getInitCoords().getY())){
                        Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "Check kill guards when shooting to the right");
                    }
            }
            if(canUseItem && getCurrentlyHeldItem_atPre.getNature() == ItemType.Gun && !isFacingRight_atPre && i < getCol_atPre &&
                !existsObstacleBetween(i, getCol_atPre, getHgt_atPre, getCellNature_atPre)  && guard != null){
                    System.out.println("SHOOT LEFT");
                    if(!(guard.getCol() == guard.getInitCoords().getX() && guard.getHgt() == guard.getInitCoords().getY())){
                        Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "Check kill guards when shooting to the left");
                    }
            }
        }

        
        //\post getCol() == getCol()@pre
        if(!(getCol() == getCol_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "getCol() == getCol()@pre");
        }

        //\post getHgt() == getHgt()@pre
        if(!(getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "getHgt() == getHgt()@pre");
        }

        
        //\post isFacingRight() == isFacingRight()@pre
        if(!(isFacingRight() == isFacingRight_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "useItem", "isFacingRight() == isFacingRight()@pre");
        }
    }

    private boolean existsObstacleBetween(int x1, int x2, int y, Cell[][] getCellNature_atPre){
        //\def ExistsObstacleBetween(x1, x2, y) = \Exists Cell c \in \Union (\Forall i in [x, x2], getEnvi().getCellNature(i, y)) \with (c \in {MTL, PLT, DOR, NPL})
        
        for(int i=x1; i<x2; i++){
            Set<Cell> PLT_MTL_DOR_NPL = new HashSet<>();
            PLT_MTL_DOR_NPL.add(Cell.PLT);
            PLT_MTL_DOR_NPL.add(Cell.MTL);
            PLT_MTL_DOR_NPL.add(Cell.DOR);
            PLT_MTL_DOR_NPL.add(Cell.NPL);
            if(PLT_MTL_DOR_NPL.contains(getCellNature_atPre[i][y])){
                return true;
            }
        }
        return false;
    }

    @Override
    public void digLeft() {
        //captures
        Cell[][] getCellNature_atPre = new Cell[getEnvi().getWidth()][getEnvi().getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvi().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvi().getHeight(); v++){
                getCellNature_atPre[u][v] = getEnvi().getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvi().getCellContent(u, v)));
            }
        }
        int getHgt_atPre = getHgt();
        int getCol_atPre = getCol();
        Environment getEnvi_atPre = getEnvi();
        Item getCurrentlyHeldItem_atPre = getCurrentlyHeldItem();
        int getNumberOfUsagesLeftForCurrentItem_atPre = getNumberOfUsagesLeftForCurrentItem();

        //inv pre
        checkInvariant();

        super.digLeft();

        //inv post
        checkInvariant();

        //\post getCol() == 0 \impl noCellNatureChanged
        if(!(Checker.implication(getCol() == 0, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getCol() == 0 \\impl noCellNatureChanged");
        }

        //\post getHgt() == getHgt()@pre
        if(!(getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getHgt() == getHgt()@pre");
        }
        
        //\post getCol() == getCol()@pre
        if(!(getCol() == getCol_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getCol() == getCol()@pre");
        }
        
        //\post \not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD, DOR, NPL}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre
        //      \impl noCellNatureChanged
        Set<Cell> PLT_MTL_LAD_DOR_NPL = new HashSet<>();
        PLT_MTL_LAD_DOR_NPL.add(Cell.PLT);
        PLT_MTL_LAD_DOR_NPL.add(Cell.MTL);
        PLT_MTL_LAD_DOR_NPL.add(Cell.LAD);
        PLT_MTL_LAD_DOR_NPL.add(Cell.DOR);
        PLT_MTL_LAD_DOR_NPL.add(Cell.NPL);
        boolean cond1 = !(PLT_MTL_LAD_DOR_NPL.contains(getCellNature_atPre[getCol()][getHgt() - 1]));
        boolean cond2 = !Util.containsGuard(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
        if(!(Checker.implication(cond1 && cond2, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "\\not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \\in {MTL, PLT, LAD, DOR, NPL} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre \\impl noCellNatureChanged");
        }


        //\post \not getEnvi().getCellNature(getCol() - 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
        //      \impl noCellNatureChanged
        Set<Cell> EMP_HOL_LAD_HDR = new HashSet<>();
        EMP_HOL_LAD_HDR.add(Cell.EMP);
        EMP_HOL_LAD_HDR.add(Cell.HOL);
        EMP_HOL_LAD_HDR.add(Cell.LAD);
        EMP_HOL_LAD_HDR.add(Cell.HDR);
        if(getCol() != 0){
            if(!(Checker.implication(!EMP_HOL_LAD_HDR.contains(getCellNature_atPre[getCol() - 1][getHgt()]), noCellNatureChanged(getCellNature_atPre)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "\\not getEnvi().getCellNature(getCol() - 1, getHgt())@pre \\in {EMP, HOL, LAD, HDR} \\impl noCellNatureChanged");
            }
        }
        
        //\post getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre != PLT
        //      \impl noCellNatureChanged
        if(getCol() != 0){
            if(!(Checker.implication(getCellNature_atPre[getCol() - 1][getHgt() - 1] != Cell.PLT, noCellNatureChanged(getCellNature_atPre)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre != PLT \\impl noCellNatureChanged");
            }
        }

        //\post (getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD, DOR, NPL}
        //          \or \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre)
        //      \and getEnvi().getCellNature(getCol() - 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
        //      \and getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre == PLT
        //      \impl getEnvi().getCellNature(getCol() - 1, getHgt() - 1) == HOL
        //            \and \Forall i in [0, getEnvi().getWidth() - 1]
        //                      \Forall j in [0, getEnvi().getHeight() - 1]
        //                          i != getCol() - 1 || j != getHgt() - 1 \impl getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
        if(getCol() != 0){
            cond1 = PLT_MTL_LAD_DOR_NPL.contains(getCellNature_atPre[getCol()][getHgt() - 1]) || Util.containsGuard(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
            cond2 = EMP_HOL_LAD_HDR.contains(getCellNature_atPre[getCol() - 1][getHgt()]);
            boolean cond3 = getCellNature_atPre[getCol() - 1][getHgt() - 1] == Cell.PLT;
            boolean condImpl1 = getEnvi().getCellNature(getCol() - 1, getHgt() - 1) == Cell.HOL;
            boolean condImpl2 = true;
            for(int i=0; i<getEnvi().getWidth(); i++){
                for(int j=0; j<getEnvi().getHeight(); j++){
                    if(!(Checker.implication((i != getCol() - 1 || j != getHgt() - 1), getEnvi().getCellNature(i, j) == getCellNature_atPre[i][j]))){
                        condImpl2 = false;
                    }
                }
            }
            if(!Checker.implication(cond1 && cond2 && cond3, condImpl1 && condImpl2)){
                Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "Check if digLeft worked correctly");
            }
        }

        //\post \not isFacingRight()
        if(!(!isFacingRight())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "\\not isFacingRight()");
        }

        //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
        if(!(getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre");
        }

        //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
        if(!(getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre");
        }

        //\post const getEnvi()
        if(!(getEnvi().equals(getEnvi_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "const getEnvi()");
        }
        
    }

    @Override
    public void digRight() {
        //captures
        Cell[][] getCellNature_atPre = new Cell[getEnvi().getWidth()][getEnvi().getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvi().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvi().getHeight(); v++){
                getCellNature_atPre[u][v] = getEnvi().getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvi().getCellContent(u, v)));
            }
        }
        int getHgt_atPre = getHgt();
        int getCol_atPre = getCol();
        Environment getEnvi_atPre = getEnvi();
        Item getCurrentlyHeldItem_atPre = getCurrentlyHeldItem();
        int getNumberOfUsagesLeftForCurrentItem_atPre = getNumberOfUsagesLeftForCurrentItem();

        //inv pre
        checkInvariant();

        super.digRight();

        //inv post
        checkInvariant();

        //\post getCol() == getEnvi().getWidth() - 1 \impl noCellNatureChanged
        if(!(Checker.implication(getCol() == getEnvi().getWidth() - 1, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "getCol() == getEnvi().getWidth() - 1 \\impl noCellNatureChanged");
        }

     
        //\post getHgt() == getHgt()@pre
        if(!(getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "getHgt() == getHgt()@pre");
        }
        
        //\post getCol() == getCol()@pre
        if(!(getCol() == getCol_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "getCol() == getCol()@pre");
        }
        
        //\post \not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD, DOR, NPL}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre
        //      \impl noCellNatureChanged
        Set<Cell> PLT_MTL_LAD_DOR_NPL = new HashSet<>();
        PLT_MTL_LAD_DOR_NPL.add(Cell.PLT);
        PLT_MTL_LAD_DOR_NPL.add(Cell.MTL);
        PLT_MTL_LAD_DOR_NPL.add(Cell.LAD);
        PLT_MTL_LAD_DOR_NPL.add(Cell.DOR);
        PLT_MTL_LAD_DOR_NPL.add(Cell.NPL);
        boolean cond1 = !(PLT_MTL_LAD_DOR_NPL.contains(getCellNature_atPre[getCol()][getHgt() - 1]));
        boolean cond2 = !Util.containsGuard(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
        if(!(Checker.implication(cond1 && cond2, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "\\not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \\in {MTL, PLT, LAD, DOR, NPL} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre \\impl noCellNatureChanged");
        }
        
        //\post \not getEnvi().getCellNature(getCol() + 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
        //      \impl noCellNatureChanged
        Set<Cell> EMP_HOL_LAD_HDR = new HashSet<>();
        EMP_HOL_LAD_HDR.add(Cell.EMP);
        EMP_HOL_LAD_HDR.add(Cell.HOL);
        EMP_HOL_LAD_HDR.add(Cell.LAD);
        EMP_HOL_LAD_HDR.add(Cell.HDR);
        if(getCol() != getEnvi().getWidth() - 1){
            if(!(Checker.implication(!EMP_HOL_LAD_HDR.contains(getCellNature_atPre[getCol() + 1][getHgt()]), noCellNatureChanged(getCellNature_atPre)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "\\not getEnvi().getCellNature(getCol() + 1, getHgt())@pre \\in {EMP, HOL, LAD, HDR} \\impl noCellNatureChanged");
            }
        }

        //\post getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre != PLT
        //      \impl noCellNatureChanged
        if(getCol() != getEnvi().getWidth() - 1){
            if(!(Checker.implication(getCellNature_atPre[getCol() + 1][getHgt() - 1] != Cell.PLT, noCellNatureChanged(getCellNature_atPre)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre != PLT \\impl noCellNatureChanged");
            }
        }

        //\post (getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD, DOR, NPL}
        //          \or \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre)
        //      \and getEnvi().getCellNature(getCol() + 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
        //      \and getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre == PLT
        //      \impl getEnvi().getCellNature(getCol() + 1, getHgt() - 1) == HOL
        //            \and \Forall i in [0, getEnvi().getWidth() - 1]
        //                      \Forall j in [0, getEnvi().getHeight() - 1]
        //                          i != getCol() + 1 || j != getHgt() - 1 \impl getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
        if(getCol() != getEnvi().getWidth() - 1){
            cond1 = PLT_MTL_LAD_DOR_NPL.contains(getCellNature_atPre[getCol()][getHgt() - 1]) || Util.containsGuard(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
            cond2 = EMP_HOL_LAD_HDR.contains(getCellNature_atPre[getCol() + 1][getHgt()]);
            boolean cond3 = getCellNature_atPre[getCol() + 1][getHgt() - 1] == Cell.PLT;

            boolean condImpl1 = getEnvi().getCellNature(getCol() + 1, getHgt() - 1) == Cell.HOL;
            boolean condImpl2 = true;
            for(int i=0; i<getEnvi().getWidth(); i++){
                for(int j=0; j<getEnvi().getHeight(); j++){
                    if(!(Checker.implication((i != getCol() + 1 || j != getHgt() - 1), getEnvi().getCellNature(i, j) == getCellNature_atPre[i][j]))){
                        condImpl2 = false;
                    }
                }
            }
            if(!Checker.implication(cond1 && cond2 && cond3, condImpl1 && condImpl2)){
                Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "Check if digRight worked correctly");
            }
        }

        //\post isFacingRight()
        if(!(isFacingRight())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "isFacingRight()");
        }

        //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
        if(!(getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre");
        }

        //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
        if(!(getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre");
        }
        

        //\post const getEnvi()
        if(!(getEnvi().equals(getEnvi_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digRight", "const getEnvi()");
        }
    }

    private boolean noCellNatureChanged(Cell[][] getCellNature_atPre){
        //\def noCellNatureChanged = \Forall i in [0, getEnvi().getWidth() - 1]
        //                              \Forall j in [0, getEnvi().getHeight() - 1]
        //                                  getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
        for(int i=0; i<getEnvi().getWidth(); i++){
            for(int j=0; j<getEnvi().getHeight(); j++){
                if(getCellNature_atPre[i][j] != getEnvi().getCellNature(i, j)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isFalling(int getCol_atPre, int getHgt_atPre, Cell[][] getCellNature_atPre,  List<List<Set<Entity>>> getCellContent_atPre){
        //\def falling = \not getEnvi().getCellNature(getCol(), getHgt())@pre \in {LAD, HDR}
        //               \and getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {EMP, HDR, HOL, NGU}
        //               \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre

        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);


        Set<Cell> EMP_HDR_HOL_NGU = new HashSet<>();
        EMP_HDR_HOL_NGU.add(Cell.EMP);
        EMP_HDR_HOL_NGU.add(Cell.HDR);
        EMP_HDR_HOL_NGU.add(Cell.HOL);
        EMP_HDR_HOL_NGU.add(Cell.NGU);

        

        boolean cond1 = !LAD_HDR.contains(getCellNature_atPre[getCol_atPre][getHgt_atPre]);
        boolean cond2 = EMP_HDR_HOL_NGU.contains(getCellNature_atPre[getCol_atPre][getHgt_atPre - 1]);
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        return cond1 && cond2 && cond3;

    }

    @Override
    public void goLeft() {
        //captures
        Cell[][] getCellNature_atPre = new Cell[getEnvi().getWidth()][getEnvi().getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvi().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvi().getHeight(); v++){
                getCellNature_atPre[u][v] = getEnvi().getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvi().getCellContent(u, v)));
            }
        }
        int getHgt_atPre = getHgt();
        int getCol_atPre = getCol();
        Environment getEnvi_atPre = getEnvi();
        Item getCurrentlyHeldItem_atPre = getCurrentlyHeldItem();
        int getNumberOfUsagesLeftForCurrentItem_atPre = getNumberOfUsagesLeftForCurrentItem();


        //inv pre
        checkInvariant();
        
        super.goLeft();

        //inv post
        checkInvariant();

        //\post getHgt() == getHgt()@pre
        if(!(getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "getHgt() == getHgt()@pre");
        }


        //\post getCol()@pre == 0 \impl getCol() == getCol()@pre
        if(!(Checker.implication(getCol_atPre == 0, getCol() == getCol_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "getCol()@pre == 0 \\impl getCol() == getCol()@pre");
        }


        //\post (getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR, NPL}) \impl getCol() == getCol()@pre
        Set<Cell> MTL_PLT_DOR_NPL = new HashSet<>();
        MTL_PLT_DOR_NPL.add(Cell.MTL);
        MTL_PLT_DOR_NPL.add(Cell.PLT);
        MTL_PLT_DOR_NPL.add(Cell.DOR);
        MTL_PLT_DOR_NPL.add(Cell.NPL);
        if(!(getCol_atPre == 0)){
            if(!(Checker.implication(MTL_PLT_DOR_NPL.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre)), getCol() == getCol_atPre))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "(getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \\in {MTL, PLT, DOR, NPL}) \\impl getCol() == getCol()@pre");
            }
        }


        //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NPL}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
        //      \impl getCol() == getCol()@pre()
        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);
        Set<Cell> PLT_MTL_LAD_DOR = new HashSet<>();
        PLT_MTL_LAD_DOR.add(Cell.PLT);
        PLT_MTL_LAD_DOR.add(Cell.MTL);
        PLT_MTL_LAD_DOR.add(Cell.LAD);
        PLT_MTL_LAD_DOR.add(Cell.DOR);
        PLT_MTL_LAD_DOR.add(Cell.NPL);

        boolean cond1 = !(LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
        boolean cond2 = !(PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "\\not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \\in {LAD, HDR}) \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {PLT, MTL, LAD, DOR, NPL} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getCol() == getCol()@pre()");
        }


        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \impl getCol() == getCol()@pre
        if(!(getCol_atPre == 0)){
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre)), getCol_atPre == getCol()))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \\impl getCol() == getCol()@pre");
            }
        }


        //\post getCol()@pre != 0
        //      \and \not getEnvi().getCellNature()(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR, NPL}
        //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
        //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NPL}
        //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre)
        //      \impl getCol() == getCol()@pre - 1
        if(!(getCol_atPre == 0)){
            boolean condAND1 = getCol_atPre != 0;
            boolean condAND2 = !MTL_PLT_DOR_NPL.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre));
            boolean condOR1 = (LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
            boolean condOR2 = (PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
            boolean condOR3 = Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            boolean condAND3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre));
            if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre - 1)){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "Check if goLeft worked correctly with all conditions");
            }
        }

        //\post \not isFacingRight()
        if(!(!isFacingRight())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "\\not isFacingRight()");
        }

        //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
        if(!(getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre");
        }

        //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
        if(!(getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre");
        }
        
        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "const getEnvi()");
        }
        
        
    }

    @Override
    public void goRight() {
        //captures
        Cell[][] getCellNature_atPre = new Cell[getEnvi().getWidth()][getEnvi().getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvi().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvi().getHeight(); v++){
                getCellNature_atPre[u][v] = getEnvi().getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvi().getCellContent(u, v)));
            }
        }
        int getHgt_atPre = getHgt();
        int getCol_atPre = getCol();
        Environment getEnvi_atPre = getEnvi();
        Item getCurrentlyHeldItem_atPre = getCurrentlyHeldItem();
        int getNumberOfUsagesLeftForCurrentItem_atPre = getNumberOfUsagesLeftForCurrentItem();



        //inv pre
        checkInvariant();
        
        super.goRight();

        //inv post
        checkInvariant();

        //\post getHgt() == getHgt()@pre
        if(!(getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "getHgt() == getHgt()@pre");
        }

        //\post getCol()@pre == getEnvi().getWidth() - 1 \impl getCol() == getCol()@pre
        if(!(Checker.implication(getCol_atPre == getEnvi().getWidth() - 1, getCol() == getCol_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "getCol()@pre == getEnvi().getWidth() - 1 \\impl getCol() == getCol()@pre");
        }
        
        //\post (getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR, NPL}) \impl getCol() == getCol()@pre
        Set<Cell> MTL_PLT_DOR_NPL = new HashSet<>();
        MTL_PLT_DOR_NPL.add(Cell.MTL);
        MTL_PLT_DOR_NPL.add(Cell.PLT);
        MTL_PLT_DOR_NPL.add(Cell.DOR);
        MTL_PLT_DOR_NPL.add(Cell.NPL);
        if(getCol_atPre != getEnvi().getWidth() - 1){
            if(!(Checker.implication(MTL_PLT_DOR_NPL.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre)), getCol() == getCol_atPre))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "(getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \\in {MTL, PLT, DOR, NPL}) \\impl getCol() == getCol()@pre");
            }
        }
        
        //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NPL}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
        //      \impl getCol() == getCol()@pre()
        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);
        Set<Cell> PLT_MTL_LAD_DOR = new HashSet<>();
        PLT_MTL_LAD_DOR.add(Cell.PLT);
        PLT_MTL_LAD_DOR.add(Cell.MTL);
        PLT_MTL_LAD_DOR.add(Cell.LAD);
        PLT_MTL_LAD_DOR.add(Cell.DOR);
        PLT_MTL_LAD_DOR.add(Cell.NPL);

        boolean cond1 = !(LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
        boolean cond2 = !(PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "\\not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \\in {LAD, HDR}) \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {PLT, MTL, LAD, DOR, NPL} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getCol() == getCol()@pre()");
        }
        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \impl getCol() == getCol()@pre
        
        if(getCol_atPre != getEnvi().getWidth() - 1){        
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre)), getCol_atPre == getCol()))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \\impl getCol() == getCol()@pre");
            }
        }
        
        
        //\post getCol()@pre != getEnvi().getWidth() - 1
        //      \and \not getEnvi().getCellNature()(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR, NPL}
        //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
        //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NPL}
        //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre)
        //      \impl getCol() == getCol()@pre + 1
        if(getCol_atPre != getEnvi().getWidth() - 1){        
            boolean condAND1 = getCol_atPre != getEnvi().getWidth() - 1;
            boolean condAND2 = !MTL_PLT_DOR_NPL.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre));
            boolean condOR1 = (LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
            boolean condOR2 = (PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
            boolean condOR3 = Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            boolean condAND3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre));
            if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre + 1)){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "Check if goRight worked correctly with all conditions");
            }
        }

        //\post isFacingRight()
        if(!(isFacingRight())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "isFacingRight()");
        }

        //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
        if(!(getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre");
        }

        //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
        if(!(getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre");
        }

        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "const getEnvi()");
        }

    }

    @Override
    public void goUp() {
        //captures
        Cell[][] getCellNature_atPre = new Cell[getEnvi().getWidth()][getEnvi().getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvi().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvi().getHeight(); v++){
                getCellNature_atPre[u][v] = getEnvi().getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvi().getCellContent(u, v)));
            }
        }
        int getHgt_atPre = getHgt();
        int getCol_atPre = getCol();
        Environment getEnvi_atPre = getEnvi();
        Item getCurrentlyHeldItem_atPre = getCurrentlyHeldItem();
        int getNumberOfUsagesLeftForCurrentItem_atPre = getNumberOfUsagesLeftForCurrentItem();
        boolean isFacingRight_atPre = isFacingRight();


        //inv pre
        checkInvariant();
        
        super.goUp();

        //inv post
        checkInvariant();

        //\post getCol() == getCol()@pre
        if(!(getCol() == getCol_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "getCol() == getCol()@pre");
        }

        //\post getHgt()@pre == getEnvi().getHeight() - 1 \impl getHgt() == getHgt()@pre
        if(!Checker.implication(getHgt_atPre == getEnvi().getHeight() - 1, getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "getHgt()@pre == getEnvi().getHeight() - 1 \\impl getHgt() == getHgt()@pre");
        }

        //\post (getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \in {MTL, PLT, DOR, NPL} \impl getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT_DOR_NPL = new HashSet<>();
        MTL_PLT_DOR_NPL.add(Cell.MTL);
        MTL_PLT_DOR_NPL.add(Cell.PLT);
        MTL_PLT_DOR_NPL.add(Cell.DOR);
        MTL_PLT_DOR_NPL.add(Cell.NPL);
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            if(!(Checker.implication(MTL_PLT_DOR_NPL.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre + 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "(getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \\in {MTL, PLT, DOR, NPL} \\impl getHgt() == getHgt()@pre");
            }
        }
        //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \impl getHgt() == getHgt()@pre
        if(!Checker.implication(getEnvi().getCellNature(getCol_atPre, getHgt_atPre) != Cell.LAD, getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \\impl getHgt() == getHgt()@pre");
        }

        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \impl getHgt() == getHgt()@pre
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            if(!Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1)), getHgt() == getHgt_atPre)){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \\impl getHgt() == getHgt()@pre");
            }
        }

        //\post getHgt()@pre != getEnvi().getHeight() - 1 
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \in {MTL, PLT, DOR, NPL}
        //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1)
        //      \impl getHgt() == getHgt()@pre + 1
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            boolean cond1 = getHgt_atPre != getEnvi().getHeight() - 1;
            boolean cond2 = !MTL_PLT_DOR_NPL.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre + 1));
            boolean cond3 = getEnvi().getCellNature(getCol_atPre, getHgt_atPre) == Cell.LAD;
            boolean cond4 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1));
            if(!Checker.implication(cond1 && cond2 && cond3 && cond4, getHgt() == getHgt_atPre + 1)){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "getHgt()@pre != getEnvi().getHeight() - 1  \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \\in {MTL, PLT, DOR, NPL} \\and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \\impl getHgt() == getHgt()@pre + 1");
            }
        }

        //\post isFacingRight() == isFacingRight()@pre
        if(!(isFacingRight() == isFacingRight_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "isFacingRight() == isFacingRight()@pre");
        }

        //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
        if(!(getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre");
        }

        //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
        if(!(getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre");
        }
        
        
        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "const getEnvi()");
        }
        
    }

    @Override
    public void goDown() {
        //captures
        Cell[][] getCellNature_atPre = new Cell[getEnvi().getWidth()][getEnvi().getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvi().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvi().getHeight(); v++){
                getCellNature_atPre[u][v] = getEnvi().getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvi().getCellContent(u, v)));
            }
        }
        int getHgt_atPre = getHgt();
        int getCol_atPre = getCol();
        Environment getEnvi_atPre = getEnvi();
        Item getCurrentlyHeldItem_atPre = getCurrentlyHeldItem();
        int getNumberOfUsagesLeftForCurrentItem_atPre = getNumberOfUsagesLeftForCurrentItem();
        boolean isFacingRight_atPre = isFacingRight();

        //inv pre
        checkInvariant();
        
        super.goDown();

        //inv post
        checkInvariant();

        //\post getCol() == getCol()@pre
        if(!(getCol() == getCol_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "getCol() == getCol()@pre");
        }
        
        //\post getHgt()@pre == 0 \impl getHgt() == getHgt()@pre
        if(!(Checker.implication(getHgt_atPre == 0, getHgt() == getHgt_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "getHgt()@pre == 0 \\impl getHgt() == getHgt()@pre");
        }
        
        //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR, NPL} \impl getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT_DOR_NPL = new HashSet<>();
        MTL_PLT_DOR_NPL.add(Cell.MTL);
        MTL_PLT_DOR_NPL.add(Cell.PLT);
        MTL_PLT_DOR_NPL.add(Cell.DOR);
        MTL_PLT_DOR_NPL.add(Cell.NPL);
        if(getHgt_atPre != 0){
            if(!(Checker.implication(MTL_PLT_DOR_NPL.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {MTL, PLT, DOR, NPL} \\impl getHgt() == getHgt()@pre");
            }
        }
        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \impl getHgt() == getHgt()@pre
        if(getHgt_atPre != 0){
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getHgt() == getHgt()@pre");
            }
        }
        
        //\post getHgt()@pre != 0
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR, NPL}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) 
        //      \impl getHgt() == getHgt()@pre - 1
        if(getHgt_atPre != 0){
            boolean cond1 = getHgt_atPre != 0;
            boolean cond2 = !MTL_PLT_DOR_NPL.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1));
            boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            if(!(Checker.implication(cond1 && cond2 && cond3, getHgt() == getHgt_atPre - 1))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "getHgt()@pre != 0 \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {MTL, PLT, DOR, NPL} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)  \\impl getHgt() == getHgt()@pre - 1");
            }
        }

        //\post isFacingRight() == isFacingRight()@pre
        if(!(isFacingRight() == isFacingRight_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "isFacingRight() == isFacingRight()@pre");
        }

        //\post getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre
        if(!(getCurrentlyHeldItem() == getCurrentlyHeldItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "getCurrentlyHeldItem() == getCurrentlyHeldItem()@pre");
        }

        //\post getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre
        if(!(getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "getNumberOfUsagesLeftForCurrentItem() == getNumberOfUsagesLeftForCurrentItem()@pre");
        }

        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "const getEnvi()");
        }
        
    }
    
    
    private Player duplicatePlayer(Player d){
        return d.getEngine().copy().getPlayer();
    }

    private boolean isEqual(PlayerContract pc){
        //TODO define this method
        return this.getCol() == pc.getCol() && this.getHgt() == pc.getHgt();
    }
    
}