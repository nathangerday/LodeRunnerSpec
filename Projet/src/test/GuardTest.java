package test;
 
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import contracts.GuardContract;
import data.Cell;
import data.Coord;
import data.CoordGuard;
import data.CoordItem;
import data.GuardType;
import data.ItemType;
import impl.EditableScreenImpl;
import impl.EngineImpl;
import impl.GuardImpl;
import impl.ScreenManagerImpl;
import services.EditableScreen;
import services.Engine;
import services.Guard;
import services.ScreenManager;

public class GuardTest {
    private Guard guard;
    private Engine engi;
    private ScreenManager sm;
    private EditableScreen screen;
    private List<CoordItem> treasureCoords;
    private List<CoordGuard> guardCoords;

    @Before
    public void beforeTests(){
        engi = new EngineImpl();
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
        treasureCoords.add(new CoordItem(5, 2, ItemType.Key));
        treasureCoords.add(new CoordItem(1, 2, ItemType.Sword));
        guardCoords = new ArrayList<>();
        guardCoords.add(new CoordGuard(1, 2, GuardType.NORMAL));
    }

    @Test
    public void testInit1(){
        //CI
        guard = new GuardContract(new GuardImpl());
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        
        //operation
        guard.init(engi, 10, 2, engi.getPlayer());

        //oracle : Pas d'exceptions
        
    }

    @Test(expected = contracts.ContractError.class)
    public void testInit2(){
        //CI
        guard = new GuardContract(new GuardImpl());
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        
        //operation
        guard.init(engi, 30, 2, engi.getPlayer());

        //oracle : Pas d'exceptions
        
    }

    @Test
    public void testStep1(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(0));


        //operations
        guard.step();

        //oracles : pas d'exception
    }

    @Test
    public void testMoveToInitCoords(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(0));
        guard.goLeft();

        //operations
        guard.moveToInitCoords();

        //oracles : pas d'exception
        assertTrue(guard.getCol() == 1  && guard.getHgt() == 2);
    }

    @Test
    public void testParalyze1(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(0));

        //operations
        guard.paralyze();

        //oracles : pas d'exception
        assertTrue(guard.getTimeLeftParalyzed() == 10);
    }

    @Test
    public void testClimbRight1(){
        //CI
        screen.setNature(4, 1, Cell.HOL);
        guardCoords.add(new CoordGuard(4, 1, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));

        //operations
        guard.climbRight();

        //oracles : pas d'exception
        assertTrue(guard.getCol() == 5 && guard.getHgt() == 2);
    }
    
    @Test(expected = contracts.ContractError.class)
    public void testClimbRight2(){
        //CI
        guardCoords.add(new CoordGuard(4, 1, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));

        //operations
        guard.climbRight();

        //oracles : pas d'exception
    }

    @Test
    public void testClimbLeft1(){
        //CI
        screen.setNature(4, 1, Cell.HOL);
        guardCoords.add(new CoordGuard(4, 1, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));

        //operations
        guard.climbLeft();

        //oracles : pas d'exception
        assertTrue(guard.getCol() == 3 && guard.getHgt() == 2);
    }
    
    @Test(expected = contracts.ContractError.class)
    public void testClimbLeft2(){
        //CI
        guardCoords.add(new CoordGuard(4, 1, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));

        //operations
        guard.climbLeft();

        //oracles : pas d'exception
    }


    @Test
    public void testGoLeft1(){
        //CI
        guardCoords.add(new CoordGuard(4, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));

        //operations
        guard.goLeft();

        //oracles : pas d'exception
        assertTrue(guard.getCol() == 3);
    }

    @Test
    public void testGoLeft2(){
        //CI
        guardCoords.add(new CoordGuard(0, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));


        //operations
        guard.goLeft();

        //oracles : pas d'exception
        assertTrue(guard.getCol() == 0);
    }

    @Test
    public void testGoRight1(){
        //CI
        guardCoords.add(new CoordGuard(4, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));


        //operations
        guard.goRight();

        //oracles : pas d'exception
        assertTrue(guard.getCol() == 5);
    }

    @Test
    public void testGoRight2(){
        //CI
        guardCoords.add(new CoordGuard(19, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));


        //operations
        guard.goRight();

        //oracles : pas d'exception
        assertTrue(guard.getCol() == 19);
    }

    @Test
    public void testGoDown1(){
        //CI
        screen.setNature(4, 1, Cell.HOL);
        guardCoords.add(new CoordGuard(4, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));

        //operations
        guard.goDown();

        //oracles : pas d'exception
        assertTrue(guard.getHgt() == 1);
    }

    @Test
    public void testGoDown2(){
        //CI
        guardCoords.add(new CoordGuard(4, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));


        //operations
        guard.goDown();

        //oracles : pas d'exception
        assertTrue(guard.getHgt() == 2);
    }

    @Test
    public void testGoUp1(){
        //CI
        screen.setNature(4, 2, Cell.LAD);
        guardCoords.add(new CoordGuard(4, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));


        //operations
        guard.goUp();

        //oracles : pas d'exception
        assertTrue(guard.getHgt() == 3);
    }

    @Test
    public void testGoUp2(){
        //CI
        guardCoords.add(new CoordGuard(4, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));


        //operations
        guard.goUp();

        //oracles : pas d'exception
        assertTrue(guard.getHgt() == 2);
    }


    @Test
    public void testEtatRemarquable_Falling(){
        //CI
        guardCoords.add(new CoordGuard(4, 7, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));

        //operations
        guard.step();

        //oracle : Pas d'exception
        assertTrue(guard.getHgt() == 6 && guard.getCol() == 4);
    }


    @Test
    public void testEtatRemarquable_FallingIntoHOL(){
        //CI
        guardCoords.add(new CoordGuard(4, 2, GuardType.NORMAL));
        screen.setNature(4, 1, Cell.HOL);
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(6, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));

        //operations
        guard.step(); //Fall into HOL
        guard.step(); 
        guard.step();
        guard.step();
        guard.step();
        guard.step();
        guard.step(); //Get out with climbRight (because player tot the right)
 
        //oracle : Pas d'exception
        assertTrue(guard.getCol() == 5 && guard.getHgt() == 2);
    }

    @Test
    public void testEtatRemarquable_StillFallingWhenParalyzed(){
        //CI
        guardCoords.add(new CoordGuard(4, 7, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(6, 2));
        engi.init(sm, null, engi);
        guard = new GuardContract(engi.getGuards().get(1));

        //operations
        guard.paralyze();
        guard.step(); 
        guard.step();
 
        //oracle : Pas d'exception
        assertTrue(guard.getCol() == 4 && guard.getHgt() == 5);
    }
    

}