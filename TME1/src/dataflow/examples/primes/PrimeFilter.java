package dataflow.examples.primes;

import java.math.BigInteger;

import dataflow.core.IntEvent;
import dataflow.core.IntEventReceiverService;
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
	private BigInteger val;
	private IntEvent event;
		
		
	public PrimeFilter() {
		next = null;
	}
	
	@Override
	public void onIntEvent(IntEvent event) {
		this.event = event;
	}

	@Override
	public void activate() {
		if(next == null) {
		this.val = event.getValue();
		this.next = new PrimeFilter();
		for(IntEventReceiverService r : this.receivers) {
			this.next.bindIntEventReceiverService(r);
			r.onIntEvent(new IntEvent(val));
		}
		this.receivers.clear();
		
		}else {
			if(!event.getValue().mod(val).equals(new BigInteger("0"))) {
				next.onIntEvent(event);
				next.activate();
			}
		}
	}
}
