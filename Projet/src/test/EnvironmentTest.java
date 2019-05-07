package test;

import org.junit.Before;
import org.junit.Test;

import contracts.EnvironmentContract;
import data.Cell;
import data.Item;
import data.ItemType;
import impl.EditableScreenImpl;
import impl.EnvironmentImpl;
import impl.GuardImpl;
import services.EditableScreen;
import services.Environment;

public class EnvironmentTest {
    private Environment envi;
    private EditableScreen screen;

    @Before
    public void beforeTests(){
        screen = new EditableScreenImpl();
        screen.init(10, 20);
        envi = new EnvironmentContract(new EnvironmentImpl());
    }


    @Test
    public void testInit1(){
        // Pas de condition initiale
        
        //operations
        envi.init(screen);

        //oracles : pas d'exception
        
    }

    @Test
    public void testAddToCellContent1(){
        //CI
        envi.init(screen);

        //operations
        envi.addToCellContent(5, 5, new Item(ItemType.Treasure, 5, 5));

        //oracles : pas d'exception
        
    }

    @Test(expected = contracts.ContractError.class)
    public void testAddToCellContent2(){
        //CI
        envi.init(screen);

        //operations
        envi.addToCellContent(30, 5, new Item(ItemType.Treasure, 5, 5));

        //oracles : pas d'exception
    }

    @Test(expected = contracts.ContractError.class)
    public void testAddToCellContent3(){
        //CI
        envi.init(screen);
        envi.addToCellContent(5, 5, new GuardImpl());

        //operations
        envi.addToCellContent(5, 5, new GuardImpl());

        //oracles : pas d'exception
    }


    @Test
    public void testRemoveFromCellContent1(){
        //CI
        envi.init(screen);
        Item i = new Item(ItemType.Treasure, 5, 5);
        envi.addToCellContent(5, 5, i);

        //operations
        envi.removeFromCellContent(5, 5, i);

        //oracles : pas d'exception
        
    }

    @Test(expected = contracts.ContractError.class)
    public void testRemoveFromCellContent2(){
        //CI
        envi.init(screen);
        envi.addToCellContent(5, 5, new Item(ItemType.Treasure, 5, 5));

        //operations
        envi.removeFromCellContent(5, 5, new Item(ItemType.Treasure, 8, 8));

        //oracles : pas d'exception
    }




    

    @Test(expected = contracts.ContractError.class)
    public void testDig1(){
        //CI
        envi.init(screen);
        
        //operations
        envi.dig(5, 2);

        //oracles : PreconditionError
        
    }

    @Test(expected = contracts.ContractError.class)
    public void testFill1(){
        //CI
        envi.init(screen);
        
        //operations
        envi.fill(5, 2);

        //oracles : PreconditionError
    }

    @Test(expected = contracts.ContractError.class)
    public void testOpenDoor1(){
        //CI
        envi.init(screen);
        
        //operations
        envi.openDoor(5, 2);

        //oracles : PreconditionError
        
    }
    
    @Test(expected = contracts.ContractError.class)
    public void testRevealTrap1(){
        //CI
        envi.init(screen);
        
        //operations
        envi.revealTrap(5, 2);

        //oracles : PreconditionError
        
    }

    @Test
    public void testDig2(){
        //CI
        screen.setNature(5, 2, Cell.PLT);
        envi.init(screen);
        
        //operations
        envi.dig(5, 2);

        //oracles : Pas d'exception
        
    }

    @Test
    public void testFill2(){
        //CI
        screen.setNature(5, 2, Cell.HOL);
        envi.init(screen);
        
        //operations
        envi.fill(5, 2);

        //oracles : Pas d'exception
        
    }


    @Test
    public void testOpenDoor2(){
        //CI
        screen.setNature(5, 2, Cell.DOR);
        envi.init(screen);
        
        //operations
        envi.openDoor(5, 2);

        //oracles : Pas d'exception
        
    }

    @Test
    public void testRevealTrap2(){
        //CI
        screen.setNature(5, 2, Cell.TRP);
        envi.init(screen);
        
        //operations
        envi.revealTrap(5, 2);

        //oracles : Pas d'exception
        
    }


}