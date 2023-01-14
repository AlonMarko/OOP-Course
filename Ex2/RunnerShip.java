import oop.ex2.*;

/**
 * this class implements the Runner spaceship which always runs from a fight.
 */
public class RunnerShip extends SpaceShip {
    /*
    data members and constants of this class
     */
    //Constants
    // the threshold angle from which it will teleport
    private static final double ANGLE = 0.23;
    // the threshold distance from which it will teleport
    private static final double UNITS = 0.25;

    /**
     * this method moved the runner space ship which always tries to run away.
     * checks the angle from closest ship and turns to the appropriate direction inorder
     * to runaway.
     *
     * @param game SpaceWars object
     */
    private void shipMovement(SpaceWars game) {
        double angleToClosestShip = game.getClosestShipTo(this).getPhysics().angleTo(this.getPhysics());
        if (angleToClosestShip > 0) {
            this.getPhysics().move(true, RIGHT);
        } else {
            this.getPhysics().move(true, LEFT);
        }
    }

    /**
     * overrides the function from the main class - this ship only tries to teleport, no shield or fire.
     * Teleportation -> Movement -> end of round actions.
     *
     * @param game The game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        double angleToClosestShip = game.getClosestShipTo(this).getPhysics().angleTo(this.getPhysics());
        double distanceToClosestShip =
                game.getClosestShipTo(this).getPhysics().distanceFrom(this.getPhysics());
        if (distanceToClosestShip < UNITS && angleToClosestShip < ANGLE) {
            this.teleport();
        }
        this.shipMovement(game);
        super.doAction(game);
    }
}
