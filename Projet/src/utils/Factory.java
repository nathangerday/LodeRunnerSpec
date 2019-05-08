package utils;

import impl.*;
import services.*;
import contracts.*;

public class Factory{
    private Factory(){}

    public static Player createPlayer(){
        return new PlayerContract(new PlayerImpl());
        // return new PlayerContract(new PlayerImplBug());
        // return new PlayerImpl();
    }

    public static services.Character createCharacter(){
        return new CharacterContract(new CharacterImpl());
        // return new CharacterContract(new CharacterImplBug());
        // return new CharacterImpl();
    }

    public static EditableScreen createEditableScreen(){
        return new EditableScreenContract(new EditableScreenImpl());
        // return new EditableScreenContract(new EditableScreenImplBug());
        // return new EditableScreenImpl();
    }
    public static Environment createEnvironment() {
        return new EnvironmentContract(new EnvironmentImpl());
        // return new EnvironmentContract(new EnvironmentImplBug());
        // return new EnvironmentImpl();
    }
    public static Engine createEngine(){
        return new EngineContract(new EngineImpl());
        // return new EngineContract(new EngineImplBug());
        // return new EngineImpl();
    }
    public static Guard createGuard(){
        return new GuardContract(new GuardImpl());
        // return new GuardContract(new GuardImplBug());
        // return new GuardImpl();
    }
    public static Screen createScreen(){
        return new ScreenContract(new ScreenImpl());
        // return new ScreenContract(new ScreenImplBug());
        // return new ScreenImpl();
    }
    public static ScreenManager createScreenManager(){
        return new ScreenManagerContract(new ScreenManagerImpl());
        // return new ScreenManagerContract(new ScreenManagerImplBug());
        // return new ScreenManagerImpl();
    }
}