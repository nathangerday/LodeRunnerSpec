package contracts;

import java.util.HashSet;
import java.util.Set;

import data.Cell;
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
        if(!(getEnvi().getCellContent(x, y).contains(this))){
            Contractor.defaultContractor().postconditionError("CharacterContract", "init", "this \\in getEnvi().getCellContent(x, y)");
        }
        
    }

    @Override
    public void goLeft() {
        //captures

        //inv pre
        checkInvariant();
        
        super.goLeft();

        //inv post
        checkInvariant();

        
        
    }

    @Override
    public void goRight() {
        //captures

        //inv pre
        checkInvariant();
        
        super.goRight();

        //inv post
        checkInvariant();

        
        
    }

    @Override
    public void goUp() {
        //captures

        //inv pre
        checkInvariant();
        
        super.goUp();

        //inv post
        checkInvariant();

        
        
    }

    @Override
    public void goDown() {
        //captures

        //inv pre
        checkInvariant();
        
        super.goDown();

        //inv post
        checkInvariant();

        
        
    }
}