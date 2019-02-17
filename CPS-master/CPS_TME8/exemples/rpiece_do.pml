
mtype { pile, face };

mtype cote = pile;

active proctype piece() {
    do
    ::true -> cote = pile
    ::true -> cote = face 
    od
}