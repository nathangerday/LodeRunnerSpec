bool b1 = true;
bool b2 = true;

int turn = 1;

int cs_counter = 0;

proctype proc1(int repeat){
    int count = 0;

    do
        ::count < repeat -> b1 = false;
                            C1:
                                if
                                    ::turn != 1 -> C2:
                                                    if
                                                        ::!b2 -> goto C2;
                                                        ::else -> turn = 1; goto C1;
                                                    fi
                                    cs_counter++;
                                    assert(cs_counter == 1);
                                    cs_counter--;
                                    b1 = true;
                                    count++;
                                    ::else->
                                fi
        ::else->break
    od
}


proctype proc2(int repeat){
    int count = 0;
    do
        ::count < repeat -> b2 = false; C1:
                                        if
                                            ::turn != 2 -> C2:
                                                            if 
                                                                ::!b1 -> goto C2;
                                                                ::else -> turn = 2; goto C1;
                                                            fi
                                            cs_counter++;
                                            assert(cs_counter == 1);
                                            cs_counter--;
                                            b2 = true;
                                            count++;
                                            ::else->
                                        fi
        ::else->break
    od
}

init{
    atomic{
        run proc1(30);
        run proc2(30);
    }
}