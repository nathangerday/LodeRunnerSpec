package contracts;

import data.Cell;
import decorators.ScreenDecorator;
import services.Screen;

public class ScreenContract extends ScreenDecorator {
    
    public ScreenContract(Screen delegate){
        super(delegate);
    }

    public void checkInvariant(){
        //rien a verifier
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public Cell getCellNature(int x, int y) {
        //\pre 0 <= y 
        if(!(0 <= y )){
            Contractor.defaultContractor().preconditionError("ScreenContract", "getCellNature", "0 <= y ");
        }

        //\pre y < getHeight()
        if(!(y < getHeight())){
            Contractor.defaultContractor().preconditionError("ScreenContract", "getCellNature", "y < getHeight()");
        }

        //\pre 0 <= x
        if(!(0 <= x)){
            Contractor.defaultContractor().preconditionError("ScreenContract", "getCellNature", "0 <= x");
        }

        //\pre x < getWidth()
        if(!(x < getWidth())){
            Contractor.defaultContractor().preconditionError("ScreenContract", "getCellNature", "x < getWidth()");
        }

        return super.getCellNature(x, y);
        
    }

    @Override
    public void init(int h, int w) {
        //\pre 0 < h
        if(!(0 < h)){
            Contractor.defaultContractor().preconditionError("ScreenContract", "init", "0 < h");
        }

        //\pre 0 < w
        if(!(0 < w)){
            Contractor.defaultContractor().preconditionError("ScreenContract", "init", "0 < w");
        }
        
        super.init(h, w);

        //inv post
        checkInvariant();
        

        //\post getHeight() == h
        if(!(getHeight() == h)){
            Contractor.defaultContractor().postconditionError("ScreenContract", "init", "getHeight() == h");
        }

        //\post getWidth() == w
        if(!(getWidth() == w)){
            Contractor.defaultContractor().postconditionError("ScreenContract", "init", "getWidth() == w");
        }

        //\post \Forall x in [0, getWidth() - 1]
        //          \Forall y in [0, getHeight() - 1]
        //              getCellNature(x, y) == Cell.EMP
        for(int x = 0; x < getWidth(); x++){
            for(int y = 0; y < getHeight(); y++){
                if(!(getCellNature(x, y) == Cell.EMP)){
                    Contractor.defaultContractor().postconditionError("ScreenContract", "init", "getCellNature(x, y) == Cell.EMP");
                }
            }
        }
    }

    @Override
    public void dig(int x, int y) {
        //\pre getCellNature(x,y) == Cell.PLT
        if(!(getCellNature(x,y) == Cell.PLT)){
            Contractor.defaultContractor().preconditionError("ScreenConctract", "dig", "getCellNature(x,y) == Cell.PLT");
        }

        //captures
        Cell[][] getCellNature_atPre = new Cell[getWidth()][getHeight()];
        for(int u = 0; u < getWidth(); u++){
            for(int v = 0; v < getHeight(); v++){
                getCellNature_atPre[u][v] = getCellNature(u, v);
            }
        }
        int getHeight_atPre = getHeight();
        int getWidth_atPre = getWidth();

        //inv pre
        checkInvariant();
        
        super.dig(x, y);

        //inv post
        checkInvariant();


        //\post getCellNature(x, y) == Cell.HOL
        if(!(getCellNature(x, y) == Cell.HOL)){
            Contractor.defaultContractor().postconditionError("ScreenContract", "dig", "getCellNature(x, y) == Cell.HOL");
        }

        //\post \Forall u in [0, getWidth() - 1]
        //          \Forall v in [0, getHeight() - 1]
        //              u != x || v != y \impl getCellNature(u,v) == getCellNature(u, v)@pre
        for(int u = 0; u < getWidth(); u++){
            for(int v = 0; v < getHeight(); v++){
                if(!Checker.implication(u != x || v != y, getCellNature(u, v) == getCellNature_atPre[u][v])){
                    Contractor.defaultContractor().postconditionError("ScreenContract", "dig", "\\Forall u in [0, getWidth() - 1] \\Forall v in [0, getHeight() - 1] u != x || v != y \\impl getCellNature(u,v) == getCellNature(u, v)@pre");
                }
            }
        }

        //\post const getHeight()
        if(!(getHeight_atPre == getHeight())){
            Contractor.defaultContractor().postconditionError("ScreenContract", "dig", "const getHeight()");
        }

        //\post const getWidth()
        if(!(getWidth_atPre == getWidth())){
            Contractor.defaultContractor().postconditionError("ScreenContract", "dig", "const getWidth()");
        }

    }

    @Override
    public void fill(int x, int y) {
        //\pre getCellNature(x,y) == Cell.HOL
        if(!(getCellNature(x,y) == Cell.HOL)){
            Contractor.defaultContractor().preconditionError("ScreenConctract", "fill", "getCellNature(x,y) == Cell.HOL");
        }

        //captures
        Cell[][] getCellNature_atPre = new Cell[getWidth()][getHeight()];
        for(int u = 0; u < getWidth(); u++){
            for(int v = 0; v < getHeight(); v++){
                getCellNature_atPre[u][v] = getCellNature(u, v);
            }
        }
        int getHeight_atPre = getHeight();
        int getWidth_atPre = getWidth();

        //inv pre
        checkInvariant();

        super.fill(x, y);

        //inv post
        checkInvariant();


        //\post getCellNature(x, y) == Cell.PLT
        if(!(getCellNature(x, y) == Cell.PLT)){
            Contractor.defaultContractor().postconditionError("ScreenContract", "fill", "getCellNature(x, y) == Cell.PLT");
        }

        //\post \Forall u in [0, getWidth() - 1]
        //          \Forall v in [0, getHeight() - 1]
        //              u != x || v != y \impl getCellNature(u,v) == getCellNature(u, v)@pre
        for(int u = 0; u < getWidth(); u++){
            for(int v = 0; v < getHeight(); v++){
                if(!Checker.implication(u != x || v != y, getCellNature(u, v) == getCellNature_atPre[u][v])){
                    Contractor.defaultContractor().postconditionError("ScreenContract", "fill", "\\Forall u in [0, getWidth() - 1] \\Forall v in [0, getHeight() - 1] u != x || v != y \\impl getCellNature(u,v) == getCellNature(u, v)@pre");
                }
            }
        }

        //\post const getHeight()
        if(!(getHeight_atPre == getHeight())){
            Contractor.defaultContractor().postconditionError("ScreenContract", "fill", "const getHeight()");
        }

        //\post const getWidth()
        if(!(getWidth_atPre == getWidth())){
            Contractor.defaultContractor().postconditionError("ScreenContract", "fill", "const getWidth()");
        }
    }
    
}