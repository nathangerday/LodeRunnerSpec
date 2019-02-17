
/* Fichier pingpong.pml */
chan ppchan = [0] of { bit };

proctype ping() {
  bit x;

  do
  :: ppchan?(x) -> printf("Ping : %d\n", x);
                   ppchan!0
  od
}

proctype pong() {
  bit x;

  do
  :: ppchan?(x) -> printf("Pong : %d\n", x);
                   ppchan!1
  od
}

init {
  run ping();
  run pong();
  ppchan!1
}