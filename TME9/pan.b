	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC loby */

	case 3: // STATE 2
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 4: // STATE 9
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 5: // STATE 16
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 6: // STATE 23
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 7: // STATE 26
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 8: // STATE 29
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 9: // STATE 36
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 10: // STATE 39
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 11: // STATE 46
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 12: // STATE 49
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 13: // STATE 52
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 14: // STATE 59
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 15: // STATE 62
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 16: // STATE 65
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 17: // STATE 72
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 18: // STATE 75
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 19: // STATE 82
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 20: // STATE 85
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 21: // STATE 92
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 22: // STATE 95
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 23: // STATE 102
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 24: // STATE 105
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 25: // STATE 112
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 26: // STATE 115
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 27: // STATE 122
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 28: // STATE 125
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 29: // STATE 132
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 30: // STATE 139
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 31: // STATE 142
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 32: // STATE 149
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 33: // STATE 152
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 34: // STATE 159
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 35: // STATE 162
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 36: // STATE 169
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 37: // STATE 176
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 38: // STATE 179
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 39: // STATE 182
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 40: // STATE 189
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 41: // STATE 192
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 42: // STATE 199
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 43: // STATE 202
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 44: // STATE 209
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 45: // STATE 216
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 46: // STATE 219
		;
		_m = unsend(now.go);
		;
		goto R999;

	case 47: // STATE 225
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC observateur */

	case 48: // STATE 1
		;
		XX = 1;
		unrecv(now.go, XX-1, 0, ((P0 *)_this)->dir, 1);
		((P0 *)_this)->dir = trpt->bup.oval;
		;
		;
		goto R999;

	case 49: // STATE 2
		;
	/* 0 */	((P0 *)_this)->dir = trpt->bup.oval;
		;
		;
		goto R999;
;
		;
		;
		;
		
	case 52: // STATE 13
		;
		p_restor(II);
		;
		;
		goto R999;
	}

