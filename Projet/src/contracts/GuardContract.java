package contracts;

import java.util.HashSet;
import java.util.Set;

import data.Cell;
import data.Coord;
import data.GuardType;
import decorators.GuardDecorator;
import services.Character;
import services.Engine;
import services.Environment;
import services.Guard;
import utils.Util;

public class GuardContract extends GuardDecorator {
    public GuardContract(Guard delegate) {
        super(delegate);
    }

    public void checkInvariant() {

    }

    @Override
    public void init(Engine e, int x, int y, Character target) {
        // \pre e != null
        if (!(e != null)) {
            Contractor.defaultContractor().preconditionError("GuardContract", "init", "e != null");
        }

        // \pre x >= 0
        if (!(x >= 0)) {
            Contractor.defaultContractor().preconditionError("GuardContract", "init", "x >= 0");
        }

        // \pre x < e.getEnvironment().getWidth()
        if (!(x < e.getEnvironment().getWidth())) {
            Contractor.defaultContractor().preconditionError("GuardContract", "init",
                    "x < e.getEnvironment().getWidth()");
        }

        // \pre y >= 0
        if (!(y >= 0)) {
            Contractor.defaultContractor().preconditionError("GuardContract", "init", "y >= 0");
        }

        // \pre y < e.getEnvironment().getHeight()
        if (!(y < e.getEnvironment().getHeight())) {
            Contractor.defaultContractor().preconditionError("GuardContract", "init",
                    "y < e.getEnvironment().getHeight()");
        }

        // \pre \not e.getEnvironment().getCellNature(x, y) \in {MTL, PLT}
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if (!(!MTL_PLT.contains(e.getEnvironment().getCellNature(x, y)))) {
            Contractor.defaultContractor().preconditionError("GuardContract", "init",
                    "\\not e.getEnvironment().getCellNature(x, y) \\in {MTL, PLT}");
        }

        super.init(e, x, y, target);

        // inv post
        checkInvariant();

        // \post getId() == getIdCounter() - 1
        if (!(getId() == getIdCounter() - 1)) {
            Contractor.defaultContractor().postconditionError("GuardContract", "init", "getId() == getIdCounter() - 1");
        }

        // \post getEngine() == e
        if (!(getEngine() == e)) {
            Contractor.defaultContractor().postconditionError("GuardContract", "init", "getEngine() == e");
        }

        // \post getNature() == NORMAL
        if (!(getNature() == GuardType.NORMAL)) {
            Contractor.defaultContractor().postconditionError("GuardContract", "init", "getNature() == NORMAL");
        }

        // \post getInitCoords().getX() == x
        if (!(getInitCoords().getX() == x)) {
            Contractor.defaultContractor().postconditionError("GuardContract", "init", "getInitCoords().getX() == x");
        }

        // \post getInitCoords().getY() == y
        if (!(getInitCoords().getY() == y)) {
            Contractor.defaultContractor().postconditionError("GuardContract", "init", "getInitCoords().getY() == y");
        }

        // \post getTarget() == target
        if (!(getTarget() == target)) {
            Contractor.defaultContractor().postconditionError("GuardContract", "init", "getTarget() == target");
        }

        // \post !isCarryingTreasure()
        if (!(!isCarryingTreasure())) {
            Contractor.defaultContractor().postconditionError("GuardContract", "init", "!isCarryingTreasure()");
        }

        // \post getTimeInHole() == 0
        if (!(getTimeInHole() == 0)) {
            Contractor.defaultContractor().postconditionError("GuardContract", "init", "getTimeInHole() == 0");
        }

    }

