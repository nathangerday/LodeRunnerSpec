
package maleva.agent;

import java.util.Random;

import maleva.framework.LifeCycleException;

public class RandomMouvement extends ComportementAgent {
	
	/* fields */
	private Random rand;
	
	/* properties */
	private int angle_step = 10;
	
	public RandomMouvement(Agent outer) {
		super(outer);
		rand = new Random();
	}
	
	/* LCStepperController */
	
	public boolean step() throws LifeCycleException {
		
		int oldAngle = outer.getAngle();
		int alea = rand.nextInt(angle_step);
		if (rand.nextDouble() > 0.5)
			outer.setAngle(oldAngle + alea);
		else
			outer.setAngle(oldAngle - alea);
		action = new Action(Action.MOVE,outer.getAngle());
		return true;
	}
}
