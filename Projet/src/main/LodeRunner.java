package main;

import data.Cell;
import impl.*;
import services.*;

public class LodeRunner{
    public static void main(String[] args){
        Engine engi = new EngineImpl();
        EditableScreen screen = new EditableScreenImpl();
        
        screen.init(5, 10);
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
        
        screen.setNature(8, 1, Cell.LAD);
        screen.setNature(8, 2, Cell.LAD);
        screen.setNature(8, 3, Cell.EMP);
        screen.setNature(3, 2, Cell.PLT);
        
        engi.init(screen, 5, 2, null, null);
        while(true){
            engi.step();
            engi.display();
        }

    }
}