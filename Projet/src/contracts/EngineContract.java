package contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Cell;
import data.Command;
import data.Coord;
import data.Hole;
import data.Item;
import data.Status;
import decorators.EngineDecorator;
import services.EditableScreen;
import services.Engine;
import services.Environment;
import services.Player;
import utils.CommandManager;

public class EngineContract extends EngineDecorator{

    public EngineContract(Engine delegate){
        super(delegate);
    }

    public void checkInvariant(){

    }

    @Override
	public Environment getEnvironment() {
		return super.getEnvironment();
	}
	
	@Override
	public void init(EditableScreen screen, int playerX, int playerY, List<Coord> guards, List<Coord> treasures, CommandManager cm, Engine engineInstance) {
		//\pre screen.isPlayable()
		if(!(screen.isPlayable())){
			Contractor.defaultContractor().preconditionError("EngineContract", "init", "screen.isPlayable()");
		}

		//\pre playerX >= 0
		if(!(playerX >= 0)){
			Contractor.defaultContractor().preconditionError("EngineContract", "init", "playerX >= 0");
		}

		//\pre playerY >= 0
		if(!(playerY >= 0)){
			Contractor.defaultContractor().preconditionError("EngineContract", "init", "playerY >= 0");
		}

		//\pre playerX < screen.getWidth()
		if(!(playerX < screen.getWidth())){
			Contractor.defaultContractor().preconditionError("EngineContract", "init", "playerX < screen.getWidth()");
		}

		//\pre playerY < screen.getHeight()
		if(!(playerY < screen.getHeight())){
			Contractor.defaultContractor().preconditionError("EngineContract", "init", "playerY < screen.getHeight()");
		}

		super.init(screen, playerX, playerY, guards, treasures, cm, engineInstance);

		//inv post
		checkInvariant();

		//\post getStatus() == Playing
		if(!(getStatus() == Status.Playing)){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getStatus() == Playing");
		}

		//\post getNextCommand() == NONE
		if(!(getNextCommand() == Command.NONE)){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getNextCommand() == NONE");
		}

		//\post getHoles() == {}
		if(!(getHoles().size() == 0)){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getHoles() == {}");
		}
	}

	@Override
	public void step() {
		//\pre getStatus() == Status.Playing
		if(!(getStatus() == Status.Playing)){
			Contractor.defaultContractor().postconditionError("EngineContract", "step", "getStatus() == Status.Playing");
		}
		
		super.step();
	}

	@Override
	public void addHole(int x, int y) {
		//\pre \not \Exists Hole h \in getHoles() \with (h.getX() == x && h.getY() == y)
		for(Hole h : getHoles()){
			if(h.getX() == x && h.getY() == y){
				Contractor.defaultContractor().preconditionError("EngineContract", "addHole", "\\not \\Exists Hole h \\in getHoles() \\with (h.getX() == x && h.getY() == y)");
			}
		}

		//\pre getEnvironment().getCellNature(x, y) == HOL
		if(!(getEnvironment().getCellNature(x, y) == Cell.HOL)){
			Contractor.defaultContractor().preconditionError("EngineContract", "addHole", "getEnvironment().getCellNature(x, y) == HOL");
		}

		//captures
		Set<Hole> getHoles_atPre = new HashSet<>(getHoles());
		Status getStatus_atPre = getStatus();
		// List<Item> getTreasures_atPre = new ArrayList<>(getTreasures());
		Environment getEnvironment_atPre = getEnvironment();
		Player getPlayer_atPre = getPlayer();
		Command getNextCommand_atPre = getNextCommand();

		//inv pre
		checkInvariant();
		
		super.addHole(x, y);

		//inv post
		checkInvariant();
		
		// \post getHoles() == getHoles()@pre \Union {h} \with (h.getX() == x \and h.getY() == y)
		Set<Hole> oldHoles = new HashSet<>(getHoles_atPre);
		oldHoles.add(new Hole(x, y));		
		for(Hole h : getHoles()) {
			if(!(oldHoles.contains(h))) {
				Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getHoles() == getHoles()@pre \\Union {h} \\with (h.getX() == x \\and h.getY() == y)");
				break;
			}
		}

		//\post getStatus() == getStatus()@pre
		if(!(getStatus_atPre == getStatus())){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getStatus() == getStatus()@pre");
		}
		//\post getNextCommand() == getNextCommand()@pre
		if(!(getNextCommand() == getNextCommand_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getNextCommand() == getNextCommand()@pre");
		}
		
		//\post getTreasures() == getTreasures()@pre
	}

	@Override
	public void display() {

		//captures
		Set<Hole> getHoles_atPre = new HashSet<>(getHoles());
		Status getStatus_atPre = getStatus();
		// List<Item> getTreasures_atPre = new ArrayList<>(getTreasures());
		Environment getEnvironment_atPre = getEnvironment();
		Player getPlayer_atPre = getPlayer();
		Command getNextCommand_atPre = getNextCommand();

		//inv pre
		checkInvariant();
		
		super.display();

		//inv post
		checkInvariant();


		// \post getHoles() == getHoles()@pre \Union {h} \with (h.getX() == x \and h.getY() == y)
		Set<Hole> oldHoles = new HashSet<>(getHoles_atPre);
		for(Hole h : getHoles()) {
			if(!(oldHoles.contains(h))) {
				Contractor.defaultContractor().postconditionError("EngineContract", "display", "getHoles() == getHoles()@pre \\Union {h} \\with (h.getX() == x \\and h.getY() == y)");
				break;
			}
		}

		//\post getStatus() == getStatus()@pre
		if(!(getStatus_atPre == getStatus())){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getStatus() == getStatus()@pre");
		}
		//\post getNextCommand() == getNextCommand()@pre
		if(!(getNextCommand() == getNextCommand_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getNextCommand() == getNextCommand()@pre");
		}
		
		//\post getTreasures() == getTreasures()@pre
		
	}
    
}