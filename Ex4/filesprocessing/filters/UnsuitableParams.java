package filesprocessing.filters;

import filesprocessing.TypeOne;

/**
 * unsuitable params exception
 */
public class UnsuitableParams extends TypeOne {
    /**
     * constructor
     *
     * @param errorMessage a message to display when an error occurs.
     */
    public UnsuitableParams(String errorMessage) {
        super(errorMessage);
    }
}
