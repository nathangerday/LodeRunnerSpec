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

	case 5: // STATE 4
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC proc2 */
;
		;
		
	case 7: // STATE 2
		;
		now.ask1 = trpt->bup.oval;
		;
		goto R999;
;
		;
		;
		;
		
	case 10: // STATE 5
		;
		now.ask1 = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 12: // STATE 7
		;
		now.ask1 = trpt->bup.oval;
		;
		goto R999;

	case 13: // STATE 16
		;
		now.cs_counter = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 15: // STATE 18
		;
		now.cs_counter = trpt->bup.oval;
		;
		goto R999;

	case 16: // STATE 19
		;
		now.turn = trpt->bup.oval;
		;
		goto R999;

	case 17: // STATE 20
		;
		now.ask1 = trpt->bup.oval;
		;
		goto R999;

	case 18: // STATE 21
		;
		((P1 *)_this)->count = trpt->bup.oval;
		;
		goto R999;

	case 19: // STATE 27
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC proc1 */
;
		;
		
	case 21: // STATE 2
		;
		now.ask0 = trpt->bup.oval;
		;
		goto R999;
;
		;
		;
		;
		
	case 24: // STATE 5
		;
		now.ask0 = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 26: // STATE 7
		;
		now.ask0 = trpt->bup.oval;
		;
		goto R999;

	case 27: // STATE 16
		;
		now.cs_counter = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 29: // STATE 18
		;
		now.cs_counter = trpt->bup.oval;
		;
		goto R999;

	case 30: // STATE 19
		;
		now.turn = trpt->bup.oval;
		;
		goto R999;

	case 31: // STATE 20
		;
		now.ask0 = trpt->bup.oval;
		;
		goto R999;

	case 32: // STATE 21
		;
		((P0 *)_this)->count = trpt->bup.oval;
		;
		goto R999;

	case 33: // STATE 27
		;
		p_restor(II);
		;
		;
		goto R999;
	}

