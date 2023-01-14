package filesprocessing.filters;

/**
 * exception for negative parameters
 */
public class NegParameters extends UnsuitableParams {
    /**
     * constructor
     *
     * @param errorMessage a message to display when an error occurs.
     */
    public NegParameters(String errorMessage) {
        super(errorMessage);
    }
}
