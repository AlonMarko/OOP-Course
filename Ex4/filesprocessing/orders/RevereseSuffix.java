package filesprocessing.orders;

import java.io.File;

/**
 * class that implements the reverse suffix for orders
 */
public class RevereseSuffix extends Order {
    /*
    data members
     */
    private final Order reverseOrder;

    /**
     * constructor
     *
     * @param normalOrder - the order object we have to reverse.
     */
    public RevereseSuffix(Order normalOrder) {
        reverseOrder = normalOrder;
    }

    @Override
    public int compare(File o1, File o2) {
        int result = reverseOrder.compare(o1, o2);
        return result * -1;
    }
}
