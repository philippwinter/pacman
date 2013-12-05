package model;

public class Map {

	private static Map instance;

	private PositionContainer positionContainer;
	
	private final int width;
	
	private final int height;

	public static Map getInstance() {
		if(Map.instance == null){
			Map.instance = new Map();
		}

		return null;
	}
	
	private Map(){
		this(300, 300);
	}

	private Map(int width, int height) {
		this.width = width;
		this.height = height;
		
		// TODO Implement method

	}

}
