package lift.test;

import lift.contracts.CommandsContract;
import lift.contracts.LiftContract;
import lift.impl.CommandsImpl;
import lift.services.LiftService;
import liftimpl1.Lift1;

public class Lift1Test extends AbstractLiftTest{

	@Override
	public void beforeTests() {
		LiftService l = new Lift1();
		setLift(new LiftContract(l));
	}

}
