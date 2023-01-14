package oop.ex5.variable;

import oop.ex5.scope.*;
import oop.ex5.sjavacexceptions.*;

import java.util.LinkedList;

/**
 * This is the variable class responsible for handling the variables
 */
public class Variable {
    /**
     * The type of the variable
     */
    private String type;

    /**
     * The name of the variable
     */
    private String name;

    /**
     * The value of the variable
     */
    private String value;

    /**
     * The block to which the variable belongs
     */
    private Scope block;

    /**
     * A boolean indicating if the variable value was initialized
     */
    private boolean valInitialized;

    /**
     * A boolean indicating if the variable value is final
     */
    private boolean isFinal;

    /**
     * The final saved word with a space
     */
    static private final String FINAL = "final ";

    /**
     * The int type name
     */
    static public final String INT = "int";

    /**
     * The double type name
     */
    static public final String DOUBLE = "double";

    /**
     * The String type name
     */
    static public final String STRING = "String";

    /**
     * The boolean type name
     */
    static public final String BOOLEAN = "boolean";

    /**
     * The char type name
     */
    public static final String CHAR = "char";

    /**
     * The zero constant
     */
    private static final String ZERO = "0";

    /**
     * The equals operator
     */
    static private final String EQUALS = "=";

    /**
     * The false constant
     */
    private static final String FALSE = "false";

    /**
     * A list of the variable type
     */
    public static final String[] variablesTypes = {INT, DOUBLE, STRING, BOOLEAN, CHAR};

    /**
     * A form of the saved words in java
     */
    public static final String SAVED_WORDS = "^(int|String|boolean|double|final|true|false|void|if|while)$";

    /**
     * Empty string
     */
    static private final String EMPTY = "";


    /**
     * Space between keywords
     */
    static private final String SPACE = " ";

    /**
     * Tab between keywords
     */
    static private final String TAB = "\t";

    /**
     * The form of a variable declaration
     */
    static private final String VAR_DEC_REGEX = "^([ \t]*(final)?[ \t]*(int|String|boolean|double|char)[ \t]" +
            "*(([_a-zA-Z]+\\w+)|([a-zA-Z]+\\w*))[ \t]*)$";

    /**
     * The form of a double number declaration
     */
    static private final String DOUBLE_DEC_REGEX = "^([-]?[0-9]+[,.]?[0-9]*([/][0-9]+[.]?[0-9]*)*)$";

    /**
     * The form of a int number declaration
     */
    static private final String INT_DEC_REGEX = "^[ \t]*[-]?[0-9]+[ \t]*";

    /**
     * The form of a String variable declaration
     */
    static private final String STRING_DEC_REGEX = "(([ \t]*\".*\"$)|(\"\"))";

    /**
     * The form of a character variable declaration
     */
    static private final String CHAR_DEC_REGEX = "'.'";

    /**
     * The form of a boolean variable declaration
     */
    static private final String BOOLEAN_DEC_REGEX = "(?:^(([-]?[0-9]+[.]?[0-9]*([/][0-9]+[,.]?[0-9]*)*)$)|(true$)|(false$))";

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
     * This is a constructor for the variable
     *
     * @param declaration the string of declaration of the variable
     * @param scope       the scope where the variable lives
     * @throws declarationException in case of illegal variable declaration
     */
    public Variable(String declaration, Scope scope) throws declarationException {
        if (!declaration.matches(VAR_DEC_REGEX)) {
            throw new declarationException();
        }
        this.valInitialized = false;
        this.block = scope;
        String trimmedDec = declaration.trim();
        this.isFinal = trimmedDec.startsWith(FINAL);
        int index = NAME_INDEX;
        if (isFinal){
            index = VAL_INDEX;
        }
        String[] unfilteredParsedDeclaration = trimmedDec.split(FINAL)[index].split(SPACE);
        LinkedList<String> parsedDeclaration = new LinkedList<>();
        for (String var : unfilteredParsedDeclaration){
            if ((!var.equals(SPACE))&&(!var.equals(EMPTY))&&(!var.equals(TAB))){
                parsedDeclaration.add(var);
            }
        }
        if (parsedDeclaration.size() != 2) {
            throw new declarationException();
        }
        this.type = parsedDeclaration.get(NAME_INDEX).trim();
        this.name = parsedDeclaration.get(VAL_INDEX).trim();
        if (this.name.matches(SAVED_WORDS) || (scope.getLocalVariables(this.name))) {
            throw new declarationException();
        }
    }

    /**
     * The copy constructor
     * @param var the copied variable
     */
    public Variable(Variable var){
        this.type = var.type;
        this.name = var.name;
        this.block = var.block;
        this.valInitialized = var.valInitialized;
        this.isFinal = var.isFinal;
    }

