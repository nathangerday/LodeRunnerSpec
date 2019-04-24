package utils;

import java.util.Set;

import data.Entity;
import data.Item;
import data.ItemType;
import services.Character;
import services.Guard;

public class Util {
    public static boolean constainsGuard(Set<Entity> content){
        for(Entity e : content){
            if(e instanceof Guard){
                return true;
            }
        }
        return false;
    }

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

    public static boolean containsTreasure(Set<Entity> content){
        for(Entity e : content){
            if(e instanceof Item && ((Item)e).getNature() == ItemType.Treasure){
                return true;
            }
        }
        return false;
    }

    public static boolean removeTreasure(Set<Entity> content){
        Item toDelete = null;
        for(Entity e : content){
            if(e instanceof Item && ((Item)e).getNature() == ItemType.Treasure){
                toDelete = (Item)e;
            }
        }

        return content.remove(toDelete);
    }
    
}