package wins.insomnia.sppcompiler.tree.statement;

import wins.insomnia.sppcompiler.parse.literal.LiteralNull;
import wins.insomnia.sppcompiler.tree.expression.Expression;

public class VariableDeclaration extends Statement {

    private final String IDENTIFIER;
    private Expression value;

    public VariableDeclaration(String identifier) {
        this.IDENTIFIER = identifier;
        value = new LiteralNull();
    }

    public VariableDeclaration(String identifier, Expression value) {
        this.IDENTIFIER = identifier;
        this.value = value;
    }

    public String getIdentifier() {
        return IDENTIFIER;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "VariableDeclaration : {\"" + getIdentifier() + "\", " + getValue() + "}";
    }

}
