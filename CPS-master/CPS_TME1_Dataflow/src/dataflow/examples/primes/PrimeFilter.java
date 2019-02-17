package dataflow.examples.primes;

import java.math.BigInteger;

import dataflow.core.IntEvent;
import dataflow.core.IntEventReceiverService;
import dataflow.core.Printer;
import dataflow.generators.SimpleGenerator;

/*
 * 
 * Exercice 3 -- Question 5
 * 
 * A compléter
 * 
 */

public class PrimeFilter extends SimpleGenerator 
		implements /* provide */
			       IntEventReceiverService {

	private PrimeFilter next;
	private BigInteger n, k;
	
	
	public PrimeFilter() {
		next = null;
	}
	
	@Override
	public void onIntEvent(IntEvent event) {
		if (next == null ) {
			k = event.getValue();
		}
		else {
			n = event.getValue();
		}
	}

	@Override
	public void activate() {
		if (next == null) {
			next = new PrimeFilter();
			send(new IntEvent(new BigInteger(k.toString())));
			next.bindIntEventReceiverService(receivers.remove(0));
			this.bindIntEventReceiverService(next);
			send(new IntEvent(new BigInteger(k.toString())));
		}
		else {
			/* deuxieme cas */
			if (n.intValue()%k.intValue() != 0) {
				send(new IntEvent(n));
				next.activate();
			}
		}
	}
	
}
