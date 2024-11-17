package wins.insomnia.sppcompiler.tree.literal;

public class LiteralString extends Literal<String> {

    public LiteralString() {
        super("");
    }

    public LiteralString(String value) {
        super(value);
    }
}
