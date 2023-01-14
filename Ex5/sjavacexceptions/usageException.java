package oop.ex5.sjavacexceptions;

public class usageException extends SJavaException {
    //serial id.
    private static final long serialVersionUID = 1L;

    private static final String errMsg = "illegal number of arguments provided to the program. The simple java " +
            "compiler can check only one file at a time.";

    /**
     * The constructor of the exception
     */
    public usageException() {
        super(errMsg);
    }
}
