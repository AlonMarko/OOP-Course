package filesprocessing;

/**
 * type I error exception class - the program continues with "default" values as
 * instructed.
 */
public class TypeOne extends ProcessingExeption {
    /**
     * constructor
     *
     * @param errorMessage a message to display when an error occurs.
     */
    public TypeOne(String errorMessage) {
        super(errorMessage);
    }

}
