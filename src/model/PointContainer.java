package model;

import java.util.ArrayList;
import java.util.Iterator;

import model.exception.ObjectAlreadyInListException;

public class PointContainer implements Container<Point>{

	private ArrayList<Point> points;

	public void add(Point point) throws ObjectAlreadyInListException {
		if(!this.points.contains(point)){
			this.points.add(point);
		}else{
			throw new ObjectAlreadyInListException(points.getClass().getCanonicalName());
		}		
	}

	public Point get(int i) {
		return this.points.get(i);
	}

	public Point get(Position pos) {
		for(Point p : this.points){
			if(p.getPosition().equals(pos)){
				return p;
			}
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Point> getAll() {
		return (ArrayList<Point>) this.points.clone();
	}

	public Iterator<Point> iterator(){
		return points.iterator();
	}
}
