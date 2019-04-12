mtype = {indetermine,rouge,vert,orange};
mtype = { initialisation,actif};
bool panne ;
bool clignotant;
mtype light = indetermine;
mtype mode ;


proctype feu (){
    
    do
        :: mode == initialisation && !panne  && clignotant -> mode =actif; light = rouge;clignotant =false;
        :: mode == actif && !panne  && !clignotant-> if 
                                    :: light == rouge && !panne && !clignotant-> light = vert;
                                    :: light == vert && !panne && !clignotant-> light = orange;
                                    :: light == orange && !panne && !clignotant-> light = rouge;
                                    fi
        :: panne -> light = orange; clignotant =true; 
        :: else ->
    od
}

active proctype injection_panne(){

    do
        ::!panne -> panne = true
        ::true -> panne = false
    od
}





init{

    light = orange;
    clignotant =true;
    mode = initialisation;
    atomic{
        run feu();
        run injection_panne();
    }
}