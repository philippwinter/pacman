package model;

import java.util.ArrayList;

public class PointContainer implements Container<Point>{

	private ArrayList<Point> points;

	public void add(Point point) {
		// TODO Implement method

	}

	public Point get(int i) {
		// TODO Implement method

		return null;
	}

	public Point get(Position pos) {
		// TODO Implement method

		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Point> getAll() {
		return (ArrayList<Point>) this.points.clone();
	}

}
