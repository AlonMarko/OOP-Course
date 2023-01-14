package filesprocessing;

/**
 * exeption handling for the processing process - all unique errors will inherit from this class - this class
 * extends the Execption class -
 */
public class ProcessingExeption extends Exception {

    /*
     constants
    */
    // universal version identifier for a serializable class. deserialization uses
    // this to ensure that a loaded class corresponds exactly to a serilialized object - otherwise an
    //exception is thrown - since Exception is serialziable
    protected static final long serialVersionUID = 1L;

    /**
     * constructor
     *
     * @param errorMessage a message to display when an error occurs.
     */
    public ProcessingExeption(String errorMessage) {
        super(errorMessage);
    }
}

