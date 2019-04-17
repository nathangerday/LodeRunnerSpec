mtype {Rouge, Orange, Vert, Indeter}
mtype couleur = Indeter;
bool clignotant = false;
bool isWorking = true;

proctype feu(){
    not_init:
    clignotant = false;
    couleur = Rouge;
    goto normal;

    normal:
    do
        :: couleur == Vert -> couleur = Orange;
        :: couleur == Orange -> couleur = Rouge;
        :: couleur == Rouge -> couleur = Vert;
        :: !isWorking -> goto panne;
    od

    panne:
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



init{
    couleur = Orange;
    clignotant = true;
    run feu();
}