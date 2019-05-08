package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import contracts.PlayerContract;
import data.Cell;
import data.Coord;
import data.CoordGuard;
import data.CoordItem;
import data.GuardType;
import data.ItemType;
import impl.EditableScreenImpl;
import impl.EngineImpl;
import impl.PlayerImpl;
import impl.ScreenManagerImpl;
import services.EditableScreen;
import services.Engine;
import services.Guard;
import services.Player;
import services.ScreenManager;


public class PlayerTest {
    private Player player;
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
        guardCoords.add(new CoordGuard(1, 7, GuardType.NORMAL));
    }

    @Test
    public void testInit1(){
        //CI
        player = new PlayerContract(new PlayerImpl()); // Pour tester uniquement l'initialisation, on a besoin d'un nouveau Player, car normalement il est init par Engine
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);

        //operations
        player.init(engi.getEnvironment(), 5, 2);

        //oracles Pas d'exceptions

    }

    @Test(expected = contracts.ContractError.class)
    public void testInit2(){
        //CI
        player = new PlayerContract(new PlayerImpl()); // Pour tester uniquement l'initialisation, on a besoin d'un nouveau Player, car normalement il est init par Engine
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);

        //operations
        player.init(engi.getEnvironment(), 30, 2);

        //oracles Pas d'exceptions

    }

    @Test
    public void testStep1(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.step();

        //oracles : pas d'exception
    }

    @Test
    public void testPickupItem1(){
         //CI
         sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
         engi.init(sm, null, engi);
         player = new PlayerContract(engi.getPlayer());
 
 
         //operations
         player.pickupItem(ItemType.Key);
 
         //oracles : pas d'exception
         assertTrue(player.getCurrentlyHeldItem().getNature() == ItemType.Key && player.getNumberOfUsagesLeftForCurrentItem() == 1);
    }
    

    @Test
    public void testUseItem1(){
         //CI
         sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
         engi.init(sm, null, engi);
         player = new PlayerContract(engi.getPlayer());
 
 
         //operations
         player.useItem();
 
         //oracles : pas d'exception
         assertTrue(player.getCurrentlyHeldItem().getNature() == ItemType.Sword && player.getNumberOfUsagesLeftForCurrentItem() == 2);
    }

    @Test
    public void digLeft1(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.digLeft();

        //oracles : pas d'exception
        assertTrue(engi.getEnvironment().getCellNature(4, 1) == Cell.HOL);
    }

    @Test
    public void digLeft2(){
        //CI
        screen.setNature(4, 1, Cell.MTL);
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.digLeft();

        //oracles : pas d'exception
        assertTrue(engi.getEnvironment().getCellNature(4, 1) == Cell.MTL);
    }
    

    @Test
    public void digRight1(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.digRight();

        //oracles : pas d'exception
        assertTrue(engi.getEnvironment().getCellNature(6, 1) == Cell.HOL);
    }

    @Test
    public void digRight2(){
        //CI
        screen.setNature(6, 1, Cell.MTL);
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.digRight();

        //oracles : pas d'exception
        assertTrue(engi.getEnvironment().getCellNature(6, 1) == Cell.MTL);
    }



    @Test
    public void testGoLeft1(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goLeft();

        //oracles : pas d'exception
        assertTrue(player.getCol() == 4);
    }

    @Test
    public void testGoLeft2(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goLeft();

        //oracles : pas d'exception
        assertTrue(player.getCol() == 4);
    }

    @Test
    public void testGoRight1(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goRight();

        //oracles : pas d'exception
        assertTrue(player.getCol() == 6);
    }

    @Test
    public void testGoRight2(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(19, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goRight();

        //oracles : pas d'exception
        assertTrue(player.getCol() == 19);
    }

    @Test
    public void testGoDown1(){
        //CI
        screen.setNature(5, 1, Cell.HOL);
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goDown();

        //oracles : pas d'exception
        assertTrue(player.getHgt() == 1);
    }

    @Test
    public void testGoDown2(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(19, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goDown();

        //oracles : pas d'exception
        assertTrue(player.getHgt() == 2);
    }

    @Test
    public void testGoUp1(){
        //CI
        screen.setNature(5, 2, Cell.LAD);
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goUp();

        //oracles : pas d'exception
        assertTrue(player.getHgt() == 3);
    }

    @Test
    public void testGoUp2(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(19, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goUp();

        //oracles : pas d'exception
        assertTrue(player.getHgt() == 2);
    }

    @Test
    public void paireTransition_goLeft_goRight(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(19, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goLeft();
        player.goRight();

        //oracles : pas d'exception
        assertTrue(player.getHgt() == 2 && player.getCol() == 19);
    }

    @Test
    public void paireTransition_goRight_goLeft(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(15, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goRight();
        player.goLeft();

        //oracles : pas d'exception
        assertTrue(player.getHgt() == 2 && player.getCol() == 15);
    }


    @Test
    public void paireTransition_goUp_goDown(){
        //CI
        screen.setNature(15, 2, Cell.LAD);
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(15, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goUp();
        player.goDown();

        //oracles : pas d'exception
        assertTrue(player.getHgt() == 2 && player.getCol() == 15);
    }

    @Test
    public void paireTransition_goDown_goUp(){
        //CI
        screen.setNature(15, 2, Cell.LAD);
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(15, 3));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.goDown();
        player.goUp();

        //oracles : pas d'exception
        assertTrue(player.getHgt() == 3 && player.getCol() == 15);
    }

    @Test
    public void paireTransition_digLeft_digRight(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(15, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.digLeft();
        player.digRight();

        //oracles : pas d'exception
        assertTrue(engi.getEnvironment().getCellNature(14, 1) == Cell.HOL && engi.getEnvironment().getCellNature(16, 1) == Cell.HOL);
    }
    
    @Test
    public void paireTransition_digRight_digLeft(){
        //CI
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(15, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());


        //operations
        player.digLeft();
        player.digRight();

        //oracles : pas d'exception
        assertTrue(engi.getEnvironment().getCellNature(14, 1) == Cell.HOL && engi.getEnvironment().getCellNature(16, 1) == Cell.HOL);
    }


    

    @Test
    public void testEtatRemarquable_UseSword(){
        //CI
        guardCoords.add(new CoordGuard(16, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(19, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());
        Guard g = engi.getGuards().get(1);
        g.goRight();

        //operations
        player.pickupItem(ItemType.Sword);
        player.useItem();

        //oracle : pas d'exception
        assertTrue(g.getCol() == 16 && g.getHgt() == 2);
    }

    @Test
    public void testEtatRemarquable_UseGun(){
        //CI
        guardCoords.add(new CoordGuard(8, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(19, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());
        Guard g = engi.getGuards().get(1);
        g.goRight();
        g.goRight();
        g.goRight();

        //operations
        player.pickupItem(ItemType.Gun);
        player.digLeft(); // To face left side
        player.useItem(); // Kill the guard

        //oracle : pas d'exception
        assertTrue(g.getCol() == 8 && g.getHgt() == 2);
    }

    @Test
    public void testEtatRemarquable_UseGunWithObstacle(){
        //CI
        screen.setNature(13, 2, Cell.PLT);
        guardCoords.add(new CoordGuard(8, 2, GuardType.NORMAL));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(19, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());
        Guard g = engi.getGuards().get(1);
        g.goRight();
        g.goRight();
        g.goRight();

        //operations
        player.pickupItem(ItemType.Gun);
        player.digLeft(); // To face left side
        player.useItem(); // Kill the guard

        //oracle : pas d'exception
        assertTrue(g.getCol() == 11 && g.getHgt() == 2);
    }

    @Test
    public void testEtatRemarquable_UnlockDoor(){
        //CI
        screen.setNature(17, 2, Cell.DOR);
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(19, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());

        //operations
        player.pickupItem(ItemType.Key);
        player.goLeft(); 
        player.useItem(); // Uses the key on the door

        //oracle : pas d'exception
        assertTrue(engi.getEnvironment().getCellNature(17, 2) == Cell.EMP);
    }

    @Test
    public void testEtatRemarquable_FlashBomb(){
        //CI
        screen.setNature(17, 2, Cell.DOR);
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(19, 2));
        engi.init(sm, null, engi);
        player = new PlayerContract(engi.getPlayer());

        //operations
        player.pickupItem(ItemType.Flash);
        player.useItem(); // Uses the bomb

        //oracle : pas d'exception
        assertTrue(engi.getGuards().get(0).getTimeLeftParalyzed() == 10);
    }
}