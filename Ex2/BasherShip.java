import oop.ex2.*;

/**
 * this class implements the basher spaceship that tries to collide with other ships.
 */
public class BasherShip extends SpaceShip {
    /*
    data members and constants of this class
     */
    //Constants
    // distance threshold from which it will activate shields.
    private static final double DISTANCE = 0.19;

    private void shipMovement(SpaceWars game) {
        double angleToClosestShip = game.getClosestShipTo(this).getPhysics().angleTo(this.getPhysics());
        if (angleToClosestShip > 0) {
            this.getPhysics().move(true, LEFT);
        } else if (angleToClosestShip < 0) {
            this.getPhysics().move(true, RIGHT);
        } else {
            this.getPhysics().move(true, FORWARD);
        }
    }

    /**
     * overrides the function from the main class - this ship only tries to activate shields when needed.
     * Movement -> shields -> end of round actions.
     *
     * @param game The game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        double distanceToShip = game.getClosestShipTo(this).getPhysics().distanceFrom(this.getPhysics());
        this.shipMovement(game);
        if (distanceToShip <= DISTANCE) {
            this.shieldOn();
        }
        super.doAction(game);
    }
}
