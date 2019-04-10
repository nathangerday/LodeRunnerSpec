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
}