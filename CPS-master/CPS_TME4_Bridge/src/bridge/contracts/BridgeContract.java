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
		checkInvariant();
		int nbIn = getDelegate().getNbIn();
		checkInvariant();
		return nbIn;
	}

	@Override
	public int getNbOut() {
		checkInvariant();
		int nbOut = getDelegate().getNbOut();
		checkInvariant();
		return nbOut;
	}

	public void checkInvariant() {
		// TODO
		if (!(getDelegate().getNbCars() == getDelegate().getNbIn() + getDelegate().getNbOut())) {
			Contractor.defaultContractor().invariantError("BridgeService", "getNbCars() should be equal to getNbIn() + getNbOut()");
		}
		if (!(getDelegate().getNbIn() >= 0)) {
			Contractor.defaultContractor().invariantError("BridgeService", "getNbIn() should be positive");
		}
		if (!(getDelegate().getNbOut() >= 0)) {
			Contractor.defaultContractor().invariantError("BridgeService", "getNbOut() should be positive");
		}
		// raffinement donc
		super.checkInvariant();
	}


	@Override
	public void init() {
		// TODO
		checkInvariant();
		super.init();
		// post : getNbCars() == 0
		if(super.getNbCars() != 0)
			Contractor.defaultContractor().postconditionError("RoadSectionService", "init", "The number of cars should be 0");
		checkInvariant();
	}

	@Override
	public void init(int lim) {
		// TODO REVOIR SI BONNE UTILISATION DE GETNBIN GETNBOUT
		checkInvariant();
		// pre: lim > 0
		if(!(lim > 0))
			Contractor.defaultContractor().preconditionError("BridgeService", "init", "The limit should be superior to 0");
		super.init(lim);
		// post: getNbIn() == 0
		if(!(getNbIn() == 0))
			Contractor.defaultContractor().postconditionError("BridgeService", "init", "nbIn should be equal to 0");
		// post: getNbOut() == 0
		if(!(getNbOut() == 0))
			Contractor.defaultContractor().postconditionError("BridgeService", "init", "nbOut should be equal to 0");
		checkInvariant();
	}

	@Override
	public void enterIn() {
		// TODO
		checkInvariant();
		//  pre: !isFull()
		if(super.isFull())
			Contractor.defaultContractor().preconditionError("BridgeService", "enterIn", "The bridge should not be full");

		int nbInPre = getNbIn();
		int nbOutPre = getNbOut();

		getDelegate().enterIn();
		// post: getNbIn() == getNbIn()@pre + 1
		if(!(getNbIn() == nbInPre+1))
			Contractor.defaultContractor().postconditionError("BridgeService", "enterIn", "nbIn should be equal to nbIn@pre+1");
		// post: getNbOut() == getNbOut()@pre
		if(!(getNbOut() == nbOutPre))
			Contractor.defaultContractor().postconditionError("BridgeService", "enterIn", "nbOut should be equal to nbOut@pre");
		checkInvariant();
	}

	@Override
	public void leaveIn() {
		// TODO
		checkInvariant();
		// pre: getNbIn() > 0
		if(!(getNbIn() > 0)) {
			Contractor.defaultContractor().preconditionError("BridgeService", "leaveIn", "nbIn should be strictly positive");
		}
		else {
			int nbInPre = getNbIn();
			int nbOutPre = getNbOut();

			getDelegate().leaveIn();

			// post: getNbIn() == getNbIn()@pre - 1
			if(!(getNbIn() == nbInPre-1))
				Contractor.defaultContractor().postconditionError("BridgeService", "leaveIn", "nbIn should be equal to nbIn@pre-1");
			// post: getNbOut() == getNbOut()@pre
			if(!(getNbOut() == nbOutPre))
				Contractor.defaultContractor().postconditionError("BridgeService", "leaveIn", "nbOut should be equal to nbOut@pre");

			checkInvariant();
		}
	}

	@Override
	public void enterOut() {
		// TODO
		checkInvariant();
		// pre: !isFull()
		if(!(!super.isFull()))
			Contractor.defaultContractor().preconditionError("BridgeService", "enterOut", "The bridge should not be full");

		int nbOutPre = getNbOut();
		int nbInPre = getNbIn();

		getDelegate().enterOut();

		// post : getNbOut() == getNbOut()@pre +1
		if(!(getNbOut() == nbOutPre+1))
			Contractor.defaultContractor().postconditionError("BridgeService", "enterOut", "nbOut should be equal to nbOut@pre+1");
		// post: getNbIn() == getNbIn()@pre
		if(!(getNbIn() == nbInPre))
			Contractor.defaultContractor().postconditionError("BridgeService", "enterOut", "nbIn should be equal to nbIn@pre");
		checkInvariant();
	}

	@Override
	public void leaveOut() {
		// TODO
		checkInvariant();
		// pre getNbOut() > 0
		if(!(getNbOut() > 0)) {
			Contractor.defaultContractor().preconditionError("BridgeService", "leaveOut", "nbOut should be superior to 0");
		}
		else {
			int nbOutPre = getNbOut();
			int nbInPre = getNbIn();

			getDelegate().leaveOut();

			// post: getNbOut() == getNbOut()@pre - 1
			if(!(getNbOut() == nbOutPre-1))
				Contractor.defaultContractor().postconditionError("BridgeService", "leaveOut", "nbOut should be equal to nbOut@pre-1");
			// post: getNbIn() == getNbIn()@pre
			if(!(getNbIn() == nbInPre))
				Contractor.defaultContractor().postconditionError("BridgeService", "leaveOut", "nbIn should be equal to nbIn@pre");
			checkInvariant();
		}

	}


	@Override
	public void enter(){
		checkInvariant();
		// pre: !isFull()
		if(super.isFull())
			Contractor.defaultContractor().preconditionError("BridgeService", "enter", "The bridge should not be full");

		int nbInPre = getNbIn();
		int nbOutPre = getNbOut();

		super.enter();

		/* post: if getNbIn()@pre > getNbOut()@pre
		 *       then
		 *         getNbIn() == getNbIn()@pre
		 *         getNbOut() == getNbOut()@pre + 1 
		 *       else
		 *         getNbIn() == getNbIn()@pre + 1 
		 *         getNbOut() == getNbOut()@pre */
		if(nbInPre > nbOutPre){
			if(getNbIn() != nbInPre)
				Contractor.defaultContractor().postconditionError("BridgeService", "enter", "nbIn should be equal to nbIn@pre");
			if(getNbOut() != nbOutPre+1)
				Contractor.defaultContractor().postconditionError("BridgeService", "enter", "nbout should be equal to nbOut@pre+1");
		}else{
			if(getNbIn() != nbInPre+1)
				Contractor.defaultContractor().postconditionError("BridgeService", "enter", "nbIn should be equal to nbIn@pre+1");
			if(getNbOut() != nbOutPre)
				Contractor.defaultContractor().postconditionError("BridgeService", "enter", "nbout should be equal to nbOut@pre");
		}

		checkInvariant();

	}

	@Override
	public void leave(){
		checkInvariant();
		// pre: getNbCars > 0
		if(!(super.getNbCars() > 0))
			Contractor.defaultContractor().preconditionError("BridgeService", "leave", "The number of cars should be superior to 0");

		int nbInPre = getNbIn();
		int nbOutPre = getNbOut();

		super.leave();

		/* post: if getNbIn()@pre > getNbOut()@pre
		 *       then
		 *         getNbIn() == getNbIn()@pre - 1 
		 *         getNbOut() == getNbOut()@pre 
		 *       else
		 *         getNbIn() == getNbIn()@pre  
		 *         getNbOut() == getNbOut()@pre - 1 */
		if(nbInPre > nbOutPre){
			if(getNbIn() != nbInPre-1 )
				Contractor.defaultContractor().postconditionError("BridgeService", "leave", "nbIn should be equal to nbIn@pre-1");
			if(getNbOut() != nbOutPre)
				Contractor.defaultContractor().postconditionError("BridgeService", "leave", "nbout should be equal to nbOut@pre");
		}else{
			if(getNbIn() != nbInPre)
				Contractor.defaultContractor().postconditionError("BridgeService", "leave", "nbIn should be equal to nbIn@pre");
			if(getNbOut() != nbOutPre-1)
				Contractor.defaultContractor().postconditionError("BridgeService", "leave", "nbout should be equal to nbOut@pre-1");
		}

		checkInvariant();
	}

}
