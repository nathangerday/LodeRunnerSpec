package utils;

import java.util.Scanner;

import data.Command;

public class CommandManager {
    private static Scanner scanner = new Scanner(System.in);

    public static Command getNextUserCommandInput(){
        while(true){
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
                    System.out.println("Unknown Command");
            }
        }

    }
}