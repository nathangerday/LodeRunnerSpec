package contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Cell;
import data.Command;
import data.CoordGuard;
import data.CoordItem;
import data.Entity;
import data.Hole;
import data.Item;
import data.ItemType;
import data.Status;
import decorators.EngineDecorator;
import services.Engine;
import services.Environment;
import services.Guard;
import services.Player;
import services.ScreenManager;
import utils.CommandManager;
import utils.Util;

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
		//\pre sm != null
		if(!(sm != null)){
			Contractor.defaultContractor().preconditionError("EngineContract", "init", "sm != null");
		}

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

	    //\post getNbTreasuresLeft() == \Count CoordItem c \in sm.getItemsFromScreen(0) \with (c.getItemType() == Treasure)
		int countTreasure = 0;
		for(CoordItem c : sm.getItemsFromScreen(0)){
			if(c.getItemType() == ItemType.Treasure){
				countTreasure++;
			}
		}

		if(!(getNbTreasuresLeft() == countTreasure)){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getNbTreasuresLeft() == \\Count CoordItem c \\in sm.getItemsFromScreen(0) \\with (c.getItemType() == Treasure)");
		}

		//\post getCurrentLevel() == 0
		if(!(getCurrentLevel() == 0)){
			Contractor.defaultContractor().postconditionError("EngineContract", "init", "getCurrentLevel() == 0");
		}

	}

	@Override
	public void step() {
		//\pre getStatus() == Status.Playing
		if(!(getStatus() == Status.Playing)){
			Contractor.defaultContractor().postconditionError("EngineContract", "step", "getStatus() == Status.Playing");
		}

		//captures
		Cell[][] getCellNature_atPre = new Cell[getEnvironment().getWidth()][getEnvironment().getHeight()];
        List<List<Set<Entity>>> getCellContent_atPre = new ArrayList<>();
        for(int u = 0; u < getEnvironment().getWidth(); u++){
            getCellContent_atPre.add(new ArrayList<>());
            for(int v = 0; v < getEnvironment().getHeight(); v++){
                getCellNature_atPre[u][v] = getEnvironment().getCellNature(u, v);
                getCellContent_atPre.get(u).add(new HashSet<>(getEnvironment().getCellContent(u, v)));
            }
        }
		Set<Hole> getHoles_atPre = new HashSet<>();
		for(Hole h : getHoles()){
			getHoles_atPre.add(new Hole(h.getX(), h.getY(), h.getTime()));
		}
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
		int getNbTreasuresLeft_atPre = getNbTreasuresLeft();
		int getCurrentLevel_atPre = getCurrentLevel();
		Command commandManagerPeekCurrentCommand_atPre;
		if(getCommandManager() == null){
			commandManagerPeekCurrentCommand_atPre = Command.NONE;
		}else{
			commandManagerPeekCurrentCommand_atPre = getCommandManager().peekCurrentCommand();
		}
		int getPlayerCol_atPre = getPlayer().getCol();
		int getPlayerHgt_atPre = getPlayer().getHgt();

		//inv pre
		checkInvariant();

		super.step();

		//inv post
		checkInvariant();

		//\post getCommandManager()@pre != null \implies getNextCommand() == getCommandManager().peekCurrentCommand()@pre
		if(!(Util.containsTreasure(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && getNbTreasuresLeft_atPre == 1) && !Util.containsGuard(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre))){
			boolean holeexists = false;
			for(Hole o: getHoles_atPre){
				if(o.getX() == getPlayerCol_atPre && o.getY() == getPlayerHgt_atPre && o.getTime() == 14){
					holeexists = true;
				}
			}
			if(!holeexists){
				if(!(Checker.implication(getCommandManager() != null, getNextCommand() == commandManagerPeekCurrentCommand_atPre))){
					Contractor.defaultContractor().postconditionError("EngineContract", "step", "getCommandManager()@pre != null \\implies getNextCommand() == getCommandManager().peekCurrentCommand()@pre");
				}
			}
		}

		//\post getCommandManager()@pre == null \implies getNextCommand() == NONE
		if(!(Checker.implication(getCommandManager() == null, getNextCommand() == Command.NONE))){
			Contractor.defaultContractor().postconditionError("EngineContract", "step", "getCommandManager()@pre == null \\implies getNextCommand() == NONE");
		}

		//\post \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//          \and getNbLifes()@pre == 1
		//          \implies getNbLifes() == 0 \and getStatus() = Loss
		if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && getNbLifes_atPre == 1, getNbLifes() == 0 && getStatus() == Status.Loss))){
			Contractor.defaultContractor().postconditionError("EngineContract", "step", "\\Exists Guard g \\in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre \\and getNbLifes()@pre == 1 \\implies getNbLifes() == 0 \\and getStatus() = Loss");
		}

		//\post \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//          \and getNbLifes()@pre > 1
		//          \implies death
		// if(!(Checker.implication(Util.containsGuard(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && getNbLifes_atPre > 1, death))){
		// 	Contractor.defaultContractor().postconditionError("EngineContract", "step", "\\Exists Guard g \\in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre \\and getNbLifes()@pre > 1 \\implies death");
		// }

		//\post \not \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//      \and \Exists Treasure t \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//              \implies t \not \in getTreasure() \and getNbTreasuresLeft() = getNbTreasuresLeft()@pre - 1 \and getScore() = getScore()@pre + 1
		//                  \and (getNbTreasuresLeft()@pre == 1 \and getCurrentLevel()@pre < getScreenManager().getNbScreen() - 1
		//                              \implies getCurrentLevel() = getCurrentLevel()@pre + 1 \and loadLevel(getCurrentLevel()@pre + 1))
		//                  \and (getNbTreasuresLeft()@pre == 1 \and getCurrentLevel()@pre = getScreenManager().getNbScreen() - 1
		//                              \implies getStatus() = Win
		if(!Util.containsGuard(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && Util.containsTreasure(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre))){
			if(Util.containsTreasure(getEnvironment().getCellContent(getPlayerCol_atPre, getPlayerHgt_atPre)) || getScore() != getScore_atPre + 1){
				Contractor.defaultContractor().postconditionError("EnfineContract", "step", "pickup treasure");
			}
			// if(!(Checker.implication(getNbTreasuresLeft_atPre == 1 && getCurrentLevel_atPre < getScreenManager().getNbScreen() - 1, getCurrentLevel() == getCurrentLevel_atPre + 1 && loadlevel(getCurrentLevel_atPre + 1)){
			// 	Contractor.defaultContractor().postconditionError("EngineContract", "step", "Load next level");
			// }

			if(!(Checker.implication(getNbTreasuresLeft_atPre == 1 && getCurrentLevel_atPre == getScreenManager().getNbScreen() - 1, getStatus() == Status.Win))){
				Contractor.defaultContractor().postconditionError("EngineContract", "step", "No more levels, win");
			}

		}

		//\post \not (\Exists Treasure t \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//              \and getNbTreasuresLeft() = 1)
		//			\and \not \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//          \and \Exists Item i \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//              \implies getPlayer().getCurrentlyHeldItem().getNature() = i.getNature()
		if(!Util.containsTreasure(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && !Util.containsGuard(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && Util.containsItem(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && getNbLifes_atPre == getNbLifes()){
			ItemType nature = Util.getItem(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)).getNature();
			if(!(getPlayer().getCurrentlyHeldItem().getNature() == nature)){
				Contractor.defaultContractor().postconditionError("EngineContract", "step", "Pickup item");
			}
		}


		
		//\post \not (\Exists Treasure t \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//              \and getNbTreasuresLeft() = 1)
		//			\and \not \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//          \and getEnvironment().getCellNature(getPlayer().getCol()@pre, getPlayer().getHgt()@pre - 1)@pre = TRP
		//                  \implies getEnvironment().getCellNature(getPlayer().getCol()@pre, getPlayer().getHgt()@pre - 1) = EMP
		if(!(Util.containsTreasure(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && getNbTreasuresLeft_atPre == 1) && !Util.containsGuard(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && getCellNature_atPre[getPlayerCol_atPre][getPlayerHgt_atPre - 1] == Cell.TRP && getNbLifes_atPre == getNbLifes()){
			if(!(getEnvironment().getCellNature(getPlayerCol_atPre, getPlayerHgt_atPre - 1) == Cell.EMP)){
				Contractor.defaultContractor().postconditionError("EngineContract", "step", "Reveal trap");
			}
		}

		//\post \not (\Exists Treasure t \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//              \and getNbTreasuresLeft() = 1)
		//      \not \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//      \and \Forall Hole h \in getHoles()@pre
		//              h.getTime() < 14 \impl \Exists Hole o \in getHoles() \with 
		//                  (o.getX() == h.getX() \and o.getY() == h.getY() \and o.getTime() == h.getTime() + 1)
		//              h.getTime() == 14 
		//                  \impl \not \Exists Hole o \in getHoles() \with (o.getX() == h.getX() \and o.getY() == h.getY())
		//              h.getTime() == 14 \and getPlayer().getX() == h.getX() \and getPlayer().getY() == h.getY() 
		//                  \impl death
		if(!(Util.containsTreasure(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && getNbTreasuresLeft_atPre == 1) && !Util.containsGuard(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && getNbLifes_atPre == getNbLifes()){
			for(Hole h : getHoles_atPre){
				boolean ok = false;
				if(h.getTime() < 14){
					for(Hole o: getHoles()){
						if(o.getX() == h.getX() && o.getY() == h.getY() && o.getTime() == h.getTime() + 1){
							ok = true;
						}
					}
					if(!ok){
						Contractor.defaultContractor().postconditionError("EngineContract", "step", "Increase time on hole");
					}
				}
				if(h.getTime() == 14){
					for(Hole o: getHoles()){
						if(o.getX() == h.getX() && o.getY() == h.getY() && o.getTime() == h.getTime() + 1){
							ok = true;
						}
					}
					if(ok){
						Contractor.defaultContractor().postconditionError("EngineContract", "step", "Fill old hole");
					}
					// if(getPlayerCol_atPre == h.getX() && getPlayerHgt_atPre == h.getY()){
					// 	death
					// }
				}
			}
		}

		//\post \not \Exists Hole h \in getHoles()@pre \with (h.getTime() == 14 \and h.getX() == getPlayer().getX() \and h.getY() == getPlayer().getY())
		//      \and \not \Exists Guard g \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//      \and \not (\Exists Treasure t \in getEnvironment().getCellContent(getPlayer().getCol()@pre, getPlayer().getHgt()@pre)@pre
		//              \and getNbTreasuresLeft() = 1)
		//      \impl getStatus() == Playing
		if(!(Util.containsTreasure(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre)) && getNbTreasuresLeft_atPre == 1) && !Util.containsGuard(getCellContent_atPre.get(getPlayerCol_atPre).get(getPlayerHgt_atPre))){
			boolean holeexists = false;
			for(Hole o: getHoles_atPre){
				if(o.getX() == getPlayerCol_atPre && o.getY() == getPlayerHgt_atPre && o.getTime() == 14){
					holeexists = true;
				}
			}
			if(!holeexists){
				if(getStatus() != Status.Playing){
					Contractor.defaultContractor().postconditionError("EngineContract", "step", "Keep playing when no reason to die");
				}
			}
		}

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
		int getNbTreasuresLeft_atPre = getNbTreasuresLeft();
		int getCurrentLevel_atPre = getCurrentLevel();

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

		//\post getNbTreasuresLeft() == getNbTreasuresLeft()@pre
		if(!(getNbTreasuresLeft() == getNbTreasuresLeft_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "diplay", "getNbTreasuresLeft() == getNbTreasuresLeft()@pre");
		}

		//\post getCurrentLevel() == getCurrentLevel()@pre
		if(!(getCurrentLevel() == getCurrentLevel_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getCurrentLevel() == getCurrentLevel()@pre");
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
		int getNbTreasuresLeft_atPre = getNbTreasuresLeft();
		int getCurrentLevel_atPre = getCurrentLevel();


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

		//\post getNbTreasuresLeft() == getNbTreasuresLeft()@pre
		if(!(getNbTreasuresLeft() == getNbTreasuresLeft_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getNbTreasuresLeft() == getNbTreasuresLeft()@pre");
		}

		//\post getCurrentLevel() == getCurrentLevel()@pre
		if(!(getCurrentLevel() == getCurrentLevel_atPre)){
			Contractor.defaultContractor().postconditionError("EngineContract", "display", "getCurrentLevel() == getCurrentLevel()@pre");
		}
		
	}
    
}