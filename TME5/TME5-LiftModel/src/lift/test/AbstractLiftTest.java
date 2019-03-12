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
	public void testBeginMoveUp1(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();

		//Op
		lift.beginMoveUp();

		//Or Pas d'exception
	}

	@Test(expected = PreconditionError.class)
	public void testBeginMoveUp2(){
		//CI
		lift.init(2, 5);
		lift.closeDoor();
		lift.doorAck();
		
		//Op
		lift.beginMoveUp();

		//Or Exception PreconditionError
	}

	@Test
	public void testStepMoveUp1(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();

		//Op
		lift.stepMoveUp();

		//Or Pas d'exception
	}
	@Test(expected = PreconditionError.class)
	public void testStepMoveUp2(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();

		//Op
		lift.stepMoveUp();

		//Or Exception PreconditionError
	}
	@Test
	public void testEndMoveUp1(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();

		//Op
		lift.endMoveUp();

		//Or Pas d'exception
	}
	@Test(expected = PreconditionError.class)
	public void testEndMoveUp2(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(4);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();

		//Op
		lift.endMoveUp();

		//Or Exception PreconditionError
	}
	@Test
	public void testBeginMoveDown1(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.selectLevel(2);
		lift.closeDoor();
		lift.doorAck();

		//Op
		lift.beginMoveDown();

		//Or Pas d'exception
	}
	@Test(expected = PreconditionError.class)
	public void testBeginMoveDown2(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.selectLevel(2);
		lift.closeDoor();

		//Op
		lift.beginMoveDown();

		//Or Exception PreconditionError
	}
	@Test
	public void testStepMoveDown1(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.selectLevel(2);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveDown();

		//Op
		lift.stepMoveDown();

		//Or Pas d'exception
	}
	@Test(expected = PreconditionError.class)
	public void testStepMoveDown2(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.selectLevel(2);
		lift.closeDoor();
		lift.doorAck();

		//Op
		lift.stepMoveDown();

		//Or Exception PreconditionError
	}
	@Test
	public void testEndMoveDown1(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.selectLevel(2);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveDown();
		lift.stepMoveDown();
		
		//Op
		lift.endMoveDown();
		
		//Or Pas d'exception
	}
	@Test(expected = PreconditionError.class)
	public void testEndMoveDown2(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(4);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.selectLevel(2);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveDown();
		lift.stepMoveDown();

		//Op
		lift.endMoveDown();

		//Or Exception PreconditionError
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
	public void testPaireTransitionInit_CloseDoor(){
		//CI Pas de Ci
		
		//Op
		lift.init(2, 5);
		lift.closeDoor();
		
		//Or : Pas d'exception dans les contrats

	}

	@Test
	public void testPaireTransitionInit_SelectLevel(){
		//CI Pas de CI
		
		//Op
		lift.init(2, 5);
		lift.selectLevel(3);

		//Or : Pas d'exception dans les contrats

	}

	@Test
	public void testPaireTransitionSelectLevel_CloseDoor(){
		//CI
		lift.init(2, 5);
		
		//Op
		lift.selectLevel(5);
		lift.closeDoor();
		
		//Or : Pas d'exception dans les contrats

	}


	@Test
	public void testPaireTransitionOpenDoor_DoorAck(){
		//CI
		lift.init(2, 5);
		lift.closeDoor();
		lift.doorAck();
		
		//Op
		lift.openDoor();
		lift.doorAck();
		
		//Or : Pas d'exception dans les contrats

	}
	
	@Test
	public void testPaireTransitionDoorAck_OpenDoor(){
		//CI
		lift.init(2, 5);
		lift.closeDoor();

		//Op
		lift.doorAck();
		lift.openDoor();
		
		//Or : Pas d'exception dans les contrats

	}

	@Test
	public void testPaireTransitionBeginMoveUp_StepMoveUp(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(5);
		lift.closeDoor();
		lift.doorAck();
		
		//Op
		lift.beginMoveUp();
		lift.stepMoveUp();
		
		//Or : Pas d'exception dans les contrats

	}

	@Test 
	public void testPaireTransitionBeginMoveDown_StepMoveDown(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.selectLevel(2);
		lift.closeDoor();
		lift.doorAck();

		//Op
		lift.beginMoveDown();
		lift.stepMoveDown();
		
		//Or : Pas d'exception dans les contrats

	}

	@Test
	public void testPaireTransitionStepMoveUp_EndMoveUp(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		//Op
		lift.stepMoveUp();
		lift.endMoveUp();

		//Or : Pas d'exception dans les contrats

	}

	@Test
	public void testPaireTransitionStepMoveDown_EndMoveDown(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.selectLevel(2);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveDown();

		//Op
		lift.stepMoveDown();
		lift.endMoveDown();

		//Or : Pas d'exception dans les contrats

	}

	@Test
	public void testPaireTransitionEndMoveUp_OpenDoor(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();

		//Op
		lift.endMoveUp();
		lift.openDoor();
		
		//Or : Pas d'exception dans les contrats

	}

	@Test
	public void testPaireTransitionEndMoveDown_OpenDoor(){
		//CI
		lift.init(2, 5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.selectLevel(2);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveDown();
		lift.stepMoveDown();

		//Op
		lift.endMoveDown();
		lift.openDoor();

		//Or : Pas d'exception dans les contrats

	}

	
	@Test
	public void testScenario1() {
		//Pas de CI
		
		lift.init(2, 5);
		lift.selectLevel(5);
		lift.selectLevel(3);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor(); //Floor 3
		lift.doorAck();
		// lift.selectLevel(2);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck(); //Floor 5
		lift.selectLevel(2);
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveDown();
		lift.stepMoveDown();
		lift.stepMoveDown();
		lift.stepMoveDown();
		lift.endMoveDown();
		lift.openDoor(); //Floor 2
		lift.doorAck();
		
		
		//Or Pas d'exception
	}
	
}
