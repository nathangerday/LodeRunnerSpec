package contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Cell;
import data.Command;
import data.Coord;
import data.Entity;
import decorators.PlayerDecorator;
import impl.EditableScreenImpl;
import impl.EngineImpl;
import impl.EnvironmentImpl;
import impl.PlayerImpl;
import services.EditableScreen;
import services.Engine;
import services.Environment;
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

        //\pre \not s.getCellNature(x, y) \in {MTL, PLT}
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if(!(!MTL_PLT.contains(e.getEnvironment().getCellNature(x, y)))){
            Contractor.defaultContractor().preconditionError("PlayerContract", "init", "\\not s.getCellNature(x, y) \\in {MTL, PLT}");
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
        
        //\post \not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre
        //      \impl noCellNatureChanged
        Set<Cell> PLT_MTL_LAD = new HashSet<>();
        PLT_MTL_LAD.add(Cell.PLT);
        PLT_MTL_LAD.add(Cell.MTL);
        PLT_MTL_LAD.add(Cell.LAD);
        boolean cond1 = !(PLT_MTL_LAD.contains(getCellNature_atPre[getCol()][getHgt() - 1]));
        boolean cond2 = !Util.containsGuard(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
        if(!(Checker.implication(cond1 && cond2, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "\\not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \\in {MTL, PLT, LAD} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre \\impl noCellNatureChanged");
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

        //\post (getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD}
        //          \or \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre)
        //      \and getEnvi().getCellNature(getCol() - 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
        //      \and getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre == PLT
        //      \impl getEnvi().getCellNature(getCol() - 1, getHgt() - 1) == HOL
        //            \and \Forall i in [0, getEnvi().getWidth() - 1]
        //                      \Forall j in [0, getEnvi().getHeight() - 1]
        //                          i != getCol() - 1 || j != getHgt() - 1 \impl getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
        if(getCol() != 0){
            cond1 = PLT_MTL_LAD.contains(getCellNature_atPre[getCol()][getHgt() - 1]) || Util.containsGuard(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
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


        //inv pre
        checkInvariant();

        super.digRight();

        //inv post
        checkInvariant();

        //\post getCol() == getEnvi().getWidth() - 1 \impl noCellNatureChanged
        if(!(Checker.implication(getCol() == getEnvi().getWidth() - 1, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getCol() == getEnvi().getWidth() - 1 \\impl noCellNatureChanged");
        }

     
        //\post getHgt() == getHgt()@pre
        if(!(getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getHgt() == getHgt()@pre");
        }
        
        //\post getCol() == getCol()@pre
        if(!(getCol() == getCol_atPre)){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getCol() == getCol()@pre");
        }
        
        //\post \not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre
        //      \impl noCellNatureChanged
        Set<Cell> PLT_MTL_LAD = new HashSet<>();
        PLT_MTL_LAD.add(Cell.PLT);
        PLT_MTL_LAD.add(Cell.MTL);
        PLT_MTL_LAD.add(Cell.LAD);
        boolean cond1 = !(PLT_MTL_LAD.contains(getCellNature_atPre[getCol()][getHgt() - 1]));
        boolean cond2 = !Util.containsGuard(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
        if(!(Checker.implication(cond1 && cond2, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "\\not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \\in {MTL, PLT, LAD} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre \\impl noCellNatureChanged");
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
                Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "\\not getEnvi().getCellNature(getCol() + 1, getHgt())@pre \\in {EMP, HOL, LAD, HDR} \\impl noCellNatureChanged");
            }
        }

        //\post getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre != PLT
        //      \impl noCellNatureChanged
        if(getCol() != getEnvi().getWidth() - 1){
            if(!(Checker.implication(getCellNature_atPre[getCol() + 1][getHgt() - 1] != Cell.PLT, noCellNatureChanged(getCellNature_atPre)))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre != PLT \\impl noCellNatureChanged");
            }
        }

        //\post (getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD}
        //          \or \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre)
        //      \and getEnvi().getCellNature(getCol() + 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
        //      \and getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre == PLT
        //      \impl getEnvi().getCellNature(getCol() + 1, getHgt() - 1) == HOL
        //            \and \Forall i in [0, getEnvi().getWidth() - 1]
        //                      \Forall j in [0, getEnvi().getHeight() - 1]
        //                          i != getCol() + 1 || j != getHgt() - 1 \impl getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
        if(getCol() != getEnvi().getWidth() - 1){
            cond1 = PLT_MTL_LAD.contains(getCellNature_atPre[getCol()][getHgt() - 1]) || Util.containsGuard(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
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
                Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "Check if digLeft worked correctly");
            }
        }
        

        //\post const getEnvi()
        if(!(getEnvi().equals(getEnvi_atPre))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "const getEnvi()");
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
        //               \and getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {EMP, HDR, HOL}
        //               \and \not \Exists Guard c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre

        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);


        Set<Cell> EMP_HDR_HOL = new HashSet<>();
        EMP_HDR_HOL.add(Cell.EMP);
        EMP_HDR_HOL.add(Cell.HDR);
        EMP_HDR_HOL.add(Cell.HOL);

        

        boolean cond1 = !LAD_HDR.contains(getCellNature_atPre[getCol_atPre][getHgt_atPre]);
        boolean cond2 = EMP_HDR_HOL.contains(getCellNature_atPre[getCol_atPre][getHgt_atPre - 1]);
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


        //\post (getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT}) \impl getCol() == getCol()@pre
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if(!(getCol_atPre == 0)){
            if(!(Checker.implication(MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre)), getCol() == getCol_atPre))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "(getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \\in {MTL, PLT}) \\impl getCol() == getCol()@pre");
            }
        }


        //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
        //      \impl getCol() == getCol()@pre()
        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);
        Set<Cell> PLT_MTL_LAD = new HashSet<>();
        PLT_MTL_LAD.add(Cell.PLT);
        PLT_MTL_LAD.add(Cell.MTL);
        PLT_MTL_LAD.add(Cell.LAD);

        boolean cond1 = !(LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
        boolean cond2 = !(PLT_MTL_LAD.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "\\not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \\in {LAD, HDR}) \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {PLT, MTL, LAD} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getCol() == getCol()@pre()");
        }


        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \impl getCol() == getCol()@pre
        if(!(getCol_atPre == 0)){
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre)), getCol_atPre == getCol()))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \\impl getCol() == getCol()@pre");
            }
        }


        //\post getCol()@pre != 0
        //      \and \not getEnvi().getCellNature()(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT}
        //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
        //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD}
        //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre)
        //      \impl getCol() == getCol()@pre - 1
        if(!(getCol_atPre == 0)){
            boolean condAND1 = getCol_atPre != 0;
            boolean condAND2 = !MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre));
            boolean condOR1 = (LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
            boolean condOR2 = (PLT_MTL_LAD.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
            boolean condOR3 = Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            boolean condAND3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre));
            if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre - 1)){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goLeft", "Check if goLeft worked correctly with all conditions");
            }
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
        
        //\post (getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT}) \impl getCol() == getCol()@pre
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if(getCol_atPre != getEnvi().getWidth() - 1){
            if(!(Checker.implication(MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre)), getCol() == getCol_atPre))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "(getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \\in {MTL, PLT}) \\impl getCol() == getCol()@pre");
            }
        }
        
        //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
        //      \impl getCol() == getCol()@pre()
        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);
        Set<Cell> PLT_MTL_LAD = new HashSet<>();
        PLT_MTL_LAD.add(Cell.PLT);
        PLT_MTL_LAD.add(Cell.MTL);
        PLT_MTL_LAD.add(Cell.LAD);

        boolean cond1 = !(LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
        boolean cond2 = !(PLT_MTL_LAD.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "\\not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \\in {LAD, HDR}) \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {PLT, MTL, LAD} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getCol() == getCol()@pre()");
        }
        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \impl getCol() == getCol()@pre
        
        if(getCol_atPre != getEnvi().getWidth() - 1){        
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre)), getCol_atPre == getCol()))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \\impl getCol() == getCol()@pre");
            }
        }
        
        
        //\post getCol()@pre != getEnvi().getWidth() - 1
        //      \and \not getEnvi().getCellNature()(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT}
        //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
        //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD}
        //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre)
        //      \impl getCol() == getCol()@pre + 1
        if(getCol_atPre != getEnvi().getWidth() - 1){        
            boolean condAND1 = getCol_atPre != getEnvi().getWidth() - 1;
            boolean condAND2 = !MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre));
            boolean condOR1 = (LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
            boolean condOR2 = (PLT_MTL_LAD.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
            boolean condOR3 = Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            boolean condAND3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre));
            if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre + 1)){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goRight", "Check if goRight worked correctly with all conditions");
            }
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

        //\post (getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \in {MTL, PLT} \impl getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            if(!(Checker.implication(MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre + 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "(getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \\in {MTL, PLT} \\impl getHgt() == getHgt()@pre");
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
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \in {MTL, PLT}
        //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1)
        //      \impl getHgt() == getHgt()@pre + 1
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            boolean cond1 = getHgt_atPre != getEnvi().getHeight() - 1;
            boolean cond2 = !MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre + 1));
            boolean cond3 = getEnvi().getCellNature(getCol_atPre, getHgt_atPre) == Cell.LAD;
            boolean cond4 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1));
            if(!Checker.implication(cond1 && cond2 && cond3 && cond4, getHgt() == getHgt_atPre + 1)){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goUp", "getHgt()@pre != getEnvi().getHeight() - 1  \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \\in {MTL, PLT} \\and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \\impl getHgt() == getHgt()@pre + 1");
            }
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
        
        //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT} \impl getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.PLT);
        if(getHgt_atPre != 0){
            if(!(Checker.implication(MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {MTL, PLT} \\impl getHgt() == getHgt()@pre");
            }
        }
        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \impl getHgt() == getHgt()@pre
        if(getHgt_atPre != 0){
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getHgt() == getHgt()@pre");
            }
        }
        
        //\post getHgt()@pre != 0
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) 
        //      \impl getHgt() == getHgt()@pre - 1
        if(getHgt_atPre != 0){
            boolean cond1 = getHgt_atPre != 0;
            boolean cond2 = !MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1));
            boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            if(!(Checker.implication(cond1 && cond2 && cond3, getHgt() == getHgt_atPre - 1))){
                Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "getHgt()@pre != 0 \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {MTL, PLT} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)  \\impl getHgt() == getHgt()@pre - 1");
            }
        }

        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("PlayerContract", "goDown", "const getEnvi()");
        }
        
    }
    
    
    private Player duplicatePlayer(Player d){
        // Player res = new PlayerImpl();

        Engine resEngine = new EngineImpl();
        EditableScreen resES = new EditableScreenImpl();
        resES.init(d.getEnvi().getHeight(), d.getEnvi().getWidth());
        
        for(int i=0; i<resES.getHeight(); i++){
            for(int j=0; j<resES.getWidth(); j++){
                resES.setNature(j, i, d.getEnvi().getCellNature(j, i));
            }
        }
        
        ScreenManager sm = Factory.createScreenManager();
        sm.init();
        sm.addScreen(resES, null, null, new Coord(d.getCol(), d.getHgt()));
        resEngine.init(sm, null, resEngine);
        Player res = resEngine.getPlayer();
        return res;
    }

    private boolean isEqual(PlayerContract pc){
        //TODO define this method
        return true;
    }
    
}