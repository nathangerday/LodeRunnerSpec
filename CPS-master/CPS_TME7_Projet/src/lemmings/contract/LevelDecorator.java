package lemmings.contract;

import java.awt.Point;

import lemmings.services.Level;

public class LevelDecorator implements Level{
	
	public void setEntrance(int x, int y) {
		level.setEntrance(x, y);
	}

	public void setExit(int x, int y) {
		level.setExit(x, y);
	}

	private Level level;

	public LevelDecorator(Level level){
		this.level = level;
	}

	/**
	 * @return
	 * @see lemmings.services.Level#getHeight()
	 */
	public int getHeight() {
		return level.getHeight();
	}

	/**
	 * @return
	 * @see lemmings.services.Level#getWidth()
	 */
	public int getWidth() {
		return level.getWidth();
	}

	/**
	 * @return
	 * @see lemmings.services.Level#isEditing()
	 */
	public boolean isEditing() {
		return level.isEditing();
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see lemmings.services.Level#getNature(int, int)
	 */
	public Nature getNature(int x, int y) {
		return level.getNature(x, y);
	}

	/**
	 * @return
	 * @see lemmings.services.Level#getEntrance()
	 */
	public Point getEntrance() {
		return level.getEntrance();
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see lemmings.services.Level#isEntrance(int, int)
	 */
	public boolean isEntrance(int x, int y) {
		return level.isEntrance(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 * @see lemmings.services.Level#isExit(int, int)
	 */
	public boolean isExit(int x, int y) {
		return level.isExit(x, y);
	}

	/**
	 * @param w
	 * @param h
	 * @see lemmings.services.Level#init(int, int)
	 */
	public void init(int w, int h) {
		level.init(w, h);
	}

	/**
	 * @param x
	 * @param y
	 * @param n
	 * @see lemmings.services.Level#setNature(int, int, lemmings.services.Level.Nature)
	 */
	public void setNature(int x, int y, Nature n) {
		level.setNature(x, y, n);
	}

	/**
	 * 
	 * @see lemmings.services.Level#goPlay()
	 */
	public void goPlay() {
		level.goPlay();
	}

	/**
	 * @param x
	 * @param y
	 * @see lemmings.services.Level#remove(int, int)
	 */
	public void remove(int x, int y) {
		level.remove(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @see lemmings.services.Level#build(int, int)
	 */
	public void build(int x, int y) {
		level.build(x, y);
	}
	
	
	

}
