package wins.insomnia.sppcompiler.tree.statement;

import wins.insomnia.sppcompiler.tree.expression.Expression;

public class ProgramEepyCall extends Statement {

    private Expression value;

    public ProgramEepyCall(Expression value) {
        this.value = value;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ProgramEepyCall : {" + getValue() + "}";
    }

}
