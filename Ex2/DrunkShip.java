import java.util.Random;

import oop.ex2.*;

/**
 * this class represents the drunk spaceship. its actions are randomized.
 */
public class DrunkShip extends SpaceShip {
    /*
    data members and constants of this class
     */
    //Constants

    // a number which the random direction is drawn from
    private static final int RANDOM_DIRECTION = 2;
    // a number from which the random action is decided.
    private static final int RANDOM_ACTION = 4;
    // the forward movement indicator.
    private static final int ACCELERATE = 0;
    // the fire indicator
    private static final int FIRE = 1;
    // the shield indicator
    private static final int SHIELD = 2;
    // the teleport indicator
    private static final int TELEPORT = 3;

    //data members of a ship - non constants

    private Random rand;

    /**
     * the constructor for the drunk spaceship - it creates a random variable.
     */
    public DrunkShip() {
        super();
        this.rand = new Random();
    }

    /**
     * moves the ship in a random direction - has a 50-50 chance to move either direction.
     * acceleration is 1/4 chance
     *
     * @param action an int to determine whether the ship will accelerate
     */
    private void shipMovement(int action) {
        int direction = this.rand.nextInt(RANDOM_DIRECTION);
        // flag for forward movement
        boolean acceleration = (action == ACCELERATE);
        if (direction - LEFT > RIGHT) {
            this.getPhysics().move(acceleration, LEFT);
        } else {
            this.getPhysics().move(acceleration, RIGHT);
        }

    }

    /**
     * overrides the function from the main class this ship will try to do everything but according to random
     * order
     *
     * @param game The game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        int action = this.rand.nextInt(RANDOM_ACTION);
        if (action == TELEPORT) {
            this.teleport();
        }
        this.shipMovement(action);
        if (action == SHIELD) {
            this.shieldOn();
        }
        if (action == FIRE) {
            this.fire(game);
        }
        super.doAction(game);
    }

}
