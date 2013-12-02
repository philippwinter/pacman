package model;

public abstract class MapObject {

	private Position position;

	public abstract void collide(MapObject obj);

	public boolean isOnPosition(Position pos) {
		// TODO Implement method

		return false;
	}

	public Position getPosition() {
		return position;
	}

}
