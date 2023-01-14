package filesprocessing.orders;

import filesprocessing.TypeOne;

/**
 * Unsuitable order exeption. for invalid orders.
 */
public class UnsuitableOrder extends TypeOne {

    /**
     * constructor
     *
     * @param errorMessage a message to display when an error occurs.
     */
    public UnsuitableOrder(String errorMessage) {
        super(errorMessage);
    }
}
