package utils;

import java.util.Set;

import data.Entity;
import data.Item;
import data.ItemType;
import services.Character;
import services.Guard;
import services.Player;

public class Util {
    public static boolean containsGuard(Set<Entity> content){
        for(Entity e : content){
            if(e instanceof Guard){
                return true;
            }
        }
        return false;
    }

    public static boolean containsPlayer(Set<Entity> content){
        for(Entity e : content){
            if(e instanceof Player){
                return true;
            }
        }
        return false;
    }

    public static Guard getGuard(Set<Entity> content){
        for(Entity e : content){
            if(e instanceof Guard){
                return (Guard)e;
            }
        }
        return null;
    }

    public static Player getPlayer(Set<Entity> content){
        for(Entity e : content){
            if(e instanceof Player){
                return (Player)e;
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

    public static Item removeTreasure(Set<Entity> content){
        Item toDelete = null;
        for(Entity e : content){
            if(e instanceof Item && ((Item)e).getNature() == ItemType.Treasure){
                toDelete = (Item)e;
            }
        }
        content.remove(toDelete);
        return toDelete;
    }
    
}