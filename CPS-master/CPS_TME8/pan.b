	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC :init: */

	case 3: // STATE 1
		;
		now.myFeu.couleur = trpt->bup.oval;
		;
		goto R999;

	case 4: // STATE 2
		;
		now.myFeu.etat = trpt->bup.oval;
		;
		goto R999;

	case 5: // STATE 3
		;
		now.myFeu.mode = trpt->bup.oval;
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
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC exec */

	case 8: // STATE 1
		;
		now.myFeu.couleur = trpt->bup.oval;
		;
		goto R999;

	case 9: // STATE 2
		;
		now.myFeu.etat = trpt->bup.oval;
		;
		goto R999;

	case 10: // STATE 3
		;
		now.myFeu.mode = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 12: // STATE 5
		;
		now.myFeu.couleur = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 14: // STATE 8
		;
		now.myFeu.couleur = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 16: // STATE 11
		;
		now.myFeu.couleur = trpt->bup.oval;
		;
		goto R999;

	case 17: // STATE 14
		;
		now.myFeu.etat = trpt->bup.oval;
		;
		goto R999;

	case 18: // STATE 15
		;
		now.myFeu.couleur = trpt->bup.oval;
		;
		goto R999;

	case 19: // STATE 16
		;
		now.myFeu.mode = trpt->bup.oval;
		;
		goto R999;

	case 20: // STATE 19
		;
		p_restor(II);
		;
		;
		goto R999;
	}

