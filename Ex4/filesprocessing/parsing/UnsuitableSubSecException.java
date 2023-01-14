package filesprocessing.parsing;

import filesprocessing.TypeTwo;

/**
 * exception for sub-sections with unsiutable (unvalid) names.
 */
public class UnsuitableSubSecException extends TypeTwo {
    /**
     * constructor
     *
     * @param errorMessage - a message to display when an error occurs.
     */
    public UnsuitableSubSecException(String errorMessage) {
        super(errorMessage);
    }
}
