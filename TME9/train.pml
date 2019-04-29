mtype { RED, GREEN };
int voie = 0;

chan gI1 = [0] of {bool}
chan gI2 = [0] of {bool}
chan gO1 = [0] of {bool}
chan gO2 = [0] of {bool}
chan sI1 = [0] of {bool}
chan sI2 = [0] of {bool}
chan sO1 = [0] of {bool}
chan sO2 = [0] of {bool}
chan w1 = [0] of {bool}
chan w2 = [0] of {bool}
chan c2 = [0] of {bool}
chan c2 = [0] of {bool}

proctype Sensor(chan sense; chan signal) {
    bool b;
    end: // pas de deadlock ici
        do
            ::sense?b -> signal!true
        od
}

proctype Feu(chan swtch; chan change) {
    bool b;
    mtype color;
    end_red_color:
        color = RED ;
        change!color ;
    end_wait_swtch:
        swtch?(b) -> if
                        ::color == RED -> goto end_green_color
                        ::color == GREEN -> goto end_red_color
                    fi
    end_green_color:
    color = GREEN ;
    change!color ;
    goto end_wait_swtch
}

proctype Train(chan senseI, chan senseO, chan change){
    loin:
        do
            ::true -> goto arrive;
            ::else ->
        od
    arrive:
        senseI ! true; //Probleme 
        change ? color -> if
                            ::color == GREEN -> goto voieu;
                            ::color == RED -> goto arrive;
                        fi
    voieu:
        voie++;
    sortie:
        senseO ! true;
        voie--;
        goto loin;
}


proctype Control(chan gI1, chan gI2, chan gO1, chan gO2, chan w1, chan w2){
    bool b;
    llrr:
        if
            ::gI1 ? b -> goto alrr;
            ::gI2 ? b -> goto larr;
        fi
    alrr:
        if
            ::true -> w1 ! true; goto alvr;
            ::gI2 ? b -> goto aarr;
        fi
    larr:
        if 
            ::true -> w2 ! true; goto larv;
            ::gI1 ? b -> goto aarr;
        fi
    aarr:
        if 
            ::true -> w1 ! true; goto aavr;
            ::true -> w2 ! true; goto aarv;
        fi
    alvr:
        if
            ::true -> goto ulvr;
            ::gI2 ? b -> goto aavr;
        fi
    aavr:
        if
            ::true -> goto uavr;
        fi
    aarv:
        if
            ::true -> goto aurv;
        fi
    larv:
        if
            ::true -> goto lurv;
            ::gI1 ? b -> goto aarv;
        fi
    ulvr:
        fi 
            ::gO1 ? b -> goto slvr;
            ::gI2 ? b -> goto uavr;
        fi
    uavr: 
        if
            ::gO1 ? b -> goto savr;
        fi
    aurv:
        if
            ::gO2 ? b -> goto asrv;
        fi
    lurv:
        if
            ::gI1 ? b -> goto aurv;
            ::g02 ? b -> goto lsrv;
        fi
    
}