package utils;

import java.util.Scanner;

import data.Command;
import services.Engine;

public class CommandManager extends Thread{
    private Scanner scanner = new Scanner(System.in);
    private Engine engine;
    private Thread mainThread;
    
    public CommandManager(Engine e, Thread mainThread){
        this.engine = e;
        this.mainThread = mainThread;
        this.start();
    }

    public Command getNextUserCommandInput(){
        String input = scanner.next();
        switch(input){
            case "q": 
                return Command.MOVEL;
            case "d":
                return Command.MOVER;
            case "z":
                return Command.MOVEU;
            case "s":
                return Command.MOVED;
            case "a":
                return Command.DIGL;
            case "e":
                return Command.DIGL;
            default:
                return Command.NONE;
        }
    }

    public void run(){
        while(this.mainThread.isAlive()){
            Command next = getNextUserCommandInput();
            this.engine.setNextCommand(next);
        }
    }

}