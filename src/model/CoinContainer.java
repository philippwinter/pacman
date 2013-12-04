package model;

import java.util.ArrayList;

public class CoinContainer implements Container<Coin> {

	private ArrayList<Coin> coins;

	public final int max = 4;

	public void add(Coin coin) {
		// TODO: Implement method

	}

	public Coin get(int i) {
		// TODO: Implement method

		return null;
	}

	public Coin get(Position pos) {
		// TODO: Implement method

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Coin> getAll(){
		return (ArrayList<Coin>) this.coins.clone();
	}

	public int getMax() {
		return max;
	}

}
