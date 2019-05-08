package contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Cell;
import data.Command;
import data.Coord;
import data.Entity;
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
        Set<Cell> HOL_HDR = new HashSet<>();
        HOL_HDR.add(Cell.HOL);
        HOL_HDR.add(Cell.HDR);

        Set<Cell> PLT_MLT_LAD_DOR_NGU_TRP = new HashSet<>();
        PLT_MLT_LAD_DOR_NGU_TRP.add(Cell.PLT);
        PLT_MLT_LAD_DOR_NGU_TRP.add(Cell.PLT);
        PLT_MLT_LAD_DOR_NGU_TRP.add(Cell.LAD);
        PLT_MLT_LAD_DOR_NGU_TRP.add(Cell.DOR);
        PLT_MLT_LAD_DOR_NGU_TRP.add(Cell.NGU);
        PLT_MLT_LAD_DOR_NGU_TRP.add(Cell.TRP);

        Set<Cell> libre = new HashSet<>();
        libre.add(Cell.EMP);
        libre.add(Cell.HDR);
        libre.add(Cell.LAD);
        libre.add(Cell.NPL);
        

        if(!(Checker.implication(getEnvi().getCellNature(getCol(), getHgt()) == Cell.LAD && getHgt() < getTarget().getHgt(), getBehaviour() == Command.MOVEU ))){
            Contractor.defaultContractor().invariantError("GuardContract", "Go up on ladder");
        }


        if(!(Checker.implication(getEnvi().getCellNature(getCol(), getHgt()) == Cell.LAD && getHgt() > getTarget().getHgt(), getBehaviour() == Command.MOVED ))){
            Contractor.defaultContractor().invariantError("GuardContract", "Go up on ladder");
        }

        if(!(Checker.implication(getEnvi().getCellNature(getCol(), getHgt()) == Cell.HOL && getCol() == getTarget().getCol() &&
        getHgt() + 1 == getTarget().getHgt() && libre.contains(getEnvi().getCellNature(getCol()+1, getHgt()+1)), getBehaviour() == Command.MOVER ))){

            Contractor.defaultContractor().invariantError("GuardContract", "Climbing on player when he is above (via MOVER)");
        }
        if(!(Checker.implication(getEnvi().getCellNature(getCol(), getHgt()) == Cell.HOL && getCol() == getTarget().getCol() &&
        getHgt() + 1 == getTarget().getHgt() && !libre.contains(getEnvi().getCellNature(getCol()+1, getHgt()+1)), getBehaviour() == Command.MOVEL ))){

            Contractor.defaultContractor().invariantError("GuardContract", "Climbing on player when he is above (via MOVEL)");
        }

        if(getEnvi().getCellNature(getCol(), getHgt()) != Cell.LAD || getHgt() == getTarget().getHgt()){
            if(!(Checker.implication((HOL_HDR.contains(getEnvi().getCellNature(getCol(), getHgt())) || PLT_MLT_LAD_DOR_NGU_TRP.contains(getEnvi().getCellNature(getCol(), getHgt()-1)) || Util.containsGuard(getEnvi().getCellContent(getCol(), getHgt()-1))) && getTarget().getCol() < getCol() , getBehaviour() == Command.MOVEL))){
                Contractor.defaultContractor().invariantError("GuardContract", "Following target to the left");
                
            }
    
            if(!(Checker.implication((HOL_HDR.contains(getEnvi().getCellNature(getCol(), getHgt())) || PLT_MLT_LAD_DOR_NGU_TRP.contains(getEnvi().getCellNature(getCol(), getHgt()-1)) || Util.containsGuard(getEnvi().getCellContent(getCol(), getHgt()-1))) && getTarget().getCol() >= getCol() , getBehaviour() == Command.MOVER))){
                Contractor.defaultContractor().invariantError("GuardContract", "Following target to the right");
                
            }
        }
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

        // \pre \not e.getEnvironment().getCellNature(x, y) \in {MTL, PLT, DOR, NGU}
        Set<Cell> MTL_PLT_DOR_NGU = new HashSet<>();
        MTL_PLT_DOR_NGU.add(Cell.MTL);
        MTL_PLT_DOR_NGU.add(Cell.PLT);
        MTL_PLT_DOR_NGU.add(Cell.DOR);
        MTL_PLT_DOR_NGU.add(Cell.NGU);
        if (!(!MTL_PLT_DOR_NGU.contains(e.getEnvironment().getCellNature(x, y)))) {
            Contractor.defaultContractor().preconditionError("GuardContract", "init",
                    "\\not e.getEnvironment().getCellNature(x, y) \\in {MTL, PLT, DOR, NGU}");
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

        //\post getTimeLeftParalyzed() == 0
        if(!(getTimeLeftParalyzed() == 0)){
            Contractor.defaultContractor().postconditionError("GuardContract", "init", "getTimeLeftParalyzed() == 0");
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
        Engine getEngine_atPre = getEngine();
        Environment getEnvi_atPre = getEnvi();
        int getId_atPre = getId();
        int getIdCounter_atPre = getIdCounter();
        Coord getInitCoords_atPre = getInitCoords();
        GuardType getNature_atPre = getNature();
        Character getTarget_atPre = getTarget();
        int getTimeInHole_atPre = getTimeInHole();
        int getTimeLeftParalyzed_atPre = getTimeLeftParalyzed();
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

        //\post getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT_DOR_NGU = new HashSet<>();
        MTL_PLT_DOR_NGU.add(Cell.MTL);
        MTL_PLT_DOR_NGU.add(Cell.PLT);
        MTL_PLT_DOR_NGU.add(Cell.DOR);
        MTL_PLT_DOR_NGU.add(Cell.NGU);
        if(!(Checker.implication(MTL_PLT_DOR_NGU.contains(getCellNature_atPre[getCol_atPre - 1][getHgt_atPre + 1]), getCol_atPre == getCol() && getHgt_atPre == getHgt()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", " getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1) \\in {MTL, PLT, DOR, NGU} \\impl getCol() == getCol()@pre && getHgt() == getHgt()@pre");
        }

        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1)@pre \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
        if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre + 1)), getCol_atPre == getCol() && getHgt_atPre == getHgt()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1) \\impl getCol() == getCol()@pre && getHgt() == getHgt()@pre");
        }

        //\post getCol()@pre != 0 && 
        //      \not getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} &&
        //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1)@pre &&
        //      \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)@pre
        //      \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre + 1
        boolean cond1 = getCol_atPre != 0;
        boolean cond2 = !MTL_PLT_DOR_NGU.contains(getCellNature_atPre[getCol_atPre - 1][getHgt_atPre + 1]);
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre + 1));
        boolean cond4 = Util.containsPlayer(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1));
        if(!(Checker.implication(cond1 && cond2 && cond3 && cond4, getCol() == getCol_atPre && getHgt() == getHgt_atPre + 1))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "Climbing on top of the HOL because there is a player juste above the guard");
        }

        //\post getCol()@pre != 0 && 
        //      \not getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} &&
        //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre + 1)@pre &&
        //      \not \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)@pre
        //      \impl getCol() == getCol()@pre - 1 && getHgt() == getHgt()@pre + 1
        cond4 = !Util.containsPlayer(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1));
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
        
        //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
        if(!(getTimeLeftParalyzed() == getTimeLeftParalyzed_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbLeft", "getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre");
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
        Cell[][] getCellNature_atPre = new Cell[getEnvi().getWidth()][getEnvi().getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvi().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvi().getHeight(); v++){
                getCellNature_atPre[u][v] = getEnvi().getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvi().getCellContent(u, v)));
            }
        }
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
        int getTimeLeftParalyzed_atPre = getTimeLeftParalyzed();
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

        //\post getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT_DOR_NGU = new HashSet<>();
        MTL_PLT_DOR_NGU.add(Cell.MTL);
        MTL_PLT_DOR_NGU.add(Cell.PLT);
        MTL_PLT_DOR_NGU.add(Cell.DOR);
        MTL_PLT_DOR_NGU.add(Cell.NGU);
        if(!(Checker.implication(MTL_PLT_DOR_NGU.contains(getCellNature_atPre[getCol_atPre + 1][getHgt_atPre + 1]), getCol_atPre == getCol() && getHgt_atPre == getHgt()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", " getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1) \\in {MTL, PLT, DOR, NGU} \\impl getCol() == getCol()@pre && getHgt() == getHgt()@pre");
        }

        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1)@pre \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre
        if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre + 1)), getCol_atPre == getCol() && getHgt_atPre == getHgt()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1) \\impl getCol() == getCol()@pre && getHgt() == getHgt()@pre");
        }

        //\post getCol()@pre != getEnvi().getWidth() - 1 && 
        //      \not getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} &&
        //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1)@pre &&
        //      \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)@pre
        //      \impl getCol() == getCol()@pre && getHgt() == getHgt()@pre + 1
        boolean cond1 = getCol_atPre != getEnvi().getWidth() - 1;
        boolean cond2 = !MTL_PLT_DOR_NGU.contains(getCellNature_atPre[getCol_atPre + 1][getHgt_atPre + 1]);
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre + 1));
        boolean cond4 = Util.containsPlayer(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1));
        if(!(Checker.implication(cond1 && cond2 && cond3 && cond4, getCol() == getCol_atPre && getHgt() == getHgt_atPre + 1))){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "Climbing on top of the HOL because there is a player juste above the guard");
        }
        
        //\post getCol()@pre != getEnvi().getWidth() - 1 && 
        //      \not getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre + 1)@pre \in {MTL, PLT, DOR, NGU} &&
        //      \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre + 1)@pre &&
        //      \not \Exists Player p \in getEnvi().getCellContent(getCol()pre, getHgt()@pre + 1)@pre
        //      \impl getCol() == getCol()@pre + 1 && getHgt() == getHgt()@pre + 1
        cond4 = !Util.containsPlayer(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1));
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

        //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
        if(!(getTimeLeftParalyzed() == getTimeLeftParalyzed_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre");
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
        //captures
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvi().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvi().getHeight(); v++){
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvi().getCellContent(u, v)));
            }
        }
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

        //\post \Exists Guard g \in getEnvi().getCellContent(getInitCoords().getX(), getInitCoords().getY())@pre
        //      \impl g.getCol() == g.getInitCoords.getX() \and g.getHgt() == g.getInitCoords().getY()
        Guard g = Util.getGuard(getCellContent_atPre.get(getInitCoords().getX()).get(getInitCoords().getY()));
        if(g != null){
            if(!(Checker.implication(g != null, g.getCol() == g.getInitCoords().getX() && g.getHgt() == g.getInitCoords().getY()))){
                Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "\\Exists Guard g \\in getEnvi().getCellContent(getInitCoords().getX(), getInitCoords().getY())@pre \\impl g.getCol() == g.getInitCoords.getX() \\and g.getHgt() == g.getInitCoords().getY()");
            }
        }


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

        //\post getTimeLeftParalyzed() == 0
        if(!(getTimeLeftParalyzed() == 0)){
            Contractor.defaultContractor().postconditionError("GuardContract", "moveToInitCoords", "getTimeLeftParalyzed() == 0");
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
    public void paralyze(){
        //captures
        boolean isCarryingTreasure_atPre = isCarryingTreasure();
        int getCol_atPre = getCol();
        int getHgt_atPre = getHgt();
        int getIdCounter_atPre = getIdCounter();
        int getTimeInHole_atPre = getTimeInHole();


        //inv pre 
        checkInvariant();
        
        super.paralyze();

        //inv post
        checkInvariant();

        //\post isCarryingTreasure() == isCarryingTreasure()@pre
        if(!(isCarryingTreasure() == isCarryingTreasure_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "paralyze", "isCarryingTreasure() == isCarryingTreasure()@pre");
        }

        //\post getTimeInHole() == getTimeInHole()@pre
        if(!(getTimeInHole() == getTimeInHole_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "paralyze", "getTimeInHole() == getTimeInHole()@pre");
        }

        //\post getCol() == getCol()@pre
        if(!(getCol() == getCol_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "paralyze", "getCol() == getCol()@pre");
        }

        //\post getHgt() == getHgt()@pre
        if(!(getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "paralyze", "getHgt() == getHgt()@pre");
        }

        //\post getIdCounter() == getIdCounter()@pre
        if(!(getIdCounter() == getIdCounter_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "paralyze", "getIdCounter() == getIdCounter()@pre");
        }

        //\post getTimeLeftParalyzed() == 10
        if(!(getTimeLeftParalyzed() == 10)){
            Contractor.defaultContractor().postconditionError("GuardContract", "paralyze", "getTimeLeftParalyzed() == 10");
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
        GuardContract this_atPre = new GuardContract(duplicateGuard(getDelegate()));
        int getIdCounter_atPre = getIdCounter();
        boolean isCarryingTreasure_atPre = isCarryingTreasure();
        int getTimeInHole_atPre = getTimeInHole();
        int getTimeLeftParalyzed_atPre = getTimeLeftParalyzed();
        Command getBehaviour_atPre = getBehaviour();
        
        //inv pre 
        checkInvariant();
        
        super.step();

        //inv post
        checkInvariant();

        //\post getTimeLeftParalyzed()@pre == 0 \impl getTimeLeftParalyzed() == 0
        if(!(Checker.implication(getTimeLeftParalyzed_atPre == 0, getTimeLeftParalyzed() == 0))){
            Contractor.defaultContractor().postconditionError("GuardContract", "step", "getTimeLeftParalyzed()@pre == 0 \\impl getTimeLeftParalyzed() == 0");
        }
        //\post getTimeLeftParalyzed()@pre > 0 \and isFalling \impl getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
        if(!(Checker.implication(getTimeLeftParalyzed_atPre > 0 && isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre), getTimeLeftParalyzed() == getTimeLeftParalyzed_atPre))){
            Contractor.defaultContractor().postconditionError("GuardContract", "step", "getTimeLeftParalyzed()@pre > 0 \\and isFalling \\impl getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre");
        }

        //\post getTimeLeftParalyzed()@pre > 0 \and \not isFalling \impl getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre - 1
        if(!(Checker.implication(getTimeLeftParalyzed_atPre > 0 && !isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre), getTimeLeftParalyzed() == getTimeLeftParalyzed_atPre - 1))){
            Contractor.defaultContractor().postconditionError("GuardContract", "step", "getTimeLeftParalyzed()@pre > 0 \\and \\not isFalling \\impl getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre - 1");
        }

        //\post \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \and \not isCarryingTreasure()@pre 
        //      \impl isCarryingTreasure() \and \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
        if(!(Checker.implication(Util.containsTreasure(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre)) && !isCarryingTreasure_atPre && getCellNature_atPre[getCol_atPre][getHgt_atPre - 1] != Cell.HOL,
                                 isCarryingTreasure() && !Util.containsTreasure(getEnvi().getCellContent(getCol_atPre, getHgt_atPre))))){
            Contractor.defaultContractor().postconditionError("GuardContract", "step", "\\Exists Treasure t \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \\and \\not isCarryingTreasure()@pre \\impl isCarryingTreasure() \\and \\not \\Exists Treasure t \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)");
        }

        //\post \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \and isCarryingTreasure()@pre
        //      \impl isCarryingTreasure() \and \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
        if(!(Checker.implication(Util.containsTreasure(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre)) && isCarryingTreasure_atPre,
                                 isCarryingTreasure() && Util.containsTreasure(getEnvi().getCellContent(getCol_atPre, getHgt_atPre))))){
            Contractor.defaultContractor().postconditionError("GuardContract", "step", "\\Exists Treasure t \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \\and isCarryingTreasure()@pre \\impl isCarryingTreasure() \\and \\Exists Treasure t \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)");
        }

        //\post \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \and \not isCarryingTreasure()@pre
        //      \impl \not isCarryingTreasure() \and \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
        if(!(Checker.implication(!Util.containsTreasure(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre)) && !isCarryingTreasure_atPre,
                                 !isCarryingTreasure() && !Util.containsTreasure(getEnvi().getCellContent(getCol_atPre, getHgt_atPre))))){
            Contractor.defaultContractor().postconditionError("GuardContract", "step", "\\not \\Exists Treasure t \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre \\and \\not isCarryingTreasure()@pre \\impl \\not isCarryingTreasure() \\and \\not \\Exists Treasure t \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)");
        }


        //\post \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre
        //      \and isCarryingTreasure()@pre
        //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1)@pre == HOL
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre)@pre \in {LAD, HDR, HOL}
        //      \and \not \Exists Guard g \ in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)@pre
        //      \impl \not isCarryingTreasure() \and  \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
        Set<Cell> LAD_HDR_HOL = new HashSet<>();
        LAD_HDR_HOL.add(Cell.LAD);
        LAD_HDR_HOL.add(Cell.HDR);
        LAD_HDR_HOL.add(Cell.HOL);

        boolean leftcond1 = !Util.containsTreasure(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre));
        boolean leftcond2 = isCarryingTreasure_atPre;
        boolean leftcond3 = getCellNature_atPre[getCol_atPre][getHgt_atPre - 1] == Cell.HOL;
        boolean leftcond4 = !LAD_HDR_HOL.contains(getCellNature_atPre[getCol_atPre][getHgt_atPre]);
        boolean leftcond5 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        boolean rightcond = !isCarryingTreasure() && Util.containsTreasure(getEnvi().getCellContent(getCol_atPre, getHgt_atPre));
        if(!(Checker.implication(leftcond1 && leftcond2 && leftcond3 && leftcond4 && leftcond5, rightcond))){
            Contractor.defaultContractor().postconditionError("GuardContract", "step", "Check drop chest when falling into a HOL");
        }



        //\post \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)@pre
        //      \and isCarryingTreasure()@pre
        //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1)@pre != HOL
        //          \or \getEnvi().getCellNature(getCol()@pre, getHgt()@pre)@pre \in {LAD, HDR, HOL}
        //          \or \Exists Guard g \ in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)@pre
        //      \impl isCarryingTreasure() \and \not \Exists Treasure t \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre)
        boolean leftcondAND1 = !Util.containsTreasure(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre));
        boolean leftcondAND2 = isCarryingTreasure_atPre;
        boolean leftcondOR1 = getCellNature_atPre[getCol_atPre][getHgt_atPre - 1] != Cell.HOL;
        boolean leftcondOR2 = Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        boolean leftcondOR3 = LAD_HDR_HOL.contains(getCellNature_atPre[getCol_atPre][getHgt_atPre]);
        rightcond =  isCarryingTreasure() && !Util.containsTreasure(getEnvi().getCellContent(getCol_atPre, getHgt_atPre));
        if(!(Checker.implication(leftcondAND1 && leftcondAND2 && (leftcondOR1 || leftcondOR2 || leftcondOR3), rightcond))){
            Contractor.defaultContractor().postconditionError("GuardContract", "step", "Check not dropping chest when not falling into a HOL");
        }


        //\post getTimeLeftParalyzed()@pre == 0 \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == HOL \and getTimeInHole()@pre < 5 \impl getTimeInHole() == getTimeInHole()@pre + 1
        if(!(Checker.implication(getTimeLeftParalyzed_atPre == 0 && getCellNature_atPre[getCol_atPre][getHgt_atPre] == Cell.HOL && getTimeInHole_atPre < 5, getTimeInHole() == getTimeInHole_atPre + 1))){
            Contractor.defaultContractor().postconditionError("GuardContract", "step", "getTimeLeftParalyzed()@pre == 0 \\and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == HOL \\and getTimeInHole()@pre < 5 \\impl getTimeInHole() == getTimeInHole()@pre + 1");
        }


        //\post getTimeLeftParalyzed()@pre == 0 \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == HOL 
        //      \and getTimeInHole()@pre >= 5 
        //      \and getBehaviour()@pre == MOVEL 
        //      \impl getTimeInHole() == 0 \and climbLeft()
        if(getTimeLeftParalyzed_atPre == 0 && getCellNature_atPre[getCol_atPre][getHgt_atPre] == Cell.HOL && getTimeInHole_atPre >= 5 && getBehaviour_atPre == Command.MOVEL){
            GuardContract clone = new GuardContract(duplicateGuard(this_atPre.getDelegate()));
            clone.climbLeft();
            if(!(getTimeInHole() == 0 && this.isEqual(clone))){
                Contractor.defaultContractor().postconditionError("GuardContract", "step", "Check try to get out of hole with climbLeft");
            }
        }


        //\post getTimeLeftParalyzed()@pre == 0 \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == HOL 
        //      \and getTimeInHole()@pre >= 5 
        //      \and getBehaviour()@pre == MOVER
        //      \impl getTimeInHole() == 0 \and climbRight()
        if(getTimeLeftParalyzed_atPre == 0 && getCellNature_atPre[getCol_atPre][getHgt_atPre] == Cell.HOL && getTimeInHole_atPre >= 5 && getBehaviour_atPre == Command.MOVER){
            GuardContract clone = new GuardContract(duplicateGuard(this_atPre.getDelegate()));
            clone.climbRight();
            if(!(getTimeInHole() == 0 && this.isEqual(clone))){
                Contractor.defaultContractor().postconditionError("GuardContract", "step", "Check try to get out of hole with climbRight");
            }
        }

        //\post isFalling \impl goDown()
        if(isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre)){
            GuardContract clone = new GuardContract(duplicateGuard(this_atPre.getDelegate()));
            clone.goDown();
            if(!(this.isEqual(clone))){
                Contractor.defaultContractor().postconditionError("GuardContract", "step", "Check goDown when falling");
            }
        }

        //\post getTimeLeftParalyzed()@pre == 0 \and \not isFalling \and getEnvi().getCellnature(getCol()@pre, getHgt()@pre)@pre != HOL \and getBehaviour()@pre == MOVEL \impl goLeft()
        if(getTimeLeftParalyzed_atPre == 0 && !isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getCellNature_atPre[getCol_atPre][getHgt_atPre] != Cell.HOL && getBehaviour_atPre == Command.MOVEL){
            GuardContract clone = new GuardContract(duplicateGuard(this_atPre.getDelegate()));
            clone.goLeft();
            if(!(this.isEqual(clone))){
                Contractor.defaultContractor().postconditionError("GuardContract", "step", "Check goLeft");
            }
        }
        
        //\post getTimeLeftParalyzed()@pre == 0 \and \not isFalling \and getEnvi().getCellnature(getCol()@pre, getHgt()@pre)@pre != HOL \and getBehaviour()@pre == MOVER \impl goRight()
        if(getTimeLeftParalyzed_atPre == 0 && !isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getCellNature_atPre[getCol_atPre][getHgt_atPre] != Cell.HOL && getBehaviour_atPre == Command.MOVER){
            GuardContract clone = new GuardContract(duplicateGuard(this_atPre.getDelegate()));
            clone.goRight();
            if(!(this.isEqual(clone))){
                Contractor.defaultContractor().postconditionError("GuardContract", "step", "Check goRight");
            }
        }

        //\post getTimeLeftParalyzed()@pre == 0 \and \not isFalling \and getEnvi().getCellnature(getCol()@pre, getHgt()@pre)@pre != HOL \and getBehaviour()@pre == MOVEU \impl goUp()
        if(getTimeLeftParalyzed_atPre == 0 && !isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getCellNature_atPre[getCol_atPre][getHgt_atPre] != Cell.HOL && getBehaviour_atPre == Command.MOVEU){
            GuardContract clone = new GuardContract(duplicateGuard(this_atPre.getDelegate()));
            clone.goUp();
            if(!(this.isEqual(clone))){
                Contractor.defaultContractor().postconditionError("GuardContract", "step", "Check goUp");
            }
        }
        
        //\post getTimeLeftParalyzed()@pre == 0 \and \not isFalling \and getEnvi().getCellnature(getCol()@pre, getHgt()@pre)@pre != HOL \and getBehaviour()@pre == MOVED \impl goDown()
        if(getTimeLeftParalyzed_atPre == 0 && !isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getCellNature_atPre[getCol_atPre][getHgt_atPre] != Cell.HOL && getBehaviour_atPre == Command.MOVED){
            GuardContract clone = new GuardContract(duplicateGuard(this_atPre.getDelegate()));
            clone.goDown();
            if(!(this.isEqual(clone))){
                Contractor.defaultContractor().postconditionError("GuardContract", "step", "Check goDown");
            }
        }
        
        
        //\post getIdCounter() == getIdCounter()@pre
        if(!(getIdCounter() == getIdCounter_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "step", "getIdCounter() == getIdCounter()@pre");
        }
        
    }

    private boolean isFalling(int getCol_atPre, int getHgt_atPre, Cell[][] getCellNature_atPre,  List<List<Set<Entity>>> getCellContent_atPre){
        //\def falling = \not getEnvi().getCellNature(getCol(), getHgt())@pre \in {LAD, HDR, HOL}
        //               \and getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {EMP, HDR, HOL, NPL}
        //               \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre

        Set<Cell> LAD_HDR_HOL = new HashSet<>();
        LAD_HDR_HOL.add(Cell.LAD);
        LAD_HDR_HOL.add(Cell.HDR);
        LAD_HDR_HOL.add(Cell.HOL);


        Set<Cell> EMP_HDR_HOL_NPL = new HashSet<>();
        EMP_HDR_HOL_NPL.add(Cell.EMP);
        EMP_HDR_HOL_NPL.add(Cell.HDR);
        EMP_HDR_HOL_NPL.add(Cell.HOL);
        EMP_HDR_HOL_NPL.add(Cell.NPL);

        

        boolean cond1 = !LAD_HDR_HOL.contains(getCellNature_atPre[getCol_atPre][getHgt_atPre]);
        boolean cond2 = EMP_HDR_HOL_NPL.contains(getCellNature_atPre[getCol_atPre][getHgt_atPre - 1]);
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
        int getIdCounter_atPre = getIdCounter();
        int getTimeInHole_atPre = getTimeInHole();
        int getTimeLeftParalyzed_atPre = getTimeLeftParalyzed();
        boolean isCarryingTreasure_atPre = isCarryingTreasure();

        //inv pre
        checkInvariant();
        
        super.goLeft();

        //inv post
        checkInvariant();

        //\post getHgt() == getHgt()@pre
        if(!(getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "getHgt() == getHgt()@pre");
        }


        //\post getCol()@pre == 0 \impl getCol() == getCol()@pre
        if(!(Checker.implication(getCol_atPre == 0, getCol() == getCol_atPre))){
            Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "getCol()@pre == 0 \\impl getCol() == getCol()@pre");
        }


        //\post (getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR, NGU}) \impl getCol() == getCol()@pre
        Set<Cell> MTL_PLT_DOR_NGU = new HashSet<>();
        MTL_PLT_DOR_NGU.add(Cell.MTL);
        MTL_PLT_DOR_NGU.add(Cell.PLT);
        MTL_PLT_DOR_NGU.add(Cell.DOR);
        MTL_PLT_DOR_NGU.add(Cell.NGU);
        if(!(getCol_atPre == 0)){
            if(!(Checker.implication(MTL_PLT_DOR_NGU.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre)), getCol() == getCol_atPre))){
                Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "(getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \\in {MTL, PLT, DOR, NGU}) \\impl getCol() == getCol()@pre");
            }
        }


        //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NGU}
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
        PLT_MTL_LAD_DOR.add(Cell.NGU);

        boolean cond1 = !(LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
        boolean cond2 = !(PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "\\not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \\in {LAD, HDR}) \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {PLT, MTL, LAD, DOR, NGU} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getCol() == getCol()@pre()");
        }


        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \impl getCol() == getCol()@pre
        if(!(getCol_atPre == 0)){
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre)), getCol_atPre == getCol()))){
                Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \\impl getCol() == getCol()@pre");
            }
        }


        //\post getCol()@pre != 0
        //      \and \not getEnvi().getCellNature()(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR, NGU}
        //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
        //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NGU}
        //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre)
        //      \impl getCol() == getCol()@pre - 1
        if(!(getCol_atPre == 0)){
            boolean condAND1 = getCol_atPre != 0;
            boolean condAND2 = !MTL_PLT_DOR_NGU.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre));
            boolean condOR1 = (LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
            boolean condOR2 = (PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
            boolean condOR3 = Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            boolean condAND3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre));
            if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre - 1)){
                Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "Check if goLeft worked correctly with all conditions");
            }
        }

        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "const getEnvi()");
        }
        
        //\post isCarryingTreasure() == isCarryingTreasure()@pre
        if(!(isCarryingTreasure() == isCarryingTreasure_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "isCarryingTreasure() == isCarryingTreasure()@pre");
        }

        //\post getTimeInHole() == getTimeInHole()@pre
        if(!(getTimeInHole() == getTimeInHole_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "getTimeInHole() == getTimeInHole()@pre");

        }
        
        //\post getIdCounter() == getIdCounter()@pre
        if(!(getIdCounter() == getIdCounter_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "getIdCounter() == getIdCounter()@pre");

        }

        //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
        if(!(getTimeLeftParalyzed() == getTimeLeftParalyzed_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goLeft", "getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre");
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
        int getIdCounter_atPre = getIdCounter();
        int getTimeInHole_atPre = getTimeInHole();
        int getTimeLeftParalyzed_atPre = getTimeLeftParalyzed();
        boolean isCarryingTreasure_atPre = isCarryingTreasure();


        //inv pre
        checkInvariant();
        
        super.goRight();

        //inv post
        checkInvariant();

        //\post getHgt() == getHgt()@pre
        if(!(getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "getHgt() == getHgt()@pre");
        }

        //\post getCol()@pre == getEnvi().getWidth() - 1 \impl getCol() == getCol()@pre
        if(!(Checker.implication(getCol_atPre == getEnvi().getWidth() - 1, getCol() == getCol_atPre))){
            Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "getCol()@pre == getEnvi().getWidth() - 1 \\impl getCol() == getCol()@pre");
        }
        
        //\post (getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR, NGU}) \impl getCol() == getCol()@pre
        Set<Cell> MTL_PLT_DOR_NGU = new HashSet<>();
        MTL_PLT_DOR_NGU.add(Cell.MTL);
        MTL_PLT_DOR_NGU.add(Cell.PLT);
        MTL_PLT_DOR_NGU.add(Cell.DOR);
        MTL_PLT_DOR_NGU.add(Cell.NGU);
        if(getCol_atPre != getEnvi().getWidth() - 1){
            if(!(Checker.implication(MTL_PLT_DOR_NGU.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre)), getCol() == getCol_atPre))){
                Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "(getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \\in {MTL, PLT, DOR, NGU}) \\impl getCol() == getCol()@pre");
            }
        }
        
        //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NGU}
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
        PLT_MTL_LAD_DOR.add(Cell.NGU);

        boolean cond1 = !(LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
        boolean cond2 = !(PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "\\not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \\in {LAD, HDR}) \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {PLT, MTL, LAD, DOR, NGU} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getCol() == getCol()@pre()");
        }
        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \impl getCol() == getCol()@pre
        
        if(getCol_atPre != getEnvi().getWidth() - 1){        
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre)), getCol_atPre == getCol()))){
                Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \\impl getCol() == getCol()@pre");
            }
        }
        
        
        //\post getCol()@pre != getEnvi().getWidth() - 1
        //      \and \not getEnvi().getCellNature()(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR, NGU}
        //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
        //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR, NGU}
        //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre)
        //      \impl getCol() == getCol()@pre + 1
        if(getCol_atPre != getEnvi().getWidth() - 1){        
            boolean condAND1 = getCol_atPre != getEnvi().getWidth() - 1;
            boolean condAND2 = !MTL_PLT_DOR_NGU.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre));
            boolean condOR1 = (LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
            boolean condOR2 = (PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
            boolean condOR3 = Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            boolean condAND3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre));
            if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre + 1)){
                Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "Check if goRight worked correctly with all conditions");
            }
        }

        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "const getEnvi()");
        }

        //\post isCarryingTreasure() == isCarryingTreasure()@pre
        if(!(isCarryingTreasure() == isCarryingTreasure_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "isCarryingTreasure() == isCarryingTreasure()@pre");
        }

        //\post getTimeInHole() == getTimeInHole()@pre
        if(!(getTimeInHole() == getTimeInHole_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "getTimeInHole() == getTimeInHole()@pre");

        }
        
        //\post getIdCounter() == getIdCounter()@pre
        if(!(getIdCounter() == getIdCounter_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "getIdCounter() == getIdCounter()@pre");

        }

        //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
        if(!(getTimeLeftParalyzed() == getTimeLeftParalyzed_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goRight", "getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre");
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
        int getIdCounter_atPre = getIdCounter();
        int getTimeInHole_atPre = getTimeInHole();
        int getTimeLeftParalyzed_atPre = getTimeLeftParalyzed();
        boolean isCarryingTreasure_atPre = isCarryingTreasure();


        //inv pre
        checkInvariant();
        
        super.goUp();

        //inv post
        checkInvariant();

        //\post getCol() == getCol()@pre
        if(!(getCol() == getCol_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "getCol() == getCol()@pre");
        }

        //\post getHgt()@pre == getEnvi().getHeight() - 1 \impl getHgt() == getHgt()@pre
        if(!Checker.implication(getHgt_atPre == getEnvi().getHeight() - 1, getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "getHgt()@pre == getEnvi().getHeight() - 1 \\impl getHgt() == getHgt()@pre");
        }

        //\post (getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \in {MTL, PLT, DOR, NGU} \impl getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT_DOR_NGU = new HashSet<>();
        MTL_PLT_DOR_NGU.add(Cell.MTL);
        MTL_PLT_DOR_NGU.add(Cell.PLT);
        MTL_PLT_DOR_NGU.add(Cell.DOR);
        MTL_PLT_DOR_NGU.add(Cell.NGU);
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            if(!(Checker.implication(MTL_PLT_DOR_NGU.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre + 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "(getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \\in {MTL, PLT, DOR, NGU} \\impl getHgt() == getHgt()@pre");
            }
        }
        //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \impl getHgt() == getHgt()@pre
        if(!Checker.implication(getEnvi().getCellNature(getCol_atPre, getHgt_atPre) != Cell.LAD, getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \\impl getHgt() == getHgt()@pre");
        }

        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \impl getHgt() == getHgt()@pre
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            if(!Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1)), getHgt() == getHgt_atPre)){
                Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \\impl getHgt() == getHgt()@pre");
            }
        }

        //\post getHgt()@pre != getEnvi().getHeight() - 1 
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \in {MTL, PLT, DOR, NGU}
        //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1)
        //      \impl getHgt() == getHgt()@pre + 1
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            boolean cond1 = getHgt_atPre != getEnvi().getHeight() - 1;
            boolean cond2 = !MTL_PLT_DOR_NGU.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre + 1));
            boolean cond3 = getEnvi().getCellNature(getCol_atPre, getHgt_atPre) == Cell.LAD;
            boolean cond4 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1));
            if(!Checker.implication(cond1 && cond2 && cond3 && cond4, getHgt() == getHgt_atPre + 1)){
                Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "getHgt()@pre != getEnvi().getHeight() - 1  \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \\in {MTL, PLT, DOR, NGU} \\and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \\impl getHgt() == getHgt()@pre + 1");
            }
        }
        
        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "const getEnvi()");
        }
        
        //\post isCarryingTreasure() == isCarryingTreasure()@pre
        if(!(isCarryingTreasure() == isCarryingTreasure_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "isCarryingTreasure() == isCarryingTreasure()@pre");
        }

        //\post getTimeInHole() == getTimeInHole()@pre
        if(!(getTimeInHole() == getTimeInHole_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "getTimeInHole() == getTimeInHole()@pre");

        }
        
        //\post getIdCounter() == getIdCounter()@pre
        if(!(getIdCounter() == getIdCounter_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "getIdCounter() == getIdCounter()@pre");

        }

        //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
        if(!(getTimeLeftParalyzed() == getTimeLeftParalyzed_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goUp", "getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre");
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
        int getIdCounter_atPre = getIdCounter();
        int getTimeInHole_atPre = getTimeInHole();
        int getTimeLeftParalyzed_atPre = getTimeLeftParalyzed();
        boolean isCarryingTreasure_atPre = isCarryingTreasure();


        //inv pre
        checkInvariant();
        
        super.goDown();

        //inv post
        checkInvariant();

        //\post getCol() == getCol()@pre
        if(!(getCol() == getCol_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "goDown", "getCol() == getCol()@pre");
        }
        
        //\post getHgt()@pre == 0 \impl getHgt() == getHgt()@pre
        if(!(Checker.implication(getHgt_atPre == 0, getHgt() == getHgt_atPre))){
            Contractor.defaultContractor().postconditionError("GuardContract", "goDown", "getHgt()@pre == 0 \\impl getHgt() == getHgt()@pre");
        }
        
        //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR, NGU} \impl getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT_DOR_NGU = new HashSet<>();
        MTL_PLT_DOR_NGU.add(Cell.MTL);
        MTL_PLT_DOR_NGU.add(Cell.PLT);
        MTL_PLT_DOR_NGU.add(Cell.DOR);
        MTL_PLT_DOR_NGU.add(Cell.NGU);
        if(getHgt_atPre != 0){
            if(!(Checker.implication(MTL_PLT_DOR_NGU.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("GuardContract", "goDown", "getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {MTL, PLT, DOR, NGU} \\impl getHgt() == getHgt()@pre");
            }
        }
        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \impl getHgt() == getHgt()@pre
        if(getHgt_atPre != 0){
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("GuardContract", "goDown", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getHgt() == getHgt()@pre");
            }
        }
        
        //\post getHgt()@pre != 0
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR, NGU}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) 
        //      \impl getHgt() == getHgt()@pre - 1
        if(getHgt_atPre != 0){
            boolean cond1 = getHgt_atPre != 0;
            boolean cond2 = !MTL_PLT_DOR_NGU.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1));
            boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            if(!(Checker.implication(cond1 && cond2 && cond3, getHgt() == getHgt_atPre - 1))){
                Contractor.defaultContractor().postconditionError("GuardContract", "goDown", "getHgt()@pre != 0 \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {MTL, PLT, DOR, NGU} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)  \\impl getHgt() == getHgt()@pre - 1");
            }
        }

        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("GuardContract", "goDown", "const getEnvi()");
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

        //\post getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre
        if(!(getTimeLeftParalyzed() == getTimeLeftParalyzed_atPre)){
            Contractor.defaultContractor().postconditionError("GuardContract", "climbRight", "getTimeLeftParalyzed() == getTimeLeftParalyzed()@pre");
        }
        
    }



    private Guard duplicateGuard(Guard d){
        List<Guard> copyGuardsOfEngine = d.getEngine().copy().getGuards();
        for(Guard g : copyGuardsOfEngine){
            if(g.getCol() == d.getCol() && g.getHgt() == d.getHgt()){
                return g;
            }
        }
        return null;
    }

    private boolean isEqual(Guard g){
        boolean cond1 =  this.getCol() == g.getCol() && this.getHgt() == g.getHgt();
        boolean cond2 = true;
        for(int i=0; i<getEnvi().getWidth(); i++){
            for(int j=0;j<getEnvi().getHeight(); j++){
                if(getEnvi().getCellNature(i, j) != g.getEnvi().getCellNature(i, j)){
                        cond2 = false;
                }
            }
        }
        boolean cond3 = g.isCarryingTreasure() == isCarryingTreasure();
        boolean cond4 = g.getTimeLeftParalyzed() == getTimeLeftParalyzed();
        return cond1 && cond2 && cond3 && cond4;
    }
    
}