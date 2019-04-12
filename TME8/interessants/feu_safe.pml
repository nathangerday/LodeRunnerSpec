mtype = {indetermine,rouge,vert,orange};
mtype = { initialisation,actif};
bool panne ; 
bool clignotant;
mtype light = indetermine;
mtype mode ;



chan ch = [2] of {mtype, mtype}
inline check(){

     assert( ! ( clignotant && (light==rouge || light == vert)  )  );

}

proctype feu (){
    
    mtype etat_avant;
    mtype etat_apres;

    do
    
        :: mode == initialisation && !panne  && clignotant ->
                                                            etat_avant = light; 
                                                            mode =actif; 
                                                            light = rouge;
                                                            clignotant =false; 
                                                            etat_apres = light;
                                                            check();   ch ! etat_avant,etat_apres
        :: mode == actif && !panne && !clignotant-> if 
                                    :: light == rouge && !panne && !clignotant-> etat_avant = light;  light = vert; etat_apres = light;ch ! etat_avant,etat_apres
                                    :: light == vert && !panne && !clignotant->  etat_avant = light; light = orange; etat_apres = light; ch ! etat_avant,etat_apres
                                    :: light == orange && !panne && !clignotant->  etat_avant = light; light = rouge; etat_apres = light;ch ! etat_avant,etat_apres
                                    fi
        :: panne -> light = orange; clignotant =true; etat_apres = light; ch ! etat_avant,etat_apres
        :: else ->

        
       
    od
}

proctype check_light(){
    mtype e1;
    mtype e2;
    ch ? e1 ,e2;
  
    do
        
        ::e1 == rouge -> assert (e2!=orange); check();
        ::e1 == orange -> assert (e2 != vert); check();
        ::e1 == vert -> assert (e2 != rouge); check();


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
        run check_light();
    }
}