    @Override
    public void climbLeft() {
        // \pre getEngine().getEnvironment().getCellNature(getCol(), getHgt()) ==
        // Cell.HOL
        if (!(getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.HOL)) {
            Contractor.defaultContractor().preconditionError("GuardContract", "climbLeft",
                    "getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.HOL");
        }

        // captures
        int getCol_atPre = getCol();
        int getHgt_atPre = getHgt();
        Engine getEngine_atPre = getEngine();
        Environment getEnvi_atPre = getEnvi();
        int getId_atPre = getId();
        int getIdCounter_atPre = getIdCounter();
        Coord getInitCoords_atPre = getInitCoords();
        GuardType getNature_atPre = getNature();
        Character getTarget_atPre = getTarget();
        int getTimeInHole_atPre = getTimeInHole();
        boolean isCarryingTreasure_atPre = isCarryingTreasure();
        

        //inv pre 
        checkInvariant();
        
        super.climbLeft();

        //inv post
        checkInvariant();
        
        //\post getCol()@pre == 0 \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
        if(!(Checker.implication(getCol_atPre == 0, getCol_atPre == getCol() && getHgt_atPre == getHgt()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "getCol()@pre == 0 \\impl getCol() == getCol()@pre && getHgt() == getHgt()@pre");
        }

        //\post getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1) \in {MTL, PLT} \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if(!(Checker.implication(MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre + 1)), getCol_atPre == getCol() && getHgt_atPre == getHgt()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", " getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1) \\in {MTL, PLT} \\impl getCol() == getCol()@pre && getHgt() == getHgt()@pre");
        }

        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1) \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
        if(!(Checker.implication(Util.containsGuard(getEnvi().getCellContent(getCol_atPre - 1, getHgt_atPre + 1)), getCol_atPre == getCol() && getHgt_atPre == getHgt()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1) \\impl getCol() == getCol()@pre && getHgt() == getHgt()@pre");
        }

        //\post getCol()@pre != 0 && 
        //      \not getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1) \in {MTL, PLT} &&
        //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1) &&
        //      \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)
        //      \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre + 1
        boolean cond1 = getCol_atPre != 0;
        boolean cond2 = !MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre - 1, getCol_atPre + 1));
        boolean cond3 = !Util.containsGuard(getEnvi().getCellContent(getCol_atPre - 1, getHgt_atPre + 1));
        boolean cond4 = Util.containsPlayer(getEnvi().getCellContent(getCol_atPre, getHgt_atPre + 1));
        if(!(Checker.implication(cond1 && cond2 && cond3 && cond4, getCol() == getCol_atPre && getHgt() == getHgt_atPre + 1))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "Climbing on top of the HOL because there is a player juste above the guard");
        }

        //\post getCol()@pre != 0 && 
        //      \not getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1) \in {MTL, PLT} &&
        //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1) &&
        //      \not \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)
        //      \impl getCol() == getCol()@pre - 1 && getHgt() == getHgt()@pre + 1
        cond4 = !Util.containsPlayer(getEnvi().getCellContent(getCol_atPre, getHgt_atPre + 1));
        if(!(Checker.implication(cond1 && cond2 && cond3 && cond4, getCol() == getCol_atPre - 1 && getHgt() == getHgt_atPre + 1))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "Climbing on top left of the HOL");
        }

        //\post isCarryingTreasure() == isCarryingTreasure()@pre
        if(!(isCarryingTreasure() == isCarryingTreasure_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "isCarryingTreasure() == isCarryingTreasure()@pre");

        }

        //\post getTimeInHole() == getTimeInHole()@pre
        if(!(getTimeInHole() == getTimeInHole_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "getTimeInHole() == getTimeInHole()@pre");

        }
        //\post getIdCounter() == getIdCounter()@pre
        if(!(getIdCounter() == getIdCounter_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "getIdCounter() == getIdCounter()@pre");

        }

        //\post const getEngine()
        if(!(getEngine_atPre == getEngine())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "const getEngine()");
        }
        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "const getEnvi()");
        }
        //\post const getId()
        if(!(getId_atPre == getId())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "const getId()");
        }
        //\post const getInitCoords()
        if(!(getInitCoords_atPre == getInitCoords())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "const getInitCoords()");
        }
        //\post const getNature()
        if(!(getNature_atPre == getNature())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "const getNature()");
        }
        //\post const getTarget()
        if(!(getTarget_atPre == getTarget())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "const getTarget()");
        }

    }   

    @Override
    public void climbRight() {
        //\pre getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.HOL
        if(!(getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.HOL)){
            Contractor.defaultContractor().preconditionError("GuardContract", "climbRight", "getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.HOL");
        }
        
        //captures
        int getCol_atPre = getCol();
        int getHgt_atPre = getHgt();
        Engine getEngine_atPre = getEngine();
        Environment getEnvi_atPre = getEnvi();
        int getId_atPre = getId();
        int getIdCounter_atPre = getIdCounter();
        Coord getInitCoords_atPre = getInitCoords();
        GuardType getNature_atPre = getNature();
        Character getTarget_atPre = getTarget();
        int getTimeInHole_atPre = getTimeInHole();
        boolean isCarryingTreasure_atPre = isCarryingTreasure();
        

        //inv pre 
        checkInvariant();
        
        super.climbRight();

        //inv post
        checkInvariant();

        //\post getCol()@pre == getEnvi().getWidth() - 1 \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
        if(!(Checker.implication(getCol_atPre == getEnvi().getWidth() - 1, getCol_atPre == getCol() && getHgt_atPre == getHgt()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "getCol()@pre == getEnvi().getWidth() - 1 \\impl getCol() == getCol()@pre && getHgt() == getHgt()@pre");
        }

        //\post getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1) \in {MTL, PLT} \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if(!(Checker.implication(MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre + 1)), getCol_atPre == getCol() && getHgt_atPre == getHgt()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", " getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1) \\in {MTL, PLT} \\impl getCol() == getCol()@pre && getHgt() == getHgt()@pre");
        }

        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1) \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
        if(!(Checker.implication(Util.containsGuard(getEnvi().getCellContent(getCol_atPre + 1, getHgt_atPre + 1)), getCol_atPre == getCol() && getHgt_atPre == getHgt()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1) \\impl getCol() == getCol()@pre && getHgt() == getHgt()@pre");
        }

        //\post getCol()@pre != getEnvi().getWidth() - 1 && 
        //      \not getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1) \in {MTL, PLT} &&
        //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1) &&
        //      \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)
        //      \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre + 1
        boolean cond1 = getCol_atPre != getEnvi().getWidth() - 1;
        boolean cond2 = !MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre + 1, getCol_atPre + 1));
        boolean cond3 = !Util.containsGuard(getEnvi().getCellContent(getCol_atPre + 1, getHgt_atPre + 1));
        boolean cond4 = Util.containsPlayer(getEnvi().getCellContent(getCol_atPre, getHgt_atPre + 1));
        if(!(Checker.implication(cond1 && cond2 && cond3 && cond4, getCol() == getCol_atPre && getHgt() == getHgt_atPre + 1))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "Climbing on top of the HOL because there is a player juste above the guard");
        }
        
        //\post getCol()@pre != getEnvi().getWidth() - 1 && 
        //      \not getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1) \in {MTL, PLT} &&
        //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1) &&
        //      \not \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)
        //      \impl getCol() == getCol()@pre + 1 && getHgt() == getHgt()@pre + 1
        cond4 = !Util.containsPlayer(getEnvi().getCellContent(getCol_atPre, getHgt_atPre + 1));
        if(!(Checker.implication(cond1 && cond2 && cond3 && cond4, getCol() == getCol_atPre + 1 && getHgt() == getHgt_atPre + 1))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "Climbing on top right of the HOL");
        }
        //\post isCarryingTreasure() == isCarryingTreasure()@pre
        if(!(isCarryingTreasure() == isCarryingTreasure_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "isCarryingTreasure() == isCarryingTreasure()@pre");

        }

        //\post getTimeInHole() == getTimeInHole()@pre
        if(!(getTimeInHole() == getTimeInHole_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "getTimeInHole() == getTimeInHole()@pre");

        }
        //\post getIdCounter() == getIdCounter()@pre
        if(!(getIdCounter() == getIdCounter_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "getIdCounter() == getIdCounter()@pre");

        }

        //\post const getEngine()
        if(!(getEngine_atPre == getEngine())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "const getEngine()");
        }
        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "const getEnvi()");
        }
        //\post const getId()
        if(!(getId_atPre == getId())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "const getId()");
        }
        //\post const getInitCoords()
        if(!(getInitCoords_atPre == getInitCoords())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "const getInitCoords()");
        }
        //\post const getNature()
        if(!(getNature_atPre == getNature())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "const getNature()");
        }
        //\post const getTarget()
        if(!(getTarget_atPre == getTarget())){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "const getTarget()");
        }
    }

    @Override
    public void moveToInitCoords() {
        //\pre getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.PLT
        if(!(getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.PLT)){
            Contractor.defaultContractor().preconditionError("GuardContract", "moveToInitCoords", "getEngine().getEnvironment().getCellNature(getCol(), getHgt()) == Cell.PLT");
        }

        //captures
        Engine getEngine_atPre = getEngine();
        Environment getEnvi_atPre = getEnvi();
        int getId_atPre = getId();
        int getIdCounter_atPre = getIdCounter();
        Coord getInitCoords_atPre = getInitCoords();
        GuardType getNature_atPre = getNature();
        Character getTarget_atPre = getTarget();
        boolean isCarryingTreasure_atPre = isCarryingTreasure();
        
        
        //inv pre 
        checkInvariant();
        
        super.moveToInitCoords();

        //inv post
        checkInvariant();
        
        //\post isCarryingTreasure() == isCarryingTreasure()@pre
        if(!(isCarryingTreasure() == isCarryingTreasure_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "isCarryingTreasure() == isCarryingTreasure()@pre");
        }
        //\post getTimeInHole() == 0
        if(!(getTimeInHole() == 0)){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "getTimeInHole() == 0");
        }
        //\post getCol() == getInitCoords().getX()
        if(!(getCol() == getInitCoords().getX())){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "getCol() == getInitCoords().getX()");
        }
        //\post getHgt() == getInitCoords().getY()
        if(!(getHgt() == getInitCoords().getY())){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "getHgt() == getInitCoords().getY()");
        }
        //\post getIdCounter() == getIdCounter()@pre
        if(!(getIdCounter() == getIdCounter_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "getIdCounter() == getIdCounter()@pre");
        }

        //\post const getEngine()
        if(!(getEngine_atPre == getEngine())){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "const getEngine()");
        }
        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "const getEnvi()");
        }
        //\post const getId()
        if(!(getId_atPre == getId())){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "const getId()");
        }
        //\post const getInitCoords()
        if(!(getInitCoords_atPre == getInitCoords())){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "const getInitCoords()");
        }
        //\post const getNature()
        if(!(getNature_atPre == getNature())){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "const getNature()");
        }
        //\post const getTarget()
        if(!(getTarget_atPre == getTarget())){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "const getTarget()");
        }

    }

    @Override
    public void step() {
        //inv pre 
        checkInvariant();
        
        super.step();

        //inv post
        checkInvariant();
        
    }
}