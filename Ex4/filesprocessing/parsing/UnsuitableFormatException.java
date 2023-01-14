package filesprocessing.parsing;

import filesprocessing.TypeTwo;

/**
 * exception for files not in the right format
 */
public class UnsuitableFormatException extends TypeTwo {
    /**
     * constructor
     *
     * @param errorMessage a message to display when an error occurs.
     */
    public UnsuitableFormatException(String errorMessage) {
        super(errorMessage);
    }

}
