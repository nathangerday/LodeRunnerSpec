
mtype { pile, face };

mtype cote = pile;

active proctype piece() {
restart:
    if
    ::true -> cote = pile ; goto restart ;
    ::true -> cote = face ; goto restart 
    fi
}