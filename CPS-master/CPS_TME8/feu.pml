mtype = {ROUGE, ORANGE, VERT}
mtype = {PANNE, INITIALISATION, ACTIF}
mtype = {CLIGNOTANT, CONTINU}

typedef Feu{
 mtype couleur;
 mtype etat;
 mtype mode;
}

Feu myFeu;

active proctype exec(){
  myFeu.couleur = ROUGE;
  myFeu.etat = ACTIF;
  myFeu.mode = CONTINU;
  restart :
  if
  :: myFeu.couleur == ROUGE -> myFeu.couleur = VERT; goto restart ;
  :: myFeu.couleur == VERT -> myFeu.couleur = ORANGE; goto restart ;
  :: myFeu.couleur == ORANGE -> myFeu.couleur = ROUGE; goto restart ;
  :: true -> myFeu.etat = PANNE; myFeu.couleur = ORANGE; myFeu.mode = CLIGNOTANT; 
  fi
}

init{
  myFeu.couleur = ORANGE;
  myFeu.etat = INITIALISATION;
  myFeu.mode = CLIGNOTANT;
  run exec();
}

