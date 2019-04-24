package utils;

import impl.*;
import services.*;
import contracts.*;

public class Factory{
    private Factory(){}

    public static Player createPlayer(){
        return new PlayerContract(new PlayerImpl());
    }

    public static services.Character createCharacter(){
        return new CharacterContract(new CharacterImpl());
    }

    public static EditableScreen createEditableScreen(){
        return new EditableScreenContract(new EditableScreenImpl());
    }
    public static Environment createEnvironment() {
        return new EnvironmentContract(new EnvironmentImpl());
    }
    public static Engine createEngine(){
        return new EngineContract(new EngineImpl());
    }
    public static Guard createGuard(){
        return new GuardImpl();
    }
    public static Screen createScreen(){
        return new ScreenContract(new ScreenImpl());
    }
    public static ScreenManager createScreenManager(){
        return new ScreenManagerImpl();
    }
}