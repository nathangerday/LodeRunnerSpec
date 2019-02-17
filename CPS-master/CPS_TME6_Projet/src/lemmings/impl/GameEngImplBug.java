package lemmings.impl;

import java.util.Set;

import lemmings.services.GameEng;
import lemmings.services.Lemming;
import lemmings.services.Level;

public class GameEngImplBug implements GameEng{

	@Override
	public Level getLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isObstacle(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSizeColony() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSpawnSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double score() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNombreTours() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNombreToursFinal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Integer> getLemmingsActifs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lemming getLemming(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNombreSauves() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNombreMorts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNombreActifs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNombreCrees() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init(int sizeColony, int spawnSpeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tuerLemming(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverLemming(int i) {
		// TODO Auto-generated method stub
		
	}

}
