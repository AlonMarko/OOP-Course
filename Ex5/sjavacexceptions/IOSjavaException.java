package oop.ex5.sjavacexceptions;

import java.io.IOException;

/**
 * specific IO exception that extends the IOexception - used for problems with the java file itself.
 */
public class IOSjavaException extends IOException {
    private static final String ERROR = "IO Error: file problem was encountered while trying to read the file";

    private static final int exCode = 2;


    private final int errCode;

    public IOSjavaException() {
        errCode = exCode;
    }

    public void showError() {
        System.err.println(ERROR);
    }

    public void showCode() {
        System.out.println(errCode);
    }
}
