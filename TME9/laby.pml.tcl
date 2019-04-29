# maxx 1
wm title . "scenario"
wm geometry . 480x600+650+100
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
# ProcLine[2] stays at 0 (Used 0 nobox 0)
.c create rectangle 275 0 329 20 -fill black
# ProcLine[2] stays at 0 (Used 0 nobox 0)
.c create rectangle 274 -2 326 18 -fill ivory
.c create text 300 8 -text "1:loby"
# ProcLine[1] stays at 0 (Used 0 nobox 0)
.c create rectangle 80 0 204 20 -fill black
# ProcLine[1] stays at 0 (Used 0 nobox 0)
.c create rectangle 79 -2 201 18 -fill ivory
.c create text 140 8 -text "0:observateur"
.c create line 300 42 220 42 -fill darkblue -tags mesg -width 2
.c create line 220 42 140 42 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 32 -fill #eef -text "1"
.c create line 140 32 300 32 -fill #eef -dash {6 4}
.c create line 300 36 300 20 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 0 to 1 (Used 1 nobox 0)
# ProcLine[2] stays at 1 (Used 1 nobox 1)
.c create rectangle 263 22 337 42 -fill white -width 0
.c create text 300 32 -text "go!NORTH"
.c create text 70 56 -fill #eef -text "3"
.c create line 140 56 300 56 -fill #eef -dash {6 4}
.c create line 140 36 140 44 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 0 to 3 (Used 1 nobox 1)
# ProcLine[1] stays at 3 (Used 1 nobox 1)
.c create rectangle 103 46 177 66 -fill white -width 0
.c create text 140 56 -text "go?NORTH"
.c create text 70 80 -fill #eef -text "5"
.c create line 140 80 300 80 -fill #eef -dash {6 4}
.c create line 140 72 140 68 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 3 to 5 (Used 1 nobox 1)
# ProcLine[1] stays at 5 (Used 1 nobox 1)
.c create rectangle 98 70 182 90 -fill white -width 0
.c create text 140 80 -text "go NORTH"
.c create line 300 114 220 114 -fill darkblue -tags mesg -width 2
.c create line 220 114 140 114 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 104 -fill #eef -text "7"
.c create line 140 104 300 104 -fill #eef -dash {6 4}
.c create line 300 48 300 92 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 1 to 7 (Used 1 nobox 1)
# ProcLine[2] stays at 7 (Used 1 nobox 1)
.c create rectangle 263 94 337 114 -fill white -width 0
.c create text 300 104 -text "go!NORTH"
.c create text 70 128 -fill #eef -text "9"
.c create line 140 128 300 128 -fill #eef -dash {6 4}
.c create line 140 96 140 116 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 5 to 9 (Used 1 nobox 1)
# ProcLine[1] stays at 9 (Used 1 nobox 1)
.c create rectangle 103 118 177 138 -fill white -width 0
.c create text 140 128 -text "go?NORTH"
.c create text 70 152 -fill #eef -text "11"
.c create line 140 152 300 152 -fill #eef -dash {6 4}
.c create line 140 144 140 140 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 9 to 11 (Used 1 nobox 1)
# ProcLine[1] stays at 11 (Used 1 nobox 1)
.c create rectangle 98 142 182 162 -fill white -width 0
.c create text 140 152 -text "go NORTH"
.c create line 300 186 220 186 -fill darkblue -tags mesg -width 2
.c create line 220 186 140 186 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 176 -fill #eef -text "13"
.c create line 140 176 300 176 -fill #eef -dash {6 4}
.c create line 300 120 300 164 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 7 to 13 (Used 1 nobox 1)
# ProcLine[2] stays at 13 (Used 1 nobox 1)
.c create rectangle 263 166 337 186 -fill white -width 0
.c create text 300 176 -text "go!NORTH"
.c create text 70 200 -fill #eef -text "15"
.c create line 140 200 300 200 -fill #eef -dash {6 4}
.c create line 140 168 140 188 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 11 to 15 (Used 1 nobox 1)
# ProcLine[1] stays at 15 (Used 1 nobox 1)
.c create rectangle 103 190 177 210 -fill white -width 0
.c create text 140 200 -text "go?NORTH"
.c create text 70 224 -fill #eef -text "17"
.c create line 140 224 300 224 -fill #eef -dash {6 4}
.c create line 140 216 140 212 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 15 to 17 (Used 1 nobox 1)
# ProcLine[1] stays at 17 (Used 1 nobox 1)
.c create rectangle 98 214 182 234 -fill white -width 0
.c create text 140 224 -text "go NORTH"
.c create line 300 258 220 258 -fill darkblue -tags mesg -width 2
.c create line 220 258 140 258 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 248 -fill #eef -text "19"
.c create line 140 248 300 248 -fill #eef -dash {6 4}
.c create line 300 192 300 236 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 13 to 19 (Used 1 nobox 1)
# ProcLine[2] stays at 19 (Used 1 nobox 1)
.c create rectangle 263 238 337 258 -fill white -width 0
.c create text 300 248 -text "go!NORTH"
.c create text 70 272 -fill #eef -text "21"
.c create line 140 272 300 272 -fill #eef -dash {6 4}
.c create line 140 240 140 260 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 17 to 21 (Used 1 nobox 1)
# ProcLine[1] stays at 21 (Used 1 nobox 1)
.c create rectangle 103 262 177 282 -fill white -width 0
.c create text 140 272 -text "go?NORTH"
.c create text 70 296 -fill #eef -text "23"
.c create line 140 296 300 296 -fill #eef -dash {6 4}
.c create line 140 288 140 284 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 21 to 23 (Used 1 nobox 1)
# ProcLine[1] stays at 23 (Used 1 nobox 1)
.c create rectangle 98 286 182 306 -fill white -width 0
.c create text 140 296 -text "go NORTH"
.c create line 300 330 220 330 -fill darkblue -tags mesg -width 2
.c create line 220 330 140 330 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 320 -fill #eef -text "25"
.c create line 140 320 300 320 -fill #eef -dash {6 4}
.c create line 300 264 300 308 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 19 to 25 (Used 1 nobox 1)
# ProcLine[2] stays at 25 (Used 1 nobox 1)
.c create rectangle 263 310 337 330 -fill white -width 0
.c create text 300 320 -text "go!NORTH"
.c create text 70 344 -fill #eef -text "27"
.c create line 140 344 300 344 -fill #eef -dash {6 4}
.c create line 140 312 140 332 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 23 to 27 (Used 1 nobox 1)
# ProcLine[1] stays at 27 (Used 1 nobox 1)
.c create rectangle 103 334 177 354 -fill white -width 0
.c create text 140 344 -text "go?NORTH"
.c create text 70 368 -fill #eef -text "29"
.c create line 140 368 300 368 -fill #eef -dash {6 4}
.c create line 140 360 140 356 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 27 to 29 (Used 1 nobox 1)
# ProcLine[1] stays at 29 (Used 1 nobox 1)
.c create rectangle 98 358 182 378 -fill white -width 0
.c create text 140 368 -text "go NORTH"
.c create line 300 402 220 402 -fill darkblue -tags mesg -width 2
.c create line 220 402 140 402 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 392 -fill #eef -text "31"
.c create line 140 392 300 392 -fill #eef -dash {6 4}
.c create line 300 336 300 380 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 25 to 31 (Used 1 nobox 1)
# ProcLine[2] stays at 31 (Used 1 nobox 1)
.c create rectangle 263 382 337 402 -fill white -width 0
.c create text 300 392 -text "go!SOUTH"
.c create text 70 416 -fill #eef -text "33"
.c create line 140 416 300 416 -fill #eef -dash {6 4}
.c create line 140 384 140 404 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 29 to 33 (Used 1 nobox 1)
# ProcLine[1] stays at 33 (Used 1 nobox 1)
.c create rectangle 103 406 177 426 -fill white -width 0
.c create text 140 416 -text "go?SOUTH"
.c create text 70 440 -fill #eef -text "35"
.c create line 140 440 300 440 -fill #eef -dash {6 4}
.c create line 140 432 140 428 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 33 to 35 (Used 1 nobox 1)
# ProcLine[1] stays at 35 (Used 1 nobox 1)
.c create rectangle 98 430 182 450 -fill white -width 0
.c create text 140 440 -text "go SOUTH"
.c create line 300 474 220 474 -fill darkblue -tags mesg -width 2
.c create line 220 474 140 474 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 464 -fill #eef -text "37"
.c create line 140 464 300 464 -fill #eef -dash {6 4}
.c create line 300 408 300 452 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 31 to 37 (Used 1 nobox 1)
# ProcLine[2] stays at 37 (Used 1 nobox 1)
.c create rectangle 263 454 337 474 -fill white -width 0
.c create text 300 464 -text "go!SOUTH"
.c create text 70 488 -fill #eef -text "39"
.c create line 140 488 300 488 -fill #eef -dash {6 4}
.c create line 140 456 140 476 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 35 to 39 (Used 1 nobox 1)
# ProcLine[1] stays at 39 (Used 1 nobox 1)
.c create rectangle 103 478 177 498 -fill white -width 0
.c create text 140 488 -text "go?SOUTH"
.c create text 70 512 -fill #eef -text "41"
.c create line 140 512 300 512 -fill #eef -dash {6 4}
.c create line 140 504 140 500 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 39 to 41 (Used 1 nobox 1)
# ProcLine[1] stays at 41 (Used 1 nobox 1)
.c create rectangle 98 502 182 522 -fill white -width 0
.c create text 140 512 -text "go SOUTH"
.c create line 300 546 220 546 -fill darkblue -tags mesg -width 2
.c create line 220 546 140 546 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 536 -fill #eef -text "43"
.c create line 140 536 300 536 -fill #eef -dash {6 4}
.c create line 300 480 300 524 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 37 to 43 (Used 1 nobox 1)
# ProcLine[2] stays at 43 (Used 1 nobox 1)
.c create rectangle 263 526 337 546 -fill white -width 0
.c create text 300 536 -text "go!SOUTH"
.c create text 70 560 -fill #eef -text "45"
.c create line 140 560 300 560 -fill #eef -dash {6 4}
.c create line 140 528 140 548 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 41 to 45 (Used 1 nobox 1)
# ProcLine[1] stays at 45 (Used 1 nobox 1)
.c create rectangle 103 550 177 570 -fill white -width 0
.c create text 140 560 -text "go?SOUTH"
.c create text 70 584 -fill #eef -text "47"
.c create line 140 584 300 584 -fill #eef -dash {6 4}
.c create line 140 576 140 572 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 45 to 47 (Used 1 nobox 1)
# ProcLine[1] stays at 47 (Used 1 nobox 1)
.c create rectangle 98 574 182 594 -fill white -width 0
.c create text 140 584 -text "go SOUTH"
.c create line 300 618 220 618 -fill darkblue -tags mesg -width 2
.c create line 220 618 140 618 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 608 -fill #eef -text "49"
.c create line 140 608 300 608 -fill #eef -dash {6 4}
.c create line 300 552 300 596 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 43 to 49 (Used 1 nobox 1)
# ProcLine[2] stays at 49 (Used 1 nobox 1)
.c create rectangle 268 598 332 618 -fill white -width 0
.c create text 300 608 -text "go!WEST"
.c create text 70 632 -fill #eef -text "51"
.c create line 140 632 300 632 -fill #eef -dash {6 4}
.c create line 140 600 140 620 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 47 to 51 (Used 1 nobox 1)
# ProcLine[1] stays at 51 (Used 1 nobox 1)
.c create rectangle 108 622 172 642 -fill white -width 0
.c create text 140 632 -text "go?WEST"
.c create text 70 656 -fill #eef -text "53"
.c create line 140 656 300 656 -fill #eef -dash {6 4}
.c create line 140 648 140 644 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 51 to 53 (Used 1 nobox 1)
# ProcLine[1] stays at 53 (Used 1 nobox 1)
.c create rectangle 103 646 177 666 -fill white -width 0
.c create text 140 656 -text "go WEST"
.c create line 300 690 220 690 -fill darkblue -tags mesg -width 2
.c create line 220 690 140 690 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 680 -fill #eef -text "55"
.c create line 140 680 300 680 -fill #eef -dash {6 4}
.c create line 300 624 300 668 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 49 to 55 (Used 1 nobox 1)
# ProcLine[2] stays at 55 (Used 1 nobox 1)
.c create rectangle 268 670 332 690 -fill white -width 0
.c create text 300 680 -text "go!WEST"
.c create text 70 704 -fill #eef -text "57"
.c create line 140 704 300 704 -fill #eef -dash {6 4}
.c create line 140 672 140 692 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 53 to 57 (Used 1 nobox 1)
# ProcLine[1] stays at 57 (Used 1 nobox 1)
.c create rectangle 108 694 172 714 -fill white -width 0
.c create text 140 704 -text "go?WEST"
.c create text 70 728 -fill #eef -text "59"
.c create line 140 728 300 728 -fill #eef -dash {6 4}
.c create line 140 720 140 716 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 57 to 59 (Used 1 nobox 1)
# ProcLine[1] stays at 59 (Used 1 nobox 1)
.c create rectangle 103 718 177 738 -fill white -width 0
.c create text 140 728 -text "go WEST"
.c create line 300 762 220 762 -fill darkblue -tags mesg -width 2
.c create line 220 762 140 762 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 752 -fill #eef -text "61"
.c create line 140 752 300 752 -fill #eef -dash {6 4}
.c create line 300 696 300 740 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 55 to 61 (Used 1 nobox 1)
# ProcLine[2] stays at 61 (Used 1 nobox 1)
.c create rectangle 268 742 332 762 -fill white -width 0
.c create text 300 752 -text "go!WEST"
.c create text 70 776 -fill #eef -text "63"
.c create line 140 776 300 776 -fill #eef -dash {6 4}
.c create line 140 744 140 764 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 59 to 63 (Used 1 nobox 1)
# ProcLine[1] stays at 63 (Used 1 nobox 1)
.c create rectangle 108 766 172 786 -fill white -width 0
.c create text 140 776 -text "go?WEST"
.c create text 70 800 -fill #eef -text "65"
.c create line 140 800 300 800 -fill #eef -dash {6 4}
.c create line 140 792 140 788 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 63 to 65 (Used 1 nobox 1)
# ProcLine[1] stays at 65 (Used 1 nobox 1)
.c create rectangle 103 790 177 810 -fill white -width 0
.c create text 140 800 -text "go WEST"
.c create line 300 834 220 834 -fill darkblue -tags mesg -width 2
.c create line 220 834 140 834 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 824 -fill #eef -text "67"
.c create line 140 824 300 824 -fill #eef -dash {6 4}
.c create line 300 768 300 812 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 61 to 67 (Used 1 nobox 1)
# ProcLine[2] stays at 67 (Used 1 nobox 1)
.c create rectangle 263 814 337 834 -fill white -width 0
.c create text 300 824 -text "go!NORTH"
.c create text 70 848 -fill #eef -text "69"
.c create line 140 848 300 848 -fill #eef -dash {6 4}
.c create line 140 816 140 836 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 65 to 69 (Used 1 nobox 1)
# ProcLine[1] stays at 69 (Used 1 nobox 1)
.c create rectangle 103 838 177 858 -fill white -width 0
.c create text 140 848 -text "go?NORTH"
.c create text 70 872 -fill #eef -text "71"
.c create line 140 872 300 872 -fill #eef -dash {6 4}
.c create line 140 864 140 860 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 69 to 71 (Used 1 nobox 1)
# ProcLine[1] stays at 71 (Used 1 nobox 1)
.c create rectangle 98 862 182 882 -fill white -width 0
.c create text 140 872 -text "go NORTH"
.c create line 300 906 220 906 -fill darkblue -tags mesg -width 2
.c create line 220 906 140 906 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 896 -fill #eef -text "73"
.c create line 140 896 300 896 -fill #eef -dash {6 4}
.c create line 300 840 300 884 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 67 to 73 (Used 1 nobox 1)
# ProcLine[2] stays at 73 (Used 1 nobox 1)
.c create rectangle 263 886 337 906 -fill white -width 0
.c create text 300 896 -text "go!NORTH"
.c create text 70 920 -fill #eef -text "75"
.c create line 140 920 300 920 -fill #eef -dash {6 4}
.c create line 140 888 140 908 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 71 to 75 (Used 1 nobox 1)
# ProcLine[1] stays at 75 (Used 1 nobox 1)
.c create rectangle 103 910 177 930 -fill white -width 0
.c create text 140 920 -text "go?NORTH"
.c create text 70 944 -fill #eef -text "77"
.c create line 140 944 300 944 -fill #eef -dash {6 4}
.c create line 140 936 140 932 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 75 to 77 (Used 1 nobox 1)
# ProcLine[1] stays at 77 (Used 1 nobox 1)
.c create rectangle 98 934 182 954 -fill white -width 0
.c create text 140 944 -text "go NORTH"
.c create line 300 978 220 978 -fill darkblue -tags mesg -width 2
.c create line 220 978 140 978 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 968 -fill #eef -text "79"
.c create line 140 968 300 968 -fill #eef -dash {6 4}
.c create line 300 912 300 956 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 73 to 79 (Used 1 nobox 1)
# ProcLine[2] stays at 79 (Used 1 nobox 1)
.c create rectangle 268 958 332 978 -fill white -width 0
.c create text 300 968 -text "go!EAST"
.c create text 70 992 -fill #eef -text "81"
.c create line 140 992 300 992 -fill #eef -dash {6 4}
.c create line 140 960 140 980 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 77 to 81 (Used 1 nobox 1)
# ProcLine[1] stays at 81 (Used 1 nobox 1)
.c create rectangle 108 982 172 1002 -fill white -width 0
.c create text 140 992 -text "go?EAST"
.c create text 70 1016 -fill #eef -text "83"
.c create line 140 1016 300 1016 -fill #eef -dash {6 4}
.c create line 140 1008 140 1004 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 81 to 83 (Used 1 nobox 1)
# ProcLine[1] stays at 83 (Used 1 nobox 1)
.c create rectangle 103 1006 177 1026 -fill white -width 0
.c create text 140 1016 -text "go EAST"
.c create line 300 1050 220 1050 -fill darkblue -tags mesg -width 2
.c create line 220 1050 140 1050 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 1040 -fill #eef -text "85"
.c create line 140 1040 300 1040 -fill #eef -dash {6 4}
.c create line 300 984 300 1028 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 79 to 85 (Used 1 nobox 1)
# ProcLine[2] stays at 85 (Used 1 nobox 1)
.c create rectangle 268 1030 332 1050 -fill white -width 0
.c create text 300 1040 -text "go!EAST"
.c create text 70 1064 -fill #eef -text "87"
.c create line 140 1064 300 1064 -fill #eef -dash {6 4}
.c create line 140 1032 140 1052 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 83 to 87 (Used 1 nobox 1)
# ProcLine[1] stays at 87 (Used 1 nobox 1)
.c create rectangle 108 1054 172 1074 -fill white -width 0
.c create text 140 1064 -text "go?EAST"
.c create text 70 1088 -fill #eef -text "89"
.c create line 140 1088 300 1088 -fill #eef -dash {6 4}
.c create line 140 1080 140 1076 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 87 to 89 (Used 1 nobox 1)
# ProcLine[1] stays at 89 (Used 1 nobox 1)
.c create rectangle 103 1078 177 1098 -fill white -width 0
.c create text 140 1088 -text "go EAST"
.c create line 300 1122 220 1122 -fill darkblue -tags mesg -width 2
.c create line 220 1122 140 1122 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 1112 -fill #eef -text "91"
.c create line 140 1112 300 1112 -fill #eef -dash {6 4}
.c create line 300 1056 300 1100 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 85 to 91 (Used 1 nobox 1)
# ProcLine[2] stays at 91 (Used 1 nobox 1)
.c create rectangle 263 1102 337 1122 -fill white -width 0
.c create text 300 1112 -text "go!NORTH"
.c create text 70 1136 -fill #eef -text "93"
.c create line 140 1136 300 1136 -fill #eef -dash {6 4}
.c create line 140 1104 140 1124 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 89 to 93 (Used 1 nobox 1)
# ProcLine[1] stays at 93 (Used 1 nobox 1)
.c create rectangle 103 1126 177 1146 -fill white -width 0
.c create text 140 1136 -text "go?NORTH"
.c create text 70 1160 -fill #eef -text "95"
.c create line 140 1160 300 1160 -fill #eef -dash {6 4}
.c create line 140 1152 140 1148 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 93 to 95 (Used 1 nobox 1)
# ProcLine[1] stays at 95 (Used 1 nobox 1)
.c create rectangle 98 1150 182 1170 -fill white -width 0
.c create text 140 1160 -text "go NORTH"
.c create line 300 1194 220 1194 -fill darkblue -tags mesg -width 2
.c create line 220 1194 140 1194 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 1184 -fill #eef -text "97"
.c create line 140 1184 300 1184 -fill #eef -dash {6 4}
.c create line 300 1128 300 1172 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 91 to 97 (Used 1 nobox 1)
# ProcLine[2] stays at 97 (Used 1 nobox 1)
.c create rectangle 268 1174 332 1194 -fill white -width 0
.c create text 300 1184 -text "go!WEST"
.c create text 70 1208 -fill #eef -text "99"
.c create line 140 1208 300 1208 -fill #eef -dash {6 4}
.c create line 140 1176 140 1196 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 95 to 99 (Used 1 nobox 1)
# ProcLine[1] stays at 99 (Used 1 nobox 1)
.c create rectangle 108 1198 172 1218 -fill white -width 0
.c create text 140 1208 -text "go?WEST"
.c create text 70 1232 -fill #eef -text "101"
.c create line 140 1232 300 1232 -fill #eef -dash {6 4}
.c create line 140 1224 140 1220 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 99 to 101 (Used 1 nobox 1)
# ProcLine[1] stays at 101 (Used 1 nobox 1)
.c create rectangle 103 1222 177 1242 -fill white -width 0
.c create text 140 1232 -text "go WEST"
.c create line 300 1266 220 1266 -fill darkblue -tags mesg -width 2
.c create line 220 1266 140 1266 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 1256 -fill #eef -text "103"
.c create line 140 1256 300 1256 -fill #eef -dash {6 4}
.c create line 300 1200 300 1244 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 97 to 103 (Used 1 nobox 1)
# ProcLine[2] stays at 103 (Used 1 nobox 1)
.c create rectangle 268 1246 332 1266 -fill white -width 0
.c create text 300 1256 -text "go!WEST"
.c create text 70 1280 -fill #eef -text "105"
.c create line 140 1280 300 1280 -fill #eef -dash {6 4}
.c create line 140 1248 140 1268 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 101 to 105 (Used 1 nobox 1)
# ProcLine[1] stays at 105 (Used 1 nobox 1)
.c create rectangle 108 1270 172 1290 -fill white -width 0
.c create text 140 1280 -text "go?WEST"
.c create text 70 1304 -fill #eef -text "107"
.c create line 140 1304 300 1304 -fill #eef -dash {6 4}
.c create line 140 1296 140 1292 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 105 to 107 (Used 1 nobox 1)
# ProcLine[1] stays at 107 (Used 1 nobox 1)
.c create rectangle 103 1294 177 1314 -fill white -width 0
.c create text 140 1304 -text "go WEST"
.c create line 300 1338 220 1338 -fill darkblue -tags mesg -width 2
.c create line 220 1338 140 1338 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 1328 -fill #eef -text "109"
.c create line 140 1328 300 1328 -fill #eef -dash {6 4}
.c create line 300 1272 300 1316 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 103 to 109 (Used 1 nobox 1)
# ProcLine[2] stays at 109 (Used 1 nobox 1)
.c create rectangle 268 1318 332 1338 -fill white -width 0
.c create text 300 1328 -text "go!WEST"
.c create text 70 1352 -fill #eef -text "111"
.c create line 140 1352 300 1352 -fill #eef -dash {6 4}
.c create line 140 1320 140 1340 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 107 to 111 (Used 1 nobox 1)
# ProcLine[1] stays at 111 (Used 1 nobox 1)
.c create rectangle 108 1342 172 1362 -fill white -width 0
.c create text 140 1352 -text "go?WEST"
.c create text 70 1376 -fill #eef -text "113"
.c create line 140 1376 300 1376 -fill #eef -dash {6 4}
.c create line 140 1368 140 1364 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 111 to 113 (Used 1 nobox 1)
# ProcLine[1] stays at 113 (Used 1 nobox 1)
.c create rectangle 103 1366 177 1386 -fill white -width 0
.c create text 140 1376 -text "go WEST"
.c create line 300 1410 220 1410 -fill darkblue -tags mesg -width 2
.c create line 220 1410 140 1410 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 1400 -fill #eef -text "115"
.c create line 140 1400 300 1400 -fill #eef -dash {6 4}
.c create line 300 1344 300 1388 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 109 to 115 (Used 1 nobox 1)
# ProcLine[2] stays at 115 (Used 1 nobox 1)
.c create rectangle 263 1390 337 1410 -fill white -width 0
.c create text 300 1400 -text "go!NORTH"
.c create text 70 1424 -fill #eef -text "117"
.c create line 140 1424 300 1424 -fill #eef -dash {6 4}
.c create line 140 1392 140 1412 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 113 to 117 (Used 1 nobox 1)
# ProcLine[1] stays at 117 (Used 1 nobox 1)
.c create rectangle 103 1414 177 1434 -fill white -width 0
.c create text 140 1424 -text "go?NORTH"
.c create text 70 1448 -fill #eef -text "119"
.c create line 140 1448 300 1448 -fill #eef -dash {6 4}
.c create line 140 1440 140 1436 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 117 to 119 (Used 1 nobox 1)
# ProcLine[1] stays at 119 (Used 1 nobox 1)
.c create rectangle 98 1438 182 1458 -fill white -width 0
.c create text 140 1448 -text "go NORTH"
.c create line 300 1482 220 1482 -fill darkblue -tags mesg -width 2
.c create line 220 1482 140 1482 -fill darkblue -width 2 -arrow last -arrowshape {5 5 5} -tags mesg
.c raise mesg
.c create text 70 1472 -fill #eef -text "121"
.c create line 140 1472 300 1472 -fill #eef -dash {6 4}
.c create line 300 1416 300 1460 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[2] from 115 to 121 (Used 1 nobox 1)
# ProcLine[2] stays at 121 (Used 1 nobox 1)
.c create rectangle 268 1462 332 1482 -fill white -width 0
.c create text 300 1472 -text "go!EXIT"
.c create text 70 1496 -fill #eef -text "123"
.c create line 140 1496 300 1496 -fill #eef -dash {6 4}
.c create line 140 1464 140 1484 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 119 to 123 (Used 1 nobox 1)
# ProcLine[1] stays at 123 (Used 1 nobox 1)
.c create rectangle 108 1486 172 1506 -fill white -width 0
.c create text 140 1496 -text "go?EXIT"
.c create text 70 1520 -fill #eef -text "125"
.c create line 140 1520 300 1520 -fill #eef -dash {6 4}
.c create line 140 1512 140 1508 -fill lightgrey -tags grid -width 1 
.c lower grid
# ProcLine[1] from 123 to 125 (Used 1 nobox 1)
# ProcLine[1] stays at 125 (Used 1 nobox 1)
.c create rectangle 108 1510 172 1530 -fill white -width 0
.c create text 140 1520 -text "go EXIT"
.c lower grid
.c raise mesg
