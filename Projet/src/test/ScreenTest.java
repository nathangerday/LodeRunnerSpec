package test;

import services.Screen;

import static org.junit.Assert.assertTrue;

import org.junit.After;
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
    public void test1(){
        assertTrue(true);
    }
    
    
}