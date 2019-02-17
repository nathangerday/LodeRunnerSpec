
proctype hello() {
  printf("Hello\n")
}

proctype world() {
  printf("World\n")
}

init {
  run hello();
  run world();
}