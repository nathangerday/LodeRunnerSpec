package data;


public class Item implements Entity{
    private int id; //TODO Peut etre gerer les id avec une variable static incremente
    private ItemType nature;
    private int x, y;
    
    public Item(int id, ItemType nature, int x, int y){
        this.id = id;
        this.nature = nature;
        this.x = x;
        this.y = y;
    }

    public int getId(){
        return this.id;
    }

    public ItemType getNature(){
        return this.nature;
    }

    public int getHgt(){
        return this.y;
    }

    public int getWdt(){
        return this.x;
    }
}