package oop.ex5.scope;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import oop.ex5.method.*;
import oop.ex5.sjavacexceptions.SJavaException;

/**
 * factory for scopes - creates a scope with given parameters
 */
public class ScopeFactory {
    /**
     * creates a new scope, also does basic input checks.
     *
     * @param wrapper   - this scopes wrapper scope
     * @param inputLine - the declaration line of the scope (method or condition or null..)
     * @param type      - the scope type
     * @return - the newly created scope
     * @throws SJavaException
     */
    public static Scope scopeCreator(Scope wrapper, String inputLine, String type) throws SJavaException {
        if (wrapper == null) {
            return new Scope(Scope.GLOBAL);
        } else {
            switch (type) {
                case Scope.METHOD:
                    Pattern pattern = MethodRegex.methodDecPattern();
                    Matcher matcher = pattern.matcher(inputLine);
                    if (matcher.matches()) {
                        ArrayList<String> arguments =
                                new ArrayList<>(Arrays.asList(matcher.group(2).split(MethodRegex.COMMA)));
                        boolean isEmpty = MethodRegex.emptyLinePattern().matcher(matcher.group(2)).matches();
                        return new MethodScope(arguments, wrapper, isEmpty);
                    } else {
                        throw new IllegalArgumentException();
                    }
                case Scope.CONDITION:
                    return new Scope(wrapper, Scope.CONDITION);
            }
            throw new IllegalArgumentException();
        }
    }
}
