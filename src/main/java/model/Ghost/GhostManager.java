package model.Ghost;

import model.GhostContainer;


public class GhostManager {

    private final GhostContainer ghosts;

    private final long[] times = {7,27,34,54,59,  79,84};

    private double time = 0;
    private int index = 0;

    private double pause = 0;

    public GhostManager(GhostContainer container){

        ghosts = container;

    }

    public void handle(double delta){

        if (pause > delta)
            pause -= delta;
        else {
            delta -= pause;
            pause = 0;
        }

        if (delta > 0){

            if (index < times.length) {

                time += delta;
                if (time >= times[index]) {
                    for (Ghost g : ghosts) g.changeBehavior();
                    index++;
                }
            }
        }
    }

    public void pause(double time){ pause = time; }
}


