package data;


public class Item implements Entity{
    private static int count = 0;
    private int id;
    private ItemType nature;
    private int x, y;
    
    public Item(ItemType nature, int x, int y){
        this.id = count++;
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

    public int getCol(){
        return this.x;
    }
}