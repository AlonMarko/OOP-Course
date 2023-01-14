package oop.ex5.method;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex5.sjavacexceptions.*;


/**
 * this class is used for creating a method instance - reading the line and deciding wheter or not it is
 * legal and proceeds to create the method.
 */
public class MethodFactory {
    /**
     * The number of arguments needed for a variable declaration
     */
    static private final int ARGUMENTS = 2;

    /**
     * The index of the value to be assigned in a variable declaration
     */
    static private final int NAME = 1;

    /**
     * The index where the name of the variable will be found
     */
    static private final int ZERO_ARGS = 0;

    /**
     * gets a line of code that should create a method - creates
     * the method and returns it. or throws exception
     *
     * @param declarationLine - the declaration line - string
     * @return the created method
     * @throws methodDeclarationException in case of bug
     */
    public static Method createMethod(String declarationLine) throws methodDeclarationException {
        Pattern pattern = MethodRegex.methodDecPattern();
        Matcher lineMatcher = pattern.matcher(declarationLine);
        if (lineMatcher.matches()) {
            int argsNum = 0;
            if (MethodRegex.emptyLinePattern().matcher(lineMatcher.group(ARGUMENTS)).matches()) {
                return new Method(lineMatcher.group(NAME), null, ZERO_ARGS);
            } else {
                String args = lineMatcher.group(ARGUMENTS);
                args = args.trim();
                String[] argsArray = args.split(MethodRegex.COMMA);
                for (int i = 0; i < argsArray.length; i++) {
                    argsNum++;
                    argsArray[i] = argsArray[i].trim();
                }
                return new Method(lineMatcher.group(1), argsArray, argsNum);
            }
        }
        throw new methodDeclarationException();

    }
}
