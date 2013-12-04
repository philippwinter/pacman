package model.event;

import java.util.ArrayList;

import model.Game;
import model.MapObject;
import model.MapObjectContainer;
import model.Pacman;
import model.PacmanContainer;

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
