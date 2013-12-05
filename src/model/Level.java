package model;

public class Level {
	
	private static Level instance;
	
	private int level = 1;
	
	private double secondsPerCoin = 15;
	
	public static Level getInstance(){
		if(Level.instance == null){
			Level.instance = new Level();
		}
		
		return Level.instance;
	}
	
	public void nextLevel(){
		// TODO Implement method
		
		// Reduce the amount of time the user has to munch a ghost
		this.secondsPerCoin *= 0.85;
		
		// Change the refresh rate = How fast is the pacman moving
		Game.getInstance().changeRefreshRate(this);
	}
	
	public int getLevel(){
		return this.level;
	}
	
	private Level(){
		// TODO Implement method
	}

	public double getSecondsPerCoin() {
		return secondsPerCoin;
	}

}
