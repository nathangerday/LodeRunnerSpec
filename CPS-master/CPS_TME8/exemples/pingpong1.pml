
/* Fichier pingpong.pml */
chan ppchan = [0] of { bit };

proctype ping() {
  bit x;

  ppchan?(x) ;
  printf("Ping : %d\n", x);
  ppchan!0
}

proctype pong() {
  bit x;

  ppchan?(x) ;
  printf("Pong : %d\n", x);
  ppchan!1
}

init {
  run ping();
  run pong();
  ppchan!1
}