package utils;

import impl.*;
import services.*;
import contracts.*;

public class Factory{
    private Factory(){}

    public static Player createPlayer(){
        return new PlayerContract(new PlayerImpl());
        // return new PlayerImpl();
    }

    public static services.Character createCharacter(){
        return new CharacterContract(new CharacterImpl());
        // return new CharacterImpl();
    }

    public static EditableScreen createEditableScreen(){
        return new EditableScreenContract(new EditableScreenImpl());
        // return new EditableScreenImpl();
    }
    public static Environment createEnvironment() {
        return new EnvironmentContract(new EnvironmentImpl());
        // return new EnvironmentImpl();
    }
    public static Engine createEngine(){
        return new EngineContract(new EngineImpl());
        // return new EngineImpl();
    }
    public static Guard createGuard(){
        return new GuardImpl();
    }
    public static Screen createScreen(){
        return new ScreenContract(new ScreenImpl());
        // return new ScreenImpl();
    }
    public static ScreenManager createScreenManager(){
        return new ScreenManagerImpl();
    }
}