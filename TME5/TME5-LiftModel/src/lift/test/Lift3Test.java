package lift.test;

import lift.contracts.LiftContract;
import lift.services.LiftService;
import liftimpl3.Lift3;

public class Lift3Test extends AbstractLiftTest{

	@Override
	public void beforeTests() {
		LiftService l = new Lift3();
		setLift(new LiftContract(l));
	}

}
