package lift.test;

import lift.contracts.PreconditionError;
import lift.services.CommandsService;
import lift.services.LiftService;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public abstract class AbstractLiftTest {
	private LiftService lift;
	private CommandsService commands;
	
	protected AbstractLiftTest() {
		lift = null;
		commands = null;
	}
	
	protected final LiftService getLift() {
		return lift;
	}
	
	protected final CommandsService getCommands() {
		return commands;
	}
	
	protected final void setLift(LiftService lift) {
		this.lift = lift;
	}
	
	protected final void setCommands(CommandsService commands) {
		this.commands = commands;
	}
	
	@Before
	public abstract void beforeTests(); 

	@After
	public final void afterTests() {
		lift = null;
		commands = null;
	}
	
	
	@Test
	public void testInit1() {
		// Pas de condition initiale
		
		//operations
		lift.init(2, 5);
		
		//oracles : Pas d'exception
	}
	
	@Test(expected = PreconditionError.class)
	public void testInit2() {
		//Pas de CI
		
		//Op
		lift.init(5, 2);
		
		//Or: Exception preconditionError
	}
	
	@Test(expected = PreconditionError.class)
	public void testInit3() {
		//Pas de CI
		
		//Op
		lift.init(-2, 5);
		
		//Or: Exception PreconditionError
	}
	
	@Test
	public void testOpenDoor1() {
		//CI
		lift.init(2, 5);
		lift.closeDoor();
		lift.doorAck();
		
		//Op
		lift.openDoor();
		
		//Or : Pas d'exception
		
	}
	
	@Test(expected = PreconditionError.class)
	public void testOpenDoor2() {
		//CI
		lift.init(2, 5);
		lift.closeDoor();
		
		//Op
		lift.openDoor();
		
		//Or Exception PreconditionError
	}
	
	@Test
	public void testCloseDoor1() {
		//CI
		lift.init(2, 5);
		
		//Op
		lift.closeDoor();
		
		//Or Pas d'exception
	}
	
	@Test(expected = PreconditionError.class)
	public void testCloseDoor2() {
		//CI
		lift.init(2, 5);
		lift.closeDoor();
		lift.doorAck();
		
		//Op
		lift.closeDoor();
		
		//Or : Exception PreconditionError
	}
	
	@Test
	public void testDoorAck1() {
		//CI
		lift.init(2, 5);
		lift.closeDoor();
		
		//Op
		lift.doorAck();
		
		//Or Pas d'exception
	}
	
	@Test(expected = PreconditionError.class)
	public void testDoorAck2() {
		//CI
		lift.init(2, 5);
		
		//Op
		lift.doorAck();
		
		//Or Exception Preconditon Error
	}
	
	@Test
	public void testSelectLevel1() {
		//CI
		lift.init(2, 5);
		
		//Op
		lift.selectLevel(3);
		
		//Or : Pas d'exception
	}
	
	@Test(expected = PreconditionError.class)
	public void testSelectLevel2() {
		//CI
		lift.init(2, 5);
		
		//Op
		lift.selectLevel(8);
		
		//Or : Exception PreconditionError
	}
	
	@Test
	public void testTransitionInit() {
		//Pas de CI
		
		//Op
		lift.init(2, 5);
		
		//Or Pas d'exception de contrats
	}
	
	@Test
	public void testTransitionOpenDoor() {
		//CI
		lift.init(2, 5);
		lift.closeDoor();
		lift.doorAck();
		
		//Op
		lift.openDoor();
		
		// Or Pas d'exception de contrats
	}
	
	
	@Test
	public void testEtatRemarquableEtageMax() {
		//Pas de CI
		
		//Op
		lift.init(2, 5);
		lift.selectLevel(5);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.stepMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		
		//Or Pas d'exception
		assertTrue(lift.getLevel() == lift.getMaxLevel());
	}
	
	@Test
	public void testPaireTransitionCloseDoor_DoorAck() {
		//CI 
		lift.init(2, 5);
		
		//Op
		lift.closeDoor();
		lift.doorAck();
		
		//Or : Pas d'exception dans les contrats
	}
	
	@Test
	public void testPaireTransitionDoorAck_CloseDoor() {
		//CI 
		lift.init(2, 5);
		lift.closeDoor();
		lift.doorAck();
		lift.openDoor();
		
		//Op
		lift.doorAck();
		lift.closeDoor();
		
		//Or : Pas d'exception dans les contrats
	}
	
	@Test
	public void testScenario1() {
		//Pas de CI
		
		//TODO
		
		//Or Pas d'exception
	}
	
}
