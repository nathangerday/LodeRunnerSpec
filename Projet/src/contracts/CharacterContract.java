package contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Cell;
import data.Entity;
import decorators.CharacterDecorator;
import services.Character;
import services.Environment;
import utils.Util;

public class CharacterContract extends CharacterDecorator {
    
    public CharacterContract(Character delegate){
        super(delegate);
    }

    public void checkInvariant(){
        //\inv getEnvi().getCellNature(getCol(), getHgt()) \in {EMP, HOL, LAD, HDR, NPL, NGU}
        Set<Cell> EMP_HOL_LAD_HDR_NPL_NGU = new HashSet<>();
        EMP_HOL_LAD_HDR_NPL_NGU.add(Cell.EMP);
        EMP_HOL_LAD_HDR_NPL_NGU.add(Cell.HOL);
        EMP_HOL_LAD_HDR_NPL_NGU.add(Cell.LAD);
        EMP_HOL_LAD_HDR_NPL_NGU.add(Cell.HDR);
        EMP_HOL_LAD_HDR_NPL_NGU.add(Cell.NPL);
        EMP_HOL_LAD_HDR_NPL_NGU.add(Cell.NGU);
        if(!(EMP_HOL_LAD_HDR_NPL_NGU.contains(getEnvi().getCellNature(getCol(), getHgt())))){
            Contractor.defaultContractor().invariantError("CharacterContract", "getEnvi().getCellNature(getCol(), getHgt()) \\in {EMP, HOL, LAD, HDR, NPL, NGU}");
        }

        //TODO Change & Move go guard inv
        // //\inv (\Exists Character c \in getEnvi().getCellContent(getCol(), getHgt()))
        // //          \impl c == this
        // if(!Checker.implication(Util.constainsCharacter(getEnvi().getCellContent(getCol(), getHgt())), Util.getCharacter(getEnvi().getCellContent(getCol(), getHgt())).equals(getDelegate()))){
        //     Contractor.defaultContractor().invariantError("CharacterContract", "(\\Exists Character c \\in getEnvi().getCellContent(getCol(), getHgt())) \\impl c == this");
        // }


    }


    @Override
    public void init(Environment s, int x, int y) {
        //\pre s != null
        if(!(s != null)){
            Contractor.defaultContractor().preconditionError("CharacterContract", "init", "s != null");
        }

        //\pre x >= 0
        if(!(x >= 0)){
            Contractor.defaultContractor().preconditionError("CharacterContract", "init", "x >= 0");
        }

        //\pre x < s.getWidth()
        if(!(x < s.getWidth())){
            Contractor.defaultContractor().preconditionError("CharacterContract", "init", "x < s.getWidth()");
        }

        //\pre y >= 0
        if(!(y >= 0)){
            Contractor.defaultContractor().preconditionError("CharacterContract", "init", "y >= 0");
        }

        //\pre y < s.getHeight()
        if(!(y < s.getHeight())){
            Contractor.defaultContractor().preconditionError("CharacterContract", "init", "y < s.getHeight()");
        }

        //\pre \not s.getCellNature(x, y) \in {MTL, PLT, DOR}
        Set<Cell> MTL_PLT_DOR = new HashSet<>();
        MTL_PLT_DOR.add(Cell.MTL);
        MTL_PLT_DOR.add(Cell.PLT);
        MTL_PLT_DOR.add(Cell.DOR);
        if(!(!MTL_PLT_DOR.contains(s.getCellNature(x, y)))){
            Contractor.defaultContractor().preconditionError("CharacterContract", "init", "\\not s.getCellNature(x, y) \\in {MTL, PLT, DOR}");
        }

        super.init(s, x, y);

        //inv post
        checkInvariant();

        
        //\post getHgt() == y
        if(!(getHgt() == y)){
            Contractor.defaultContractor().postconditionError("CharacterContract", "init", "getHgt() == y");
        }
        //\post getCol() == x
        if(!(getCol() == x)){
            Contractor.defaultContractor().postconditionError("CharacterContract", "init", "getCol() == x");
        }
        //\post getEnvi() == s
        if(!(getEnvi() == s)){
            Contractor.defaultContractor().postconditionError("CharacterContract", "init", "getEnvi() == s");
        }
        //\post this \in getEnvi().getCellContent(x, y)
        if(!(getEnvi().getCellContent(x, y).contains(getDelegate()))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "init", "this \\in getEnvi().getCellContent(x, y)");
        }
        
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


