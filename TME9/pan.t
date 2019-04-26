#ifdef PEG
struct T_SRC {
	char *fl; int ln;
} T_SRC[NTRANS];

void
tr_2_src(int m, char *file, int ln)
{	T_SRC[m].fl = file;
	T_SRC[m].ln = ln;
}

void
putpeg(int n, int m)
{	printf("%5d	trans %4d ", m, n);
	printf("%s:%d\n",
		T_SRC[n].fl, T_SRC[n].ln);
}
#endif

void
settable(void)
{	Trans *T;
	Trans *settr(int, int, int, int, int, char *, int, int, int);

	trans = (Trans ***) emalloc(3*sizeof(Trans **));

	/* proctype 1: loby */

	trans[1] = (Trans **) emalloc(226*sizeof(Trans *));

	trans[1][6]	= settr(18,0,5,1,0,".(goto)", 0, 2, 0);
	T = trans[1][5] = settr(17,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(17,0,1,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(17,0,4,0,0,"DO", 0, 2, 0);
	trans[1][1]	= settr(13,0,2,1,0,"(1)", 0, 2, 0);
	trans[1][2]	= settr(14,0,172,3,3,"go!NORTH", 1, 3, 0);
	trans[1][3]	= settr(15,0,172,1,0,"goto case51", 0, 2, 0);
	trans[1][4]	= settr(16,0,5,2,0,"else", 0, 2, 0);
	trans[1][7]	= settr(19,0,12,1,0,"break", 0, 2, 0);
	trans[1][13]	= settr(25,0,12,1,0,".(goto)", 0, 2, 0);
	T = trans[1][12] = settr(24,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(24,0,8,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(24,0,11,0,0,"DO", 0, 2, 0);
	trans[1][8]	= settr(20,0,9,1,0,"(1)", 0, 2, 0);
	trans[1][9]	= settr(21,0,68,4,4,"go!EAST", 1, 3, 0);
	trans[1][10]	= settr(22,0,68,1,0,"goto case23", 0, 2, 0);
	trans[1][11]	= settr(23,0,12,2,0,"else", 0, 2, 0);
	trans[1][14]	= settr(26,0,19,1,0,"break", 0, 2, 0);
	trans[1][20]	= settr(32,0,19,1,0,".(goto)", 0, 2, 0);
	T = trans[1][19] = settr(31,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(31,0,15,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(31,0,18,0,0,"DO", 0, 2, 0);
	trans[1][15]	= settr(27,0,16,1,0,"(1)", 0, 2, 0);
	trans[1][16]	= settr(28,0,32,5,5,"go!NORTH", 1, 3, 0);
	trans[1][17]	= settr(29,0,32,1,0,"goto case15", 0, 2, 0);
	trans[1][18]	= settr(30,0,19,2,0,"else", 0, 2, 0);
	trans[1][21]	= settr(33,0,32,1,0,"break", 0, 2, 0);
	trans[1][33]	= settr(45,0,32,1,0,".(goto)", 0, 2, 0);
	T = trans[1][32] = settr(44,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(44,0,22,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(44,0,25,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(44,0,28,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(44,0,31,0,0,"DO", 0, 2, 0);
	trans[1][22]	= settr(34,0,23,1,0,"(1)", 0, 2, 0);
	trans[1][23]	= settr(35,0,221,6,6,"go!NORTH", 1, 3, 0);
	trans[1][24]	= settr(36,0,221,1,0,"goto exit", 0, 2, 0);
	trans[1][25]	= settr(37,0,26,1,0,"(1)", 0, 2, 0);
	trans[1][26]	= settr(38,0,19,7,7,"go!SOUTH", 1, 3, 0);
	trans[1][27]	= settr(39,0,19,1,0,"goto case14", 0, 2, 0);
	trans[1][28]	= settr(40,0,29,1,0,"(1)", 0, 2, 0);
	trans[1][29]	= settr(41,0,88,8,8,"go!EAST", 1, 3, 0);
	trans[1][30]	= settr(42,0,88,1,0,"goto case25", 0, 2, 0);
	trans[1][31]	= settr(43,0,32,2,0,"else", 0, 2, 0);
	trans[1][34]	= settr(46,0,42,1,0,"break", 0, 2, 0);
	trans[1][43]	= settr(55,0,42,1,0,".(goto)", 0, 2, 0);
	T = trans[1][42] = settr(54,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(54,0,35,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(54,0,38,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(54,0,41,0,0,"DO", 0, 2, 0);
	trans[1][35]	= settr(47,0,36,1,0,"(1)", 0, 2, 0);
	trans[1][36]	= settr(48,0,98,9,9,"go!NORTH", 1, 3, 0);
	trans[1][37]	= settr(49,0,98,1,0,"goto case31", 0, 2, 0);
	trans[1][38]	= settr(50,0,39,1,0,"(1)", 0, 2, 0);
	trans[1][39]	= settr(51,0,55,10,10,"go!EAST", 1, 3, 0);
	trans[1][40]	= settr(52,0,55,1,0,"goto case22", 0, 2, 0);
	trans[1][41]	= settr(53,0,42,2,0,"else", 0, 2, 0);
	trans[1][44]	= settr(56,0,55,1,0,"break", 0, 2, 0);
	trans[1][56]	= settr(68,0,55,1,0,".(goto)", 0, 2, 0);
	T = trans[1][55] = settr(67,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(67,0,45,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(67,0,48,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(67,0,51,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(67,0,54,0,0,"DO", 0, 2, 0);
	trans[1][45]	= settr(57,0,46,1,0,"(1)", 0, 2, 0);
	trans[1][46]	= settr(58,0,68,11,11,"go!NORTH", 1, 3, 0);
	trans[1][47]	= settr(59,0,68,1,0,"goto case23", 0, 2, 0);
	trans[1][48]	= settr(60,0,49,1,0,"(1)", 0, 2, 0);
	trans[1][49]	= settr(61,0,42,12,12,"go!SOUTH", 1, 3, 0);
	trans[1][50]	= settr(62,0,42,1,0,"goto case21", 0, 2, 0);
	trans[1][51]	= settr(63,0,52,1,0,"(1)", 0, 2, 0);
	trans[1][52]	= settr(64,0,108,13,13,"go!EAST", 1, 3, 0);
	trans[1][53]	= settr(65,0,108,1,0,"goto case32", 0, 2, 0);
	trans[1][54]	= settr(66,0,55,2,0,"else", 0, 2, 0);
	trans[1][57]	= settr(69,0,68,1,0,"break", 0, 2, 0);
	trans[1][69]	= settr(81,0,68,1,0,".(goto)", 0, 2, 0);
	T = trans[1][68] = settr(80,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(80,0,58,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(80,0,61,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(80,0,64,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(80,0,67,0,0,"DO", 0, 2, 0);
	trans[1][58]	= settr(70,0,59,1,0,"(1)", 0, 2, 0);
	trans[1][59]	= settr(71,0,78,14,14,"go!NORTH", 1, 3, 0);
	trans[1][60]	= settr(72,0,78,1,0,"goto case24", 0, 2, 0);
	trans[1][61]	= settr(73,0,62,1,0,"(1)", 0, 2, 0);
	trans[1][62]	= settr(74,0,55,15,15,"go!SOUTH", 1, 3, 0);
	trans[1][63]	= settr(75,0,55,1,0,"goto case22", 0, 2, 0);
	trans[1][64]	= settr(76,0,65,1,0,"(1)", 0, 2, 0);
	trans[1][65]	= settr(77,0,12,16,16,"go!WEST", 1, 3, 0);
	trans[1][66]	= settr(78,0,12,1,0,"goto case13", 0, 2, 0);
	trans[1][67]	= settr(79,0,68,2,0,"else", 0, 2, 0);
	trans[1][70]	= settr(82,0,78,1,0,"break", 0, 2, 0);
	trans[1][79]	= settr(91,0,78,1,0,".(goto)", 0, 2, 0);
	T = trans[1][78] = settr(90,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(90,0,71,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(90,0,74,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(90,0,77,0,0,"DO", 0, 2, 0);
	trans[1][71]	= settr(83,0,72,1,0,"(1)", 0, 2, 0);
	trans[1][72]	= settr(84,0,118,17,17,"go!EAST", 1, 3, 0);
	trans[1][73]	= settr(85,0,118,1,0,"goto case34", 0, 2, 0);
	trans[1][74]	= settr(86,0,75,1,0,"(1)", 0, 2, 0);
	trans[1][75]	= settr(87,0,68,18,18,"go!SOUTH", 1, 3, 0);
	trans[1][76]	= settr(88,0,68,1,0,"goto case23", 0, 2, 0);
	trans[1][77]	= settr(89,0,78,2,0,"else", 0, 2, 0);
	trans[1][80]	= settr(92,0,88,1,0,"break", 0, 2, 0);
	trans[1][89]	= settr(101,0,88,1,0,".(goto)", 0, 2, 0);
	T = trans[1][88] = settr(100,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(100,0,81,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(100,0,84,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(100,0,87,0,0,"DO", 0, 2, 0);
	trans[1][81]	= settr(93,0,82,1,0,"(1)", 0, 2, 0);
	trans[1][82]	= settr(94,0,32,19,19,"go!WEST", 1, 3, 0);
	trans[1][83]	= settr(95,0,32,1,0,"goto case15", 0, 2, 0);
	trans[1][84]	= settr(96,0,85,1,0,"(1)", 0, 2, 0);
	trans[1][85]	= settr(97,0,128,20,20,"go!EAST", 1, 3, 0);
	trans[1][86]	= settr(98,0,128,1,0,"goto case35", 0, 2, 0);
	trans[1][87]	= settr(99,0,88,2,0,"else", 0, 2, 0);
	trans[1][90]	= settr(102,0,98,1,0,"break", 0, 2, 0);
	trans[1][99]	= settr(111,0,98,1,0,".(goto)", 0, 2, 0);
	T = trans[1][98] = settr(110,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(110,0,91,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(110,0,94,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(110,0,97,0,0,"DO", 0, 2, 0);
	trans[1][91]	= settr(103,0,92,1,0,"(1)", 0, 2, 0);
	trans[1][92]	= settr(104,0,42,21,21,"go!WEST", 1, 3, 0);
	trans[1][93]	= settr(105,0,42,1,0,"goto case21", 0, 2, 0);
	trans[1][94]	= settr(106,0,95,1,0,"(1)", 0, 2, 0);
	trans[1][95]	= settr(107,0,135,22,22,"go!EAST", 1, 3, 0);
	trans[1][96]	= settr(108,0,135,1,0,"goto case41", 0, 2, 0);
	trans[1][97]	= settr(109,0,98,2,0,"else", 0, 2, 0);
	trans[1][100]	= settr(112,0,108,1,0,"break", 0, 2, 0);
	trans[1][109]	= settr(121,0,108,1,0,".(goto)", 0, 2, 0);
	T = trans[1][108] = settr(120,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(120,0,101,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(120,0,104,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(120,0,107,0,0,"DO", 0, 2, 0);
	trans[1][101]	= settr(113,0,102,1,0,"(1)", 0, 2, 0);
	trans[1][102]	= settr(114,0,55,23,23,"go!WEST", 1, 3, 0);
	trans[1][103]	= settr(115,0,55,1,0,"goto case22", 0, 2, 0);
	trans[1][104]	= settr(116,0,105,1,0,"(1)", 0, 2, 0);
	trans[1][105]	= settr(117,0,145,24,24,"go!EAST", 1, 3, 0);
	trans[1][106]	= settr(118,0,145,1,0,"goto case42", 0, 2, 0);
	trans[1][107]	= settr(119,0,108,2,0,"else", 0, 2, 0);
	trans[1][110]	= settr(122,0,118,1,0,"break", 0, 2, 0);
	trans[1][119]	= settr(131,0,118,1,0,".(goto)", 0, 2, 0);
	T = trans[1][118] = settr(130,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(130,0,111,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(130,0,114,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(130,0,117,0,0,"DO", 0, 2, 0);
	trans[1][111]	= settr(123,0,112,1,0,"(1)", 0, 2, 0);
	trans[1][112]	= settr(124,0,78,25,25,"go!WEST", 1, 3, 0);
	trans[1][113]	= settr(125,0,78,1,0,"goto case24", 0, 2, 0);
	trans[1][114]	= settr(126,0,115,1,0,"(1)", 0, 2, 0);
	trans[1][115]	= settr(127,0,155,26,26,"go!EAST", 1, 3, 0);
	trans[1][116]	= settr(128,0,155,1,0,"goto case44", 0, 2, 0);
	trans[1][117]	= settr(129,0,118,2,0,"else", 0, 2, 0);
	trans[1][120]	= settr(132,0,128,1,0,"break", 0, 2, 0);
	trans[1][129]	= settr(141,0,128,1,0,".(goto)", 0, 2, 0);
	T = trans[1][128] = settr(140,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(140,0,121,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(140,0,124,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(140,0,127,0,0,"DO", 0, 2, 0);
	trans[1][121]	= settr(133,0,122,1,0,"(1)", 0, 2, 0);
	trans[1][122]	= settr(134,0,88,27,27,"go!WEST", 1, 3, 0);
	trans[1][123]	= settr(135,0,88,1,0,"goto case25", 0, 2, 0);
	trans[1][124]	= settr(136,0,125,1,0,"(1)", 0, 2, 0);
	trans[1][125]	= settr(137,0,165,28,28,"go!EAST", 1, 3, 0);
	trans[1][126]	= settr(138,0,165,1,0,"goto case45", 0, 2, 0);
	trans[1][127]	= settr(139,0,128,2,0,"else", 0, 2, 0);
	trans[1][130]	= settr(142,0,135,1,0,"break", 0, 2, 0);
	trans[1][136]	= settr(148,0,135,1,0,".(goto)", 0, 2, 0);
	T = trans[1][135] = settr(147,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(147,0,131,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(147,0,134,0,0,"DO", 0, 2, 0);
	trans[1][131]	= settr(143,0,132,1,0,"(1)", 0, 2, 0);
	trans[1][132]	= settr(144,0,98,29,29,"go!WEST", 1, 3, 0);
	trans[1][133]	= settr(145,0,98,1,0,"goto case31", 0, 2, 0);
	trans[1][134]	= settr(146,0,135,2,0,"else", 0, 2, 0);
	trans[1][137]	= settr(149,0,145,1,0,"break", 0, 2, 0);
	trans[1][146]	= settr(158,0,145,1,0,".(goto)", 0, 2, 0);
	T = trans[1][145] = settr(157,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(157,0,138,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(157,0,141,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(157,0,144,0,0,"DO", 0, 2, 0);
	trans[1][138]	= settr(150,0,139,1,0,"(1)", 0, 2, 0);
	trans[1][139]	= settr(151,0,108,30,30,"go!WEST", 1, 3, 0);
	trans[1][140]	= settr(152,0,108,1,0,"goto case32", 0, 2, 0);
	trans[1][141]	= settr(153,0,142,1,0,"(1)", 0, 2, 0);
	trans[1][142]	= settr(154,0,185,31,31,"go!EAST", 1, 3, 0);
	trans[1][143]	= settr(155,0,185,1,0,"goto case52", 0, 2, 0);
	trans[1][144]	= settr(156,0,145,2,0,"else", 0, 2, 0);
	trans[1][147]	= settr(159,0,155,1,0,"break", 0, 2, 0);
	trans[1][156]	= settr(168,0,155,1,0,".(goto)", 0, 2, 0);
	T = trans[1][155] = settr(167,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(167,0,148,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(167,0,151,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(167,0,154,0,0,"DO", 0, 2, 0);
	trans[1][148]	= settr(160,0,149,1,0,"(1)", 0, 2, 0);
	trans[1][149]	= settr(161,0,165,32,32,"go!NORTH", 1, 3, 0);
	trans[1][150]	= settr(162,0,165,1,0,"goto case45", 0, 2, 0);
	trans[1][151]	= settr(163,0,152,1,0,"(1)", 0, 2, 0);
	trans[1][152]	= settr(164,0,118,33,33,"go!EAST", 1, 3, 0);
	trans[1][153]	= settr(165,0,118,1,0,"goto case34", 0, 2, 0);
	trans[1][154]	= settr(166,0,155,2,0,"else", 0, 2, 0);
	trans[1][157]	= settr(169,0,165,1,0,"break", 0, 2, 0);
	trans[1][166]	= settr(178,0,165,1,0,".(goto)", 0, 2, 0);
	T = trans[1][165] = settr(177,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(177,0,158,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(177,0,161,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(177,0,164,0,0,"DO", 0, 2, 0);
	trans[1][158]	= settr(170,0,159,1,0,"(1)", 0, 2, 0);
	trans[1][159]	= settr(171,0,128,34,34,"go!WEST", 1, 3, 0);
	trans[1][160]	= settr(172,0,128,1,0,"goto case35", 0, 2, 0);
	trans[1][161]	= settr(173,0,162,1,0,"(1)", 0, 2, 0);
	trans[1][162]	= settr(174,0,155,35,35,"go!SOUTH", 1, 3, 0);
	trans[1][163]	= settr(175,0,155,1,0,"goto case44", 0, 2, 0);
	trans[1][164]	= settr(176,0,165,2,0,"else", 0, 2, 0);
	trans[1][167]	= settr(179,0,172,1,0,"break", 0, 2, 0);
	trans[1][173]	= settr(185,0,172,1,0,".(goto)", 0, 2, 0);
	T = trans[1][172] = settr(184,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(184,0,168,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(184,0,171,0,0,"DO", 0, 2, 0);
	trans[1][168]	= settr(180,0,169,1,0,"(1)", 0, 2, 0);
	trans[1][169]	= settr(181,0,185,36,36,"go!NORTH", 1, 3, 0);
	trans[1][170]	= settr(182,0,185,1,0,"goto case52", 0, 2, 0);
	trans[1][171]	= settr(183,0,172,2,0,"else", 0, 2, 0);
	trans[1][174]	= settr(186,0,185,1,0,"break", 0, 2, 0);
	trans[1][186]	= settr(198,0,185,1,0,".(goto)", 0, 2, 0);
	T = trans[1][185] = settr(197,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(197,0,175,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(197,0,178,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(197,0,181,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(197,0,184,0,0,"DO", 0, 2, 0);
	trans[1][175]	= settr(187,0,176,1,0,"(1)", 0, 2, 0);
	trans[1][176]	= settr(188,0,195,37,37,"go!NORTH", 1, 3, 0);
	trans[1][177]	= settr(189,0,195,1,0,"goto case53", 0, 2, 0);
	trans[1][178]	= settr(190,0,179,1,0,"(1)", 0, 2, 0);
	trans[1][179]	= settr(191,0,145,38,38,"go!WEST", 1, 3, 0);
	trans[1][180]	= settr(192,0,145,1,0,"goto case42", 0, 2, 0);
	trans[1][181]	= settr(193,0,182,1,0,"(1)", 0, 2, 0);
	trans[1][182]	= settr(194,0,172,39,39,"go!EAST", 1, 3, 0);
	trans[1][183]	= settr(195,0,172,1,0,"goto case51", 0, 2, 0);
	trans[1][184]	= settr(196,0,185,2,0,"else", 0, 2, 0);
	trans[1][187]	= settr(199,0,195,1,0,"break", 0, 2, 0);
	trans[1][196]	= settr(208,0,195,1,0,".(goto)", 0, 2, 0);
	T = trans[1][195] = settr(207,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(207,0,188,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(207,0,191,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(207,0,194,0,0,"DO", 0, 2, 0);
	trans[1][188]	= settr(200,0,189,1,0,"(1)", 0, 2, 0);
	trans[1][189]	= settr(201,0,205,40,40,"go!NORTH", 1, 3, 0);
	trans[1][190]	= settr(202,0,205,1,0,"goto case54", 0, 2, 0);
	trans[1][191]	= settr(203,0,192,1,0,"(1)", 0, 2, 0);
	trans[1][192]	= settr(204,0,185,41,41,"go!SOUTH", 1, 3, 0);
	trans[1][193]	= settr(205,0,185,1,0,"goto case52", 0, 2, 0);
	trans[1][194]	= settr(206,0,195,2,0,"else", 0, 2, 0);
	trans[1][197]	= settr(209,0,205,1,0,"break", 0, 2, 0);
	trans[1][206]	= settr(218,0,205,1,0,".(goto)", 0, 2, 0);
	T = trans[1][205] = settr(217,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(217,0,198,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(217,0,201,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(217,0,204,0,0,"DO", 0, 2, 0);
	trans[1][198]	= settr(210,0,199,1,0,"(1)", 0, 2, 0);
	trans[1][199]	= settr(211,0,212,42,42,"go!NORTH", 1, 3, 0);
	trans[1][200]	= settr(212,0,212,1,0,"goto case55", 0, 2, 0);
	trans[1][201]	= settr(213,0,202,1,0,"(1)", 0, 2, 0);
	trans[1][202]	= settr(214,0,195,43,43,"go!SOUTH", 1, 3, 0);
	trans[1][203]	= settr(215,0,195,1,0,"goto case53", 0, 2, 0);
	trans[1][204]	= settr(216,0,205,2,0,"else", 0, 2, 0);
	trans[1][207]	= settr(219,0,212,1,0,"break", 0, 2, 0);
	trans[1][213]	= settr(225,0,212,1,0,".(goto)", 0, 2, 0);
	T = trans[1][212] = settr(224,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(224,0,208,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(224,0,211,0,0,"DO", 0, 2, 0);
	trans[1][208]	= settr(220,0,209,1,0,"(1)", 0, 2, 0);
	trans[1][209]	= settr(221,0,205,44,44,"go!SOUTH", 1, 3, 0);
	trans[1][210]	= settr(222,0,205,1,0,"goto case54", 0, 2, 0);
	trans[1][211]	= settr(223,0,212,2,0,"else", 0, 2, 0);
	trans[1][214]	= settr(226,0,221,1,0,"break", 0, 2, 0);
	trans[1][222]	= settr(234,0,221,1,0,".(goto)", 0, 2, 0);
	T = trans[1][221] = settr(233,0,0,0,0,"DO", 0, 2, 0);
	T = T->nxt	= settr(233,0,215,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(233,0,218,0,0,"DO", 0, 2, 0);
	trans[1][215]	= settr(227,0,216,1,0,"(1)", 0, 2, 0);
	trans[1][216]	= settr(228,0,32,45,45,"go!SOUTH", 1, 3, 0);
	trans[1][217]	= settr(229,0,32,1,0,"goto case15", 0, 2, 0);
	trans[1][218]	= settr(230,0,219,1,0,"(1)", 0, 2, 0);
	trans[1][219]	= settr(231,0,224,46,46,"go!EXIT", 1, 3, 0);
	trans[1][220]	= settr(232,0,224,1,0,"goto end", 0, 2, 0);
	trans[1][223]	= settr(235,0,224,1,0,"break", 0, 2, 0);
	trans[1][224]	= settr(236,0,225,1,0,"(1)", 0, 2, 0);
	trans[1][225]	= settr(237,0,0,47,47,"-end-", 0, 3500, 0);

	/* proctype 0: observateur */

	trans[0] = (Trans **) emalloc(14*sizeof(Trans *));

	trans[0][10]	= settr(9,0,9,1,0,".(goto)", 0, 2, 0);
	T = trans[0][9] = settr(8,0,0,0,0,"DO", 0, 2, 0);
	    T->nxt	= settr(8,0,1,0,0,"DO", 0, 2, 0);
	trans[0][1]	= settr(0,0,7,48,48,"go?dir", 1, 503, 0);
	T = trans[0][7] = settr(6,0,0,0,0,"IF", 0, 2, 0);
	T = T->nxt	= settr(6,0,2,0,0,"IF", 0, 2, 0);
	    T->nxt	= settr(6,0,5,0,0,"IF", 0, 2, 0);
	trans[0][2]	= settr(1,0,12,49,49,"((dir==EXIT))", 0, 2, 0); /* m: 3 -> 12,0 */
	reached0[3] = 1;
	trans[0][3]	= settr(0,0,0,0,0,"printf('go EXIT')",0,0,0);
	trans[0][4]	= settr(3,0,12,1,0,"goto exit", 0, 2, 0);
	trans[0][8]	= settr(7,0,9,1,0,".(goto)", 0, 2, 0);
	trans[0][5]	= settr(4,0,6,2,0,"else", 0, 2, 0);
	trans[0][6]	= settr(5,0,9,50,0,"printf('go %e\\n',dir)", 0, 2, 0);
	trans[0][11]	= settr(10,0,12,1,0,"break", 0, 2, 0);
	trans[0][12]	= settr(11,0,13,51,0,"(0)", 0, 2, 0);
	trans[0][13]	= settr(12,0,0,52,52,"-end-", 0, 3500, 0);
	/* np_ demon: */
	trans[_NP_] = (Trans **) emalloc(3*sizeof(Trans *));
	T = trans[_NP_][0] = settr(9997,0,1,_T5,0,"(np_)", 1,2,0);
	    T->nxt	  = settr(9998,0,0,_T2,0,"(1)",   0,2,0);
	T = trans[_NP_][1] = settr(9999,0,1,_T5,0,"(np_)", 1,2,0);
}

Trans *
settr(	int t_id, int a, int b, int c, int d,
	char *t, int g, int tpe0, int tpe1)
{	Trans *tmp = (Trans *) emalloc(sizeof(Trans));

	tmp->atom  = a&(6|32);	/* only (2|8|32) have meaning */
	if (!g) tmp->atom |= 8;	/* no global references */
	tmp->st    = b;
	tmp->tpe[0] = tpe0;
	tmp->tpe[1] = tpe1;
	tmp->tp    = t;
	tmp->t_id  = t_id;
	tmp->forw  = c;
	tmp->back  = d;
	return tmp;
}

Trans *
cpytr(Trans *a)
{	Trans *tmp = (Trans *) emalloc(sizeof(Trans));

	int i;
	tmp->atom  = a->atom;
	tmp->st    = a->st;
#ifdef HAS_UNLESS
	tmp->e_trans = a->e_trans;
	for (i = 0; i < HAS_UNLESS; i++)
		tmp->escp[i] = a->escp[i];
#endif
	tmp->tpe[0] = a->tpe[0];
	tmp->tpe[1] = a->tpe[1];
	for (i = 0; i < 6; i++)
	{	tmp->qu[i] = a->qu[i];
		tmp->ty[i] = a->ty[i];
	}
	tmp->tp    = (char *) emalloc(strlen(a->tp)+1);
	strcpy(tmp->tp, a->tp);
	tmp->t_id  = a->t_id;
	tmp->forw  = a->forw;
	tmp->back  = a->back;
	return tmp;
}

#ifndef NOREDUCE
int
srinc_set(int n)
{	if (n <= 2) return LOCAL;
	if (n <= 2+  DELTA) return Q_FULL_F; /* 's' or nfull  */
	if (n <= 2+2*DELTA) return Q_EMPT_F; /* 'r' or nempty */
	if (n <= 2+3*DELTA) return Q_EMPT_T; /* empty */
	if (n <= 2+4*DELTA) return Q_FULL_T; /* full  */
	if (n ==   5*DELTA) return GLOBAL;
	if (n ==   6*DELTA) return TIMEOUT_F;
	if (n ==   7*DELTA) return ALPHA_F;
	Uerror("cannot happen srinc_class");
	return BAD;
}
int
srunc(int n, int m)
{	switch(m) {
	case Q_FULL_F: return n-2;
	case Q_EMPT_F: return n-2-DELTA;
	case Q_EMPT_T: return n-2-2*DELTA;
	case Q_FULL_T: return n-2-3*DELTA;
	case ALPHA_F:
	case TIMEOUT_F: return 257; /* non-zero, and > MAXQ */
	}
	Uerror("cannot happen srunc");
	return 0;
}
#endif
int cnt;
#ifdef HAS_UNLESS
int
isthere(Trans *a, int b)
{	Trans *t;
	for (t = a; t; t = t->nxt)
		if (t->t_id == b)
			return 1;
	return 0;
}
#endif
#ifndef NOREDUCE
int
mark_safety(Trans *t) /* for conditional safety */
{	int g = 0, i, j, k;

	if (!t) return 0;
	if (t->qu[0])
		return (t->qu[1])?2:1;	/* marked */

	for (i = 0; i < 2; i++)
	{	j = srinc_set(t->tpe[i]);
		if (j >= GLOBAL && j != ALPHA_F)
			return -1;
		if (j != LOCAL)
		{	k = srunc(t->tpe[i], j);
			if (g == 0
			||  t->qu[0] != k
			||  t->ty[0] != j)
			{	t->qu[g] = k;
				t->ty[g] = j;
				g++;
	}	}	}
	return g;
}
#endif
void
retrans(int n, int m, int is, short srcln[], uchar reach[], uchar lpstate[])
	/* process n, with m states, is=initial state */
{	Trans *T0, *T1, *T2, *T3;
	Trans *T4, *T5; /* t_reverse or has_unless */
	int i;
#if defined(HAS_UNLESS) || !defined(NOREDUCE)
	int k;
#endif
#ifndef NOREDUCE
	int g, h, j, aa;
#endif
#ifdef HAS_UNLESS
	int p;
#endif
	if (state_tables >= 4)
	{	printf("STEP 1 %s\n", 
			procname[n]);
		for (i = 1; i < m; i++)
		for (T0 = trans[n][i]; T0; T0 = T0->nxt)
			crack(n, i, T0, srcln);
		return;
	}
	do {
		for (i = 1, cnt = 0; i < m; i++)
		{	T2 = trans[n][i];
			T1 = T2?T2->nxt:(Trans *)0;
/* prescan: */		for (T0 = T1; T0; T0 = T0->nxt)
/* choice in choice */	{	if (T0->st && trans[n][T0->st]
				&&  trans[n][T0->st]->nxt)
					break;
			}
#if 0
		if (T0)
		printf("\tstate %d / %d: choice in choice\n",
		i, T0->st);
#endif
			if (T0)
			for (T0 = T1; T0; T0 = T0->nxt)
			{	T3 = trans[n][T0->st];
				if (!T3->nxt)
				{	T2->nxt = cpytr(T0);
					T2 = T2->nxt;
					imed(T2, T0->st, n, i);
					continue;
				}
				do {	T3 = T3->nxt;
					T2->nxt = cpytr(T3);
					T2 = T2->nxt;
					imed(T2, T0->st, n, i);
				} while (T3->nxt);
				cnt++;
			}
		}
	} while (cnt);
	if (state_tables >= 3)
	{	printf("STEP 2 %s\n", 
			procname[n]);
		for (i = 1; i < m; i++)
		for (T0 = trans[n][i]; T0; T0 = T0->nxt)
			crack(n, i, T0, srcln);
		return;
	}
	for (i = 1; i < m; i++)
	{	if (trans[n][i] && trans[n][i]->nxt) /* optimize */
		{	T1 = trans[n][i]->nxt;
#if 0
			printf("\t\tpull %d (%d) to %d\n",
			T1->st, T1->forw, i);
#endif
			srcln[i] = srcln[T1->st];	/* Oyvind Teig, 5.2.0 */

			if (!trans[n][T1->st]) continue;
			T0 = cpytr(trans[n][T1->st]);
			trans[n][i] = T0;
			reach[T1->st] = 1;
			imed(T0, T1->st, n, i);
			for (T1 = T1->nxt; T1; T1 = T1->nxt)
			{
#if 0
			printf("\t\tpull %d (%d) to %d\n",
				T1->st, T1->forw, i);
#endif
		/*		srcln[i] = srcln[T1->st];  gh: not useful */
				if (!trans[n][T1->st]) continue;
				T0->nxt = cpytr(trans[n][T1->st]);
				T0 = T0->nxt;
				reach[T1->st] = 1;
				imed(T0, T1->st, n, i);
	}	}	}
	if (state_tables >= 2)
	{	printf("STEP 3 %s\n", 
			procname[n]);
		for (i = 1; i < m; i++)
		for (T0 = trans[n][i]; T0; T0 = T0->nxt)
			crack(n, i, T0, srcln);
		return;
	}
#ifdef HAS_UNLESS
	for (i = 1; i < m; i++)
	{	if (!trans[n][i]) continue;
		/* check for each state i if an
		 * escape to some state p is defined
		 * if so, copy and mark p's transitions
		 * and prepend them to the transition-
		 * list of state i
		 */
	 if (!like_java) /* the default */
	 {	for (T0 = trans[n][i]; T0; T0 = T0->nxt)
		for (k = HAS_UNLESS-1; k >= 0; k--)
		{	if (p = T0->escp[k])
			for (T1 = trans[n][p]; T1; T1 = T1->nxt)
			{	if (isthere(trans[n][i], T1->t_id))
					continue;
				T2 = cpytr(T1);
				T2->e_trans = p;
				T2->nxt = trans[n][i];
				trans[n][i] = T2;
		}	}
	 } else /* outermost unless checked first */
	 {	T4 = T3 = (Trans *) 0;
		for (T0 = trans[n][i]; T0; T0 = T0->nxt)
		for (k = HAS_UNLESS-1; k >= 0; k--)
		{	if (p = T0->escp[k])
			for (T1 = trans[n][p]; T1; T1 = T1->nxt)
			{	if (isthere(trans[n][i], T1->t_id))
					continue;
				T2 = cpytr(T1);
				T2->nxt = (Trans *) 0;
				T2->e_trans = p;
				if (T3)	T3->nxt = T2;
				else	T4 = T2;
				T3 = T2;
		}	}
		if (T4)
		{	T3->nxt = trans[n][i];
			trans[n][i] = T4;
		}
	 }
	}
#endif
#ifndef NOREDUCE
	for (i = 1; i < m; i++)
	{	if (a_cycles)
		{ /* moves through these states are visible */
	#if PROG_LAB>0 && defined(HAS_NP)
			if (progstate[n][i])
				goto degrade;
			for (T1 = trans[n][i]; T1; T1 = T1->nxt)
				if (progstate[n][T1->st])
					goto degrade;
	#endif
			if (accpstate[n][i] || visstate[n][i])
				goto degrade;
			for (T1 = trans[n][i]; T1; T1 = T1->nxt)
				if (accpstate[n][T1->st])
					goto degrade;
		}
		T1 = trans[n][i];
		if (!T1) continue;
		g = mark_safety(T1);	/* V3.3.1 */
		if (g < 0) goto degrade; /* global */
		/* check if mixing of guards preserves reduction */
		if (T1->nxt)
		{	k = 0;
			for (T0 = T1; T0; T0 = T0->nxt)
			{	if (!(T0->atom&8))
					goto degrade;
				for (aa = 0; aa < 2; aa++)
				{	j = srinc_set(T0->tpe[aa]);
					if (j >= GLOBAL && j != ALPHA_F)
						goto degrade;
					if (T0->tpe[aa]
					&&  T0->tpe[aa]
					!=  T1->tpe[0])
						k = 1;
			}	}
			/* g = 0;	V3.3.1 */
			if (k)	/* non-uniform selection */
			for (T0 = T1; T0; T0 = T0->nxt)
			for (aa = 0; aa < 2; aa++)
			{	j = srinc_set(T0->tpe[aa]);
				if (j != LOCAL)
				{	k = srunc(T0->tpe[aa], j);
					for (h = 0; h < 6; h++)
						if (T1->qu[h] == k
						&&  T1->ty[h] == j)
							break;
					if (h >= 6)
					{	T1->qu[g%6] = k;
						T1->ty[g%6] = j;
						g++;
			}	}	}
			if (g > 6)
			{	T1->qu[0] = 0;	/* turn it off */
				printf("pan: warning, line %d, ",
					srcln[i]);
			 	printf("too many stmnt types (%d)",
					g);
			  	printf(" in selection\n");
			  goto degrade;
			}
		}
		/* mark all options global if >=1 is global */
		for (T1 = trans[n][i]; T1; T1 = T1->nxt)
			if (!(T1->atom&8)) break;
		if (T1)
degrade:	for (T1 = trans[n][i]; T1; T1 = T1->nxt)
			T1->atom &= ~8;	/* mark as unsafe */
		/* can only mix 'r's or 's's if on same chan */
		/* and not mixed with other local operations */
		T1 = trans[n][i];
		if (!T1 || T1->qu[0]) continue;
		j = T1->tpe[0];
		if (T1->nxt && T1->atom&8)
		{ if (j == 5*DELTA)
		  {	printf("warning: line %d ", srcln[i]);
			printf("mixed condition ");
			printf("(defeats reduction)\n");
			goto degrade;
		  }
		  for (T0 = T1; T0; T0 = T0->nxt)
		  for (aa = 0; aa < 2; aa++)
		  if  (T0->tpe[aa] && T0->tpe[aa] != j)
		  {	printf("warning: line %d ", srcln[i]);
			printf("[%d-%d] mixed %stion ",
				T0->tpe[aa], j, 
				(j==5*DELTA)?"condi":"selec");
			printf("(defeats reduction)\n");
			printf("	'%s' <-> '%s'\n",
				T1->tp, T0->tp);
			goto degrade;
		} }
	}
#endif
	for (i = 1; i < m; i++)
	{	T2 = trans[n][i];
		if (!T2
		||  T2->nxt
		||  strncmp(T2->tp, ".(goto)", 7)
		||  !stopstate[n][i])
			continue;
		stopstate[n][T2->st] = 1;
	}
	if (state_tables && !verbose)
	{	if (dodot)
		{	char buf[256], *q = buf, *p = procname[n];
			while (*p != '\0')
			{	if (*p != ':')
				{	*q++ = *p;
				}
				p++;
			}
			*q = '\0';
			printf("digraph ");
			switch (Btypes[n]) {
			case I_PROC:  printf("init {\n"); break;
			case N_CLAIM: printf("claim_%s {\n", buf); break;
			case E_TRACE: printf("notrace {\n"); break;
			case N_TRACE: printf("trace {\n"); break;
			default:      printf("p_%s {\n", buf); break;
			}
			printf("size=\"8,10\";\n");
			printf("  GT [shape=box,style=dotted,label=\"%s\"];\n", buf);
			printf("  GT -> S%d;\n", is);
		} else
		{	switch (Btypes[n]) {
			case I_PROC:  printf("init\n"); break;
			case N_CLAIM: printf("claim %s\n", procname[n]); break;
			case E_TRACE: printf("notrace assertion\n"); break;
			case N_TRACE: printf("trace assertion\n"); break;
			default:      printf("proctype %s\n", procname[n]); break;
		}	}
		for (i = 1; i < m; i++)
		{	reach[i] = 1;
		}
		tagtable(n, m, is, srcln, reach);
		if (dodot) printf("}\n");
	} else
	for (i = 1; i < m; i++)
	{	int nrelse;
		if (Btypes[n] != N_CLAIM)
		{	for (T0 = trans[n][i]; T0; T0 = T0->nxt)
			{	if (T0->st == i
				&& strcmp(T0->tp, "(1)") == 0)
				{	printf("error: proctype '%s' ",
						procname[n]);
		  			printf("line %d, state %d: has un",
						srcln[i], i);
					printf("conditional self-loop\n");
					pan_exit(1);
		}	}	}
		nrelse = 0;
		for (T0 = trans[n][i]; T0; T0 = T0->nxt)
		{	if (strcmp(T0->tp, "else") == 0)
				nrelse++;
		}
		if (nrelse > 1)
		{	printf("error: proctype '%s' state",
				procname[n]);
		  	printf(" %d, inherits %d", i, nrelse);
		  	printf(" 'else' stmnts\n");
			pan_exit(1);
	}	}
#if !defined(LOOPSTATE) && !defined(BFS_PAR)
	if (state_tables)
#endif
	do_dfs(n, m, is, srcln, reach, lpstate);

	if (!t_reverse)
	{	return;
	}
	/* process n, with m states, is=initial state -- reverse list */
	if (!state_tables && Btypes[n] != N_CLAIM)
	{	for (i = 1; i < m; i++)
		{	Trans *Tx = (Trans *) 0; /* list of escapes */
			Trans *Ty = (Trans *) 0; /* its tail element */
			T1 = (Trans *) 0; /* reversed list */
			T2 = (Trans *) 0; /* its tail */
			T3 = (Trans *) 0; /* remembers possible 'else' */

			/* find unless-escapes, they should go first */
			T4 = T5 = T0 = trans[n][i];
	#ifdef HAS_UNLESS
			while (T4 && T4->e_trans) /* escapes are first in orig list */
			{	T5 = T4;	  /* remember predecessor */
				T4 = T4->nxt;
			}
	#endif
			/* T4 points to first non-escape, T5 to its parent, T0 to original list */
			if (T4 != T0)		 /* there was at least one escape */
			{	T3 = T5->nxt;		 /* start of non-escapes */
				T5->nxt = (Trans *) 0;	 /* separate */
				Tx = T0;		 /* start of the escapes */
				Ty = T5;		 /* its tail */
				T0 = T3;		 /* the rest, to be reversed */
			}
			/* T0 points to first non-escape, Tx to the list of escapes, Ty to its tail */

			/* first tail-add non-escape transitions, reversed */
			T3 = (Trans *) 0;
			for (T5 = T0; T5; T5 = T4)
			{	T4 = T5->nxt;
	#ifdef HAS_UNLESS
				if (T5->e_trans)
				{	printf("error: cannot happen!\n");
					continue;
				}
	#endif
				if (strcmp(T5->tp, "else") == 0)
				{	T3 = T5;
					T5->nxt = (Trans *) 0;
				} else
				{	T5->nxt = T1;
					if (!T1) { T2 = T5; }
					T1 = T5;
			}	}
			/* T3 points to a possible else, which is removed from the list */
			/* T1 points to the reversed list so far (without escapes) */
			/* T2 points to the tail element -- where the else should go */
			if (T2 && T3)
			{	T2->nxt = T3;	/* add else */
			} else
			{	if (T3) /* there was an else, but there's no tail */
				{	if (!T1)	/* and no reversed list */
					{	T1 = T3; /* odd, but possible */
					} else		/* even stranger */
					{	T1->nxt = T3;
			}	}	}

			/* add in the escapes, to that they appear at the front */
			if (Tx && Ty) { Ty->nxt = T1; T1 = Tx; }

			trans[n][i] = T1;
			/* reversed, with escapes first and else last */
	}	}
	if (state_tables && verbose)
	{	printf("FINAL proctype %s\n", 
			procname[n]);
		for (i = 1; i < m; i++)
		for (T0 = trans[n][i]; T0; T0 = T0->nxt)
			crack(n, i, T0, srcln);
	}
}
void
imed(Trans *T, int v, int n, int j)	/* set intermediate state */
{	progstate[n][T->st] |= progstate[n][v];
	accpstate[n][T->st] |= accpstate[n][v];
	stopstate[n][T->st] |= stopstate[n][v];
	mapstate[n][j] = T->st;
}
void
tagtable(int n, int m, int is, short srcln[], uchar reach[])
{	Trans *z;

	if (is >= m || !trans[n][is]
	||  is <= 0 || reach[is] == 0)
		return;
	reach[is] = 0;
	if (state_tables)
	for (z = trans[n][is]; z; z = z->nxt)
	{	if (dodot)
			dot_crack(n, is, z);
		else
			crack(n, is, z, srcln);
	}

	for (z = trans[n][is]; z; z = z->nxt)
	{
#ifdef HAS_UNLESS
		int i, j;
#endif
		tagtable(n, m, z->st, srcln, reach);
#ifdef HAS_UNLESS
		for (i = 0; i < HAS_UNLESS; i++)
		{	j = trans[n][is]->escp[i];
			if (!j) break;
			tagtable(n, m, j, srcln, reach);
		}
#endif
	}
}

extern Trans *t_id_lkup[];

void
dfs_table(int n, int m, int is, short srcln[], uchar reach[], uchar lpstate[])
{	Trans *z;

	if (is >= m || is <= 0 || !trans[n][is])
		return;
	if ((reach[is] & (4|8|16)) != 0)
	{	if ((reach[is] & (8|16)) == 16)	/* on stack, not yet recorded */
		{	lpstate[is] = 1;
			reach[is] |= 8; /* recorded */
			if (state_tables && verbose)
			{	printf("state %d line %d is a loopstate\n", is, srcln[is]);
		}	}
		return;
	}
	reach[is] |= (4|16);	/* visited | onstack */
	for (z = trans[n][is]; z; z = z->nxt)
	{	t_id_lkup[z->t_id] = z;
#ifdef HAS_UNLESS
		int i, j;
#endif
		dfs_table(n, m, z->st, srcln, reach, lpstate);
#ifdef HAS_UNLESS
		for (i = 0; i < HAS_UNLESS; i++)
		{	j = trans[n][is]->escp[i];
			if (!j) break;
			dfs_table(n, m, j, srcln, reach, lpstate);
		}
#endif
	}
	reach[is] &= ~16; /* no longer on stack */
}
void
do_dfs(int n, int m, int is, short srcln[], uchar reach[], uchar lpstate[])
{	int i;
	dfs_table(n, m, is, srcln, reach, lpstate);
	for (i = 0; i < m; i++)
		reach[i] &= ~(4|8|16);
}
void
crack(int n, int j, Trans *z, short srcln[])
{	int i;

	if (!z) return;
	printf("	state %3d -(tr %3d)-> state %3d  ",
		j, z->forw, z->st);
	printf("[id %3d tp %3d", z->t_id, z->tpe[0]);
	if (z->tpe[1]) printf(",%d", z->tpe[1]);
#ifdef HAS_UNLESS
	if (z->e_trans)
		printf(" org %3d", z->e_trans);
	else if (state_tables >= 2)
	for (i = 0; i < HAS_UNLESS; i++)
	{	if (!z->escp[i]) break;
		printf(" esc %d", z->escp[i]);
	}
#endif
	printf("]");
	printf(" [%s%s%s%s%s] %s:%d => ",
		z->atom&6?"A":z->atom&32?"D":"-",
		accpstate[n][j]?"a" :"-",
		stopstate[n][j]?"e" : "-",
		progstate[n][j]?"p" : "-",
		z->atom & 8 ?"L":"G",
		PanSource, srcln[j]);
	for (i = 0; z->tp[i]; i++)
		if (z->tp[i] == '\n')
			printf("\\n");
		else
			putchar(z->tp[i]);
	if (verbose && z->qu[0])
	{	printf("\t[");
		for (i = 0; i < 6; i++)
			if (z->qu[i])
				printf("(%d,%d)",
				z->qu[i], z->ty[i]);
		printf("]");
	}
	printf("\n");
	fflush(stdout);
}
/* spin -a m.pml; cc -o pan pan.c; ./pan -D | dot -Tps > foo.ps; ps2pdf foo.ps */
void
dot_crack(int n, int j, Trans *z)
{	int i;

	if (!z) return;
	printf("	S%d -> S%d  [color=black", j, z->st);

	if (z->atom&6) printf(",style=dashed");
	else if (z->atom&32) printf(",style=dotted");
	else if (z->atom&8) printf(",style=solid");
	else printf(",style=bold");

	printf(",label=\"");
	for (i = 0; z->tp[i]; i++)
	{	if (z->tp[i] == '\\'
		&&  z->tp[i+1] == 'n')
		{	i++; printf(" ");
		} else
		{	putchar(z->tp[i]);
	}	}
	printf("\"];\n");
	if (accpstate[n][j]) printf("  S%d [color=red,style=bold];\n", j);
	else if (progstate[n][j]) printf("  S%d [color=green,style=bold];\n", j);
	if (stopstate[n][j]) printf("  S%d [color=blue,style=bold,shape=box];\n", j);
}

#ifdef VAR_RANGES
#define BYTESIZE	32	/* 2^8 : 2^3 = 256:8 = 32 */

typedef struct Vr_Ptr {
	char	*nm;
	uchar	vals[BYTESIZE];
	struct Vr_Ptr *nxt;
} Vr_Ptr;
Vr_Ptr *ranges = (Vr_Ptr *) 0;

void
logval(char *s, int v)
{	Vr_Ptr *tmp;

	if (v<0 || v > 255) return;
	for (tmp = ranges; tmp; tmp = tmp->nxt)
		if (!strcmp(tmp->nm, s))
			goto found;
	tmp = (Vr_Ptr *) emalloc(sizeof(Vr_Ptr));
	tmp->nxt = ranges;
	ranges = tmp;
	tmp->nm = s;
found:
	tmp->vals[(v)/8] |= 1<<((v)%8);
}

void
dumpval(uchar X[], int range)
{	int w, x, i, j = -1;

	for (w = i = 0; w < range; w++)
	for (x = 0; x < 8; x++, i++)
	{
from:		if ((X[w] & (1<<x)))
		{	printf("%d", i);
			j = i;
			goto upto;
	}	}
	return;
	for (w = 0; w < range; w++)
	for (x = 0; x < 8; x++, i++)
	{
upto:		if (!(X[w] & (1<<x)))
		{	if (i-1 == j)
				printf(", ");
			else
				printf("-%d, ", i-1);
			goto from;
	}	}
	if (j >= 0 && j != 255)
		printf("-255");
}

void
dumpranges(void)
{	Vr_Ptr *tmp;
	printf("\nValues assigned within ");
	printf("interval [0..255]:\n");
	for (tmp = ranges; tmp; tmp = tmp->nxt)
	{	printf("\t%s\t: ", tmp->nm);
		dumpval(tmp->vals, BYTESIZE);
		printf("\n");
	}
}
#endif
