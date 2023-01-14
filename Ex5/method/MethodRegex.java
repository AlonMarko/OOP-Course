package oop.ex5.method;

import java.util.regex.Pattern;

/**
 * a regex class for methods - contains the regex and pattern getters for these.
 */
public abstract class MethodRegex {
    /*
    regex related to methods - declaration, naming spacing and etc
     */
    /**
     * the regex for a comma - which is used to separate arguments
     */
    public static final String COMMA = ",";
    /**
     * the regex used for spaces
     */
    public static final String SPACES = "\\s+";
    /**
     * the regex for the final keyword.
     */
    public static final String FINAL = "final";
    /**
     * the regex for a method name
     */
    public static final String METHOD_NAME = "^\\s*(?:void)\\s*[A-Za-z]\\w*\\s*";
    /**
     * teh regex for method declaration - ficided into two groups
     */
    //group 1: name regex, group 2: arguments regex
    public static final String METHOD_DECLARATION = "\\s*void\\s+(\\s*[A-Za-z]\\w*\\s*)\\s*\\(\\s*" +
            "([A-Za-z0-9_\\s,\\-]*)\\s*\\)\\s*\\{\\s*";
    /**
     * the regex for a block ending
     */
    public static final String BLOCK_END = "^\\s*\\}\\s*$";
    /**
     * the regex for an invalid line such as inverse parenthesis.
     */
    public static final String INVALID_LINE = "[^;\\}\\{]$";
    /**
     * the regex for return statement
     */
    public static final String RETURN = "^\\s*return\\s*;\\s*";
    /**
     * the regex for an empty line
     */
    public static final String EMPTY_LINE = "|\\s+";
    /**
     * the regex for a - comment line
     */
    public static final String COMMENT_LINE = "^//.+";
    /**
     * the regex for a name of a method in a method call line
     */
    public static final String METHOD_CALL_NAME = "[A-Za-z]+\\w*";
    /**
     * the regex for a method call - comprised from name followed by arguments
     */
    public static final String METHOD_CALL = String.format("\\s*(%s)\\s*\\(([A-Za-z0-9._\\s,\"'\\-]*)\\)\\s*;$"
            , METHOD_CALL_NAME);

    /*
    patterns matching the regex - these functions return a compiled pattern for each regex
     */
    public static Pattern methodNamePattern() {
        return Pattern.compile(METHOD_NAME);
    }

    public static Pattern methodDecPattern() {
        return Pattern.compile(METHOD_DECLARATION);
    }

    public static Pattern emptyLinePattern() {
        return Pattern.compile(EMPTY_LINE);
    }

    public static Pattern methodCallPattern() {
        return Pattern.compile(METHOD_CALL);
    }

    public static Pattern returnPattern() {
        return Pattern.compile(RETURN);
    }

    public static Pattern invalidPattern() {
        return Pattern.compile(INVALID_LINE);
    }

    public static Pattern scopeEndPattern() {
        return Pattern.compile(BLOCK_END);
    }

    public static Pattern commentPattern() {
        return Pattern.compile(COMMENT_LINE);
    }


}
