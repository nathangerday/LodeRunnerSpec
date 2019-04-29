#define rand	pan_rand
#define pthread_equal(a,b)	((a)==(b))
#if defined(HAS_CODE) && defined(VERBOSE)
	#ifdef BFS_PAR
		bfs_printf("Pr: %d Tr: %d\n", II, t->forw);
	#else
		cpu_printf("Pr: %d Tr: %d\n", II, t->forw);
	#endif
#endif
	switch (t->forw) {
	default: Uerror("bad forward move");
	case 0:	/* if without executable clauses */
		continue;
	case 1: /* generic 'goto' or 'skip' */
		IfNotBlocked
		_m = 3; goto P999;
	case 2: /* generic 'else' */
		IfNotBlocked
		if (trpt->o_pm&1) continue;
		_m = 3; goto P999;

		 /* PROC loby */
	case 3: // STATE 2 - laby.pml:20 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][2] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 4: // STATE 9 - laby.pml:25 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][9] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 5: // STATE 16 - laby.pml:30 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][16] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 6: // STATE 23 - laby.pml:35 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][23] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 7: // STATE 26 - laby.pml:36 - [go!SOUTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][26] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 4); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 4, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 8: // STATE 29 - laby.pml:37 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][29] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 9: // STATE 36 - laby.pml:42 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][36] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 10: // STATE 39 - laby.pml:43 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][39] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 11: // STATE 46 - laby.pml:48 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][46] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 12: // STATE 49 - laby.pml:49 - [go!SOUTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][49] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 4); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 4, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 13: // STATE 52 - laby.pml:50 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][52] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 14: // STATE 59 - laby.pml:55 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][59] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 15: // STATE 62 - laby.pml:56 - [go!SOUTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][62] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 4); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 4, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 16: // STATE 65 - laby.pml:57 - [go!WEST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][65] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 2); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 2, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 17: // STATE 72 - laby.pml:62 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][72] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 18: // STATE 75 - laby.pml:63 - [go!SOUTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][75] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 4); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 4, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 19: // STATE 82 - laby.pml:68 - [go!WEST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][82] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 2); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 2, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 20: // STATE 85 - laby.pml:69 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][85] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 21: // STATE 92 - laby.pml:74 - [go!WEST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][92] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 2); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 2, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 22: // STATE 95 - laby.pml:75 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][95] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 23: // STATE 102 - laby.pml:80 - [go!WEST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][102] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 2); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 2, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 24: // STATE 105 - laby.pml:81 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][105] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 25: // STATE 112 - laby.pml:86 - [go!WEST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][112] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 2); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 2, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 26: // STATE 115 - laby.pml:87 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][115] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 27: // STATE 122 - laby.pml:92 - [go!WEST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][122] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 2); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 2, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 28: // STATE 125 - laby.pml:93 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][125] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 29: // STATE 132 - laby.pml:98 - [go!WEST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][132] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 2); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 2, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 30: // STATE 139 - laby.pml:103 - [go!WEST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][139] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 2); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 2, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 31: // STATE 142 - laby.pml:104 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][142] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 32: // STATE 149 - laby.pml:109 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][149] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 33: // STATE 152 - laby.pml:110 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][152] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 34: // STATE 159 - laby.pml:115 - [go!WEST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][159] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 2); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 2, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 35: // STATE 162 - laby.pml:116 - [go!SOUTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][162] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 4); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 4, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 36: // STATE 169 - laby.pml:121 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][169] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 37: // STATE 176 - laby.pml:126 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][176] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 38: // STATE 179 - laby.pml:127 - [go!WEST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][179] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 2); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 2, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 39: // STATE 182 - laby.pml:128 - [go!EAST] (0:0:0 - 1)
		IfNotBlocked
		reached[1][182] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 3); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 3, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 40: // STATE 189 - laby.pml:133 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][189] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 41: // STATE 192 - laby.pml:134 - [go!SOUTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][192] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 4); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 4, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 42: // STATE 199 - laby.pml:139 - [go!NORTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][199] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 5); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 5, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 43: // STATE 202 - laby.pml:140 - [go!SOUTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][202] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 4); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 4, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 44: // STATE 209 - laby.pml:145 - [go!SOUTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][209] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 4); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 4, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 45: // STATE 216 - laby.pml:150 - [go!SOUTH] (0:0:0 - 1)
		IfNotBlocked
		reached[1][216] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 4); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 4, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 46: // STATE 219 - laby.pml:151 - [go!EXIT] (0:0:0 - 1)
		IfNotBlocked
		reached[1][219] = 1;
		if (q_len(now.go))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.go);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.go, 0, 1, 1);
		{ boq = now.go; };
		_m = 2; goto P999; /* 0 */
	case 47: // STATE 225 - laby.pml:156 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[1][225] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC observateur */
	case 48: // STATE 1 - laby.pml:7 - [go?dir] (0:0:1 - 1)
		reached[0][1] = 1;
		if (boq != now.go) continue;
		if (q_len(now.go) == 0) continue;

		XX=1;
		(trpt+1)->bup.oval = ((P0 *)_this)->dir;
		;
		((P0 *)_this)->dir = qrecv(now.go, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("observateur:dir", ((P0 *)_this)->dir);
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", now.go);
		sprintf(simtmp, "%d", ((P0 *)_this)->dir); strcat(simvals, simtmp);		}
#endif
		if (q_zero(now.go))
		{	boq = -1;
#ifndef NOFAIR
			if (fairness
			&& !(trpt->o_pm&32)
			&& (now._a_t&2)
			&&  now._cnt[now._a_t&1] == II+2)
			{	now._cnt[now._a_t&1] -= 1;
#ifdef VERI
				if (II == 1)
					now._cnt[now._a_t&1] = 1;
#endif
#ifdef DEBUG
			printf("%3ld: proc %d fairness ", depth, II);
			printf("Rule 2: --cnt to %d (%d)\n",
				now._cnt[now._a_t&1], now._a_t);
#endif
				trpt->o_pm |= (32|64);
			}
#endif

		};
		_m = 4; goto P999; /* 0 */
	case 49: // STATE 2 - laby.pml:9 - [((dir==EXIT))] (12:0:1 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		if (!((((P0 *)_this)->dir==1)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: dir */  (trpt+1)->bup.oval = ((P0 *)_this)->dir;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P0 *)_this)->dir = 0;
		/* merge: printf('go EXIT')(0, 3, 12) */
		reached[0][3] = 1;
		Printf("go EXIT");
		/* merge: goto exit(0, 4, 12) */
		reached[0][4] = 1;
		;
		_m = 3; goto P999; /* 2 */
	case 50: // STATE 6 - laby.pml:10 - [printf('go %e\\n',dir)] (0:0:0 - 1)
		IfNotBlocked
		reached[0][6] = 1;
		Printf("go %e\n", ((P0 *)_this)->dir);
		_m = 3; goto P999; /* 0 */
	case 51: // STATE 12 - laby.pml:14 - [(0)] (0:0:0 - 3)
		IfNotBlocked
		reached[0][12] = 1;
		if (!(0))
			continue;
		_m = 3; goto P999; /* 0 */
	case 52: // STATE 13 - laby.pml:15 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[0][13] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */
	case  _T5:	/* np_ */
		if (!((!(trpt->o_pm&4) && !(trpt->tau&128))))
			continue;
		/* else fall through */
	case  _T2:	/* true */
		_m = 3; goto P999;
#undef rand
	}

