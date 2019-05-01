	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC :init: */

	case 3: // STATE 1
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 4: // STATE 2
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 5: // STATE 3
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 6: // STATE 4
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 7: // STATE 5
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 8: // STATE 6
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 9: // STATE 7
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 10: // STATE 8
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 11: // STATE 9
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 12: // STATE 11
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC Control */

	case 13: // STATE 1
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gI1, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 14: // STATE 3
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gI2, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 15: // STATE 8
		;
		_m = unsend(((P3 *)_this)->w1);
		;
		goto R999;

	case 16: // STATE 10
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gI2, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 17: // STATE 15
		;
		_m = unsend(((P3 *)_this)->w2);
		;
		goto R999;

	case 18: // STATE 17
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gI1, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 19: // STATE 22
		;
		_m = unsend(((P3 *)_this)->w1);
		;
		goto R999;

	case 20: // STATE 25
		;
		_m = unsend(((P3 *)_this)->w2);
		;
		goto R999;

	case 21: // STATE 31
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gI2, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 22: // STATE 45
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gI1, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 23: // STATE 49
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gO1, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 24: // STATE 51
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gI2, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 25: // STATE 55
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gO1, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 26: // STATE 59
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gO2, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 27: // STATE 63
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gI1, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 28: // STATE 65
		;
	/* 0 */	((P3 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P3 *)_this)->gO2, XX-1, 0, ((int)((P3 *)_this)->b), 1);
		((P3 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 29: // STATE 70
		;
		_m = unsend(((P3 *)_this)->w1);
		;
		goto R999;

	case 30: // STATE 75
		;
		_m = unsend(((P3 *)_this)->w1);
		;
		goto R999;

	case 31: // STATE 80
		;
		_m = unsend(((P3 *)_this)->w2);
		;
		goto R999;

	case 32: // STATE 85
		;
		_m = unsend(((P3 *)_this)->w2);
		;
		goto R999;

		 /* PROC Train */

	case 33: // STATE 7
		;
		_m = unsend(((P2 *)_this)->senseI);
		;
		goto R999;

	case 34: // STATE 8
		;
		XX = 1;
		unrecv(((P2 *)_this)->change, XX-1, 0, ((P2 *)_this)->color, 1);
		((P2 *)_this)->color = trpt->bup.oval;
		;
		;
		goto R999;

	case 35: // STATE 9
		;
	/* 0 */	((P2 *)_this)->color = trpt->bup.oval;
		;
		;
		goto R999;

	case 36: // STATE 11
		;
	/* 0 */	((P2 *)_this)->color = trpt->bup.oval;
		;
		;
		goto R999;

	case 37: // STATE 15
		;
		voie = trpt->bup.oval;
		;
		goto R999;

	case 38: // STATE 16
		;
		_m = unsend(((P2 *)_this)->senseO);
		;
		goto R999;

	case 39: // STATE 17
		;
		voie = trpt->bup.oval;
		;
		goto R999;

		 /* PROC Feu */

	case 40: // STATE 1
		;
		((P1 *)_this)->color = trpt->bup.oval;
		;
		goto R999;

	case 41: // STATE 2
		;
		_m = unsend(((P1 *)_this)->change);
		;
		goto R999;

	case 42: // STATE 3
		;
	/* 0 */	((P1 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P1 *)_this)->swtch, XX-1, 0, ((int)((P1 *)_this)->b), 1);
		((P1 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 43: // STATE 4
		;
	/* 0 */	((P1 *)_this)->color = trpt->bup.oval;
		;
		;
		goto R999;

	case 44: // STATE 6
		;
	/* 0 */	((P1 *)_this)->color = trpt->bup.oval;
		;
		;
		goto R999;

	case 45: // STATE 10
		;
		((P1 *)_this)->color = trpt->bup.oval;
		;
		goto R999;

	case 46: // STATE 11
		;
		_m = unsend(((P1 *)_this)->change);
		;
		goto R999;

		 /* PROC Sensor */

	case 47: // STATE 1
		;
	/* 0 */	((P0 *)_this)->b = trpt->bup.ovals[1];
		XX = 1;
		unrecv(((P0 *)_this)->sense, XX-1, 0, ((int)((P0 *)_this)->b), 1);
		((P0 *)_this)->b = trpt->bup.ovals[0];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 48: // STATE 2
		;
		_m = unsend(((P0 *)_this)->signal);
		;
		goto R999;

	case 49: // STATE 6
		;
		p_restor(II);
		;
		;
		goto R999;
	}

