package model;

public class Position {

	private int x;

	private int y;

	private MapObjectContainer onPosition;
	
	public Position(int x, int y){
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	private void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	private void setY(int y) {
		this.y = y;
	}

	public MapObjectContainer getOnPosition() {
		return onPosition;
	}

	public void setOnPosition(MapObjectContainer onPosition) {
		this.onPosition = onPosition;
	}

}
