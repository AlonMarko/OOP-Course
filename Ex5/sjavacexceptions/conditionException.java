package oop.ex5.sjavacexceptions;

/**
 * class for condition related exceptions
 */
public class conditionException extends SJavaException{
    //serial id.
    private static final long serialVersionUID = 1L;

    private static  final String errMsg = "illegal condition. Condition was provided with arguments that are not" +
            "boolean, constants or initialized boolean, integer or double variables. Arguments might have been" +
            "separated by illegal separators (not && or ||)";

    /**
     * The constructor of the exception
     */
    public conditionException() {
        super(errMsg);
    }
}
