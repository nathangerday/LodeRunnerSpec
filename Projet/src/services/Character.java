package services;

import data.Entity;

public interface Character extends Entity{
    /* Observators */
    public Environment getEnvi();
    public int getHgt();
    public int getWdt();


    /* Constructors */

    //\pre s.getCellNature(x, y) == EMP
    //TODO Veut-on directement un environment ici ? Dans le sujet on donne un Screen, sauf qu'on veut que getEnvi renvoi un environment. Il faut peut etre créer ici l'environement a partir du screen...
    public void init(Environment s, int x, int y);

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