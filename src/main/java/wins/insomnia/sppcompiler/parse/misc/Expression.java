package wins.insomnia.sppcompiler.parse.misc;

import wins.insomnia.sppcompiler.Statement;
import wins.insomnia.sppcompiler.runtime.Environment;

public class Expression extends Statement {

    public Expression evaluate(Environment environment) {
        return this;
    }
}
