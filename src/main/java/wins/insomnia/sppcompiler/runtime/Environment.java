package wins.insomnia.sppcompiler.runtime;

import wins.insomnia.sppcompiler.tree.literal.LiteralBool;
import wins.insomnia.sppcompiler.tree.literal.LiteralNull;
import wins.insomnia.sppcompiler.tree.expression.Expression;

import java.util.HashMap;

public class Environment {

    private final Environment PARENT;



    private final HashMap<String, Expression> VARIABLES;


    public Environment() {
        this.PARENT = null;
        this.VARIABLES = new HashMap<>();
    }

    public Environment(Environment parent) {

        this.PARENT = parent;
        this.VARIABLES = new HashMap<>();

    }

    public void initializeGlobalScope() {
        declareAndInitializeVariable("false", new LiteralBool(false));
        declareAndInitializeVariable("true", new LiteralBool(true));
    }

    public Environment getParent() {
        return PARENT;
    }

    public Expression declareAndInitializeVariable(String variableName, Expression value) {
        declareVariable(variableName);
        setVariable(variableName, value);
        return value;
    }

    public Expression declareVariable(String variableName) {

        if (VARIABLES.containsKey(variableName)) {
            throw new RuntimeException("Failed to declare variable! : already defined \"" + variableName + "\"");
        }

        VARIABLES.put(variableName, new LiteralNull());
        return new LiteralNull();

    }

    public Expression setVariable(String variableName, Expression value) {

        Environment environment = getVariableEnvironment(variableName);

        if (environment == null) {
            throw new RuntimeException("Could not find environment for variable! : " + variableName);
        }

        environment.VARIABLES.put(variableName, value);
        return value;
    }

    public Environment getVariableEnvironment(String variableName) {
        if (!VARIABLES.containsKey(variableName)) {

            if (getParent() != null) {
                return getParent();
            }

            throw new RuntimeException("Failed to get value of variable! : did not define var \"" + variableName + "\"");
        }

        return this;
    }

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
