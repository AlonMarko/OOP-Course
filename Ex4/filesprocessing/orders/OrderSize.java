package filesprocessing.orders;

import java.io.File;

/**
 * class implements size ordering
 */
public class OrderSize extends Order {
    /*
    data members
     */
    // a singleton class for OrderSize - can have only 1 at a time
    private static final OrderSize singleSize = new OrderSize();

    /**
     * private constructor
     */
    private OrderSize() {
    }

    /**
     * static return method for singleton classes.
     *
     * @return a orderabs object
     */
    public static OrderSize returnObject() {
        return singleSize;
    }

    @Override
    public int compare(File o1, File o2) {
        long lengthDiff = o1.length() - o2.length();
        if (lengthDiff == 0) {
            OrderAbs secondOrder = OrderAbs.returnObject();
            return secondOrder.compare(o1, o2);
        } else {
            return (int) lengthDiff;
        }
    }
}
