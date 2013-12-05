package model;

public class Ghost extends DynamicTarget {

	private Colour colour;

	private String name;

	private GhostState state;

	@Override
	public void collide(MapObject obj) {
		// TODO Implement method

	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void changeState(GhostState state) {
		// Perform operations respective to new state
		
		this.state = state;
	}

	@Override
	public void changeState(StaticTargetState state) {
		// TODO Auto-generated method stub
		
	}

}
