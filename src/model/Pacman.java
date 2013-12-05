package model;

public class Pacman extends DynamicTarget {

	private String name;

	private Highscore highscore;
	
	@Override
	public void collide(MapObject obj) {
		// TODO Implement method

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void computeHighscore(){
		if(this.highscore == null){
			this.highscore = new Highscore(this);
		}
		this.highscore.compute();
	}

	public Highscore getHighscore() {
		return highscore;
	}

	@Override
	public void changeState(StaticTargetState state) {
		// TODO Auto-generated method stub
		
	}

}
