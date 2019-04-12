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
	case 3: // STATE 1 - feu_safe.pml:75 - [light = orange] (0:0:1 - 1)
		IfNotBlocked
		reached[3][1] = 1;
		(trpt+1)->bup.oval = now.light;
		now.light = 1;
#ifdef VAR_RANGES
		logval("light", now.light);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - feu_safe.pml:76 - [clignotant = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[3][2] = 1;
		(trpt+1)->bup.oval = ((int)now.clignotant);
		now.clignotant = 1;
#ifdef VAR_RANGES
		logval("clignotant", ((int)now.clignotant));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - feu_safe.pml:77 - [mode = initialisation] (0:0:1 - 1)
		IfNotBlocked
		reached[3][3] = 1;
		(trpt+1)->bup.oval = now.mode;
		now.mode = 6;
#ifdef VAR_RANGES
		logval("mode", now.mode);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 6: // STATE 4 - feu_safe.pml:79 - [(run feu())] (0:0:0 - 1)
		IfNotBlocked
		reached[3][4] = 1;
		if (!(addproc(II, 1, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 5 - feu_safe.pml:80 - [(run injection_panne())] (0:0:0 - 1)
		IfNotBlocked
		reached[3][5] = 1;
		if (!(addproc(II, 1, 2)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 8: // STATE 6 - feu_safe.pml:81 - [(run check_light())] (0:0:0 - 1)
		IfNotBlocked
		reached[3][6] = 1;
		if (!(addproc(II, 1, 1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 8 - feu_safe.pml:83 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[3][8] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC injection_panne */
	case 10: // STATE 1 - feu_safe.pml:64 - [(!(panne))] (0:0:0 - 1)
		IfNotBlocked
		reached[2][1] = 1;
		if (!( !(((int)now.panne))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 2 - feu_safe.pml:64 - [panne = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[2][2] = 1;
		(trpt+1)->bup.oval = ((int)now.panne);
		now.panne = 1;
#ifdef VAR_RANGES
		logval("panne", ((int)now.panne));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 12: // STATE 4 - feu_safe.pml:65 - [panne = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[2][4] = 1;
		(trpt+1)->bup.oval = ((int)now.panne);
		now.panne = 0;
#ifdef VAR_RANGES
		logval("panne", ((int)now.panne));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 13: // STATE 8 - feu_safe.pml:67 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[2][8] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC check_light */
	case 14: // STATE 1 - feu_safe.pml:47 - [ch?e1,e2] (0:0:2 - 1)
		reached[1][1] = 1;
		if (q_len(now.ch) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((P1 *)this)->e1;
		(trpt+1)->bup.ovals[1] = ((P1 *)this)->e2;
		;
		((P1 *)this)->e1 = qrecv(now.ch, XX-1, 0, 0);
#ifdef VAR_RANGES
		logval("check_light:e1", ((P1 *)this)->e1);
#endif
		;
		((P1 *)this)->e2 = qrecv(now.ch, XX-1, 1, 1);
#ifdef VAR_RANGES
		logval("check_light:e2", ((P1 *)this)->e2);
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", now.ch);
		sprintf(simtmp, "%d", ((P1 *)this)->e1); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((P1 *)this)->e2); strcat(simvals, simtmp);		}
#endif
		;
		_m = 4; goto P999; /* 0 */
	case 15: // STATE 2 - feu_safe.pml:51 - [((e1==rouge))] (5:0:0 - 1)
		IfNotBlocked
		reached[1][2] = 1;
		if (!((((P1 *)this)->e1==3)))
			continue;
		/* merge: assert((e2!=orange))(0, 3, 5) */
		reached[1][3] = 1;
		spin_assert((((P1 *)this)->e2!=1), "(e2!=1)", II, tt, t);
		_m = 3; goto P999; /* 1 */
	case 16: // STATE 4 - feu_safe.pml:13 - [assert(!((clignotant&&((light==rouge)||(light==vert)))))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][4] = 1;
		spin_assert( !((((int)now.clignotant)&&((now.light==3)||(now.light==2)))), " !((clignotant&&((light==3)||(light==2))))", II, tt, t);
		_m = 3; goto P999; /* 0 */
	case 17: // STATE 6 - feu_safe.pml:52 - [((e1==orange))] (9:0:0 - 1)
		IfNotBlocked
		reached[1][6] = 1;
		if (!((((P1 *)this)->e1==1)))
			continue;
		/* merge: assert((e2!=vert))(0, 7, 9) */
		reached[1][7] = 1;
		spin_assert((((P1 *)this)->e2!=2), "(e2!=2)", II, tt, t);
		_m = 3; goto P999; /* 1 */
	case 18: // STATE 8 - feu_safe.pml:13 - [assert(!((clignotant&&((light==rouge)||(light==vert)))))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][8] = 1;
		spin_assert( !((((int)now.clignotant)&&((now.light==3)||(now.light==2)))), " !((clignotant&&((light==3)||(light==2))))", II, tt, t);
		_m = 3; goto P999; /* 0 */
	case 19: // STATE 10 - feu_safe.pml:53 - [((e1==vert))] (13:0:0 - 1)
		IfNotBlocked
		reached[1][10] = 1;
		if (!((((P1 *)this)->e1==2)))
			continue;
		/* merge: assert((e2!=rouge))(0, 11, 13) */
		reached[1][11] = 1;
		spin_assert((((P1 *)this)->e2!=3), "(e2!=3)", II, tt, t);
		_m = 3; goto P999; /* 1 */
	case 20: // STATE 12 - feu_safe.pml:13 - [assert(!((clignotant&&((light==rouge)||(light==vert)))))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][12] = 1;
		spin_assert( !((((int)now.clignotant)&&((now.light==3)||(now.light==2)))), " !((clignotant&&((light==3)||(light==2))))", II, tt, t);
		_m = 3; goto P999; /* 0 */
	case 21: // STATE 17 - feu_safe.pml:58 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[1][17] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC feu */
	case 22: // STATE 1 - feu_safe.pml:24 - [((((mode==initialisation)&&!(panne))&&clignotant))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		if (!((((now.mode==6)&& !(((int)now.panne)))&&((int)now.clignotant))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 23: // STATE 2 - feu_safe.pml:25 - [etat_avant = light] (0:0:1 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->etat_avant;
		((P0 *)this)->etat_avant = now.light;
#ifdef VAR_RANGES
		logval("feu:etat_avant", ((P0 *)this)->etat_avant);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 24: // STATE 3 - feu_safe.pml:26 - [mode = actif] (0:0:1 - 1)
		IfNotBlocked
		reached[0][3] = 1;
		(trpt+1)->bup.oval = now.mode;
		now.mode = 5;
#ifdef VAR_RANGES
		logval("mode", now.mode);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 25: // STATE 4 - feu_safe.pml:27 - [light = rouge] (0:0:1 - 1)
		IfNotBlocked
		reached[0][4] = 1;
		(trpt+1)->bup.oval = now.light;
		now.light = 3;
#ifdef VAR_RANGES
		logval("light", now.light);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 26: // STATE 5 - feu_safe.pml:28 - [clignotant = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[0][5] = 1;
		(trpt+1)->bup.oval = ((int)now.clignotant);
		now.clignotant = 0;
#ifdef VAR_RANGES
		logval("clignotant", ((int)now.clignotant));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 27: // STATE 6 - feu_safe.pml:29 - [etat_apres = light] (0:0:1 - 1)
		IfNotBlocked
		reached[0][6] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->etat_apres;
		((P0 *)this)->etat_apres = now.light;
#ifdef VAR_RANGES
		logval("feu:etat_apres", ((P0 *)this)->etat_apres);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 28: // STATE 7 - feu_safe.pml:13 - [assert(!((clignotant&&((light==rouge)||(light==vert)))))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][7] = 1;
		spin_assert( !((((int)now.clignotant)&&((now.light==3)||(now.light==2)))), " !((clignotant&&((light==3)||(light==2))))", II, tt, t);
		_m = 3; goto P999; /* 0 */
	case 29: // STATE 9 - feu_safe.pml:30 - [ch!etat_avant,etat_apres] (0:0:0 - 1)
		IfNotBlocked
		reached[0][9] = 1;
		if (q_full(now.ch))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.ch);
		sprintf(simtmp, "%d", ((P0 *)this)->etat_avant); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((P0 *)this)->etat_apres); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.ch, 0, ((P0 *)this)->etat_avant, ((P0 *)this)->etat_apres, 2);
		_m = 2; goto P999; /* 0 */
	case 30: // STATE 10 - feu_safe.pml:31 - [((((mode==actif)&&!(panne))&&!(clignotant)))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][10] = 1;
		if (!((((now.mode==5)&& !(((int)now.panne)))&& !(((int)now.clignotant)))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 31: // STATE 11 - feu_safe.pml:32 - [((((light==rouge)&&!(panne))&&!(clignotant)))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][11] = 1;
		if (!((((now.light==3)&& !(((int)now.panne)))&& !(((int)now.clignotant)))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 32: // STATE 12 - feu_safe.pml:32 - [etat_avant = light] (0:0:1 - 1)
		IfNotBlocked
		reached[0][12] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->etat_avant;
		((P0 *)this)->etat_avant = now.light;
#ifdef VAR_RANGES
		logval("feu:etat_avant", ((P0 *)this)->etat_avant);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 33: // STATE 13 - feu_safe.pml:32 - [light = vert] (0:0:1 - 1)
		IfNotBlocked
		reached[0][13] = 1;
		(trpt+1)->bup.oval = now.light;
		now.light = 2;
#ifdef VAR_RANGES
		logval("light", now.light);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 34: // STATE 14 - feu_safe.pml:32 - [etat_apres = light] (0:0:1 - 1)
		IfNotBlocked
		reached[0][14] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->etat_apres;
		((P0 *)this)->etat_apres = now.light;
#ifdef VAR_RANGES
		logval("feu:etat_apres", ((P0 *)this)->etat_apres);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 35: // STATE 15 - feu_safe.pml:32 - [ch!etat_avant,etat_apres] (0:0:0 - 1)
		IfNotBlocked
		reached[0][15] = 1;
		if (q_full(now.ch))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.ch);
		sprintf(simtmp, "%d", ((P0 *)this)->etat_avant); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((P0 *)this)->etat_apres); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.ch, 0, ((P0 *)this)->etat_avant, ((P0 *)this)->etat_apres, 2);
		_m = 2; goto P999; /* 0 */
	case 36: // STATE 16 - feu_safe.pml:33 - [((((light==vert)&&!(panne))&&!(clignotant)))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][16] = 1;
		if (!((((now.light==2)&& !(((int)now.panne)))&& !(((int)now.clignotant)))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 37: // STATE 17 - feu_safe.pml:33 - [etat_avant = light] (0:0:1 - 1)
		IfNotBlocked
		reached[0][17] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->etat_avant;
		((P0 *)this)->etat_avant = now.light;
#ifdef VAR_RANGES
		logval("feu:etat_avant", ((P0 *)this)->etat_avant);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 38: // STATE 18 - feu_safe.pml:33 - [light = orange] (0:0:1 - 1)
		IfNotBlocked
		reached[0][18] = 1;
		(trpt+1)->bup.oval = now.light;
		now.light = 1;
#ifdef VAR_RANGES
		logval("light", now.light);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 39: // STATE 19 - feu_safe.pml:33 - [etat_apres = light] (0:0:1 - 1)
		IfNotBlocked
		reached[0][19] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->etat_apres;
		((P0 *)this)->etat_apres = now.light;
#ifdef VAR_RANGES
		logval("feu:etat_apres", ((P0 *)this)->etat_apres);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 40: // STATE 20 - feu_safe.pml:33 - [ch!etat_avant,etat_apres] (0:0:0 - 1)
		IfNotBlocked
		reached[0][20] = 1;
		if (q_full(now.ch))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.ch);
		sprintf(simtmp, "%d", ((P0 *)this)->etat_avant); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((P0 *)this)->etat_apres); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.ch, 0, ((P0 *)this)->etat_avant, ((P0 *)this)->etat_apres, 2);
		_m = 2; goto P999; /* 0 */
	case 41: // STATE 21 - feu_safe.pml:34 - [((((light==orange)&&!(panne))&&!(clignotant)))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][21] = 1;
		if (!((((now.light==1)&& !(((int)now.panne)))&& !(((int)now.clignotant)))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 42: // STATE 22 - feu_safe.pml:34 - [etat_avant = light] (0:0:1 - 1)
		IfNotBlocked
		reached[0][22] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->etat_avant;
		((P0 *)this)->etat_avant = now.light;
#ifdef VAR_RANGES
		logval("feu:etat_avant", ((P0 *)this)->etat_avant);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 43: // STATE 23 - feu_safe.pml:34 - [light = rouge] (0:0:1 - 1)
		IfNotBlocked
		reached[0][23] = 1;
		(trpt+1)->bup.oval = now.light;
		now.light = 3;
#ifdef VAR_RANGES
		logval("light", now.light);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 44: // STATE 24 - feu_safe.pml:34 - [etat_apres = light] (0:0:1 - 1)
		IfNotBlocked
		reached[0][24] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->etat_apres;
		((P0 *)this)->etat_apres = now.light;
#ifdef VAR_RANGES
		logval("feu:etat_apres", ((P0 *)this)->etat_apres);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 45: // STATE 25 - feu_safe.pml:34 - [ch!etat_avant,etat_apres] (0:0:0 - 1)
		IfNotBlocked
		reached[0][25] = 1;
		if (q_full(now.ch))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.ch);
		sprintf(simtmp, "%d", ((P0 *)this)->etat_avant); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((P0 *)this)->etat_apres); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.ch, 0, ((P0 *)this)->etat_avant, ((P0 *)this)->etat_apres, 2);
		_m = 2; goto P999; /* 0 */
	case 46: // STATE 28 - feu_safe.pml:36 - [(panne)] (0:0:0 - 1)
		IfNotBlocked
		reached[0][28] = 1;
		if (!(((int)now.panne)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 47: // STATE 29 - feu_safe.pml:36 - [light = orange] (0:0:1 - 1)
		IfNotBlocked
		reached[0][29] = 1;
		(trpt+1)->bup.oval = now.light;
		now.light = 1;
#ifdef VAR_RANGES
		logval("light", now.light);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 48: // STATE 30 - feu_safe.pml:36 - [clignotant = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][30] = 1;
		(trpt+1)->bup.oval = ((int)now.clignotant);
		now.clignotant = 1;
#ifdef VAR_RANGES
		logval("clignotant", ((int)now.clignotant));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 49: // STATE 31 - feu_safe.pml:36 - [etat_apres = light] (0:0:1 - 1)
		IfNotBlocked
		reached[0][31] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->etat_apres;
		((P0 *)this)->etat_apres = now.light;
#ifdef VAR_RANGES
		logval("feu:etat_apres", ((P0 *)this)->etat_apres);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 50: // STATE 32 - feu_safe.pml:36 - [ch!etat_avant,etat_apres] (0:0:0 - 1)
		IfNotBlocked
		reached[0][32] = 1;
		if (q_full(now.ch))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.ch);
		sprintf(simtmp, "%d", ((P0 *)this)->etat_avant); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((P0 *)this)->etat_apres); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.ch, 0, ((P0 *)this)->etat_avant, ((P0 *)this)->etat_apres, 2);
		_m = 2; goto P999; /* 0 */
	case 51: // STATE 37 - feu_safe.pml:42 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[0][37] = 1;
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

