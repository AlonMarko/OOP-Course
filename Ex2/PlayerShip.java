import java.awt.Image;

import oop.ex2.*;

/**
 * the Human controlled spaceship class that extends the normal spaceship class
 */
public class PlayerShip extends SpaceShip {
    /**
     * this method it a private method used to move the ship during the round - checks the correct inputs and
     * uses the game methods to move the ship around the game
     *
     * @param game SpaceWars object.
     */
    private void shipMovement(SpaceWars game) {
        boolean forwardAcceleration = game.getGUI().isUpPressed();
        boolean right = game.getGUI().isRightPressed();
        boolean left = game.getGUI().isLeftPressed();
        if (forwardAcceleration) {
            if (right) {
                this.getPhysics().move(true, RIGHT);
            } else if (left) {
                this.getPhysics().move(true, LEFT);
            }
            this.getPhysics().move(true, FORWARD);
        } else if (right) {
            this.getPhysics().move(false, RIGHT);
        } else if (left) {
            this.getPhysics().move(false, LEFT);
        }
    }

    /**
     * overrides the original getImage function since this is a human ship so different image!
     *
     * @return Image of the ship according to its shield status.
     */
    public Image getImage() {
        if (this.isShieldOn) {
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        }
        return GameGUI.SPACESHIP_IMAGE;
    }

    /**
     * does the action of the ships during this round.
     * it will check for human input based on the game methods according to the game order which is
     * Teleportation -> Movement (turn and forward) -> Shields -> Shot -> end of round actions.
     *
     * @param game The game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        if (game.getGUI().isTeleportPressed()) {
            this.teleport();
        }
        this.shipMovement(game);
        if (game.getGUI().isShieldsPressed()) {
            this.shieldOn();
        }
        if (game.getGUI().isShotPressed()) {
            this.fire(game);
        }
        super.doAction(game);
    }
}
