package model;

import java.util.ArrayList;

import model.exception.ObjectAlreadyInListException;

public class PositionContainer implements Container<Position>{
	
	private ArrayList<Position> positions;

	public Position get(int i) {
		// TODO: Implement method
		return null;
	}

	public void add(Position el) throws ObjectAlreadyInListException {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Position> getAll() {
		return (ArrayList<Position>) this.positions.clone();
	}

}
