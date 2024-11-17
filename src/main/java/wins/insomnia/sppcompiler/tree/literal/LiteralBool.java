package wins.insomnia.sppcompiler.tree.literal;

public class LiteralBool extends Literal<Boolean> {

    public static final String TRUE_SYNONYM = "frfr";
    public static final String FALSE_SYNONYM = "cap";

    public LiteralBool() {
        super(false);
    }

    public LiteralBool(Boolean value) {
        super(value);
    }

    @Override
    public String getReadableValue() {
        return getValue() ? TRUE_SYNONYM : FALSE_SYNONYM;
    }
}
