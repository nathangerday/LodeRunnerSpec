package services;

public interface Player extends /* includes */ Character{
    /* Observators */
    
    public Engine getEngine();
    

    /* Constructors */
    public void init(int x, int y, Engine e);


    /* Operators */
    public void step();

}