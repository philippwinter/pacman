package model;

import java.util.ArrayList;
import java.util.Iterator;

public class GhostContainer implements Container<Ghost> {

	private ArrayList<Ghost> ghosts;

	public final int max = 4;

	public void add(Ghost ghost) {
		// TODO Implement method

	}

	public Ghost get(int i) {
		// TODO Implement method

		return null;
	}

	public Ghost get(Position pos) {
		// TODO Implement method

		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Ghost> getAll() {
		return (ArrayList<Ghost>) this.ghosts.clone();
	}

	public int getMax() {
		return max;
	}
	
	public Iterator<Ghost> iterator(){
		return ghosts.iterator();
	}

}