    /**
     * This is a getter for the variable type
     *
     * @return the variable type
     */
    public String getType() {
        return type;
    }

    /**
     * This is a getter for the variable name
     *
     * @return the variable name
     */
    public String getName() {
        return name;
    }

    /**
     * This is a getter for the variable value
     *
     * @return the variable value or null if it is not initialized
     */
    public String getValue() {
        if (valInitialized) {
            return value;
        }
        return null;
    }

    /**
     * This function sets the value of a variable
     *
     * @param value the value to be set
     * @throws SJavaException in case of a value of illegal form
     */
    public void setValue(String value) throws SJavaException {
        if (this.isFinal) {
            throw new finalValException();
        }
        if (!checkExpression(this, value)) {
            throw new declarationException();
        }
        this.value = value;
        this.valInitialized = true;
    }

    /**
     * This function sets the value of a final value variable
     *
     * @param value the value to be set
     * @throws declarationException in case the value does not fit the variable
     */
    protected void setFinalValue(String value) throws declarationException {
        if (!checkExpression(this, value)) {
            throw new declarationException();
        }
        this.value = value;
        this.valInitialized = true;
    }

    /**
     * This function is a getter of the block where the variable is initialized
     *
     * @return the block where the variable is initialized
     */
    public Scope getBlock() {
        return block;
    }

    /**
     * This function returns if the variable is initialized
     *
     * @return boolean indicating if the variable is initialized
     */
    public boolean getValInitialized() {
        return valInitialized;
    }

    /**
     * This function returns if the variable is final
     *
     * @return boolean indicating if the variable is final
     */
    public boolean getIsFinal() {
        return isFinal;
    }

    public boolean valToBoolean() {
        if (type.equals(INT) || type.equals(DOUBLE)) {
            return !((value == null) || value.equals(ZERO));
        } else if (type.equals(BOOLEAN)) {
            return !(value == null || value.equals(ZERO) || value.equals(FALSE));
        } else if (type.equals(STRING) || type.equals(CHAR)) {
            return !((value == null) || value.equals(EMPTY));
        } else {
            return false;
        }
    }

    /**
     * setter for value initialization
     * @param status - the status to update
     */
    public void varInitialized(boolean status) {
        this.valInitialized = status;
    }


    /**
     * This function checks if the expression is legal to be contained in the variable
     *
     * @param var        the variable
     * @param expression the expression to be contained in the variable
     * @return true if it is legal, false otherwise
     */
    public static boolean checkExpression(Variable var, String expression) {
        String pattern;
        if (var.type.equals(STRING)) {
            pattern = STRING_DEC_REGEX;
        } else if (var.type.equals(INT)){
            pattern = INT_DEC_REGEX;
        } else if (var.type.equals(DOUBLE)) {
            pattern = DOUBLE_DEC_REGEX;
        }
        else if (var.type.equals(CHAR)) {
            pattern = CHAR_DEC_REGEX;
        } else {
            pattern = BOOLEAN_DEC_REGEX;
        }
        if (expression.matches(pattern)) {
            return true;
        } else {
            Variable currVar = var.getBlock().getVariableFromScope(expression.trim());
            return (currVar != null) && (currVar.getType().equals(var.getType())) && (currVar.valInitialized);
        }
    }

    /**
     * This function checks if the expression is legal to be contained in the variable
     *
     * @param type        the variable type
     * @param expression the expression to be contained in the variable
     * @return true if it is legal, false otherwise
     */
    public static boolean checkExpression(String type, String expression) {
        String pattern;
        if (type.equals(STRING)) {
            pattern = STRING_DEC_REGEX;
        } else if (type.equals(INT)){
            pattern = INT_DEC_REGEX;
        } else if (type.equals(DOUBLE)) {
            pattern = DOUBLE_DEC_REGEX;
        } else if (type.equals(CHAR)) {
            pattern = CHAR_DEC_REGEX;
        } else {
            pattern = BOOLEAN_DEC_REGEX;
        }
        return  (expression.matches(pattern));
    }

    /**
     * This function assigns a value to a variable
     * @param string the assignment
     * @param wrapper the scope where the variable belongs
     * @throws SJavaException in case of illegal arguments
     */
    public static void assignVal(String string, Scope wrapper) throws SJavaException {
        String[] declaration = string.trim().substring(0, string.trim().length() - 1).split(EQUALS);
        if (declaration.length != NUM_OF_ARGS) {
            throw new assingmentException();
        }
        Variable currVar = wrapper.getVariableFromScope(declaration[NAME_INDEX].trim());
        currVar.setValue(declaration[VAL_INDEX].trim());
    }

    /**
     * This function checks if a final value was not initialized
     * @return true if a final value was not initialized, false otherwise
     */
    public boolean checkFinalInitialized(){
        return isFinal && (!valInitialized);
    }
}

