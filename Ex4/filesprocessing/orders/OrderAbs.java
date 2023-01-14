package filesprocessing.orders;

import java.io.File;

/**
 * class imlements Abs ordering
 */
public class OrderAbs extends Order {
    /*
    data members
     */
    // a singleton class for OrderAbs - can have only 1 at a time
    private static final OrderAbs singletonAbs = new OrderAbs();

    /**
     * private constructor
     */
    private OrderAbs() {
    }

    /**
     * static return method for singleton classes.
     *
     * @return a orderabs object
     */
    public static OrderAbs returnObject() {
        return singletonAbs;
    }

    @Override
    public int compare(File o1, File o2) {
        return (o1.getAbsolutePath().compareTo(o2.getAbsolutePath()));
    }
}
