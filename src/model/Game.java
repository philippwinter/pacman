package model;

public class Game {

	private static Game instance;

	private static boolean initialized;

	private GhostContainer ghostContainer;

	private CoinContainer coinContainer;

	private PointContainer pointsContainer;

	private PacmanContainer pacmanContainer;

	private Map map;

	public static Game getInstance() {
		// TODO: Implement method

		return null;
	}

	public static void initializeGame() {
		// TODO: Implement method

	}

	public static boolean isInitialized() {
		// TODO: Implement method

		return false;
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

}
