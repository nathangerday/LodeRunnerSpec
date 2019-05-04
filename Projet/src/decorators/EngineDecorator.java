package decorators;

import java.util.List;
import java.util.Set;

import data.Command;
import data.Coord;
import data.Hole;
import data.Item;
import data.Status;
import services.EditableScreen;
import services.Engine;
import services.Environment;
import services.Guard;
import services.Player;
import services.ScreenManager;
import utils.CommandManager;

public class EngineDecorator implements Engine{
    protected final Engine delegate;

    public EngineDecorator(Engine delegate){
        this.delegate = delegate;
    }

    protected Engine getDelegate(){
        return this.delegate;
    }

	@Override
	public Environment getEnvironment() {
		return getDelegate().getEnvironment();
	}

	@Override
	public ScreenManager getScreenManager(){
		return getDelegate().getScreenManager();
	}

	@Override
	public Player getPlayer() {
		return getDelegate().getPlayer();
	}

	@Override
	public List<Item> getTreasures() {
		return getDelegate().getTreasures();
	}
	
	@Override
	public List<Guard> getGuards(){
		return getDelegate().getGuards();
	}

	@Override
	public Status getStatus() {
		return getDelegate().getStatus();
	}

	public int getNbLifes(){
        return getDelegate().getNbLifes();
    }
    
	public int getScore(){
        return getDelegate().getScore();
    }
    
	public int getScoreAtStartOfLevel(){
        return getDelegate().getScoreAtStartOfLevel();
    }

	@Override
	public Command getNextCommand() {
		return getDelegate().getNextCommand();
	}

	@Override
	public Set<Hole> getHoles() {
		return getDelegate().getHoles();
	}

	@Override
	public void init(ScreenManager sm, CommandManager cm, Engine engineInstance) {
		getDelegate().init(sm, cm, engineInstance);
	}

	@Override
	public void step() {
		getDelegate().step();
	}

	@Override
	public void addHole(int x, int y) {
		getDelegate().addHole(x, y);
	}

	@Override
	public void display() {
		getDelegate().display();
	}

	@Override
	public Engine copy() {
        return getDelegate().copy();
	}
}