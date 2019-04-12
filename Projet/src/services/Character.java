package services;

import data.Entity;

public interface Character extends Entity{
    /* Observators */
    public Environment getEnvi();
    public int getHgt();
    public int getWdt();


    /* Constructors */

    //\pre s != null
    //\pre x >= 0
    //\pre x < s.getWidth()
    //\pre y >= 0
    //\pre y < s.getHeight()
    //\pre s.getCellNature(x, y) == EMP
    //TODO Surement ajouter une precondition pour le fait qu'on ne veut qu'un seul personnage par case (Ou pas en fait, a la fin du sujet on dit qu'on peut avoir plusieurs character sur la meme case pour detecter une partie perdu)
    //TODO Veut-on directement un environment ici ? Dans le sujet on donne un Screen, sauf qu'on veut que getEnvi renvoi un environment....
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