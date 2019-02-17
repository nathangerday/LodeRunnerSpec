package dataflow.examples.fact;

import java.math.BigInteger;

import dataflow.core.Composite;
import dataflow.core.IntEvent;
import dataflow.core.IntEventReceiverService;
import dataflow.core.RequireIntEventReceiverService;
import dataflow.examples.basics.GenInt;
import dataflow.generators.GenConst;
import dataflow.operators.Mul;


/*
 * 
 * Exercice 2 -- Question 3
 * 
 * A compléter  
 * 
 */

public class GenFact implements Composite,
			/* require */
			RequireIntEventReceiverService {

	//TODO 
	private GenInt gen;
	private Mul mul;
	
	public GenFact() {
		// TODO
		gen = new GenInt(1);
		mul = new Mul();
		gen.bindIntEventReceiverService(mul);
		mul.bindIntEventReceiverService(mul);
		mul.onIntEvent(new IntEvent(BigInteger.ONE));
	}	
	
	@Override
	public void bindIntEventReceiverService(IntEventReceiverService serv) {
		mul.bindIntEventReceiverService(serv);
	}
		
	@Override
	public void activate() {
		gen.activate();
		mul.activate();
	}

}
