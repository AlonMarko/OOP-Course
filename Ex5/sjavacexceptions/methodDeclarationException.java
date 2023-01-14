package oop.ex5.sjavacexceptions;

/**
 * exception class for method declaration problems
 */
public class methodDeclarationException extends SJavaException {
    //serial id.
    private static final long serialVersionUID = 1L;

    private static final String errMsg = "illegal method declaration, the format should be as follows: " +
            "void-name-(arguments) {";

    /**
     * The constructor of the exception
     */
    public methodDeclarationException() {
        super(errMsg);
    }
}
