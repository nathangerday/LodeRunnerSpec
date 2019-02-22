
package maleva.agent;

import java.util.Random;

import maleva.framework.LifeCycleException;

public class RandomMouvement extends ComportementAgent {
	
	/* fields */
	private Random rand;
	
	/* properties */
	private int angle_step = 20;
	
	public RandomMouvement(Agent outer) {
		super(outer);
		rand = new Random();
	}
	
	/* LCStepperController */
	
	public boolean step() throws LifeCycleException {

		int curAngle = outer.getAngle();
		curAngle = curAngle + (rand.nextInt(angle_step*2) - 20 );
		action = new Action(Action.MOVE, curAngle);

		return true;
	}
}
