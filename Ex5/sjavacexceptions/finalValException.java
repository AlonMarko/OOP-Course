package oop.ex5.sjavacexceptions;

/**
 * exception class for final variables exceptions
 */
public class finalValException extends SJavaException{

    //serial id.
    private static final long serialVersionUID = 1L;

    private static  final String errMsg = "illegal assignment in final value. Final values can obtain a value only" +
            "in their declaration.";

    /**
     * The constructor of the exception
     */
    public finalValException() {
        super(errMsg);
    }

}
