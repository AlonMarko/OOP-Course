package oop.ex5.main;

import oop.ex5.method.*;
import oop.ex5.scope.*;
import oop.ex5.sjavacexceptions.*;
import oop.ex5.variable.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    /*
    constants
     */
    // line types
    private static final String MSG = "Invalid S-Java Structure - regocnized a line which does not comply " +
            "with any familiar legal line type.";
    private static final String METHOD_CALL = "METHOD CALL";
    private static final String ASSIGN_VALUE = "ASSIGN VALUE";
    private static final String METHOD_DEC = "METHOD DECLARATATION";
    private static final String VAR_DECLARATION = "VALUE DECLARATION";
    private static final String CONDITION = "CONDITION";
    private static final String NULL_LINE = "NULL LINE";
    private static final String COMMENT_LINE = "COMMENT LINE";
    private static final String INVALID_LINE = "INVALID LINE";
    private static final String RETURN = "RETURN";
    private static final String SCOPE_END = "SCOPE END";

    /**
     * The regex checking for condition
     */
    public static final String CONDITION_REGEX =
            "\\s*(?:if|while)\\s*\\(([A-Za-z0-9_\\s,\\|&\\-\\.]*)\\)\\s*\\{\\s*";

    /**
     * The regex for boolean separators
     */
    public static final String LOGIC_REGEX = "&&|\\|\\|";

    /**
     * The regex of the form of a boolean value
     */
    public static final String BOOL_VALUE = "\\s*(?:true|false|\\s*(?:\\-?\\d+(?:\\.\\d+)?))\\s*";

    /**
     * The false constant
     */
    private static final String FALSE = "false";

    /**
     * The true constant
     */
    private static final String TRUE = "true";

    /**
     * The regex checking for variable declaration
     */
    public static final String VARIABLE_DECLARATION =
            "([ \t]*(final)?[ \t]*(int|String|boolean|double|char)[ \t]*(([ \t]*[_a-zA-Z]+.*[ \t]*)[ \t]*(([ \t]*|=[ \t]*\"?.+\"?)(,|;$){1})?)*)[ \t]*;[ \t]*$";

    /**
     * The regex checking for assignment of values
     */
    public static final String ASSIGNMENT_REGEX = "[ \t]*\\w[ \t]*\\=[ \t]*\\w[ \t]*;[ \t]*$";

    /**
     * The scanner parsing the file
     */
    private String path;

    /**
     * The constructor
     *
     * @param path the path to the file
     */
    Parser(String path) {
        this.path = path;
    }

    /**
     * tries to load the buffer with a file reader and returns it. or
     * throws an error
     *
     * @param path - the file path
     * @return - buffered reader
     * @throws IOSjavaException - if file is not found
     */
    private BufferedReader fileReader(String path) throws IOSjavaException {
        try {
            return new BufferedReader(new FileReader(path));
        } catch (IOException e) {
            throw new IOSjavaException();
        }
    }

    /**
     * this method gets a line and returns a string representing what type of line we got.
     *
     * @param line - the line we want to get a type of
     * @return - string representing the line.
     */
    private String lineType(String line) {
        if (MethodRegex.methodDecPattern().matcher(line).matches()) {
            return METHOD_DEC;
        }
        if (MethodRegex.methodCallPattern().matcher(line).matches()) {
            return METHOD_CALL;
        }
        if (MethodRegex.commentPattern().matcher(line).matches()) {
            return COMMENT_LINE;
        }
        if (MethodRegex.emptyLinePattern().matcher(line).matches()) {
            return NULL_LINE;
        }
        if (MethodRegex.invalidPattern().matcher(line).matches()) {
            return INVALID_LINE;
        }
        if (MethodRegex.returnPattern().matcher(line).matches()) {
            return RETURN;
        }
        if (MethodRegex.scopeEndPattern().matcher(line).matches()) {
            return SCOPE_END;
        }
        if (line.matches(ASSIGNMENT_REGEX)) {
            return ASSIGN_VALUE;
        }
        if (line.matches(VARIABLE_DECLARATION)) {
            return VAR_DECLARATION;
        }
        if (line.matches(CONDITION_REGEX)) {
            return CONDITION;
        }
        return INVALID_LINE;
    }

    /**
     * line getter function as the one above but only for lines we care about in the first read of the file
     * so we can get to know all values.
     *
     * @param line - the line - string
     * @return - string of the line type
     */
    private String lineTypeFirstRead(String line) {
        if (MethodRegex.methodDecPattern().matcher(line).matches()) {
            return METHOD_DEC;
        }
        if (line.matches(ASSIGNMENT_REGEX)) {
            return ASSIGN_VALUE;
        }
        if (line.matches(VARIABLE_DECLARATION)) {
            return VAR_DECLARATION;
        }
        return null;
    }

    /**
     * checks for a legit return statement from a method
     *
     * @param scope          - the current scope - we want to heck if we reached its end
     * @param wrapper        - the wrapper scope - since we cant have more than 2 scopes deep
     * @param bufferedReader - the bufferedreader loaded with the file
     * @param splittedLine   - the line we are reading after it was split
     * @return - true or false
     * @throws SJavaException - if the line has invalid structure
     * @throws IOException    - if we have error while reading the file
     */
    private boolean returnCheck(Scope scope, Scope wrapper, BufferedReader bufferedReader, String[] splittedLine)
            throws SJavaException, IOException {
        try {
            if (wrapper == null) {
                throw new SJavaException(MSG);
            }
            if (scope.getScopeType().equals(Scope.CONDITION)) {
                splittedLine[0] = bufferedReader.readLine();
                return false;
            } else {
                String line = bufferedReader.readLine();
                while (line != null && (lineType(line).equals(CONDITION) ||
                        lineType(line).equals(NULL_LINE))) {
                    line = bufferedReader.readLine();
                }
                splittedLine[0] = line;
                return ((line != null) && (lineType(line).equals(SCOPE_END)));
            }
        } catch (IOException e) {
            throw new IOSjavaException();
        }
    }


    /**
     * predicate used for boolean expression checking.
     *
     * @return
     */
    private static boolean booleanPredicate(String splitCondition) {
        return (splitCondition.equals(TRUE) || splitCondition.equals(FALSE) || splitCondition.matches(BOOL_VALUE));
    }

    /**
     * predicate used for boolean expression checking.
     *
     * @return
     */
    private static boolean booleanTypeCheck(Variable variable) {
        return (variable.getType().equals(Variable.INT) ||
                variable.getType().equals(Variable.DOUBLE) ||
                variable.getType().matches(Variable.BOOLEAN));
    }

    /**
     * this method checks a condition line - if or while statemanets and validates it is legit.
     *
     * @param line    - the line we are validating
     * @param scope
     * @param wrapper
     * @throws SJavaException - an exception if we get an invalid argument
     */
    private void conditionCheck(String line, Scope scope, Scope wrapper) throws SJavaException {
        if (wrapper == null) {
            throw new conditionException();
        }
        Pattern pattern = Pattern.compile(CONDITION_REGEX);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            String[] conditionString = matcher.group(1).split(LOGIC_REGEX);
            for (String splitCondition : conditionString) {
                splitCondition = splitCondition.trim();
                if (!booleanPredicate(splitCondition)) {
                    Variable variable = scope.getVariableFromScope(splitCondition);
                    if ((variable == null) || (!booleanTypeCheck(variable))) {
                        throw new conditionException();
                    } else {
                        if (!variable.getValInitialized()) {
                            throw new conditionException();
                        }
                    }
                }
            }
        }
    }

    /**
     * checks wheter the method call is a valid call - if so its ok . otherwise we throw an exception
     *
     * @param line    - the line of the call
     * @param scope   - the scope this happened in
     * @param wrapper - the wrapper scope
     * @throws SJavaException - the exception we throw
     */
    private void checkMethodCall(String line, Scope scope, Scope wrapper) throws SJavaException {
        if (wrapper == null) {
            throw new methodCallException();
        }
        Matcher matcher = MethodRegex.methodCallPattern().matcher(line);
        if (matcher.matches()) {
            String name = matcher.group(1);
            Method method = scope.getMethodFromScope(name);
            if (method == null) {
                throw new methodCallException();
            }
            String args = matcher.group(2);
            if (method.validCall(args, scope)) {
            } else {
                throw new methodCallException();
            }
        }
    }

    /**
     * checks wheter the method name is valid for the declaration or if declared in scope which is not
     * global
     *
     * @param line    - the declaration line
     * @param scope   - the scope
     * @param wrapper -  the wrapper scope
     * @throws SJavaException
     */
    private void checkMethodDec(String line, Scope scope, Scope wrapper) throws SJavaException {
        if (wrapper != null) {
            throw new methodDeclarationException();
        }
        // splits when we see the start of method input args so we can check for the method name
        String name = line.split("\\(")[0];
        if (!MethodRegex.methodNamePattern().matcher(name).matches()) {
            throw new methodDeclarationException();
        }
    }

    /**
     * this method gets used when we reach a scope end(conditional scope) - if this is legal scope end than
     * thats
     * ok so we delete
     * all local values declared inside the scope , otherwise if it is not a conditional scope than we throw
     * an error - since we cannot end an scope that is not conditional without a return.
     *
     * @param scope - the scope
     * @return - the scope after we ended it
     * @throws SJavaException - exception as needed
     */
    private Scope conditionaalScopeEnd(Scope scope) throws SJavaException {
        if (scope.getScopeType().equals(Scope.CONDITION)) {
            scope.removeLocalVars();
            return scope;
        } else {
            throw new SJavaException(MSG);
        }
    }

    /**
     * same as the above but used for after we get an return statement inside a method.
     *
     * @param scope - the scope.
     * @return the scope after teh vars are removed.
     */
    private Scope methodScopeEnd(Scope scope) {
        scope.removeLocalVars();
        return scope;
    }

    /**
     * preforms teh first read of the file - will read method decs , vals decs and etc - only global things
     * in order to get ready for the full file walkthrough
     *
     * @param globalScope - the global scope
     * @throws SJavaException
     * @throws IOSjavaException
     */
    private void setGlobalScope(Scope globalScope) throws SJavaException, IOSjavaException {
        BufferedReader bufferedReader = fileReader(this.path);
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                String lineType = lineTypeFirstRead(line);
                if (lineType != null) {
                    if (lineType.equals(METHOD_DEC)) {
                        globalScope.addMethodToScope(MethodFactory.createMethod(line));
                        Queue<String> q = new LinkedList<>();
                        q.add(MethodRegex.COMMA);
                        while (!q.isEmpty()) {
                            line = bufferedReader.readLine();
                            if (line == null) throw new methodDeclarationException();
                            lineType = lineType(line);
                            if (lineType.equals(CONDITION)) {
                                q.add(MethodRegex.COMMA);
                            } else if (lineType.equals(SCOPE_END)) {
                                q.remove();
                            }
                        }
                    } else if (lineType.equals(VAR_DECLARATION)) {
                        globalScope.addVariablesToScope(VariableFactory.variableFactory(line, globalScope));
                    } else if (lineType.equals(ASSIGN_VALUE)) {
                        Variable.assignVal(line, globalScope);
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new IOSjavaException();
        }
    }

    /**
     * this method basically combines everything from above - when a scope start is recognized this method
     * is called and it parses the block - deals with all the variables, etc. after we checked the first line
     * to validate its legit - can be recursively called
     *
     * @param wrapper        - the wrapper scope
     * @param type           - this current scope type
     * @param bufferedReader - the reader object that we reading the file with
     * @param scopeFirstLine - the line that declares the block
     * @return - the scope after it was parsed
     * @throws SJavaException - exception as needed to be thrown
     */
    private Scope scopeParser(Scope wrapper, String type, BufferedReader bufferedReader,
                              String scopeFirstLine) throws SJavaException, IOSjavaException {
        Scope scope = ScopeFactory.scopeCreator(wrapper, scopeFirstLine, type);
        if (wrapper == null) {
            setGlobalScope(scope);
        }
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                String lineType = lineType(line);
                switch (lineType) {
                    case RETURN:
                        String[] splitLine = new String[]{line};
                        if (returnCheck(scope, wrapper, bufferedReader, splitLine)) {
                            return methodScopeEnd(scope);
                        }
                        line = splitLine[0];
                        continue;
                    case METHOD_CALL:
                        checkMethodCall(line, scope, wrapper);
                        break;
                    case METHOD_DEC:
                        checkMethodDec(line, scope, wrapper);
                        scopeParser(scope, Scope.METHOD, bufferedReader, line);
                        break;
                    case ASSIGN_VALUE:
                        if (wrapper != null) Variable.assignVal(line, scope);
                        break;
                    case VAR_DECLARATION:
                        if (wrapper != null)
                            scope.addVariablesToScope(VariableFactory.variableFactory(line, scope));
                        break;
                    case CONDITION:
                        conditionCheck(line, scope, wrapper);
                        scopeParser(scope, Scope.CONDITION, bufferedReader, line);
                        break;
                    case COMMENT_LINE:
                    case NULL_LINE:
                        break;
                    case INVALID_LINE:
                        throw new SJavaException(MSG);
                    case SCOPE_END:
                        return conditionaalScopeEnd(scope);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new IOSjavaException();
        }
        if (wrapper == null) {
            return scope;
        }
        throw new SJavaException(MSG);
    }

    /**
     * this method starts the parsing by creating the global scope and starts reading the file
     *
     * @throws SJavaException
     * @throws IOSjavaException
     */
    public void parseBegin() throws SJavaException, IOSjavaException {
        scopeParser(null, null, fileReader(this.path), null);
    }

}
