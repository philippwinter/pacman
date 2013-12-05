package model;

public class Game {

	private static Game instance;

	private static boolean initialized;

	private GhostContainer ghostContainer;

	private CoinContainer coinContainer;

	private PointContainer pointsContainer;

	private PacmanContainer pacmanContainer;
	
	private double refreshRate = 0.25;

	private Map map;
	
	public static Game getInstance() {
		if(Game.instance == null){
			Game.instance = new Game();
		}

		return Game.instance;
	}

	public static void initializeGame() {
		// TODO: Implement method

		
		// Mark Game as initialized
		
		Game.initialized = true;
	}

	public static boolean isInitialized() {
		return Game.initialized;
	}

	private Game() {
		// TODO: Implement method

	}

	public GhostContainer getGhostContainer() {
		return ghostContainer;
	}

	public CoinContainer getCoinContainer() {
		return coinContainer;
	}

	public PointContainer getPointsContainer() {
		return pointsContainer;
	}

	public PacmanContainer getPacmanContainer() {
		return pacmanContainer;
	}

	public Map getMap() {
		return map;
	}
	
	public void changeRefreshRate(Level l){
		// f(x) = 1 / sqrt(x+2)
		this.refreshRate = (1/Math.sqrt(l.getLevel() + 2));
	}

	public double getRefreshRate() {
		return this.refreshRate;
	}

}
