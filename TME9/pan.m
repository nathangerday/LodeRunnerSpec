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

		 /* PROC :init: */
	case 3: // STATE 1 - train.pml:154 - [(run Sensor(sI1,gI1))] (0:0:0 - 1)
		IfNotBlocked
		reached[4][1] = 1;
		if (!(addproc(II, 1, 0, ((P4 *)_this)->sI1, ((P4 *)_this)->gI1, 0, 0, 0, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - train.pml:155 - [(run Sensor(sI2,gI2))] (0:0:0 - 1)
		IfNotBlocked
		reached[4][2] = 1;
		if (!(addproc(II, 1, 0, ((P4 *)_this)->sI2, ((P4 *)_this)->gI2, 0, 0, 0, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - train.pml:156 - [(run Sensor(sO1,gI1))] (0:0:0 - 1)
		IfNotBlocked
		reached[4][3] = 1;
		if (!(addproc(II, 1, 0, ((P4 *)_this)->sO1, ((P4 *)_this)->gI1, 0, 0, 0, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 6: // STATE 4 - train.pml:157 - [(run Sensor(sO2,gI2))] (0:0:0 - 1)
		IfNotBlocked
		reached[4][4] = 1;
		if (!(addproc(II, 1, 0, ((P4 *)_this)->sO2, ((P4 *)_this)->gI2, 0, 0, 0, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 5 - train.pml:159 - [(run Feu(w1,c1))] (0:0:0 - 1)
		IfNotBlocked
		reached[4][5] = 1;
		if (!(addproc(II, 1, 1, ((P4 *)_this)->w1, ((P4 *)_this)->c1, 0, 0, 0, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 8: // STATE 6 - train.pml:160 - [(run Feu(c2,c2))] (0:0:0 - 1)
		IfNotBlocked
		reached[4][6] = 1;
		if (!(addproc(II, 1, 1, ((P4 *)_this)->c2, ((P4 *)_this)->c2, 0, 0, 0, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 7 - train.pml:161 - [(run Train(sI1,sO1,c1))] (0:0:0 - 1)
		IfNotBlocked
		reached[4][7] = 1;
		if (!(addproc(II, 1, 2, ((P4 *)_this)->sI1, ((P4 *)_this)->sO1, ((P4 *)_this)->c1, 0, 0, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 10: // STATE 8 - train.pml:162 - [(run Train(sI2,sO2,c2))] (0:0:0 - 1)
		IfNotBlocked
		reached[4][8] = 1;
		if (!(addproc(II, 1, 2, ((P4 *)_this)->sI2, ((P4 *)_this)->sO2, ((P4 *)_this)->c2, 0, 0, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 9 - train.pml:164 - [(run Control(gI1,gI2,gO1,gO2,w1,w2))] (0:0:0 - 1)
		IfNotBlocked
		reached[4][9] = 1;
		if (!(addproc(II, 1, 3, ((P4 *)_this)->gI1, ((P4 *)_this)->gI2, ((P4 *)_this)->gO1, ((P4 *)_this)->gO2, ((P4 *)_this)->w1, ((P4 *)_this)->w2)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 12: // STATE 11 - train.pml:166 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[4][11] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC Control */
	case 13: // STATE 1 - train.pml:59 - [gI1?b] (0:0:2 - 1)
		reached[3][1] = 1;
		if (boq != ((P3 *)_this)->gI1) continue;
		if (q_len(((P3 *)_this)->gI1) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gI1, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gI1);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gI1))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 14: // STATE 3 - train.pml:60 - [gI2?b] (0:0:2 - 1)
		reached[3][3] = 1;
		if (boq != ((P3 *)_this)->gI2) continue;
		if (q_len(((P3 *)_this)->gI2) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gI2, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gI2);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gI2))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 15: // STATE 8 - train.pml:64 - [w1!1] (0:0:0 - 1)
		IfNotBlocked
		reached[3][8] = 1;
		if (q_len(((P3 *)_this)->w1))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P3 *)_this)->w1);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P3 *)_this)->w1, 0, 1, 1);
		{ boq = ((P3 *)_this)->w1; };
		_m = 2; goto P999; /* 0 */
	case 16: // STATE 10 - train.pml:65 - [gI2?b] (0:0:2 - 1)
		reached[3][10] = 1;
		if (boq != ((P3 *)_this)->gI2) continue;
		if (q_len(((P3 *)_this)->gI2) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gI2, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gI2);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gI2))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 17: // STATE 15 - train.pml:69 - [w2!1] (0:0:0 - 1)
		IfNotBlocked
		reached[3][15] = 1;
		if (q_len(((P3 *)_this)->w2))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P3 *)_this)->w2);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P3 *)_this)->w2, 0, 1, 1);
		{ boq = ((P3 *)_this)->w2; };
		_m = 2; goto P999; /* 0 */
	case 18: // STATE 17 - train.pml:70 - [gI1?b] (0:0:2 - 1)
		reached[3][17] = 1;
		if (boq != ((P3 *)_this)->gI1) continue;
		if (q_len(((P3 *)_this)->gI1) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gI1, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gI1);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gI1))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 19: // STATE 22 - train.pml:74 - [w1!1] (0:0:0 - 1)
		IfNotBlocked
		reached[3][22] = 1;
		if (q_len(((P3 *)_this)->w1))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P3 *)_this)->w1);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P3 *)_this)->w1, 0, 1, 1);
		{ boq = ((P3 *)_this)->w1; };
		_m = 2; goto P999; /* 0 */
	case 20: // STATE 25 - train.pml:75 - [w2!1] (0:0:0 - 1)
		IfNotBlocked
		reached[3][25] = 1;
		if (q_len(((P3 *)_this)->w2))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P3 *)_this)->w2);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P3 *)_this)->w2, 0, 1, 1);
		{ boq = ((P3 *)_this)->w2; };
		_m = 2; goto P999; /* 0 */
	case 21: // STATE 31 - train.pml:80 - [gI2?b] (0:0:2 - 1)
		reached[3][31] = 1;
		if (boq != ((P3 *)_this)->gI2) continue;
		if (q_len(((P3 *)_this)->gI2) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gI2, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gI2);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gI2))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 22: // STATE 45 - train.pml:93 - [gI1?b] (0:0:2 - 1)
		reached[3][45] = 1;
		if (boq != ((P3 *)_this)->gI1) continue;
		if (q_len(((P3 *)_this)->gI1) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gI1, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gI1);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gI1))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 23: // STATE 49 - train.pml:97 - [gO1?b] (0:0:2 - 1)
		reached[3][49] = 1;
		if (boq != ((P3 *)_this)->gO1) continue;
		if (q_len(((P3 *)_this)->gO1) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gO1, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gO1);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gO1))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 24: // STATE 51 - train.pml:98 - [gI2?b] (0:0:2 - 1)
		reached[3][51] = 1;
		if (boq != ((P3 *)_this)->gI2) continue;
		if (q_len(((P3 *)_this)->gI2) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gI2, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gI2);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gI2))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 25: // STATE 55 - train.pml:102 - [gO1?b] (0:0:2 - 1)
		reached[3][55] = 1;
		if (boq != ((P3 *)_this)->gO1) continue;
		if (q_len(((P3 *)_this)->gO1) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gO1, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gO1);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gO1))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 26: // STATE 59 - train.pml:106 - [gO2?b] (0:0:2 - 1)
		reached[3][59] = 1;
		if (boq != ((P3 *)_this)->gO2) continue;
		if (q_len(((P3 *)_this)->gO2) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gO2, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gO2);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gO2))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 27: // STATE 63 - train.pml:110 - [gI1?b] (0:0:2 - 1)
		reached[3][63] = 1;
		if (boq != ((P3 *)_this)->gI1) continue;
		if (q_len(((P3 *)_this)->gI1) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gI1, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gI1);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gI1))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 28: // STATE 65 - train.pml:111 - [gO2?b] (0:0:2 - 1)
		reached[3][65] = 1;
		if (boq != ((P3 *)_this)->gO2) continue;
		if (q_len(((P3 *)_this)->gO2) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P3 *)_this)->b);
		;
		((P3 *)_this)->b = qrecv(((P3 *)_this)->gO2, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Control:b", ((int)((P3 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P3 *)_this)->gO2);
		sprintf(simtmp, "%d", ((int)((P3 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P3 *)_this)->gO2))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P3 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P3 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 29: // STATE 70 - train.pml:115 - [w1!1] (0:0:0 - 1)
		IfNotBlocked
		reached[3][70] = 1;
		if (q_len(((P3 *)_this)->w1))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P3 *)_this)->w1);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P3 *)_this)->w1, 0, 1, 1);
		{ boq = ((P3 *)_this)->w1; };
		_m = 2; goto P999; /* 0 */
	case 30: // STATE 75 - train.pml:119 - [w1!1] (0:0:0 - 1)
		IfNotBlocked
		reached[3][75] = 1;
		if (q_len(((P3 *)_this)->w1))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P3 *)_this)->w1);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P3 *)_this)->w1, 0, 1, 1);
		{ boq = ((P3 *)_this)->w1; };
		_m = 2; goto P999; /* 0 */
	case 31: // STATE 80 - train.pml:123 - [w2!1] (0:0:0 - 1)
		IfNotBlocked
		reached[3][80] = 1;
		if (q_len(((P3 *)_this)->w2))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P3 *)_this)->w2);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P3 *)_this)->w2, 0, 1, 1);
		{ boq = ((P3 *)_this)->w2; };
		_m = 2; goto P999; /* 0 */
	case 32: // STATE 85 - train.pml:127 - [w2!1] (0:0:0 - 1)
		IfNotBlocked
		reached[3][85] = 1;
		if (q_len(((P3 *)_this)->w2))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P3 *)_this)->w2);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P3 *)_this)->w2, 0, 1, 1);
		{ boq = ((P3 *)_this)->w2; };
		_m = 2; goto P999; /* 0 */

		 /* PROC Train */
	case 33: // STATE 7 - train.pml:39 - [senseI!1] (0:0:0 - 3)
		IfNotBlocked
		reached[2][7] = 1;
		if (q_len(((P2 *)_this)->senseI))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P2 *)_this)->senseI);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P2 *)_this)->senseI, 0, 1, 1);
		{ boq = ((P2 *)_this)->senseI; };
		_m = 2; goto P999; /* 0 */
	case 34: // STATE 8 - train.pml:42 - [change?color] (0:0:1 - 3)
		reached[2][8] = 1;
		if (boq != ((P2 *)_this)->change) continue;
		if (q_len(((P2 *)_this)->change) == 0) continue;

		XX=1;
		(trpt+1)->bup.oval = ((P2 *)_this)->color;
		;
		((P2 *)_this)->color = qrecv(((P2 *)_this)->change, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Train:color", ((P2 *)_this)->color);
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P2 *)_this)->change);
		sprintf(simtmp, "%d", ((P2 *)_this)->color); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P2 *)_this)->change))
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
	case 35: // STATE 9 - train.pml:43 - [((color==GREEN))] (0:0:1 - 1)
		IfNotBlocked
		reached[2][9] = 1;
		if (!((((P2 *)_this)->color==1)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: color */  (trpt+1)->bup.oval = ((P2 *)_this)->color;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P2 *)_this)->color = 0;
		_m = 3; goto P999; /* 0 */
	case 36: // STATE 11 - train.pml:44 - [((color==RED))] (0:0:1 - 1)
		IfNotBlocked
		reached[2][11] = 1;
		if (!((((P2 *)_this)->color==2)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: color */  (trpt+1)->bup.oval = ((P2 *)_this)->color;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P2 *)_this)->color = 0;
		_m = 3; goto P999; /* 0 */
	case 37: // STATE 15 - train.pml:47 - [voie = (voie+1)] (0:0:1 - 3)
		IfNotBlocked
		reached[2][15] = 1;
		(trpt+1)->bup.oval = voie;
		voie = (voie+1);
