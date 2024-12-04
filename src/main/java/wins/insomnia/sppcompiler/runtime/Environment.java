package wins.insomnia.sppcompiler.runtime;

import wins.insomnia.sppcompiler.tree.literal.LiteralBool;
import wins.insomnia.sppcompiler.tree.literal.LiteralNull;
import wins.insomnia.sppcompiler.tree.expression.Expression;

import java.util.HashMap;
/*

Environments help with scope-variables. For example, a variable
defined in one closure cannot be used by outside of that closure but
can be used by nested closures.


 */
public class Environment {

    private final Environment PARENT;



    private final HashMap<String, Expression> VARIABLES;


    public Environment() {
        this.PARENT = null;
        this.VARIABLES = new HashMap<>();
    }

    // used for child-environments/closures
    public Environment(Environment parent) {

        this.PARENT = parent;
        this.VARIABLES = new HashMap<>();

    }

    // declares global-scope variables
    public void initializeGlobalScope() {
        declareAndInitializeVariable(LiteralBool.FALSE_SYNONYM, new LiteralBool(false));
        declareAndInitializeVariable(LiteralBool.TRUE_SYNONYM, new LiteralBool(true));
        declareAndInitializeVariable("fact", new LiteralBool(true));
    }

    // get parent environment/closure
    public Environment getParent() {
        return PARENT;
    }

    // declares a variable and initializes it to {value}
    public Expression declareAndInitializeVariable(String variableName, Expression value) {
        declareVariable(variableName);
        setVariable(variableName, value);
        return value;
    }

    // desclares a variable with an initial value of NULL
    public Expression declareVariable(String variableName) {

        if (VARIABLES.containsKey(variableName)) {
            throw new RuntimeException("Failed to declare variable! : already defined \"" + variableName + "\"");
        }

        VARIABLES.put(variableName, new LiteralNull());
        return new LiteralNull();

    }

    // sets a variable and returns the variable
    public Expression setVariable(String variableName, Expression value) {

        Environment environment = getVariableEnvironment(variableName);

        if (environment == null) {
            throw new RuntimeException("Could not find environment for variable! : " + variableName);
        }

        environment.VARIABLES.put(variableName, value);
        return value;
    }

    // gets the environment containing the variable name
    // if the variable is out of scope, it will throw an exception;
    public Environment getVariableEnvironment(String variableName) {
        if (!VARIABLES.containsKey(variableName)) {

            if (getParent() != null) {
                return getParent();
            }

            throw new RuntimeException("Failed to get value of variable! : did not define var \"" + variableName + "\"");
        }

        return this;
    }

    // gets variable expression
    public Expression getVariable(String variableName) {

        if (!VARIABLES.containsKey(variableName)) {

            if (getParent() != null) {
                return getParent().getVariable(variableName);
            }

            throw new RuntimeException("Failed to get value of variable! : did not defined \"" + variableName + "\"");
        }

        return VARIABLES.get(variableName);

    }

}
