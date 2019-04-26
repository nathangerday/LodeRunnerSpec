package data;

import java.util.Objects;

public class Hole{
    private int x, y, time;

    public Hole(int x, int y, int time){
        this.x = x;
        this.y = y;
        this.time = time;
    }

    public Hole(int x, int y){
        this(x, y, 0);
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getTime(){
        return this.time;
    }

    public void incTime(){
        this.time++;
    }

    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        }

        if(other instanceof Hole){
            Hole h = (Hole)other;
            if(h.x == x && h.y == y && h.time == time){
                return true;
            }
        }

        return false;

    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y, time);
    }
    
}