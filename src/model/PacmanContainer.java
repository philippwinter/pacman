package model;

import java.util.ArrayList;
import java.util.Iterator;

import model.exception.ObjectAlreadyInListException;

public class PacmanContainer implements Container<Pacman> {

	/**
	 * All pacman instances, for example Pacman and Mrs. Pacman
	 */
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
	
	public Iterator<Pacman> iterator(){
		return pacmans.iterator();
	}

}
