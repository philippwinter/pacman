/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package model.event;

import model.*;

/**
 * @author Philipp Winter
 * @author Jonas Heidecke
 * @author Niklas Kaddatz
 */
public class GhostEventHandler extends EventHandler {

    private GhostContainer ghostContainer;

    public GhostEventHandler() {
        this.ghostContainer = Game.getInstance().getGhostContainer();
    }

    /**
     * Run the event handler.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for(Ghost g : this.ghostContainer){
            Position newPosition = Map.getPositionByDirectionIfMoveableTo(g.getPosition(), g.getHeadingTo());

            if(newPosition == null){
                Direction wantedDirection = g.getHeadingTo();
                Direction realizedDirection = null;

                for(Direction d : Direction.values()){
                    if(d == g.getHeadingTo()){
                        continue;
                    }
                    Position temp =  Map.getPositionByDirectionIfMoveableTo(g.getPosition(), g.getHeadingTo());
                    if(temp != null){
                        newPosition = temp;
                        realizedDirection = d;
                    }
                }

                if(wantedDirection.equals(realizedDirection)){
                    throw new RuntimeException("Cannot move to any point, something went wrong.");
                }
            }

            if(g.getState() == DynamicTargetState.HUNTER){
                g.move(newPosition);
            }else if(g.getState() == DynamicTargetState.MUNCHED){
                // Move to base
            }else if(g.getState() == DynamicTargetState.WAITING) {
                if(g.getWaitingSeconds() == 0){
                    // If time is up, releash the kraken
                    g.changeState(DynamicTargetState.HUNTER);
                }
            }else if(g.getState() == DynamicTargetState.HUNTED){
                if(g.getMovedInLastTurn()){
                    g.setMovedInLastTurn(false);
                }else{
                    g.move(newPosition);
                    g.setMovedInLastTurn(true);
                }
            }
        }
    }
}
