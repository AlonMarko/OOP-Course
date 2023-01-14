package filesprocessing;

/**
 * Type II error exception class - throws an error message and stops the program.
 * incorrect usage - incorrect number of args etc...
 */
public class TypeTwo extends ProcessingExeption {
    /**
     * constructor
     *
     * @param errorMessage a message to display when an error occurs.
     */
    public TypeTwo(String errorMessage) {
        super(errorMessage);
    }
}
