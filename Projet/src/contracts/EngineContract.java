package contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Cell;
import data.Command;
import data.Coord;
import data.CoordGuard;
import data.CoordItem;
import data.Hole;
import data.Item;
import data.Status;
import decorators.EngineDecorator;
import services.EditableScreen;
import services.Engine;
import services.Environment;
import services.Guard;
import services.Player;
import services.ScreenManager;
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
	public void init(ScreenManager sm, CommandManager cm, Engine engineInstance) {
		//\pre sm.getNbScreen() >= 1
		if(!(sm.getNbScreen() >= 1)){
			Contractor.defaultContractor().preconditionError("EngineContract", "init", "sm.getNbScreen() >= 1");
		}
				
		super.init(sm, cm, engineInstance);

		//inv post
		checkInvariant();

		//\post getEnvironment().getHeight() == sm.getScreen(0).getHeight()
		if(!(getEnvironment().getHeight() == sm.getScreen(0).getHeight())){
			Contractor.defaultContractor().postconditionError("EngineContractt", "init", "getEnvironment().getHeight() == sm.getScreen(0).getHeight()");
		}

		//\post getEnvironment().getWidth() == sm.getScreen(0).getWidth()
		if(!(getEnvironment().getWidth() == sm.getScreen(0).getWidth())){
			Contractor.defaultContractor().postconditionError("EngineContractt", "init", "getEnvironment().getWidth() == sm.getScreen(0).getWidth()");
		}

		//\post \Forall i in |0, getEnvironment().getWidth() - 1]
		//          \Forall j in [0, getEnvironment().getHeight() - 1]
		//              sm.getScreen(0).getCellNature(i, j) == getEnvironment().getCellNature(i, j))
		for(int i=0; i<getEnvironment().getWidth(); i++){
			for(int j=0; j<getEnvironment().getHeight(); j++){
				if(!(sm.getScreen(0).getCellNature(i, j) == getEnvironment().getCellNature(i, j))){
					Contractor.defaultContractor().postconditionError("EngineContract", "init", "\\Forall i in |0, getEnvironment().getWidth() - 1] \\Forall j in [0, getEnvironment().getHeight() - 1] sm.getScreen(0).getCellNature(i, j) == getEnvironment().getCellNature(i, j))s");
				}
			}
		}


		//\post getPlayer().getCol() == sm.getPlayerFromScreen(0).getX()
		if(!(getPlayer().getCol() == sm.getPlayerFromScreen(0).getX())){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getPlayer().getCol() == sm.getPlayerFromScreen(0).getX()");
		}

		//\post getPlayer().getHgt() == sm.getPlayerFromScreen(0).getY()
		if(!(getPlayer().getHgt() == sm.getPlayerFromScreen(0).getY())){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getPlayer().getHgt() == sm.getPlayerFromScreen(0).getY()");
		}

		//\post getPlayer().getEngine() == engineInstance
		if(!(getPlayer().getEngine() == engineInstance)){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getPlayer().getEngine() == engineInstance");
		}

		//\post \Forall CoordItem c \in sm.getItemsFromScreen(0)
		//          \Exists Item i \in getTreasures() \with (i.getHgt() == c.getY() && i.getCol() == c.getX() && i.getNature() == c.getItemType())
		for(CoordItem c : sm.getItemsFromScreen(0)){
			boolean found = false;
			for(Item i : getTreasures()){
				if(i.getHgt() == c.getY() && i.getCol() == c.getX() && i.getNature() == c.getItemType()){
					found = true;
				}
			}
			if(!found){
				Contractor.defaultContractor().postconditionError("EngineContract", "init", "\\Forall CoordItem c \\in sm.getItemsFromScreen(0) \\Exists Item i \\in getTreasures() \\with (i.getHgt() == c.getY() && i.getCol() == c.getX() && i.getNature() == c.getItemType())");
			}
		}
		//\post \Forall CoordGuard c \ in sm.getGuardsFromScreen(0)
		//          \Exists Guard g \in getGuards() \with (g.getHgt() == c.getY() && g.getCol() == g.getX() && g.getNature() == c.getType())
		for(CoordGuard c : sm.getGuardsFromScreen(0)){
			boolean found = false;
			for(Guard g : getGuards()){
				if(g.getHgt() == c.getY() && g.getCol() == c.getX() && g.getNature() == c.getType()){
					found = true;
				}
			}
			if(!found){
				Contractor.defaultContractor().postconditionError("EngineContract", "init", "\\Forall CoordGuard c \\ in sm.getGuardsFromScreen(0) \\Exists Guard g \\in getGuards() \\with (g.getHgt() == c.getY() && g.getCol() == g.getX() && g.getNature() == c.getType())");
			}
		}



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

		//\post getNbLifes() == 3
		if(!(getNbLifes() == 3)){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getNbLifes() == 3");
		}

		//\post getScore() == 0
		if(!(getScore() == 0)){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getScore() == 0");
		}

		//\post getScoreAtStartOfLevel() == 0
		if(!(getScoreAtStartOfLevel() == 0)){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getScoreAtStartOfLevel() == 0");
		}

		//\post getScreenManager() == sm
		if(!(getScreenManager() == sm)){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getScreenManager() == sm");
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
		List<Item> getTreasures_atPre = new ArrayList<>(getTreasures());
		Environment getEnvironment_atPre = getEnvironment();
		Player getPlayer_atPre = getPlayer();
		Command getNextCommand_atPre = getNextCommand();
		List<Guard> getGuards_atPre = new ArrayList<>(getGuards());
		int getNbLifes_atPre = getNbLifes();
		int getScore_atPre = getScore();
		int getScoreAtStartOfLevel_atPre = getScoreAtStartOfLevel();
		ScreenManager getScreenManager_atPre = getScreenManager();

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

		//\post getEnvironment() == getEnvironment()@pre
		if(!(getEnvironment() == getEnvironment_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getEnvironment() == getEnvironment()@pre");
		}

		//\post getPlayer() == getPlayer()@pre
		if(!(getPlayer() == getPlayer_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getPlayer() == getPlayer()@pre");
		}

		//\post getGuards() == getGuards()@pre
		if(!(getGuards().equals(getGuards_atPre))){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getGuards() == getGuards()@pre");
		}

		//\post getTreasures() == getTreasures()@pre
		if(!(getTreasures().equals(getTreasures_atPre))){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getTreasures() == getTreasures()@pre");
		}

		//\post getStatus() == getStatus()@pre
		if(!(getStatus() == getStatus_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getStatus() == getStatus()@pre");
		}

		//\post getNextCommand() == getNextCommand()@pre
		if(!(getNextCommand() == getNextCommand_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getNextCommand() == getNextCommand()@pre");
		}

		//\post getNbLifes() == getNbLifes()@pre
		if(!(getNbLifes() == getNbLifes_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getNbLifes() == getNbLifes()@pre");
		}

		//\post getScore() == getScore()@pre
		if(!(getScore() == getScore_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getScore() == getScore()@pre");
		}

		//\post getScoreAtStartOfLevel() == getScoreAtStartOfLevel()@pre
		if(!(getScoreAtStartOfLevel() == getScoreAtStartOfLevel_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getScoreAtStartOfLevel() == getScoreAtStartOfLevel()@pre");
		}

		//\post getScreenManager() == getScreenManager()@pre
		if(!(getScreenManager() == getScreenManager_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "addHole", "getScreenManager() == getScreenManager()@pre");
		}

	}

	@Override
	public void display() {

		//captures
		Set<Hole> getHoles_atPre = new HashSet<>(getHoles());
		Status getStatus_atPre = getStatus();
		List<Item> getTreasures_atPre = new ArrayList<>(getTreasures());
		Environment getEnvironment_atPre = getEnvironment();
		Player getPlayer_atPre = getPlayer();
		Command getNextCommand_atPre = getNextCommand();
		List<Guard> getGuards_atPre = new ArrayList<>(getGuards());
		int getNbLifes_atPre = getNbLifes();
		int getScore_atPre = getScore();
		int getScoreAtStartOfLevel_atPre = getScoreAtStartOfLevel();
		ScreenManager getScreenManager_atPre = getScreenManager();


		//inv pre
		checkInvariant();
		
		super.display();

		//inv post
		checkInvariant();


		//\post getEnvironment() == getEnvironment()@pre
		if(!(getEnvironment() == getEnvironment_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getEnvironment() == getEnvironment()@pre");
		}

		//\post getPlayer() == getPlayer()@pre
		if(!(getPlayer() == getPlayer_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getPlayer() == getPlayer()@pre");
		}

		//\post getGuards() == getGuards()@pre
		if(!(getGuards().equals(getGuards_atPre))){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getGuards() == getGuards()@pre");
		}

		//\post getTreasures() == getTreasures()@pre
		if(!(getTreasures().equals(getTreasures_atPre))){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getTreasures() == getTreasures()@pre");
		}

		//\post getStatus() == getStatus()@pre
		if(!(getStatus() == getStatus_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getStatus() == getStatus()@pre");
		}

		//\post getNextCommand() == getNextCommand()@pre
		if(!(getNextCommand() == getNextCommand_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getNextCommand() == getNextCommand()@pre");
		}

		//\post getHoles() == getHoles()@pre
		for(Hole h : getHoles()) {
			if(!(getHoles_atPre.contains(h))) {
				Contractor.defaultContractor().postconditionError("EngineContract", "display", "getHoles() == getHoles()@pre");
				break;
			}
		}

		//\post getNbLifes() == getNbLifes()@pre
		if(!(getNbLifes() == getNbLifes_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getNbLifes() == getNbLifes()@pre");
		}

		//\post getScore() == getScore()@pre
		if(!(getScore() == getScore_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getScore() == getScore()@pre");
		}

		//\post getScoreAtStartOfLevel() == getScoreAtStartOfLevel()@pre
		if(!(getScoreAtStartOfLevel() == getScoreAtStartOfLevel_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getScoreAtStartOfLevel() == getScoreAtStartOfLevel()@pre");
		}

		//\post getScreenManager() == getScreenManager()@pre
		if(!(getScreenManager() == getScreenManager_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getScreenManager() == getScreenManager()@pre");
		}

		
	}
    
}