package bridge.contracts;

import bridge.services.BridgeService;

public class BridgeContract extends LimitedRoadContract implements BridgeService {

	public BridgeContract(BridgeService delegate) {
		super(delegate);
	}

	@Override
	protected BridgeService getDelegate() {
		return (BridgeService) super.getDelegate();
	}
	
	@Override
	public int getNbIn() {
		return getDelegate().getNbIn();
	}

	@Override
	public int getNbOut() {
		return getDelegate().getNbOut();
	}
	
	public void checkInvariant() {
		//inv : getNbCars() == ( getNbIn() + getNbOut()
		if(! (getNbCars() == (getNbIn() + getNbOut()))) {
			Contractor.defaultContractor().invariantError("BridgeContract","getNbCars() == ( getNbIn() + getNbOut()");
		}
		
		//inv getNbIn() >= 0
		if(!(getNbIn() >= 0)) {
			Contractor.defaultContractor().invariantError("BridgeContract","Le nombre de voiture sur in est negatif");
		}
		
		
		//inv getNbOut() >= 0
		if(! (getNbOut() >= 0)) {
			Contractor.defaultContractor().invariantError("BridgeContract","Le nombre de voiture sur out est negatif");
		}
		
		
		// raffinement donc
		super.checkInvariant();
	}

	@Override
	public void init(int lim) {
		//pre lim > 0
		if(! (lim > 0)) {
			Contractor.defaultContractor().preconditionError("BridgeContract", "init", "The limit is not greater than 0");
		}
		
		getDelegate().init(lim);
		
		checkInvariant();
		
		//post  getNbIn() == 0
		if(! (getNbIn() == 0)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "init", "Nombre de voiture sur in different de 0");
		}
		
