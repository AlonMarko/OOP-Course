package oop.ex5.sjavacexceptions;

/**
 * a class for exception caused by S-java problems. basic class that prints a message.
 * will be used by sub classes to print specific messages or different code errors.
 */
public class SJavaException extends Exception {
    /**
     * the msg header
     */
    private static final String ERROR = "Error: ";
    /**
     * the exit code from the origram
     */
    private static final int exCode = 1;
    /**
     * the error message
     */
    private final String errMsg;
    /**
     * the error code
     */
    private final int errCode;

    public SJavaException(String errorMessage) {
        super();
        errMsg = errorMessage;
        errCode = exCode;
    }

    public void showError() {
        System.err.println(ERROR + errMsg);
    }

    public void showCode() {
        System.out.println(errCode);
    }

}
