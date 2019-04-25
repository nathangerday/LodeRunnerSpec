package main;

import java.util.ArrayList;
import java.util.List;

import contracts.EditableScreenContract;
import contracts.EngineContract;
import contracts.EnvironmentContract;
import contracts.PlayerContract;
import data.Cell;
import data.Coord;
import data.Status;
import impl.*;
import services.*;
import utils.CommandManager;
import utils.Factory;

public class LodeRunner{
    public static void main(String[] args){
        Engine engi = Factory.createEngine();
        EditableScreen screen = Factory.createEditableScreen();
        
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
        
        screen.setNature(8, 1, Cell.LAD);
        screen.setNature(8, 2, Cell.LAD);
        screen.setNature(8, 3, Cell.LAD);
        screen.setNature(8, 4, Cell.LAD);
        screen.setNature(8, 5, Cell.LAD);
        screen.setNature(8, 6, Cell.LAD);

        screen.setNature(9, 4, Cell.HDR);
        screen.setNature(10, 4, Cell.HDR);
        screen.setNature(11, 4, Cell.HDR);
        screen.setNature(12, 4, Cell.HDR);

        screen.setNature(13, 3, Cell.PLT);
        screen.setNature(14, 3, Cell.PLT);
        screen.setNature(15, 3, Cell.PLT);
        screen.setNature(16, 3, Cell.PLT);
        screen.setNature(17, 3, Cell.PLT);
        screen.setNature(18, 3, Cell.PLT);
        // screen.setNature(3, 2, Cell.PLT);
        CommandManager cm = new CommandManager(Thread.currentThread());
        ScreenManager sm = Factory.createScreenManager();
        List<Coord> treasureCoords = new ArrayList<>();
        treasureCoords.add(new Coord(0, 2));
        treasureCoords.add(new Coord(17, 4));
        List<Coord> guardCoords = new ArrayList<>();
        guardCoords.add(new Coord(1, 7));
        // guardCoords.add(new Coord(10, 3));
        sm.addScreen(screen, guardCoords, treasureCoords, new Coord(5, 2));


        EditableScreen screen2 = Factory.createEditableScreen();
        
        screen2.init(8, 20);
        for(int i=0; i<screen2.getWidth(); i++){
            screen2.setNature(i, 0, Cell.MTL);
        }
        for(int i=0; i<screen2.getWidth(); i++){
            screen2.setNature(i, 1, Cell.PLT);
        }

        for(int i=2; i<screen2.getHeight(); i++){
            for(int j=0; j<screen2.getWidth(); j++){
                screen2.setNature(j, i, Cell.EMP);
            }
        }
        List<Coord> treasureCoords2 = new ArrayList<>();
        treasureCoords2.add(new Coord(10, 2));
        treasureCoords2.add(new Coord(11, 2));
        List<Coord> guardCoords2 = new ArrayList<>();
        guardCoords2.add(new Coord(16, 7));

        sm.addScreen(screen2, guardCoords2, treasureCoords2, new Coord(8, 2));

        engi.init(sm, cm, engi);
        while(engi.getStatus() == Status.Playing){
            engi.step();
            engi.display();
            //UGLY
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}