package oop.ex5.scope;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

import oop.ex5.method.*;
import oop.ex5.variable.*;

/**
 * class that refers to a scope - wheter its a global scope, if scope, while scope and method scope.
 */
public class Scope {
    /*
    constants
     */
    public final static String METHOD = "method";
    public final static String GLOBAL = "global";
    public final static String CONDITION = "condition";
    //    public final static String WHILE = "while";
    public final static String[] SCOPE_TYPE = {METHOD, GLOBAL, CONDITION};

    /*
    data members
     */
    /**
     * // the type of the specific scope
     */
    private final String scopeType;
    /**
     * // a set of all variables this scope can see
     */
    private HashMap<String, Variable> scopeVariables;
    /**
     * // a set of all methods this scope can see
     */
    private HashMap<String, Method> scopeMethods;
    /**
     * // a list of all variables that are specific to this scope
     */
    private ArrayList<String> innerVarNames;
    /**
     * an array containing inner variables names that have been initialized.
     */
    protected ArrayList<String> innerVarsInitialized;
    /**
     * // the block "above" this one
     */
    private Scope wrapperScope;

    /**
     * constructor - normal
     *
     * @param scopeType - the type of score - string
     */
    public Scope(String scopeType) {
        this.scopeType = scopeType;
        scopeVariables = new HashMap<>();
        scopeMethods = new HashMap<>();
        innerVarNames = new ArrayList<>();
        innerVarsInitialized = new ArrayList<>();
        wrapperScope = null;
    }

    /**
     * constructor of an inner scope
     *
     * @param wrapperScope - the scope that wrapps our this scope.
     * @param scopeType    the type of scope - string
     */
    public Scope(Scope wrapperScope, String scopeType) {
        this.scopeType = scopeType;
        scopeVariables = new HashMap<>();
        for (String name : wrapperScope.scopeVariables.keySet()) {
            Variable newVar = new Variable(wrapperScope.scopeVariables.get(name));
            scopeVariables.put(name, newVar);
        }
        scopeMethods = wrapperScope.scopeMethods;
        innerVarNames = new ArrayList<>();
        innerVarsInitialized = new ArrayList<>();
        this.wrapperScope = wrapperScope;

    }

    /**
     * scope type getter
     *
     * @return string - scope type
     */
    public String getScopeType() {
        return scopeType;
    }

    /**
     * getter for all methods in this scope.
     *
     * @return - set of methods
     */
    public HashMap<String, Method> getMethods() {
        return scopeMethods;
    }

    /**
     * getter for all variables in this scope
     *
     * @return - set of variables
     */
    public HashMap<String, Variable> getVariables() {
        return scopeVariables;
    }

    /**
     * getter for methods from this scope
     *
     * @param methodName - the name of the method we want
     * @return the method if it exists in the scope or null otherwise
     */
    public Method getMethodFromScope(String methodName) {
        return scopeMethods.getOrDefault(methodName, null);
    }

    /**
     * getter for variable from scope
     *
     * @param varName the name of the variable we want to get
     * @return - the variable if exists in the scope or null otherwise
     */
    public Variable getVariableFromScope(String varName) {
        return scopeVariables.getOrDefault(varName, null);
    }

    /**
     * setter of some sort - adding new variables to the scope.
     *
     * @param variable - the variable we need to add.
     */
    public void addVariableToScope(Variable variable) {
        scopeVariables.put(variable.getName(), variable);
        innerVarNames.add(variable.getName());
    }

    /**
     * setter of some sort - adding new variables to the scope.
     *
     * @param variableList - the variable List we need to add.
     */
    public void addVariablesToScope(LinkedList<Variable> variableList) {
        for (Variable variable : variableList) {
            scopeVariables.put(variable.getName(), variable);
            innerVarNames.add(variable.getName());
        }
    }

    /**
     * setter of some sort - adding a new method to the scope
     *
     * @param method - the method we want to add to the scope
     */
    public void addMethodToScope(Method method) {
        scopeMethods.put(method.getMethodName(), method);
    }

    /**
     * for a given variable name we check if we can shadow it in this scope
     *
     * @param varName the name of the variable
     * @return
     */
    public boolean isShadowingVariableValid(String varName) {
        return wrapperScope != null;
    }

    /**
     * adds a bunch of variables at once to the scope
     *
     * @param variables the variables to add
     */
    protected void addVariables(HashMap<String, Variable> variables) {
        scopeVariables.putAll(variables);
        innerVarNames.addAll(variables.keySet());
    }

    /**
     * remove all local variables - used when we finish a scope.
     */
    public void removeLocalVars() {
        for (String varName : innerVarNames) {
            scopeVariables.remove(varName);
        }
    }

    /**
     * Checks if a variable with such a name exists in the scope as a local variable
     *
     * @param name the variable name
     * @return boolean indicating if a variable with such a name exists in the scope as a local variable
     */
    public boolean getLocalVariables(String name) {
        return innerVarNames.contains(name);
    }


}
