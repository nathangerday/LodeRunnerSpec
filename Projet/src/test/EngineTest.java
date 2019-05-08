package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import contracts.EngineContract;
import data.Cell;
import data.Coord;
import data.CoordGuard;
import data.CoordItem;
import data.GuardType;
import data.ItemType;
import data.Status;
import impl.EditableScreenImpl;
import impl.EngineImpl;
import impl.ScreenManagerImpl;
import services.EditableScreen;
import services.Engine;
import services.ScreenManager;


public class EngineTest {
    private Engine engi;
    private ScreenManager sm;
    private EditableScreen screen;
    private List<CoordItem> treasureCoords;
    private List<CoordGuard> guardCoords;

    @Before
    public void beforeTests(){
        engi = new EngineContract(new EngineImpl());
        screen = new EditableScreenImpl();
        
        screen.init(8, 20);
        for(int i=0; i<screen.getWidth(); i++){
            screen.setNature(i, 0, Cell.MTL);
        }
        for(int i=0; i<screen.getWidth(); i++){
            screen.setNature(i, 1, Cell.PLT);
        }

        for(int i=2; i<screen.getHeight(); i++){
            for(int j=0; j<screen.getWidth(); j++){
                screen.setNature(j, i, Cell.EMP);
            }
        }

        sm = new ScreenManagerImpl();
        sm.init();
        treasureCoords = new ArrayList<>();
        treasureCoords.add(new CoordItem(0, 2, ItemType.Treasure));
        treasureCoords.add(new CoordItem(17, 4, ItemType.Treasure));
        treasureCoords.add(new CoordItem(8, 2, ItemType.Key));
        treasureCoords.add(new CoordItem(1, 2, ItemType.Sword));
        guardCoords = new ArrayList<>();
        guardCoords.add(new CoordGuard(1, 7, GuardType.NORMAL));
        // guardCoords.add(new Coord(10, 3));
    }
    
    
    @Test
    public void testInit1(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));

        //operation
        engi.init(sm, null, engi);

        //oracle : Pas d'exceptions
    }

    @Test(expected = contracts.ContractError.class)
    public void testInit2(){
        //Pas de CI

        //operation
        engi.init(null, null, engi);

        //oracle : Pas d'exceptions
    }

    @Test(expected = contracts.ContractError.class)
    public void testInit3(){
        //Pas de CI

        //operation
        engi.init(sm, null, engi);

        //oracle : Pas d'exceptions
    }

    @Test
    public void testAdd1(){
        //CI
        screen.setNature(3, 1, Cell.HOL);
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);

        //operation
        engi.addHole(3, 1);

        //oracle : Pas d'exceptions
    }

    @Test(expected = contracts.ContractError.class)
    public void testAdd2(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);

        //operation
        engi.addHole(3, 1);

        //oracle : Pas d'exceptions
    }

    @Test
    public void testDisplay(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);

        //operation
        engi.display();

        //oracle : Pas d'exceptions
    }

    @Test
    public void testStep(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);

        //operation
        engi.step();

        //oracle : Pas d'exceptions
    }



    @Test
    public void testEtatRemarquable_Win(){
        //CI
        treasureCoords.clear();
        treasureCoords.add(new CoordItem(5, 2, ItemType.Treasure));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));

        //operations
        engi.init(sm, null, engi);
        engi.step();

        //oracles : pas d'exceptions
        assertTrue(engi.getStatus() == Status.Win);
    }

    @Test
    public void testEtatRemarquable_Loss(){
        //CI
        guardCoords.add(new CoordGuard(5, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));

        //operations
        engi.init(sm, null, engi); // 3 lifes at this point

        engi.step(); // First death
        engi.step(); // Second death
        engi.step(); // Third and last death, no more lifes left

        //oracles : pas d'exceptions
        assertTrue(engi.getStatus() == Status.Loss);
    }

    @Test    
    public void testEtatRemarquable_RemplissageTrouApresDuree(){
        //CI
        screen.setNature(2, 1, Cell.HOL);
        sm.addScreen(screen, null, treasureCoords, new Coord(5, 2));

        //operations
        engi.init(sm, null, engi);
        engi.addHole(2, 1);
        for(int i=0; i<15; i++){
            engi.step();
        }
        
        //oracles : pas d'exceptions
        assertTrue(engi.getEnvironment().getCellNature(2, 1) == Cell.PLT);        
    }
}