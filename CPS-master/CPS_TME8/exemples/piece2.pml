
mtype { pile, face };

mtype cote = pile;

active proctype piece() {
    if
    ::true -> cote = pile ;
    ::true -> cote = face ;
    fi
    
    if
    ::(cote == pile) -> printf("Choix = pile\n");
    ::(cote == face) -> printf("Choix = face\n");
    fi
}