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
        //\inv getEnvi().getCellNature(getCol(), getHgt()) \in {EMP, HOL, LAD, HDR}
        Set<Cell> EMP_HOL_LAD_HDR = new HashSet<>();
        EMP_HOL_LAD_HDR.add(Cell.EMP);
        EMP_HOL_LAD_HDR.add(Cell.HOL);
        EMP_HOL_LAD_HDR.add(Cell.LAD);
        EMP_HOL_LAD_HDR.add(Cell.HDR);
        if(!(EMP_HOL_LAD_HDR.contains(getEnvi().getCellNature(getCol(), getHgt())))){
            Contractor.defaultContractor().invariantError("CharacterContract", "getEnvi().getCellNature(getCol(), getHgt()) \\in {EMP, HOL, LAD, HDR}");
        }


        //\inv (\Exists Character c \in getEnvi().getCellContent(getCol(), getHgt()))
        //          \impl c == this
        if(!Checker.implication(Util.constainsCharacter(getEnvi().getCellContent(getCol(), getHgt())), Util.getCharacter(getEnvi().getCellContent(getCol(), getHgt())).equals(this))){
            Contractor.defaultContractor().invariantError("CharacterContract", "(\\Exists Character c \\in getEnvi().getCellContent(getCol(), getHgt())) \\impl c == this");
        }


    }
    

    @Override
    public Environment getEnvi() {
        Environment res = super.getEnvi();
        return res;
    }

    @Override
    public int getHgt() {
        int res = super.getHgt();
        return res;
    }

    @Override
    public int getCol() {
        int res = super.getCol();
        return res;
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

        //\pre s.getCellNature(x, y) == EMP
        if(!(s.getCellNature(x, y) == Cell.EMP)){
            Contractor.defaultContractor().preconditionError("CharacterContract", "init", "s.getCellNature(x, y) == EMP");
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
        if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre)){
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
        if(!Checker.implication(condAND1 && condAND2 && (condOR1 || condOR2 || condOR3) && condAND3, getCol() == getCol_atPre)){
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
}