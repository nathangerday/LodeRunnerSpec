package contracts;

import java.util.List;

import data.Coord;
import data.CoordGuard;
import data.CoordItem;
import data.LevelSetup;
import decorators.ScreenManagerDecorator;
import services.EditableScreen;
import services.ScreenManager;

public class ScreenManagerContract extends ScreenManagerDecorator{
    public ScreenManagerContract(ScreenManager delegate){
        super(delegate);
    }

    public void checkInvariant(){

    }

    @Override
    public LevelSetup getLevelSetup(int i) {
        //\pre i < getNbScreen()
        if(!(i < getNbScreen())){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "getLevelSetup", "i < getNbScreen()");
        }

        //\pre i >= 0
        if(!(i >= 0)){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "getLevelSetup", "i >= 0");
        }

        return super.getLevelSetup(i);
    }

    @Override
    public EditableScreen getScreen(int i) {
        //\pre i < getNbScreen()
        if(!(i < getNbScreen())){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "getScreen", "i < getNbScreen()");
        }

        //\pre i >= 0
        if(!(i >= 0)){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "getScreen", "i >= 0");
        }
        
        return super.getScreen(i);
    }

    @Override
    public List<CoordGuard> getGuardsFromScreen(int i) {
        //\pre i < getNbScreen()
        if(!(i < getNbScreen())){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "getGuardsFromScreen", "i < getNbScreen()");
        }

        //\pre i >= 0
        if(!(i >= 0)){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "getGuardsFromScreen", "i >= 0");
        }
        
        
        return super.getGuardsFromScreen(i);
    }

    @Override
    public List<CoordItem> getItemsFromScreen(int i) {
        //\pre i < getNbScreen()
        if(!(i < getNbScreen())){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "getItemsFromScreen", "i < getNbScreen()");
        }

        //\pre i >= 0
        if(!(i >= 0)){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "getItemsFromScreen", "i >= 0");
        }
        
        
        return super.getItemsFromScreen(i);
    }

    @Override
    public Coord getPlayerFromScreen(int i) {
        //\pre i < getNbScreen()
        if(!(i < getNbScreen())){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "getPlayerFromScreen", "i < getNbScreen()");
        }

        //\pre i >= 0
        if(!(i >= 0)){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "getPlayerFromScreen", "i >= 0");
        }
        
        
        return super.getPlayerFromScreen(i);
    }

    @Override
    public void init(){ 
        super.init();

        //inv post
        checkInvariant();

        //\post getNbScreen() == 0
        if(!(getNbScreen() == 0)){
            Contractor.defaultContractor().postconditionError("ScreenManagerContract", "init", "getNbScreen() == 0");
        }


    }

    @Override
    public void addScreen(EditableScreen screen, List<CoordGuard> guards, List<CoordItem> items, Coord player) {
        //\pre screen != null
        if(!(screen != null)){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "addScreen", "screen != null");
        }
        //\pre player != null
        if(!(player != null)){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "addScreen", "player != null");
        }

        //captures
        int getNbScreen_atPre = getNbScreen();
        LevelSetup getLevelSetup_atPre[] = new LevelSetup[getNbScreen()];
        for(int i=0; i<getNbScreen(); i++){
            getLevelSetup_atPre[i] = getLevelSetup(i);
        }

        //inv pre
        checkInvariant();
        
        super.addScreen(screen, guards, items, player);

        //inv post
        checkInvariant();

        //\post getNbScreen() == getNbScreen()@pre + 1
        if(!(getNbScreen() == getNbScreen_atPre + 1)){
            Contractor.defaultContractor().postconditionError("ScreenManagerContract", "addScreen", "getNbScreen() == getNbScreen()@pre + 1");            
        }

        //\post getScreen(getNbScreen()@pre) == screen
        if(!(getScreen(getNbScreen_atPre) == screen)){
            Contractor.defaultContractor().postconditionError("ScreenManagerContract", "addScreen", "getScreen(getNbScreen()@pre) == screen");            
        }


        //\post getPlayerFromScreen(getNbScreen()@pre) == player
        if(!(getPlayerFromScreen(getNbScreen_atPre) == player)){
            Contractor.defaultContractor().postconditionError("ScreenManagerContract", "addScreen", "getPlayerFromScreen(getNbScreen()@pre) == player");            
        }


        //\post guards == null \impl getGuardsFromScreen(getNbScreen()@pre).size == 0
        if(!(Checker.implication(guards == null, getGuardsFromScreen(getNbScreen_atPre).size() == 0))){
            Contractor.defaultContractor().postconditionError("ScreenManagerContract", "addScreen", "guards == null \\impl getGuardsFromScreen(getNbScreen()@pre).size == 0");                
        }


        //\post guards != null \impl getGuardsFromScreen(getNbScreen()@pre) == guards
        if(!(Checker.implication(guards != null, getGuardsFromScreen(getNbScreen_atPre) == guards))){
            Contractor.defaultContractor().postconditionError("ScreenManagerContract", "addScreen", "guards != null \\impl getGuardsFromScreen(getNbScreen()@pre) == guards");            
        }

        //\post items == null \impl getItemsFromScreen(getNbScreen()@pre).size == 0
        if(!(Checker.implication(items == null, getItemsFromScreen(getNbScreen_atPre).size() == 0))){
            Contractor.defaultContractor().postconditionError("ScreenManagerContract", "addScreen", "items == null \\impl getItemsFromScreen(getNbScreen()@pre).size == 0");                
        }


        //\post items != null \impl getItemsFromScreen(getNbScreen()@pre) == items
        if(!(Checker.implication(items != null, getItemsFromScreen(getNbScreen_atPre) == items))){
            Contractor.defaultContractor().postconditionError("ScreenManagerContract", "addScreen", "items != null \\impl getItemsFromScreen(getNbScreen()@pre) == items");                
        }
        

        //\post \Forall i in [0, getNbScreen()@pre - 1]
        //          getLevelSetup(i) == getLevelSetup(i)@pre
        for(int i=0; i<getNbScreen_atPre ; i++){
            if(!(getLevelSetup(i) == getLevelSetup_atPre[i])){
                Contractor.defaultContractor().postconditionError("ScreenManagerContract", "addScreen", "\\Forall i in [0, getNbScreen()@pre - 1] getLevelSetup(i) == getLevelSetup(i)@pre");               
            }
        }
        
    }

    @Override
    public void removeScreen(int i) {
        //\pre i < getNbScreen()
        if(!(i < getNbScreen())){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "removeScreen", "i < getNbScreen()");
        }

        //\pre i >= 0
        if(!(i >= 0)){
            Contractor.defaultContractor().preconditionError("ScreenManagerContract", "removeScreen", "i >= 0");
        }

        //captures
        int getNbScreen_atPre = getNbScreen();
        LevelSetup getLevelSetup_atPre[] = new LevelSetup[getNbScreen()];
        for(int v=0; v<getNbScreen(); v++){
            getLevelSetup_atPre[v] = getLevelSetup(v);
        }

        //inv pre
        checkInvariant();

        super.removeScreen(i);

        //inv post
        checkInvariant();

        //\post getNbScreen() == getNbScreen()@pre - 1
        if(!(getNbScreen() == getNbScreen_atPre - 1)){
            Contractor.defaultContractor().postconditionError("ScreenManagerContract", "removeScreen", "getNbScreen() == getNbScreen()@pre - 1");
        }

        //\post \Forall v \in [0, getNbScreen() - 1]
        //          v < i \impl getLevelSetup(v) == getLevelSetup(v)@pre
        //          v >= i \impl getLevelSetup(v) == getLevelSetup(v + 1)@pre
        for(int v=0; v< getNbScreen(); v++){
            if(!(Checker.implication(v < i, getLevelSetup(v) == getLevelSetup_atPre[v]))){
                Contractor.defaultContractor().postconditionError("ScreenManagerContract", "removeScreen", "\\Forall v \\in [0, getNbScreen() - 1] v < i \\impl getLevelSetup(v) == getLevelSetup(v)@pre");
            }
            if(!(Checker.implication(v >= i, getLevelSetup(v) == getLevelSetup_atPre[v + 1]))){
                Contractor.defaultContractor().postconditionError("ScreenManagerContract", "removeScreen", "\\Forall v \\in [0, getNbScreen() - 1] v >= i \\impl getLevelSetup(v) == getLevelSetup(v + 1)@pre");                
            }
        }
        
    }


}