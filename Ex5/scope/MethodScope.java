package oop.ex5.scope;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;


import oop.ex5.sjavacexceptions.SJavaException;
import oop.ex5.variable.*;

/**
 * scope class for method a method scope.
 */
public class MethodScope extends Scope {
    //data members


    /**
     * a set of variable names that we do not allow shadowing - these are the input arguments for the method
     */
    private final HashSet<String> untouchableVariables;

    /**
     * constructor for the methodscope class - it adds the method arguments as variables
     *
     * @param arguments        - the line of arguments
     * @param wrapper          - the wrapper scope
     * @param emptyDeclaration - boolean condition whether to check if it has no args
     * @throws SJavaException
     */
    public MethodScope(ArrayList<String> arguments, Scope wrapper, boolean emptyDeclaration) throws SJavaException {
        super(wrapper, METHOD);
        untouchableVariables = new HashSet<>();
        if (!(emptyDeclaration)) {
            HashMap<String, Variable> args = new HashMap<>();
            for (String argument : arguments) {
                argument = argument.trim();
                Variable variable = new Variable(argument, this);
                variable.varInitialized(true);
                args.put(variable.getName(), variable);
            }
            addVariables(args);
        }
    }

    /**
     * remove all local variables - used when we finish a scope.
     */
    @Override
    public void removeLocalVars() {
        super.removeLocalVars();
        for (String variableName : innerVarsInitialized) {
            getVariableFromScope(variableName).varInitialized(false);
        }
    }

    /**
     * setter of some sort - adding new variables to the scope.
     *
     * @param variable - the variable we need to add.
     */
    @Override
    public void addVariableToScope(Variable variable) {
        untouchableVariables.add(variable.getName());
        super.addVariableToScope(variable);
    }

    /**
     * for a given variable name we check if we can shadow it in this scope
     *
     * @param varName the name of the variable
     * @return
     */
    @Override
    public boolean isShadowingVariableValid(String varName) {
        return !(untouchableVariables.contains(varName));
    }

    /**
     * adds a bunch of variables at once to the scope
     *
     * @param variables the variables to add
     */
    @Override
    public void addVariables(HashMap<String, Variable> variables) {
        super.addVariables(variables);
        untouchableVariables.addAll(variables.keySet());
    }
}
