package wins.insomnia.sppcompiler.tree.expression;

import wins.insomnia.sppcompiler.tree.statement.Statement;
import wins.insomnia.sppcompiler.runtime.Environment;

public class Expression extends Statement {

    public Expression evaluate(Environment environment) {
        return this;
    }
}
