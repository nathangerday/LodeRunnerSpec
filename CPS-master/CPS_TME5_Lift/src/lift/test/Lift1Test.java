package lift.test;

import lift.contracts.LiftContract;
import liftimpl1.Lift1;


public class Lift1Test extends AbstractLiftTest {

	@Override
	public void beforeTests() {
		setLift(new LiftContract(new Lift1()));
	}
	
	

}
