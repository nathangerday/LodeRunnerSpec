
/* Fichier pileouface.pml */
chan piece = [0] of { bit };

proctype lanceur() {

  do
  :: piece!0 
  :: piece!1
  od
}

proctype compteur() {
  byte nb_piles;
  byte nb_faces;
  
  nb_piles = 0;
  nb_faces = 0;
  
  do
  :: piece?0 -> 
       printf("Pile : %d", nb_piles);
       if 
       ::nb_piles < 10 -> nb_piles ++
       fi
  :: piece?1 ->
       printf("Face : %d", nb_faces);
       if 
       ::nb_faces < 10 -> nb_faces ++
       fi
  od
}


init {
  run lanceur();
  run compteur()
}