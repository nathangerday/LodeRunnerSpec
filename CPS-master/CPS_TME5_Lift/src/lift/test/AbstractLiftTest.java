package lift.test;

import lift.contracts.InvariantError;
import lift.contracts.PostconditionError;
import lift.contracts.PreconditionError;
import lift.services.CommandsService;
import lift.services.DoorStatus;
import lift.services.LiftService;
import lift.services.LiftStatus;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testInitPre1() {
		// cas positif
		try{
			lift.init(1, 3);
		}catch(PostconditionError p){
		}

	}

	@Test
	public void testInitPre2() {
		// pre: minLevel >= 0
		thrown.expect(PreconditionError.class);
		lift.init(-1,1);
	}

	@Test
	public void testInitPre3() {
		// pre: minLevel < maxLevel
		thrown.expect(PreconditionError.class);
		lift.init(5, 3);
	}

	@Test
	public void testInitPre4() {
		thrown.expect(PreconditionError.class);
		lift.init(-8, -5);		
	}

	@Test
	public void testOpenDoorPre1() {
		// cas negatif
		thrown.expect(PreconditionError.class);
		try {
			// condition initiale
			lift.init(0, 1);
		} catch (PostconditionError pce ) {}
		try {
			// pre: getDoorStatus() == CLOSED
			lift.openDoor();
		} catch (InvariantError ie) {}
	}

	@Test
	public void testOpenDoorPre2() {
		// cas positif
		lift.init(1, 3);
		lift.closeDoor();
		lift.doorAck();
		lift.openDoor();
	}
	
	@Test
	public void testCloseDoorPre1() {
		lift.init(1, 3);
		lift.closeDoor();
		lift.doorAck();
	}
	
	@Test
	public void testCloseDoorPre2() {
		lift.init(1, 3);
		lift.closeDoor();
		lift.doorAck();
		lift.closeDoor();
		lift.doorAck();
	}
	
	@Test
	public void testBeginMoveUpTrans1() {
		lift.init(1,3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		assertTrue(lift.getLiftStatus() == LiftStatus.MOVING_UP);
		assertTrue(lift.getMinLevel() <= lift.getLevel() && lift.getLevel() <= lift.getMaxLevel());
		assertTrue(lift.getDoorStatus() == DoorStatus.CLOSED);
	}
	
	@Test
	public void testCloseDoorTrans1() {
		lift.init(0, 4);
		lift.closeDoor();
		assertTrue(lift.getDoorStatus() == DoorStatus.CLOSING);
		assertTrue(lift.getMinLevel() <= lift.getLevel() && lift.getLevel() <= lift.getMaxLevel());
		
		assertTrue(lift.getLiftStatus() != LiftStatus.MOVING_UP && 
				lift.getLiftStatus() != LiftStatus.MOVING_DOWN && 
				lift.getLiftStatus() != LiftStatus.IDLE);
		
	}
	
	@Test
	public void testScenario() {
		lift.init(0, 3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveDown();
		lift.endMoveDown();
		lift.openDoor();
		lift.doorAck();
		lift.doorAck();
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveDown();
		lift.endMoveDown();
		lift.openDoor();
		lift.doorAck();
	}

}
