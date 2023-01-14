import oop.ex2.*;

/**
 * This class has a single static method. It is used by the supplied driver to create all the spaceship
 * objects according to the command line arguments.
 */
public class SpaceShipFactory {
    // the different chars according to the ship types that can be given by the command line.
    private static final char HUMAN_SHIP = 'h';
    private static final char RUNNER_SHIP = 'r';
    private static final char BASHER_SHIP = 'b';
    private static final char AGGRESIVE_SHIP = 'a';
    private static final char DRUNK_SHIP = 'd';
    private static final char SPECIAL_SHIP = 's';

    /**
     * The function create spaceship objects according to the command line arguments.
     *
     * @param args Program arguments.
     * @return Array filled with spaceships.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        // your code goes here
        SpaceShip[] gameShips = new SpaceShip[args.length];
        int counter = 0;
        int humanCounter = 0;
        for (String arg : args) {
            if (arg.length() > 1) {
                return null;
            }
            char shipType = arg.charAt(0);
            if (humanCounter > 1) {
                return null;
            }
            if (shipType == HUMAN_SHIP) {
                humanCounter++;
                gameShips[counter] = new PlayerShip();
                counter++;
            }
            if (shipType == RUNNER_SHIP) {
                gameShips[counter] = new RunnerShip();
                counter++;
            }
            if (shipType == AGGRESIVE_SHIP) {
                gameShips[counter] = new AggresiveShip();
                counter++;
            }
            if (shipType == BASHER_SHIP) {
                gameShips[counter] = new BasherShip();
                counter++;
            }
            if (shipType == DRUNK_SHIP) {
                gameShips[counter] = new DrunkShip();
                counter++;
            }
            if (shipType == SPECIAL_SHIP) {
                gameShips[counter] = new SpecialShip();
                counter++;
            }
        }
        return gameShips;
    }
}
