bool ask0, ask1 ;
byte turn = 0;
int cs_counter = 0;

proctype proc1(int repeat){
    byte count = 0;
    byte count;
    for (count : 0..repeat-1){

        ask0 = true;
        while(ask1 == true) {
            if(turn!=0) {
                ask0 = false;
                while (turn != 0) {
                // attente active
                }
                
                ask0 = true;
            }
        }

        // Critical section
        printf("[Proc1]: entrée section critique...\n");
        cs_counter++;
        assert (cs_counter == 1);
        
        int msec = rand() % max_sleep; // Remarque: non-uniforme
        
        usleep(msec);

        cs_counter--;
        printf("[Proc1]: sortie section critique...\n");

        turn = 1;
        ask0 = false;


    }


}

proctype proc2(int repeat){


    
}