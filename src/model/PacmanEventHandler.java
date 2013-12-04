package model;

import java.util.ArrayList;

public class PacmanEventHandler extends EventHandler {

	private PacmanContainer pacmanContainer;

	public void perform() {
		ArrayList<Pacman> pacmans = Game.getInstance().getPacmanContainer().getAll();

		for(Pacman p : pacmans){
			this.handlePacman(p);
		}
	}
	
	private void handlePacman(Pacman pac){
		MapObjectContainer mapObjectsOnPos = pac.getPosition().getOnPosition();
		
		for(MapObject mO : mapObjectsOnPos.getAll()){
			
		}
	}
	
}
