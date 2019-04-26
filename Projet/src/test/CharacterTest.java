package test;

import services.Environment;

import org.junit.Before;
import org.junit.Test;

import contracts.CharacterContract;
import data.Cell;
import impl.CharacterImpl;
import impl.EditableScreenImpl;
import impl.EnvironmentImpl;
import services.Character;
import services.EditableScreen;

public class CharacterTest {
    private Character character;
    private Environment envi;

    @Before
    public void beforeTests(){
        envi = new EnvironmentImpl();
        character = new CharacterContract(new CharacterImpl());
        EditableScreen screen = new EditableScreenImpl();
        
        screen.init(10, 20);
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
        envi.init(screen);
    }


    @Test
    public void testInit1(){
        //Pas de CI

        //operations
        character.init(envi, 5, 2);

        //oracles : pas d'exception
    }

    @Test
    public void testGoLeft1(){
        //CI
        character.init(envi, 5, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
    }
    
    @Test
    public void testGoLeft2(){
        //CI
        character.init(envi, 0, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
    }

}