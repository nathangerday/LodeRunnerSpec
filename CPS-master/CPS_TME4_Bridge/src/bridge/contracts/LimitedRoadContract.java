package bridge.contracts;

import bridge.decorators.LimitedRoadDecorator;
import bridge.services.LimitedRoadService;

public class LimitedRoadContract extends LimitedRoadDecorator {

	public LimitedRoadContract(LimitedRoadService delegate) {
		super(delegate);
	}
	
	@Override
	public void init(int lim){
		checkInvariant();
		// pre lim > 0
		if(!(lim > 0))
			Contractor.defaultContractor().preconditionError("LimitedRoadService", "init", "The car number limit should be superior to 0");
		super.init(lim);
		// post : getLimit() == lim
		if(!(super.getLimit() == lim))
			Contractor.defaultContractor().postconditionError("LimitedRoadService", "init", "The car number limit is not the same as the one given as argument");
		checkInvariant();
	}
	
	@Override
	public int getLimit(){
		checkInvariant();
		int res = super.getLimit();
		checkInvariant();
		return res;
	}
	
	@Override
	public boolean isFull(){
		checkInvariant();
		boolean res = super.isFull();
		checkInvariant();
		return res;
	}
	
	@Override
	public void enter(){
		checkInvariant();
		// pre !isFull()
		if(super.isFull())
			Contractor.defaultContractor().preconditionError("LimitedRoadService", "enter", "The limited road section is full");
		super.enter();
		checkInvariant();
	}

	public void checkInvariant() {
		// remarque : include et non refine donc on n'hérite
		// pas des invariants de RoadSectionService, il faut refaire des tests.
				
		// inv: getNbCars() >= 0
		if(!(super.getNbCars() >= 0)) {
			Contractor.defaultContractor().invariantError("LimitedRoadService","The number of cars should be positive");
		}
		// inv: isFull() == (getNbCars() == getLimit())
		if(!(super.isFull() == (super.getNbCars() == super.getLimit())))
			Contractor.defaultContractor().invariantError("LimitedRoadService", "The road is not full when the number of cars has reached the limit");
		// inv: getNbCars() <= getLimit()
		if(!(super.getNbCars() <= super.getLimit()))
			Contractor.defaultContractor().invariantError("LimitedRoadService", "The number of cars is higher than the limit");
	}
	
}
