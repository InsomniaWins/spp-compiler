package wins.insomnia.sppcompiler.tree.literal;

public class LiteralNull extends Literal<String> {

    private static final String NULL_STRING = "NULL";

    public LiteralNull() {
        super(NULL_STRING);
    }

}
