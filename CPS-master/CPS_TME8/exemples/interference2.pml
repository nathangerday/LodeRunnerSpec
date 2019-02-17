
byte z = 0;

active proctype proc1() {
  byte y = 10;

  z = 2;
  y = y + z;
  assert(y == 12);
}

active proctype proc2() {
  z = z + 1;
}