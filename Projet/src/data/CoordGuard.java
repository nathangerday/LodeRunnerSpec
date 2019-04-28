package data;

public class CoordGuard extends Coord{

    private GuardType type;
    

    public CoordGuard(int x, int y, GuardType t){
        super(x, y);
        this.type = t;
    }


    public GuardType getType(){
        return this.type;
    }
}