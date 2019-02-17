
bit go = 0;

mtype = { act1_1, act1_2, act2_1, act2_2, act2_3 };

active proctype proc1() {
    mtype act;
      
    act = act1_1;
    
    go = 1;

    act = act1_2;
}

active proctype proc2() {
    mtype act;
    
    if
    ::true ->  act = act2_1;               
    ::true ->  act = act2_2;               
    fi
    
    go == 1 ;;
    
    act = act2_3;              
}