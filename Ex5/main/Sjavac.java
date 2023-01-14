package oop.ex5.main;


import oop.ex5.sjavacexceptions.*;

import java.io.File;

/**
 * the main class of the S-Java-compiler.
 */
public class Sjavac {
    /**
     * The expected number of arguments
     */
    private static final int ARG_NUM = 1;

    /**
     * The index of the expected argument
     */
    private static final int ARG_INDEX = 0;

    /**
     * The return code of a successful run
     */
    private static final int SUCCESSFUL_RUN = 0;

    /**
     * The main function of the program
     * @param args the recieved arguments
     */
    public static void main(String args[]) {
        try {
            if (args.length == ARG_NUM) {
                File file = new File(args[ARG_INDEX]);
                if (file.isFile()) {
                    Parser fileParser = new Parser(args[ARG_INDEX]);
                    fileParser.parseBegin();
                    System.out.println(SUCCESSFUL_RUN);
                } else {
                    throw new IOSjavaException();
                }
            }
            else {
                throw new usageException();
            }
        } catch (SJavaException e) {
            e.showError();
            e.showCode();
        } catch (IOSjavaException e) {
            e.showError();
            e.showCode();
        }
    }
}
