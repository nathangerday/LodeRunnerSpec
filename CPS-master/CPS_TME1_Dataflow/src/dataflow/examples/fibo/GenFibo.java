package dataflow.examples.fibo;

import java.math.BigInteger;

import dataflow.core.Composite;
import dataflow.core.IntEvent;
import dataflow.core.IntEventReceiverService;
import dataflow.core.Relay;
import dataflow.core.RequireIntEventReceiverService;
import dataflow.operators.Add;

/*
 * 
 * Exercice 2 -- Question 4
 * 
 * A compléter  
 * 
 */


public class GenFibo implements Composite,
			/* require */
			RequireIntEventReceiverService {

	private Add add;
	private Relay r1;
	private Relay r2;
	
	public GenFibo() {
		add = new Add();
		r1 = new Relay();
		r2 = new Relay();
		/* attention ordre du bind */
		add.bindIntEventReceiverService(add);
		r1.bindIntEventReceiverService(r2);
		add.bindIntEventReceiverService(r1);
		r2.bindIntEventReceiverService(add);
		add.onIntEvent(new IntEvent(BigInteger.ONE));
		r1.onIntEvent(new IntEvent(BigInteger.ZERO));
		r2.onIntEvent(new IntEvent(BigInteger.ZERO));
	}	
	
	@Override
	public void bindIntEventReceiverService(IntEventReceiverService serv) {
		add.bindIntEventReceiverService(serv);
	}
		
	@Override
	public void activate() {
		r2.activate();
		r1.activate();
		add.activate();	
	}

}
