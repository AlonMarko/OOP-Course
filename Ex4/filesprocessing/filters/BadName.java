package filesprocessing.filters;

import filesprocessing.TypeOne;

/**
 * exception for bad filter name
 */
public class BadName extends TypeOne {
    /**
     * constructor
     *
     * @param errorMessage a message to display when an error occurs.
     */
    public BadName(String errorMessage) {
        super(errorMessage);
    }
}