#ifdef VAR_RANGES
		logval("voie", voie);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 38: // STATE 16 - train.pml:49 - [senseO!1] (0:0:0 - 1)
		IfNotBlocked
		reached[2][16] = 1;
		if (q_len(((P2 *)_this)->senseO))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P2 *)_this)->senseO);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P2 *)_this)->senseO, 0, 1, 1);
		{ boq = ((P2 *)_this)->senseO; };
		_m = 2; goto P999; /* 0 */
	case 39: // STATE 17 - train.pml:50 - [voie = (voie-1)] (0:0:1 - 1)
		IfNotBlocked
		reached[2][17] = 1;
		(trpt+1)->bup.oval = voie;
		voie = (voie-1);
#ifdef VAR_RANGES
		logval("voie", voie);
#endif
		;
		_m = 3; goto P999; /* 0 */

		 /* PROC Feu */
	case 40: // STATE 1 - train.pml:18 - [color = RED] (0:0:1 - 1)
		IfNotBlocked
		reached[1][1] = 1;
		(trpt+1)->bup.oval = ((P1 *)_this)->color;
		((P1 *)_this)->color = 2;
#ifdef VAR_RANGES
		logval("Feu:color", ((P1 *)_this)->color);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 41: // STATE 2 - train.pml:19 - [change!color] (0:0:0 - 1)
		IfNotBlocked
		reached[1][2] = 1;
		if (q_len(((P1 *)_this)->change))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P1 *)_this)->change);
		sprintf(simtmp, "%d", ((P1 *)_this)->color); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P1 *)_this)->change, 0, ((P1 *)_this)->color, 1);
		{ boq = ((P1 *)_this)->change; };
		_m = 2; goto P999; /* 0 */
	case 42: // STATE 3 - train.pml:21 - [swtch?b] (0:0:2 - 3)
		reached[1][3] = 1;
		if (boq != ((P1 *)_this)->swtch) continue;
		if (q_len(((P1 *)_this)->swtch) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P1 *)_this)->b);
		;
		((P1 *)_this)->b = qrecv(((P1 *)_this)->swtch, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Feu:b", ((int)((P1 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P1 *)_this)->swtch);
		sprintf(simtmp, "%d", ((int)((P1 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P1 *)_this)->swtch))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P1 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P1 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 43: // STATE 4 - train.pml:22 - [((color==RED))] (0:0:1 - 1)
		IfNotBlocked
		reached[1][4] = 1;
		if (!((((P1 *)_this)->color==2)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: color */  (trpt+1)->bup.oval = ((P1 *)_this)->color;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P1 *)_this)->color = 0;
		_m = 3; goto P999; /* 0 */
	case 44: // STATE 6 - train.pml:23 - [((color==GREEN))] (0:0:1 - 1)
		IfNotBlocked
		reached[1][6] = 1;
		if (!((((P1 *)_this)->color==1)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: color */  (trpt+1)->bup.oval = ((P1 *)_this)->color;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P1 *)_this)->color = 0;
		_m = 3; goto P999; /* 0 */
	case 45: // STATE 10 - train.pml:26 - [color = GREEN] (0:0:1 - 3)
		IfNotBlocked
		reached[1][10] = 1;
		(trpt+1)->bup.oval = ((P1 *)_this)->color;
		((P1 *)_this)->color = 1;
#ifdef VAR_RANGES
		logval("Feu:color", ((P1 *)_this)->color);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 46: // STATE 11 - train.pml:27 - [change!color] (0:0:0 - 1)
		IfNotBlocked
		reached[1][11] = 1;
		if (q_len(((P1 *)_this)->change))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P1 *)_this)->change);
		sprintf(simtmp, "%d", ((P1 *)_this)->color); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P1 *)_this)->change, 0, ((P1 *)_this)->color, 1);
		{ boq = ((P1 *)_this)->change; };
		_m = 2; goto P999; /* 0 */

		 /* PROC Sensor */
	case 47: // STATE 1 - train.pml:10 - [sense?b] (0:0:2 - 1)
		reached[0][1] = 1;
		if (boq != ((P0 *)_this)->sense) continue;
		if (q_len(((P0 *)_this)->sense) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)((P0 *)_this)->b);
		;
		((P0 *)_this)->b = qrecv(((P0 *)_this)->sense, XX-1, 0, 1);
#ifdef VAR_RANGES
		logval("Sensor:b", ((int)((P0 *)_this)->b));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", ((P0 *)_this)->sense);
		sprintf(simtmp, "%d", ((int)((P0 *)_this)->b)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(((P0 *)_this)->sense))
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
		if (TstOnly) return 1; /* TT */
		/* dead 2: b */  (trpt+1)->bup.ovals[1] = ((P0 *)_this)->b;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P0 *)_this)->b = 0;
		_m = 4; goto P999; /* 0 */
	case 48: // STATE 2 - train.pml:10 - [signal!1] (0:0:0 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		if (q_len(((P0 *)_this)->signal))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", ((P0 *)_this)->signal);
		sprintf(simtmp, "%d", 1); strcat(simvals, simtmp);		}
#endif
		
		qsend(((P0 *)_this)->signal, 0, 1, 1);
		{ boq = ((P0 *)_this)->signal; };
		_m = 2; goto P999; /* 0 */
	case 49: // STATE 6 - train.pml:12 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[0][6] = 1;
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

