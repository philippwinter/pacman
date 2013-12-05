package model;

import java.util.ArrayList;
import java.util.Iterator;

import model.exception.ObjectAlreadyInListException;

public class PositionContainer implements Container<Position>{
	
	private ArrayList<Position> positions;

	public Position get(int i) {
		return this.positions.get(i);
	}

	public void add(Position el) throws ObjectAlreadyInListException {
		if(this.positions.contains(el)){
			throw new ObjectAlreadyInListException(el.getClass().getCanonicalName());
		}
		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Position> getAll() {
		return (ArrayList<Position>) this.positions.clone();
	}
	
	public Iterator<Position> iterator(){
		return positions.iterator();
	}

}