        //\post (getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR}) \impl getCol() == getCol()@pre
        Set<Cell> MTL_PLT_DOR = new HashSet<>();
        MTL_PLT_DOR.add(Cell.MTL);
        MTL_PLT_DOR.add(Cell.PLT);
        MTL_PLT_DOR.add(Cell.DOR);
        if(!(getCol_atPre == 0)){
            if(!(Checker.implication(MTL_PLT_DOR.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre)), getCol() == getCol_atPre))){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "(getEnvi().getCellNature(getCol()@pre - 1, getHgt()@pre) \\in {MTL, PLT, DOR}) \\impl getCol() == getCol()@pre");
            }
        }


        //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR}
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

        boolean cond1 = !(LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
        boolean cond2 = !(PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "\\not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \\in {LAD, HDR}) \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {PLT, MTL, LAD, DOR} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getCol() == getCol()@pre()");
        }


        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \impl getCol() == getCol()@pre
        if(!(getCol_atPre == 0)){
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre)), getCol_atPre == getCol()))){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre) \\impl getCol() == getCol()@pre");
            }
        }


        //\post getCol()@pre != 0
        //      \and \not getEnvi().getCellNature()(getCol()@pre - 1, getHgt()@pre) \in {MTL, PLT, DOR}
        //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
        //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR}
        //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre - 1, getHgt()@pre)
        //      \impl getCol() == getCol()@pre - 1
        if(!(getCol_atPre == 0)){
            boolean condAND1 = getCol_atPre != 0;
            boolean condAND2 = !MTL_PLT_DOR.contains(getEnvi().getCellNature(getCol_atPre - 1, getHgt_atPre));
            boolean condOR1 = (LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
            boolean condOR2 = (PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
            boolean condOR3 = Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            boolean condAND3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre - 1).get(getHgt_atPre));
            if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre - 1)){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goLeft", "Check if goLeft worked correctly with all conditions");
            }
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
        
        //\post (getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR}) \impl getCol() == getCol()@pre
        Set<Cell> MTL_PLT_DOR = new HashSet<>();
        MTL_PLT_DOR.add(Cell.MTL);
        MTL_PLT_DOR.add(Cell.PLT);
        MTL_PLT_DOR.add(Cell.DOR);
        if(getCol_atPre != getEnvi().getWidth() - 1){
            if(!(Checker.implication(MTL_PLT_DOR.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre)), getCol() == getCol_atPre))){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "(getEnvi().getCellNature(getCol()@pre + 1, getHgt()@pre) \\in {MTL, PLT, DOR}) \\impl getCol() == getCol()@pre");
            }
        }
        
        //\post \not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR})
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR}
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

        boolean cond1 = !(LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
        boolean cond2 = !(PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
        boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
        if(!(Checker.implication(cond1 && cond2 && cond3, getCol_atPre == getCol()))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "\\not (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \\in {LAD, HDR}) \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {PLT, MTL, LAD, DOR} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getCol() == getCol()@pre()");
        }
        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \impl getCol() == getCol()@pre
        
        if(getCol_atPre != getEnvi().getWidth() - 1){        
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre)), getCol_atPre == getCol()))){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre) \\impl getCol() == getCol()@pre");
            }
        }
        
        
        //\post getCol()@pre != getEnvi().getWidth() - 1
        //      \and \not getEnvi().getCellNature()(getCol()@pre + 1, getHgt()@pre) \in {MTL, PLT, DOR}
        //      \and (getEnvi().getCellNature(getCol()@pre, getHgt()@pre) \in {LAD, HDR} 
        //           \or getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {PLT, MTL, LAD, DOR}
        //           \or \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1))
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre + 1, getHgt()@pre)
        //      \impl getCol() == getCol()@pre + 1
        if(getCol_atPre != getEnvi().getWidth() - 1){        
            boolean condAND1 = getCol_atPre != getEnvi().getWidth() - 1;
            boolean condAND2 = !MTL_PLT_DOR.contains(getEnvi().getCellNature(getCol_atPre + 1, getHgt_atPre));
            boolean condOR1 = (LAD_HDR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre)));
            boolean condOR2 = (PLT_MTL_LAD_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)));
            boolean condOR3 = Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            boolean condAND3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre + 1).get(getHgt_atPre));
            if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre + 1)){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goRight", "Check if goRight worked correctly with all conditions");
            }
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

        //\post (getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \in {MTL, PLT, DOR} \impl getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT_DOR = new HashSet<>();
        MTL_PLT_DOR.add(Cell.MTL);
        MTL_PLT_DOR.add(Cell.PLT);
        MTL_PLT_DOR.add(Cell.DOR);
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            if(!(Checker.implication(MTL_PLT_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre + 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "(getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1) \\in {MTL, PLT, DOR} \\impl getHgt() == getHgt()@pre");
            }
        }
        //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \impl getHgt() == getHgt()@pre
        if(!Checker.implication(getEnvi().getCellNature(getCol_atPre, getHgt_atPre) != Cell.LAD, getHgt() == getHgt_atPre)){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "getEnvi().getCellNature(getCol()@pre, getHgt()@pre) != LAD \\impl getHgt() == getHgt()@pre");
        }

        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \impl getHgt() == getHgt()@pre
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            if(!Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1)), getHgt() == getHgt_atPre)){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \\impl getHgt() == getHgt()@pre");
            }
        }

        //\post getHgt()@pre != getEnvi().getHeight() - 1 
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \in {MTL, PLT, DOR}
        //      \and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1)
        //      \impl getHgt() == getHgt()@pre + 1
        if(getHgt_atPre != getEnvi().getHeight() - 1){
            boolean cond1 = getHgt_atPre != getEnvi().getHeight() - 1;
            boolean cond2 = !MTL_PLT_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre + 1));
            boolean cond3 = getEnvi().getCellNature(getCol_atPre, getHgt_atPre) == Cell.LAD;
            boolean cond4 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre + 1));
            if(!Checker.implication(cond1 && cond2 && cond3 && cond4, getHgt() == getHgt_atPre + 1)){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goUp", "getHgt()@pre != getEnvi().getHeight() - 1  \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre + 1 \\in {MTL, PLT, DOR} \\and getEnvi().getCellNature(getCol()@pre, getHgt()@pre) == LAD \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre + 1) \\impl getHgt() == getHgt()@pre + 1");
            }
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
        
        //\post getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR} \impl getHgt() == getHgt()@pre
        Set<Cell> MTL_PLT_DOR = new HashSet<>();
        MTL_PLT_DOR.add(Cell.MTL);
        MTL_PLT_DOR.add(Cell.PLT);
        MTL_PLT_DOR.add(Cell.DOR);
        if(getHgt_atPre != 0){
            if(!(Checker.implication(MTL_PLT_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goDown", "getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {MTL, PLT, DOR} \\impl getHgt() == getHgt()@pre");
            }
        }
        
        //\post \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \impl getHgt() == getHgt()@pre
        if(getHgt_atPre != 0){
            if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1)), getHgt() == getHgt_atPre))){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goDown", "\\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) \\impl getHgt() == getHgt()@pre");
            }
        }
        
        //\post getHgt()@pre != 0
        //      \and \not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \in {MTL, PLT, DOR}
        //      \and \not \Exists Guard c \in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1) 
        //      \impl getHgt() == getHgt()@pre - 1
        if(getHgt_atPre != 0){
            boolean cond1 = getHgt_atPre != 0;
            boolean cond2 = !MTL_PLT_DOR.contains(getEnvi().getCellNature(getCol_atPre, getHgt_atPre - 1));
            boolean cond3 = !Util.containsGuard(getCellContent_atPre.get(getCol_atPre).get(getHgt_atPre - 1));
            if(!(Checker.implication(cond1 && cond2 && cond3, getHgt() == getHgt_atPre - 1))){
                Contractor.defaultContractor().postconditionError("CharacterContract", "goDown", "getHgt()@pre != 0 \\and \\not getEnvi().getCellNature(getCol()@pre, getHgt()@pre - 1) \\in {MTL, PLT, DOR} \\and \\not \\Exists Guard c \\in getEnvi().getCellContent(getCol()@pre, getHgt()@pre - 1)  \\impl getHgt() == getHgt()@pre - 1");
            }
        }

        //\post const getEnvi()
        if(!(getEnvi_atPre == getEnvi())){
            Contractor.defaultContractor().postconditionError("CharacterContract", "goDown", "const getEnvi()");
        }
        
    }
}