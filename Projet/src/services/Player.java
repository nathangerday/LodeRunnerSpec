package services;

public interface Player extends /* includes */ Character{
    /* Observators */
    
    public Engine getEngine();
    

    /* Operators */
    public void step();

}