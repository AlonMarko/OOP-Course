package oop.ex5.sjavacexceptions;

/**
 * used for exceptions related to method calls
 */
public class methodCallException extends SJavaException{
    //serial id.
    private static final long serialVersionUID = 1L;

    private static  final String errMsg = "illegal method call. The method was provided with illegal arguments" +
            "or a lacking number of arguments.";

    /**
     * The constructor of the exception
     */
    public methodCallException() {
        super(errMsg);
    }
}
