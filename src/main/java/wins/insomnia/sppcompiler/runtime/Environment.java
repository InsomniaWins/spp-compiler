package wins.insomnia.sppcompiler.runtime;

import wins.insomnia.sppcompiler.parse.literal.Literal;
import wins.insomnia.sppcompiler.parse.literal.LiteralNull;
import wins.insomnia.sppcompiler.parse.misc.Expression;

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

    public Environment getParent() {
        return PARENT;
    }

    public void declareAndInitializeVariable(String variableName, Expression value) {
        declareVariable(variableName);
        setVariable(variableName, value);
    }

    public void declareVariable(String variableName) {

        if (VARIABLES.containsKey(variableName)) {
            throw new RuntimeException("Failed to declare variable! : already defined \"" + variableName + "\"");
        }

        VARIABLES.put(variableName, new LiteralNull());

    }

    public void setVariable(String variableName, Expression value) {

        Environment environment = getVariableEnvironment(variableName);

        if (environment == null) {
            throw new RuntimeException("Could not find environment for variable! : " + variableName);
        }

        environment.VARIABLES.put(variableName, value);

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
