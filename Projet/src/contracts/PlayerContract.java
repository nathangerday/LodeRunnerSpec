package contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Cell;
import data.Command;
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
import utils.Util;

public class PlayerContract extends PlayerDecorator{

    public PlayerContract(Player delegate){
        super(delegate);
    }

    public void checkInvariant(){
        
    }

    public boolean equals(PlayerContract other){
        return getDelegate().equals(other.getDelegate());
    }

    @Override
    public Engine getEngine() {
        Engine res = super.getEngine();
        return res;
    }

    @Override
    public void init(int x, int y, Engine e) {
    	System.out.println("init");
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

        //\pre e.getEnvironment().getCellNature(x, y) == EMP
        if(!(e.getEnvironment().getCellNature(x, y) == Cell.EMP)){
            Contractor.defaultContractor().preconditionError("PlayerContract", "init", "e.getEnvironment().getCellNature(x, y) == EMP");
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
        // PlayerContract clone = (PlayerContract)this_atPre.clone();
        // clone.goDown();
        // if(!(Checker.implication(isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre), this.isEqual(clone)))){
        //     Contractor.defaultContractor().postconditionError("PlayerContract", "step", "falling \\impl step() == this@pre.goDown()");
        // }

        // //\post (\not falling) \and getNextCommand()@pre == MOVEL \impl step() == this@pre.goLeft()
        // clone = (PlayerContract)this_atPre.clone();
        // clone.goLeft();
        // if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVEL, this.isEqual(clone)))){
        //     Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == MOVEL \\impl step() == this@pre.goLeft()");
        // }


        //\post (\not falling) \and getNextCommand()@pre == MOVER \impl step() == this@pre.goRight()
        PlayerContract clone = new PlayerContract(duplicatePlayer(this_atPre.getDelegate()));
        clone.goRight();
        if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVER, this.isEqual(clone)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == MOVER \\impl step() == this@pre.goRight()");
        }

        // //\post (\not falling) \and getNextCommand()@pre == MOVED \impl step() == this@pre.goDown()
        // clone = (PlayerContract)this_atPre.clone();
        // clone.goDown();
        // if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVED, this.isEqual(clone)))){
        //     Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == MOVED \\impl step() == this@pre.goDown()");
        // }

        // //\post (\not falling) \and getNextCommand()@pre == MOVEU \impl step() == this@pre.goUp()
        // clone = (PlayerContract)this_atPre.clone();
        // clone.goUp();
        // if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.MOVEU, this.isEqual(clone)))){
        //     Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == MOVEU \\impl step() == this@pre.goUp()");
        // }

        // //\post (\not falling) \and getNextCommand()@pre == DIGL \impl step() == this@pre.digLeft()
        // clone = (PlayerContract)this_atPre.clone();
        // clone.digLeft();
        // if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.DIGL, this.isEqual(clone)))){
        //     Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == DIGL \\impl step() == this@pre.digLeft()");
        // }

        // //\post (\not falling) \and getNextCommand()@pre == DIGR \impl step() == this@pre.digRight()
        // clone = (PlayerContract)this_atPre.clone();
        // clone.digRight();
        // if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.DIGR, this.isEqual(clone)))){
        //     Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == DIGR \\impl step() == this@pre.digRight()");
        // }

        // //\post (\not falling) \and getNextCommand()@pre == NONE \impl this == this@pre
        // clone = (PlayerContract)this_atPre.clone();
        // if(!(Checker.implication(!isFalling(getCol_atPre, getHgt_atPre, getCellNature_atPre, getCellContent_atPre) && getEngine().getNextCommand() == Command.NONE, this.isEqual(clone)))){
        //     Contractor.defaultContractor().postconditionError("PlayerContract", "step", "(\\not falling) \\and getNextCommand()@pre == NONE \\impl this == this@pre");
        // }
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
        //      \and \not \Exists Character c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre
        //      \impl noCellNatureChanged
        Set<Cell> PLT_MTL_LAD = new HashSet<>();
        PLT_MTL_LAD.add(Cell.PLT);
        PLT_MTL_LAD.add(Cell.MTL);
        PLT_MTL_LAD.add(Cell.LAD);
        boolean cond1 = !(PLT_MTL_LAD.contains(getCellNature_atPre[getCol()][getHgt() - 1]));
        boolean cond2 = !Util.constainsCharacter(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
        if(!(Checker.implication(cond1 && cond2, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "\\not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \\in {MTL, PLT, LAD} \\and \\not \\Exists Character c \\in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre \\impl noCellNatureChanged");
        }


        //\post \not getEnvi().getCellNature(getCol() - 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
        //      \impl noCellNatureChanged
        Set<Cell> EMP_HOL_LAD_HDR = new HashSet<>();
        EMP_HOL_LAD_HDR.add(Cell.EMP);
        EMP_HOL_LAD_HDR.add(Cell.HOL);
        EMP_HOL_LAD_HDR.add(Cell.LAD);
        EMP_HOL_LAD_HDR.add(Cell.HDR);
        if(!(Checker.implication(!EMP_HOL_LAD_HDR.contains(getCellNature_atPre[getCol() - 1][getHgt()]), noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "\\not getEnvi().getCellNature(getCol() - 1, getHgt())@pre \\in {EMP, HOL, LAD, HDR} \\impl noCellNatureChanged");
        }
        
        //\post getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre != PLT
        //      \impl noCellNatureChanged
        if(!(Checker.implication(getCellNature_atPre[getCol() - 1][getHgt() - 1] != Cell.PLT, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre != PLT \\impl noCellNatureChanged");
        }

        //\post (getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD}
        //          \or \Exists Character c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre)
        //      \and getEnvi().getCellNature(getCol() - 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
        //      \and getEnvi().getCellNature(getCol() - 1, getHgt() - 1)@pre == PLT
        //      \impl getEnvi().getCellNature(getCol() - 1, getHgt() - 1) == HOL
        //            \and \Forall i in [0, getEnvi().getWidth() - 1]
        //                      \Forall j in [0, getEnvi().getHeight() - 1]
        //                          i != getCol() - 1 || j != getHgt() - 1 \impl getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
        cond1 = PLT_MTL_LAD.contains(getCellNature_atPre[getCol()][getHgt() - 1]) || Util.constainsCharacter(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
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
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "Check if digLeft is possible");
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
        //      \and \not \Exists Character c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre
        //      \impl noCellNatureChanged
        Set<Cell> PLT_MTL_LAD = new HashSet<>();
        PLT_MTL_LAD.add(Cell.PLT);
        PLT_MTL_LAD.add(Cell.MTL);
        PLT_MTL_LAD.add(Cell.LAD);
        boolean cond1 = !(PLT_MTL_LAD.contains(getCellNature_atPre[getCol()][getHgt() - 1]));
        boolean cond2 = !Util.constainsCharacter(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
        if(!(Checker.implication(cond1 && cond2, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "\\not getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \\in {MTL, PLT, LAD} \\and \\not \\Exists Character c \\in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre \\impl noCellNatureChanged");
        }
        
        //\post \not getEnvi().getCellNature(getCol() + 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
        //      \impl noCellNatureChanged
        Set<Cell> EMP_HOL_LAD_HDR = new HashSet<>();
        EMP_HOL_LAD_HDR.add(Cell.EMP);
        EMP_HOL_LAD_HDR.add(Cell.HOL);
        EMP_HOL_LAD_HDR.add(Cell.LAD);
        EMP_HOL_LAD_HDR.add(Cell.HDR);
        if(!(Checker.implication(!EMP_HOL_LAD_HDR.contains(getCellNature_atPre[getCol() + 1][getHgt()]), noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "\\not getEnvi().getCellNature(getCol() + 1, getHgt())@pre \\in {EMP, HOL, LAD, HDR} \\impl noCellNatureChanged");
        }

        //\post getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre != PLT
        //      \impl noCellNatureChanged
        if(!(Checker.implication(getCellNature_atPre[getCol() + 1][getHgt() - 1] != Cell.PLT, noCellNatureChanged(getCellNature_atPre)))){
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre != PLT \\impl noCellNatureChanged");
        }

        //\post (getEnvi().getCellNature(getCol(), getHgt() - 1)@pre \in {MTL, PLT, LAD}
        //          \or \Exists Character c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre)
        //      \and getEnvi().getCellNature(getCol() + 1, getHgt())@pre \in {EMP, HOL, LAD, HDR}
        //      \and getEnvi().getCellNature(getCol() + 1, getHgt() - 1)@pre == PLT
        //      \impl getEnvi().getCellNature(getCol() + 1, getHgt() - 1) == HOL
        //            \and \Forall i in [0, getEnvi().getWidth() - 1]
        //                      \Forall j in [0, getEnvi().getHeight() - 1]
        //                          i != getCol() + 1 || j != getHgt() - 1 \impl getEnvi().getCellNature(i, j) == getEnvi().getCellNature(i, j)@pre
        cond1 = PLT_MTL_LAD.contains(getCellNature_atPre[getCol()][getHgt() - 1]) || Util.constainsCharacter(getCellContent_atPre.get(getCol()).get(getHgt() - 1));
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
            Contractor.defaultContractor().postconditionError("PlayerContract", "digLeft", "Check if digLeft is possible");
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
        //               \and \not \Exists Character c \in getEnvi().getCellContent(getCol(), getHgt() - 1)@pre

        Set<Cell> LAD_HDR = new HashSet<>();
        LAD_HDR.add(Cell.LAD);
        LAD_HDR.add(Cell.HDR);


        Set<Cell> EMP_HDR_HOL = new HashSet<>();
        EMP_HDR_HOL.add(Cell.EMP);
        EMP_HDR_HOL.add(Cell.HDR);
        EMP_HDR_HOL.add(Cell.HOL);

        

        boolean cond1 = !LAD_HDR.contains(getCellNature_atPre[getCol_atPre][getHgt_atPre]);
        boolean cond2 = EMP_HDR_HOL.contains(getCellNature_atPre[getCol_atPre][getHgt_atPre - 1]);
        boolean cond3 = !Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre));
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
            Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "getHgt() == getHgt()@pre");
        }


        //\post getCol()@pre == 0 \impl getCol() == getCol()@pre
        if(!(Checker.implication(getCol_atPre == 0, getCol() == getCol_atPre))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "getCol()@pre == 0 \\impl getCol() == getCol()@pre");
        }


        //\post (getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT}) \impl getCol() == getCol()@pre
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.HOL);
        if(!(Checker.implication(MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre)), getCol() == getCol_atPre))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "(getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \\in {MTL, PLT}) \\impl getCol() == getCol()@pre");
        }


