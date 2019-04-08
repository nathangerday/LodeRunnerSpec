public interface Character{
    /* Observators */
    public Environment getEnvi();
    public int getHgt();
    public int getWdt();


    /* Constructors */

    //\pre s.getCellNature(x, y) == EMP
    public void init(Screen s, int x, int y);

    /* Invariants */
    //\inv getEnvi().getCellNature(getWdt(), getHdt()) \in {EMP, HOL, LAD, HDR}
    //\inv (\Exists Character c \in getEnvi().getCellContent(getWdth(), getHdt()))
    //          \impl c == this

    //TODO A verifier pour le 2eme invariants, (2eme erreur que le prof avait corrige au tableau)

    /* Operators */
    //TODO goLeft, goRight, goUp, goDown
    public void goLeft();
    public void goRight();
    public void goUp();
    public void goDown();


    
}