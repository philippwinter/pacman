package model;

public abstract class Target extends MapObject {

	private TargetState state;

	public TargetState getState() {
		return state;
	}

	public void setState(TargetState state) {
		this.state = state;
	}

}
