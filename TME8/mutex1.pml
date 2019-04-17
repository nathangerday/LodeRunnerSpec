bool ask0 = false;
bool ask1 = false;

byte turn = 1;

int cs_counter = 0;

proctype proc1(int repeat){
    int count = 0;

    do
        ::count<repeat->ask0 = true; do
                                            ::ask1 -> if
                                                        ::turn != 0 -> ask0 = false; turn == 0; ask0 = true;
                                                        ::else ->
                                                    fi
                                            ::else -> break
                                        od;

        cs_counter++;
        assert(cs_counter == 1);
        
        cs_counter--;

        turn = 1;
        ask0 = false;

        count++;

        ::else->break;
    od
}

proctype proc2(int repeat){
    int count = 0;
    do 
        :: count < repeat -> ask1 = true; do
                                            ::ask0 -> if
                                                        ::turn != 1 -> ask1 = false; turn == 1; ask1 = true;
                                                        ::else->
                                                    fi
                                            ::else -> break
                                        od;
            cs_counter++;
            assert(cs_counter == 1);
            cs_counter--;
            turn = 0;
            ask1 = false;

            count++;
        ::else -> break
    od
}

init{
    atomic{
        run proc1(30);
        run proc2(30);
    }
}