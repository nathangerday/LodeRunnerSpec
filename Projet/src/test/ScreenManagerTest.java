package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import contracts.ScreenManagerContract;
import data.Coord;
import data.CoordGuard;
import data.CoordItem;
import data.GuardType;
import data.ItemType;
import impl.EditableScreenImpl;
import impl.ScreenManagerImpl;
import services.EditableScreen;
import services.ScreenManager;


public class ScreenManagerTest {
    private ScreenManager manager;

    @Before
    public void beforeTests(){
        manager = new ScreenManagerContract(new ScreenManagerImpl());
    }

    @Test
    public void testInit1(){
        // Pas de conditions initiales

        //operations
        manager.init();

        //oracles : pas d'exception
    }

    @Test
    public void testAddScreen1(){
        //CI
        manager.init();
        List<CoordItem> itemsCoord = new ArrayList<>();
        itemsCoord.add(new CoordItem(10, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(11, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(8, 2, ItemType.Gun));
        List<CoordGuard> guardCoords2 = new ArrayList<>();
        guardCoords2.add(new CoordGuard(9, 7, GuardType.NORMAL));
        EditableScreen screen = new EditableScreenImpl();
        screen.init(8, 20);

        //operations
        manager.addScreen(screen, guardCoords2, itemsCoord, new Coord(5, 2));

        //oracles : pas d'exception
    }

    @Test(expected = contracts.ContractError.class)
    public void testAddScreen2(){
        //CI
        manager.init();
        List<CoordItem> itemsCoord = new ArrayList<>();
        itemsCoord.add(new CoordItem(10, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(11, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(8, 2, ItemType.Gun));
        List<CoordGuard> guardCoords2 = new ArrayList<>();
        guardCoords2.add(new CoordGuard(9, 7, GuardType.NORMAL));
        EditableScreen screen = new EditableScreenImpl();
        screen.init(8, 20);

        //operations
        manager.addScreen(screen, guardCoords2, itemsCoord, null);

        //Oracle : ContractError attendu
    }


    @Test
    public void testRemoveScreen1(){
        //CI
        manager.init();
        List<CoordItem> itemsCoord = new ArrayList<>();
        itemsCoord.add(new CoordItem(10, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(11, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(8, 2, ItemType.Gun));
        List<CoordGuard> guardCoords2 = new ArrayList<>();
        guardCoords2.add(new CoordGuard(9, 7, GuardType.NORMAL));
        EditableScreen screen = new EditableScreenImpl();
        screen.init(8, 20);
        manager.addScreen(screen, guardCoords2, itemsCoord, new Coord(5, 2));

        //operations
        manager.removeScreen(0);

        //oracles : pas d'exception
    }

    @Test(expected = contracts.ContractError.class)
    public void testRemoveScreen2(){
        //CI
        manager.init();

        //operations
        manager.removeScreen(0);

        //Oracle : ContractError attendu
    }

    @Test(expected = contracts.ContractError.class)
    public void testRemoveScreen3(){
        //CI
        manager.init();
        List<CoordItem> itemsCoord = new ArrayList<>();
        itemsCoord.add(new CoordItem(10, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(11, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(8, 2, ItemType.Gun));
        List<CoordGuard> guardCoords2 = new ArrayList<>();
        guardCoords2.add(new CoordGuard(9, 7, GuardType.NORMAL));
        EditableScreen screen = new EditableScreenImpl();
        screen.init(8, 20);
        manager.addScreen(screen, guardCoords2, itemsCoord, new Coord(5, 2));

        //operations
        manager.removeScreen(1);

        //Oracle : ContractError attendu
    }


    @Test
    public void testPaireTransition_addScreen_removeScreen(){
        //CI
        manager.init();
        List<CoordItem> itemsCoord = new ArrayList<>();
        itemsCoord.add(new CoordItem(10, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(11, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(8, 2, ItemType.Gun));
        List<CoordGuard> guardCoords2 = new ArrayList<>();
        guardCoords2.add(new CoordGuard(9, 7, GuardType.NORMAL));
        EditableScreen screen = new EditableScreenImpl();
        screen.init(8, 20);

        //operations
        manager.addScreen(screen, guardCoords2, itemsCoord, new Coord(5, 2));
        manager.removeScreen(0);

        //oracles : pas d'exception
    }

    @Test
    public void testPaireTransition_removeScreen_addScreen(){
        //CI
        manager.init();
        List<CoordItem> itemsCoord = new ArrayList<>();
        itemsCoord.add(new CoordItem(10, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(11, 2, ItemType.Treasure));
        itemsCoord.add(new CoordItem(8, 2, ItemType.Gun));
        List<CoordGuard> guardCoords2 = new ArrayList<>();
        guardCoords2.add(new CoordGuard(9, 7, GuardType.NORMAL));
        EditableScreen screen = new EditableScreenImpl();
        screen.init(8, 20);

        //operations
        manager.addScreen(screen, guardCoords2, itemsCoord, new Coord(5, 2));
        manager.removeScreen(0);
        manager.addScreen(screen, guardCoords2, itemsCoord, new Coord(8, 2));


        //oracles : pas d'exception
    }
    
}