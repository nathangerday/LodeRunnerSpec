package lemmings.contract;

import java.util.Set;

import lemmings.services.GameEng;
import lemmings.services.Lemming;
import lemmings.services.Level;

public class GameEngDecorator implements GameEng{
	
	private GameEng gameEng;
	
	public GameEngDecorator(GameEng gameEng) {
		this.gameEng = gameEng;
	}
	
	/**
	 * @return
	 * @see lemmings.services.GameEng#getLevel()
	 */
	public Level getLevel() {
		return gameEng.getLevel();
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see lemmings.services.GameEng#isObstacle(int, int)
	 */
	public boolean isObstacle(int x, int y) {
		return gameEng.isObstacle(x, y);
	}

	@Override
	public boolean isLibre(int x, int y) {
		return gameEng.isLibre(x, y);
	}
	

	/**
	 * @return
	 * @see lemmings.services.GameEng#getSizeColony()
	 */
	public int getSizeColony() {
		return gameEng.getSizeColony();
	}

	/**
	 * @return
	 * @see lemmings.services.GameEng#getSpawnSpeed()
	 */
	public int getSpawnSpeed() {
		return gameEng.getSpawnSpeed();
	}

	/**
	 * @return
	 * @see lemmings.services.GameEng#gameOver()
	 */
	public boolean gameOver() {
		return gameEng.gameOver();
	}

	/**
	 * @return
	 * @see lemmings.services.GameEng#score()
	 */
	public double score() {
		return gameEng.score();
	}

	/**
	 * @return
	 * @see lemmings.services.GameEng#getNombreTours()
	 */
	public int getNombreTours() {
		return gameEng.getNombreTours();
	}

	/**
	 * @return
	 * @see lemmings.services.GameEng#getNombreToursFinal()
	 */
	public int getNombreToursFinal() {
		return gameEng.getNombreToursFinal();
	}

	/**
	 * @return
	 * @see lemmings.services.GameEng#getLemmingsActifs()
	 */
	public Set<Integer> getLemmingsActifs() {
		return gameEng.getLemmingsActifs();
	}

	/**
	 * @param i
	 * @return
	 * @see lemmings.services.GameEng#getLemming(int)
	 */
	public Lemming getLemming(int i) {
		return gameEng.getLemming(i);
	}

	/**
	 * @return
	 * @see lemmings.services.GameEng#getNombreSauves()
	 */
	public int getNombreSauves() {
		return gameEng.getNombreSauves();
	}

	/**
	 * @return
	 * @see lemmings.services.GameEng#getNombreMorts()
	 */
	public int getNombreMorts() {
		return gameEng.getNombreMorts();
	}

	/**
	 * @return
	 * @see lemmings.services.GameEng#getNombreActifs()
	 */
	public int getNombreActifs() {
		return gameEng.getNombreActifs();
	}

	/**
	 * @return
	 * @see lemmings.services.GameEng#getNombreCrees()
	 */
	public int getNombreCrees() {
		return gameEng.getNombreCrees();
	}

	/**
	 * @param sizeColony
	 * @param spawnSpeed
	 * @see lemmings.services.GameEng#init(int, int)
	 */
	public void init(int sizeColony, int spawnSpeed) {
		gameEng.init(sizeColony, spawnSpeed);
	}

	/**
	 * 
	 * @see lemmings.services.GameEng#step()
	 */
	public void step() {
		gameEng.step();
	}

	/**
	 * @param i
	 * @see lemmings.services.GameEng#tuerLemming(int)
	 */
	public void tuerLemming(int i) {
		gameEng.tuerLemming(i);
	}

	/**
	 * @param i
	 * @see lemmings.services.GameEng#sauverLemming(int)
	 */
	public void sauverLemming(int i) {
		gameEng.sauverLemming(i);
	}

}