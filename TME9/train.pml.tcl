# maxx 1
# maxx 2
# maxx 3
# maxx 4
# maxx 5
# maxx 6
# maxx 7
# maxx 8
# maxx 9
# Scaler 0.312500, MH 1920
wm title . "scenario"
wm geometry . 1760x600+650+100
canvas .c -width 800 -height 800 \
	-scrollregion {0c -1c 30c 100c} \
	-xscrollcommand ".hscroll set" \
	-yscrollcommand ".vscroll set" \
	-bg white -relief raised -bd 2
scrollbar .vscroll -relief sunken  -command ".c yview"
scrollbar .hscroll -relief sunken -orient horiz  -command ".c xview"
pack append . \
	.vscroll {right filly} \
	.hscroll {bottom fillx} \
	.c {top expand fill}
.c yview moveto 0
# ProcLine[1] stays at 0 (Used 0 nobox 0)
.c create rectangle 104 0 180 20 -fill black
# ProcLine[1] stays at 0 (Used 0 nobox 0)
.c create rectangle 103 -2 177 18 -fill ivory
.c create text 140 8 -text "0::init:"
.c create text 70 32 -fill #eef -text "1"
.c create line 140 32 1580 32 -fill #eef -dash {6 4}
.c create line 300 36 300 20 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 0 to 1 (Used 0 nobox 0)
.c create rectangle 264 24 340 44 -fill black
# ProcLine[2] stays at 1 (Used 0 nobox 0)
.c create rectangle 263 22 337 42 -fill ivory
.c create text 300 32 -text "1:Sensor"
.c create text 70 56 -fill #eef -text "3"
.c create line 140 56 1580 56 -fill #eef -dash {6 4}
.c create line 460 36 460 44 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[3] from 0 to 3 (Used 0 nobox 0)
.c create rectangle 424 48 500 68 -fill black
# ProcLine[3] stays at 3 (Used 0 nobox 0)
.c create rectangle 423 46 497 66 -fill ivory
.c create text 460 56 -text "2:Sensor"
.c create text 70 80 -fill #eef -text "5"
.c create line 140 80 1580 80 -fill #eef -dash {6 4}
.c create line 620 36 620 68 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[4] from 0 to 5 (Used 0 nobox 0)
.c create rectangle 584 72 660 92 -fill black
# ProcLine[4] stays at 5 (Used 0 nobox 0)
.c create rectangle 583 70 657 90 -fill ivory
.c create text 620 80 -text "3:Sensor"
.c create text 70 104 -fill #eef -text "7"
.c create line 140 104 1580 104 -fill #eef -dash {6 4}
.c create line 780 36 780 92 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[5] from 0 to 7 (Used 0 nobox 0)
.c create rectangle 744 96 820 116 -fill black
# ProcLine[5] stays at 7 (Used 0 nobox 0)
.c create rectangle 743 94 817 114 -fill ivory
.c create text 780 104 -text "4:Sensor"
.c create text 70 128 -fill #eef -text "9"
.c create line 140 128 1580 128 -fill #eef -dash {6 4}
.c create line 940 36 940 116 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[6] from 0 to 9 (Used 0 nobox 0)
.c create rectangle 918 120 966 140 -fill black
# ProcLine[6] stays at 9 (Used 0 nobox 0)
.c create rectangle 916 118 964 138 -fill ivory
.c create text 940 128 -text "5:Feu"
.c create text 70 152 -fill #eef -text "11"
.c create line 140 152 1580 152 -fill #eef -dash {6 4}
.c create line 1100 36 1100 140 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[7] from 0 to 11 (Used 0 nobox 0)
.c create rectangle 1078 144 1126 164 -fill black
# ProcLine[7] stays at 11 (Used 0 nobox 0)
.c create rectangle 1076 142 1124 162 -fill ivory
.c create text 1100 152 -text "6:Feu"
.c create text 70 176 -fill #eef -text "13"
.c create line 140 176 1580 176 -fill #eef -dash {6 4}
.c create line 1260 36 1260 164 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[8] from 0 to 13 (Used 0 nobox 0)
.c create rectangle 1230 168 1294 188 -fill black
# ProcLine[8] stays at 13 (Used 0 nobox 0)
.c create rectangle 1228 166 1292 186 -fill ivory
.c create text 1260 176 -text "7:Train"
.c create text 70 200 -fill #eef -text "15"
.c create line 140 200 1580 200 -fill #eef -dash {6 4}
.c create line 1420 36 1420 188 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[9] from 0 to 15 (Used 0 nobox 0)
.c create rectangle 1390 192 1454 212 -fill black
# ProcLine[9] stays at 15 (Used 0 nobox 0)
.c create rectangle 1388 190 1452 210 -fill ivory
.c create text 1420 200 -text "8:Train"
.c create text 70 224 -fill #eef -text "17"
.c create line 140 224 1580 224 -fill #eef -dash {6 4}
.c create line 1580 36 1580 212 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[10] from 0 to 17 (Used 0 nobox 0)
.c create rectangle 1539 216 1625 236 -fill black
# ProcLine[10] stays at 17 (Used 0 nobox 0)
.c create rectangle 1538 214 1622 234 -fill ivory
.c create text 1580 224 -text "9:Control"
.c create line 1420 258 940 258 -fill darkblue -tags mesg -width 2
.c create line 940 258 460 258 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 248 -fill #eef -text "19"
.c create line 140 248 1580 248 -fill #eef -dash {6 4}
.c create line 1420 216 1420 236 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[9] from 15 to 19 (Used 1 nobox 0)
# ProcLine[9] stays at 19 (Used 1 nobox 1)
.c create rectangle 1383 238 1457 258 -fill white -width 0
.c create text 1420 248 -text "senseI!1"
.c create text 70 272 -fill #eef -text "21"
.c create line 140 272 1580 272 -fill #eef -dash {6 4}
.c create line 460 72 460 260 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[3] from 3 to 21 (Used 1 nobox 1)
# ProcLine[3] stays at 21 (Used 1 nobox 1)
.c create rectangle 428 262 492 282 -fill white -width 0
.c create text 460 272 -text "sense?1"
.c create line 1260 306 780 306 -fill darkblue -tags mesg -width 2
.c create line 780 306 300 306 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 296 -fill #eef -text "23"
.c create line 140 296 1580 296 -fill #eef -dash {6 4}
.c create line 1260 192 1260 284 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[8] from 13 to 23 (Used 1 nobox 1)
# ProcLine[8] stays at 23 (Used 1 nobox 1)
.c create rectangle 1223 286 1297 306 -fill white -width 0
.c create text 1260 296 -text "senseI!1"
.c create text 70 320 -fill #eef -text "25"
.c create line 140 320 1580 320 -fill #eef -dash {6 4}
.c create line 300 48 300 308 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 1 to 25 (Used 1 nobox 1)
# ProcLine[2] stays at 25 (Used 1 nobox 1)
.c create rectangle 268 310 332 330 -fill white -width 0
.c create text 300 320 -text "sense?1"
.c create line 1100 354 1260 354 -fill darkblue -tags mesg -width 2
.c create line 1260 354 1420 354 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 344 -fill #eef -text "27"
.c create line 140 344 1580 344 -fill #eef -dash {6 4}
.c create line 1100 168 1100 332 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[7] from 11 to 27 (Used 1 nobox 1)
# ProcLine[7] stays at 27 (Used 1 nobox 1)
.c create rectangle 1052 334 1148 354 -fill white -width 0
.c create text 1100 344 -text "change!RED"
.c create text 70 368 -fill #eef -text "29"
.c create line 140 368 1580 368 -fill #eef -dash {6 4}
.c create line 1420 264 1420 356 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[9] from 19 to 29 (Used 1 nobox 1)
# ProcLine[9] stays at 29 (Used 1 nobox 1)
.c create rectangle 1372 358 1468 378 -fill white -width 0
.c create text 1420 368 -text "change?RED"
.c create line 940 402 1100 402 -fill darkblue -tags mesg -width 2
.c create line 1100 402 1260 402 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 392 -fill #eef -text "31"
.c create line 140 392 1580 392 -fill #eef -dash {6 4}
.c create line 940 144 940 380 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[6] from 9 to 31 (Used 1 nobox 1)
# ProcLine[6] stays at 31 (Used 1 nobox 1)
.c create rectangle 892 382 988 402 -fill white -width 0
.c create text 940 392 -text "change!RED"
.c create text 70 416 -fill #eef -text "33"
.c create line 140 416 1580 416 -fill #eef -dash {6 4}
.c create line 1260 312 1260 404 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[8] from 23 to 33 (Used 1 nobox 1)
# ProcLine[8] stays at 33 (Used 1 nobox 1)
.c create rectangle 1212 406 1308 426 -fill white -width 0
.c create text 1260 416 -text "change?RED"
.c create line 460 450 1020 450 -fill darkblue -tags mesg -width 2
.c create line 1020 450 1580 450 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 440 -fill #eef -text "35"
.c create line 140 440 1580 440 -fill #eef -dash {6 4}
.c create line 460 288 460 428 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[3] from 21 to 35 (Used 1 nobox 1)
# ProcLine[3] stays at 35 (Used 1 nobox 1)
.c create rectangle 423 430 497 450 -fill white -width 0
.c create text 460 440 -text "signal!1"
.c create text 70 464 -fill #eef -text "37"
.c create line 140 464 1580 464 -fill #eef -dash {6 4}
.c create line 1580 240 1580 452 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[10] from 17 to 37 (Used 1 nobox 1)
# ProcLine[10] stays at 37 (Used 1 nobox 1)
.c create rectangle 1556 454 1604 474 -fill white -width 0
.c create text 1580 464 -text "gI2?1"
.c lower grid
.c raise mesg
