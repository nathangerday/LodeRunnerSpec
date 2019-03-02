package bridge.contracts;

import bridge.decorators.LimitedRoadDecorator;
import bridge.services.LimitedRoadService;

public class LimitedRoadContract extends LimitedRoadDecorator {

	public LimitedRoadContract(LimitedRoadService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		// remarque : include et non refine donc on n'hérite
		// pas des invariants de RoadSectionService, il faut refaire des tests.
				
		// inv: getNbCars() >= 0
		if(!(getNbCars() >= 0)) {
			Contractor.defaultContractor().invariantError("LimitedRoadService","The number of cars should be positive");
		}
		
		// inv: isFull() == (getNbCars() == getLimit())
		if(! ( isFull() == (getNbCars() == getLimit()))){
			Contractor.defaultContractor().invariantError("LimitedRoadService", "isFull() == (getNbCars() == getLimit())");
		}
		
		//inv : getNbCars() <= getLimit()
		if(! (getNbCars() <= getLimit())) {
			Contractor.defaultContractor().invariantError("LimitedRoadService", "getNbCars() <= getLimit()");
		}
	}
	
	/* A COMPLETER */
	

	@Override
	public void enter() {
		//pre ! (isFull())
		if(isFull()) {
			Contractor.defaultContractor().preconditionError("LimitedRoadService", "enter", "Le pont est rempli");
		}
		
		// captures
		int getNbCars_atPre = getNbCars();
		int getLimit_atPre = getLimit();
		
		// inv pre
		checkInvariant();
		// run
		super.enter();
		// int post
		checkInvariant();
		// post: getNbCars() == getNbCars()@pre + 1 
		if(!(getNbCars() == getNbCars_atPre + 1)) {
			Contractor.defaultContractor().postconditionError("RoadSectionService", "enter", "The cars count did not increase");
		}
		//post getLimit() == getLimit()@pre
		if(! (getLimit_atPre == getLimit())) {
			Contractor.defaultContractor().postconditionError("RoadSectionService", "enter", "Limit changed");
		}
	}
	
	@Override
	public void leave() {
		// pre: getNbCars() > 0
		if(!(getNbCars() > 0)) {
			Contractor.defaultContractor().preconditionError("RoadSectionService", "leave", "The number of cars is not strictly positive");
		}
		// captures
		int getNbCars_atPre = getNbCars();
		int getLimit_atPre = getLimit();
		// inv pre
		checkInvariant();
		// run
		super.leave();
		// int post
		checkInvariant();
		// post: getNbCars() == getNbCars()@pre - 1 
		if(!(getNbCars() == getNbCars_atPre - 1)) {
			Contractor.defaultContractor().postconditionError("RoadSectionService", "leave", "The cars count did not decrease");
		}
		//post getLimit() == getLimit()@pre
		if(! (getLimit_atPre == getLimit())) {
			Contractor.defaultContractor().postconditionError("RoadSectionService", "leave", "Limit changed");
		}
	}
	
	@Override
	public void init(int limit) {
		// pre: limit > 0
		if(! (limit > 0)) {
			Contractor.defaultContractor().preconditionError("RoadSectionService", "init", "The limit is not greater than 0");
		}
		
		super.init(limit);
		
		checkInvariant();
		
		//post getNbCars == 0
		if(! (getNbCars() == 0)) {
			Contractor.defaultContractor().postconditionError("RoadSectionService", "init", "The number of cars is not 0");
		}
		
		//post getLimit() == limit
		if(! (getLimit() == limit)) {
			Contractor.defaultContractor().postconditionError("RoadSectionService", "init", "getLimit() == limit");
		}
		
		
		
	}
	
	
	
}
