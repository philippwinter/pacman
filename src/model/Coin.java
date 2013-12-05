package model;

public class Coin extends StaticTarget {

	@Override
	public void collide(MapObject obj) {

		if (obj instanceof Pacman) {
			if (this.getState() == StaticTargetState.AVAILABLE) {
				// Change all states of Ghosts to "Hunted"

				for (Ghost g : Game.getInstance().getGhostContainer()) {
					// Perform changes

					g.changeState(GhostState.HUNTED);
					

				}

				// Add points to Pacmans score
			}
		} else {
			return;
		}
	}

	@Override
	public void changeState(StaticTargetState state) {
		// TODO Auto-generated method stub

	}

}
