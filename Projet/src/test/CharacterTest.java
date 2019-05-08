package test;

import services.Environment;

import static org.junit.Assert.assertTrue;

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
    private EditableScreen screen;
    private Environment envi;

    @Before
    public void beforeTests(){
        envi = new EnvironmentImpl();
        character = new CharacterContract(new CharacterImpl());
        screen = new EditableScreenImpl();
        
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
    }


    @Test
    public void testInit1(){
        //CI
        envi.init(screen);

        //operations
        character.init(envi, 5, 2);

        //oracles : pas d'exception
    }

    @Test(expected = contracts.ContractError.class)
    public void testInit2(){
        //CI
        screen.setNature(5, 2, Cell.MTL);
        envi.init(screen);

        //operations
        character.init(envi, 5, 2);

        //Oracle : ContractError attendu
    }

    @Test(expected = contracts.ContractError.class)
    public void testInit3(){
        //Pas de CI
        envi.init(screen);

        //operations
        character.init(envi, 30, 2);

        //Oracle : ContractError attendu
    }


    @Test
    public void testGoLeft1(){
        //CI
        envi.init(screen);
        character.init(envi, 5, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 4);
    }
    
    @Test
    public void testGoLeft2(){
        //CI
        envi.init(screen);
        character.init(envi, 0, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 0);

    }

    @Test
    public void testGoLeft3(){
        //CI
        screen.setNature(4, 2, Cell.PLT);
        envi.init(screen);
        character.init(envi, 5, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 5);
    }


    @Test
    public void testGoLeft4(){
        //CI
        screen.setNature(4, 2, Cell.MTL);
        envi.init(screen);
        character.init(envi, 5, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 5);
    }

    @Test
    public void testGoLeft5(){
        //CI
        screen.setNature(4, 2, Cell.NPL);
        envi.init(screen);
        character.init(envi, 5, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 4);
    }

    @Test
    public void testGoLeft6(){
        //CI
        screen.setNature(4, 2, Cell.HDR);
        envi.init(screen);
        character.init(envi, 5, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 4);
    }

    @Test
    public void testGoLeft7(){
        //CI
        screen.setNature(4, 2, Cell.HOL);
        envi.init(screen);
        character.init(envi, 5, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 4);
    }

    @Test
    public void testGoLeft8(){
        //CI
        screen.setNature(4, 2, Cell.LAD);
        envi.init(screen);
        character.init(envi, 5, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 4);
    }

    @Test
    public void testGoLeft9(){
        //CI
        screen.setNature(4, 2, Cell.NGU);
        envi.init(screen);
        character.init(envi, 5, 2);

        //operations
        character.goLeft();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 4);
    }
    

    @Test
    public void testGoRight1(){
        //CI
        envi.init(screen);
        character.init(envi, 5, 2);

        //operations
        character.goRight();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 6);
    }
    
    @Test
    public void testGoRight2(){
        //CI
        envi.init(screen);
        character.init(envi, 19, 2);

        //operations
        character.goRight();

        //oracles : pas d'exception
        assertTrue(character.getCol() == 19);

    }


    @Test
    public void testGoUp1(){
        //CI
        screen.setNature(19, 2, Cell.LAD);
        envi.init(screen);
        character.init(envi, 19, 2);

        //operations
        character.goUp();

        //oracles : pas d'exception
        assertTrue(character.getHgt() == 3);
    }
    
    @Test
    public void testGoUp2(){
        //CI
        envi.init(screen);
        character.init(envi, 19, 2);

        //operations
        character.goUp();

        //oracles : pas d'exception
        assertTrue(character.getHgt() == 2);

    }

    @Test
    public void testGoDown1(){
        //CI
        screen.setNature(19, 1, Cell.EMP);
        envi.init(screen);
        character.init(envi, 19, 2);

        //operations
        character.goDown();

        //oracles : pas d'exception
        assertTrue(character.getHgt() == 1);
    }
    
    @Test
    public void testGoDown2(){
        //CI
        envi.init(screen);
        character.init(envi, 19, 2);

        //operations
        character.goDown();

        //oracles : pas d'exception
        assertTrue(character.getHgt() == 2);

    }
    

}