		//post getNbOut() == 0
		if(! (getNbOut() == 0)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "init", "Nombre de voiture sur out different de 0");
		}
		
		//post getLimit() == lim
		if(! (getLimit() == lim)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "init", "Limite mal initialise");
		}
		
		
	}

	@Override
	public void enterIn() {
		//pre ! isFull()
		if(isFull()) {
			Contractor.defaultContractor().preconditionError("BridgeContract", "enterIn", "Route in pleine");
		}
		
		//capture
		int getLimit_atPre = getLimit();
		int getNbIn_atPre = getNbIn();
		int getNbOut_atPre = getNbOut();
		
		
		//inv pre
		checkInvariant();
		
		getDelegate().enterIn();
		
		//inv post
		checkInvariant();
		
		//post getNbIn() == getNbIn_atPre + 1
		if(! (getNbIn() == getNbIn_atPre + 1)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "enterIn", "getNbIn() == getNbIn_atPre + 1");
		}
		
		//post getNbOut() == getNbOut_atPre
		if(! (getNbOut() == getNbOut_atPre)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "enterIn", "getNbOut() == getNbOut_atPre");
		}
		
		//post getLimit() == getLimit_atPre
		if(! (getLimit() == getLimit_atPre)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "enterIn", "getLimit() == getLimit_atPre");
		}
	}

	@Override
	public void leaveIn() {
		//pre getNbIn() > 0
		if(! (getNbIn() > 0)) {
			Contractor.defaultContractor().preconditionError("BridgeContract", "leaveIn", "getNbIn() > 0");
		}
		
		//capture
		int getLimit_atPre = getLimit();
		int getNbIn_atPre = getNbIn();
		int getNbOut_atPre = getNbOut();
				
		
		//inv pre
		checkInvariant();
		
		getDelegate().leaveIn();
		
		//inv post
		checkInvariant();
		
		//post getNbIn() == getNbIn_atPre - 1
		if(! (getNbIn() == getNbIn_atPre -1)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "leaveIn", "getNbIn() == getNbIn_atPre - 1");
		}
		
		//post getNbOut() == getNbOut_atpre
		if(! (getNbOut() == getNbOut_atPre)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "leaveIn", "getNbOut() == getNbOut_atpre");
		}
		
		//post getLimit() == getLimit_atPre
		if(! (getLimit_atPre == getLimit())) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "leaveIn", "getLimit() == getLimit_atPre");
		}
		
	}

	@Override
	public void enterOut() {
		//pre ! isFull()
		if(isFull()) {
			Contractor.defaultContractor().preconditionError("BridgeContract", "enterOut", "Route in pleine");
		}
		
		//capture
		int getLimit_atPre = getLimit();
		int getNbIn_atPre = getNbIn();
		int getNbOut_atPre = getNbOut();
		
		
		//inv pre
		checkInvariant();
		
		getDelegate().enterOut();
		
		//inv post
		checkInvariant();
		
		//post getNbIn() == getNbIn_atPre 
		if(! (getNbIn() == getNbIn_atPre)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "enterOut", "getNbIn() == getNbIn_atPre");
		}
		
		//post getNbOut() == getNbOut_atPre + 1
		if(! (getNbOut() == getNbOut_atPre + 1)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "enterOut", "getNbOut() == getNbOut_atPre + 1");
		}
		
		//post getLimit() == getLimit_atPre
		if(! (getLimit() == getLimit_atPre)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "enterOut", "getLimit() == getLimit_atPre");
		}

	}

	@Override
	public void leaveOut() {
		//pre getNbIn() > 0
		if(! (getNbIn() > 0)) {
			Contractor.defaultContractor().preconditionError("BridgeContract", "leaveOut", "getNbIn() > 0");
		}
		
		//capture
		int getLimit_atPre = getLimit();
		int getNbIn_atPre = getNbIn();
		int getNbOut_atPre = getNbOut();
				
		
		//inv pre
		checkInvariant();
		
		getDelegate().leaveOut();
		
		//inv post
		checkInvariant();
		
		//post getNbIn() == getNbIn_atPre
		if(! (getNbIn() == getNbIn_atPre)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "leaveOut", "getNbIn() == getNbIn_atPre");
		}
		
		//post getNbOut() == getNbOut_atpre - 1
		if(! (getNbOut() == getNbOut_atPre - 1)) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "leaveOut", "getNbOut() == getNbOut_atpre - 1");
		}
		
		//post getLimit() == getLimit_atPre
		if(! (getLimit_atPre == getLimit())) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "leaveOut", "getLimit() == getLimit_atPre");
		}
		
	}
	
	
	@Override
	public void enter() {
		//capture
		int getNbIn_atPre = getNbIn();
		int getNbOut_atPre = getNbOut();
		
		checkInvariant();
		
		//raffinement
		super.enter();
		
		checkInvariant();
		
		//post (getNbIn() > getNbOut()) => (getNbIn() == getNbIn_atPre ) && (getNbOut() == getnbOut_atPre + 1)
		if(! (!(getNbIn_atPre > getNbOut_atPre) || ((getNbIn() == getNbIn_atPre) && (getNbOut() == getNbOut_atPre + 1)))) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "enter", "(getNbIn() > getNbOut()) \\impl (getNbIn() == getNbIn_atPre ) && (getNbOut() == getnbOut_atPre + 1)");
		}
		
		//post (getNbIn() <= getNbOut()) => (getNbIn() == getNbIn_atPre +1 ) && (getNbOut() == getnbOut_atPre)
		if(! (!(getNbIn_atPre <= getNbOut_atPre) || ((getNbIn() == getNbIn_atPre+1) && (getNbOut() == getNbOut_atPre)))) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "enter","(getNbIn() <= getNbOut()) \\impl (getNbIn() == getNbIn_atPre +1 ) && (getNbOut() == getnbOut_atPre)");
		}
		
	}
	
	@Override
	public void leave() {
		//capture
		int getNbIn_atPre = getNbIn();
		int getNbOut_atPre = getNbOut();
				
		
		//raffinement
		super.leave();
		
		//post (getNbIn() > getNbOut()) \imp (getNbIn() == getNbIn_atPre - 1) && (getNbOut() == getnbOut_atPre)
		if(! (!(getNbIn_atPre > getNbOut_atPre) || ((getNbIn() == getNbIn_atPre) && (getNbOut() == getNbOut_atPre + 1)))) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "leave", "(getNbIn() > getNbOut()) \\impl (getNbIn() == getNbIn_atPre - 1) && (getNbOut() == getnbOut_atPre)");
		}
		
		//post (getNbIn() <= getNbOut()) \imp (getNbIn() == getNbIn_atPre) && (getNbOut() == getnbOut_atPre - 1)
		if(! (!(getNbIn_atPre <= getNbOut_atPre) || ((getNbIn() == getNbIn_atPre+1) && (getNbOut() == getNbOut_atPre)))) {
			Contractor.defaultContractor().postconditionError("BridgeContract", "leave","(getNbIn() <= getNbOut()) \\impl (getNbIn() == getNbIn_atPre) && (getNbOut() == getnbOut_atPre - 1)");
		}
		
	}
	
}
