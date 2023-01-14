package filesprocessing.orders;

import java.io.File;

/**
 * implements type ordering
 */
public class OrderType extends Order {
    /*
    data members
     */
    // a singleton class for OrderType - can have only 1 at a time
    private static final OrderType singleType = new OrderType();

    /**
     * private constructor
     */
    private OrderType() {
    }

    /**
     * static return method for singleton classes.
     *
     * @return a orderabs object
     */
    public static OrderType returnObject() {
        return singleType;
    }

    @Override
    public int compare(File o1, File o2) {
        String typeOne = TypeParse(o1);
        String typeTwo = TypeParse(o2);
        int result = typeOne.compareTo(typeTwo);
        if (result == 0) {
            OrderAbs secondOrder = OrderAbs.returnObject();
            return secondOrder.compare(o1, o2);
        } else {
            return result;
        }
    }

    /**
     * gets the file suffix
     *
     * @param file the file ot get the suffix from
     * @return the suffix.
     */
    private String TypeParse(File file) {
        String fileName = file.getName();
        String type = "";
        int dotPlace = fileName.lastIndexOf(".");
        if (dotPlace > 0) {
            type = fileName.substring(dotPlace + 1);
        }
        return type;
    }
}
