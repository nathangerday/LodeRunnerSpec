
mtype { pile, face };

mtype cote = pile;

active proctype piece() {
    if
    ::true -> cote = pile ;
    ::true -> cote = face ;
    fi
}

active proctype compte_face() {
    byte ratio = 0 ;
    
    if
    ::(cote == pile) -> ratio++ ;
    ::(cote == face) -> ratio-- ;
    fi
}