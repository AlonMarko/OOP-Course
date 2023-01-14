package oop.ex5.sjavacexceptions;

/**
 * class for variable declaration related exceptions
 */
public class declarationException extends SJavaException{

    //serial id.
    private static final long serialVersionUID = 1L;

    private static  final String errMsg = "illegal declaration. Values ought to be declared in the following form" +
            "final? Type Name (= Value corresponding to the type)?, More variables ;";

    /**
     * The constructor of the exception
     */
    public declarationException() {
        super(errMsg);
    }
}
