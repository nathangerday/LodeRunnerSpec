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
	case 3: // STATE 1 - mutex1.pml:57 - [(run proc1(30))] (0:0:0 - 1)
		IfNotBlocked
		reached[2][1] = 1;
		if (!(addproc(II, 1, 0, 30)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - mutex1.pml:58 - [(run proc2(30))] (0:0:0 - 1)
		IfNotBlocked
		reached[2][2] = 1;
		if (!(addproc(II, 1, 1, 30)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 4 - mutex1.pml:60 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[2][4] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC proc2 */
	case 6: // STATE 1 - mutex1.pml:37 - [((count<repeat))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][1] = 1;
		if (!((((P1 *)_this)->count<((P1 *)_this)->repeat)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 2 - mutex1.pml:37 - [ask1 = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[1][2] = 1;
		(trpt+1)->bup.oval = ((int)now.ask1);
		now.ask1 = 1;
#ifdef VAR_RANGES
		logval("ask1", ((int)now.ask1));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 8: // STATE 3 - mutex1.pml:38 - [(ask0)] (0:0:0 - 1)
		IfNotBlocked
		reached[1][3] = 1;
		if (!(((int)now.ask0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 4 - mutex1.pml:39 - [((turn!=1))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][4] = 1;
		if (!((((int)now.turn)!=1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 10: // STATE 5 - mutex1.pml:39 - [ask1 = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[1][5] = 1;
		(trpt+1)->bup.oval = ((int)now.ask1);
		now.ask1 = 0;
#ifdef VAR_RANGES
		logval("ask1", ((int)now.ask1));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 6 - mutex1.pml:39 - [((turn==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][6] = 1;
		if (!((((int)now.turn)==1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 12: // STATE 7 - mutex1.pml:39 - [ask1 = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[1][7] = 1;
		(trpt+1)->bup.oval = ((int)now.ask1);
		now.ask1 = 1;
#ifdef VAR_RANGES
		logval("ask1", ((int)now.ask1));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 13: // STATE 16 - mutex1.pml:44 - [cs_counter = (cs_counter+1)] (0:0:1 - 3)
		IfNotBlocked
		reached[1][16] = 1;
		(trpt+1)->bup.oval = now.cs_counter;
		now.cs_counter = (now.cs_counter+1);
#ifdef VAR_RANGES
		logval("cs_counter", now.cs_counter);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 14: // STATE 17 - mutex1.pml:45 - [assert((cs_counter==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][17] = 1;
		spin_assert((now.cs_counter==1), "(cs_counter==1)", II, tt, t);
		_m = 3; goto P999; /* 0 */
	case 15: // STATE 18 - mutex1.pml:46 - [cs_counter = (cs_counter-1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][18] = 1;
		(trpt+1)->bup.oval = now.cs_counter;
		now.cs_counter = (now.cs_counter-1);
#ifdef VAR_RANGES
		logval("cs_counter", now.cs_counter);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 16: // STATE 19 - mutex1.pml:47 - [turn = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[1][19] = 1;
		(trpt+1)->bup.oval = ((int)now.turn);
		now.turn = 0;
#ifdef VAR_RANGES
		logval("turn", ((int)now.turn));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 17: // STATE 20 - mutex1.pml:48 - [ask1 = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[1][20] = 1;
		(trpt+1)->bup.oval = ((int)now.ask1);
		now.ask1 = 0;
#ifdef VAR_RANGES
		logval("ask1", ((int)now.ask1));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 18: // STATE 21 - mutex1.pml:50 - [count = (count+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][21] = 1;
		(trpt+1)->bup.oval = ((P1 *)_this)->count;
		((P1 *)_this)->count = (((P1 *)_this)->count+1);
#ifdef VAR_RANGES
		logval("proc2:count", ((P1 *)_this)->count);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 19: // STATE 27 - mutex1.pml:53 - [-end-] (0:0:0 - 3)
		IfNotBlocked
		reached[1][27] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC proc1 */
	case 20: // STATE 1 - mutex1.pml:12 - [((count<repeat))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		if (!((((P0 *)_this)->count<((P0 *)_this)->repeat)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 21: // STATE 2 - mutex1.pml:12 - [ask0 = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		(trpt+1)->bup.oval = ((int)now.ask0);
		now.ask0 = 1;
#ifdef VAR_RANGES
		logval("ask0", ((int)now.ask0));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 22: // STATE 3 - mutex1.pml:13 - [(ask1)] (0:0:0 - 1)
		IfNotBlocked
		reached[0][3] = 1;
		if (!(((int)now.ask1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 23: // STATE 4 - mutex1.pml:14 - [((turn!=0))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][4] = 1;
		if (!((((int)now.turn)!=0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 24: // STATE 5 - mutex1.pml:14 - [ask0 = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[0][5] = 1;
		(trpt+1)->bup.oval = ((int)now.ask0);
		now.ask0 = 0;
#ifdef VAR_RANGES
		logval("ask0", ((int)now.ask0));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 25: // STATE 6 - mutex1.pml:14 - [((turn==0))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][6] = 1;
		if (!((((int)now.turn)==0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 26: // STATE 7 - mutex1.pml:14 - [ask0 = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][7] = 1;
		(trpt+1)->bup.oval = ((int)now.ask0);
		now.ask0 = 1;
#ifdef VAR_RANGES
		logval("ask0", ((int)now.ask0));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 27: // STATE 16 - mutex1.pml:20 - [cs_counter = (cs_counter+1)] (0:0:1 - 3)
		IfNotBlocked
		reached[0][16] = 1;
		(trpt+1)->bup.oval = now.cs_counter;
		now.cs_counter = (now.cs_counter+1);
#ifdef VAR_RANGES
		logval("cs_counter", now.cs_counter);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 28: // STATE 17 - mutex1.pml:21 - [assert((cs_counter==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][17] = 1;
		spin_assert((now.cs_counter==1), "(cs_counter==1)", II, tt, t);
		_m = 3; goto P999; /* 0 */
	case 29: // STATE 18 - mutex1.pml:23 - [cs_counter = (cs_counter-1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][18] = 1;
		(trpt+1)->bup.oval = now.cs_counter;
		now.cs_counter = (now.cs_counter-1);
#ifdef VAR_RANGES
		logval("cs_counter", now.cs_counter);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 30: // STATE 19 - mutex1.pml:25 - [turn = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][19] = 1;
		(trpt+1)->bup.oval = ((int)now.turn);
		now.turn = 1;
#ifdef VAR_RANGES
		logval("turn", ((int)now.turn));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 31: // STATE 20 - mutex1.pml:26 - [ask0 = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[0][20] = 1;
		(trpt+1)->bup.oval = ((int)now.ask0);
		now.ask0 = 0;
#ifdef VAR_RANGES
		logval("ask0", ((int)now.ask0));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 32: // STATE 21 - mutex1.pml:28 - [count = (count+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][21] = 1;
		(trpt+1)->bup.oval = ((P0 *)_this)->count;
		((P0 *)_this)->count = (((P0 *)_this)->count+1);
#ifdef VAR_RANGES
		logval("proc1:count", ((P0 *)_this)->count);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 33: // STATE 27 - mutex1.pml:32 - [-end-] (0:0:0 - 3)
		IfNotBlocked
		reached[0][27] = 1;
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

