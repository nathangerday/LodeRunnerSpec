package contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Cell;
import data.Entity;
import decorators.EnvironmentDecorator;
import services.Character;
import services.EditableScreen;
import services.Environment;
import utils.Util;

public class EnvironmentContract extends EnvironmentDecorator{

    public EnvironmentContract(Environment delegate){
        super(delegate);
    }

    public void checkInvariant(){
        
    }

    @Override
    public Cell getCellNature(int x, int y) {
        //\pre 0 <= y 
        if(!(0 <= y )){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "getCellNature", "0 <= y ");
        }

        //\pre y < getHeight()
        if(!(y < getHeight())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "getCellNature", "y < getHeight()");
        }

        //\pre 0 <= x
        if(!(0 <= x)){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "getCellNature", "0 <= x");
        }

        //\pre x < getWidth()
        if(!(x < getWidth())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "getCellNature", "x < getWidth()");
        }

        return super.getCellNature(x, y);
        
    }

    @Override
    public Set<Entity> getCellContent(int x, int y) {
        //\pre 0 <= y 
        if(!(0 <= y )){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "getCellContent", "0 <= y ");
        }

        //\pre y < getHeight()
        if(!(y < getHeight())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "getCellContent", "y < getHeight()");
        }

        //\pre 0 <= x
        if(!(0 <= x)){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "getCellContent", "0 <= x");
        }

        //\pre x < getWidth()
        if(!(x < getWidth())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "getCellContent", "x < getWidth()");
        }
        
        return super.getCellContent(x, y);
    }

    @Override
    public void dig(int x, int y) {
        //\pre getCellNature(x,y) == Cell.PLT
        if(!(getCellNature(x,y) == Cell.PLT)){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "dig", "getCellNature(x,y) == Cell.PLT");
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
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "dig", "getCellNature(x, y) == Cell.HOL");
        }

        //\post \Forall u in [0, getWidth() - 1]
        //          \Forall v in [0, getHeight() - 1]
        //              u != x || v != y \impl getCellNature(u,v) == getCellNature(u, v)@pre
        for(int u = 0; u < getWidth(); u++){
            for(int v = 0; v < getHeight(); v++){
                if(!Checker.implication(u != x || v != y, getCellNature(u, v) == getCellNature_atPre[u][v])){
                    Contractor.defaultContractor().postconditionError("EnvironmentContract", "dig", "\\Forall u in [0, getWidth() - 1] \\Forall v in [0, getHeight() - 1] u != x || v != y \\impl getCellNature(u,v) == getCellNature(u, v)@pre");
                }
            }
        }

        //\post const getHeight()
        if(!(getHeight_atPre == getHeight())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "dig", "const getHeight()");
        }

        //\post const getWidth()
        if(!(getWidth_atPre == getWidth())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "dig", "const getWidth()");
        }

    }

    @Override
    public void fill(int x, int y) {
        //\pre getCellNature(x,y) == Cell.HOL
        if(!(getCellNature(x,y) == Cell.HOL)){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "fill", "getCellNature(x,y) == Cell.HOL");
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
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "fill", "getCellNature(x, y) == Cell.PLT");
        }

        //\post \Forall u in [0, getWidth() - 1]
        //          \Forall v in [0, getHeight() - 1]
        //              u != x || v != y \impl getCellNature(u,v) == getCellNature(u, v)@pre
        for(int u = 0; u < getWidth(); u++){
            for(int v = 0; v < getHeight(); v++){
                if(!Checker.implication(u != x || v != y, getCellNature(u, v) == getCellNature_atPre[u][v])){
                    Contractor.defaultContractor().postconditionError("EnvironmentContract", "fill", "\\Forall u in [0, getWidth() - 1] \\Forall v in [0, getHeight() - 1] u != x || v != y \\impl getCellNature(u,v) == getCellNature(u, v)@pre");
                }
            }
        }

        //\post const getHeight()
        if(!(getHeight_atPre == getHeight())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "fill", "const getHeight()");
        }

        //\post const getWidth()
        if(!(getWidth_atPre == getWidth())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "fill", "const getWidth()");
        }
    }


    @Override
    public void addToCellContent(int x, int y, Entity e) {
        //\pre 0 <= y 
        if(!(0 <= y )){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "addToCellContent", "0 <= y ");
        }

        //\pre y < getHeight()
        if(!(y < getHeight())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "addToCellContent", "y < getHeight()");
        }

        //\pre 0 <= x
        if(!(0 <= x)){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "addToCellContent", "0 <= x");
        }

        //\pre x < getWidth()
        if(!(x < getWidth())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "addToCellContent", "x < getWidth()");
        }

        //\pre e != null
        if(!(e != null)){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "addToCellContent", "e != null");
        }

        //\pre (\Exists Character c \in getCellContent(x, y)) \impl (\not (e \is Character))
        if(!(Checker.implication(Util.constainsCharacter(getCellContent(x, y)), !(e instanceof Character)))){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "addToCellContent", "(\\Exists Character c \\in getCellContent(x, y)) \\impl (\\not (e \\is Character))");
        }

        //captures
        Cell[][] getCellNature_atPre = new Cell[getWidth()][getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getHeight(); v++){
                getCellNature_atPre[u][v] = getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getCellContent(u, v)));
            }
        }
        int getHeight_atPre = getHeight();
        int getWidth_atPre = getWidth();

        //inv pre
        checkInvariant();

        super.addToCellContent(x, y, e);

        //inv post
        checkInvariant();


        //\post getCellContent(x, y) == getCellContent(x, y)@pre \Union {e}
        Set<Entity> getCellContent_X_Y_atPre_Union_e = new HashSet<>(getCellContent_atPre.get(x).get(y));
        getCellContent_X_Y_atPre_Union_e.add(e);
        if(!(getCellContent(x, y).equals(getCellContent_X_Y_atPre_Union_e))){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "addToCellContent", "getCellContent(x, y) == getCellContent(x, y)@pre \\Union {e}");
        }

        //\post \Forall i in [0, getWidth() - 1]
        //          \Forall j in [0, getHeight() - 1]
        //              i != x || j != y \impl getCellContent(i, j) == getCellContent(i, j)@pre
        //              getCellNature(i, j) == getCellNature(i, j)@pre
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                if(!Checker.implication(i != x || j != y, getCellContent(i, j).equals(getCellContent_atPre.get(x).get(y)))){
                    Contractor.defaultContractor().postconditionError("EnvironmentContract", "addToCellContent", "\\Forall i in [0, getWidth() - 1] \\Forall j in [0, getHeight() - 1] i != x || j != y \\impl getCellContent(i,j) == getCellContent(i, j)@pre");
                }
                if(!(getCellNature(i, j) == getCellNature_atPre[i][j])){
                    Contractor.defaultContractor().postconditionError("EnvironmentContract", "addToCellContent", "\\Forall i in [0, getWidth() - 1] \\Forall j in [0, getHeight() - 1] getCellNature(i, j) == getCellNature(i, j)@pre");
                }
            }
        }

        //\post const getHeight()
        if(!(getHeight_atPre == getHeight())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "addToCellContent", "const getHeight()");
        }

        //\post const getWidth()
        if(!(getWidth_atPre == getWidth())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "addToCellContent", "const getWidth()");
        }
        
    }


    @Override
    public void removeFromCellContent(int x, int y, Entity e) {
        //\pre 0 <= y 
        if(!(0 <= y )){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "removeFromCellContent", "0 <= y ");
        }

        //\pre y < getHeight()
        if(!(y < getHeight())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "removeFromCellContent", "y < getHeight()");
        }

        //\pre 0 <= x
        if(!(0 <= x)){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "removeFromCellContent", "0 <= x");
        }

        //\pre x < getWidth()
        if(!(x < getWidth())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "removeFromCellContent", "x < getWidth()");
        }

        //\pre \Exists e \in getCellContent(x, y)
        if(!(getCellContent(x, y).contains(e))){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "removeFromCellContent", "\\Exists e \\in getCellContent(x, y)");
            
        }

        //captures
        Cell[][] getCellNature_atPre = new Cell[getWidth()][getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getHeight(); v++){
                getCellNature_atPre[u][v] = getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getCellContent(u, v)));
            }
        }
        int getHeight_atPre = getHeight();
        int getWidth_atPre = getWidth();


        //inv pre
        checkInvariant();

        super.removeFromCellContent(x, y, e);

        //inv post
        checkInvariant();

        //\post getCellContent(x, y) == getCellContent(x, y)@pre \ {e}
        Set<Entity> getCellContent_X_Y_atPre_Without_e = new HashSet<>(getCellContent_atPre.get(x).get(y));
        getCellContent_X_Y_atPre_Without_e.remove(e);
        if(!(getCellContent(x, y).equals(getCellContent_X_Y_atPre_Without_e))){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "removeFromCellContent", "getCellContent(x, y) == getCellContent(x, y)@pre \\ {e}");
        }


        //\post \Forall i in [0, getWidth() - 1]
        //          \Forall j in [0, getHeight() - 1]
        //              i != x || j != y \impl getCellContent(i, j) == getCellContent(i, j)@pre
        //              getCellNature(i, j) == getCellNature(i, j)@pre
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                if(!Checker.implication(i != x || j != y, getCellContent(i, j).equals(getCellContent_atPre.get(x).get(y)))){
                    Contractor.defaultContractor().postconditionError("EnvironmentContract", "removeFromCellContent", "\\Forall i in [0, getWidth() - 1] \\Forall j in [0, getHeight() - 1] i != x || j != y \\impl getCellContent(i,j) == getCellContent(i, j)@pre");
                }
                if(!(getCellNature(i, j) == getCellNature_atPre[i][j])){
                    Contractor.defaultContractor().postconditionError("EnvironmentContract", "removeFromCellContent", "\\Forall i in [0, getWidth() - 1] \\Forall j in [0, getHeight() - 1] getCellNature(i, j) == getCellNature(i, j)@pre");
                }
            }
        }
        
        //\post const getHeight()
        if(!(getHeight_atPre == getHeight())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "removeFromCellContent", "const getHeight()");
        }

        //\post const getWidth()
        if(!(getWidth_atPre == getWidth())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "removeFromCellContent", "const getWidth()");
        }
    }

    @Override
    public Character removeCharacter(int x, int y) {
         //\pre 0 <= y 
         if(!(0 <= y )){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "removeCharacter", "0 <= y ");
        }

        //\pre y < getHeight()
        if(!(y < getHeight())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "removeCharacter", "y < getHeight()");
        }

        //\pre 0 <= x
        if(!(0 <= x)){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "removeCharacter", "0 <= x");
        }

        //\pre x < getWidth()
        if(!(x < getWidth())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "removeCharacter", "x < getWidth()");
        }

        //\pre \Exists Character c \in getCellContent(x, y)
        if(!(Util.constainsCharacter(getCellContent(x, y)))){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "removeCharacter", "\\Exists Character c \\in getCellContent(x, y)");
            
        }

        //captures
        Cell[][] getCellNature_atPre = new Cell[getWidth()][getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getHeight(); v++){
                getCellNature_atPre[u][v] = getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getCellContent(u, v)));
            }
        }
        int getHeight_atPre = getHeight();
        int getWidth_atPre = getWidth();


        //inv pre
        checkInvariant();

        Character res = super.removeCharacter(x, y);

        //inv post
        checkInvariant();

        //\post \Exists Character c \in getCellContent(x, y)@pre 
        //          \impl getCellContent(x, y) == getCellContent(x, y)@pre \ {c}
        Set<Entity> getCellContent_X_Y_atPre_Without_c = new HashSet<>(getCellContent_atPre.get(x).get(y));
        Character c = Util.getCharacter(getCellContent_X_Y_atPre_Without_c);
        getCellContent_X_Y_atPre_Without_c.remove(c);
        if(!(getCellContent(x, y).equals(getCellContent_X_Y_atPre_Without_c))){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "removeCharacter", "Exists Character c \\in getCellContent(x, y)@pre \\impl getCellContent(x, y) == getCellContent(x, y)@pre \\ {c}");
        }


        //\post \Forall i in [0, getWidth() - 1]
        //          \Forall j in [0, getHeight() - 1]
        //              i != x || j != y \impl getCellContent(i, j) == getCellContent(i, j)@pre
        //              getCellNature(i, j) == getCellNature(i, j)@pre
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                if(!Checker.implication(i != x || j != y, getCellContent(i, j).equals(getCellContent_atPre.get(x).get(y)))){
                    Contractor.defaultContractor().postconditionError("EnvironmentContract", "removeCharacter", "\\Forall i in [0, getWidth() - 1] \\Forall j in [0, getHeight() - 1] i != x || j != y \\impl getCellContent(i,j) == getCellContent(i, j)@pre");
                }
                if(!(getCellNature(i, j) == getCellNature_atPre[i][j])){
                    Contractor.defaultContractor().postconditionError("EnvironmentContract", "removeCharacter", "\\Forall i in [0, getWidth() - 1] \\Forall j in [0, getHeight() - 1] getCellNature(i, j) == getCellNature(i, j)@pre");
                }
            }
        }

        //\post const getHeight()
        if(!(getHeight_atPre == getHeight())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "removeCharacter", "const getHeight()");
        }

        //\post const getWidth()
        if(!(getWidth_atPre == getWidth())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "removeCharacter", "const getWidth()");
        }
        

        return res;
    }

    @Override
    public void init(EditableScreen screen) {
        //\pre screen != null
        if(!(screen != null)){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "init", "screen != null");
        }

        //\pre screen.isPlayable()
        if(!(screen.isPlayable())){
            Contractor.defaultContractor().preconditionError("EnvironmentContract", "init", "screen.isPlayable()");
        }

        super.init(screen);

        //inv post
        checkInvariant();

        //\post getHeight() == screen.getHeight()
        if(!(getHeight() == screen.getHeight())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "init", "getHeight() == screen.getHeight()");
        }

        //\post getWidth() == screen.getWidth()
        if(!(getWidth() == screen.getWidth())){
            Contractor.defaultContractor().postconditionError("EnvironmentContract", "init", "getWidth() == screen.getWidth()");
        }

        //\post \Forall x in [0, getWidth() - 1]
        //          \Forall y in [0, getHeight() - 1]
        //              getCellNature(x, y) == screen.getCellNature(x, y)
        //              getCellContent(x, y) == {}
        for(int x = 0; x < getWidth(); x++){
            for(int y = 0; y < getHeight(); y++){
                if(!(getCellNature(x, y) == screen.getCellNature(x, y))){
                    Contractor.defaultContractor().postconditionError("EnvironmentContract", "init", "\\Forall x in [0, getWidth() - 1] \\Forall y in [0, getHeight() - 1] getCellNature(x, y) == screen.getCellNature(x, y)");
                }
                if(!(getCellContent(x, y).size() == 0)){
                    Contractor.defaultContractor().postconditionError("EnvironmentContract", "init", "\\Forall x in [0, getWidth() - 1] \\Forall y in [0, getHeight() - 1] getCellContent(x, y) == {}");

                }
            }
        }
        
    }
}