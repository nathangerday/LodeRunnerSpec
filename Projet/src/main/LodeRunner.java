package main;

import contracts.EditableScreenContract;
import contracts.PlayerContract;
import data.Cell;
import data.Status;
import impl.*;
import services.*;
import utils.CommandManager;

public class LodeRunner{
    public static void main(String[] args){
        Engine engi = new EngineImpl(new PlayerContract(new PlayerImpl()), new EnvironmentImpl());
        EditableScreen screen = new EditableScreenContract(new EditableScreenImpl());
        
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
        engi.init(screen, 5, 2, null, null, cm);
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