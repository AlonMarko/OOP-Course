package filesprocessing.orders;

import filesprocessing.TypeOne;
import filesprocessing.filters.BadName;

/**
 * order class creator.
 */
public class OrderFactory {
    /*
     constants
     */
    private static final int REVERSE_PATTERN = 2;
    private static final String ABS = "abs";
    private static final String SIZE = "size";
    private static final String TYPE = "type";
    private static final String BAD_ORDER = "invalid order.";

    /**
     * order creator!
     *
     * @param order - the order to create from the command file
     * @return - order object
     * @throws TypeOne - error thrower.
     */
    public static Order orderCreator(String order) throws TypeOne {
        String[] splitInput = order.split("#", -1);
        // did cases like filterfactory but intellliJ did not like and suggested to switch to if's
        if (ABS.equals(splitInput[0])) {
            if (splitInput.length == REVERSE_PATTERN) {
                return new RevereseSuffix(OrderAbs.returnObject());
            }
            return OrderAbs.returnObject();
        } else if (SIZE.equals(splitInput[0])) {
            if (splitInput.length == REVERSE_PATTERN) {
                return new RevereseSuffix(OrderSize.returnObject());
            }
            return OrderSize.returnObject();
        } else if (TYPE.equals(splitInput[0])) {
            if (splitInput.length == REVERSE_PATTERN) {
                return new RevereseSuffix(OrderType.returnObject());
            }
            return OrderType.returnObject();
        }
        throw new BadName(BAD_ORDER);
    }
}
