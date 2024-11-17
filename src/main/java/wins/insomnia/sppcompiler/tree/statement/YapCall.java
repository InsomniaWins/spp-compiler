package wins.insomnia.sppcompiler.tree.statement;

import wins.insomnia.sppcompiler.tree.expression.Expression;

public class YapCall extends Statement {

    private Expression value;

    public YapCall(Expression value) {
        this.value = value;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "YapCall : {" + getValue() + "}";
    }

}
