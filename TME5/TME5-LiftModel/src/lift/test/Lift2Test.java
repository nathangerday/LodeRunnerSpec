package lift.test;

import lift.contracts.LiftContract;
import lift.services.LiftService;
import liftimpl2.Lift2;

public class Lift2Test extends AbstractLiftTest{

	@Override
	public void beforeTests() {
		LiftService l = new Lift2();
		setLift(new LiftContract(l));
	}

}
