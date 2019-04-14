package utils;

import java.util.Set;

import data.Entity;
import services.Character;

public class Util {
    public static boolean constainsCharacter(Set<Entity> content){
        for(Entity e : content){
            if(e instanceof Character){
                return true;
            }
        }
        return false;
    }

    public static Character getCharacter(Set<Entity> content){
        for(Entity e : content){
            if(e instanceof Character){
                return (Character)e;
            }
        }
        return null;
    }
}