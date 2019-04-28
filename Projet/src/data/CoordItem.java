package data;

public class CoordItem extends Coord{
    private ItemType type;

    public CoordItem(int x, int y, ItemType type){
        super(x, y);
        this.type = type;
    }

    public ItemType getItemType(){
        return this.type;
    }
}