package model;

import java.util.ArrayList;

public class PacmanContainer {

	private ArrayList<Pacman> pacmans;

	public void add(Pacman pacman) throws ObjectAlreadyInListException {
		if(!this.pacmans.contains(pacman)){
			this.pacmans.add(pacman);
		}else{
			throw new ObjectAlreadyInListException(pacman.getClass().getCanonicalName());
		}
	}

	public Pacman get(int i) {
		return this.pacmans.get(i);
	}

	public ArrayList<Pacman> get(Position pos) {
		ArrayList<Pacman> pacmansOnPosition = new ArrayList<Pacman>(2);
		
		for(Pacman p : this.pacmans){
			if(p.isOnPosition(pos)){
				pacmansOnPosition.add(p);
			}
		}

		return pacmansOnPosition;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Pacman> getAll(){
		return (ArrayList<Pacman>) this.pacmans.clone();
	}

}
