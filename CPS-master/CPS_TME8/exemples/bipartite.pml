
chan send = [0] of { bit };
chan ack = [0] of { bit };

active proctype emetteur() {
    mtype m;
   
    do
    ::send!0 ;
      ack?m ;
      assert (m==1);
    od
    
}

active proctype recepteur1() {
    bit m;
    send?m ;
    assert (m==0);
    ack!1 ;
}

active proctype recepteur2() {
    bit m;
    send?m ;
    assert (m==0);
    ack!1;
}