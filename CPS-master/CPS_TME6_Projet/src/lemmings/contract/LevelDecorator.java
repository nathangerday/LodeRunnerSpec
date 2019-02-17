package lemmings.contract;

import lemmings.services.Level;

public class LevelDecorator implements Level{
	private Level level;

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEditing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Nature getNature(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(int w, int h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNature(int x, int y, Nature n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goPlay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void build(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEntrance(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExit(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
