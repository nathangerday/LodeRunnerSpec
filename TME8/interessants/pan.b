	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC :init: */

	case 3: // STATE 1
		;
		now.light = trpt->bup.oval;
		;
		goto R999;

	case 4: // STATE 2
		;
		now.clignotant = trpt->bup.oval;
		;
		goto R999;

	case 5: // STATE 3
		;
		now.mode = trpt->bup.oval;
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

	case 9: // STATE 8
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC injection_panne */
;
		;
		
	case 11: // STATE 2
		;
		now.panne = trpt->bup.oval;
		;
		goto R999;

	case 12: // STATE 4
		;
		now.panne = trpt->bup.oval;
		;
		goto R999;

	case 13: // STATE 8
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC check_light */

	case 14: // STATE 1
		;
		XX = 1;
		unrecv(now.ch, XX-1, 0, ((P1 *)this)->e1, 1);
		unrecv(now.ch, XX-1, 1, ((P1 *)this)->e2, 0);
		((P1 *)this)->e1 = trpt->bup.ovals[0];
		((P1 *)this)->e2 = trpt->bup.ovals[1];
		;
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;
;
		
	case 15: // STATE 2
		goto R999;
;
		;
		;
		
	case 17: // STATE 6
		goto R999;
;
		;
		;
		
	case 19: // STATE 10
		goto R999;
;
		;
		
	case 21: // STATE 17
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC feu */
;
		;
		
	case 23: // STATE 2
		;
		((P0 *)this)->etat_avant = trpt->bup.oval;
		;
		goto R999;

	case 24: // STATE 3
		;
		now.mode = trpt->bup.oval;
		;
		goto R999;

	case 25: // STATE 4
		;
		now.light = trpt->bup.oval;
		;
		goto R999;

	case 26: // STATE 5
		;
		now.clignotant = trpt->bup.oval;
		;
		goto R999;

	case 27: // STATE 6
		;
		((P0 *)this)->etat_apres = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 29: // STATE 9
		;
		_m = unsend(now.ch);
		;
		goto R999;
;
		;
		;
		;
		
	case 32: // STATE 12
		;
		((P0 *)this)->etat_avant = trpt->bup.oval;
		;
		goto R999;

	case 33: // STATE 13
		;
		now.light = trpt->bup.oval;
		;
		goto R999;

	case 34: // STATE 14
		;
		((P0 *)this)->etat_apres = trpt->bup.oval;
		;
		goto R999;

	case 35: // STATE 15
		;
		_m = unsend(now.ch);
		;
		goto R999;
;
		;
		
	case 37: // STATE 17
		;
		((P0 *)this)->etat_avant = trpt->bup.oval;
		;
		goto R999;

	case 38: // STATE 18
		;
		now.light = trpt->bup.oval;
		;
		goto R999;

	case 39: // STATE 19
		;
		((P0 *)this)->etat_apres = trpt->bup.oval;
		;
		goto R999;

	case 40: // STATE 20
		;
		_m = unsend(now.ch);
		;
		goto R999;
;
		;
		
	case 42: // STATE 22
		;
		((P0 *)this)->etat_avant = trpt->bup.oval;
		;
		goto R999;

	case 43: // STATE 23
		;
		now.light = trpt->bup.oval;
		;
		goto R999;

	case 44: // STATE 24
		;
		((P0 *)this)->etat_apres = trpt->bup.oval;
		;
		goto R999;

	case 45: // STATE 25
		;
		_m = unsend(now.ch);
		;
		goto R999;
;
		;
		
	case 47: // STATE 29
		;
		now.light = trpt->bup.oval;
		;
		goto R999;

	case 48: // STATE 30
		;
		now.clignotant = trpt->bup.oval;
		;
		goto R999;

	case 49: // STATE 31
		;
		((P0 *)this)->etat_apres = trpt->bup.oval;
		;
		goto R999;

	case 50: // STATE 32
		;
		_m = unsend(now.ch);
		;
		goto R999;

	case 51: // STATE 37
		;
		p_restor(II);
		;
		;
		goto R999;
	}

