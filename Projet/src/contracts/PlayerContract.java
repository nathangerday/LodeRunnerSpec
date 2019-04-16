package contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Cell;
import data.Entity;
import decorators.PlayerDecorator;
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

    @Override
    public Engine getEngine() {
        Engine res = super.getEngine();
        return res;
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
        if(!(getEnvi().getCellContent(x, y).contains(this))){
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

        //inv pre
        checkInvariant();

        super.step();

        //inv post
        checkInvariant();

        //\post falling \impl step() == this@pre.goDown()


        //\post (\not falling) \and getNextCommand()@pre == MOVEL \impl step() == this@pre.goLeft()


        //\post (\not falling) \and getNextCommand()@pre == MOVER \impl step() == this@pre.goRight()


        //\post (\not falling) \and getNextCommand()@pre == MOVED \impl step() == this@pre.goDown()


        //\post (\not falling) \and getNextCommand()@pre == MOVEU \impl step() == this@pre.goUp()


        //\post (\not falling) \and getNextCommand()@pre == DIGL \impl step() == this@pre.digLeft()


        //\post (\not falling) \and getNextCommand()@pre == DIGR \impl step() == this@pre.digRight()


        //\post (\not falling) \and getNextCommand()@pre == NONE \impl this == this@pre


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
    
    
}