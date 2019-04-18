package test;

import services.Screen;

import org.junit.Before;
import org.junit.Test;

import contracts.ScreenContract;
import impl.ScreenImpl;


public class ScreenTest {
    private Screen screen;


    @Before
    public void beforeTests(){
        screen = new ScreenContract(new ScreenImpl());
    }

    @Test
    public void testInit1(){
        // Pas de condition initiale
        
        //operations
        screen.init(10, 20);

        //oracles : pas d'exception
        
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
    
}