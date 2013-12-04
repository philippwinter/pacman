package model;

import java.util.ArrayList;

public class MapObjectContainer implements Container<MapObject> {

	private ArrayList<MapObject> mapObjects;

	public MapObject get(int i) {
		// TODO Implement method

		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<MapObject> getAll() {
		return (ArrayList<MapObject>) this.mapObjects.clone();
	}

	public void add(MapObject el) {
		// TODO Auto-generated method stub
		
	}

}
