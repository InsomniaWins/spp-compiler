package wins.insomnia.sppcompiler.tree.expression;

import wins.insomnia.sppcompiler.parse.literal.LiteralInteger;
import wins.insomnia.sppcompiler.runtime.Environment;

public class AssignmentExpression extends Expression {

    private final Identifier ASSIGNEE;
    private final Expression VALUE;

    public AssignmentExpression(Identifier assignee, Expression value) {

        ASSIGNEE = assignee;
        VALUE = value;

    }

    public Identifier getAssignee() {
        return ASSIGNEE;
    }

    public Expression getValue() {
        return VALUE;
    }

    @Override
    public String toString() {
        return "AssignmentExpression : {" + getAssignee() + ", " + getValue() + "}";
    }

    @Override
    public Expression evaluate(Environment environment) {

        String variableName = getAssignee().getIdentifierString();
        return environment.setVariable(variableName, getValue().evaluate(environment));

    }

}
