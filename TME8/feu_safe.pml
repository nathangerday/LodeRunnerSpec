mtype {Rouge, Orange, Vert, Indeter}
mtype couleur = Indeter;
bool clignotant = false;
bool isWorking = true;
chan ch = [0] of {mtype, mtype, bool}

proctype feu(){
    mtype couleur1;
    mtype couleur2;
    mtype old_couleur;
    not_init:
    clignotant = false;
    couleur = Rouge;
    goto normal;
    
    normal:
    
    do
        :: couleur == Vert -> couleur1 = couleur; couleur = Orange; couleur2 = couleur; ch ! couleur1, couleur2, clignotant;
        :: couleur == Orange -> couleur1 = couleur; couleur = Rouge;  couleur2 = couleur; ch ! couleur1, couleur2, clignotant;
        :: couleur == Rouge ->  couleur1 = couleur; couleur = Vert;  couleur2 = couleur; ch ! couleur1, couleur2, clignotant;
        :: !isWorking -> goto panne;
    od

    panne:
    old_couleur = couleur;
    clignotant = true;
    couleur = Orange;
    ch ! old_couleur, couleur, clignotant;
    if
        ::true->goto panne;
    fi
}

active proctype inject_panne(){
    if
        ::true -> isWorking = false;
        ::true -> isWorking = true;
    fi
}

active proctype observer(){
    mtype c1;
    mtype c2;
    bool clign;
    loop:
        ch ? c1, c2, clign;
        assert((clign || !(c1 == Rouge && c2 == Orange))); 
        assert((clign || !(c1 == Orange && c2 == Vert))); 
        assert((clign || !(c1 == Vert && c2 == Rouge)));
        assert(!clign || c2 != Rouge); 
        assert(!clign || c2 != Vert); 
        goto loop;

}


init{
    couleur = Orange;
    clignotant = true;
    run feu();
}