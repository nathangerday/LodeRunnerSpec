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
	case 3: // STATE 1 - feu.pml:27 - [myFeu.couleur = ORANGE] (0:0:1 - 1)
		IfNotBlocked
		reached[1][1] = 1;
		(trpt+1)->bup.oval = now.myFeu.couleur;
		now.myFeu.couleur = 2;
#ifdef VAR_RANGES
		logval("myFeu.couleur", now.myFeu.couleur);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - feu.pml:28 - [myFeu.etat = INITIALISATION] (0:0:1 - 1)
		IfNotBlocked
		reached[1][2] = 1;
		(trpt+1)->bup.oval = now.myFeu.etat;
		now.myFeu.etat = 5;
#ifdef VAR_RANGES
		logval("myFeu.etat", now.myFeu.etat);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - feu.pml:29 - [myFeu.mode = CLIGNOTANT] (0:0:1 - 1)
		IfNotBlocked
		reached[1][3] = 1;
		(trpt+1)->bup.oval = now.myFeu.mode;
		now.myFeu.mode = 8;
#ifdef VAR_RANGES
		logval("myFeu.mode", now.myFeu.mode);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 6: // STATE 4 - feu.pml:30 - [(run exec())] (0:0:0 - 1)
		IfNotBlocked
		reached[1][4] = 1;
		if (!(addproc(II, 1, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 5 - feu.pml:31 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[1][5] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC exec */
	case 8: // STATE 1 - feu.pml:14 - [myFeu.couleur = ROUGE] (0:0:1 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		(trpt+1)->bup.oval = now.myFeu.couleur;
		now.myFeu.couleur = 3;
#ifdef VAR_RANGES
		logval("myFeu.couleur", now.myFeu.couleur);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 2 - feu.pml:15 - [myFeu.etat = ACTIF] (0:0:1 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		(trpt+1)->bup.oval = now.myFeu.etat;
		now.myFeu.etat = 4;
#ifdef VAR_RANGES
		logval("myFeu.etat", now.myFeu.etat);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 10: // STATE 3 - feu.pml:16 - [myFeu.mode = CONTINU] (0:0:1 - 1)
		IfNotBlocked
		reached[0][3] = 1;
		(trpt+1)->bup.oval = now.myFeu.mode;
		now.myFeu.mode = 7;
#ifdef VAR_RANGES
		logval("myFeu.mode", now.myFeu.mode);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 4 - feu.pml:19 - [((myFeu.couleur==ROUGE))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][4] = 1;
		if (!((now.myFeu.couleur==3)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 12: // STATE 5 - feu.pml:19 - [myFeu.couleur = VERT] (0:0:1 - 1)
		IfNotBlocked
		reached[0][5] = 1;
		(trpt+1)->bup.oval = now.myFeu.couleur;
		now.myFeu.couleur = 1;
#ifdef VAR_RANGES
		logval("myFeu.couleur", now.myFeu.couleur);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 13: // STATE 7 - feu.pml:20 - [((myFeu.couleur==VERT))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][7] = 1;
		if (!((now.myFeu.couleur==1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 14: // STATE 8 - feu.pml:20 - [myFeu.couleur = ORANGE] (0:0:1 - 1)
		IfNotBlocked
		reached[0][8] = 1;
		(trpt+1)->bup.oval = now.myFeu.couleur;
		now.myFeu.couleur = 2;
#ifdef VAR_RANGES
		logval("myFeu.couleur", now.myFeu.couleur);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 15: // STATE 10 - feu.pml:21 - [((myFeu.couleur==ORANGE))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][10] = 1;
		if (!((now.myFeu.couleur==2)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 16: // STATE 11 - feu.pml:21 - [myFeu.couleur = ROUGE] (0:0:1 - 1)
		IfNotBlocked
		reached[0][11] = 1;
		(trpt+1)->bup.oval = now.myFeu.couleur;
		now.myFeu.couleur = 3;
#ifdef VAR_RANGES
		logval("myFeu.couleur", now.myFeu.couleur);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 17: // STATE 14 - feu.pml:22 - [myFeu.etat = PANNE] (0:0:1 - 1)
		IfNotBlocked
		reached[0][14] = 1;
		(trpt+1)->bup.oval = now.myFeu.etat;
		now.myFeu.etat = 6;
#ifdef VAR_RANGES
		logval("myFeu.etat", now.myFeu.etat);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 18: // STATE 15 - feu.pml:22 - [myFeu.couleur = ORANGE] (0:0:1 - 1)
		IfNotBlocked
		reached[0][15] = 1;
		(trpt+1)->bup.oval = now.myFeu.couleur;
		now.myFeu.couleur = 2;
#ifdef VAR_RANGES
		logval("myFeu.couleur", now.myFeu.couleur);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 19: // STATE 16 - feu.pml:22 - [myFeu.mode = CLIGNOTANT] (0:0:1 - 1)
		IfNotBlocked
		reached[0][16] = 1;
		(trpt+1)->bup.oval = now.myFeu.mode;
		now.myFeu.mode = 8;
#ifdef VAR_RANGES
		logval("myFeu.mode", now.myFeu.mode);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 20: // STATE 19 - feu.pml:24 - [-end-] (0:0:0 - 2)
		IfNotBlocked
		reached[0][19] = 1;
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

