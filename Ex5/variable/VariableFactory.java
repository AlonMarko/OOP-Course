package oop.ex5.variable;

import oop.ex5.scope.*;
import oop.ex5.sjavacexceptions.*;

import java.util.LinkedList;

/**
 * This is a singleton creating the variables
 */
public class VariableFactory {
    /**
     * The instance of the singleton
     */
    private static final VariableFactory instance = new VariableFactory();

    /**
     * The final saved word
     */
    static private final String FINAL = "final ";

    /**
     * A separator comma in the declaration of variables
     */
    static private final String COMMA = ",";

    /**
     * The equals operator
     */
    static private final String EQUALS = "=";

    /**
     * The form of variable value assignment
     */
    static private final String EQUAL_REGEX = ".*=.*";

    /**
     * Empty string
     */
    static private final String EMPTY = "";

    /**
     * Space between keywords
     */
    static private final String SPACE = " ";

    /**
     * The number of arguments needed for a variable declaration
     */
    static private final int NUM_OF_ARGS = 2;

    /**
     * The index of the value to be assigned in a variable declaration
     */
    static private final int VAL_INDEX = 1;

    /**
     * The index where the name of the variable will be found
     */
    static private final int NAME_INDEX = 0;

    /**
     * This is the variable factory singleton constructor
     */
    private VariableFactory() {
    }


    /**
     * This function creates the variables declared in the declaration
     *
     * @param string the string of declaration of the variable
     * @param scope  the scope where the variable lives
     * @return a list of the variables initialized
     * @throws declarationException in case the variable was not correctly declared
     */
    public static LinkedList<Variable> variableFactory(String string, Scope scope) throws declarationException {
        LinkedList<Variable> varList = new LinkedList<>();
        LinkedList<String> varNames = new LinkedList<>();
        String[] decList = string.trim().substring(0, string.trim().length() - 1).split(COMMA);
        Variable currVar = parseVar(decList[NAME_INDEX], scope);
        varList.add(currVar);
        String type = currVar.getType();
        boolean isFinal = currVar.getIsFinal();
        for (int index = 1; index < decList.length; index++) {
            varList.add(createVar(isFinal, type, decList, index, scope, varNames));
        }
        return varList;
    }


    /**
     * This function parses the first declared variable in the line
     *
     * @param variable the declaration of the first variable in the line
     * @param scope    the scope where the variable lives
     * @return the variable we created from the declaration
     * @throws declarationException in case the variable was not correctly declared
     */
    static private Variable parseVar(String variable, Scope scope) throws declarationException {
        if (variable.contains(EQUALS) && (!variable.matches(EQUAL_REGEX))) {
            throw new declarationException();
        }
        String[] varParsed = variable.trim().split(EQUALS);
        Variable currVar = new Variable(varParsed[NAME_INDEX].trim(), scope);
        if (varParsed.length == NUM_OF_ARGS) {
            currVar.setFinalValue(varParsed[VAL_INDEX].trim());
        } else if ((varParsed.length > NUM_OF_ARGS) || currVar.checkFinalInitialized()) {
            throw new declarationException();
        }
        return currVar;
    }

    /**
     * This function is responsible for the creation of the second variable declared in the line and those who
     * come after him
     *
     * @param isFinal if the first variable declared was final
     * @param type    the type of the first variable
     * @param decList the list of variables to be declared
     * @param index   currently read index in decList
     * @param scope   the scope to which the variable will belong
     * @return the created variable
     * @throws declarationException in case the variable was illegally declared
     */
    static private Variable createVar(boolean isFinal, String type, String[] decList, int index, Scope scope,
                                      LinkedList<String> varNames) throws declarationException {
        String varDeclaration = EMPTY;
        if (isFinal) {
            varDeclaration = FINAL;
        }
        varDeclaration += type;
        varDeclaration += SPACE;
        if (decList[index].contains(EQUALS) && (!decList[index].matches(EQUAL_REGEX))) {
            throw new declarationException();
        }
        String[] varParsed = decList[index].trim().split(EQUALS);
        String varName = varParsed[NAME_INDEX].trim();
        if (varNames.contains(varName)) {
            throw new declarationException();
        }
        varNames.add(varName);
        varDeclaration += varName;
        Variable currVar = new Variable(varDeclaration, scope);
        if (varParsed.length == NUM_OF_ARGS) {
            currVar.setFinalValue(varParsed[VAL_INDEX].trim());
        } else if ((varParsed.length > NUM_OF_ARGS) || currVar.checkFinalInitialized()) {
            throw new declarationException();
        }
        return currVar;
    }
}
