
mtype { pile, face };

mtype cote = pile;

active proctype piece() {
    if
    ::true -> cote = pile ;
    ::true -> cote = face ;
    fi
}