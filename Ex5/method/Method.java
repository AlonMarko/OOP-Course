package oop.ex5.method;

import oop.ex5.sjavacexceptions.*;
import oop.ex5.scope.*;
import oop.ex5.variable.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * class that represents a method in javac - it is a base class that contains
 * the method name, arguments and scope (which is actually global)
 */
public class Method {
    //data members

    /**
     * a list of the argument names this method gets.
     */
    private final ArrayList<String> methodArgNames;

    /**
     * a list of arguments types this method gets - corresponds to the names array
     */
    private final ArrayList<String> methodArgTypes;

    /**
     * the name of the method
     */
    private final String methodName;

    /**
     *  the number of arguments the method receives
     */
    private final int argNum;

    /**
     * constructor
     *
     * @param metName - the method name
     * @param metArgs - the method arguments - string array
     * @param argNum  - the method arguments number.
     * @throws methodDeclarationException - an exception if some aspect of the method creation is forbidden.
     */
    public Method(String metName, String[] metArgs, int argNum) throws methodDeclarationException {
        methodName = metName;
        this.argNum = argNum;
        if (this.argNum > 0) {
            methodArgNames = getArgNames(metArgs);
            methodArgTypes = getArgTypes(metArgs);
        } else {
            methodArgNames = new ArrayList<>();
            methodArgTypes = new ArrayList<>();
        }
    }


    /**
     * when a method is called - this checks if the call corresponds with the method as defined
     *
     * @param inputLine - the arguments of the method as a string
     * @param scope     - the scope this method is called in.
     * @return - true if valid call or false otherwise
     */
    public boolean validCall(String inputLine, Scope scope) throws methodCallException {
        Matcher matcher = MethodRegex.emptyLinePattern().matcher(inputLine);
        if (matcher.matches()) {
            if (this.argNum == 0) {
                return true;
            } else {
                throw new methodCallException();
            }
        }
        String[] splitLine = inputLine.split(MethodRegex.COMMA);
        if (splitLine.length != this.argNum) {
            return false;
        }
        int idx = 0;
        for (String arg : splitLine) {
            arg = arg.trim();
            String type = this.methodArgTypes.get(idx);
            Variable var = scope.getVariableFromScope(arg);
            if (var == null) {
                if (!Variable.checkExpression(type, arg)){
                    return false;
                }
            }
            else {
                String argType = var.getType();
                if (var.getValInitialized()) {
                    switch (type) {
                        case Variable.BOOLEAN:
                            return (argType.equals(Variable.INT) ||
                                    argType.equals(Variable.DOUBLE) || argType.equals(Variable.BOOLEAN));
                        case Variable.DOUBLE:
                            return (argType.equals(Variable.INT) || argType.equals(Variable.DOUBLE));
                        default:
                            return argType.equals(type);
                    }
                }
            }
            idx = idx + 1;
        }
        return true;
    }

    /**
     * getter for the method name
     *
     * @return string - the methods name
     */
    public String getMethodName() {
        return methodName;
    }


    /**
     * validates that the given argument for the method are legal as in the pattern is legal and there are
     * no duplicates. returns an array of the names of the arguments
     *
     * @param metArgs - string array of input to method
     * @return - list array of names of the arguments
     * @throws methodDeclarationException
     */
    private ArrayList<String> getArgNames(String[] metArgs) throws methodDeclarationException {
        ArrayList<String> names = new ArrayList<>();
        for (String argument : metArgs) {
            int idx = 1;
            String[] arg = argument.split(MethodRegex.SPACES);
            if (arg[0].trim().equals(MethodRegex.FINAL)) {
                idx = idx + 1;
            }
            // checks for sufficient length of argument declaration (2-3 strings)
            if (arg.length != idx + 1) {
                throw new methodDeclarationException();
            }
            String argName = arg[idx].trim();
            if (names.contains(argName)) {
                throw new methodDeclarationException();
            } else {
                // adding the names of the arguments to an array.
                names.add(argName);
            }
        }
        return names;
    }

    /**
     * checks if the given string is a valid variable type
     *
     * @param type - string
     * @throws methodDeclarationException
     */
    private void typeLegitCheck(String type) throws methodDeclarationException {
        for (String varType : Variable.variablesTypes) {
            if (type.equals(varType)) {
                return;
            }
        }
        throw new methodDeclarationException();
    }

    /**
     * this method creates an array of the types of the arguments the function calls that matches
     * to the name array. it also does sanity checks and throws an exception nas needed.
     *
     * @param metArgs - string array - method arguments
     * @return - array of the methods argument type
     * @throws methodDeclarationException
     */
    private ArrayList<String> getArgTypes(String[] metArgs) throws methodDeclarationException {
        ArrayList<String> types = new ArrayList<>();
        for (String argument : metArgs) {
            int idx = 0;
            String type;
            argument = argument.trim();
            if (argument.split(MethodRegex.SPACES)[idx].equals(MethodRegex.FINAL)) {
                idx = idx + 1;
            }
            type = argument.split(MethodRegex.SPACES)[idx];
            typeLegitCheck(type);
            types.add(type);
        }
        return types;
    }


}
