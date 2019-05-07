package main;

import java.util.ArrayList;
import java.util.List;

import contracts.EditableScreenContract;
import contracts.EngineContract;
import contracts.EnvironmentContract;
import contracts.PlayerContract;
import data.Cell;
import data.Coord;
import data.CoordGuard;
import data.CoordItem;
import data.GuardType;
import data.ItemType;
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
        sm.init();
        List<CoordItem> treasureCoords = new ArrayList<>();
        treasureCoords.add(new CoordItem(0, 2, ItemType.Treasure));
        treasureCoords.add(new CoordItem(17, 4, ItemType.Treasure));
        treasureCoords.add(new CoordItem(5, 2, ItemType.Key));
        treasureCoords.add(new CoordItem(1, 2, ItemType.Sword));
        List<CoordGuard> guardCoords = new ArrayList<>();
        guardCoords.add(new CoordGuard(1, 7, GuardType.NORMAL));
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

        // screen2.setNature(4, 2, Cell.NPL);
        screen2.setNature(10, 2, Cell.PLT);
        screen2.setNature(6, 1, Cell.TRP);
        List<CoordItem> treasureCoords2 = new ArrayList<>();
        treasureCoords2.add(new CoordItem(10, 2, ItemType.Treasure));
        treasureCoords2.add(new CoordItem(11, 2, ItemType.Treasure));
        treasureCoords2.add(new CoordItem(8, 2, ItemType.Gun));
        treasureCoords2.add(new CoordItem(8, 2, ItemType.Gun));
        treasureCoords2.add(new CoordItem(8, 2, ItemType.Gun));
        treasureCoords2.add(new CoordItem(8, 2, ItemType.Gun));
        treasureCoords2.add(new CoordItem(1, 2, ItemType.Sword));
        // treasureCoords2.add(new CoordItem(3, 2, ItemType.Gun));
        // treasureCoords2.add(new CoordItem(4, 2, ItemType.Flash));
        // treasureCoords2.add(new CoordItem(5, 2, ItemType.Flash));
        List<CoordGuard> guardCoords2 = new ArrayList<>();
        guardCoords2.add(new CoordGuard(9, 7, GuardType.NORMAL));

        sm.addScreen(screen2, guardCoords2, treasureCoords2, new Coord(8, 2));
        // sm.removeScreen(0);
        engi.init(sm, cm, engi);
        while(engi.getStatus() == Status.Playing){
            engi.step();
            engi.display();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}