package oop.ex5.sjavacexceptions;

/**
 * class for assignment related exceptions
 */
public class assingmentException extends SJavaException {

    //serial id.
    private static final long serialVersionUID = 1L;

    private static  final String errMsg = "illegal assignment. Value assigned does not correspond to the type" +
            "of the variable.";

    /**
     * The constructor of the exception
     */
    public assingmentException() {
        super(errMsg);
    }

}
