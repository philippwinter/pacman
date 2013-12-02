package model;

public abstract class DynamicTarget extends Target {

	private Direction headingTo;

	public void move(Position pos) {
		// TODO: Implement method

	}

	public void eat(Target target) {
		// TODO: Implement method

	}

	public Direction getHeadingTo() {
		return headingTo;
	}

	public void setHeadingTo(Direction headingTo) {
		this.headingTo = headingTo;
	}

}