        //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD}
        //      \and \not \Exists Character c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
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
        boolean cond3 = !Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "\\not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \\in {LAD, HDR}) \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {PLT, MTL, LAD} \\and \\not \\Exists Character c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getCol() == getCol()@pre()");
        }


        
        //\post \Exists Character c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \impl getCol() == getCol()@pre
        if(!(Checker.implication(Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre)), getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "\\Exists Character c \\in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \\impl getCol() == getCol()@pre");
        }


        //\post getCol()@pre != 0
        //      \and \not getEnvi().getCellNature()(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT}
        //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
        //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD}
        //           \or \Exists Character c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
        //      \and \not \Exists Character c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre)
        //      \impl getCol() == getCol()@pre - 1
        boolean condAND1 = getCol_atPre != 0;
        boolean condAND2 = !MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre));
        boolean condOR1 = (LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
        boolean condOR2 = (PLT_MTL_LAD.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
        boolean condOR3 = Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        boolean condAND3 = !Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre));
        if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre - 1)){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "Check if goLeft is possible with all conditions");
        }


        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "const getEnvi()");
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
            Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "getHgt() == getHgt()@pre");
        }

        //\post getCol()@pre == getEnvi().getWidth() - 1 \impl getCol() == getCol()@pre
        if(!(Checker.implication(getCol_atPre == getEnvi().getWidth() - 1, getCol() == getCol_atPre))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "getCol()@pre == getEnvi().getWidth() - 1 \\impl getCol() == getCol()@pre");
        }
        
        //\post (getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT}) \impl getCol() == getCol()@pre
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.HOL);
        if(!(Checker.implication(MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre)), getCol() == getCol_atPre))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "(getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \\in {MTL, PLT}) \\impl getCol() == getCol()@pre");
        }
        
        
        //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD}
        //      \and \not \Exists Character c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)
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
        boolean cond3 = !Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "\\not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \\in {LAD, HDR}) \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {PLT, MTL, LAD} \\and \\not \\Exists Character c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getCol() == getCol()@pre()");
        }
        
        //\post \Exists Character c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \impl getCol() == getCol()@pre
        if(!(Checker.implication(Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre)), getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "\\Exists Character c \\in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \\impl getCol() == getCol()@pre");
        }
        
        
        //\post getCol()@pre != getEnvi().getWidth() - 1
        //      \and \not getEnvi().getCellNature()(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT}
        //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
        //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD}
        //           \or \Exists Character c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
        //      \and \not \Exists Character c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre)
        //      \impl getCol() == getCol()@pre + 1
        boolean condAND1 = getCol_atPre != getEnvi().getWidth() - 1;
        boolean condAND2 = !MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre));
        boolean condOR1 = (LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
        boolean condOR2 = (PLT_MTL_LAD.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
        boolean condOR3 = Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        boolean condAND3 = !Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre));
        if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre + 1)){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "Check if goRight is possible with all conditions");
        }
        
        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "const getEnvi()");
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
            Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "getCol() == getCol()@pre");
        }

        //\post getHgt()@pre == getEnvi().getHeight() - 1 \impl getHgt() == getHgt()@pre
        if(!Checker.implication(getHgt_atPre == getEnvi().getHeight() - 1, getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "getHgt()@pre == getEnvi().getHeight() - 1 \\impl getHgt() == getHgt()@pre");
        }

        //\post (getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \in {MTL, PLT} \impl getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.HOL);
        if(!(Checker.implication(MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre + 1)), getHgt() == getHgt_atPre))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "(getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \\in {MTL, PLT} \\impl getHgt() == getHgt()@pre");
        }

        //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \impl getHgt() == getHgt()@pre
        if(!Checker.implication(getEnvi().getCellNature(getCol_atPre, getHgt_atPre) != Cell.LAD, getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \\impl getHgt() == getHgt()@pre");
        }

        //\post \Exists Character c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \impl getHgt() == getHgt()@pre
        if(!Checker.implication(Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1)), getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "\\Exists Character c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \\impl getHgt() == getHgt()@pre");
        }

        //\post getHgt()@pre != getEnvi().getHeight() - 1 
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \in {MTL, PLT}
        //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD
        //      \and \not \Exists Character c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1)
        //      \impl getHgt() == getHgt()@pre + 1
        boolean cond1 = getHgt_atPre != getEnvi().getHeight() - 1;
        boolean cond2 = !MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre + 1));
        boolean cond3 = getEnvi().getCellNature(getCol_atPre, getHgt_atPre) == Cell.LAD;
        boolean cond4 = !Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1));
        if(!Checker.implication(cond1 && cond2 && cond3 && cond4, getHgt() == getHgt_atPre + 1)){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "getHgt()@pre != getEnvi().getHeight() - 1  \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \\in {MTL, PLT} \\and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD \\and \\not \\Exists Character c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \\impl getHgt() == getHgt()@pre + 1");
        }

        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "const getEnvi()");
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
            Contractor.defaultContractor().postconditionError("CharacterContract", "goDown", "getCol() == getCol()@pre");
        }
        
        //\post getHgt()@pre == 0 \impl getHgt() == getHgt()@pre
        if(!(Checker.implication(getHgt_atPre == 0, getHgt() == getHgt_atPre))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goDown", "getHgt()@pre == 0 \\impl getHgt() == getHgt()@pre");
        }
        
        //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT} \impl getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT = new HashSet<>();
        MTL_PLT.add(Cell.MTL);
        MTL_PLT.add(Cell.HOL);
        if(!(Checker.implication(MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)), getHgt() == getHgt_atPre))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goDown", "getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {MTL, PLT} \\impl getHgt() == getHgt()@pre");
        }
        
        //\post \Exists Character c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \impl getHgt() == getHgt()@pre
        if(!(Checker.implication(Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1)), getHgt() == getHgt_atPre))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goDown", "\\Exists Character c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getHgt() == getHgt()@pre");
        }
        
        //\post getHgt()@pre != 0
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT}
        //      \and \not \Exists Character c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) 
        //      \impl getHgt() == getHgt()@pre - 1
        boolean cond1 = getHgt_atPre != 0;
        boolean cond2 = !MTL_PLT.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1));
        boolean cond3 = !Util.constainsCharacter(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getHgt() == getCol_atPre - 1))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goDown", "getHgt()@pre != 0 \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {MTL, PLT} \\and \\not \\Exists Character c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)  \\impl getHgt() == getHgt()@pre - 1");
        }
        
        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goDown", "const getEnvi()");
        }
        
    }
    
    
    private Player duplicatePlayer(Player d){
        Player res = new PlayerImpl();

        Engine resEngine = new EngineImpl(res, new EnvironmentImpl());
        EditableScreen resES = new EditableScreenImpl();
        resES.init(d.getEnvi().getHeight(), d.getEnvi().getWidth());
        
        for(int i=0; i<resES.getHeight(); i++){
            for(int j=0; j<resES.getWidth(); j++){
                resES.setNature(j, i, d.getEnvi().getCellNature(j, i));
            }
        }
        
        resEngine.init(resES, d.getCol(), d.getHgt(), null, null);
        res.init(d.getCol(), d.getHgt(), resEngine);
        return res;
    }

    private boolean isEqual(PlayerContract pc){
        //TODO define this method
        return true;
    }
    
}