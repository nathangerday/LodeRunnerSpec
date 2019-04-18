package contracts;

import java.util.List;
import java.util.Set;

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

    @Override
	public Environment getEnvironment() {
        Environment res = super.getEnvironment();
        return res;
	}

	@Override
	public Player getPlayer() {
        Player res = super.getPlayer();
        return res;
	}

	@Override
	public List<Item> getTreasures() {
        List<Item> res = super.getTreasures();
        return res;
	}

	@Override
	public Status getStatus() {
        Status res = super.getStatus();
        return res;
	}

	@Override
	public Command getNextCommand() {
        Command res = super.getNextCommand();
        return res;
	}

	@Override
	public Set<Hole> getHoles() {
        Set<Hole> res = super.getHoles();
        return res;
	}

	@Override
	public void init(EditableScreen screen, int playerX, int playerY, List<Coord> guards, List<Coord> treasures, CommandManager cm) {
		super.init(screen, playerX, playerY, guards, treasures, cm);
	}

	@Override
	public void step() {
		super.step();
	}

	@Override
	public void addHole(int x, int y) {
		super.addHole(x, y);
	}

	@Override
	public void display() {
		super.display();
	}
    
}