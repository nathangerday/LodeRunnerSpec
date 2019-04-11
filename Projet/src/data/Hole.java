package data;

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
    
}