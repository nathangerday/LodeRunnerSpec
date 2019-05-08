package test;

import org.junit.Before;
import org.junit.Test;

import contracts.EditableScreenContract;
import data.Cell;
import impl.EditableScreenImpl;
import services.EditableScreen;

public class EditableScreenTest {
    private EditableScreen screen;

    
    @Before
    public void beforeTests(){
        screen = new EditableScreenContract(new EditableScreenImpl());
    }


    @Test
    public void testSetNature1(){
        //CI
        screen.init(10, 20);
        
        //operations
        screen.setNature(4, 5, Cell.PLT);

        //oracles : pas d'exception
        
    }

    @Test(expected = contracts.ContractError.class)
    public void testSetNature2(){
        //CI
        screen.init(10, 20);
        
        //operations
        screen.setNature(24, 5, Cell.PLT);

       //Oracle : ContractError attendu
        
    }

    @Test
    public void testInit1(){
        // Pas de condition initiale
        
        //operations
        screen.init(10, 20);

        //oracles : pas d'exception
        
    }

    @Test(expected = contracts.ContractError.class)
    public void testInit2(){
        //CI
        
        //operations
        screen.init(-10, 20);

        //Oracle : ContractError attendu
        
    }
    

    @Test(expected = contracts.ContractError.class)
    public void testDig1(){
        //CI
        screen.init(10, 20);
        
        //operations
        screen.dig(5, 2);

        //oracles : PreconditionError
        
    }

    @Test(expected = contracts.ContractError.class)
    public void testFill1(){
        //CI
        screen.init(10, 20);
        
        //operations
        screen.fill(5, 2);

        //oracles : PreconditionError
    }

    @Test(expected = contracts.ContractError.class)
    public void testOpenDoor1(){
        //CI
        screen.init(10, 20);
        
        //operations
        screen.openDoor(5, 2);

        //oracles : PreconditionError
        
    }
    
    @Test(expected = contracts.ContractError.class)
    public void testRevealTrap1(){
        //CI
        screen.init(10, 20);
        
        //operations
        screen.revealTrap(5, 2);

        //oracles : PreconditionError
        
    }

    @Test
    public void testDig2(){
        //CI
        screen.init(10, 20);
        screen.setNature(5, 2, Cell.PLT);
        
        //operations
        screen.dig(5, 2);

        //oracles : Pas d'exception
        
    }

    @Test
    public void testFill2(){
        //CI
        screen.init(10, 20);
        screen.setNature(5, 2, Cell.HOL);
        
        //operations
        screen.fill(5, 2);

        //oracles : Pas d'exception
        
    }


    @Test
    public void testOpenDoor2(){
        //CI
        screen.init(10, 20);
        screen.setNature(5, 2, Cell.DOR);
        
        //operations
        screen.openDoor(5, 2);

        //oracles : Pas d'exception
        
    }

    @Test
    public void testRevealTrap2(){
        //CI
        screen.init(10, 20);
        screen.setNature(5, 2, Cell.TRP);
        
        //operations
        screen.revealTrap(5, 2);

        //oracles : Pas d'exception
        
    }

    @Test
    public void testPaireTransition_Dig_Fill(){
        //CI
        screen.init(10, 20);
        screen.setNature(5, 2, Cell.PLT);
        
        //operations
        screen.dig(5, 2);
        screen.fill(5, 2);

        //oracles : Pas d'exception
        
    }

    @Test
    public void testPaireTransition_Fill_Dig(){
        //CI
        screen.init(10, 20);
        screen.setNature(5, 2, Cell.HOL);
        
        //operations
        screen.fill(5, 2);
        screen.dig(5, 2);

        //oracles : Pas d'exception
        
    }
    


}