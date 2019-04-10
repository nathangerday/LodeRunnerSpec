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
        
        engi.init(screen, null, null);
        engi.display();

    }
}