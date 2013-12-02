package model;

public class Pacman extends DynamicTarget {

	private String name;

	private Highscore highscore;

	public Highscore computeHighscore() {
		// TODO Implement method

		return null;
	}

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

	public Highscore getHighscore() {
		return highscore;
	}

}
