package services;

public interface Player extends /* includes */ Character{
    /* Observators */
    
    public Engine getEngine();
    

    /* Constructors */
    //\pre e != null
    //\pre x >= 0
    //\pre x < e.getEnvironment().getWidth()
    //\pre y >= 0
    //\pre y < e.getEnvironment().getHeight()
    //\pre e.getEnvironment().getCellNature(x, y) == EMP
    public void init(int x, int y, Engine e);


    /* Operators */
    public void step();
    public void digLeft();
    public void digRight();

}