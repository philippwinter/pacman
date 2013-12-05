package model.event;

import model.Coin;
import model.Game;
import model.Level;

public class CoinEventHandler extends EventHandler {

	private double activePointSeconds = 0;
	
	@Override
	public void perform() {
		// TODO Auto-generated method stub
		
		this.activePointSeconds -= Game.getInstance().getRefreshRate();
		
	}

	public double getActivePointSeconds() {
		return activePointSeconds;
	}

	public void gotEated(Coin c){
		this.activePointSeconds += Level.getInstance().getSecondsPerCoin();
	}

}
