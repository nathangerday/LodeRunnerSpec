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
        ScreenManager sm = Factory.createScreenManager();
        sm.init();
        CommandManager cm = new CommandManager(Thread.currentThread());
 
 
        //==============================================
        //                  LEVEL 1
        //==============================================


        EditableScreen screen1 = Factory.createEditableScreen();
        
        screen1.init(8, 20);
        for(int i=0; i<screen1.getWidth(); i++){
            screen1.setNature(i, 0, Cell.MTL);
        }
        for(int i=0; i<screen1.getWidth(); i++){
            screen1.setNature(i, 1, Cell.PLT);
        }

        for(int i=2; i<screen1.getHeight(); i++){
            for(int j=0; j<screen1.getWidth(); j++){
                screen1.setNature(j, i, Cell.EMP);
            }
        }
 
        screen1.setNature(18, 1, Cell.LAD);
        screen1.setNature(18, 2, Cell.LAD);
        screen1.setNature(18, 3, Cell.LAD);
        screen1.setNature(18, 4, Cell.LAD);
        screen1.setNature(18, 5, Cell.LAD);
        screen1.setNature(18, 6, Cell.LAD);

        screen1.setNature(17, 3, Cell.PLT);
        screen1.setNature(17, 4, Cell.PLT);
        screen1.setNature(17, 5, Cell.PLT);
        screen1.setNature(13, 3, Cell.PLT);
        screen1.setNature(13, 4, Cell.PLT);
        screen1.setNature(13, 5, Cell.PLT);
        screen1.setNature(14, 5, Cell.PLT);
        screen1.setNature(15, 5, Cell.PLT);
        screen1.setNature(16, 5, Cell.PLT);
        screen1.setNature(14, 3, Cell.PLT);
        screen1.setNature(15, 3, Cell.PLT);
        screen1.setNature(16, 3, Cell.PLT);
    
 
        List<CoordItem> itemsCoords1 = new ArrayList<>();
        itemsCoords1.add(new CoordItem(10, 2, ItemType.Treasure));
        itemsCoords1.add(new CoordItem(15, 4, ItemType.Treasure));
        itemsCoords1.add(new CoordItem(5, 2, ItemType.Treasure));
        List<CoordGuard> guardCoords1 = new ArrayList<>();
        guardCoords1.add(new CoordGuard(1, 3, GuardType.NORMAL));

        sm.addScreen(screen1, guardCoords1, itemsCoords1, new Coord(8, 2));
 
 
        //==============================================
        //                  LEVEL 2
        //==============================================

 
 
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

        screen.setNature(15, 4, Cell.NGU);
        screen.setNature(16, 2, Cell.NPL);
        screen.setNature(7, 2, Cell.DOR);

        List<CoordItem> itemsCoords = new ArrayList<>();
        itemsCoords.add(new CoordItem(0, 2, ItemType.Treasure));
        itemsCoords.add(new CoordItem(17, 4, ItemType.Treasure));
        itemsCoords.add(new CoordItem(17, 2, ItemType.Treasure));
        itemsCoords.add(new CoordItem(1, 2, ItemType.Key));
        itemsCoords.add(new CoordItem(12, 2, ItemType.Gun));
        List<CoordGuard> guardCoords = new ArrayList<>();
        guardCoords.add(new CoordGuard(0, 7, GuardType.NORMAL));
        // guardCoords.add(new Coord(10, 3));
        sm.addScreen(screen, guardCoords, itemsCoords, new Coord(5, 2));



        //==============================================
        //                  LEVEL 3
        //==============================================

        
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



        screen2.setNature(3, 1, Cell.LAD);
        screen2.setNature(3, 2, Cell.LAD);
        screen2.setNature(3, 3, Cell.LAD);
        screen2.setNature(3, 4, Cell.LAD);
        screen2.setNature(3, 5, Cell.LAD);
        screen2.setNature(3, 6, Cell.LAD);
        screen2.setNature(3, 7, Cell.LAD);

        screen2.setNature(4, 4, Cell.PLT);
        screen2.setNature(5, 4, Cell.TRP);
        screen2.setNature(6, 4, Cell.PLT);
        screen2.setNature(7, 4, Cell.PLT);

        screen2.setNature(4, 6, Cell.PLT);
        screen2.setNature(5, 6, Cell.PLT);
        screen2.setNature(6, 6, Cell.PLT);
        screen2.setNature(7, 6, Cell.PLT);
        screen2.setNature(8, 6, Cell.PLT);

        List<CoordItem> itemsCoords2 = new ArrayList<>();
        itemsCoords2.add(new CoordItem(6, 5, ItemType.Treasure));
        itemsCoords2.add(new CoordItem(11, 2, ItemType.Treasure));
        itemsCoords2.add(new CoordItem(19, 2, ItemType.Gun));
        itemsCoords2.add(new CoordItem(1, 2, ItemType.Flash));
        // itemsCoords2.add(new CoordItem(3, 2, ItemType.Gun));
        // itemsCoords2.add(new CoordItem(4, 2, ItemType.Flash));
        // itemsCoords2.add(new CoordItem(5, 2, ItemType.Flash));
        List<CoordGuard> guardCoords2 = new ArrayList<>();
        guardCoords2.add(new CoordGuard(9, 7, GuardType.NORMAL));

        sm.addScreen(screen2, guardCoords2, itemsCoords2, new Coord(8, 2));

        //==============================================
        //         CREATE ENGI AND EXECUTE
        //==============================================
        

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