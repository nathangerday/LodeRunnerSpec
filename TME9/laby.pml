/** La recherche en largeur permet de trouver le plus court chemin mais prend plus de mémoire que la parcourt en profondeur qui va trouver un chemin "aléatoire" */

mtype {NORTH, SOUTH, EAST, WEST, EXIT}
chan go = [0] of {mtype}

active proctype observateur() {
    mtype dir ;
    do
        ::go?(dir) -> 
            if
                ::dir==EXIT -> printf("go EXIT") ; goto exit
                ::else -> printf("go %e\n", dir) // %e pour afficher un mtype
            fi
    od   
    exit:
        false
}

active proctype loby(){
    entree:
        do
            ::true -> go ! NORTH; goto case51;
            ::else ->
        od
    case13:
        do
            ::true -> go ! EAST; goto case23;
            ::else ->
        od
    case14:
        do
            ::true -> go ! NORTH; goto case15;
            ::else ->
        od
    case15:
        do
            ::true -> go ! NORTH; goto exit;
            ::true -> go ! SOUTH; goto case14;
            ::true -> go ! EAST; goto case25;
            ::else ->
        od
    case21:
        do
            ::true -> go ! NORTH; goto case31;
            ::true -> go ! EAST; goto case22;
            ::else ->
        od
    case22:
        do
            ::true -> go ! NORTH; goto case23;
            ::true -> go ! SOUTH; goto case21;
            ::true -> go ! EAST; goto case32;
            ::else ->
        od
    case23:
        do
            ::true -> go ! NORTH; goto case24;
            ::true -> go ! SOUTH; goto case22;
            ::true -> go ! WEST; goto case13;
            ::else ->
        od
    case24:
        do
            ::true -> go ! EAST; goto case34;
            ::true -> go ! SOUTH; goto case23;
            ::else ->
        od
    case25:
        do
            ::true -> go ! WEST; goto case15;
            ::true -> go ! EAST; goto case35;
            ::else ->
        od
    case31:
        do
            ::true -> go ! WEST; goto case21;
            ::true -> go ! EAST; goto case41;
            ::else ->
        od
    case32:
        do
            ::true -> go ! WEST; goto case22;
            ::true -> go ! EAST; goto case42;
            ::else ->
        od
    case34:
        do
            ::true -> go ! WEST; goto case24;
            ::true -> go ! EAST; goto case44;
            ::else ->
        od
    case35:
        do
            ::true -> go ! WEST; goto case25;
            ::true -> go ! EAST; goto case45;
            ::else ->
        od
    case41:
        do
            ::true -> go ! WEST; goto case31;
            ::else ->
        od
    case42:
        do
            ::true -> go ! WEST; goto case32;
            ::true -> go ! EAST; goto case52;
            ::else ->
        od
    case44:
        do
            ::true -> go ! NORTH; goto case45;
            ::true -> go ! EAST; goto case34;
            ::else ->
        od
    case45:
        do
            ::true -> go ! WEST; goto case35;
            ::true -> go ! SOUTH; goto case44;
            ::else ->
        od
    case51:
        do
            ::true -> go ! NORTH; goto case52;
            ::else ->
        od
    case52:
        do
            ::true -> go ! NORTH; goto case53;
            ::true -> go ! WEST; goto case42;
            ::true -> go ! EAST; goto case51;
            ::else ->
        od
    case53:
        do
            ::true -> go ! NORTH; goto case54;
            ::true -> go ! SOUTH; goto case52;
            ::else ->
        od
    case54:
        do
            ::true -> go ! NORTH; goto case55;
            ::true -> go ! SOUTH; goto case53;
            ::else ->
        od
    case55:
        do
            ::true -> go ! SOUTH; goto case54;
            ::else ->
        od
    exit:
        do
            ::true -> go ! SOUTH; goto case15;
            ::true -> go ! EXIT; goto end;
        od

    end:

}



