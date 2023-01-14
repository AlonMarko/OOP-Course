package filesprocessing;

/**
 * invalid usage exception class - relates to type 2 errors.
 */
public class UsageHandler extends TypeTwo {
    /**
     * constructor
     *
     * @param errorMessage - a message to display when an error occurs.
     */
    public UsageHandler(String errorMessage) {
        super(errorMessage);
    }
}
