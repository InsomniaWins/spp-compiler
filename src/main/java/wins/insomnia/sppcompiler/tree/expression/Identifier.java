package wins.insomnia.sppcompiler.tree.expression;


import wins.insomnia.sppcompiler.runtime.Environment;

public class Identifier extends Expression {

    private final String NAME;

    public Identifier(String name) {
        NAME = name;
    }

    @Override
    public String toString() {
        return "Identifier: {" + NAME + "}";
    }

    private Expression getValue(Environment environment) {
        return environment.getVariableEnvironment(NAME).getVariable(NAME).evaluate(environment);
    }

    @Override
    public Expression evaluate(Environment environment) {

        if (environment == null) {
            throw new RuntimeException("Identifier has no environment to evaluate from! : " + this);
        }

        return getValue(environment);

    }

}
