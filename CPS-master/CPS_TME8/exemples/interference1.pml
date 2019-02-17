
byte z = 0;

active proctype proc1() {
  byte y = 10;

  z = 2;
  y = y + z;
  assert(y == 12);
}